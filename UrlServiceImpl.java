package com.company.urlshortener.service;

import com.company.urlshortener.entity.UrlMapping;
import com.company.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;  

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public UrlMapping shortenUrl(String longUrl) {

        // Avoid duplicate long URLs
        return urlRepository.findByLongUrl(longUrl)
                .orElseGet(() -> {
                    String shortCode = UUID.randomUUID()
                            .toString()
                            .substring(0, 6);

                    UrlMapping mapping = new UrlMapping(shortCode, longUrl);
                    return urlRepository.save(mapping);
                });
    }

    @Override
    public String getOriginalUrl(String shortUrl) {

        UrlMapping mapping = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        mapping.setClickCount(mapping.getClickCount() + 1);
        urlRepository.save(mapping);

        return mapping.getLongUrl();
    }
}

