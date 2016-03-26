package com.aeg.ims.transfer.model;

import com.aeg.domain.LayerSupertype;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLIENTS")
@EqualsAndHashCode(callSuper=false)
public class Client extends LayerSupertype {

    private String name;
    private String ftpHost;
    private String ftpUsername;
    private String ftpPassword;

    @OneToOne(fetch = FetchType.LAZY)
    private Path transferDirectory;
}
