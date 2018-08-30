package com.ymagis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

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
//		SpringApplication.run(GestionDuMaterielApplication.class, args);
		ApplicationContext ctx =SpringApplication.run(GestionDuMaterielApplication.class, args);
		ClientRepository clientRepository=ctx.getBean(ClientRepository.class);
		MaterielRepository materielRepository=ctx.getBean(MaterielRepository.class);
		List<Emprunt> emprunts = new ArrayList<>();
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		Emprunt emprunt1 = new Emprunt(df.parse("26/08/2018"), df.parse("30/08/2018"), null);
		Emprunt emprunt2 = new Emprunt(df.parse("01/09/2018"), df.parse("20/09/2018"), null);
		emprunts.add(emprunt1);emprunts.add(emprunt2);
		Client client = new Client("Rabab","Tahiri","Rabat","rabab@gmail.xom","321554",df.parse("01/04/2018"));
		client.setEmprunts(emprunts);
		clientRepository.save( client);
		clientRepository.save( new Client("ibtissam","Tahiri","Rabat","rabab@gmail.xom","321554",df.parse("01/04/2018")));
		clientRepository.save( new Client("mouna","Tahiri","Rabat","rabab@gmail.xom","321554",df.parse("01/04/2018")));
		materielRepository.save(new Materiel("xxx", "PC Dell i7" ,df.parse("01/04/2018"), true,"bonne", 3));
		materielRepository.save(new Materiel("xxx", "Sourie Dell" ,df.parse("01/04/2018"), true,"bonne", 3));
		materielRepository.save(new Materiel("xxx", "ClavierDell" ,df.parse("01/04/2018"), true,"bonne", 3));
		materielRepository.save(new Materiel("xxx", "PC Acer i7" ,df.parse("01/04/2018"), true,"bonne", 3));
	}

	
}
