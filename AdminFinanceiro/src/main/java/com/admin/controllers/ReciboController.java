package com.admin.controllers;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.admin.Extenso;
import com.admin.models.Recibo;
import com.admin.repositorys.ReciboRepository;


@Controller
public class ReciboController {
	
	Locale ptBr = new Locale("pt", "BR");
	NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);

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
	
	@RequestMapping(value="/gerarRecibo", method=RequestMethod.GET)
	public ModelAndView gerarRecibo(long id){
		Recibo recibo = rr.findByIdRec(id);
		ModelAndView mv = new ModelAndView("modelRecibo");

		 // Converte do tipo BigDecimal para a String por extenso.
        Extenso teste = new Extenso(new BigDecimal(recibo.getValor()));

        // Mostra o número informado no formato de valor monetário.
        System.out.println("Numero  : " + teste.DecimalFormat());

        // Mostra o número informado no formato de valor por extenso.
        System.out.println("Extenso : " + teste.toString());
		
        mv.addObject("recibo",recibo);	
		mv.addObject("valorExtenso", teste.toString());
		mv.addObject("valorFormatado", teste.DecimalFormat());	
		return mv;
	}
	
	@RequestMapping(value="/editarRecibo", method=RequestMethod.POST)
	public String salvarEdicaoRecibo(Recibo recibo){
		rr.save(recibo);
		return "redirect:/recibos";
	}
	
}
