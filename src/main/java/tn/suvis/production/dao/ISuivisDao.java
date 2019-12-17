package tn.suvis.production.dao;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import tn.suvis.production.entities.Alert;
import tn.suvis.production.entities.Chaine;
import tn.suvis.production.entities.ConfigurationApplication;
import tn.suvis.production.entities.ContreGroupe;
import tn.suvis.production.entities.MMRT;
import tn.suvis.production.entities.Message;
import tn.suvis.production.entities.Mesure;
import tn.suvis.production.entities.PlantSection;
import tn.suvis.production.entities.Post;
import tn.suvis.production.entities.Role;
import tn.suvis.production.entities.Segment;
import tn.suvis.production.entities.Users;

public interface ISuivisDao {
	public Users addUser(Users users);

	public void deleteUser(Long codeUser);

	public void editUser(Users users);

	public Users findUser(Long codeUser);

	public List<Users> findAllUser();

	public Role findRole(Long code);

	public List<Role> findRoleByUser(Long codeUser);

	public Users findUserByName(String nom);

	public Role findRoleByName(String name);

	public List<Locale> listPays();

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

	public Alert addAlert(Alert alert);

	public void editAlert(Alert alert);

	public void deleteAlert(Long id);

	public Alert findAlertById(Long id);

	public List<Alert> findAllAlert();

	public Mesure addMesure(Mesure mesure);

	public Mesure editMesure(Mesure mesure);

	public ConfigurationApplication addConfiguration(ConfigurationApplication configurationApplication);

	public List<ConfigurationApplication> findAllConfiguration();

	public ConfigurationApplication findLastConfiguration();

	public Segment addSegment(Segment s);

	public void editSegment(Segment s);

	public void deleteSegment(Long id);

	public Segment findSegmentById(Long id);

	public List<Segment> findAllSegment();

	public PlantSection addPlentSection(PlantSection plantSection);

	public void editPlanSection(PlantSection section);

	public void deletePlentSection(Long id);

	public PlantSection findPlentSectionById(Long id);

	public List<PlantSection> findAllPlentSection();

	public MMRT addMMRT(MMRT mmrt);

	public void editMMRT(MMRT mmrt);

	public List<MMRT> findAllMMRT();

	public ContreGroupe addContreGroupe(ContreGroupe groupe);

	public void editContreGroupe(ContreGroupe groupe);

	public List<ContreGroupe> findAllContreGroupe();
	public ContreGroupe findContreGroupeById(Long id);
	public Post addPost(Post post);

	public void editPost(Post post);

	public List<Post> findAllPost();

	public Chaine addChaine(Chaine chaine);

	public void editChaine(Chaine chaine);

	public Chaine findChaineById(Long id);

	public List<Mesure> findAllMesure();

	public void deleteChaine(Long id);

	public List<Users> findUserByRole(String roleName);

	public List<Chaine> findAllChaine();

	public List<Object> findProductionOfMonth(Date mois);

	public List<Object> findProductionBySegmentByMonth(Date mois);

	public List<Object> loadMonthProductionOnSegment(Date month, Long idSegment);

	public List<Object> loadMonthProductionOnSegmentGroupByChaine(Date month, Long idSegment);

	public List<Object> loadProductionOnAllSegementByDay(Date day);

	public List<Object> loadProductionOnSegmentByDay(Date day, Long idSegment);

	public List<Object> loadProductionOnAllSegmentByYear(Date year, Long idSegment);

	public List<Object> loadProductionOnSegmentByYear(Date year, Long idSegment);

	public List<Chaine> loadChaineOnSegment(Long idSegment);

	public List<Object> loadProductionOnAllSegmentByPeriode(Date begin, Date end, Long idSegment);

	public List<Object> loadProductionByPeriode(Date begin, Date end, Long idSegment);

	public List<Object> loadProductionOnAllSegementBySegmentByDay(Date day, Long idSegment);

	public List<Object> loadProductionOnAllSegmentByPeriodeBySegment(Date begin, Date end);

	public List<Object> loadProductionByChaineOnSegmentAtDay(Date day, Long idSegment);

	public List<Object> loadProductionYearBySegment(Date year, Long idSegment);

	public List<Object> loadArretMonth(Date month, Long idSegment);

	public List<Object> loadArretDay(Date date, Long idSegment);

	public List<Object> loadArretYear(Date year, Long idSegment);

	public List<Object> loadArretPeriode(Date begin, Date end, Long idSegment);

	public String totalSegment();

	public String totalChaine();

}
