package com.admin.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.models.Cliente;
import com.admin.repositorys.ClienteRepository;

@RestController
@RequestMapping("/ws/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository cr;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Cliente> listaClientes(){	
		Iterable<Cliente> listaCliente = cr.findAll();
		return listaCliente;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> busca(@PathVariable long id){	
		Cliente cliente = cr.findById(id);
		
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

}
