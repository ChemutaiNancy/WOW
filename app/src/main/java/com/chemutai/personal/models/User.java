package com.chemutai.personal.models;

public class User {

    private String fname, lname, phoneno, email, password;


    public User(String fname, String lname, String phoneno, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.phoneno = phoneno;
        this.email = email;
        this.password = password;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
