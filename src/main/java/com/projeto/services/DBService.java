package com.projeto.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.domain.Auth;
import com.projeto.domain.Costumer;
import com.projeto.repositories.AuthRepository;
import com.projeto.repositories.CostumerRepository;

@Service
public class DBService {

	@Autowired
	private CostumerRepository costumerRep;
	
	@Autowired
	private AuthRepository authRep;
	
	public void initDatabase() {
		Auth auth = new Auth("12345678", "123");
		Auth auth2 = new Auth("12345678", "123");
		
		Costumer costumer = new Costumer(null,"Cliente 1", "12345678", auth);
		Costumer costumer2 = new Costumer(null,"Cliente 2", "12345678", auth2);
		
		authRep.saveAll(Arrays.asList(auth,auth2));
		costumerRep.saveAll(Arrays.asList(costumer,costumer2));
		
	}
}
