package com.company.urlshortener.service;

import com.company.urlshortener.entity.UrlMapping;

public interface UrlService {

    UrlMapping shortenUrl(String longUrl);

    String getOriginalUrl(String shortUrl);
}





