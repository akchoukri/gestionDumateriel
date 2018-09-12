package com.ymagis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ymagis.dao.ClientRepository;
import com.ymagis.dao.EmpruntRepository;
import com.ymagis.dao.MaterielRepository;
import com.ymagis.model.Client;
import com.ymagis.model.Emprunt;
import com.ymagis.model.Materiel;

@SpringBootApplication
public class GestionDuMaterielApplication {
	@Autowired
	private ClientRepository client;
	@Autowired
	private static MaterielRepository materielRepository;
	@Autowired
	private static EmpruntRepository empruntRepository;

	public static void main(String[] args) throws ParseException {

//		// SpringApplication.run(GestionDuMaterielApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(GestionDuMaterielApplication.class, args);
		ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
		MaterielRepository materielRepository = ctx.getBean(MaterielRepository.class);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		clientRepository.save(new Client("Rabab", "Tahiri", "Rabat", "rabab@gmail.xom", "321554", df.parse("01/04/2018")));
//		clientRepository.save(new Client("ibtissam", "Tahiri", "Rabat", "rabab@gmail.xom", "321554", df.parse("01/04/2018")));
//		clientRepository.save(new Client("mouna", "Tahiri", "Rabat", "rabab@gmail.xom", "321554", df.parse("01/04/2018")));
//		materielRepository.save(new Materiel("xxx", "PC Dell i7", df.parse("01/04/2018"), true, "bonne", 3));
//		materielRepository.save(new Materiel("yyyy", "Sourie Dell", df.parse("01/04/2018"), true, "bonne", 2));
//		materielRepository.save(new Materiel("ssss", "ClavierDell", df.parse("01/04/2018"), true, "bonne", 3));
//		materielRepository.save(new Materiel("aaaa", "PC Acer i7", df.parse("01/04/2018"), true, "bonne", 3));
//		materielRepository.save(new Materiel("bbbb", "PC Acer i7", df.parse("01/04/2018"), true, "bonne", 3));
	}

}
