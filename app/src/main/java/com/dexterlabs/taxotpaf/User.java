package com.dexterlabs.taxotpaf;

public class User {
    String id;
    String name;
    String email;
    String date;
    String number;
    String caName;
    public User () {

    }

    public User(String id, String name, String email, String date,String number,String caName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.date = date;
        this.number = number;
        this.caName = caName;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public String getNumber() {
        return number;
    }

    public String getCaName() {
        return caName;
    }
}


