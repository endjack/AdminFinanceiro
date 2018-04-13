package com.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginasController {

	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/modeloRecibo")
	public String gerarImpressoes() {
		return "modelRecibo";
	}	
	
}
