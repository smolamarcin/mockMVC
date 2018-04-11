package com.smola.demo.controller;

import com.google.gson.Gson;
import com.smola.demo.model.library.Author;
import com.smola.demo.model.library.Book;
import com.smola.demo.model.library.ISBN;
import com.smola.demo.model.library.Tittle;
import com.smola.demo.repository.BookRepository;
import com.smola.demo.service.LibraryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LibraryControllerTest {
    private static final String endPoint = "/library/books";
    private static final String SAMPLE_AUTHOR_NAME = "Adam Mickiewicz";
    private static final String SAMPLE_BOOK_TITTLE = "Pan Tadeusz";
    private static final String SAMPLE_BOOK_ISBN = "978-1-56619-909-4";
    private MockMvc mockMvc;
    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookRepository bookRepository;

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
    public void should_ReturnHttp200_whenGetOnAllBooks() throws Exception {
        this.mockMvc.perform(get(endPoint))
                .andExpect(status().isOk());
    }

    @Test
    public void should_ReturnHttp201_whenPostNewBook() throws Exception {
        //given
        Book bookToAdd = createSampleBook();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);
        //when - then
        this.mockMvc.perform(post(endPoint).contentType(contentType).content(bookToAddJSON))
                .andExpect(status().isCreated());
        this.mockMvc.perform(get(endPoint)).andDo(print());
    }

    @Test
    public void should_AddBookToRepository_whenPostNewBook() throws Exception {
        //given
        Book bookToAdd = createSampleBook();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);
        //when
        this.mockMvc.perform(post(endPoint).contentType(contentType).content(bookToAddJSON));

        //then
        this.mockMvc.perform(get(endPoint))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].author.name", is(SAMPLE_AUTHOR_NAME)))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void should_ReturnBook_whenGetById() throws Exception {
        //given
        int idToGet = 1;
        String endPointToGetBookById = String.format(endPoint + "/%s", idToGet);
        //when
        Book bookToAdd = createSampleBook();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);

        //when
        this.mockMvc.perform(post(endPoint).contentType(contentType).content(bookToAddJSON));

        //then
        this.mockMvc.perform(get(endPointToGetBookById))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author.name", is(SAMPLE_AUTHOR_NAME)))
                .andDo(print());
    }

    private Book createSampleBook() {
        Author author = new Author(SAMPLE_AUTHOR_NAME);
        Tittle tittle = new Tittle(SAMPLE_BOOK_TITTLE);
        ISBN isbn = new ISBN(SAMPLE_BOOK_ISBN);
        return new Book.BookBuilder(author, tittle).setIsbn(isbn).build();
    }
}