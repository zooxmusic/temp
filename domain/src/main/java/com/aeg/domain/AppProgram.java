package com.aeg.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "APP_PROGRAM")
@EqualsAndHashCode(callSuper = false)
public class AppProgram extends LayerSupertype{

    private Application application;
    private Program program;

}
