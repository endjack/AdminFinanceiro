package com.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.admin.models.Usuario;
import com.admin.repositorys.UsuarioRepository;

@Service
public class ImplementsUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario user = ur.findByLogin(login);
		
		if(user == null) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}

		return user;
	}

}
