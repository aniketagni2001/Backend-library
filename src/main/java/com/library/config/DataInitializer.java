package com.library.config;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Only add sample data if the database is empty
        if (bookRepository.count() == 0) {
            addSampleBooks();
            System.out.println("Sample books have been added to the database.");
        }
    }
    
    // Add sample Indian books to the database
    private void addSampleBooks() {
        // Create sample books
        Book book1 = new Book("Ramayana", "Valmiki", "978-0140447444", true);
        Book book2 = new Book("Mahabharata", "Vyasa", "978-0140446812", true);
        Book book3 = new Book("Bhagavad Gita", "Krishna Dvaipayana Vyasa", "978-0140449233", false);
        Book book4 = new Book("Panchatantra", "Vishnu Sharma", "978-0140455227", true);
        Book book5 = new Book("Arthashastra", "Kautilya", "978-0140446034", true);
        
        // Save books to database
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
    }
} 