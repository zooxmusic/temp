package com.aeg.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "APPLICATION")
public class Application extends IMSIdLayerSupertype {
    private String number;


}
