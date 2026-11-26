package com.app.library.model;

import java.time.LocalDate;

/**
 * Represents a library member and their membership period.
 *
 * <p>Instances are plain data holders used both as the in-memory storage
 * value in {@link com.app.library.services.LibraryService} and as the
 * JSON request/response body for the member endpoints in
 * {@link com.app.library.controller.LibraryController}.
 */
public class Member {

    /** Client-supplied identifier used as the member map key. */
    private Long id;
    /** Member's full name. */
    private String name;
    /** Member's email address. */
    private String email;
    /** Member's contact phone number. */
    private String phoneNumber;
    /** First date of the membership. */
    private LocalDate startDate;
    /** Last date of the membership, if one is set. */
    private LocalDate endDate;

    /** Creates an empty member for JSON deserialization. */
    public Member() {}

    /**
     * Creates a member with contact details and membership dates.
     *
     * @param name the member's full name
     * @param email the member's email address
     * @param phoneNumber the member's contact phone number
     * @param startDate the first date of the membership
     * @param endDate the last date of the membership, or {@code null} if still active
     */
    public Member(String name, String email, String phoneNumber, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the member identifier.
     *
     * @return the member's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the member identifier.
     *
     * @param id the member's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the member's name.
     *
     * @return the member's full name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the member's name.
     *
     * @param name the member's full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the member's email address.
     *
     * @return the member's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the member's email address.
     *
     * @param email the member's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the member's phone number.
     *
     * @return the member's contact phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the member's phone number.
     *
     * @param phoneNumber the member's contact phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the membership start date.
     *
     * @return the first date of the membership
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the membership start date.
     *
     * @param startDate the first date of the membership
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the membership end date.
     *
     * @return the last date of the membership, or {@code null} if still active
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the membership end date.
     *
     * @param endDate the last date of the membership, or {@code null} if still active
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns a readable representation of this member.
     *
     * @return a string containing all field values, for logging and debugging
     */
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", membershipStartDate=" + startDate +
                ", membershipEndDate=" + endDate +
                '}';
    }
}
