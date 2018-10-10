package com.ymagis.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymagis.dao.UserRepository;
import com.ymagis.model.AppUser;
import com.ymagis.model.RegisterForm;
import com.ymagis.service.AccountService;

@RestController
public class AccountRestController {
	@Autowired
	private AccountService  accountService;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/registerUser")
	public AppUser registerUser(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRepassword())) throw new  RuntimeException("Vous devez confirmer votre mot de pass");
		AppUser user=accountService.findUserByUsername(userForm.getUsername());
		if(user!=null) throw new RuntimeException("Ce nom d'utilisateur existe deja, Veuillez choisir un autre");
		AppUser appUser=new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		accountService.saveUser(appUser);
		accountService.addRoleToUse(userForm.getUsername(), "ADMIN");
		return appUser;
	}
//chercher utilisteur par son nom d'utilisateur
	// chercher les clients

		@RequestMapping(value = "/user", method = RequestMethod.GET)
		public AppUser chercherUser(@RequestParam(name = "username", defaultValue = "") String username) {
			AppUser user=userRepository.findByUsername(username);
			return user;
		}

}
