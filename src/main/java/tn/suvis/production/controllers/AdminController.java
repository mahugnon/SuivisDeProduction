package tn.suvis.production.controllers;

import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tn.suvis.production.entities.Arret;
import tn.suvis.production.entities.Chaine;
import tn.suvis.production.entities.ConfigurationApplication;
import tn.suvis.production.entities.Message;
import tn.suvis.production.entities.Mesure;
import tn.suvis.production.entities.PlantSection;
import tn.suvis.production.entities.Post;
import tn.suvis.production.entities.Production;
import tn.suvis.production.entities.Role;
import tn.suvis.production.entities.Segment;
import tn.suvis.production.entities.Users;
import tn.suvis.production.metier.ISuivisAdminMetier;
import tn.suvis.production.metier.ISuivisSuperAdminMetier;
import tn.suvis.production.metier.MesureMetier;
import tn.suvis.production.metier.SuivisMetierImpl;
import tn.suvis.production.model.ArretRowMapper;
import tn.suvis.production.model.ExelUserExporter;
import tn.suvis.production.model.MessageModel;
import tn.suvis.production.model.PdfUserExporter;
import tn.suvis.production.model.SuivisProductionForm;

@Controller
@SuppressWarnings("unused")
public class AdminController {
	private SuivisProductionForm webModel = new SuivisProductionForm();
	Users user = new Users();
	MultipartFile userPhoto;
	private List<PlantSection> plantSections;
	private List<Segment> segments;
	@Autowired
	private ISuivisSuperAdminMetier metier;
	@Autowired
	private JavaMailSender sender;
	private String roles;
	
	@ModelAttribute
	public Users construct() {
		return new Users();
	}

	// charger un utilisateur
	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/loadUser/{id}", method = RequestMethod.GET)
	public Users loadUser(@PathVariable("id") Long id) {
		Users u = metier.findUser(id);
		u.setRoles(metier.findRoleByUser(id));
		return u;
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/loadUserTable")
	public List<Users> loadUserTable() {
		List<Users> us = metier.findAllUser();

		return us;
	}

	@RequestMapping(value = "/kosu_connect/admin/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("/list", "users", metier.findAllUser());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		model.addObject("actualUser", current);
		model.addObject("user", user);
		model.addObject("roles", roles);
		return model;
	}

	@RequestMapping(value = "/kosu_connect/admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("/create");
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/create", method = RequestMethod.POST)
	public Users create(HttpServletRequest request, MultipartFile file) throws IOException, ParseException {

		Users u = new Users();
		Collection<Role> userRoles = new ArrayList<Role>();
		if (!file.isEmpty()) {
			/*
			 * BufferedImage bi = ImageIO.read(file.getInputStream());
			 * u.setPhoto(file.getBytes());
			 */
			u.setPhoto(userPhoto.getBytes());

		}
		roles = request.getParameter("roles");

		System.out.println(roles);
		userRoles.add(metier.findRole(new Long(roles)));

		u.setRoles(userRoles);
		u.setFirstName(request.getParameterValues("firstName")[0]);
		u.setLastName(request.getParameterValues("lastName")[0]);
		u.setAdresseMail(request.getParameterValues("adresseMail")[0]);
		u.setAdresse(request.getParameterValues("adresse")[0]);
		Date dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameterValues("dateDeNaissance")[0]);
		System.out.println(dateFormat);
		u.setDateDeNaissance(dateFormat);
		u.setSexe(request.getParameterValues("sexe")[0]);

		u.setTelephone(request.getParameter("telephone"));
		return metier.addUser(u);

	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/update", method = RequestMethod.POST)
	public void update(HttpServletRequest request, MultipartFile file) throws IOException, Exception {

		String inputVal = request.getParameterValues("idUser")[0];
		Long id = new Long(inputVal);
		Users u = metier.findUser(id);
		/*
		 * if (!file.isEmpty()) { BufferedImage bi =
		 * ImageIO.read(file.getInputStream()); u.setPhoto(file.getBytes()); }
		 */
		Collection<Role> userRoles = new ArrayList<Role>();
		if (!file.isEmpty()) {
			u.setPhoto(userPhoto.getBytes());

		}
		roles = request.getParameter("roles");
		if (roles != null) {

			userRoles.add(metier.findRole(new Long(roles)));

			u.setRoles(userRoles);
			u.setFirstName(request.getParameterValues("firstName")[0]);
			u.setLastName(request.getParameterValues("lastName")[0]);
			u.setAdresseMail(request.getParameterValues("adresseMail")[0]);
			u.setAdresse(request.getParameterValues("adresse")[0]);
			Date dateFormat = new SimpleDateFormat("dd/MM/yyyy")
					.parse(request.getParameterValues("dateDeNaissance")[0]);
			System.out.println(dateFormat);
			u.setDateDeNaissance(dateFormat);
			u.setSexe(request.getParameterValues("sexe")[0]);
			u.setTelephone(request.getParameter("telephone"));
			metier.editUser(u);
		}else {
			u.setFirstName(request.getParameterValues("firstName")[0]);
			u.setLastName(request.getParameterValues("lastName")[0]);
			u.setAdresseMail(request.getParameterValues("adresseMail")[0]);
			u.setAdresse(request.getParameterValues("adresse")[0]);
			Date dateFormat = new SimpleDateFormat("dd/MM/yyyy")
					.parse(request.getParameterValues("dateDeNaissance")[0]);
			System.out.println(dateFormat);
			u.setDateDeNaissance(dateFormat);
			u.setSexe(request.getParameterValues("sexe")[0]);
			u.setTelephone(request.getParameter("telephone"));
			metier.editUser(u);
		}

	}

	@RequestMapping(value = "/kosu_connect/admin/delete/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable("id") Long id) {
		metier.deleteUser(id);
		ModelAndView model = new ModelAndView("/list", "users", metier.findAllUser());
		model.addObject("user", user);
		model.addObject("roles", roles);
		return model;
	}

	@RequestMapping(value = "/kosu_connect/admin/loadUserProfile/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] loadProfile(@PathVariable(value = "id") Long id) throws IOException {
		Users users = metier.findUser(id);

		return IOUtils.toByteArray(new ByteArrayInputStream(users.getPhoto()));
	}

	@RequestMapping(value = "/kosu_connect/admin/loadUserProfile", method = RequestMethod.GET)
	public ModelAndView userDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users u = metier.findUserByName(authentication.getName());
		ModelAndView modelAndView = new ModelAndView("profile", "current", u);
		modelAndView.addObject("actualUser", u);

		return modelAndView;
	}

	@RequestMapping(value = "/kosu_connect/admin/postPhoto")
	@ResponseBody
	public void postPhoto(HttpServletRequest request, MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			BufferedImage bi = ImageIO.read(file.getInputStream());
			userPhoto = file;
			System.out.println(userPhoto.getContentType());
		}
	}

