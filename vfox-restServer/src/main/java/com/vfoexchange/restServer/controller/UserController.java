package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.ResponseDTO;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.model.Services;
import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    Method for adding new user(user can be advisor or client or admin)
     */
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addUser(@RequestBody UserDTO userDto) {
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

    /*
    Method for fetching user details
     */
    @RequestMapping(value = "/fetch/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO getUserDetail(@RequestBody UserDTO userDto) {
        ResponseDTO resp = new ResponseDTO();
        try {
            User user = userService.getUser(userDto.getUsername());
            resp.setCode("200");
            resp.setMsg("User details fetched successfully");
            resp.setResult(user);
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while fetching user details");
        }
        return resp;
    }

    /*
    Method for fetching advisor services
     */
    @RequestMapping(value = "/get/advisor/services", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO getAdvisorServices(@RequestBody UserDTO userDto) {
        ResponseDTO resp = new ResponseDTO();
        try {
            List<Services> list = userService.getAdvisorServices(userDto.getUsername());
            resp.setCode("200");
            resp.setMsg("Advisor services fetched successfully");
            resp.setResult(list);
            resp.setResultSize(list.size());
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while fetching advisor services");
        }
        return resp;
    }

}
