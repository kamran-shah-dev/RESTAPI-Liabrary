package com.app.library.model;

import java.time.LocalDate;

/** Records a member borrowing a book and its due and return dates. */
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

    /** Creates a record with the associated book, member, and initial dates. */
    public BorrowingRecord(Book book, Member member, LocalDate borrowDate, LocalDate dueDate) {
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    /** Returns the borrowing-record identifier. */
    public Long getId() {
        return id;
    }

    /** Sets the borrowing-record identifier. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Returns the borrowed book. */
    public Book getBook() {
        return book;
    }

    /** Sets the borrowed book. */
    public void setBook(Book book) {
        this.book = book;
    }

    /** Returns the borrowing member. */
    public Member getMember() {
        return member;
    }

    /** Sets the borrowing member. */
    public void setMember(Member member) {
        this.member = member;
    }

    /** Returns the date the book was borrowed. */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    /** Sets the date the book was borrowed. */
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    /** Returns the date the book was returned, or {@code null} while it is on loan. */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /** Sets the book's return date. */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /** Returns the date the book is due back. */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /** Sets the date the book is due back. */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    /** Returns a readable representation of this borrowing record. */
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

