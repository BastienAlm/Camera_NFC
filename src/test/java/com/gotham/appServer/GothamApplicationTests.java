package com.gotham.appServer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.gotham.cashmanagerApp.GothamApplication;

@SpringBootTest(classes = GothamApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GothamApplicationTests {
        
      //  @LocalServerPort           // shorthand for @Value("${local.server.port}")
        //private Integer port;
	@Test
	void contextLoads() {
	}

}
