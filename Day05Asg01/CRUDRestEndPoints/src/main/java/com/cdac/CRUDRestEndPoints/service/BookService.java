package com.cdac.CRUDRestEndPoints.service;


import java.util.*;

import org.springframework.stereotype.Service;

import com.cdac.CRUDRestEndPoints.model.Book;

@Service
public class BookService {

    private Map<Integer, Book> bookStore = new HashMap<>();

    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }

    public Book findById(int id) {
        return bookStore.get(id);
    }

    public Book save(Book book) {
        bookStore.put(book.getId(), book);
        return book;
    }

    public Book update(int id, Book book) {
        if (!bookStore.containsKey(id)) return null;
        book.setId(id);
        bookStore.put(id, book);
        return book;
    }

    public boolean delete(int id) {
        return bookStore.remove(id) != null;
    }
}
