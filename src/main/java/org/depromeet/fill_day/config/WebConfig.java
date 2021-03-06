package org.depromeet.fill_day.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v1/**")
                        .allowedOrigins("fillday-feelday.firebaseapp.com")
                        .allowedMethods(HttpMethod.POST.name())
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}

