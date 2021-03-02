package com.certdevops.certdevops;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
class CertdevopsApplicationTests {

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@Autowired
	private HomeController controller;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void contextLoads_HomeController() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void shouldReturn200WhenSendingRequestToController() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/greet",
				Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + this.mgt + "/actuator/info", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
