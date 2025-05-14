package com.example.book_service.service;

import com.example.book_service.Repository.BookRepository;
import com.example.book_service.model.Book;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


import java.util.Arrays;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Cacheable(value = "books", key = "#isbn")
    public Book getBookByIsbn(String isbn) {
        System.out.println("Fetching from DB for ISBN: " + isbn); // pour tester si le cache marche
        return (Book) bookRepository.findByIsbn(isbn)
                .orElseThrow();
    }

    @Override
    @CacheEvict(value = "books", key = "#isbn")
    public Book updateBook(String isbn, Book updatedBook) {
        Book book =  bookRepository.getBookByIsbn(isbn);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(book);
    }

    @Override
    @CacheEvict(value = "books", key = "#isbn")
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
}
