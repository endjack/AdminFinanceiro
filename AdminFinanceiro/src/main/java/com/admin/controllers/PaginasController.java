package com.admin.controllers;


import java.awt.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.models.Cliente;
import com.admin.models.Recibo;
import com.admin.models.Voucher;
import com.admin.repositorys.ClienteRepository;
import com.admin.repositorys.ReciboRepository;
import com.admin.repositorys.VoucherRepository;

@Controller
public class PaginasController {

	@Autowired	
	ClienteRepository crepo;
	
	@Autowired	
	ReciboRepository rrepo;
	
	@Autowired	
	VoucherRepository vrepo;
	
	@RequestMapping("/")
	public ModelAndView home() throws ParseException {
		ModelAndView mv = new ModelAndView("home");
		long totalClientes = crepo.count();
		long totalRecibos = rrepo.count();
		long totalVouchers = vrepo.count();
		
		ArrayList<String> tempoRestanteCheckins = new ArrayList<>();
		
		ArrayList<Cliente> todosClientes = (ArrayList<Cliente>) crepo.findAll();
		ArrayList<Cliente> ultClientes = new ArrayList<Cliente>();
		
		ArrayList<Recibo> todosRecibos = (ArrayList<Recibo>) rrepo.findAll();
		ArrayList<Recibo> ultRecibos= new ArrayList<Recibo>();
		
		
		ArrayList<Voucher> todosVouchers = (ArrayList<Voucher>) vrepo.findAll();
		ArrayList<Voucher> ultVouchers = new ArrayList<Voucher>();
		
		
		 
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
		
		
		if(todosVouchers.size() >= 1) {	
			ultVouchers.add(todosVouchers.get(todosVouchers.size()-1));

			long tempo = calcularDatasCheckin(todosVouchers.get(todosVouchers.size()-1).getCheckin());
			if(tempo <= 0) {
				tempoRestanteCheckins.add("Já passou!");
			}else if(tempo == 1){
				tempoRestanteCheckins.add("Entra Hoje!");
			}else if(tempo == 2){
					tempoRestanteCheckins.add("Entra Amanhã!");
			}else {
				tempoRestanteCheckins.add("Faltam "+tempo+" dias!");
			}				
		}
		if(todosVouchers.size() >= 2){
			ultVouchers.add(todosVouchers.get(todosVouchers.size()-2));	
			
			long tempo = calcularDatasCheckin(todosVouchers.get(todosVouchers.size()-2).getCheckin());
			if(tempo <= 0) {
				tempoRestanteCheckins.add("Já passou!");
			}else if(tempo == 1){
				tempoRestanteCheckins.add("Entra Hoje!");
			}else if(tempo == 2){
				tempoRestanteCheckins.add("Entra Amanhã!");
			}else {
			tempoRestanteCheckins.add("Faltam "+tempo+" dias!");
			}	
		}
		if(todosVouchers.size() >= 3){
			ultVouchers.add(todosVouchers.get(todosVouchers.size()-3));

			long tempo = calcularDatasCheckin(todosVouchers.get(todosVouchers.size()-3).getCheckin());
			if(tempo <= 0) {
				tempoRestanteCheckins.add("Já passou!");
			}else if(tempo == 1){
				tempoRestanteCheckins.add("Entra Hoje!");
			}else if(tempo == 2){
				tempoRestanteCheckins.add("Entra Amanhã!");
			}else {
			tempoRestanteCheckins.add("Faltam "+tempo+" dias!");
			}	
		}
	
		System.out.println("Últimos Cadastrados: ");
		for (int i = 0; i < ultClientes.size(); i++) {	
			System.out.println(ultClientes.get(i).getNome());
		}		
		System.out.println("Total de Cliente no Banco: "+totalClientes);
		
		System.out.println("Próximos Vouchers");
		for (int i = 0; i < tempoRestanteCheckins.size(); i++) {	
			System.out.println(tempoRestanteCheckins.get(i).toString());
		}
		
		mv.addObject("totalClientes",totalClientes);
		mv.addObject("ultClientes",ultClientes);
		mv.addObject("totalRecibos",totalRecibos);
		mv.addObject("ultRecibos",ultRecibos);
		mv.addObject("totalVouchers",totalVouchers);
		mv.addObject("ultVouchers",ultVouchers);
		mv.addObject("tempoRestanteCheckins",tempoRestanteCheckins);
		return mv;
	}
	
	@RequestMapping("/modeloRecibo")
	public String gerarImpressoes() {
		return "modelRecibo";
	}	
	
	public long calcularDatasCheckin(String dataCheckin) throws ParseException {
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		Calendar dataHoje = Calendar.getInstance();
				
        df.setLenient(false);
        Date d1 = dataHoje.getTime();      
        Date d2 = df.parse (dataCheckin);
        System.out.println (d2);
        long dt = (d2.getTime() - d1.getTime()); 
        long restante = (dt / 86400000L)+1;
        System.out.println ("Hoje é: "+df.format(dataHoje.getTime())+", Faltam "+restante+" dias para "+df.format(d2)); // passaram-se 67111 dias
        return restante;
	}
	
}
