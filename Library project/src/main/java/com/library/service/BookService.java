package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    // Add a new book
    public Book addBook(Book book) {
        // Check if book with same ISBN already exists
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        return bookRepository.save(book);
    }
    
    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // Get book by ID
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    // Get book by ISBN
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    // Delete book by ID
    public boolean deleteBook(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Update book availability
    public Book updateBookAvailability(Integer id, Boolean available) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setAvailable(available);
            return bookRepository.save(book);
        }
        return null;
    }
    
    // Update book details
    public Book updateBook(Integer id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            // Check if ISBN is being changed and if the new ISBN already exists
            if (!existingBook.getIsbn().equals(updatedBook.getIsbn()) && 
                bookRepository.existsByIsbn(updatedBook.getIsbn())) {
                throw new RuntimeException("Book with ISBN " + updatedBook.getIsbn() + " already exists");
            }
            
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setAvailable(updatedBook.getAvailable());
            
            return bookRepository.save(existingBook);
        }
        return null;
    }
    
    // Get books by availability
    public List<Book> getBooksByAvailability(Boolean available) {
        return bookRepository.findByAvailable(available);
    }
} 