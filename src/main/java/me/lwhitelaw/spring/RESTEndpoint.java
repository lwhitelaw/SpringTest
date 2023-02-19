package me.lwhitelaw.spring;

import java.nio.ByteBuffer;
import java.nio.file.Paths;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.lwhitelaw.hoard.FileRepository;
import me.lwhitelaw.hoard.Hashes;

@RestController
public class RESTEndpoint {
	private final Logger logger;
	private final FileRepository repo;
	
	public RESTEndpoint() {
		logger = LoggerFactory.getLogger(getClass());
		repo = new FileRepository(Paths.get("./hoard.blk"), false);
		logger.info("Started the REST endpoint!");
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!";
	}
	
	// /string?value=""
	@GetMapping("/string")
	public String string(@RequestParam("value") String value) {
		return "Your string is " + value;
	}
	
	// Test with hash D0E47486BBF4C16ACAC26F8B653592973C1362909F90262877089F9C8A4536AF
	
	@GetMapping("/read/{sha3}")
	public byte[] read(@PathVariable String sha3) {
		logger.info("Reading " + sha3);
		ByteBuffer buf = repo.readBlock(Hashes.stringToHash(sha3));
		if (buf == null) return new byte[0]; // no data here, so return nothing (until I figure out how to 404)
		if (!buf.hasArray()) throw new RuntimeException("no backing array");
		return buf.array();
	}
	
	@GetMapping("/binary")
	public byte[] binary() {
		return "Binary string".getBytes();
	}
}
