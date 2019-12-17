package tn.suvis.production.metier;

import java.util.Date;
import java.util.List;

import tn.suvis.production.entities.Chaine;
import tn.suvis.production.entities.ConfigurationApplication;
import tn.suvis.production.entities.Mesure;
import tn.suvis.production.entities.PlantSection;
import tn.suvis.production.entities.Post;
import tn.suvis.production.entities.Segment;
import tn.suvis.production.entities.Users;

public interface ISuivisSuperAdminMetier extends ISuivisAdminMetier{
	public Users findUser(Long codeUser);
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
	public Post addPost(Post post);
	public void editPost(Post post);
	public List<Post> findAllPost();
	public Chaine addChaine(Chaine chaine);
	public void editChaine(Chaine chaine);
	public Chaine findChaineById(Long id);
	public List<Mesure> findAllMesure();
	public void deleteChaine(Long id);
	List<Object> findProductionBySegmentByMonth(Date mois);
	public List<Object> loadProductionByChaineOnSegmentAtDay(Date day,Long idSegment);
}
