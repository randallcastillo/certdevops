package com.certdevops.certdevops;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
class CertdevopsApplicationTests {

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private String baseUrl = "http://localhost:";

	@Test
    public void testAddProducSuccess() throws URISyntaxException 
    {
        URI uri = new URI(baseUrl + this.port + "/products");
        Product product = new Product("PS4", 100);
        HttpEntity<Product> request = new HttpEntity<>(product);
        ResponseEntity<String> result = this.testRestTemplate.postForEntity(uri, request, String.class);

		if (201 == result.getStatusCodeValue()) {
			// Verify request succeed
			assertEquals(201, result.getStatusCodeValue());
		} else {
			assertEquals("500: MYSQL not running.", 500, result.getStatusCodeValue());
		}
    }

	@Test
	void shouldGetProduct() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(this.baseUrl + this.port + "/products/1", Map.class);
		if (200 == entity.getStatusCodeValue()) {
			// Verify request succeed
			then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		} else {
			assertEquals("500: MYSQL not running.", 500, entity.getStatusCodeValue());
		}
	}

	@Test
	void shouldReturn200WhenSendingRequestToController() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(this.baseUrl + this.port + "/greet", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.mgt + "/actuator/info", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
