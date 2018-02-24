package com;

import com.cc.WebApplication;
import com.cc.dao.UserDao;
import com.cc.entity.User;
import com.cc.pack.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.assertNotNull;

import javax.annotation.security.RunAs;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class UserTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private UserDao userDao;

    @Before
    public void initMock(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testDao(){
        Response response=restTemplate.getForObject("http://127.0.0.1:8080/user/1", Response.class);
        assertNotNull(response.getData());
    }

    @Test
    public void testController(){
        System.out.println(userDao.getUserById(1));
    }

}
