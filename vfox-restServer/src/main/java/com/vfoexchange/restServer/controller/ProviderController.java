package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.ResponseDTO;
import com.vfoexchange.restServer.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProviderController {

    @Autowired
    ProviderService providerService;

    /*
    Method for fetching service providers linked with service
     */
    @RequestMapping(value = "/get/serviceProviders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO getServiceProviders(@RequestParam String servicesName) {
        ResponseDTO resp = new ResponseDTO();
        try {
            resp.setResult(providerService.getServiceProviders(servicesName));
            resp.setCode("200");
            resp.setMsg("Service Providers fetched successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while fetching service  providers");
        }
        return resp;
    }

    /*
    Method for updating provider billing with user
    */
    @RequestMapping(value = "/update/provider/billing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO updateProviderBilling(@RequestParam String userName, @RequestParam String providerName) {
        ResponseDTO resp = new ResponseDTO();
        try {
            providerService.updateProviderBilling(userName, providerName);
            resp.setCode("200");
            resp.setMsg("Service Providers billing updated successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while update service providers billing");
        }
        return resp;
    }

}
