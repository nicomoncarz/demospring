package com.nicolasmoncarz.requestcache.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nicolasmoncarz.requestcache.HttpUrlConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestServiceTests {
    
    @Autowired
	@InjectMocks
	private RequestService requestService;

	@Mock
    private RequestRepository requestRepository;

    @BeforeEach
    public void setUp(){
		MockitoAnnotations.openMocks(this);
        Request request = new Request("url", "response", LocalDate.now());
        List<Request> requests = new ArrayList<Request>();
        requests.add(request);
        Mockito.when(requestRepository.findAll()).thenReturn(requests);
    }

	@Test
	public void findOrCreateGetExistingRequest() {
		assertEquals("response", requestService.findOrCreate("url").getResponse());
	}

    @Test
	public void findOrCreateCreateNewRequest() {
		MockedStatic<HttpUrlConnection> httpUtils = Mockito.mockStatic(HttpUrlConnection.class);
        httpUtils.when(() -> HttpUrlConnection.sendGET("newUrl")).thenReturn("newResponse");
		assertEquals("newResponse", requestService.findOrCreate("newUrl").getResponse());
	}
}
