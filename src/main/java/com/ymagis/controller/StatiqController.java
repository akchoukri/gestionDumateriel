package com.ymagis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ymagis.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymagis.dao.ClientRepository;
import com.ymagis.dao.EmpruntRepository;
import com.ymagis.dao.MaterielRepository;
import com.ymagis.model.Client;
import com.ymagis.model.Emprunt;
import com.ymagis.model.Materiel;

@RestController
public class StatiqController {

	@Autowired
	private EmpruntRepository empruntRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private MaterielRepository materielRepository;

	// recuperer les 3 mois derrr
	@RequestMapping(value = "/mois", method = RequestMethod.GET)
	public List<Date> getLastMonths() {

		List<Date> list = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			Date date = new Date();

			// la date pour generer les graphes en retournant les mois dernier
			if ((date.getDate() == 1) && (date.getHours() == 8) && (date.getMinutes() == 0)
					&& (date.getSeconds() == 0)) {
				date.setMonth(date.getMonth() - i - 1);
			} else
				date.setMonth(date.getMonth() - i);

			list.add(date);
		}
		return list;
	}

	// recuperer les emprunts retourné sans retard et avec pour les mois derniers
	@RequestMapping(value = "/nbrEmprun", method = RequestMethod.GET)
	public Map<Date, Map<String, Integer>> getempruntR() throws ParseException {
		Map<Date, Map<String, Integer>> empruntByMonth = new TreeMap<>();
		List<Date> dates = getLastMonths();// les mois derniers
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy/dd");
		for (Date date : dates) {// pour chaque mois
			List<Emprunt> empruntRetard = new ArrayList<>();
			List<Emprunt> empruntSsRetard = new ArrayList<>();
			Map<String, Integer> mapEmprunt = new TreeMap<>();
			//Convert from Date to LocalDate
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			// les emprunts d'un mois
			List<Emprunt> emprunts = empruntRepository.getEmpruntRetour(localDate.getYear(), localDate.getMonthValue());
			String s = formatter.format(date);
			date = formatter.parse(s);
			for (Emprunt emprunt : emprunts) {// pour chaque emprunt
				// je cherche les emprunt qui ne sont pas en retard
				if (emprunt.getDateRetourPrevu().getTime() >= emprunt.getDateRetour().getTime()) {
					empruntSsRetard.add(emprunt);
				} else // les emprunts en retard
					empruntRetard.add(emprunt);
			}

			mapEmprunt.put("ssRetard", empruntSsRetard.size());
			mapEmprunt.put("retard", empruntRetard.size());
			empruntByMonth.put(date, mapEmprunt);

		}
		return empruntByMonth;

	}

	// recuperer les nouveau client par mois
	@RequestMapping(value = "/nvClient", method = RequestMethod.GET)
	public Map<Date, Integer> getNvClient() throws ParseException {
		Map<Date, Integer> nvCLientByMonths = new TreeMap<>();
		List<Date> dates = getLastMonths();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy/dd");
		for (Date date : dates) {

			//Convert from Date to LocalDate
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			List<Client> list = clientRepository.getNvClientByMonth(localDate.getYear(), localDate.getMonthValue());
			//changer format de date en MM/yyyy/dd de type string
			String s = formatter.format(date);
			//Convert date from String to Date
			date = formatter.parse(s);
			nvCLientByMonths.put(date, list.size());
		}

		return nvCLientByMonths;
	}

	// recuperer les emprunt d'un cleint 3 mois derrr
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public Map<String, Integer> getEmprByclient() {
		Date date = new Date();
		Map<String, Integer> empruntByClient = new TreeMap<>();
		List<Client> clients = clientRepository.getClients();
		//Convert from Date to LocalDate
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for (Client client : clients) {
			// pour chaque clients
			List<Emprunt> listEmpr = new ArrayList<>();
			// les emprunts du client du mois courant
			listEmpr = empruntRepository.getEmpruntsClientByMonth(localDate.getYear(), localDate.getMonthValue(),
					client.getIdClient());
			empruntByClient.put(client.getNomClient()+Constantes.ESPACE+client.getPrenomClient(),listEmpr.size());
		}
		return empruntByClient;
	}

	// comparer l'etat des materiels
	@RequestMapping(value = "/etatMat", method = RequestMethod.GET)
	public Map<String, Integer> getEtatMat() {
		Map<String, Integer> etatMat = new TreeMap<>();
		List<Materiel> materiels = materielRepository.findAll();
		int i, j, k;
		i = j = k = 0;
		for (Materiel materiel : materiels) {
			if (materiel.getEtatMateriel().equals(Constantes.BONNE_ETAT))
				i++;// les nmbrs des materiels qui ont en bonne etat
			else if (materiel.getEtatMateriel().equals(Constantes.EN_PANNE))
				j++;// les nmbrs des materiels qui ont en panne
			else
				k++;// les nmbrs des materiels qui ont en endommagé

		}

		etatMat.put(Constantes.BONNE_ETAT, i);
		etatMat.put(Constantes.EN_PANNE, j);
		etatMat.put(Constantes.ENDOMMAGE, k);

		return etatMat;
	}

	// recuperer les nouveau Materiel par mois
	@RequestMapping(value = "/nvMat", method = RequestMethod.GET)
	public Map<Date, Integer> getNvMat() throws ParseException {
		Map<Date, Integer> nvMateriels = new TreeMap<>();
		List<Date> dates = getLastMonths();
		SimpleDateFormat formatter = new SimpleDateFormat(Constantes.PATTERN_MM_YYYY_DD);
		for (Date date : dates) {// pour chaque mois

			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			// recuperer les nouveaux materiels ajouter
			List<Materiel> list = materielRepository.getNvMatByMonth(localDate.getYear(), localDate.getMonthValue());
			String s = formatter.format(date);
			date = formatter.parse(s);
			nvMateriels.put(date, list.size());
		}

		return nvMateriels;
	}

	// recuperer les nouveaus emprunt de 3 derniers mois
	@RequestMapping(value = "/nvEmp", method = RequestMethod.GET)
	public Map<Date, Integer> getNvEmp() throws ParseException {
		Map<Date, Integer> nvEmprunt = new TreeMap<>();
		List<Date> dates = getLastMonths();
		SimpleDateFormat formatter = new SimpleDateFormat(Constantes.PATTERN_MM_YYYY_DD);
		for (Date date : dates) {

			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			// les nouveaux emprunt ajouter
			List<Emprunt> list = empruntRepository.getEmpruntsByMonth(localDate.getYear(), localDate.getMonthValue());

			//changer format de date en MM/yyyy/dd de type string
			String s = formatter.format(date);
			//Convert date from String to Date
			date = formatter.parse(s);
			nvEmprunt.put(date, list.size());
		}

		return nvEmprunt;
	}
}
