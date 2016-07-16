package com.cwitter.controller;

import com.cwitter.dto.ApplicationResponseDto;
import com.cwitter.dto.AuthenticationResponseDto;
import com.cwitter.model.User;
import com.cwitter.service.RegistrationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 8:19 PM
 * To change this template use File | Settings | File Templates.
 */

@RestController
public class RegistrationController {
    Logger log = Logger.getLogger(RegistrationController.class);

    @Autowired
    RegistrationService registrationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApplicationResponseDto register(@RequestBody User user) {
        log.info("Registration request for user : " + user.getUserName());
        return registrationService.doRegistration(user);
    }

}
