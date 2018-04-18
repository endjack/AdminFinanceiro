package com.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.admin.models.Cliente;
import com.admin.repositorys.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository cr;
	

	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.GET)
	public String formCliente(){
		return "cadastrarClientes";
	}
	
	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.POST)
	public String formCliente(Cliente cliente){	
		cr.save(cliente);
		return "redirect:/clientes";
	}
	
	@RequestMapping("/clientes")
	public ModelAndView listarClientes(){
		ModelAndView mv = new ModelAndView("clientes");
		Iterable<Cliente> clientes = cr.findAll();
		System.out.println("Tamanho Tabela Cliente="+ cr.count());
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	@RequestMapping("/deletarCliente")
	public String deletarCliente(long id){
		Cliente cliente = cr.findById(id);
		cr.delete(cliente);
		return "redirect:/clientes";
	}
	
	@RequestMapping("/delCliente")
	public String delCliente(String idCli){
		long aux = Long.parseLong(idCli);
		Cliente cliente = cr.findById(aux);
		cr.delete(cliente);
		return "redirect:/clientes";
	}
	
	@RequestMapping(value="/editar", method=RequestMethod.GET)
	public ModelAndView editarCliente(long id){
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("editarCliente");
		mv.addObject("cliente",cliente);	
		return mv;
	}
	
	@RequestMapping(value="/editar", method=RequestMethod.POST)
	public String salvarEdicaoCliente(Cliente cliente){
		cr.save(cliente);
		return "redirect:/clientes";
	}	
}
