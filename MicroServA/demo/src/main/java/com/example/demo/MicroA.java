package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MicroA {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MicroA.class, args);

	}

}
