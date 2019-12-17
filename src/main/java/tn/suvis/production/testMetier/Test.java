package tn.suvis.production.testMetier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tn.suvis.production.controllers.TrameController;
import tn.suvis.production.entities.Arret;
import tn.suvis.production.entities.Chaine;
import tn.suvis.production.entities.ConfigurationApplication;
import tn.suvis.production.entities.ContreGroupe;
import tn.suvis.production.entities.MMRT;
import tn.suvis.production.entities.Mesure;
import tn.suvis.production.entities.PlantSection;
import tn.suvis.production.entities.Post;
import tn.suvis.production.entities.Production;
import tn.suvis.production.entities.Role;
import tn.suvis.production.entities.Segment;
import tn.suvis.production.entities.Users;
import tn.suvis.production.metier.ISuivisSuperAdminMetier;
import tn.suvis.production.metier.MesureMetier;

public class Test {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		ISuivisSuperAdminMetier metier = (ISuivisSuperAdminMetier) context.getBean("metier");
		final AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/integration/spring-integration-context.xml");
	/*	for (int i = 40; i < 41; i++) {
			Users u = metier.findUser(new Long(i));
			Role r = metier.findRole(10L);
			u.getRoles().add(r);
			metier.editUser(u);
			c++;
		}*/
		int b=87;
		for (int i = 1; i <97; i++) {
			b=b+1;
			Users chefChaine=metier.findUser(new Long(b));
			Chaine c=metier.findChaineById(new Long(i));
			c.setChefChaine(chefChaine);
			metier.editChaine(c);
		}
		/*
		 * Date dat1 = null; SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat("dd/MM/yyyy");
		 * 
		 * String date1 = "13/11/2017"; String date2 = "08/2017";
		 * 
		 * try { dat1 = simpleDateFormat.parse(date1);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } String a="";
		 * System.out.println(a.length()); List<Object> list= metier.loadArretMonth(new
		 * Date(), null); for (Object quentiteProduite : list) { Object[] obj=(Object[])
		 * quentiteProduite; System.out.println(obj[0]+"     "+obj[1]); }
		 */
		/*
		 * MesureMetier service = ctx.getBean(MesureMetier.class); Production
		 * p=service.findProduction(new Date(), 3L, 2L);
		 * System.out.println(p.toString()+"\n----------------------");
		 * p.setQteProduite(20); service.updateProduction(p);
		 * System.out.println(service.findProduction(new Date(), 2L,
		 * 5L,"PM").toString());
		 */

	}

	/*
	 * public static final void main(String[] args) { TrameController ct=new
	 * TrameController(); System.out.println(ct.compareDate(new Date())); }
	 */
}
