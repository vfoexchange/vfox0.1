package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.*;
import com.vfoexchange.restServer.model.Services;
import com.vfoexchange.restServer.service.EmailServices;
import com.vfoexchange.restServer.service.UserService;
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

        ResponseDTO resp = new ResponseDTO();
        ResponseEntity<ResponseDTO> responseEntity;
        if (userService.isAleadyExist(userDto.getUsername())) {
            resp.setCode(HttpStatus.ALREADY_REPORTED.toString());
            resp.setMsg("This email already exists in our system. Please try another email for registration");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
            LOGGER.info(userDto.getUsername()+" email already exists in our system.");
            return responseEntity;
        }
        try {
            emailServices.sendMail(userDto.getUsername());
            userService.addUser(userDto);
            resp.setCode(HttpStatus.OK.toString());
            resp.setMsg("Your account has been created,  please verify it by clicking the activation link that has been send to your email.");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
            LOGGER.info("Mail has been sent for verify");
        } catch (Exception e) {
            LOGGER.error("User " + e.getMessage());
            resp.setCode(HttpStatus.BAD_REQUEST.toString());
            resp.setMsg("Error occurred while sending mail.");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
        }
        return responseEntity;
    }


    /*
    Method for adding new client
    */
    @RequestMapping(value = "/add/client", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> addClient(@RequestBody ClientDetailsDTO clientDetailsDTO) {
        ResponseEntity<ResponseDTO> responseEntity;
        ResponseDTO resp = new ResponseDTO();
        if (userService.isAleadyExist(clientDetailsDTO.getUsername())) {
            resp.setCode(HttpStatus.ALREADY_REPORTED.toString());
            resp.setMsg("Client already register");
            responseEntity =  new ResponseEntity<ResponseDTO>(resp,HttpStatus.OK);
            LOGGER.info(clientDetailsDTO.getUsername()+" client already exists in our system.");
            return responseEntity;
        }
        try {
            userService.addClient(clientDetailsDTO);
            resp.setCode(HttpStatus.OK.toString());
            resp.setMsg("Client added successfully");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
            LOGGER.info("Client added successfully");
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
    public ResponseEntity<ResponseDTO> getUserDetail(@RequestBody UserDTO userDto) {
        ResponseEntity<ResponseDTO> responseEntity;
        ResponseDTO resp = new ResponseDTO();
        try {
            UserProfileDTO userProfile = userService.getUserProfile(userDto.getUsername());
            resp.setCode(HttpStatus.OK.toString());
            resp.setMsg("User details fetched successfully");
            resp.setResult(userProfile);
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
            LOGGER.info("User details fetched successfully");
        } catch (Exception e) {
            LOGGER.error("User details "+e.getMessage());
            resp.setCode(HttpStatus.BAD_REQUEST.toString());
            resp.setMsg("Error occurred while fetching user details");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
        }
        return responseEntity;
    }

    /*
    Method for fetching advisor services
     */
    @RequestMapping(value = "/get/advisor/services", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> getAdvisorServices(@RequestBody UserDTO userDto) {
        ResponseEntity<ResponseDTO> responseEntity;
        ResponseDTO resp = new ResponseDTO();
        try {
            List<Services> list = userService.getAdvisorServices(userDto.getUsername());
            resp.setCode(HttpStatus.OK.toString());
            resp.setMsg("Advisor services fetched successfully");
            resp.setResult(list);
            resp.setResultSize(list.size());
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
            LOGGER.info("Advisor services fetched successfully");
        } catch (Exception e) {
            LOGGER.error("Fetching advisor services "+e.getMessage());
            resp.setCode(HttpStatus.BAD_REQUEST.toString());
            resp.setMsg("Error occurred while fetching advisor services");
            responseEntity = new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
        }
        return responseEntity;
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
            LOGGER.info("Advisor services updated successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while updating advisor services");
            LOGGER.error("Error occurred while updating advisor services "+e.getMessage());
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
            LOGGER.info("User verification done successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while updating user verification");
            LOGGER.error("Error occurred while updating user verification "+e.getMessage());
        }
        return resp;
    }

}
