package com.admin.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.models.Recibo;
import com.admin.repositorys.ReciboRepository;

@RestController
@RequestMapping("/ws/recibos")
public class ReciboResource {
	
	@Autowired
	private ReciboRepository rr;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Recibo> listaRecibos(){	
		Iterable<Recibo> listaRecibo = rr.findAll();
		return listaRecibo;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Recibo> busca(@PathVariable long id){	
		Recibo recibo = rr.findByIdRec(id);
		
		if(recibo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(recibo);
	}

}
