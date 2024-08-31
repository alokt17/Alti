package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.util.BookResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private static final String BOOKS_API_URL = "https://gist.githubusercontent.com/nanotaboada/6396437/raw/855dd84436be2c86e192abae2ac605743fc3a127/books.json";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BookService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        try {
            // Fetch response as a String
            ResponseEntity<String> response = restTemplate.getForEntity(BOOKS_API_URL, String.class);

            // Check if the response is successful
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Convert the response body to BookResponse
                BookResponse bookResponse = objectMapper.readValue(response.getBody(), BookResponse.class);
                List<Book> books = bookResponse.getBooks();

                // Find the book by ISBN
                return books.stream()
                        .filter(book -> book.getIsbn().equals(isbn))
                        .findFirst();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception appropriately in production code
        }

        return Optional.empty();
    }
}
