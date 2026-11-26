package com.app.library.controller;

import com.app.library.model.Book;
import com.app.library.model.Member;
import com.app.library.model.BorrowingRecord;
import com.app.library.services.LibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Exposes HTTP endpoints for books, members, and borrowing records.
 *
 * <p>All endpoints are mounted under {@code /api} and delegate their logic to
 * {@link LibraryService}, which holds the in-memory data backing this API.
 */
@RestController
@RequestMapping("/api")
public class LibraryController {

    /** Logs API actions and returned resource details. */
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    /** Provides the in-memory operations used by the API endpoints. */
    @Autowired
    private LibraryService libraryService;

    /** Configured description returned by {@code GET /api/description}. */
    @Value("${app.description}")
    private String appDescription;

    /**
     * Returns the description configured for this application.
     *
     * @return the value of the {@code app.description} property
     */
    @GetMapping ("/description")
    public String getDescription() {
        return appDescription;
    }

    /**
     * Lists all books in insertion order.
     *
     * @return 200 OK with every stored book
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = libraryService.getAllBooks();
        logger.info("The list of books returned"+books);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Finds a book by ID, returning 404 when it does not exist.
     *
     * @param id the book's ID
     * @return 200 OK with the matching book, or 404 Not Found if no book has that ID
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = libraryService.getBookById(id);
        logger.info("The book returned"+book);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Finds the first book with a matching title, returning 404 when absent.
     *
     * @param title the title to search for
     * @return 200 OK with the first matching book, or 404 Not Found if none match
     */
    @GetMapping("/books/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        Optional<Book> book = libraryService.getBookByTitle(title);
        System.out.println(book);
        logger.info("The book returned"+book);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Finds the first book with a matching author, returning 404 when absent.
     *
     * @param author the author to search for
     * @return 200 OK with the first matching book, or 404 Not Found if none match
     */
    @GetMapping("/books/author/{author}")
    public ResponseEntity<Book> getBookByAuthor(@PathVariable String author) {
        Optional<Book> book = libraryService.getBookByAuthor(author);
        logger.info("The book returned"+book);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Gets all books of a specific genre; returns 404 if no books are found.
     *
     * @param genere the genre to search for
     * @return 200 OK with every matching book, or 404 Not Found if none match
     */
    @GetMapping("/books/genere/{genere}")
    public ResponseEntity<List<Book>> getBookByGenere(@PathVariable String genere) {
        List<Book> books = libraryService.getBookByGenre(genere);
        logger.info("The book returned"+books);
        return books.isEmpty()? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Gets the earliest date the given book is available to borrow.
     *
     * @param id the book's ID
     * @return 200 OK with today's date if a copy is free now, or the earliest active
     *     loan's due date if all copies are out; 404 Not Found if the book does not
     *     exist or has no due date to report
     */
    @GetMapping("/books/earliestavailable/{id}")
    public ResponseEntity<LocalDate> getEarliestAvailableBook(@PathVariable Long id) {
        LocalDate date = libraryService.getEarliestAvailableBook(id);
        logger.info("The book returned"+date);
        return date == null? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(date, HttpStatus.OK);
    }

    /**
     * Gets all books with an active loan due on the given date.
     *
     * @param date the due date to match
     * @return 200 OK with every book due back on {@code date}, or 404 Not Found if none are due
     */
    @GetMapping("/books/duebooks/{date}")
    public ResponseEntity<List<Book>> getBookByGenere(@PathVariable LocalDate date) {
        List<Book> books = libraryService.getDueBooks(date);
        logger.info("The book returned"+books);
        return books.isEmpty()? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Stores a book supplied in the request body and returns 201 Created.
     *
     * @param book the book to add
     * @return 201 Created with the stored book
     */
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        libraryService.addBook(book);
        logger.info("The book was added");
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    /**
     * Stores a batch of books supplied in the request body and returns 201 Created.
     *
     * @param books the books to add
     * @return 201 Created with the stored books
     */
    @PostMapping("/books/bulk")
    public ResponseEntity<List<Book>> addBulkBook(@RequestBody List<Book> books) {
        for(Book book: books) {
            libraryService.addBook(book);
        }
        logger.info("The book was added");
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    /**
     * Replaces an existing book's fields, using the path ID as its identifier.
     *
     * @param id the ID of the book to update
     * @param updatedBook the replacement book data
     * @return 200 OK with the updated book, or 404 Not Found if no book has that ID
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        if (!libraryService.getBookById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedBook.setId(id);
        libraryService.updateBook(updatedBook);
        logger.info("The book has been updated "+updatedBook);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    /**
     * Deletes a book by ID and returns 204 when successful.
     *
     * @param id the ID of the book to delete
     * @return 204 No Content on success, or 404 Not Found if no book has that ID
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!libraryService.getBookById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libraryService.deleteBook(id);
        logger.info("The book has been deleted ");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Lists all registered members in insertion order.
     *
     * @return 200 OK with every stored member
     */
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = libraryService.getAllMembers();
        logger.info("The members in the system " + members);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Finds a member by ID, returning 404 when it does not exist.
     *
     * @param id the member's ID
     * @return 200 OK with the matching member, or 404 Not Found if no member has that ID
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = libraryService.getMemberById(id);
        logger.info("The member you retrieved "+member);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Stores a member supplied in the request body and returns 201 Created.
     *
     * @param member the member to add
     * @return 201 Created with the stored member
     */
    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        libraryService.addMember(member);
        logger.info("The member has been added ");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    /**
     * Replaces an existing member's fields, using the path ID as its identifier.
     *
     * @param id the ID of the member to update
     * @param updatedMember the replacement member data
     * @return 200 OK with the updated member, or 404 Not Found if no member has that ID
     */
    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        if (!libraryService.getMemberById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedMember.setId(id);
        libraryService.updateMember(updatedMember);
        logger.info("The member has been updated "+updatedMember);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    /**
     * Deletes a member by ID and returns 204 when successful.
     *
     * @param id the ID of the member to delete
     * @return 204 No Content on success, or 404 Not Found if no member has that ID
     */
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (!libraryService.getMemberById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libraryService.deleteMember(id);
        logger.info("The member has been deleted "+id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Lists all borrowing records in insertion order.
     *
     * @return 200 OK with every stored borrowing record
     */
    @GetMapping("/borrowing-records")
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowingRecords() {
        List<BorrowingRecord> records = libraryService.getAllBorrowingRecords();
        logger.info("The records has been retrieved "+records);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    /**
     * Creates a borrowing record; the service sets the borrow and due dates.
     *
     * @param record the requested borrowing record, referencing an existing book and member
     * @return 201 Created with the stored borrowing record
     * @throws IllegalArgumentException if the record ID is already in use, or the
     *     referenced book or member does not exist
     * @throws IllegalStateException if the referenced book has no available copies
     */
    @PostMapping("/borrow")
    public ResponseEntity<BorrowingRecord> borrowBook(@RequestBody BorrowingRecord record) {
        libraryService.borrowBook(record);
        logger.info("The book has been borrowed "+record);
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    /**
     * Marks a borrowing record as returned on the current date.
     *
     * @param recordId the ID of the borrowing record to close
     * @return 200 OK once the return has been processed
     */
    @PutMapping("/return/{recordId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long recordId) {
        libraryService.returnBook(recordId, LocalDate.now());
        logger.info("The book has been retrieved "+recordId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


