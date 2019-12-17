package tn.suvis.production.metier;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.suvis.production.dao.ISuivisDao;
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

@Transactional
@Service
public class SuivisMetierImpl implements ISuivisSuperAdminMetier {
	private static final Logger LOG=LoggerFactory.getLogger(SuivisMetierImpl.class); 
	private ISuivisDao dao;
	@Autowired
	public JavaMailSenderImpl emailSender;

	public void setDao(ISuivisDao dao) {
		this.dao = dao;
	}

	@Override
	public void editUser(Users users) {
		dao.editUser(users);
	}

	@Override
	public Users findUser(Long codeUser) {
		Users u = dao.findUser(codeUser);
		List<Role> roles = findRoleByUser(u.getIdUser());
		u.setRoles(roles);
		return u;
	}

	@Override
	public Users addUser(Users user) {
		String random = UUID.randomUUID().toString();
		random = random.substring(30);

		user.setUserName(user.getFirstName() + "." + user.getLastName());
		user.setPassword(random);
		System.out.println(user.getPassword());
		String mdp = user.getPassword();
		user.setActived(true);
		user.setFirsTime(true);
		dao.addUser(user);

		
		/*  sendSimpleMessage(user.getAdresseMail(), "Bienvenue  Kuso Connect",
		  "Votre mot de passe est : " + mdp + "\tVotre nom d'utilisateur est : " +
		  user.getUserName() + ".\n VEILLEZ CHANGER AVANT D'AVOIR ACCES AU SYSTEME");*/
		 
		return user;
	}

	@Override
	public void deleteUser(Long codeUser) {
		dao.deleteUser(codeUser);
	}

	@Override
	public Role findRole(Long codeRole) {
		return dao.findRole(codeRole);
	}

	@Override
	public List<Users> findAllUser() {
		List<Users> us = dao.findAllUser();
		for (Users u : us) {
			u.setRoles(findRoleByUser(u.getIdUser()));
		}
		return us;
	}

	@Override
	public Users findUserByName(String name) {
		Users u = dao.findUserByName(name);
		List<Role> roles = findRoleByUser(u.getIdUser());
		u.setRoles(roles);
		return u;
	}

