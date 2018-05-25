package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.service.UserService;
import com.vfoexchange.restServer.dto.UserDTO;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    Method for adding new user(as of now only for adviser, later should be moved to generic one)
     */
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO getAccountDetail(@RequestBody UserDTO userDto) {
        ResponseDTO resp = new ResponseDTO();
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            userService.addUser(user);
            resp.setCode("200");
            resp.setMsg("New user added successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occured while adding new user");
        }
        return resp;
    }

}
