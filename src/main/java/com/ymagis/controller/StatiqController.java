package com.ymagis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymagis.dao.EmpruntRepository;
import com.ymagis.model.Emprunt;

@RestController
public class StatiqController {
	
	@Autowired
	private EmpruntRepository empruntRepository;
	
	// recuperer les 3 mois derrr
		@RequestMapping(value = "/mois", method = RequestMethod.GET)
		public List<Date> getLastMonths(  Date date) {
			
			List<Date> list = new ArrayList<>();
			
			for(int i=0;i<4;i++)
				{
				date = new Date();
				date.setMonth(date.getMonth()-i);
				list.add(date);
				
				}

			return list;
		}
		
		// emprut
		@RequestMapping(value = "/nbrEmprun", method = RequestMethod.GET)
		public int getempruntR(  Date date) {
			List<Emprunt> emprunts = empruntRepository.getEmpruntRetour();
     return emprunts.size();
		}
	
}
