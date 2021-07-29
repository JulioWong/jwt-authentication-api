package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {

	@GetMapping("/only_jwt")
	public ResponseEntity<?> getInfoBancaria() {
		List<String> movimientosBancarios = obtenerUltimosMovimientosBancarios();
		if (movimientosBancarios != null && movimientosBancarios.size() > 0) {
			return new ResponseEntity<>(movimientosBancarios, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	private List<String> obtenerUltimosMovimientosBancarios() {
		List<String> movimientosBancarios = new ArrayList<String>();
		movimientosBancarios.add("S/ 20");
		movimientosBancarios.add("S/ 40");
		movimientosBancarios.add("S/ 30");
		movimientosBancarios.add("S/ 10");
		movimientosBancarios.add("S/ 80");
		movimientosBancarios.add("S/ 20");
		return movimientosBancarios;
	}
	
}
