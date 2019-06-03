package beans;

import annotations.Entity;
import annotations.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Owner {
    @PrimaryKey
    private long ownerID;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;

    public Owner(long ownerID, String firstname, String lastname, LocalDate dateOfBirth) {
        this.ownerID = ownerID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }
}
