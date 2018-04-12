package com.admin.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {
	
	@RequestMapping("/pdf")
	public void gerarPdf() throws IOException, InterruptedException {
		
	}
	
}
