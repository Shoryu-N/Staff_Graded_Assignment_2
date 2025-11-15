package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.service.AuthorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // LIST
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    // CREATE SUBMIT (POST /authors/new)
    @PostMapping("/new")
    public String createAuthor(@ModelAttribute("author") Author author, Model model) {
        try {
            authorService.saveAuthor(author);
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Email must be unique.");
            return "author-form";
        }
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author id"));
        model.addAttribute("author", author);
        return "author-form";
    }

    // UPDATE SUBMIT (POST /authors/edit/{id})
    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @ModelAttribute("author") Author author,
                               Model model) {
        author.setId(id);
        try {
            authorService.saveAuthor(author);
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Email must be unique.");
            return "author-form";
        }
    }

    // DELETE (POST /authors/delete/{id})
    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id, Model model) {
        try {
            authorService.deleteAuthor(id);
            return "redirect:/authors";
        } catch (Exception e) {
            // シンプルに一覧にエラーメッセージを出す例
            model.addAttribute("authors", authorService.findAllAuthors());
            model.addAttribute("errorMessage", "Failed to delete author.");
            return "authors";
        }
    }
}
