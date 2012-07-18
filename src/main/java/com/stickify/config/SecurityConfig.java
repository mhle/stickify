package com.stickify.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:spring-security-config.xml"})
public class SecurityConfig {

}
