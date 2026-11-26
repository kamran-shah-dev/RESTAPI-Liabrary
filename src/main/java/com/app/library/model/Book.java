package com.app.library.model;


/**
 * Represents a book and the number of copies currently available to borrow.
 *
 * <p>Instances are plain data holders used both as the in-memory storage
 * value in {@link com.app.library.services.LibraryService} and as the
 * JSON request/response body for the book endpoints in
 * {@link com.app.library.controller.LibraryController}.
 */
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

    /**
     * Creates a book with its bibliographic details and available-copy count.
     *
     * @param title the book title
     * @param author the book author
     * @param publicationYear the year the book was published
     * @param genre the genre or category of the book
     * @param availableCopies the number of copies available to borrow
     */
    public Book(String title, String author, int publicationYear, String genre, int availableCopies) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.availableCopies = availableCopies;
    }

    /**
     * Returns this book's identifier.
     *
     * @return the book's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets this book's identifier.
     *
     * @param id the book's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the title.
     *
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the author.
     *
     * @return the book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the book author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the publication year.
     *
     * @return the year the book was published
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year.
     *
     * @param publicationYear the year the book was published
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Returns the book's genre.
     *
     * @return the genre or category of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the book's genre.
     *
     * @param genre the genre or category of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Returns how many copies are currently available.
     *
     * @return the number of copies available to borrow
     */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Sets how many copies are currently available.
     *
     * @param availableCopies the number of copies available to borrow
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    /**
     * Returns a readable representation of this book.
     *
     * @return a string containing all field values, for logging and debugging
     */
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
