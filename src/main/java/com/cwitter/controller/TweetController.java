package com.cwitter.controller;

import com.cwitter.dto.ApplicationResponseDto;
import com.cwitter.dto.TweetDto;
import com.cwitter.service.TweetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */

@RestController
public class TweetController {

    Logger log = Logger.getLogger(TweetController.class);


    @Autowired
    private TweetService tweetService;

    @RequestMapping(value = "/tweet", method = RequestMethod.POST)
    public ApplicationResponseDto tweet(@RequestHeader("Authorization") String token, @RequestBody TweetDto tweet) {
        log.info("posting tweets");
        return tweetService.postTweet(token, tweet);
    }

    @RequestMapping(value = "/fetchTweets", method = RequestMethod.GET)
    public List<TweetDto> fetchTweets() {
        log.info("fetching tweets");
        return tweetService.getTweets();
    }
}
