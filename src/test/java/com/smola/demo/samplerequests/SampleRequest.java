package com.smola.demo.samplerequests;

import com.google.gson.Gson;
import com.smola.demo.model.library.Author;
import com.smola.demo.model.library.Book;
import com.smola.demo.model.library.ISBN;
import com.smola.demo.model.library.Tittle;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Sample requests for localhost.
 * Application should be turned on.
 */
@Ignore
public class SampleRequest {

    private static final int PORT = 8080;

    @Test
    public void createBook() throws IOException {
        //given
        Author author = new Author("Adam Mickiewicz");
        Tittle tittle = new Tittle("Pan Tadeusz");
        ISBN isbn = new ISBN("978-1-56619-909-4 ");

        Book bookToAdd = new Book.BookBuilder(author, tittle).setIsbn(isbn).build();
        Gson gson = new Gson();
        String bookToAddJSON = gson.toJson(bookToAdd);

        String postUrl = String.format("http://localhost:%s/library/books", PORT);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(bookToAddJSON);//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);

        StatusLine responseStatusLine = response.getStatusLine();

        assertEquals(responseStatusLine.getStatusCode(), 201);

    }
}
