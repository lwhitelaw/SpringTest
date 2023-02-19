package me.lwhitelaw.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTEndpoint {
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!";
	}
}
