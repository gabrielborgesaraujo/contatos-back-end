package com.gba.contatosbackend.utils;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("profilePics", registry);
    }

    private void exposeDirectory(String dir, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dir);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        if(dir.startsWith("../")){
            dir = dir.replace("../", "");
        }
        registry.addResourceHandler("/" + dir + "/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }

}
