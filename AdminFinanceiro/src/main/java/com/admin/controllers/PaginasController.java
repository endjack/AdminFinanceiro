package com.admin.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.models.Cliente;
import com.admin.models.Recibo;
import com.admin.repositorys.ClienteRepository;
import com.admin.repositorys.ReciboRepository;

@Controller
public class PaginasController {

	@Autowired	
	ClienteRepository crepo;
	
	@Autowired	
	ReciboRepository rrepo;
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		long totalClientes = crepo.count();
		long totalRecibos = rrepo.count();
		ArrayList<Cliente> todosClientes = (ArrayList<Cliente>) crepo.findAll();
		ArrayList<Cliente> ultClientes = new ArrayList<Cliente>();
		ArrayList<Recibo> todosRecibos = (ArrayList<Recibo>) rrepo.findAll();
		ArrayList<Recibo> ultRecibos= new ArrayList<Recibo>();
		 
		if(todosClientes.size() >= 1) {	
			ultClientes.add(todosClientes.get(todosClientes.size()-1));
		}
		if(todosClientes.size() >= 2){
			ultClientes.add(todosClientes.get(todosClientes.size()-2));	
		}
		if(todosClientes.size() >= 3){
			ultClientes.add(todosClientes.get(todosClientes.size()-3));
		}
		
		if(todosRecibos.size() >= 1) {	
			ultRecibos.add(todosRecibos.get(todosRecibos.size()-1));
		}
		if(todosRecibos.size() >= 2){
			ultRecibos.add(todosRecibos.get(todosRecibos.size()-2));	
		}
		if(todosRecibos.size() >= 3){
			ultRecibos.add(todosRecibos.get(todosRecibos.size()-3));
		}
	
		System.out.println("Últimos Cadastrados: ");
		for (int i = 0; i < ultClientes.size(); i++) {	
			System.out.println(ultClientes.get(i).getNome());
		}		
		System.out.println("Total de Cliente no Banco: "+totalClientes);
		
		
		mv.addObject("totalClientes",totalClientes);
		mv.addObject("ultClientes",ultClientes);
		mv.addObject("totalRecibos",totalRecibos);
		mv.addObject("ultRecibos",ultRecibos);
		return mv;
	}
	
	@RequestMapping("/modeloRecibo")
	public String gerarImpressoes() {
		return "modelRecibo";
	}	
	
}
