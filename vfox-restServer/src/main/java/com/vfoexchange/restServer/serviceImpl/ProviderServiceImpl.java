package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.dao.ServicesDao;
import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.dto.BillingDTO;
import com.vfoexchange.restServer.model.Billing;
import com.vfoexchange.restServer.model.Mail;
import com.vfoexchange.restServer.model.ServiceProviders;
import com.vfoexchange.restServer.service.EmailServices;
import com.vfoexchange.restServer.service.ProviderService;
import com.vfoexchange.restServer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
    private static Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);

    @Value("${mail.learn.more.subject}")
    private String subject;

    @Value("${mail.contactus.recipients}")
    private String recipients;

    @Autowired
    private ServicesDao servicesDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailServices emailServices;

    /*
    Method to fetch serviceProviders using service name
     */
    public List<ServiceProviders> getServiceProviders(String serviceName) {
        List<ServiceProviders> list = servicesDao.findServiceProviders(serviceName);
        return list;
    }

    /*
    Method to update provider billing with user
    */
    public void updateProviderBilling(BillingDTO billingDTO) {
        Optional<Billing> optional = servicesDao.fetchProviderBilling(billingDTO.getUserId(), billingDTO.getProviderId());
        if (!optional.isPresent()) {
            servicesDao.updateProviderBilling(billingDTO.getUserId(), billingDTO.getServiceId(), billingDTO.getProviderId());
            sendLearnMoreMail(billingDTO);
        } else {
            LOGGER.info("Billing already exists with user " + billingDTO.getUserName() + " and service provider " + billingDTO.getProviderName());
        }
    }

    /*
    Method for sending email for learn more request
     */
    private void sendLearnMoreMail(BillingDTO billingDTO) {
        try {
            Mail mail = new Mail();
            mail.setTo(recipients);
            mail.setSubject(subject);
            mail.setContent(AppUtil.getLearnMoreMailBody(billingDTO));
            emailServices.sendMail(mail);
            LOGGER.info("Email sent successfully for learn more request for user " + billingDTO.getUserName());
        } catch (Exception e) {
            LOGGER.error("Error occured while sending email" + e);
        }
    }

}
