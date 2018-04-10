package com.smola.demo.controller;

import com.google.gson.Gson;
import com.smola.demo.model.Content;
import com.smola.demo.model.Greeting;
import com.smola.demo.repository.HelloWorldRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class HelloWorldControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private HelloWorldRepository helloWorldRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        fillRepositoryWithGreetings();
    }

    @After
    public void tearDown() {
        this.helloWorldRepository.deleteAll();
    }

    private void fillRepositoryWithGreetings() {
        Content content = new Content("Przykladowa wiadomosc");
        Content content2 = new Content("Przkyladowa wiadomosc 2");
        Content content3 = new Content("Przykladowa wiadomosc 3");
        helloWorldRepository.addGreeting(new Greeting(content));
        helloWorldRepository.addGreeting(new Greeting(content2));
        helloWorldRepository.addGreeting(new Greeting(content3));
    }

    @Test
    public void shouldReturnAllGreetings() throws Exception {
        mockMvc.perform(get("/greetings"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].content.text", is("Przykladowa wiadomosc")))
                .andExpect(jsonPath("$[1].content.text", is("Przkyladowa wiadomosc 2")))
                .andExpect(jsonPath("$[2].content.text", is("Przykladowa wiadomosc 3")))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldCreateGreeting() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(new Greeting(new Content("Pozdrowienia !!!!!!!!!")));
        mockMvc.perform(post("/greetings")
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnHttp200_whenDelete_existing_Greeting() throws Exception {
        Long toDeleteId = 0L;
        this.mockMvc.perform(delete("/greetings/" + toDeleteId))
                .andExpect(content().string("Successfully deleted! Id: " + toDeleteId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDecreaseNbOfGreetingsInRepository_whenDelete_existing_Greeting() throws Exception {
        Long toDeleteId = 0L;
        this.mockMvc.perform(delete("/greetings/" + toDeleteId))
                .andExpect(content().string("Successfully deleted! Id: " + toDeleteId))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/greetings")).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnHttp404_whenDelete_nonExisting_Greeting() throws Exception {
        int nonExistingId = 99;
        this.mockMvc.perform(delete("/greetings/" + nonExistingId))
                .andExpect(content().string("This ID does not exist!"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnHttp200_whenPut_existing_Greeting() throws Exception {
        Long existingId = 0L;
        Gson gson = new Gson();
        Greeting greetingToUpdate = new Greeting(1, new Content("x"));
        String json = gson.toJson(greetingToUpdate);
        this.mockMvc.perform(put("/greetings/" + existingId)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdate_whenPut_existing_Greeting() throws Exception {
        //given
        Long existingId = 0L;
        Gson gson = new Gson();
        String message = "bulbulator";
        Greeting greetingToUpdate = new Greeting(1, new Content(message));
        String json = gson.toJson(greetingToUpdate);
        //when
        this.mockMvc.perform(put("/greetings/" + existingId)
                .contentType(contentType)
                .content(json));
        //then
        this.mockMvc.perform(get("/greetings"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(existingId.intValue())))
                .andExpect(jsonPath("$[0].content.text", is(message)));

    }
}