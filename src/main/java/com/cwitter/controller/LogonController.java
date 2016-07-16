package com.cwitter.controller;

import com.cwitter.dto.AuthenticationResponseDto;
import com.cwitter.service.LogonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class LogonController {

    Logger log = Logger.getLogger(LogonController.class);

    @Autowired
    LogonService logonService;

    @RequestMapping(value = "/logon", method = RequestMethod.POST)
    public AuthenticationResponseDto logon(@RequestParam String username, @RequestParam String password) {
        log.info("logon request for user : " + username);
        return logonService.logon(username, password);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public AuthenticationResponseDto logout(@RequestHeader("Authorization") String token) {
        log.info("logout request");
        return logonService.logout(token);
    }


    @RequestMapping(value = "/validateToken", method = RequestMethod.GET)
    public AuthenticationResponseDto validateToken(
            @RequestHeader String Authorization) {
        log.info("validating request for token : " + Authorization);
        return logonService.validateToken(Authorization);
    }
}

