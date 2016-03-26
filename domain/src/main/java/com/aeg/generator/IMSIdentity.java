package com.aeg.generator;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class IMSIdentity implements Serializable{

    private String id;
}
