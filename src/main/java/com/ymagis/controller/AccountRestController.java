package com.ymagis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ymagis.model.AppUser;
import com.ymagis.model.RegisterForm;
import com.ymagis.service.AccountService;

@RestController
public class AccountRestController {
	@Autowired
	private AccountService  accountService;
	@PostMapping("/registerUser")
	public AppUser registerUser(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRepassword())) throw new  RuntimeException("Vous devez confirmer votre mot de pass");
		AppUser user=accountService.findUserByUsername(userForm.getUsername());
		if(user!=null) throw new RuntimeException("Ce nom d'utilisateur existe deja, Veuillez choisir un autre");
		AppUser appUser=new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		accountService.saveUser(appUser);
		accountService.addRoleToUse(userForm.getUsername(), "USER");
		return appUser;
	}

}
