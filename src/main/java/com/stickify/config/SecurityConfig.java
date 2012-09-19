package com.stickify.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Loads xml configuration for Spring Security
 * @author mle
 *
 */
@Configuration
@ImportResource({"classpath:spring-security-config.xml"})
public class SecurityConfig {

}
