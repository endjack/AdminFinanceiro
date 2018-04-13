package com.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.admin.models.Voucher;
import com.admin.repositorys.VoucherRepository;

@Controller
public class VoucherControllers {
	
	@Autowired
	private VoucherRepository vr;
	
	@RequestMapping(value="/cadastrarCheckin", method=RequestMethod.GET)
	public String formCheckin(){
		return "cadastrarCheckin";
	}
	
	@RequestMapping(value="/cadastrarCheckin", method=RequestMethod.POST)
	public String formCheckin(Voucher voucher){
		vr.save(voucher);
		return "redirect:checkins";
	}
	
	@RequestMapping("/checkins")
	public ModelAndView listarCheckins(){
		ModelAndView mv = new ModelAndView("checkins");
		Iterable<Voucher> checkins = vr.findAll();
		System.out.println("Tamanho Tabela checkins="+ vr.count());
		mv.addObject("checkins", checkins);
		return mv;
	}
	
	@RequestMapping("/delCheckin")
	public String delCheckin(String idCin){
		long idLong = Long.parseLong(idCin);
		Voucher aux = vr.findByIdCin(idLong);
		vr.delete(aux);
		return "redirect:/checkins";
	}
	
	
	@RequestMapping(value="/editarCheckin", method=RequestMethod.GET)
	public ModelAndView editarCheckin(long id){
		Voucher aux = vr.findByIdCin(id);
		ModelAndView mv = new ModelAndView("editarCheckin"); //TODO criar editar checkin
		mv.addObject("checkin",aux);	
		return mv;
	}
	
	@RequestMapping(value="/gerarCheckin", method=RequestMethod.GET)
	public ModelAndView gerarCheckin(long id){
		Voucher aux = vr.findByIdCin(id);
		ModelAndView mv = new ModelAndView("modelVoucher");		
        mv.addObject("checkin",aux);	
		return mv;
	}
	
}
