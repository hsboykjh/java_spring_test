package com.jihoon.config;

import com.jihoon.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Base64;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, BasicSecurity.class})
@SpringBootTest
@EnableWebSecurity
public class BasicSecurityTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void BasicAuthDecodingTest() {
        // Questions Credentials decoding
        byte[] decodedBytes = Base64.getDecoder().decode("b3B0dXM6Y2FuZGlkYXRlcw==");

        String decodedString = new String(decodedBytes);
        System.out.println("decodedString: "+decodedString);
    }

    @Test
    public void BasicAuthenticationTest() {

        String username = "user1";
        String password = "password1";
        String credential = username+":"+password;

        byte[] authEncBytes = Base64.getEncoder().encode((credential).getBytes());
        String encodedString = new String(authEncBytes);

        System.out.println("encodedString: "+encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        String decodedString = new String(decodedBytes);
        System.out.println("decodedString: "+decodedString);

        assertThat(decodedString).isEqualTo(credential);
    }

    @Test
    @WithMockUser(username = "optus", password = "candidates")
    public void connectSecurity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/counter-api/top/2").accept("text/csv;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
