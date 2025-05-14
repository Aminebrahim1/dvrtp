package com.example.book_service.Repository;

import com.example.book_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Object> findByIsbn(String isbn);
}
