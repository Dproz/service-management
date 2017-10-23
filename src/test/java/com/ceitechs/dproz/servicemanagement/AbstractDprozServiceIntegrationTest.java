package com.ceitechs.dproz.servicemanagement;

import com.ceitechs.dproz.servicemanagement.config.DatabaseConfiguration;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author iddymagohe on 10/22/17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {
        "spring.data.mongodb.uri=mongodb://rwUser:rwUserPass@localhost:27017/dproz-dev",
        "servicemanagement.db.host=localhost:27017",
        "servicemanagement.db.user=rwUser",
        "servicemanagement.db.password=rwUserPass",
        "servicemanagement.db.name=dproz-dev",
        "servicemanagement.magickey=5rGXHCU2yoGTn600Gz9i5A=="
})
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Ignore
public class AbstractDprozServiceIntegrationTest {

}
