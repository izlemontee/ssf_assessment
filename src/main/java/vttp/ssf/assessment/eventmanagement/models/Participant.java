package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;

public class Participant {
    private String fullName;

    private Date dob;

    private String email;

    private String phoneNo;

    private String gender;

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
