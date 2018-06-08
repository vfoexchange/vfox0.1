package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.*;
import com.vfoexchange.restServer.model.Mail;
import com.vfoexchange.restServer.model.Services;
import com.vfoexchange.restServer.service.EmailServices;
import com.vfoexchange.restServer.service.UserService;
import com.vfoexchange.restServer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    /*
    Method for adding new user(user can be advisor or admin)
     */
    @RequestMapping(value = "/add/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDto) {
        String url;
        String msg = null;
        ResponseDTO resp = new ResponseDTO();
        ResponseEntity<ResponseDTO> responseEntity;
        if (userService.isAleadyExist(userDto.getUsername())) {
            resp.setCode(HttpStatus.ALREADY_REPORTED.toString());
            resp.setMsg("This email already exists in our system. Please try another email for registration");
            responseEntity =  new ResponseEntity<ResponseDTO>(resp,HttpStatus.OK);
            return responseEntity;
        }
        try {
            userService.addUser(userDto);
            url = AppUtil.getURL(environment.getProperty("angular.host"),environment.getProperty("angular.port"),AppUtil.getEncodedString(userDto.getUsername()));
            Mail mail = new Mail();
            mail.setFrom(environment.getProperty("spring.mail.username"));
            mail.setTo(userDto.getUsername());
            mail.setSubject("Verify Your Email Address");
            mail.setContent(AppUtil.getHtmlString(url));
            emailServices.sendMail(mail);
            resp.setCode(HttpStatus.OK.toString());
            resp.setMsg("Your account has been created,  please verify it by clicking the activation link that has been send to your email.");
            responseEntity =  new ResponseEntity<ResponseDTO>(resp,HttpStatus.OK);
        } catch (Exception  e){
            LOGGER.error("User "+e.getMessage());
            resp.setCode(HttpStatus.BAD_REQUEST.toString());
            resp.setMsg("mail has been successfully send ");
            responseEntity =  new ResponseEntity<ResponseDTO>(resp,HttpStatus.OK);
        }

        return responseEntity;
    }


    /*
    Method for adding new client
    */
    @RequestMapping(value = "/add/client", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addClient(@RequestBody ClientDetailsDTO clientDetailsDTO) {
        String msg = null;
        ResponseEntity<ResponseDTO> responseEntity;
        ResponseDTO resp = new ResponseDTO();
        if (userService.isAleadyExist(clientDetailsDTO.getUsername())) {
            resp.setCode(HttpStatus.ALREADY_REPORTED.toString());
            resp.setMsg("Client already register");
            responseEntity =  new ResponseEntity<ResponseDTO>(resp,HttpStatus.OK);
            return responseEntity;
        }
        try {
            userService.addClient(clientDetailsDTO);
            resp.setCode(HttpStatus.OK.toString());
            resp.setMsg("Client added successfully");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Client "+e.getMessage());
            resp.setCode(HttpStatus.BAD_REQUEST.toString());
            resp.setMsg("Error occured while adding new client");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);

        }
        return responseEntity;
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
