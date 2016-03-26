package com.aeg.ims.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Message extends LayerSupertype {

    @NotEmpty(message = "Message is required.")
    private String text;

    @NotEmpty(message = "Summary is required.")
    private String summary;

    @OneToOne
    private User to;

}
