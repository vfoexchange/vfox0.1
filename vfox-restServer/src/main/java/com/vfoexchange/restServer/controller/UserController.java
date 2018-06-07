package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.*;
import com.vfoexchange.restServer.model.Mail;
import com.vfoexchange.restServer.model.Services;
import com.vfoexchange.restServer.service.EmailServices;
import com.vfoexchange.restServer.service.UserService;
import com.vfoexchange.restServer.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailServices emailServices;

    @Autowired
    Environment environment;
    /*
    Method for adding new user(user can be advisor or admin)
     */
    @RequestMapping(value = "/add/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDto) {
        userService.addUser(userDto);
        String url = null;
        String msg = null;
        ResponseEntity<String> responseEntity = null;
        if (userService.isValidUser(userDto.getUsername())) {
            msg = "Mail already register";
            responseEntity =  new ResponseEntity<String>(msg,HttpStatus.ALREADY_REPORTED);
            return responseEntity;
        }
        try {
            url = AppUtil.getURL(environment.getProperty("angular.host"),environment.getProperty("angular.port"),AppUtil.getEncodedString(userDto.getUsername()));
            Mail mail = new Mail();
            mail.setFrom(environment.getProperty("spring.mail.username"));
            mail.setTo(userDto.getUsername());
            mail.setSubject("Verify Your Email Address");
            mail.setContent(AppUtil.getHtmlString(url));
            emailServices.sendMail(mail);
            msg = "mail has been successfully send ";
            responseEntity = new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
        } catch (Exception  e){
            responseEntity = new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }


    /*
    Method for adding new client
    */
    @RequestMapping(value = "/add/client", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO addClient(@RequestBody ClientDetailsDTO clientDetailsDTO) {
        ResponseDTO resp = new ResponseDTO();
        try {
            userService.addClient(clientDetailsDTO);
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
    @RequestMapping(value = "/fetch/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO getUserDetail(@RequestBody UserDTO userDto) {
        ResponseDTO resp = new ResponseDTO();
        try {
            UserProfileDTO userProfile = userService.getUserProfile(userDto.getUsername());
            resp.setCode("200");
            resp.setMsg("User details fetched successfully");
            resp.setResult(userProfile);
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while fetching user details");
        }
        return resp;
    }

    /*
    Method for fetching advisor services
     */
    @RequestMapping(value = "/get/advisor/services", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    /*
    Method for updating advisor services
     */
    @RequestMapping(value = "/update/advisor/services", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO updateAdvisorServices(@RequestBody LinkedServicesDTO linkedServicesDTO) {
        ResponseDTO resp = new ResponseDTO();
        try {
            userService.updateAdvisorServices(linkedServicesDTO);
            resp.setCode("200");
            resp.setMsg("Advisor services updated successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while updating advisor services");
        }
        return resp;
    }

    /*
    Method for verification of user
    */
    @RequestMapping(value = "/user/verification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO userVerification(@RequestBody UserDTO userDTO) {
        ResponseDTO resp = new ResponseDTO();
        try {
            userService.userVerification(userDTO.getUsername());
            resp.setCode("200");
            resp.setMsg("User verification done successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while updating user verification");
        }
        return resp;
    }

}
