package com.admin.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.admin.models.Voucher;


public interface VoucherRepository extends CrudRepository<Voucher, String> {
	
	Voucher findByIdCin(long idCin);
}
