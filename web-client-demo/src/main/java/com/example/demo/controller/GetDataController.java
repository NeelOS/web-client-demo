package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDataController {

	@PostMapping("/getData")
	public ResponseEntity<Object> getData(){
		return new ResponseEntity<Object>("Hello World", HttpStatus.OK);
	}
}
