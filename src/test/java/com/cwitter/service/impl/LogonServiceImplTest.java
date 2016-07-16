package com.cwitter.service.impl;

import com.cwitter.dao.LogonDao;
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
    LogonDao logonDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnFalseOnNullUserName() throws Exception {
        AuthenticationResponseDto authenticationResponseDto = logonService.logon(null, "password");
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnFalseOnNullPassword() throws Exception {
        AuthenticationResponseDto authenticationResponseDto = logonService.logon("username", null);
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnTrueForValidUserNameAndPassword() throws Exception {
        User user = new User();
        Mockito.when(logonDao.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        AuthenticationResponseDto authenticationResponseDto = logonService.logon("username", "password");
        TestCase.assertTrue(authenticationResponseDto.isAuthenticated());
    }

    @Test
    public void shouldReturnFalseForInvalidUserNameAndPassword() throws Exception {
        Mockito.when(logonDao.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        AuthenticationResponseDto authenticationResponseDto = logonService.logon("username", "password");
        TestCase.assertFalse(authenticationResponseDto.isAuthenticated());
    }
}
