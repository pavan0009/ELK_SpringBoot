package com.simple.elk.app.rest;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ElkResource {
	
	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping(value = "/elk")
	public ResponseEntity<Object> elk() {

		
		return restTemplate.exchange
		 ("https://10.15.1.148:9200/agency/_search", HttpMethod.GET, new HttpEntity(createHeaders()), Object.class);
	}
	
	HttpHeaders createHeaders(){
		   return new HttpHeaders() {{
		         String auth = "es_client" + ":" + "es_client";
		         byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}
}
