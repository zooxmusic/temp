package com.aeg.domain;


import com.aeg.generator.IMSIdentity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class IMSIdLayerSupertype implements Serializable {

    @Id
    private IMSIdentity id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DT", nullable = false)
    private Date created;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DT", nullable = false)
    private Date updated;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
