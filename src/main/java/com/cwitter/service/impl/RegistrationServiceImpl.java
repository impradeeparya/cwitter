package com.cwitter.service.impl;

import com.cwitter.dao.UserDao;
import com.cwitter.dto.ApplicationResponseDto;
import com.cwitter.model.User;
import com.cwitter.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserDao userDao;

    @Override
    public ApplicationResponseDto doRegistration(User user) {

        ApplicationResponseDto applicationResponseDto = new ApplicationResponseDto();
        applicationResponseDto.setSuccess(false);
        if (user != null) {
            if (isContainAllInformation(user)) {
                if (!isUserExists(user)) {
                    user.setCreatedOn(LocalDateTime.now());
                    userDao.save(user);
                    applicationResponseDto.setSuccess(true);
                    applicationResponseDto.setMessage("successfully registered, no need of email and phone number validation as we don't have mailing and messaging server");
                } else {
                    applicationResponseDto.setMessage("LOL, either username or email id or phone number already exists");
                }

            } else {
                applicationResponseDto.setMessage("Don't be smart please fill all information in registration form");
            }
        }

        return applicationResponseDto;
    }

    private boolean isUserExists(User user) {
        boolean isExists = false;
        User userResponse = userDao.findByEmailIdOrPhoneNumberOrUserName(user.getEmailId(), user.getPhoneNumber(), user.getUserName());
        if (userResponse != null) {
            isExists = true;
        }
        return isExists;
    }

    private boolean isContainAllInformation(User user) {
        return ((user.getUserName() != null) && (user.getPassword() != null) && (user.getEmailId() != null) && (user.getPhoneNumber() != null));
    }
}
