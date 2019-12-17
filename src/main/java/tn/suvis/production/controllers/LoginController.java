package tn.suvis.production.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.suvis.production.config.TcpServer;
import tn.suvis.production.dao.Cryptage;
import tn.suvis.production.entities.Users;
import tn.suvis.production.metier.ISuivisAdminMetier;
import tn.suvis.production.model.SuivisProductionForm;

@Controller
public class LoginController {
	SuivisProductionForm webModel=new SuivisProductionForm();
	Users autenthified;
	@Autowired
	private ISuivisAdminMetier metier;
	@RequestMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/kosu_connect/bienvenue")
	public String pageAcceil(Model model) {
		//TcpServer tcpServer = new TcpServer();
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		Users users=metier.findUserByName(authentication.getName());
		if(users.isFirsTime()){
		autenthified=users;
		
		model.addAttribute("pass",new MDPass());
			return"change_passe";
		}else{
			
			model.addAttribute("user",new Users());

			model.addAttribute("actualUser",users);
			return "bienvenue";
		}
		
	}
	@RequestMapping(value="/kosu_connect/changer_mot_de_passe",method=RequestMethod.POST)
	public String changerPasse(HttpServletRequest request){
		String mdp=request.getParameterValues("mdp")[0];
		
		Cryptage cryptage=new Cryptage();
		String mdpcrypt=cryptage.crypter(mdp);
		autenthified.setPassword(mdpcrypt);
		Users u=metier.findUser(autenthified.getIdUser());
		u.setFirsTime(false);

		u.setPassword(mdpcrypt);
		metier.editUser(u);
		return"bienvenue1";
	}
	@RequestMapping(value="/403")
	public String accesRefuser(){
		return"403";
	}
}
