package com.aeg.ims;

import com.aeg.ims.transfer.config.TestConfig;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@SpringApplicationConfiguration(classes = TestConfig.class)
@Transactional
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {



    @BeforeTransaction
    public void setupData() throws Exception {

    }
}
