package com.aeg.ims.transfer.repository;

import com.aeg.ims.AbstractTest;
import com.aeg.ims.transfer.model.AppConfig;
import com.aeg.ims.transfer.model.Client;
import com.aeg.ims.transfer.model.Path;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;


public class AppConfigRepositoryTest extends AbstractTest {

    @Autowired
    private AppConfigRepository appConfigRepository;

    @Test
    public void testApplicationConfig() {
        Iterable<Client> clients = new HashSet<Client>();
        Client c = new Client();
        c.setName("CleaResult");
        c.setTransferDirectory(getDirectory());

        AppConfig appConfig = new AppConfig(clients);


        appConfigRepository.save(appConfig);
    }

    private Path getDirectory() {
        Path root = Path.createRoot("IMS Files");
        Path inbound = new Path(root, "inbound");
        Path nuu = new Path(inbound, "New");

        return root;
    }
}