	// changer la photo de profile via drag and drop
	@RequestMapping(value = "/kosu_connect/admin/modifierPhoto")
	public void modifierPhoto(HttpServletRequest request, MultipartFile file) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		if (!file.isEmpty()) {
			BufferedImage bi = ImageIO.read(file.getInputStream());
			current.setPhoto(file.getBytes());
			metier.editUser(current);
		}
	}

	@RequestMapping(value = "/kosu_connect/admin/info_generale", method = RequestMethod.GET)
	public ModelAndView infoGenerale() {
		ModelAndView model = new ModelAndView("info_generale");
		String totalSegment=metier.totalSegment();
		String totalChaine=metier.totalChaine();
		model.addObject("nbreSegment", totalSegment);
		model.addObject("nbreChaine",totalChaine);
		return model;

	}

	@RequestMapping(value = "/kosu_connect/admin/messages_view")
	public ModelAndView messageView() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users u = metier.findUserByName(authentication.getName());
		ModelAndView model = new ModelAndView("message");
		model.addObject("actualUser", u);
		return model;

	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/loadMessagesTable")
	public List<MessageModel> listMessage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		List<Message> messages = metier.findReceiveMessageByUser(current.getIdUser());
		List<MessageModel> messageModels = new ArrayList<MessageModel>();
		for (Message m : messages) {
			MessageModel messageModel = new MessageModel();
			messageModel.setMessage(m);
			messageModel.setVu(false);
			List<Users> lecteurs = metier.findUserViewMessage(m.getId());

			for (Users u : lecteurs) {
				if (u.getIdUser() == current.getIdUser()) {
					messageModel.setVu(true);
				}
				break;
			}
			messageModels.add(messageModel);

		}
		return messageModels;
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/testLecture/{id}")
	public boolean findEmetteur(@PathVariable Long id) {
		boolean test = true;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		List<Users> lecteurs = metier.findUserViewMessage(id);
		if (lecteurs.isEmpty()) {
			return test;
		} else {
			for (Users u : lecteurs) {
				if (u.getIdUser() == current.getIdUser()) {
					test = false;
					break;
				}
			}
			return test;

		}

	}

	@RequestMapping(value = "/kosu_connect/admin/edit/{id}")
	public ModelAndView editMessage(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		Message m = metier.findMessageById(id);
		List<Users> lecteurs = metier.findUserViewMessage(id);
		lecteurs.add(current);
		m.setEstVuPar(lecteurs);
		metier.editMessage(m);
		return new ModelAndView("message_contenu");

	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/countNonVu")
	public Long countNonVu() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		Long count = metier.countNonReadMessageByUser(current.getIdUser());
		return count;
	}

	@RequestMapping(value = "/kosu_connect/admin/nouveau_message")
	public ModelAndView nouveauMessage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users u = metier.findUserByName(authentication.getName());
		ModelAndView model = new ModelAndView("nouveau_message");
		model.addObject("actualUser", u);

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/sendMessage")
	public void sendMessage(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users u = metier.findUserByName(authentication.getName());
		Message m = new Message(request.getParameter("contenu"), request.getParameter("sujet"));
		m.setEmetteur(u);
		List<Users> recept = metier
				.findUserByRole(metier.findRole(new Long(request.getParameter("role"))).getRoleName(), u.getIdUser());
		m.setRecepteurs(recept);
		m.setDate(new Date());
		metier.addMessage(m);
	}

	@RequestMapping(value = "/kosu_connect/admin/export")
	public ModelAndView exportXlsPdf(HttpServletRequest request, HttpServletResponse response) {
		Map<Chaine, Date> tempsArrêt = new HashMap<Chaine, Date>();
		String type = request.getParameter("type");
		System.out.println(type);

		List<Users> userList = metier.findAllUser();
		if (type != null && type.toLowerCase().equals("xls")) {
			return new ModelAndView(new ExelUserExporter(), "userList", userList);
		} else if (type != null && type.toLowerCase().equals("pdf")) {
			return new ModelAndView(new PdfUserExporter(), "userList", userList);
		}

		ModelAndView model = new ModelAndView("/list", "users", metier.findAllUser());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		model.addObject("actualUser", current);
		model.addObject("user", user);
		model.addObject("roles", roles);
		return model;
	}

	
	
	

	@RequestMapping(value = "/kosu_connect/admin/configuration")
	public ModelAndView config() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		Chaine chaine = new Chaine();
		
		List<Users> consultant = metier.findAllUser();
		

		PlantSection plantSection = new PlantSection();
		segments = metier.findAllSegment();
		plantSections = metier.findAllPlentSection();
		Segment segment = new Segment();
		ModelAndView model = new ModelAndView("configuration");
		model.addObject("escalation", new ConfigurationApplication());
		model.addObject("plantSection", plantSection);
		model.addObject("actualUser", current);
		model.addObject("segment", segment);
		model.addObject("segments", segments);
		model.addObject("chaine", chaine);
		model.addObject("plantSections", plantSections);
		model.addObject("users", consultant);
		return model;
	}

	// Configuration d'envoie d'sms
	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/loadEscalation")
	public ConfigurationApplication loadEscalation() {
		return metier.findLastConfiguration();
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/createEscalation", method = RequestMethod.POST)
	public ConfigurationApplication createEscalation(HttpServletRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());

		ConfigurationApplication config = new ConfigurationApplication();
		config.setEscale1(Integer.parseInt(req.getParameter("escale1")));
		config.setEscale2(Integer.parseInt(req.getParameter("escale2")));
		config.setEscale3(Integer.parseInt(req.getParameter("escale3")));
		config.setEscale4(Integer.parseInt(req.getParameter("escale4")));

		config.setDateConfig(new Date());
		config.setUser(current);
		return metier.addConfiguration(config);
	}

	// Charger les plent section
	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/loadPlentSection")
	public List<PlantSection> loadPlentSection() {
		List<PlantSection> plantSections = metier.findAllPlentSection();
		for (PlantSection p : plantSections) {
			if (p.getSmrt() != null) {
				p.setSmrt(metier.findUser(p.getSmrt().getIdUser()));
			}
		}
		return plantSections;
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/createPentSection")
	public PlantSection createPlentSection(HttpServletRequest request) {
		Users u = metier.findUser(Long.parseLong(request.getParameter("chefPlentSection")));
		System.out.println(u.toString());
		PlantSection plantSection = new PlantSection(request.getParameter("nom"), request.getParameter("entreprise"),
				new Date());
		plantSection.setSmrt(u);
		return metier.addPlentSection(plantSection);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/editPentSection")
	public void editPlentSection(PlantSection plantSection) {
		metier.editPlanSection(plantSection);
	}

	// Segment
	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/loadSegment")
	public List<Segment> loadSegment() {
		List<Segment> segments = metier.findAllSegment();

		return segments;
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/createSegment")
	public Segment createSegment(HttpServletRequest request) {
		Enumeration es = request.getParameterNames();
		while (es.hasMoreElements()) {
			System.out.println(es.nextElement());

		}
		Segment s = new Segment(request.getParameter("nom"));
		//s.setPlantSection(metier.findPlentSectionById(Long.parseLong(request.getParameter("plentSectionSegment"))));
		s.setBmrt(metier.findUser(Long.parseLong(request.getParameter("chef_segment"))));
		Post pm = new Post("PM", request.getParameter("description_PM"), null);
		pm.setUsers(metier.findUser(Long.parseLong(request.getParameter("chefPM"))));
		Post pa = new Post("PA", request.getParameter("description_PA"), null);
		pm.setUsers(metier.findUser(Long.parseLong(request.getParameter("chefPA"))));

		s = metier.addSegment(s);
		pa.setSegment(s);
		metier.addPost(pa);
		pm.setSegment(s);
		metier.addPost(pm);
		return s;
	}

	// chaine
	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/loadChaine")
	public List<Chaine> loadChaine() {
		return metier.findAllChaine();
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/createChaine")
	public Chaine createChaine(HttpServletRequest request) {
		Chaine chaine = new Chaine(request.getParameter("nom"), request.getParameter("description"),
				Integer.parseInt(request.getParameter("nombrePostTravail")));
		chaine.setChefChaine(metier.findUser(Long.parseLong(request.getParameter("chefChaine"))));
	//	chaine.setSegment(metier.findSegmentById(Long.parseLong(request.getParameter("segmentChaine"))));
		return metier.addChaine(chaine);

	}

	// Statistique
	@RequestMapping(value = "/kosu_connect/admin/statistiques")
	public ModelAndView statistiques() {
		ModelAndView model = new ModelAndView("statistique");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users current = metier.findUserByName(authentication.getName());
		model.addObject("actualUser", current);
		model.addObject("segments", metier.findAllSegment());
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/month_production")
	public List<Object> loadMonthProduction(HttpServletRequest request) {
		Date month = new Date();
		return metier.findProductionOfMonth(month);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/month_production_by_segment")
	public List<Object> loadMonthBySegmentProduction(HttpServletRequest request) {

		Date month = new Date();
		return metier.findProductionBySegmentByMonth(month);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/jour_production_on_segment")
	public List<Object> loadProductionJourOnSegment(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date jour = simpleDateFormat.parse(request.getParameter("filtre_jour"));
		return metier.loadProductionOnAllSegementByDay(jour);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/jour_production_By_segment")
	public List<Object> loadProductionJourBySegment(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date jour = simpleDateFormat.parse(request.getParameter("filtre_jour"));
		String id=request.getParameter("filtre_jours_segment");
if(id.isEmpty()) {
	return metier.loadProductionOnAllSegementBySegmentByDay(jour,null);
	
}else {
	Long idSegment=Long.parseLong(id);
	return metier.loadProductionOnAllSegementBySegmentByDay(jour,idSegment);
}
	}

	/*
	 * L'approche ici est que s'il l'utilisateur ne selectionne pas la date mais
	 * selectionne plutôt un segment je prend par defaut le jours où on est
	 */
	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/jour_By_production_By_segment")
	public List<Object> loadProductionOnSegmentByDay(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Long idSegment = Long.parseLong(request.getParameter("filtre_jours_segment"));
		String jour = request.getParameter("filtre_jour");
		if (jour != "") {
			Date day = simpleDateFormat.parse(jour);
			return metier.loadProductionOnSegmentByDay(day, idSegment);
		} else {
			return metier.loadProductionOnSegmentByDay(new Date(), idSegment);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/loadChaine/{idSegment}")
	public List<Chaine> loadChaineBySegment(@PathVariable Long idSegment) {
		return metier.loadChaineOnSegment(idSegment);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/loadproductionChaineByDayBySegment")
	public List<Object> loadproductionChaineByDayBySegment(HttpServletRequest request) throws ParseException {
		Long idSegment = Long.parseLong(request.getParameter("filtre_jours_segment"));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String jour = request.getParameter("filtre_jour");
		Date day = simpleDateFormat.parse(jour);

		return metier.loadProductionByChaineOnSegmentAtDay(day, idSegment);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/loadProductionByMonth")
	public List<Object> loadProductionByMonth(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
		String mois = request.getParameter("select_mois");
		Date month = simpleDateFormat.parse(mois);
		return metier.findProductionOfMonth(month);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/loadProductionByMonthBySegment")
	public List<Object> loasProductionByMonthBySegment(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
		String mois = request.getParameter("select_mois");
		Date month = simpleDateFormat.parse(mois);
		return metier.findProductionBySegmentByMonth(month);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/month/segment/line")
	public List<Object> loadMonthProductionOnSegment(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
		String mois = request.getParameter("select_mois");
		Date month = simpleDateFormat.parse(mois);
		Long idSegment = Long.parseLong(request.getParameter("filtre_mois_segment"));
		return metier.loadMonthProductionOnSegment(month, idSegment);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/month/segment/chart")
	public List<Object> loadMonthProductionOnSegmentGroupByChaine(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
		String mois = request.getParameter("select_mois");
		Date month = simpleDateFormat.parse(mois);
		Long idSegment = Long.parseLong(request.getParameter("filtre_mois_segment"));
		return metier.loadMonthProductionOnSegmentGroupByChaine(month, idSegment);
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/year/line")
	public List<Object> loadProductionGroupByMonth(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String annee = request.getParameter("select_annee");
		Date year = simpleDateFormat.parse(annee);
		String id = request.getParameter("filtre_annee_segment");
		if (id.isEmpty()) {
			return metier.loadProductionOnAllSegmentByYear(year, null);

		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadProductionOnAllSegmentByYear(year, idSegment);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/year/pie")
	public List<Object> loadProductionGroupBySegment(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String annee = request.getParameter("select_annee");
		Date year = simpleDateFormat.parse(annee);
		String id = request.getParameter("filtre_annee_segment");
		if (id.isEmpty()) {
			return metier.loadProductionYearBySegment(year, null);

		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadProductionYearBySegment(year, idSegment);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/periode/line")
	public List<Object> loadProductionGroupByDay(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String debut = request.getParameter("choix_debut");
		String fin = request.getParameter("choix_fins");

		Date dateDebut = simpleDateFormat.parse(debut);
		Date dateFin = simpleDateFormat.parse(fin);

		String id = request.getParameter("filtre_periode_segment");
		if (id.isEmpty()) {
			return metier.loadProductionOnAllSegmentByPeriode(dateDebut, dateFin, null);

		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadProductionOnAllSegmentByPeriode(dateDebut, dateFin, idSegment);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/statistiques/periode/pie")
	public List<Object> loadProductionByPeriode(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String debut = request.getParameter("choix_debut");
		String fin = request.getParameter("choix_fins");

		Date dateDebut = simpleDateFormat.parse(debut);
		Date dateFin = simpleDateFormat.parse(fin);

		String id = request.getParameter("filtre_periode_segment");
		if (id.isEmpty()) {
			return metier.loadProductionByPeriode(dateDebut, dateFin, null);

		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadProductionByPeriode(dateDebut, dateFin, idSegment);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/month")
	public List<Object> loadMonthArret(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
		String mois = request.getParameter("select_mois");
		System.out.println(mois);
		String id = "";
		id = request.getParameter("filtre_mois_segment");
		System.out.println(id == "");
		Date month = simpleDateFormat.parse(mois);
		if (id == null || id == "") {
			return metier.loadArretMonth(month, null);
		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadArretMonth(month, idSegment);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/year")
	public List<Object> loadYearArret(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String annee = request.getParameter("select_annee");
		String id = "";
		id = request.getParameter("filtre_annee_segment");
		System.out.println(id == "");
		Date year = simpleDateFormat.parse(annee);
		if (id == null || id == "") {
			return metier.loadArretYear(year, null);
		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadArretYear(year, idSegment);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/day")
	public List<Object> loadDayArret(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String jours = request.getParameter("filtre_jour");
		System.out.println(jours);
		String id = "";
		id = request.getParameter("filtre_jours_segment");
		System.out.println(id == "");
		Date day = simpleDateFormat.parse(jours);
		if (id == null || id == "") {
			return metier.loadArretDay(day, null);
		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadArretDay(day, idSegment);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/kosu_connect/admin/periode")
	public List<Object> loadDayPeriode(HttpServletRequest request) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String debut = request.getParameter("choix_debut");
		String fin = request.getParameter("choix_fins");
		String id = "";
		id = request.getParameter("filtre_periode_segment");
		System.out.println(id == "");
		Date begin = simpleDateFormat.parse(debut);
		Date end = simpleDateFormat.parse(fin);

		if (id == null || id == "") {
			return metier.loadArretPeriode(begin, end, null);
		} else {
			Long idSegment = Long.parseLong(id);
			return metier.loadArretPeriode(begin, end, idSegment);

		}
	}

}
