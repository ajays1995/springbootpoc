package com.example.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceApplicationTests {
	
	@Autowired
	protected RestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testEurekaClient() {
		
		String url = "http://ACCOUNT-SERVICE/";
		
		
		String response = restTemplate.getForObject(url+"api/account/1", String.class);
		
		System.out.println("account number "+response);
		
	}

}
