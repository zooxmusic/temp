package com.aeg.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Message extends LayerSupertype {

    private String text;

    private String summary;

    @OneToMany
    private Collection<User> recipients;

    @OneToOne
    private User author;
}
