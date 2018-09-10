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
//		SpringApplication.run(GestionDuMaterielApplication.class, args);
		ApplicationContext ctx =SpringApplication.run(GestionDuMaterielApplication.class, args);
		ClientRepository clientRepository=ctx.getBean(ClientRepository.class);
		MaterielRepository materielRepository=ctx.getBean(MaterielRepository.class);
		List<Emprunt> emprunts = new ArrayList<>();
		List<Emprunt> empruntsI = new ArrayList<>();
		List<Materiel> materiels = new ArrayList<>();
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		Emprunt emprunt1 = new Emprunt(df.parse("01/08/2018"), df.parse("23/08/2018"), null);
		Emprunt emprunt2 = new Emprunt(df.parse("01/09/2018"), df.parse("20/09/2018"), null);
		Emprunt emprunt3 = new Emprunt(df.parse("01/09/2018"), df.parse("20/09/2018"), df.parse("02/10/2018"));
		Materiel materiel1 =  new Materiel("x1", "PC Dell i7" ,df.parse("01/04/2018"), false,"bonne", 3);
		Materiel materiel2 =  new Materiel("x2", "Sourie Dell" ,df.parse("01/04/2018"), false,"bonne", 3);
		materiels.add(materiel1);materiels.add(materiel2);
		emprunt1.setMateriels(materiels);
		emprunts.add(emprunt1);emprunts.add(emprunt2);
		Client Rabab = new Client("Rabab","Tahiri","Rabat","rabab@gmail.xom","321554",df.parse("01/04/2018"));
		Client ibtssam = new Client("ibtissam","Tahiri","Rabat","rabab@gmail.xom","321554",df.parse("01/04/2018"));
		emprunt2.setClient(Rabab);
		emprunt1.setClient(Rabab);
		emprunt3.setClient(ibtssam);
		Rabab.setEmprunts(emprunts);
		empruntsI.add(emprunt3);
		ibtssam.setEmprunts(empruntsI);
		clientRepository.save( Rabab);
		clientRepository.save( ibtssam);
		clientRepository.save( new Client("mouna","Tahiri","Rabat","rabab@gmail.xom","321554",df.parse("01/04/2018")));

		//materielRepository.save(materiel1);
		//materielRepository.save(materiel2);
		materielRepository.save(new Materiel("x3", "ClavierDell" ,df.parse("01/04/2018"), true,"bonne", 3));
		materielRepository.save(new Materiel("x4", "PC Acer i7" ,df.parse("01/04/2018"), true,"bonne", 3));
	}
}
