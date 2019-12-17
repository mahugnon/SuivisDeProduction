package tn.suvis.production.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import tn.suvis.production.entities.Mesure;

public class MesureTransformer {
public Mesure transform(String input) {
	String data[]=input.split(",");
	Mesure m=new Mesure();
	m.setIdSegment(Long.parseLong(data[0]));
	m.setPost(data[1]);
	m.setIdChaine(Long.parseLong(data[2]));
	m.setEtatArret(Integer.parseInt(data[3]));
	m.settArret(Integer.parseInt(data[4]));
	m.setQteProduite(Double.parseDouble(data[5]));
	m.setType(data[6].split(";")[0]);
	/*Date now = new Date();
	String date=new SimpleDateFormat("dd/MM/yyyy").format(now);*/
	m.setDate(new Date());
	return m;
}
}
