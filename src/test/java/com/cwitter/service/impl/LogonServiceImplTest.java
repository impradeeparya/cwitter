package com.cwitter.service.impl;

import com.cwitter.dao.UserDao;
import com.cwitter.dto.AuthenticationResponseDto;
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
 * Date: 16/7/16
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogonServiceImplTest {

    @InjectMocks
    LogonServiceImpl logonService;

    @Mock
    UserDao userDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /*Login Test cases*/

    @Test
    public void shouldReturnFalseOnNullUserNameOnLogin() {
        AuthenticationResponseDto authenticationResponseDto = logonService.logon(null, "password");
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnFalseOnNullPasswordOnLogin() {
        AuthenticationResponseDto authenticationResponseDto = logonService.logon("username", null);
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnTrueForValidUserNameAndPasswordOnLogin() {
        User user = new User();
        Mockito.when(userDao.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        AuthenticationResponseDto authenticationResponseDto = logonService.logon("username", "password");
        TestCase.assertTrue(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnFalseForInvalidUserNameAndPasswordOnLogin() {
        Mockito.when(userDao.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        AuthenticationResponseDto authenticationResponseDto = logonService.logon("username", "password");
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }


    /*Logout test cases*/

    @Test
    public void shouldReturnFalseOnNullTokenOnLogout() {
        AuthenticationResponseDto authenticationResponseDto = logonService.logout(null);
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnFalseForInvalidTokenOnLogout() {
        Mockito.when(userDao.findByToken(Mockito.anyString())).thenReturn(null);
        AuthenticationResponseDto authenticationResponseDto = logonService.logout("token");
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnTrueForValidUserTokenOnLogout() {
        User user = new User();
        Mockito.when(userDao.findByToken(Mockito.anyString())).thenReturn(user);
        Mockito.when(userDao.save(user)).thenReturn(user);
        AuthenticationResponseDto authenticationResponseDto = logonService.logout("token");
        TestCase.assertTrue(authenticationResponseDto.isAuthenticated());
    }


    /*Token validation test cases*/

    @Test
    public void shouldReturnTrueForValidTokenOnTokenValidation() {
        User user = new User();
        Mockito.when(userDao.findByToken(Mockito.anyString())).thenReturn(user);
        AuthenticationResponseDto authenticationResponseDto = logonService.validateToken("token");
        TestCase.assertTrue(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnFalseForInvalidUserTokenOnTokenValidation() {
        Mockito.when(userDao.findByToken(Mockito.anyString())).thenReturn(null);
        AuthenticationResponseDto authenticationResponseDto = logonService.validateToken("token");
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }


    @Test
    public void shouldReturnFalseForNullTokenOnTokenValidation() {
        AuthenticationResponseDto authenticationResponseDto = logonService.validateToken(null);
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }


}
