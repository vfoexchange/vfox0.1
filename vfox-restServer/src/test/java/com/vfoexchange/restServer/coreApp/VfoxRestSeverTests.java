package com.vfoexchange.restServer.coreApp;

import com.vfoexchange.restServer.controller.UserController;
import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.dao.UserRoleDao;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.model.UserRole;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class VfoxRestSeverTests {

    @MockBean
    UserRoleDao userRoleDao;
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;
    @MockBean
    private UserDao userDao;

    /*
    Commented for now because fetch user API is changes and unit test needs to be updated
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUserDetailTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTORequest = new UserDTO();
        userDTORequest.setUsername("admin");
        userDTORequest.setPassword("pwd");
        userDTORequest.setRole("role");

        String userDTOJson = objectMapper.writeValueAsString(userDTORequest);

        User user = new User();
        user.setId(13);
        user.setUsername("admin");
        user.setFirstLogin(false);
        user.setRoleId(1);
        UserRole userRole = new UserRole();
        userRole.setRole("advisor");

        Mockito.when(userDao.findByUsernameWithState(Mockito.anyString())).thenReturn(user);
        Mockito.when(userRoleDao.findByRoleId(Mockito.anyInt())).thenReturn(userRole);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/fetch/user")
                .accept(MediaType.APPLICATION_JSON).content(userDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("User details fetched successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.userId").value(13))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.username").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.roleId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.firstLogin").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.role").value("advisor"))
                .andReturn();
    }

    @Test
    public void contextLoads() {
    }

}
