package tn.suvis.production.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
@SuppressWarnings("unchecked")
public class SuivisDaoImpl implements ISuivisDao {
	@PersistenceContext
	EntityManager em;
	Cryptage cryptage = new Cryptage();

	@Override
	public void editUser(Users users) {

		// users.setPassword(cryptage.crypter(users.getPassword()));
		em.merge(users);
	}

	@Override
	public Users findUser(Long codeUser) {

		Users u = em.find(Users.class, codeUser);
		if (u == null)
			throw new RuntimeException("Utilisateur introuvable");
		return u;
	}

	@Override
	public Users addUser(Users users) {

		users.setPassword(cryptage.crypter(users.getPassword()));
		em.persist(users);
		return users;
	}

	@Override
	public void deleteUser(Long codeUser) {

		em.remove(findUser(codeUser));

	}

	@Override
	public Role findRole(Long code) {

		Role r = em.find(Role.class, code);
		if (r == null)
			throw new RuntimeException("Le role n'existe pas");
		return r;
	}

	@Override
	public List<Users> findAllUser() {
		Query req = em.createQuery("select u from Users u order by u.idUser desc");
		return req.getResultList();
	}

	@Override
	public List<Role> findRoleByUser(Long codeUser) {

		Query req = em.createQuery("select r from Role r join r.users u where u.idUser=:x ");
		req.setParameter("x", codeUser);
		return req.getResultList();
	}

	@Override
	public Users findUserByName(String nom) {
		Query req = em.createQuery("select u from Users u where u.userName=:x");
		req.setParameter("x", nom);
		return (Users) req.getSingleResult();
	}

	@Override
	public List<Locale> listPays() {
		List<Locale> pays = new ArrayList<Locale>();
		String[] locales = Locale.getISOCountries();
		for (String codePays : locales) {
			Locale locale = new Locale("", codePays);
			pays.add(locale);
		}
		return pays;
	}

	@Override
	public Role findRoleByName(String name) {
		Query req = em.createQuery("select r from Role r where r.roleName=:x");
		req.setParameter("x", name);
		return (Role) req.getSingleResult();
	}

	@Override
	public List<Users> findUserByRole(String roleName, Long authID) {
		Query req = em.createQuery("select distinct u from Users u join u.roles r where r.roleName=:x and u.idUser!=:y");
		req.setParameter("x", roleName);
		req.setParameter("y", authID);
		return req.getResultList();
	}

	@Override
	public Message addMessage(Message message) {

		em.persist(message);
		return message;
	}

	@Override
	public List<Message> findSentMessageByUser(Long idUser) {
		Query req = em.createQuery("select m from Message m where m.emetteur.idUser=:x order by m.date desc");
		req.setParameter("x", idUser);
		return req.getResultList();
	}

	@Override
	public List<Message> findReceiveMessageByUser(Long idUser) {
		Query req = em
				.createQuery("SELECT m From Message m join m.recepteurs u where u.idUser=:x order by m.date desc");
		// Query req =em.createQuery("select * from message m join user_recept u where
		// u.recepteurs_user_id=:x ORDER BY m.date DESC");
		req.setParameter("x", idUser);
		return req.getResultList();
	}

	@Override
	public Long countSentMessageByUser(Long idUser) {
		Query req = em.createQuery("select count(*) from Message m where m.emetteur.idUser=:x");
		req.setParameter("x", idUser);
		return (Long) req.getSingleResult();
	}

	@Override
	public Long countReceiveMessageByUser(Long idUser) {
		Query req = em.createQuery("select count(*) from Message m join m.recepteurs u where u.idUser =:x");
		req.setParameter("x", idUser);
		return (Long) req.getSingleResult();
	}

	@Override
	public Long countNonReadMessageByUser(Long idUser) {
		Query req = em.createNativeQuery("select COUNT(*)"
				+ " from message m ,user_recept ur,users u "
				+ "where u.user_id=:x and m.id=ur.messageRecu_id and "
				+ "u.user_id=recepteurs_user_id and (select count(*) "
				+ "from user_vu_smg uv"
				+ " where uv.messageVu_id=m.id and uv.estVuPar_user_id=:x)=0");
		
		req.setParameter("x", idUser);
		return new Long(req.getSingleResult().toString());
	}

