package com.ymagis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ymagis.dao.ClientRepository;
import com.ymagis.dao.EmpruntRepository;
import com.ymagis.dao.MaterielRepository;
import com.ymagis.model.Client;
import com.ymagis.model.Emprunt;
import com.ymagis.model.Materiel;
import com.ymagis.service.EmailService;
import com.ymagis.service.geneChart;
import com.ymagis.dao.CategorieRepository;
import com.ymagis.dao.MaterielRepository;
import com.ymagis.model.Categorie;
import com.ymagis.model.Materiel;

@SpringBootApplication
@EnableScheduling
public class GestionDuMaterielApplication implements CommandLineRunner{

	@Autowired
	private MaterielRepository materielRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private  geneChart geneChart;
	@Autowired
	private EmailService emailService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionDuMaterielApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		



	}

}
