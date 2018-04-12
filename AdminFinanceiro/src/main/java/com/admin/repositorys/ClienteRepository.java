package com.admin.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.admin.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String>{

	Cliente findById(long id);
	
	
}
