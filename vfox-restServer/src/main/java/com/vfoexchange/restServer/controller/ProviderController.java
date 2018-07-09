package com.vfoexchange.restServer.controller;

import com.vfoexchange.restServer.dto.BillingDTO;
import com.vfoexchange.restServer.dto.ResponseDTO;
import com.vfoexchange.restServer.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProviderController {
    private static Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);
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
    @RequestMapping(value = "/update/provider/billing", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO updateProviderBilling(@RequestBody BillingDTO billingDTO) {
        ResponseDTO resp = new ResponseDTO();
        try {
            providerService.updateProviderBilling(billingDTO);
            resp.setCode("200");
            resp.setMsg("Service Providers billing updated successfully");
            LOGGER.info("Service Providers billing updated successfully");
        } catch (Exception e) {
            resp.setCode("400");
            resp.setMsg("Error occurred while update service providers billing");
            LOGGER.error("Error occurred while update service providers billing" + e.getMessage());
        }
        return resp;
    }

}
