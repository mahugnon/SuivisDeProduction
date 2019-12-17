package tn.suvis.production.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tn.suvis.production.entities.Arret;
import tn.suvis.production.entities.ConfigurationApplication;
import tn.suvis.production.entities.Mesure;
import tn.suvis.production.entities.Post;
import tn.suvis.production.entities.Production;
import tn.suvis.production.entities.Segment;
import tn.suvis.production.entities.Users;
import tn.suvis.production.metier.MesureMetier;

public class TrameController {
	final static AbstractApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:META-INF/spring/integration/spring-integration-context.xml");

	public void ecouteServeur(Mesure input) throws Exception {
		if ("FAIL".equals(input)) {
			throw new RuntimeException("Failure Demonstration");
		}
		System.out.println("\n----------------\n inbound data: " + input.getPost() + "\n-------------------");

		context.registerShutdownHook();
		final MesureMetier service = context.getBean(MesureMetier.class);
		ConfigurationApplication config = service.findLastConfig();

		System.out.println("info :" + input + "\n" + "config " + config);

		// recuperer le dernier arret enregistrer
		Arret arret = service.findArret(input.getIdChaine(), input.getIdSegment(), new Date());
		System.out.println(arret == null);
		if (input.getEtatArret() == 1) {
			if (arret == null) {
				service.createArret(
						new Arret(new Date(), null, input.getIdSegment(), input.getIdChaine(), "active", ""));
			} else {
				if (arret.getFin() == null) {
					// ancien arret en cours
					int duree = input.gettArret();
					sendNotification(config, input);
				} else {
					// nouvel arret
					service.createArret(
							new Arret(new Date(), null, input.getIdSegment(), input.getIdChaine(), "active", ""));
					int duree = input.gettArret();
					sendNotification(config, input);

				}
			}

		} else {
			// tester si il y a un arret au paravant
			if (arret != null) {
				if (arret.getFin() == null) {

					arret.setFin(new Date());
					service.updateArret(arret);
				}
			}
		}
		miseAjoutProduction(input);
		service.createMesure(input);

	}

	public void sendNotification(ConfigurationApplication config, Mesure m) {

		MesureMetier service = context.getBean(MesureMetier.class);

		if (m.gettArret() == config.getEscale1()) {
			Users u = service.findChefChaine(m.getIdChaine());
			System.out.println("Chef chaine :" + u.getTelephone());
		} else if (m.gettArret() >= config.getEscale2()) {
			Segment s = new Segment();
			s.setIdSegment(m.getIdSegment());
			Post p = new Post();
			p.setSegment(s);
			p.setNom(m.getPost());
			Users u = service.findControleMaitre(p);
			System.out.println("Controle maitre :" + u.getTelephone());
		} else if (m.gettArret() >= config.getEscale3()) {

			Users u = service.findPlentSection(m.getIdSegment());
			System.out.println("Chef plent section " + u.getTelephone());
		} else if (m.gettArret() >= config.getEscale4()) {

		}
	}

	public void miseAjoutProduction(Mesure m) {
		/*-------
		 * Algo
		 * ------
		 * Selectionner la production dont la date et le post est la même que la date de la trame
		 * si exist mettre à jour la quantité produite
		 * sinon enrégistrer une nouvelle production et mettre 
		 * */
		final AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/integration/spring-integration-context.xml");

		MesureMetier service = ctx.getBean(MesureMetier.class);
		Production p = service.findProduction(m.getDate(), m.getIdSegment(), m.getIdChaine(), compareDate(m.getDate()));
		System.out.println(p == null);
		if (p == null) {
			p = new Production(new Date(), m.getIdSegment(), m.getIdChaine(), m.getQteProduite(), compareDate(m.getDate()));
			service.createProduction(p);
		} else {
			p.setQteProduite(m.getQteProduite());
			service.updateProduction(p);
		}
	}

	public String compareDate(Date date) {
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		String datestr = parser.format(date);
		String post = null;
		try {
			date = parser.parse(datestr);
			Date cinqTrente = parser.parse("05:30");
			Date treizeCinquante = parser.parse("13:50");
			Date quatorze = parser.parse("14:00");
			Date vengtDeux = parser.parse("22:15");

			if (date.after(cinqTrente) && date.before(treizeCinquante)) {
				post = "PM";
			}
			if (date.after(quatorze) && date.before(vengtDeux)) {
				post = "PA";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(post);
		return post;
	}

}
