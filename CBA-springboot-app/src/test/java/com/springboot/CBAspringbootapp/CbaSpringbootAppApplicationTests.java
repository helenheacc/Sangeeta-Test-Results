package com.springboot.CBAspringbootapp;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class CbaSpringbootAppApplicationTests {
	
	 TestRestTemplate restTemplate = new TestRestTemplate();
	 HttpHeaders headers = new HttpHeaders();

	  @Test
	   public void testGetProducts() throws Exception {
	        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

	        ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort("/v1/products"), HttpMethod.GET, entity, String.class);

	        String expected = "{\"products\":[{\"id\":1,\"brand\":\"Apple\",\"model\":\"iPhone X\"},{\"id\":2,\"brand\":\"Samsung\",\"model\":\"Note 9\"},{\"id\":3,\"brand\":\"Google\",\"model\":\"Pixel 3\"},{\"id\":4,\"brand\":\"OnePlus\",\"model\":\"6T\"}]}";

	        JSONAssert.assertEquals(expected, response.getBody(), false);
	    }
	  
	  @Test
	   public void testGetProduct() throws Exception {
	        
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        
	        
	        ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort("/v1/product?id=1"), HttpMethod.GET, entity, String.class);

	        String expected = "{\"id\":1,\"brand\":\"Apple\",\"model\":\"iPhone X\",\"price\":2000.0}";

	        JSONAssert.assertEquals(expected, response.getBody(), false);
	    }	
	  
	  @Test
	   public void testGetHc() throws Exception {
	        
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        
	        
	        ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort("/v1/hc"), HttpMethod.GET, entity, String.class);

	        String expected = "{\"version\":\"0.0.1-SNAPSHOT\"}";

	        JSONAssert.assertEquals(expected, response.getBody(), false);
	    }	
	  

	    private String createURLWithPort(String uri) {
	        return "http://localhost:" + 8080 + uri;
	    }	

}
