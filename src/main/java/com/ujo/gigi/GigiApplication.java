package com.ujo.gigi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@SpringBootApplication
public class GigiApplication {
	public static void main(String[] args) {
		SpringApplication.run(GigiApplication.class, args);
	}

}
