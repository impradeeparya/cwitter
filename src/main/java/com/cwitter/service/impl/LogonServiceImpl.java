package com.cwitter.service.impl;

import com.cwitter.dao.LogonDao;
import com.cwitter.dto.AuthenticationResponseDto;
import com.cwitter.model.User;
import com.cwitter.service.LogonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class LogonServiceImpl implements LogonService {

    private Logger log = Logger.getLogger(LogonServiceImpl.class);

    @Autowired
    LogonDao logonDao;


    @Override
    public AuthenticationResponseDto logon(String username, String password) {

        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();

        if (username != null && password != null) {
            User user = logonDao.findByUserNameAndPassword(username, password);
            if (user != null) {
                log.info("Login Success");
                authenticationResponseDto.setAuthenticated(true);
                authenticationResponseDto.setMessage("Login Success");
                authenticationResponseDto.setUsername(username);
                String token = genrateToken();
                authenticationResponseDto.setToken(token);

                /*updating token in db*/
                user.setToken(token);
                user.setCreatedOn(LocalDateTime.now());
                logonDao.save(user);
            } else {
                log.info("Login failed");
                authenticationResponseDto.setAuthenticated(false);
                authenticationResponseDto.setMessage("Login failed");
                authenticationResponseDto.setUsername(username);
            }
        }


        return authenticationResponseDto;
    }

    @Override
    public AuthenticationResponseDto logout(String token) {

        log.info("Logging out current user");
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setAuthenticated(true);
        authenticationResponseDto.setMessage("Logout Success");
        User user = logonDao.findByToken(token);

        if (user != null) {
            authenticationResponseDto.setUsername(user.getUserName());
            user.setToken(null);
            user.setCreatedOn(LocalDateTime.now());
            logonDao.save(user);
        }


        return authenticationResponseDto;
    }

    private String genrateToken() {
        log.info("generating token");
        return UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
    }

}
