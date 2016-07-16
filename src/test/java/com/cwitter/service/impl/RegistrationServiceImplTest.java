package com.cwitter.service.impl;

import com.cwitter.dao.UserDao;
import com.cwitter.dto.ApplicationResponseDto;
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
 * Time: 8:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationServiceImplTest {

    @InjectMocks
    RegistrationServiceImpl registrationService;

    @Mock
    UserDao userDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnFalseOnNullUser() {
        ApplicationResponseDto applicationResponseDto = registrationService.doRegistration(null);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseOnNullUserName() {
        User user = new User();
        ApplicationResponseDto applicationResponseDto = registrationService.doRegistration(user);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseOnNullPassword() {
        User user = new User();
        user.setUserName("userName");
        ApplicationResponseDto applicationResponseDto = registrationService.doRegistration(user);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseOnNullEmailId() {
        User user = new User();
        user.setUserName("userName");
        user.setPassword("password");
        ApplicationResponseDto applicationResponseDto = registrationService.doRegistration(user);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnFalseOnNullPhoneNumber() {
        User user = new User();
        user.setUserName("userName");
        user.setPassword("password");
        user.setEmailId("emailId@emailId.com");
        ApplicationResponseDto applicationResponseDto = registrationService.doRegistration(user);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }


    @Test
    public void shouldReturnFalseIfUserExists() {
        User user = new User();
        user.setUserName("userName");
        user.setPassword("password");
        user.setEmailId("emailId@emailId.com");
        user.setPhoneNumber("9999710381");
        Mockito.when(userDao.findByEmailIdOrPhoneNumberOrUserName(user.getEmailId(), user.getPhoneNumber(), user.getUserName())).thenReturn(user);
        ApplicationResponseDto applicationResponseDto = registrationService.doRegistration(user);
        TestCase.assertFalse(applicationResponseDto.isSuccess());
    }

    @Test
    public void shouldReturnTrueOnNewUserWithValidInformation() {
        User user = new User();
        user.setUserName("userName");
        user.setPassword("password");
        user.setEmailId("emailId@emailId.com");
        user.setPhoneNumber("9999710381");
        Mockito.when(userDao.findByEmailIdOrPhoneNumberOrUserName(user.getEmailId(), user.getPhoneNumber(), user.getUserName())).thenReturn(null);
        Mockito.when(userDao.save(user)).thenReturn(user);
        ApplicationResponseDto applicationResponseDto = registrationService.doRegistration(user);
        TestCase.assertTrue(applicationResponseDto.isSuccess());
    }
}
