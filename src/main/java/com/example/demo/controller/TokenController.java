package com.example.demo.controller;

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
		System.out.println("AQUIIIIII");
		System.out.println(jwtGenerator);
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping
	public ResponseEntity<String> generate(@RequestBody final Login login) {
		JwtUser jwtUser = new JwtUser();
		jwtUser = existUser(login);
		
		if (jwtUser != null) {
			return new ResponseEntity<String>(jwtGenerator.generate(jwtUser), HttpStatus.OK);
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
