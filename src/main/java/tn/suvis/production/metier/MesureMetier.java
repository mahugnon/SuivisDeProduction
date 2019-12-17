package tn.suvis.production.metier;

import java.util.Date;

import org.springframework.integration.annotation.Payload;

import tn.suvis.production.entities.Arret;
import tn.suvis.production.entities.ConfigurationApplication;
import tn.suvis.production.entities.Mesure;
import tn.suvis.production.entities.Post;
import tn.suvis.production.entities.Production;
import tn.suvis.production.entities.Users;
@SuppressWarnings("deprecation")
public interface MesureMetier {
public Mesure createMesure(Mesure mesure);
public Users findPlentSection(Long idSegment);

public Users findControleMaitre(Post post);
public Users findChefSegment(Long idSegment);
public Users findChefChaine(Long idChaine);
@Payload("new java.util.Date()")
public ConfigurationApplication findLastConfig();
@Payload("#args")
public Arret findArret(Long idChaine,Long idSegment,Date day) throws Exception;
public Arret createArret(Arret arret);
public Arret updateArret(Arret arret);
public Production createProduction(Production p);
@Payload("#args")
public Production findProduction(Date date, Long idSegment,Long idChaine,String post);
public void updateProduction(Production p);

}
