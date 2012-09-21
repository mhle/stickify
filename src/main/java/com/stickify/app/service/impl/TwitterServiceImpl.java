package com.stickify.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import com.stickify.app.service.TwitterService;

/**
 * Service implementation for <code>TwitterService</code>
 * @author mle
 *
 */
@Service
public class TwitterServiceImpl implements TwitterService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterServiceImpl.class);
	
	/**
	 * Twitter source for inspirational quotes
	 */
	private static final String INSPIRE_US = "Inspire_Us";
	
	@Inject
	private Twitter twitter;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Tweet> findInspirationalTweets() {
		//TODO: Consider using Spring's @Cache for more efficient retrieval of tweets
		TimelineOperations timelineOperations = twitter.timelineOperations();
		List<Tweet> tweets = timelineOperations.getUserTimeline(INSPIRE_US, 1, 200);
		return tweets;
	}

}