	@Override
	public Users findUserByMessage(Long id) {
		Query req = em.createQuery("select u from Users u join u.messageEmit me where me.id=:x");
		req.setParameter("x", id);
		return (Users) req.getSingleResult();
	}

	@Override
	public List<Users> findUserViewMessage(Long id) {
		Query req = em.createQuery("select u from Users u join u.messageVu mv where mv.id=:x");
		req.setParameter("x", id);
		return req.getResultList();
	}

	@Override
	public Message findMessageById(Long id) {
		Message m = em.find(Message.class, id);
		if (m == null)
			throw new RuntimeException("Le role n'existe pas");
		return m;
	}

	@Override
	public Message editMessage(Message m) {
		em.merge(m);
		return m;
	}

	@Override
	public Alert addAlert(Alert alert) {
		em.persist(alert);
		return alert;
	}

	@Override
	public void editAlert(Alert alert) {
		em.merge(alert);
	}

	@Override
	public void deleteAlert(Long id) {
		Alert alert = em.find(Alert.class, id);
		em.remove(alert);
	}

	@Override
	public Alert findAlertById(Long id) {
		Alert alert = em.find(Alert.class, id);
		if (alert == null)
			throw new RuntimeException("L'alert " + id + " est introuvable");
		return alert;
	}

	@Override
	public List<Alert> findAllAlert() {
		Query query = em.createQuery("select a from Alert a");
		return query.getResultList();
	}

	@Override
	public Mesure addMesure(Mesure mesure) {
		em.persist(mesure);
		return mesure;
	}

	@Override
	public Mesure editMesure(Mesure mesure) {
		em.merge(mesure);
		return null;
	}

	@Override
	public ConfigurationApplication addConfiguration(ConfigurationApplication configurationApplication) {
		em.persist(configurationApplication);
		return configurationApplication;
	}

	@Override
	public List<ConfigurationApplication> findAllConfiguration() {
		Query req = em.createQuery("select c from ConfigurationApplication c");
		return req.getResultList();
	}

	@Override
	public ConfigurationApplication findLastConfiguration() {
		Query req = em.createQuery("select c from ConfigurationApplication c order by c.id desc");
		req.setMaxResults(1);
		return (ConfigurationApplication) req.getSingleResult();
	}

	@Override
	public Segment addSegment(Segment s) {
		em.persist(s);
		return s;
	}

	@Override
	public void editSegment(Segment s) {
		em.merge(s);
	}

	@Override
	public void deleteSegment(Long id) {
		Segment s = em.find(Segment.class, id);
		em.remove(s);
	}

	@Override
	public Segment findSegmentById(Long id) {
		Segment s = em.find(Segment.class, id);
		return s;
	}

	@Override
	public List<Segment> findAllSegment() {
		Query req = em.createQuery("select s from Segment s");
		return req.getResultList();
	}

	@Override
	public PlantSection addPlentSection(PlantSection plantSection) {
		em.persist(plantSection);
		return plantSection;
	}

	@Override
	public void editPlanSection(PlantSection section) {
		em.merge(section);
	}

	@Override
	public void deletePlentSection(Long id) {
		PlantSection plantSection = em.find(PlantSection.class, id);
		em.remove(plantSection);
	}

	@Override
	public PlantSection findPlentSectionById(Long id) {
		PlantSection plantSection = em.find(PlantSection.class, id);
		if (plantSection == null)
			throw new RuntimeException("plent section introuvable");
		return plantSection;
	}

	@Override
	public List<PlantSection> findAllPlentSection() {
		Query req = em.createQuery("select p from PlantSection p order by p.id desc");
		return req.getResultList();
	}

	@Override
	public Post addPost(Post post) {
		em.persist(post);
		return post;
	}

	@Override
	public void editPost(Post post) {
		em.merge(post);
	}

	@Override
	public List<Post> findAllPost() {
		Query req = em.createQuery("select p from Post p");
		return req.getResultList();
	}

	@Override
	public Chaine addChaine(Chaine chaine) {
		em.persist(chaine);
		return chaine;
	}

	@Override
	public void editChaine(Chaine chaine) {
		em.merge(chaine);
	}

	@Override
	public Chaine findChaineById(Long id) {
		Chaine ch = em.find(Chaine.class, id);
		if (ch == null)
			throw new RuntimeException("chaine introuvable");
		return ch;
	}

	@Override
	public List<Mesure> findAllMesure() {
		Query req = em.createQuery("select m from Mesure m");
		return req.getResultList();
	}

