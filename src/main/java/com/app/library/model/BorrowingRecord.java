package com.app.library.model;

import java.time.LocalDate;

/**
 * Records a member borrowing a book and its due and return dates.
 *
 * <p>Instances are plain data holders used both as the in-memory storage
 * value in {@link com.app.library.services.LibraryService} and as the
 * JSON request/response body for the borrowing endpoints in
 * {@link com.app.library.controller.LibraryController}. The
 * {@link com.app.library.services.LibraryService#borrowBook(BorrowingRecord)}
 * method fills in the resolved {@link Book}, {@link Member}, borrow date, and
 * due date; the caller only needs to supply the requested book and member.
 */
public class BorrowingRecord {

    /** Client-supplied identifier used as the borrowing-record map key. */
    private Long id;
    /** Book associated with this loan. */
    private Book book;
    /** Member who borrowed the book. */
    private Member member;

    /** Date the loan began. */
    private LocalDate borrowDate;
    /** Date the book was returned; null while the loan is active. */
    private LocalDate returnDate;
    /** Date the book is due back. */
    private LocalDate dueDate;

    /** Creates an empty record for JSON deserialization. */
    public BorrowingRecord() {}

    /**
     * Creates a record with the associated book, member, and initial dates.
     *
     * @param book the book being borrowed
     * @param member the member borrowing the book
     * @param borrowDate the date the loan began
     * @param dueDate the date the book is due back
     */
    public BorrowingRecord(Book book, Member member, LocalDate borrowDate, LocalDate dueDate) {
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    /**
     * Returns the borrowing-record identifier.
     *
     * @return the record's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the borrowing-record identifier.
     *
     * @param id the record's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the borrowed book.
     *
     * @return the book associated with this loan
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the borrowed book.
     *
     * @param book the book associated with this loan
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Returns the borrowing member.
     *
     * @return the member who borrowed the book
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the borrowing member.
     *
     * @param member the member who borrowed the book
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Returns the date the book was borrowed.
     *
     * @return the date the loan began
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    /**
     * Sets the date the book was borrowed.
     *
     * @param borrowDate the date the loan began
     */
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    /**
     * Returns the date the book was returned, or {@code null} while it is on loan.
     *
     * @return the return date, or {@code null} if the loan is still active
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the book's return date.
     *
     * @param returnDate the date the book was returned
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Returns the date the book is due back.
     *
     * @return the due date for this loan
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the date the book is due back.
     *
     * @param dueDate the due date for this loan
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a readable representation of this borrowing record.
     *
     * @return a string containing all field values, for logging and debugging
     */
    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
