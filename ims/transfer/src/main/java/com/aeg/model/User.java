package com.aeg.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "USER")
@EqualsAndHashCode(callSuper=false)
public class User extends LayerSupertype {

    private String first;
    private String last;
    private String email;
    private String password;

    public String getName() {
        return String.format("%s %s", getFirst(), getLast());
    }

}
