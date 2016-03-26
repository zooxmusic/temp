package com.aeg.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PROGRAMS")
@EqualsAndHashCode(callSuper = false)
public class Program extends IMSIdLayerSupertype{
    private String number;
    private String mm;

}
