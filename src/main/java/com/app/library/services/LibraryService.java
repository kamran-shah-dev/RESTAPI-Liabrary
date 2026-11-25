package com.app.library.services;

import com.app.library.model.Book;
import com.app.library.model.BorrowingRecord;
import com.app.library.model.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Provides in-memory operations for books, members, and borrowing records. */
@Service
public class LibraryService {

    /** Stores books by ID and preserves insertion order for API responses. */
    private final Map<Long, Book> books = new LinkedHashMap<>();
    /** Stores members by ID and preserves insertion order for API responses. */
    private final Map<Long, Member> members = new LinkedHashMap<>();
    /** Stores borrowing records by ID and preserves insertion order. */
    private final Map<Long, BorrowingRecord> borrowingRecords = new LinkedHashMap<>();

    /** Returns all books as a snapshot list. */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    /** Finds a book by its ID. */
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    /** Finds the first title match, ignoring whitespace and letter case. */
    public Optional<Book> getBookByTitle(String title) {
        String normalizedTitle = normalize(title);
        return books.values().stream()
                .filter(book -> normalize(book.getTitle()).equals(normalizedTitle))
                .findFirst();
    }

    /** Finds the first author match, ignoring whitespace and letter case. */
    public Optional<Book> getBookByAuthor(String author) {
        String normalizedAuthor = normalize(author);
        return books.values().stream()
                .filter(book -> normalize(book.getAuthor()).equals(normalizedAuthor))
                .findFirst();
    }

    /** Finds the books filtered by genre, ignoring whitespace and letter case. */
    public List<Book> getBookByGenre(String genre) {
        String normalizedGenre = normalize(genre);
        return books.values().stream()
                .filter(book -> book.getGenre().equals(normalizedGenre))
                .toList();
    }

    /** Normalizes a search value for title and author comparisons. */
    private String normalize(String value) {
        return value == null ? "" : value.replaceAll("\\s+", "").toLowerCase();
    }

    /** Returns books whose active borrowing records are due on the given date. */
    public List<Book> getDueBooks(LocalDate date) {
        return borrowingRecords.values().stream()
                .filter(record -> date != null && date.equals(record.getDueDate())
                        && record.getReturnDate() == null)
                .map(BorrowingRecord::getBook)
                .toList();
    }

    /** Returns today if a copy is available, otherwise the earliest active loan due date.
     * Returns {@code null} if the book or an active loan due date is unavailable.
     */
    public LocalDate getEarliestAvailableBook(Long id) {
        Book book = books.get(id);
        if (book == null) {
            return null;
        }
        if (book.getAvailableCopies() > 0) {
            return LocalDate.now();
        }
        return borrowingRecords.values().stream()
                .filter(record -> record.getBook() != null
                    && id.equals(record.getBook().getId())
                    && record.getReturnDate() == null)
                .map(BorrowingRecord::getDueDate)
                .filter(date -> date != null)
                .min(LocalDate::compareTo)
                .orElse(null);
    }

    /** Adds or replaces a book using its ID as the map key. */
    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    /** Replaces a book if the ID already exists. */
    public void updateBook(Book updatedBook) {
        if (books.containsKey(updatedBook.getId())) {
            books.put(updatedBook.getId(), updatedBook);
        }
    }

    /** Removes the book with the given ID, if present. */
    public void deleteBook(Long id) {
        books.remove(id);
    }

    /** Returns all members as a snapshot list. */
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    /** Finds a member by its ID. */
    public Optional<Member> getMemberById(Long id) {
        return Optional.ofNullable(members.get(id));
    }

    /** Adds or replaces a member using its ID as the map key. */
    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    /** Replaces a member if the ID already exists. */
    public void updateMember(Member updatedMember) {
        if (members.containsKey(updatedMember.getId())) {
            members.put(updatedMember.getId(), updatedMember);
        }
    }

    /** Removes the member with the given ID, if present. */
    public void deleteMember(Long id) {
        members.remove(id);
    }

    /** Returns all borrowing records as a snapshot list. */
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return new ArrayList<>(borrowingRecords.values());
    }

    /** Validates and records a two-week loan, then decrements the book's available copies. */
    public void borrowBook(BorrowingRecord record) {
        Book requestedBook = record.getBook();
        Member requestedMember = record.getMember();
        Book book = requestedBook == null ? null : books.get(requestedBook.getId());
        Member member = requestedMember == null ? null : members.get(requestedMember.getId());

        if (borrowingRecords.containsKey(record.getId())) {
            throw new IllegalArgumentException("A borrowing record with this ID already exists");
        }
        if (book == null || member == null) {
            throw new IllegalArgumentException("The book and member must already exist in the library");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No copies of this book are currently available");
        }

        record.setBook(book);
        record.setMember(member);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        borrowingRecords.put(record.getId(), record);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
    }

    /** Marks an active loan returned and restores a copy; ignores unknown or already returned IDs. */
    public void returnBook(Long recordId, LocalDate returnDate) {
        BorrowingRecord record = borrowingRecords.get(recordId);
        if (record != null && record.getReturnDate() == null) {
            record.setReturnDate(returnDate);
            record.getBook().setAvailableCopies(record.getBook().getAvailableCopies() + 1);
        }
    }
}

