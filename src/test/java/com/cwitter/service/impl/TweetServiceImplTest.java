package com.cwitter.service.impl;

import com.cwitter.dao.TweetDao;
import com.cwitter.dao.UserDao;
import com.cwitter.dto.ApplicationResponseDto;
import com.cwitter.dto.TweetDto;
import com.cwitter.model.Tweet;
import com.cwitter.model.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class TweetServiceImplTest {

    @InjectMocks
    TweetServiceImpl tweetService;

    @Mock
    UserDao userDao;

    @Mock
    TweetDao tweetDao;

    private TweetDto tweetDto;
    private User user;
    private Tweet tweet;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tweetDto = new TweetDto();
        user = new User();
        tweet = new Tweet();

    }

    @Test
    public void shouldReturnFalseWhenTokenIsNull() {
        ApplicationResponseDto applicationResponseDto = tweetService.postTweet(null, tweetDto);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTweetDtoIsNull() {
        ApplicationResponseDto applicationResponseDto = tweetService.postTweet("token", null);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTweetIsNull() {
        ApplicationResponseDto applicationResponseDto = tweetService.postTweet("token", tweetDto);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTweetSizeIsZero() {
        tweetDto.setTweet(" ");
        ApplicationResponseDto applicationResponseDto = tweetService.postTweet("token", tweetDto);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }


    @Test
    public void shouldReturnFalseOnInvalidTokenIsNull() {
        tweetDto.setTweet("tweet");
        Mockito.when(userDao.findByToken("token")).thenReturn(null);
        ApplicationResponseDto applicationResponseDto = tweetService.postTweet("token", tweetDto);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnTrueOnValidTokenAndValidTweet() {
        tweetDto.setTweet("tweet");
        Mockito.when(userDao.findByToken("token")).thenReturn(user);
        Mockito.when(tweetDao.save(Mockito.any(Tweet.class))).thenReturn(null);
        ApplicationResponseDto applicationResponseDto = tweetService.postTweet("token", tweetDto);
        TestCase.assertTrue(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnEmptyListIfNoTweetsFound() {
        Mockito.when(tweetDao.findAll()).thenReturn(Collections.emptyList());
        List<TweetDto> tweets = tweetService.getTweets();
        TestCase.assertTrue(tweets.isEmpty());
    }

    @Test
    public void shouldReturnTweetsIfTweetsFound() {
        tweet.setMessage("This is tweet");
        user.setUserName("user");
        tweet.setUser(user);
        tweet.setCreatedOn(LocalDateTime.now());
        Mockito.when(tweetDao.findAll()).thenReturn(Arrays.asList(tweet));
        List<TweetDto> tweets = tweetService.getTweets();
        TestCase.assertTrue(tweets.size() > 0);
    }
}
