package com.sndi.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sndi.dao.WhereClause;
import com.sndi.dao.WhereClause.Comparateur;
import com.sndi.model.TMotdepasse;
import com.sndi.model.TOperateur;
import com.sndi.security.CustomPasswordEncoder;
import com.sndi.security.UserController;
import com.sndi.service.Iservice;
import com.sndi.utilitaires.KeyGen;

@Component
@Scope(value="session")

public class PasswordController {
	Logger _logger = Logger.getLogger(PasswordController.class);
	@Autowired
	Iservice iservice;

	@Autowired
	KeyGen keyGen;

	@Autowired
	UserController userController;
	
	private String actuelPass="";
	private String nouveauPass="";
	private String passConfirme="";
	
	
	public void recupMotPass() {
	
		
		
	}

	 @Transactional
	public void changePassword() {
		 CustomPasswordEncoder cp = new CustomPasswordEncoder();
				List<TMotdepasse> lmdp = iservice.getObjectsByColumn("TMotdepasse",
						new WhereClause("ope_matricule",Comparateur.EQ,userController.getOperateur().getOpeMatricule()));

				if(checkMotDePasseUser(lmdp)){
					for(TMotdepasse pwd: lmdp){
						pwd.setMdpMotdepasseInit("");
						pwd.setMdpStatut(false);
						iservice.updateObject(pwd);
						_logger.info("Mot de passe modifie id:"+pwd.getMdpId());
					}
					
					TMotdepasse mdp = new TMotdepasse();
					mdp.setTOperateur(userController.getOperateur());
					mdp.setMdpMotdepasse(cp.encode(nouveauPass));
					mdp.setMdpMotdepasseInit("");
					mdp.setMdpDate(Calendar.getInstance().getTime());
					mdp.setMdpStatut(true);
					iservice.addObject(mdp);
					nouveauPass="";
					actuelPass="";
					passConfirme="";
					
					//renderPage("14");
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Mot de passe modifié avec succès ! ", ""));
				}else {
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN," Modification impossible! ", ""));
					 
				}
			
}
	 
	 public boolean checkMotDePasseUser(List<TMotdepasse> lmdp){
			HashMap<String, String> hmErrors = new HashMap <String, String>();
			String messages ="";
			CustomPasswordEncoder cp = new CustomPasswordEncoder();
		
			if("".equals(actuelPass) ){
				hmErrors.put("MDP0", "- Veuillez remplir l'ancien mot de passe SVP!");
			}
			for(TMotdepasse mdp : lmdp){
				if(mdp.getMdpStatut() && !cp.matches(actuelPass, mdp.getMdpMotdepasse())){
					hmErrors.put("MDP", "- Le mot de passe actuel est incorrect!");
					break;
				}
			}
			if("".equals(nouveauPass) ){
				hmErrors.put("MDP1", "- Veuillez remplir le nouveau mot de passe SVP!");
			}
			
			if("".equals(passConfirme) ){
				hmErrors.put("MDP2", "- Veuillez confirmer le nouveau mot de passe SVP!");
			}
			for(TMotdepasse mdp : lmdp){
				if(cp.matches(nouveauPass, mdp.getMdpMotdepasse())){
					hmErrors.put("MDP", "- Le mot de passe exist déjà!");
					break;
				}
			}
			if(!nouveauPass.equals(passConfirme)){
				hmErrors.put("MDP3", "- Re-confirmer le nouveau mot de passe SVP!");
			}
			
			
			if(hmErrors.size() > 0){
				for(String s: hmErrors.values()){
					messages = s;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,messages, ""));  

				}
				return false;
			}
			return true;

		}
	
	public String getActuelPass() {
		return actuelPass;
	}

	public void setActuelPass(String actuelPass) {
		this.actuelPass = actuelPass;
	}

	public String getNouveauPass() {
		return nouveauPass;
	}

	public void setNouveauPass(String nouveauPass) {
		this.nouveauPass = nouveauPass;
	}

	public String getPassConfirme() {
		return passConfirme;
	}
	public void setPassConfirme(String passConfirme) {
		this.passConfirme = passConfirme;
	}
	
}
