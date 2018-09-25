package com.ymagis.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGraphics2D;
import com.lowagie.text.pdf.PdfPrinterGraphics2D;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.ymagis.controller.StatiqController;

@Service
public class geneChart {

	@Autowired
	private StatiqController statiqController;
	@Autowired
	private EmailService emailService;
	@Autowired
	private  geneChart geneChart;
	
	// chart pour nbr cleint ajouté par mois (chart type line)
	public JFreeChart nbrClientAjoutByMonth() throws ParseException {
		Map<Date, Integer> data = statiqController.getNvClient();
		TimeSeries pop = new TimeSeries("nbrClient", Day.class);
		for(Date date :data.keySet()) {
			 
			LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.println(date);
			pop.add(new Day(date), data.get(date));
		}

		 TimeSeriesCollection dataset = new TimeSeriesCollection();
		 dataset.addSeries(pop);
		 
		 JFreeChart chart = ChartFactory.createTimeSeriesChart(
				 "nbr client ajouté par mois",
				 "année-mois",
				 "nbr client",
				 dataset,
				 true,
				 true,
				 false);
		
		 XYPlot plot = chart.getXYPlot();
		 DateAxis axis = (DateAxis) plot.getDomainAxis();
		 axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));
		 
		return chart;
		
	}
	
	//chart emprunt par client mois courant (chart type ar)
	public JFreeChart nbrEmpruntByClient() throws ParseException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<String,Integer> data = statiqController.getEmprByclient();
		for(String client : data.keySet()) {
			System.out.println(client+" => "+data.get(client));
			dataset.setValue(data.get(client), "nbr emprunt", client);
		}
		
		 JFreeChart chart = ChartFactory.createBarChart("nbr emprunt par client mois courant",
		 "clients", "nbr emprunt", dataset, PlotOrientation.HORIZONTAL,
		 false, true, false);
		 
		return chart;
		
	}
	//chart emprunts avec et sans retard
	public JFreeChart getempruntR() throws ParseException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<Date, Map<String, Integer>> data = statiqController.getempruntR();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		for(Date date : data.keySet()) {
			
			String s = formatter.format(date);
			date = formatter.parse(s);
			System.out.println(s.substring(3)+" => "+date);
			for(String cle : data.get(date).keySet())
			dataset.setValue(data.get(date).get(cle), cle, s.substring(3));
		}
		
		 JFreeChart chart = ChartFactory.createBarChart("emprunts avec et sans retard",
		 "mois/année", "br emprunt ", dataset, PlotOrientation.VERTICAL,
		 true, true, false);

		return chart;
		
	}
	// chart nbr emprunt par mois
		public JFreeChart nvEmpByMonth() throws ParseException {
			Map<Date, Integer> data = statiqController.getNvEmp();
			TimeSeries pop = new TimeSeries("nbrEmprunt", Day.class);
			for(Date date :data.keySet()) {
				 
				LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				System.out.println(date);
				pop.add(new Day(date), data.get(date));
			}

			 TimeSeriesCollection dataset = new TimeSeriesCollection();
			 dataset.addSeries(pop);
			 
			 JFreeChart chart = ChartFactory.createTimeSeriesChart(
					 "nbr Emprunt  par mois",
					 "année-mois",
					 "nbr emprunt",
					 dataset,
					 true,
					 true,
					 false);
			
			 XYPlot plot = chart.getXYPlot();
			 DateAxis axis = (DateAxis) plot.getDomainAxis();
			 axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));
			 
			return chart;
			
		}
		// chart nouveau materiel par mois
				public JFreeChart nvMatByMonth() throws ParseException {
					Map<Date, Integer> data = statiqController.getNvMat();
					TimeSeries pop = new TimeSeries("nbrEmprunt", Day.class);
					for(Date date :data.keySet()) {
						 
						LocalDate localDate =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						System.out.println(date);
						pop.add(new Day(date), data.get(date));
					}

					 TimeSeriesCollection dataset = new TimeSeriesCollection();
					 dataset.addSeries(pop);
					 
					 JFreeChart chart = ChartFactory.createTimeSeriesChart(
							 "nbr nouveau materiel  par mois",
							 "année-mois",
							 "nV Materiel",
							 dataset,
							 true,
							 true,
							 false);
					
					 XYPlot plot = chart.getXYPlot();
					 DateAxis axis = (DateAxis) plot.getDomainAxis();
					 axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));
					 
					return chart;
					
				}
				// chart l'etat des materiels 
				public JFreeChart etatMat() throws ParseException {
					Map<String,Integer> data = statiqController.getEtatMat();
					 DefaultPieDataset pieDataset = new DefaultPieDataset(); 

					for(String etat :data.keySet()) {
						pieDataset.setValue(etat, data.get(etat));
					}


					 
					 JFreeChart chart = ChartFactory.createPieChart(
							 "Etat Materiel",
							 pieDataset,
							 true,
							 true,
							 false);
					
					 PiePlot plot = (PiePlot) chart.getPlot();
					 plot.setSectionPaint("bonne etat", Color.GREEN);
					 plot.setSectionPaint("endommagé", Color.RED);
					 plot.setSectionPaint("en panne", Color.BLUE);
					return chart;
					
				}
	// enregistrer les chart dans pdf
	public  void writeChartToPDF(List<JFreeChart> charts, String fileName) throws ParseException {
		PdfWriter writer = null;

		
		
		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(
					fileName));
			document.open();
			
			PdfContentByte contentByte = writer.getDirectContent();
			
			float widthh = PageSize.A4.getWidth();
	        float heightt = PageSize.A4.getHeight() / 2;
	        // Pie chart
	        int i = 0;
	        for(JFreeChart element : charts) {
	        	PdfTemplate pdfTemplate = contentByte.createTemplate(widthh, heightt);
	        	Graphics2D graphics2d = pdfTemplate.createGraphics(widthh, heightt,
						new DefaultFontMapper());
	        	Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, widthh, heightt);
	        	element.draw(graphics2d, rectangle2d);
	        	graphics2d.dispose();
	        	if(i%2==0)
	        	contentByte.addTemplate(pdfTemplate, 0, heightt);
	        	else {
	        		contentByte.addTemplate(pdfTemplate, 0, 0);
	        		document.newPage();
	        	}
	        	i++;
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

	}
	
	// planifier pour envoi Mail la fin du mois
	@Scheduled(cron= " 0 0 8 1 * ?")
	public void sendMailMontly() throws ParseException
    {
		    Date date = new Date();
		    date.setMonth(date.getMonth()-1);
		    JFreeChart  chart =  geneChart.getempruntR();
		    JFreeChart chart3 = geneChart.nbrClientAjoutByMonth();
			JFreeChart chart4 = geneChart.nbrEmpruntByClient();
			JFreeChart chart5 = geneChart.nvEmpByMonth();
			JFreeChart chart6 = geneChart.nvMatByMonth();
			JFreeChart chart7 = geneChart.etatMat();
			SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy-dd");
			String s = formatter.format(date);
			date = formatter.parse(s);
			List<JFreeChart> charts = Arrays.asList(chart,chart3,chart4,chart5,chart6,chart7);
			String chemin = "./src/main/resources/static/archiveStatiq/statiq-"+s.substring(3)+".pdf";
			geneChart.writeChartToPDF(charts, chemin);
			String msg = "Bonjour <br> vous trouverez ci-joint les statistiques du mois ";
			emailService.sendMessageWithAttachment("choukri.akram@gmail.com", "statistique", msg,chemin);
    }
}
