package com.sndi.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.sndi.model.TAssignation;
import com.sndi.model.TFonction;
import com.sndi.model.TTypeFonction;
import com.sndi.report.ProjetReport;
import com.sndi.security.UserController;
import com.sndi.service.ConstantService;
import com.sndi.service.Iservice;
import com.sndi.utilitaires.KeyGen;

@Component
@Scope(value="session")
public class FonctionController {
	Logger _logger = Logger.getLogger(FonctionController.class);
	@Autowired
	Iservice iservice;
	
	@Autowired
	KeyGen keyGen;
	
	@Autowired
	UserController userController;
	
	@Autowired
	ProjetReport projetReport;
	@Autowired
	ConstantService constantService;
	
	private String filterCode="";
	private TFonction fonction= new TFonction();
	private List <TFonction> fonctionListe = new ArrayList<TFonction>();
	private List<TTypeFonction> listTypefonction = new ArrayList<TTypeFonction>();
	private TFonction slctdTd=new TFonction();
	

	private String typefonc="";
	
	 @PostConstruct
	public void postconst() {
		 chargeFonction();
		chargeTypeFonction();
		fonction = new TFonction();
		fonction.setTTypeFonction(new TTypeFonction());
		
		slctdTd = new TFonction();
		slctdTd.setTTypeFonction(new TTypeFonction());
		
	
	}
    
	 public void chargeFonction() {
		 fonctionListe= iservice.getObjectsByColumn("TFonction", new ArrayList<String>(Arrays.asList("fon_libelle")));
		
	 }
	 
	 
	 public void chargeTypeFonction(){
			listTypefonction.clear();
			listTypefonction =(List<TTypeFonction>) iservice.getObjectsByColumn("TTypeFonction", new ArrayList<String>(Arrays.asList("tyfCod")));
		}
	 
//		public void chargeSiteData(){
//			listSite.clear();
//			listSite =(List<TSite>) iservice.getObjectsByColumn("TSite", new ArrayList<String>(Arrays.asList("sitCode")));
//		}

	public void filtrefonction() {
		fonctionListe= iservice.getObjectsByColumn("TFonction", new ArrayList<String>(Arrays.asList("fonCod")),
				new WhereClause("FON_LIBELLE",WhereClause.Comparateur.LIKE,"%"+filterCode+"%"));
	}
	
	
	

	
	public void onSelectTypeFonction() {

		
		if(!("empty".equalsIgnoreCase(typefonc) || "".equalsIgnoreCase(typefonc)) ){
    		
    		int i =-1;
    		while( ++i<listTypefonction.size() && !(""+listTypefonction.get(i).getTyfCod()).equalsIgnoreCase(typefonc) );
    		fonction.setTTypeFonction(listTypefonction.get(i));
    		fonction.setFonCod(keyGen.getFonctionID(listTypefonction.get(i).getTyfCod()));
    		fonction.setFonLibelle(listTypefonction.get(i).getTyfLibelle());
    		
    	}else{
    		//slctdTd.setStructure(null);
    	}
		
	}
	public void onSelectTypeFonctionMoif() {
		
		
		if(!("empty".equalsIgnoreCase(typefonc) || "".equalsIgnoreCase(typefonc)) ){
			
			int i =-1;
			while( ++i<listTypefonction.size() && !(""+listTypefonction.get(i).getTyfCod()).equalsIgnoreCase(typefonc) );
			slctdTd.setTTypeFonction(listTypefonction.get(i));
			slctdTd.setFonCod(keyGen.getFonctionID(listTypefonction.get(i).getTyfCod()));
			slctdTd.setFonLibelle(listTypefonction.get(i).getTyfLibelle());
			
		}else{
			//slctdTd.setStructure(null);
		}
		
	}
	
	@Transactional
	public String saveFonction() throws IOException {

		 String chaine="";
		if(typefonc.equalsIgnoreCase("") || "".equalsIgnoreCase(fonction.getFonCod())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR," selectionnez le type fonction", ""));
		return null;
		}
		else {
			
				//fonction.setFonCod(keyGen.getFonctionID(typefonc));
				//fonction.setTTypeFonction(new TTypeFonction(typefonc));
				iservice.addObject(fonction);
				
				fonction = new TFonction();
				fonction.setTTypeFonction(new TTypeFonction());
				
				chargeFonction();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," Enregistrement effectué avec succés! ", ""));
				return	userController.renderPage("fon1");
			}
	 
		
	}
	@Transactional
	public String updatefonction() throws IOException {
		iservice.updateObject(slctdTd);
		chargeFonction();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," Modification effectuée avec succés! ", ""));
		return	userController.renderPage("fon1");
	}
	
	@Transactional
	public void delete(){
		System.out.println("+-------------+ "+getSlctdTd().getFonCod()+"---"+getSlctdTd().getFonLibelle());
		try {
			iservice.deleteObject(getSlctdTd());
			chargeFonction();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," Fonction supprimée ! ", ""));
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN," Impossible de supprimer la Fonction ! ", ""));
		}  
	}
	
	public String renderPage(String value) throws IOException{
		switch(value) {
		case "fon1":
			chargeFonction();
		//	userController.renderPage(value);
		break;
		case "fon2":
			typefonc ="";
			fonction = new TFonction();
			fonction.setTTypeFonction(new TTypeFonction());
			
			slctdTd = new TFonction();
			slctdTd.setTTypeFonction(new TTypeFonction());
		break;
		case "fon3":
			typefonc = slctdTd.getTTypeFonction().getTyfCod();
		
			//userController.renderPage(value);
		break;
		}
		
		return userController.renderPage(value);
	}
	public String getFilterCode() {
		return filterCode;
	}

	public void setFilterCode(String filterCode) {
		this.filterCode = filterCode;
	}


	public List<TFonction> getFonctionListe() {
		return fonctionListe;
	}

	public void setFonctionListe(List<TFonction> fonctionListe) {
		this.fonctionListe = fonctionListe;
	}

	public List<TFonction> geTFonctionListe() {
		return fonctionListe;
	}

	public void seTFonctionListe(List<TFonction> fonctionListe) {
		this.fonctionListe = fonctionListe;
	}

	public TFonction getSlctdTd() {
		return slctdTd;
	}

	public void setSlctdTd(TFonction slctdTd) {
		this.slctdTd = slctdTd;
	}

	public TFonction getFonction() {
		return fonction;
	}

	public void setFonction(TFonction fonction) {
		this.fonction = fonction;
	}

//	public List<TSite> getListSite() {
//		return listSite;
//	}
//
//	public void setListSite(List<TSite> listSite) {
//		this.listSite = listSite;
//	}

	public List<TTypeFonction> getListTypefonction() {
		return listTypefonction;
	}

	public void setListTypefonction(List<TTypeFonction> listTypefonction) {
		this.listTypefonction = listTypefonction;
	}

	

	public String getTypefonc() {
		return typefonc;
	}

	public void setTypefonc(String typefonc) {
		this.typefonc = typefonc;
	}



}
