package com.example.storage.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class CookieConfig {
    @Bean
    public DefaultCookieSerializer defaultCookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setSameSite("Strict"); // Set SameSite attribute
        serializer.setUseSecureCookie(false); // Set Secure attribute
        serializer.setUseHttpOnlyCookie(false); // Turn off HttpOnly attribute

        return serializer;
    }
}
