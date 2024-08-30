package com.example.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.*;


@Service
public class BookService {
    private  static  final String  BOOK_JSON_URL ="https://gist.githubusercontent.com/nanotaboada/6396437/raw/855dd84436be2c86e192abae2ac605743fc3a127/books.json";
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public Map<String,Object> serchBookByIsbn(String isbn)
    {
        Map <String, Object> response = restTemplate.getForObject(BOOK_JSON_URL, Map.class);
        List<Map<String,Object>>  books  = (List<Map<String,Object>>)response.get("books");

        return books.stream()
                .filter( b->b.get("isbn").equals(isbn))
                .findFirst()
                .orElse(null);
    }

}