	@Override
	public List<Locale> listPays() {
		return dao.listPays();
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	@Override
	public List<Role> findRoleByUser(Long id) {
		return dao.findRoleByUser(id);
	}

	@Override
	public List<Users> findUserByRole(String roleName, Long authID) {

		return dao.findUserByRole(roleName, authID);
	}

	@Override
	public Message addMessage(Message message) {

		return dao.addMessage(message);
	}

	@Override
	public List<Message> findSentMessageByUser(Long idUser) {
		return dao.findSentMessageByUser(idUser);
	}

	@Override
	public List<Message> findReceiveMessageByUser(Long idUser) {
		List<Message> messages = dao.findReceiveMessageByUser(idUser);
		for (Message m : messages) {
			m.setEmetteur(findUserByMessage(m.getId()));
		}
		return messages;
	}

	@Override
	public Long countSentMessageByUser(Long idUser) {
		return dao.countSentMessageByUser(idUser);
	}

	@Override
	public Long countReceiveMessageByUser(Long idUser) {

		return dao.countReceiveMessageByUser(idUser);
	}

	@Override
	public Long countNonReadMessageByUser(Long idUser) {
		return dao.countNonReadMessageByUser(idUser);
	}

	@Override
	public Users findUserByMessage(Long id) {
		Users u = dao.findUserByMessage(id);
		List<Role> roles = findRoleByUser(u.getIdUser());
		u.setRoles(roles);
		return u;
	}

	@Override
	public List<Users> findUserViewMessage(Long id) {

		return dao.findUserViewMessage(id);
	}

	@Override
	public Message findMessageById(Long id) {

		return dao.findMessageById(id);
	}

	@Override
	public Message editMessage(Message m) {
		return dao.editMessage(m);
	}

	@Override
	public void sendMailUser( String to, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		emailSender.setUsername("honorhouekpe@gmail.com");
		emailSender.setPassword("HmhB6413");
		message.setTo(to);
		message.setText(content);
		
		emailSender.send(message);
	}

	@Override
	public Mesure addMesure(Mesure mesure) {
		System.out.println("hello je m'execute");
		return dao.addMesure(mesure);
	}

	@Override
	public void editMesure(Mesure mesure) {
		dao.editMesure(mesure);
	}

	@Override
	public ConfigurationApplication addConfiguration(ConfigurationApplication configurationApplication) {
		return dao.addConfiguration(configurationApplication);
	}

	@Override
	public List<ConfigurationApplication> findAllConfiguration() {
		return dao.findAllConfiguration();
	}

	@Override
	public ConfigurationApplication findLastConfiguration() {
		return dao.findLastConfiguration();
	}

	@Override
	public Segment addSegment(Segment s) {

		return dao.addSegment(s);
	}

	@Override
	public void editSegment(Segment s) {
		dao.editSegment(s);
	}

	@Override
	public void deleteSegment(Long id) {
		dao.deleteSegment(id);
	}

	@Override
	public Segment findSegmentById(Long id) {
		Segment segment= dao.findSegmentById(id);
		if (segment.getBmrt() != null) {
			segment.setBmrt(findUser(segment.getBmrt().getIdUser()));

		}
		return segment;
	}

	@Override
	public List<Segment> findAllSegment() {
		List<Segment> segments = dao.findAllSegment();
		
		return segments;
	}

	@Override
	public PlantSection addPlentSection(PlantSection plantSection) {
		return dao.addPlentSection(plantSection);
	}

	@Override
	public void editPlanSection(PlantSection section) {
		dao.editPlanSection(section);
	}

	@Override
	public void deletePlentSection(Long id) {
		dao.deletePlentSection(id);
	}

	@Override
	public PlantSection findPlentSectionById(Long id) {
		PlantSection p= dao.findPlentSectionById(id);
		
		return p;
	}

	@Override
	public List<PlantSection> findAllPlentSection() {
		List<PlantSection> plantSections = dao.findAllPlentSection();
		
		return plantSections;
	}

	@Override
	public Post addPost(Post post) {
		return dao.addPost(post);
	}

	@Override
	public void editPost(Post post) {
		dao.editPost(post);
	}

	@Override
	public List<Post> findAllPost() {
		return dao.findAllPost();
	}

	@Override
	public Chaine addChaine(Chaine chaine) {
		return dao.addChaine(chaine);
	}

	@Override
	public void editChaine(Chaine chaine) {
		dao.editChaine(chaine);
	}

	@Override
	public Chaine findChaineById(Long id) {
		return dao.findChaineById(id);
	}

	@Override
	public List<Mesure> findAllMesure() {
		return dao.findAllMesure();
	}

	@Override
	public void deleteChaine(Long id) {
		dao.deleteChaine(id);
	}

	@Override
	public List<Users> findUserByRole(String roleName) {
		List<Users> users = dao.findUserByRole(roleName);


		return users;
	}

	@Override
	public List<Chaine> findAllChaine() {
		List<Chaine> chaines=dao.findAllChaine();
		return chaines;
	}


	@Override
	public List<Object> findProductionOfMonth(Date mois) {
		return dao.findProductionOfMonth(mois);
	}

	@Override
	public List<Object> findProductionBySegmentByMonth(Date mois) {
		return dao.findProductionBySegmentByMonth(mois);
	}

	@Override
	public List<Object> loadMonthProductionOnSegment(Date month, Long idSegment) {
		return dao.loadMonthProductionOnSegment(month, idSegment);
	}

	@Override
	public List<Object> loadProductionOnAllSegementByDay(Date day) {
		return dao.loadProductionOnAllSegementByDay(day);
	}

	@Override
	public List<Object> loadProductionOnSegmentByDay(Date day, Long idSegment) {
		
		return dao.loadProductionOnSegmentByDay(day, idSegment);
	}

	@Override
	public List<Object> loadProductionOnAllSegmentByYear(Date year,Long idSegment) {
	
		return dao.loadProductionOnAllSegmentByYear(year,idSegment);
	}

	@Override
	public List<Object> loadProductionOnSegmentByYear(Date year, Long idSegment) {
		
		return dao.loadProductionOnSegmentByYear(year, idSegment);
	}

	@Override
	public List<Chaine> loadChaineOnSegment(Long idSegment) {
		return dao.loadChaineOnSegment(idSegment);
	}

	@Override
	public List<Object> loadProductionOnAllSegmentByPeriode(Date begin, Date end,Long idSegment) {
		return dao.loadProductionOnAllSegmentByPeriode(begin, end,idSegment);
	}

	@Override
	public List<Object> loadProductionByPeriode(Date begin, Date end, Long idSegment) {
		return dao.loadProductionByPeriode(begin, end, idSegment);
	}

	@Override
	public List<Object> loadProductionOnAllSegementBySegmentByDay(Date day,Long idSegment) {
		
		return dao.loadProductionOnAllSegementBySegmentByDay(day,idSegment);
	}

	@Override
	public List<Object> loadProductionByChaineOnSegmentAtDay(Date day, Long idSegment) {
		return dao.loadProductionByChaineOnSegmentAtDay(day, idSegment);
	}

	@Override
	public List<Object> loadMonthProductionOnSegmentGroupByChaine(Date month, Long idSegment) {
		
		return dao.loadMonthProductionOnSegmentGroupByChaine( month,  idSegment);
	}

	@Override
	public List<Object> loadProductionYearBySegment(Date year,Long idSegment) {
		
		return dao.loadProductionYearBySegment(year,idSegment);
	}

	@Override
	public List<Object> loadArretMonth(Date month, Long idSegment) {
		
		return dao.loadArretMonth(month, idSegment);
	}

	@Override
	public List<Object> loadArretDay(Date date, Long idSegment) {
		
		return dao.loadArretDay(date, idSegment);
	}

	@Override
	public List<Object> loadArretYear(Date year, Long idSegment) {
		return dao.loadArretYear(year, idSegment);
	}

	@Override
	public List<Object> loadArretPeriode(Date begin, Date end, Long idSegment) {
		return dao.loadArretPeriode(begin, end, idSegment);
	}

	@Override
	public String totalSegment() {
		
		return dao.totalSegment();
	}

	@Override
	public String totalChaine() {
		return dao.totalChaine();
	}

	@Override
	public ContreGroupe addContreGroupe(ContreGroupe groupe) {
		return dao.addContreGroupe(groupe);
	}

	@Override
	public void editContreGroupe(ContreGroupe groupe) {
dao.editContreGroupe(groupe);		
	}

	@Override
	public List<ContreGroupe> findAllContreGroupe() {
		return dao.findAllContreGroupe();
	}

	@Override
	public MMRT addMMRT(MMRT mmrt) {
		return dao.addMMRT(mmrt);
	}

	@Override
	public void editMMRT(MMRT mmrt) {
		dao.editMMRT(mmrt);
		
	}

	@Override
	public List<MMRT> findAllMMRT() {
		return dao.findAllMMRT();
	}

	@Override
	public ContreGroupe findContreGroupeById(Long id) {
ContreGroupe g=dao.findContreGroupeById(id);
if(g==null) {
	LOG.warn("Contre groupe {} introuvable",id);
	throw new RuntimeException("Contre groupe introuvable");
}
		return g;
	}

}
