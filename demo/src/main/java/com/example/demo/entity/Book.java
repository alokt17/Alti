package com.example.demo.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String isbn;
    private String title;
    private String subtitle;
    private String author;
    private String published;
    private String publisher;
    private int pages;
    private String description;
    private String website;
}
