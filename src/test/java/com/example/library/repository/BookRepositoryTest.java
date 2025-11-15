package com.example.library.repository;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testFindBooksByAuthorLastName() {
        Author author = new Author("Test", "MathLast", "test.math@example.com");
        author = authorRepository.save(author);

        Book book = new Book("Test Math Book", "MATH-TEST", 2024, new BigDecimal("10.00"), author);
        bookRepository.save(book);

        List<Book> result = bookRepository.findBooksByAuthorLastName("MathLast");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Math Book");
    }
}
