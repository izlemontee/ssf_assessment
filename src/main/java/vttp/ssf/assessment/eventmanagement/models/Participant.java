package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Participant {

    @NotEmpty(message = "name required")
    @Size(min = 5, max = 25, message = "Must be between 5 and 25 characters")
    private String fullName;


    @NotNull(message = "DOB required")
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    @Past(message = "birthday must be in the past")
    private Date dob;

    @NotEmpty(message = "Email required")
    @Email(message = "Must be valid email format")
    @Size(max = 50, message = "Max 50 characters for email")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Must start with 8 or 9 followed by 7 digits")
    private String phoneNo;

    private String gender;

    @Min(value = 1, message = "Minimum 1 ticket")
    @Max(value = 3, message = "Maximum 3 tickets")
    private Integer tickets;

    public Participant(){

    }

    public Participant(String fullName, Date dob, String email, String phoneNo, String gender, Integer tickets) {
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.tickets = tickets;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    
}
