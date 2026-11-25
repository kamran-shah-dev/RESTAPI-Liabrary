package com.app.library.model;


/** Represents a book and the number of copies currently available to borrow. */
public class Book {

    /** Client-supplied identifier used as the book map key. */
    private Long id;

    /** Book title. */
    private String title;
    /** Book author. */
    private String author;
    /** Year the book was published. */
    private int publicationYear;
    /** Genre or category of the book. */
    private String genre;
    /** Number of copies available to borrow. */
    private int availableCopies;

    /** Creates an empty book for JSON deserialization. */
    public Book() {}

    /** Creates a book with its bibliographic details and available-copy count. */
    public Book(String title, String author, int publicationYear, String genre, int availableCopies) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.availableCopies = availableCopies;
    }

    /** Returns this book's identifier. */
    public Long getId() {
        return id;
    }

    /** Sets this book's identifier. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Returns the title. */
    public String getTitle() {
        return title;
    }

    /** Sets the title. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Returns the author. */
    public String getAuthor() {
        return author;
    }

    /** Sets the author. */
    public void setAuthor(String author) {
        this.author = author;
    }

    /** Returns the publication year. */
    public int getPublicationYear() {
        return publicationYear;
    }

    /** Sets the publication year. */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /** Returns the book's genre. */
    public String getGenre() {
        return genre;
    }

    /** Sets the book's genre. */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /** Returns how many copies are currently available. */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /** Sets how many copies are currently available. */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    

    /** Returns a readable representation of this book. */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", genre='" + genre + '\'' +
                ", availableCopies=" + availableCopies +
                '}';
    }
}

