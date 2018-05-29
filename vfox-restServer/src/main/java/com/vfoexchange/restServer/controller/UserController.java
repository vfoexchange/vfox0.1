package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.ResponseDTO;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    Method for adding new user(user can be advisor or client or admin)
     */
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO getAccountDetail(@RequestBody UserDTO userDto) {
        ResponseDTO resp = new ResponseDTO();
        try {
            userService.addUser(userDto);
            resp.setCode("200");
            resp.setMsg("New user added successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occured while adding new user");
        }
        return resp;
    }

}
