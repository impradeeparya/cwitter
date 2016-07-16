package com.cwitter.service;

import com.cwitter.dto.AuthenticationResponseDto;
import com.cwitter.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LogonService {
    AuthenticationResponseDto logon(String username, String password);

    AuthenticationResponseDto logout(String token);

    AuthenticationResponseDto validateToken(String authorization);
}
