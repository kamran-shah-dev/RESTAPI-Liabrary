package com.app.library.controller;

import com.app.library.model.Book;
import com.app.library.services.LibraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.Objects;

/**
 * Serves the Thymeleaf page for adding a book.
 */
@Controller
@RequestMapping("/books")
public class BookViewController {

    @Autowired
    private LibraryService libraryService;

    /**
     * Shows the blank add-book form.
     *
     * @param model holds the form-backing {@link Book}; untouched on a flash-redirect
     *     from {@link #addBook}, which already populated the "message" attribute
     * @return the add-book form view
     */
    @GetMapping("/new")
    public String showAddForm(Model model) {
        if (!model.containsAttribute("book")) {
            model.addAttribute("book", new Book());
        }
        return "books/form";
    }

    /**
     * Assigns the next available ID, stores the submitted book, and redirects back
     * to the form with a success message.
     *
     * @param book the form-bound book, without an ID
     * @param redirectAttributes carries the success message across the redirect
     * @return a redirect to the add-book form
     */
    @PostMapping("/new")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        book.setId(nextId());
        libraryService.addBook(book);
        redirectAttributes.addFlashAttribute("message", "\"" + book.getTitle() + "\" was added successfully.");
        return "redirect:/books/new";
    }

    /**
     * Computes the next free book ID from the highest one currently stored.
     *
     * @return the highest existing book ID plus one, or 1 if there are no books yet
     */
    private Long nextId() {
        return libraryService.getAllBooks().stream()
                .map(Book::getId)
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .map(id -> id + 1)
                .orElse(1L);
    }
}
