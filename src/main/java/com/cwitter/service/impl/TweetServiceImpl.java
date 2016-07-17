package com.cwitter.service.impl;

import com.cwitter.dao.TweetDao;
import com.cwitter.dao.UserDao;
import com.cwitter.dto.ApplicationResponseDto;
import com.cwitter.dto.TweetDto;
import com.cwitter.model.Tweet;
import com.cwitter.model.User;
import com.cwitter.service.TweetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class TweetServiceImpl implements TweetService {

    Logger log = Logger.getLogger(TweetServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private TweetDao tweetDao;

    @Override
    public ApplicationResponseDto postTweet(String token, TweetDto tweetDto) {
        ApplicationResponseDto applicationResponseDto = new ApplicationResponseDto();
        applicationResponseDto.setSuccess(false);
        applicationResponseDto.setMessage("unable to post, u r not authorized to tweet");

        if (token != null && isValidTweet(tweetDto)) {

            User user = userDao.findByToken(token);

            if (user != null) {
                Tweet tweet = new Tweet();
                tweet.setCreatedOn(LocalDateTime.now());
                tweet.setMessage(tweetDto.getTweet());
                tweet.setUser(user);

                tweetDao.save(tweet);
                applicationResponseDto.setSuccess(true);
                applicationResponseDto.setMessage("tweet posted");

            }

        }
        return applicationResponseDto;
    }

    @Override
    public List<TweetDto> getTweets() {

        Iterable<Tweet> tweets = tweetDao.findAll();

        List<TweetDto> tweetDtoList = new ArrayList<>();
        tweets.forEach((tweet) -> {
            TweetDto tweetDto = new TweetDto();
            tweetDto.setTweet(tweet.getMessage());
            tweetDto.setCreatedOn(tweet.getCreatedOn());
            tweetDto.setUserName(tweet.getUser().getUserName());
            tweetDtoList.add(tweetDto);
        });

        return tweetDtoList;
    }

    private boolean isValidTweet(TweetDto tweet) {
        return ((tweet != null) && (tweet.getTweet() != null) && (tweet.getTweet().trim().length() > 0));
    }
}
