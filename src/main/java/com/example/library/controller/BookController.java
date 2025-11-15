package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // LIST
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    // INNER JOIN VIEW
    @GetMapping("/with-authors")
    public String listBooksWithAuthors(Model model) {
        model.addAttribute("bookAuthorRows", bookService.findAllBooksWithAuthors());
        return "books-with-authors";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "book-form";
    }

    // CREATE SUBMIT (POST /books/new)
    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") Book book,
                             @RequestParam("authorId") Long authorId,
                             Model model) {
        try {
            Author author = authorService.findById(authorId)
                    .orElseThrow(() -> new EntityNotFoundException("Author not found"));
            book.setAuthor(author);
            bookService.createBook(book);
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("authors", authorService.findAllAuthors());
            model.addAttribute("errorMessage", "Failed to save book. Check code or author.");
            return "book-form";
        }
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findAllBooks().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id"));

        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "book-form";
    }

    // UPDATE SUBMIT (POST /books/edit/{id})
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute("book") Book formBook,
                             @RequestParam("authorId") Long authorId,
                             Model model) {
        try {
            Author author = authorService.findById(authorId)
                    .orElseThrow(() -> new EntityNotFoundException("Author not found"));
            formBook.setAuthor(author);
            bookService.updateBook(id, formBook);
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("authors", authorService.findAllAuthors());
            model.addAttribute("errorMessage", "Failed to update book. Check code or author.");
            return "book-form";
        }
    }

    // DELETE (POST /books/delete/{id})
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            bookService.deleteBook(id);
            return "redirect:/books";
        } catch (Exception e) {
            model.addAttribute("books", bookService.findAllBooks());
            model.addAttribute("errorMessage", "Failed to delete book.");
            return "books";
        }
    }
}
