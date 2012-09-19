package com.stickify.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Triggers component scanning
 * @author mle
 *
 */
@Configuration
@ComponentScan(basePackages="com.stickify")
public class ComponentConfig {

}
