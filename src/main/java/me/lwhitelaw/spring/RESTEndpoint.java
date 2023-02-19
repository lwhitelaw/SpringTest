package me.lwhitelaw.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTEndpoint {
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!";
	}
	
	// /string?value=""
	@GetMapping("/string")
	public String string(@RequestParam("value") String value) {
		return "Your string is " + value;
	}
	
	@GetMapping("/read/{sha3}")
	public String read(@PathVariable String sha3) {
		return "Requested " + sha3;
	}
	
	@GetMapping("/binary")
	public byte[] binary() {
		return "Binary string".getBytes();
	}
}
