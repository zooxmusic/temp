package com.aeg.ims.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "USER")
public class User extends LayerSupertype {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
