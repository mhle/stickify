package com.stickify.config;

import java.util.List;

import javax.inject.Inject;

import nz.net.ultraq.web.thymeleaf.LayoutDialect;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.stickify.web.interceptor.SecurityInterceptor;

@Configuration
@EnableWebMvc
@ImportResource({"classpath:spring-data-jpa-config.xml"})
@PropertySource("classpath:application.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Inject
    private Environment environment;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SecurityInterceptor());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJacksonHttpMessageConverter());
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(environment.getRequiredProperty("message.source.basename"));
        messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(environment.getRequiredProperty("message.source.use.code.as.default.message")));

        return messageSource;
    }
	
	/*
	 * Thymeleaf setup 
	 */
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
	@Bean 
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix(environment.getRequiredProperty("thymeleaf.resolver.prefix"));
		templateResolver.setSuffix(environment.getRequiredProperty("thymeleaf.resolver.suffix"));
		templateResolver.setTemplateMode(environment.getRequiredProperty("thymeleaf.resolver.templatemode"));
		templateResolver.setCacheable(Boolean.parseBoolean(environment.getRequiredProperty("thymeleaf.resolver.cacheable")));
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(layoutDialect());
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thymeLeafViewResolver() {
		String[] viewNames = {"*"};
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		thymeleafViewResolver.setOrder(1);
		thymeleafViewResolver.setViewNames(viewNames);
		thymeleafViewResolver.setCharacterEncoding(environment.getRequiredProperty("thymeleaf.view.resolver.encoding"));
		return thymeleafViewResolver;
	}
	
}