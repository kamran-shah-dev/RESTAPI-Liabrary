package com.app.library.controller;

import com.app.library.model.Member;
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
 * Serves the Thymeleaf page for adding a member.
 */
@Controller
@RequestMapping("/member")
public class MemberViewController {

    @Autowired
    private LibraryService libraryService;

    /**
     * Shows the blank add-member form.
     *
     * @param model holds the form-backing {@link Member}; untouched on a flash-redirect
     *     from {@link #addBook}, which already populated the "message" attribute
     * @return the add-member form view
     */
    @GetMapping("/new")
    public String showAddForm(Model model) {
        if (!model.containsAttribute("member")) {
            model.addAttribute("member", new Member());
        }
        return "member/form";
    }

    /**
     * Assigns the next available ID, stores the submitted member, and redirects back
     * to the form with a success message.
     *
     * @param member the form-bound member, without an ID
     * @param redirectAttributes carries the success message across the redirect
     * @return a redirect to the add-member form
     */
    @PostMapping("/new")
    public String addBook(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        member.setId(nextId());
        libraryService.addMember(member);
        redirectAttributes.addFlashAttribute("message", "\"" + member.getName() + "\" was added successfully.");
        return "redirect:/member/new";
    }

    /**
     * Computes the next free member ID from the highest one currently stored.
     *
     * @return the highest existing member ID plus one, or 1 if there are no members yet
     */
    private Long nextId() {
        return libraryService.getAllMembers().stream()
                .map(Member::getId)
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .map(id -> id + 1)
                .orElse(1L);
    }
}
