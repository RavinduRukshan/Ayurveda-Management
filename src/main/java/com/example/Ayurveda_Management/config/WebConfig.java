package com.example.Ayurveda_Management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map the '/uploads/**' URL pattern to the local 'uploads' folder
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

}
