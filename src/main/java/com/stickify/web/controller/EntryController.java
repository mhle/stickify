package com.stickify.web.controller;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stickify.app.service.TwitterService;

/**
 * Maps top level entry points, e.g. /, /admin etc.
 */
@Controller
public class EntryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EntryController.class);
	
	@Inject
	private TwitterService twitterService;
	
	/**
	 * The random number generator used to get a tweet
	 */
	private Random rand = new Random();
	
	/**
	 * Handles request for home screen
	 * @return viewname
	 */
	@RequestMapping("/")
	public String home(Model model, UsernamePasswordAuthenticationToken token) {
		if (token != null) {
			List<Tweet> tweets = twitterService.findInspirationalTweets();
			//Generate a random index to get tweet
			int min = 0, max = tweets.size() - 1;
			int randomNum = rand.nextInt(max - min + 1) + min;
			model.addAttribute("tweet", tweets.get(randomNum));
		}
		return "home/index";
	}
	
	/**
	 * Handles requests for admin pages
	 * @return
	 */
	@RequestMapping("/admin")
	public String admin() {
		return "admin/welcome";
	}
	
}
