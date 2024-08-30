package com.example.demo.controller;

import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class BookSearchController {
    @Autowired
    private   BookService bookService;


    @GetMapping ("/search")
    public String search ( @RequestParam String isbn)

    {
        System.out.println(isbn);
    //    Map<String,Object>  book =bookService.serchBookByIsbn(isbn);

             return  isbn;//   return book!=null  ? (String) book.get("title") : "Book not found";


    }
}
