package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT b FROM Book b JOIN b.author a WHERE a.lastName = :lastName
        """)
    List<Book> findBooksByAuthorLastName(String lastName);

    @Query("""
        SELECT b, a FROM Book b JOIN b.author a
        """)
    List<Object[]> findAllBooksWithAuthors();
}
