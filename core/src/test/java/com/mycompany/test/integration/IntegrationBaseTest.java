package com.mycompany.test.integration;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/app-tests-context.xml")
public class IntegrationBaseTest {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

}
