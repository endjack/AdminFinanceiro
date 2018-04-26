package com.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.admin.models.Usuario;
import com.admin.repositorys.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository ur;
	
	public void criarLoginAdmin() {
		Usuario userAdmin = new Usuario();
		userAdmin.setLogin("admin");
		userAdmin.setNomeCompleto("USER ADMIN");
		userAdmin.setSenha(new BCryptPasswordEncoder().encode("er34er34"));
		ur.save(userAdmin);
		
		System.out.println(new BCryptPasswordEncoder().encode("er34er34"));
	}
}
