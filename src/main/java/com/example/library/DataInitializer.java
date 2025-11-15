package com.example.library;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AuthorRepository authorRepository,
                               BookRepository bookRepository) {
        return args -> {
            if (authorRepository.count() > 0 || bookRepository.count() > 0) {
                return;
            }

            Author a1 = new Author("Carl", "Gauss", "gauss@math.example.com");
            Author a2 = new Author("Emmy", "Noether", "noether@math.example.com");
            Author a3 = new Author("David", "Hilbert", "hilbert@math.example.com");
            Author a4 = new Author("Sofia", "Kovalevskaya", "kovalevskaya@math.example.com");
            Author a5 = new Author("Leonhard", "Euler", "euler@math.example.com");
            Author a6 = new Author("Henri", "Poincare", "poincare@math.example.com");
            Author a7 = new Author("Alexandr", "Grothendieck", "grothendieck@math.example.com");
            Author a8 = new Author("Andrew", "Wiles", "wiles@math.example.com");
            Author a9 = new Author("Terence", "Tao", "tao@math.example.com");
            Author a10 = new Author("Maryam", "Mirzakhani", "mirzakhani@math.example.com");

            List<Author> authors = authorRepository.saveAll(
                    Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10)
            );

            Book b1 = new Book("Prime Numbers and Modular Forms", "MATH-001", 2020, new BigDecimal("29.99"), a1);
            Book b2 = new Book("Algebraic Structures in Physics", "MATH-002", 2019, new BigDecimal("34.50"), a2);
            Book b3 = new Book("Hilbert Spaces and Operators", "MATH-003", 2018, new BigDecimal("39.90"), a3);
            Book b4 = new Book("Partial Differential Equations for Dynamics", "MATH-004", 2017, new BigDecimal("42.00"), a4);
            Book b5 = new Book("Analytic Number Theory Notes", "MATH-005", 2016, new BigDecimal("31.75"), a5);
            Book b6 = new Book("Topology of Three-Manifolds", "MATH-006", 2015, new BigDecimal("37.20"), a6);
            Book b7 = new Book("Sheaves and Schemes in Geometry", "MATH-007", 2014, new BigDecimal("45.00"), a7);
            Book b8 = new Book("Elliptic Curves and Fermat", "MATH-008", 2013, new BigDecimal("38.40"), a8);
            Book b9 = new Book("Harmonic Analysis and PDE", "MATH-009", 2012, new BigDecimal("41.10"), a9);
            Book b10 = new Book("Geodesics on Riemann Surfaces", "MATH-010", 2011, new BigDecimal("36.80"), a10);

            bookRepository.saveAll(Arrays.asList(
                    b1, b2, b3, b4, b5, b6, b7, b8, b9, b10
            ));
        };
    }
}
