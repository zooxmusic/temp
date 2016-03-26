package com.aeg.ims.transfer.model;

import com.aeg.domain.LayerSupertype;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "APP_CONFIG")
@EqualsAndHashCode(callSuper=false)
public class AppConfig extends LayerSupertype {

    private AppConfig(){}

    public AppConfig(List<Client> clients) {
        this.clients = clients;
    }

    private String name = "ROOT";
    @OneToMany
    private List<Client> clients;
}
