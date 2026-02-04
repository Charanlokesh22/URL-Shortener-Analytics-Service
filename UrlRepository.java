package com.company.urlshortener.repository;

import com.company.urlshortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByShortUrl(String shortUrl);

    Optional<UrlMapping> findByLongUrl(String longUrl);
}


