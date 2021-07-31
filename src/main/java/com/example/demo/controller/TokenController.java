package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JwtUser;
import com.example.demo.model.Login;
import com.example.demo.security.jwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	private jwtGenerator jwtGenerator;
	
	public TokenController(jwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping
	public ResponseEntity<?> generate(@RequestBody final Login login) {
		JwtUser jwtUser = new JwtUser();
		jwtUser = existUser(login);
		HashMap<String, String> lista = new HashMap<String, String>();
		lista.put("Token", jwtGenerator.generate(jwtUser));
		if (jwtUser != null) {
			return new ResponseEntity<HashMap<String, String>>(lista, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	private JwtUser existUser(Login login) {
		if(login.getUser().equals("julio") && login.getPassword().equals("123456")) {
			JwtUser jwtUser = new JwtUser();
			jwtUser.setUserName(login.getUser());
			jwtUser.setId(1);
			jwtUser.setRole("Admin");
			return jwtUser;
		}
		return null;
	}
}
