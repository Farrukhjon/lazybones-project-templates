package com.farrukh.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	public String getUsers(final UserRestClient restClient) {
		return restClient.getUsers();
	}

}

@Component
class UserRestClient {

	private final RestTemplate restTemplate;
        @Autowired
	UserRestClient(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@HystrixCommand(fallbackMethod = "getDefault")
	public String getUsers() {
		String url = "http://localhost:8081/users";
		return restTemplate.getForObject(url, String.class);
	}

	public List getDefault() {
		return Collections.emptyList();
	}

}
