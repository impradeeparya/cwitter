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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnFalseWhenTokenIsNull() {
        TweetDto tweet = new TweetDto();
        ApplicationResponseDto applicationResponseDto = tweetService.saveTweet(null, tweet);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTweetDtoIsNull() {
        ApplicationResponseDto applicationResponseDto = tweetService.saveTweet("token", null);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTweetIsNull() {
        TweetDto tweetDto = new TweetDto();
        ApplicationResponseDto applicationResponseDto = tweetService.saveTweet("token", tweetDto);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTweetSizeIsZero() {
        TweetDto tweetDto = new TweetDto();
        tweetDto.setTweet(" ");
        ApplicationResponseDto applicationResponseDto = tweetService.saveTweet("token", tweetDto);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }


    @Test
    public void shouldReturnFalseOnInvalidTokenIsNull() {
        TweetDto tweetDto = new TweetDto();
        tweetDto.setTweet("tweet");
        Mockito.when(userDao.findByToken("token")).thenReturn(null);
        ApplicationResponseDto applicationResponseDto = tweetService.saveTweet("token", tweetDto);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnTrueOnValidTokenAndValidTweet() {
        TweetDto tweetDto = new TweetDto();
        tweetDto.setTweet("tweet");
        User user = new User();
        Mockito.when(userDao.findByToken("token")).thenReturn(user);
        Mockito.when(tweetDao.save(Mockito.any(Tweet.class))).thenReturn(null);
        ApplicationResponseDto applicationResponseDto = tweetService.saveTweet("token", tweetDto);
        TestCase.assertTrue(applicationResponseDto.isSuccess());
    }
}
