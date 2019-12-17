package tn.suvis.production.metier;

import java.util.Date;
import java.util.List;

import tn.suvis.production.entities.Chaine;
import tn.suvis.production.entities.ContreGroupe;
import tn.suvis.production.entities.MMRT;
import tn.suvis.production.entities.Message;
import tn.suvis.production.entities.Mesure;
import tn.suvis.production.entities.Role;
import tn.suvis.production.entities.Users;

public interface ISuivisAdminMetier extends ISuivisOperateurMetier {
	public void editUser(Users users);

	public Users addUser(Users users);

	public void deleteUser(Long codeUser);

	public Users findUser(Long codeUser);

	public Role findRole(Long code);

	public List<Users> findAllUser();

	public List<Role> findRoleByUser(Long id);

	public void sendSimpleMessage(String to, String subject, String text);

	public List<Users> findUserByRole(String roleName, Long authID);

	public Message addMessage(Message message);

	public List<Message> findSentMessageByUser(Long idUser);

	public List<Message> findReceiveMessageByUser(Long idUser);

	public Long countSentMessageByUser(Long idUser);

	public Long countReceiveMessageByUser(Long idUser);

	public Long countNonReadMessageByUser(Long idUser);

	public Users findUserByMessage(Long id);

	public List<Users> findUserViewMessage(Long id);

	public Message findMessageById(Long id);

	public Message editMessage(Message m);

	public void sendMailUser(String to, String content);

	public Mesure addMesure(Mesure mesure);

	public void editMesure(Mesure mesure);

	public List<Users> findUserByRole(String roleName);

	public List<Chaine> findAllChaine();

	public List<Object> findProductionOfMonth(Date mois);

	public List<Object> loadMonthProductionOnSegment(Date month, Long idSegment);

	public List<Object> loadProductionOnAllSegementByDay(Date day);

	public List<Object> loadProductionOnSegmentByDay(Date day, Long idSegment);

	public List<Object> loadProductionOnAllSegmentByYear(Date year, Long idSegment);

	public List<Object> loadProductionOnSegmentByYear(Date year, Long idSegment);

	public List<Chaine> loadChaineOnSegment(Long idSegment);

	public List<Object> loadProductionOnAllSegmentByPeriode(Date begin, Date end, Long idSegment);

	public List<Object> loadProductionByPeriode(Date begin, Date end, Long idSegment);

	public List<Object> loadProductionOnAllSegementBySegmentByDay(Date day, Long idSegment);

	public List<Object> loadMonthProductionOnSegmentGroupByChaine(Date month, Long idSegment);

	public List<Object> loadProductionYearBySegment(Date year, Long idSegment);

	public List<Object> loadArretMonth(Date month, Long idSegment);

	public List<Object> loadArretDay(Date date, Long idSegment);

	public List<Object> loadArretYear(Date year, Long idSegment);

	public List<Object> loadArretPeriode(Date begin, Date end, Long idSegment);

	public String totalSegment();

	public String totalChaine();

	public ContreGroupe addContreGroupe(ContreGroupe groupe);

	public void editContreGroupe(ContreGroupe groupe);

	public ContreGroupe findContreGroupeById(Long id);

	public List<ContreGroupe> findAllContreGroupe();

	public MMRT addMMRT(MMRT mmrt);

	public void editMMRT(MMRT mmrt);

	public List<MMRT> findAllMMRT();
}
