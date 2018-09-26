package com.ymagis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymagis.dao.RoleRepository;
import com.ymagis.dao.UserRepository;
import com.ymagis.model.AppRole;
import com.ymagis.model.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
     @Autowired
     private UserRepository userRepository;
     @Autowired
     private RoleRepository roleRepository;
     
	@Override
	public AppUser saveUser(AppUser user) {
		//crypter le mot de pass avant l'enregistrement d'utilisateur
		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUse(String username, String roleName) {
		AppRole role=roleRepository.findByRoleName(roleName);
		AppUser user=userRepository.findByUsername(username);
		user.getRoles().add(role);
		
		
	}

	@Override
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
