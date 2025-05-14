package com.example.book_service.model; // <-- ajuste ce package selon ton projet

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String isbn;

    private String title;

    private String author;
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
