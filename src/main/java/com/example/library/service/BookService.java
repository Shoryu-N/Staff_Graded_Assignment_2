package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Object[]> findAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }

    public Book createBook(Book book) throws DataIntegrityViolationException {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updated) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        existing.setTitle(updated.getTitle());
        existing.setIsbn(updated.getIsbn());
        existing.setPublishedYear(updated.getPublishedYear());
        existing.setPrice(updated.getPrice());
        existing.setAuthor(updated.getAuthor());

        return bookRepository.save(existing);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
