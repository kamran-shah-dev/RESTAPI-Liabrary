package com.app.library.model;

import java.time.LocalDate;

/** Represents a library member and their membership period. */
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

    /** Creates a member with contact details and membership dates. */
    public Member(String name, String email, String phoneNumber, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /** Returns the member identifier. */
    public Long getId() {
        return id;
    }

    /** Sets the member identifier. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Returns the member's name. */
    public String getName() {
        return name;
    }

    /** Sets the member's name. */
    public void setName(String name) {
        this.name = name;
    }

    /** Returns the member's email address. */
    public String getEmail() {
        return email;
    }

    /** Sets the member's email address. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Returns the member's phone number. */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** Sets the member's phone number. */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** Returns the membership start date. */
    public LocalDate getStartDate() {
        return startDate;
    }

    /** Sets the membership start date. */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /** Returns the membership end date. */
    public LocalDate getEndDate() {
        return endDate;
    }

    /** Sets the membership end date. */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /** Returns a readable representation of this member. */
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

