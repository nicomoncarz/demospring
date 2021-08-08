package com.nicolasmoncarz.requestcache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import com.nicolasmoncarz.requestcache.request.Request;
import com.nicolasmoncarz.requestcache.request.RequestController;
import com.nicolasmoncarz.requestcache.request.RequestService;

import org.hibernate.JDBCException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RequestCacheApplicationTests {

	@Autowired
	@InjectMocks
	private RequestController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Mock
    private RequestService requestService;

    @BeforeEach
    public void setUp(){
		MockitoAnnotations.openMocks(this);
    }

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

	@Test
	public void getRequestSuccessful() throws IOException {
		Request request = new Request("url", "response");
        Mockito.when(requestService.findOrCreate("url")).thenReturn(request);
		assertEquals(
			this.restTemplate.getForObject("http://localhost:" + port + "/request?url=url",
			String.class
		), "response");
	}

	@Test
	public void getRequestThrowingDBError() throws IOException {
		Mockito.when(requestService.findOrCreate("url")).thenThrow(JDBCException.class);
		assertEquals(
			this.restTemplate.getForObject("http://localhost:" + port + "/request?url=url",
			String.class
		), "DB Error");
	}
}
