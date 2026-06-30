package com.udacity.bootstrap.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.security.user.name=testuser",
                "spring.security.user.password=testpass"
        }
)
class DogControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getDogs_shouldReturnOk_withValidCredentials() {
        TestRestTemplate authClient = testRestTemplate.withBasicAuth("testuser", "testpass");

        ResponseEntity<String> response =
                authClient.getForEntity("http://localhost:" + port + "/dogs", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getBreeds_shouldReturnOk_withValidCredentials() {
        TestRestTemplate authClient = testRestTemplate.withBasicAuth("testuser", "testpass");

        ResponseEntity<String> response =
                authClient.getForEntity("http://localhost:" + port + "/dogs/breeds", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
