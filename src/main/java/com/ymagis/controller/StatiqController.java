package com.ymagis.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
		public List<Date> getLastMonths(  ) {
			
			List<Date> list = new ArrayList<>();
			
			for(int i=0;i<4;i++)
				{
				Date date = new Date();
				
				if((date.getDate()==1)&&(date.getHours()==8)
						&&(date.getMinutes()==0)&&(date.getSeconds()==0)){
						date.setMonth(date.getMonth()-i-1);
				}else
					date.setMonth(date.getMonth()-i);
				
				list.add(date);
				}
			return list;
		}
		
		// emprut
		@RequestMapping(value = "/nbrEmprun", method = RequestMethod.GET)
		public Map<Date, Map<String, Integer>> getempruntR() throws ParseException {
			Map<Date, List<Integer>> map = new TreeMap<>();   
			Map<Date, Map<String, Integer>> map1 = new TreeMap<>(); 
		//Map<Date, List<Integer>>

			List<Date> dates = getLastMonths();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy/dd");
			for(Date date : dates) {
				
				List<Integer> integers = new ArrayList<>();
				List<Emprunt> empruntRetard = new ArrayList<>();
				List<Emprunt> empruntSsRetard = new ArrayList<>();
				Map<String,Integer> map2 = new TreeMap<>(); 
				
			LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			List<Emprunt> emprunts =   empruntRepository.getEmpruntRetour(localDate.getYear(),localDate.getMonthValue());
			String s = formatter.format(date);
			
			date = formatter.parse(s);
			for(Emprunt emprunt : emprunts) {
				if(emprunt.getDateRetourPrevu().getTime()>= emprunt.getDateRetour().getTime()) {
					empruntSsRetard.add(emprunt);
				}
				else empruntRetard.add(emprunt);
			}
				 integers.add(0, empruntSsRetard.size());
				 integers.add(1, empruntRetard.size());
				
			   map2.put("ssRetard",empruntSsRetard.size()); 
			   map2.put("retard",empruntRetard.size() ); 
			   map.put(date, integers);
			   map1.put(date, map2);
			   
			}
			System.out.println(map1);
     return map1;
     
		}
		// recuperer les nouveau client par mois 
		@RequestMapping(value = "/nvClient", method = RequestMethod.GET)
		public Map<Date,Integer> getNvClient( ) throws ParseException {
			Map<Date,Integer> map = new TreeMap<>();
			List<Date> dates = getLastMonths();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy/dd");
			for(Date date : dates) {
				
				LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				List<Client> list = clientRepository.getNvClientByMonth(localDate.getYear(),localDate.getMonthValue());
				String s = formatter.format(date);
				date = formatter.parse(s);
				map.put(date, list.size());
			}
			
			return map;
		}
		
		// recuperer les emprunt d'un cleint 3 mois derrr
		@RequestMapping(value = "/emp", method = RequestMethod.GET)
		public Map<String,Integer> getEmprByclient(  ) {
			Date date = new Date();
			Map<String,Integer> map = new TreeMap<>();
			List<Client> clients = clientRepository.getClients();
			LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			for(Client client : clients) {
				List<Emprunt> list = new ArrayList<>();
				list = empruntRepository.getEmpruntsClientByMonth(localDate.getYear(),localDate.getMonthValue(),client.getIdClient());
			
				map.put(client.getNomClient()+" "+client.getPrenomClient(), list.size());
			}
			
	

			return map;
		}
		
		// comapraison l'etat des materiels 
		@RequestMapping(value = "/etatMat", method = RequestMethod.GET)
		public Map<String,Integer> getEtatMat(  ) {
			Map<String,Integer> map = new TreeMap<>();
			List<Materiel> materiels =materielRepository.findAll(); 
			int i,j,k;
			i=j=k=0;
			for(Materiel materiel : materiels) {
				if(materiel.getEtatMateriel().equals("bonne etat"))
						i++;
				else if(materiel.getEtatMateriel().equals("en panne"))
					j++;
				else k++;
				
			}
			
			map.put("bonne etat", i);
			map.put("en panne", j);
			map.put("endommagé", k);

			return map;
		}
		
		// recuperer les nouveau Materiel par mois 
		@RequestMapping(value = "/nvMat", method = RequestMethod.GET)
		public Map<Date,Integer> getNvMat( ) throws ParseException {
			Map<Date,Integer> map = new TreeMap<>();
			List<Date> dates = getLastMonths();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy/dd");
			for(Date date : dates) {
				
				LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				List<Materiel> list =materielRepository.getNvMatByMonth(localDate.getYear(),localDate.getMonthValue());
				String s = formatter.format(date);
				date = formatter.parse(s);
				map.put(date, list.size());
			}
			
			return map;
		}
		
		// recuperer les nouveau emprunt par mois 
		@RequestMapping(value = "/nvEmp", method = RequestMethod.GET)
		public Map<Date,Integer> getNvEmp( ) throws ParseException {
			Map<Date,Integer> map = new TreeMap<>();
			List<Date> dates = getLastMonths();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy/dd");
			for(Date date : dates) {
				
				LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				List<Emprunt> list =empruntRepository.getEmpruntsByMonth(localDate.getYear(),localDate.getMonthValue());
						
				String s = formatter.format(date);
				date = formatter.parse(s);
				map.put(date, list.size());
			}
			
			return map;
		}
}
