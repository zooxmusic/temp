package com.aeg.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PROGRAM_APP_TYPE")
@EqualsAndHashCode(callSuper = false)
public class ProgramAppType extends LayerSupertype {
    private Program program;

    @Column(name = "PRG_APP_TYPE_NAME")
    private String name;


}
