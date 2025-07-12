package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
    // Find books by availability
    List<Book> findByAvailable(Boolean available);
    
    // Find book by ISBN
    Book findByIsbn(String isbn);
    
    // Check if book exists by ISBN
    boolean existsByIsbn(String isbn);
} 