package com.stickify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

/**
 * Configures Spring Social Twitter
 * @author mle
 *
 */
@Configuration
public class TwitterConfiguration {
	
	  /**
	   * Bean allows access to Twitter API without authentication
	   */
	  @Bean
	  @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	  public Twitter twitter() {
		  return new TwitterTemplate();
	  }
}
