package com.cwitter.service;

import com.cwitter.dto.ApplicationResponseDto;
import com.cwitter.dto.TweetDto;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TweetService {
    ApplicationResponseDto saveTweet(String token, TweetDto tweet);
}
