package com.jihoon.controller;

import com.jihoon.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class MainControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }


//    @Test
//    public void getCounterApi() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/counter-api/test"))
//                .andExpect(status().isOk());
//    }

    @Test
    public void getCounterApiSearch() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("/counter-api/search")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"searchText\":[\"Duis\"]}")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.counts[0].name").value("Duis")))
                .andExpect((jsonPath("$.counts[0].count").value(11)));

        mockMvc.perform(MockMvcRequestBuilders.post("/counter-api/search")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"searchText\":[\"Duis\",\"Sed\",\"Donec\",\"Augue\",\"Pellentesque\",\"123\"]}")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.counts[0].name").value("Duis")))
                .andExpect((jsonPath("$.counts[0].count").value(11)))
                .andExpect((jsonPath("$.counts[1].name").value("Sed")))
                .andExpect((jsonPath("$.counts[1].count").value(16)))
                .andExpect((jsonPath("$.counts[2].name").value("Donec")))
                .andExpect((jsonPath("$.counts[2].count").value(8)))
                .andExpect((jsonPath("$.counts[3].name").value("Augue")))
                .andExpect((jsonPath("$.counts[3].count").value(7)))
                .andExpect((jsonPath("$.counts[4].name").value("Pellentesque")))
                .andExpect((jsonPath("$.counts[4].count").value(6)))
                .andExpect((jsonPath("$.counts[5].name").value("123")))
                .andExpect((jsonPath("$.counts[5].count").value(0)));
    }

    @Test
    public void getCounterApiTopRanks5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/counter-api/top/3").accept("text/csv;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("vel|17\neget|17\nsed|16\n"));

        mockMvc.perform(MockMvcRequestBuilders.get("/counter-api/top/5").accept("text/csv;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("vel|17\neget|17\nsed|16\nin|15\net|14\n"));
    }
}

