package com.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.admin.models.Recibo;
import com.admin.repositorys.ReciboRepository;

@Controller
public class ReciboController {

	@Autowired
	private ReciboRepository rr;
	

	@RequestMapping(value="/cadastrarRecibo", method=RequestMethod.GET)
	public String formRecibo(){
		return "cadastrarRecibo";
	}
	
	@RequestMapping(value="/cadastrarRecibo", method=RequestMethod.POST)
	public String formCliente(Recibo recibo){	
		rr.save(recibo);
		return "cadastrarRecibo";
	}
	
	@RequestMapping("/recibos")
	public ModelAndView listarRecibos(){
		ModelAndView mv = new ModelAndView("recibos");
		Iterable<Recibo> recibos = rr.findAll();
		System.out.println("Tamanho Tabela Recibo="+ rr.count());
		mv.addObject("recibos", recibos);
		return mv;
	}
	
	@RequestMapping("/deletarRecibo")
	public String deletarRecibo(long id){
		Recibo recibo = rr.findByIdRec(id);
		rr.delete(recibo);
		return "redirect:/recibos";
	}
	
	@RequestMapping("/delRecibo")
	public String delCliente(String idRec){
		long aux = Long.parseLong(idRec);
		Recibo recibo = rr.findByIdRec(aux);
		rr.delete(recibo);
		return "redirect:/clientes";
	}
	
	
	@RequestMapping(value="/editarRecibo", method=RequestMethod.GET)
	public ModelAndView editarRecibo(long id){
		Recibo recibo = rr.findByIdRec(id);
		ModelAndView mv = new ModelAndView("editarRecibo");
		mv.addObject("recibo",recibo);	
		return mv;
	}
	
	@RequestMapping(value="/editarRecibo", method=RequestMethod.POST)
	public String salvarEdicaoRecibo(Recibo recibo){
		rr.save(recibo);
		return "redirect:/recibos";
	}
	
}
