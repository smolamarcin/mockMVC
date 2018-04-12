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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class LibraryControllerTest {
    private static final String endPoint = "/library/books";
    private static final String SAMPLE_AUTHOR_NAME = "Adam Mickiewicz";
    private static final String SAMPLE_BOOK_TITTLE = "Pan Tadeusz";
    private static final String SAMPLE_BOOK_ISBN = "978-1-56619-909-4";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Before
    public void setUp() {
        fillDbWithDummyData();
    }


    @After
    public void tearDown() {
        bookRepository.deleteAll();
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
    }

    @Test
    public void should_AddBookToRepository_whenPostNewBook() throws Exception {
        //given
        Book bookToAdd = createSampleBook();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);

        //when - then
        this.mockMvc.perform(post(endPoint).contentType(contentType).content(bookToAddJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author.name", is(SAMPLE_AUTHOR_NAME)));


    }

    @Test
    public void should_ReturnBook_whenGetByAuthor() throws Exception {
        //given
        String authorToFind = SAMPLE_AUTHOR_NAME;
        Book bookToAdd = createSampleBook();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);

        //when
        this.mockMvc.perform(post(endPoint).contentType(contentType).content(bookToAddJSON));

        //then
        this.mockMvc.perform(get(authorToFind)
                .param("authorr", authorToFind))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author.name", is(SAMPLE_AUTHOR_NAME)));
    }

    private Book createSampleBook() {
        Author author = new Author(SAMPLE_AUTHOR_NAME);
        Tittle tittle = new Tittle(SAMPLE_BOOK_TITTLE);
        ISBN isbn = new ISBN(SAMPLE_BOOK_ISBN);
        return new Book.BookBuilder(author, tittle).setIsbn(isbn).build();
    }

    private void fillDbWithDummyData() {
        Author sienkiewicz = new Author("Henryk Sienkiewicz");
        Tittle tittleQuoVadis = new Tittle("Quo Vadis");
        ISBN isbnQuoVadis = new ISBN("978-1-56619-909-5");
        Book quoVadis = new Book.BookBuilder(sienkiewicz, tittleQuoVadis).setIsbn(isbnQuoVadis).build();
        bookRepository.save(quoVadis);

        Author reymont = new Author("Wladyslaw Reymont");
        Tittle tittleChlopi = new Tittle("Chlopi");
        ISBN isbnChlopi = new ISBN("978-1-56619-909-5");
        Book chlopi = new Book.BookBuilder(reymont, tittleChlopi).setIsbn(isbnChlopi).build();
        bookRepository.save(chlopi);
    }


}