	@Override
	public void deleteChaine(Long id) {
		Chaine ch = em.find(Chaine.class, id);
		em.remove(ch);
	}

	@Override
	public List<Users> findUserByRole(String roleName) {
Query req=em.createQuery("select distinct r.users from Role r where r.roleName =:x");
req.setParameter("x", roleName);
		return req.getResultList();
	}

	@Override
	public List<Chaine> findAllChaine() {
		Query req=em.createQuery("select c from Chaine c");
				return req.getResultList();
			}

	

	@Override
	public List<Object> findProductionOfMonth(Date mois) {
		Query req=em.createNativeQuery("SELECT  DATE(p.date) as jour , SUM(p.qteProduite) as qteProduite FROM production p "
				+ "where MONTH(p.date)=MONTH(:x) and  YEAR(p.date)=YEAR(:x)" + 
				"GROUP BY DAY(p.date)");
		req.setParameter("x", mois);
	
		return req.getResultList();
	}

	@Override
	public List<Object> findProductionBySegmentByMonth(Date mois){
		Query req=em.createNativeQuery("SELECT s.nom , SUM(p.qteProduite) as qteProduite FROM production p, segment s \r\n" + 
				"	where MONTH(p.date)=MONTH(:x) and  YEAR(p.date)=YEAR(:x) and p.idSegment=s.idSegment"
				+ " group by s.nom");
		req.setParameter("x", mois);
		return req.getResultList();

	}
	@Override
	public List<Object> loadMonthProductionOnSegment(Date month,Long idSegment){
		Query req=em.createNativeQuery("SELECT  DATE(p.date) , SUM(p.qteProduite) as qteProduite FROM production p "
				+ "where MONTH(p.date)=MONTH(:x) and  YEAR(p.date)=YEAR(:x) and p.idSegment =:y"+ 
				" GROUP BY DAY(p.date)");
		req.setParameter("x", month);
		req.setParameter("y", idSegment);
		return req.getResultList();	
	}
	@Override
	public List<Object> loadProductionOnAllSegementByDay(Date day){
		Query req=em.createNativeQuery("SELECT  p.post, sum(p.qteProduite)"
				+ " FROM production p "
				+ "where date(p.date)=date(:x)"+ 
				" GROUP BY p.post");
		req.setParameter("x", day);
		return req.getResultList();	
	}
	@Override
	public List<Object> loadProductionOnAllSegementBySegmentByDay(Date day,Long idSegment){
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  distinct s.nom, SUM(p.qteProduite) as qteProduite FROM production p, segment s "
					+ "where date(p.date)=date(:x) and p.idSegment=s.idSegment"+ 
					" GROUP BY s.nom");
			req.setParameter("x", day);
			return req.getResultList();	
		}else {
			Query req=em.createNativeQuery("SELECT  distinct c.nom, SUM(p.qteProduite) as qteProduite FROM production p, chaine c "
					+ "where date(p.date)=date(:x) and p.idSegment=c.idChaine and p.idSegment=:y"+ 
					" GROUP BY c.nom");
			req.setParameter("x", day);
			req.setParameter("y", idSegment);
			return req.getResultList();	
		}
		
	}
	@Override
	public List<Object> loadProductionOnSegmentByDay(Date day,Long idSegment){
		Query req=em.createNativeQuery("SELECT  p.post  , SUM(p.qteProduite) as qteProduite FROM production p "
				+ "where date(p.date)=date(:x) and p.idSegment =:y"+ 
				" GROUP BY p.post");
		req.setParameter("x", day);
		req.setParameter("y", idSegment);

		return req.getResultList();	
	}
	@Override
	public List<Object> loadProductionOnAllSegmentByYear(Date year,Long idSegment){
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  date(p.date) , SUM(p.qteProduite) as qteProduite FROM production p "
					+ "where Year(p.date)=year(:x)"+ 
					" GROUP BY Month(p.date)");
			req.setParameter("x", year);
			return req.getResultList();
		}else {
			Query req=em.createNativeQuery("SELECT  date(p.date) , SUM(p.qteProduite) as qteProduite FROM production p "
					+ "where Year(p.date)=year(:x) and p.idSegment=:y"+ 
					" GROUP BY Month(p.date)");
			req.setParameter("x", year);
			req.setParameter("y", idSegment);
			return req.getResultList();
		}
		
	}
	
	@Override
	public List<Object> loadProductionYearBySegment(Date year,Long idSegment){
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  distinct s.nom, SUM(p.qteProduite) as qteProduite FROM production p, segment s "
					+ "where year(p.date)=year(:x) and p.idSegment=s.idSegment"+ 
					" GROUP BY s.nom");
			req.setParameter("x", year);
			return req.getResultList();	
		}else {
			Query req=em.createNativeQuery("SELECT  distinct c.nom, SUM(p.qteProduite) as qteProduite FROM production p, chaine c "
					+ "where year(p.date)=year(:x)and p.idSegment=:y and p.idChaine=c.idChaine"+ 
					" GROUP BY c.nom");
			req.setParameter("x", year);
			req.setParameter("y", idSegment);
			return req.getResultList();	
		}
		
	}
	@Override
	public List<Object> loadProductionOnSegmentByYear(Date year,Long idSegment){
		Query req=em.createNativeQuery("SELECT  Month(m.date) , SUM(m.qteProduite) as qteProduite FROM mesure m "
				+ "where Year(m.date)=year(:x) and m.idSegment =:y"+ 
				" GROUP BY Month(m.date)");
		req.setParameter("x", year);
		req.setParameter("y", idSegment);
		return req.getResultList();
	}
	
	@Override
	public List<Chaine> loadChaineOnSegment(Long idSegment) {
		Query req=em.createQuery("select c from Chaine c where "
				+ "c.segment.idSegment =:x");
		req.setParameter("x", idSegment);
		return req.getResultList();
	}

	@Override
	public List<Object> loadProductionOnAllSegmentByPeriode(Date begin, Date end,Long idSegment) {
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  date(p.date) , SUM(p.qteProduite) as qteProduite FROM production p "
					+ "where date(p.date) between :x and :y"+ 
					" GROUP BY date(p.date)");
			req.setParameter("x", begin);
			req.setParameter("y", end);
			return req.getResultList();
		}else {
			Query req=em.createNativeQuery("SELECT  date(p.date) , SUM(p.qteProduite) as qteProduite FROM production p "
					+ "where p.idSegment=:z and date(p.date) between :x and :y"+ 
					" GROUP BY date(p.date)");
			req.setParameter("x", begin);
			req.setParameter("y", end);
			req.setParameter("z", idSegment);
			return req.getResultList();
		}
		
	}
	@Override
	public List<Object> loadProductionOnAllSegmentByPeriodeBySegment(Date begin, Date end) {
		Query req=em.createNativeQuery("SELECT  s.nom , SUM(p.qteProduite) as qteProduite FROM production p, segment s "
				+ "where date(p.date) between :x and :y and s.idSegment=p.idSegment"+ 
				" GROUP BY s.nom");
		req.setParameter("x", begin);
		req.setParameter("y", end);
		return req.getResultList();
	}

	@Override
	public List<Object> loadProductionByPeriode(Date begin, Date end, Long idSegment) {
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT s.nom , SUM(p.qteProduite) as qteProduite FROM production p ,segment s "
					+ "where date(p.date) between :x and :y and p.idSegment =s.idSegment"+ 
					" GROUP BY s.nom");
			req.setParameter("x", begin);
			req.setParameter("y", end);
			return req.getResultList();
		}else {
			Query req=em.createNativeQuery("SELECT c.nom , SUM(p.qteProduite) as qteProduite FROM production p ,chaine c "
					+ "where date(p.date) between :x and :y and p.idSegment =:z and p.idChaine=c.idChaine"+ 
					" GROUP BY c.nom");
			req.setParameter("x", begin);
			req.setParameter("y", end);
			req.setParameter("z", idSegment);
			return req.getResultList();
		}
		
	}

	@Override
	public List<Object> loadProductionByChaineOnSegmentAtDay(Date day, Long idSegment) {
		Query req=em.createNativeQuery("SELECT  c.nom , SUM(p.qteProduite) as qteProduite FROM production p,chaine c "
				+ "where date(p.date)=date(:x) and p.idSegment =:y and p.idChaine=c.idChaine"+ 
				" GROUP BY c.nom");
		req.setParameter("x", day);
		req.setParameter("y", idSegment);
		return req.getResultList();
	}

	@Override
	public List<Object> loadMonthProductionOnSegmentGroupByChaine(Date month, Long idSegment) {
		
		Query req=em.createNativeQuery("SELECT  c.nom , SUM(p.qteProduite) as qteProduite FROM production p,chaine c "
				+ "where Month(p.date)=Month(:x) and p.idSegment =:y and p.idChaine=c.idChaine"+ 
				" GROUP BY c.nom");
		req.setParameter("x", month);
		req.setParameter("y", idSegment);
		return req.getResultList();	}

	@Override
	public List<Object> loadArretMonth(Date month, Long idSegment) {
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  a.debut, count(a.id) from arret a "
					+ "where Month(a.debut)=Month(:x) and Year(a.debut)=Year(:x) group by day(a.debut)"
					);
			req.setParameter("x", month);
			return req.getResultList();
		}else {
			Query req=em.createNativeQuery("select a.debut, count(a.id) from arret a"
					+ " where a.idSegment=:y and Month(a.debut)=Month(:x) and Year(a.debut)=Year(:x) group by day(a.debut)");
			req.setParameter("x", month);
			req.setParameter("y", idSegment);
			return req.getResultList();
		}
		
	}
	
	@Override
	public List<Object> loadArretDay(Date date, Long idSegment) {
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  a.debut, count(a.id) from arret a "
					+ "where date(a.debut)=date(:x) group by date(a.debut)"
					);
			req.setParameter("x", date);
			return req.getResultList();
		}else {
			Query req=em.createNativeQuery("select a.debut, count(a.id) from arret a"
					+ " where a.idSegment=:y and date(a.debut)=date(:x) group by date(a.debut)");
			req.setParameter("x", date);
			req.setParameter("y", idSegment);
			return req.getResultList();
		}
		
	}
	
	@Override
	public List<Object> loadArretYear(Date year, Long idSegment) {
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  a.debut, count(a.id) from arret a "
					+ "where Year(a.debut)=Year(:x) group by Month(a.debut)"
					);
			req.setParameter("x", year);
			return req.getResultList();
		}else {
			Query req=em.createNativeQuery("select a.debut, count(a.id) from arret a"
					+ " where a.idSegment=:y and Year(a.debut)=Year(:x) group by Month(a.debut)");
			req.setParameter("x", year);
			req.setParameter("y", idSegment);
			return req.getResultList();
		}
		
	}
	
	@Override
	public List<Object> loadArretPeriode(Date begin,Date end, Long idSegment) {
		if(idSegment==null) {
			Query req=em.createNativeQuery("SELECT  a.debut, count(a.id) from arret a "
					+ "where date(a.debut) between date(:x) and date(:y) group by day(a.debut) order by date(a.debut) asc"
					);
			req.setParameter("x", begin);
			req.setParameter("y", end);
			return req.getResultList();
		}else {
			Query req=em.createNativeQuery("select a.debut, count(a.id) from arret a"
					+ " where a.idSegment=:z and date(a.debut) between date(:x) and date(:y) group by Month(a.debut)");
			req.setParameter("x", begin);
			req.setParameter("y", end);
			req.setParameter("z", idSegment);
			return req.getResultList();
		}
		
	}

	@Override
	public String totalSegment() {
		Query req=em.createQuery("select count(*) from Segment");
		Long result=(Long) req.getSingleResult();
		return result.toString();
	}

	@Override
	public String totalChaine() {
		Query req=em.createQuery("select count(*) from Chaine");
		Long result=(Long) req.getSingleResult();
		return result.toString();	}

	@Override
	public ContreGroupe addContreGroupe(ContreGroupe groupe) {
		em.persist(groupe);
		return groupe;
	}

	@Override
	public void editContreGroupe(ContreGroupe groupe) {
			em.merge(groupe);
	}

	@Override
	public List<ContreGroupe> findAllContreGroupe() {
		Query req=em.createQuery("select g from ContreGroupe g");
		return req.getResultList();
	}

	@Override
	public MMRT addMMRT(MMRT mmrt) {
		em.persist(mmrt);
		return mmrt;
	}

	@Override
	public void editMMRT(MMRT mmrt) {
em.merge(mmrt);		
	}

	@Override
	public List<MMRT> findAllMMRT() {
Query req=em.createQuery("select m from MMRT m");
		return req.getResultList();
	}

	@Override
	public ContreGroupe findContreGroupeById(Long id) {
		ContreGroupe groupe=em.find(ContreGroupe.class, id);
		return groupe;
	}
}
