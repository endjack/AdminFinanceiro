package com.admin.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.admin.models.Recibo;

@Repository
public interface ReciboRepository extends CrudRepository<Recibo, String>{

	Recibo findByIdRec(long idRec);
	
	
}
