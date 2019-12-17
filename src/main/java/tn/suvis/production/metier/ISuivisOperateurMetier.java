package tn.suvis.production.metier;

import java.util.List;
import java.util.Locale;

import tn.suvis.production.entities.Users;

public interface ISuivisOperateurMetier{
	public Users findUserByName(String name);
	public List<Locale> listPays();
}
