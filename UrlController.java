package com.company.urlshortener.controller;

import com.company.urlshortener.entity.UrlMapping;
import com.company.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public UrlMapping shortenUrl(@RequestParam String longUrl) {
        return urlService.shortenUrl(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public void redirect(@PathVariable String shortUrl,
                         HttpServletResponse response) throws IOException {

        String originalUrl = urlService.getOriginalUrl(shortUrl);
        response.sendRedirect(originalUrl);
    }
}
