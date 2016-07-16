package com.cwitter.service;

import com.cwitter.dto.ApplicationResponseDto;
import com.cwitter.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RegistrationService {
    ApplicationResponseDto doRegistration(User user);
}
