package com.vfoexchange.restServer.coreApp;

import com.vfoexchange.restServer.controller.UserController;
import com.vfoexchange.restServer.dto.UserDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class VfoxRestSeverTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
   @Test
    public void getUserDetailTest() throws Exception{
       ObjectMapper objectMapper = new ObjectMapper();

       UserDTO userDTO = new UserDTO();
       userDTO.setUsername("user");
       userDTO.setPassword("pwd");
       userDTO.setRole("role");

       String userDTOJson = objectMapper.writeValueAsString(userDTO);
       RequestBuilder requestBuilder = MockMvcRequestBuilders
               .post("/fetch/user")
               .accept(MediaType.APPLICATION_JSON).content(userDTOJson)
               .contentType(MediaType.APPLICATION_JSON);

       MvcResult result = mockMvc.perform(requestBuilder).andReturn();

   }
    @Test
    public void contextLoads() {

    }

}
