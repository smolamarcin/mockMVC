package com.smola.demo.controller;

import com.google.gson.Gson;
import com.smola.demo.model.library.Author;
import com.smola.demo.model.library.Book;
import com.smola.demo.model.library.ISBN;
import com.smola.demo.model.library.Tittle;
import com.smola.demo.repository.LibraryRepository;
import com.smola.demo.service.LibraryService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LibraryControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnHttp200_whenGetOnAllBooks() throws Exception {
        String endPoint = "/library/books";
        this.mockMvc.perform(get(endPoint))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnHttp201_whenAddNewBook() throws Exception {
       /* //given
        String endPoint = "/library/books";
        Author author = new Author("Adam Mickiewicz");
        Tittle tittle = new Tittle("Pan Tadeusz");
        ISBN isbn = new ISBN("978-1-56619-909-4 ");

        Book bookToAdd = new Book.BookBuilder(author, tittle).setIsbn(isbn).build();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);
        //when - then
        this.mockMvc.perform(post(endPoint).contentType(contentType).content(bookToAddJSON))
                .andExpect(status().isCreated());
        this.mockMvc.perform(get(endPoint)).andDo(print());*/
    }

    @Test
    public void shouldAddBookToRepository_whenPostNewBook() throws Exception {
       /* //given
        String endPoint = "/library/books";
        Author author = new Author("Adam Mickiewicz");
        Tittle tittle = new Tittle("Pan Tadeusz");
        ISBN isbn = new ISBN("978-1-56619-909-4 ");

        Book bookToAdd = new Book.BookBuilder(author, tittle).setIsbn(isbn).build();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);
        //when
        this.mockMvc.perform(post(endPoint).contentType(contentType).content(bookToAddJSON));

        //then
        this.mockMvc.perform(get(endPoint))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));*/
    }
}