package com.stickify.app.service;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;

/**
 * Service for accessing the Twitter API
 * @author mle
 *
 */
public interface TwitterService {
	
	/**
	 * Gets a list of <code>Tweet</code>s from user "@Inspire_Us"
	 * @return
	 */
	public List<Tweet> findInspirationalTweets();
}
