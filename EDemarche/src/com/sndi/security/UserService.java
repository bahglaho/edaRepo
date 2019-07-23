package com.sndi.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.sndi.model.TAssignation;
import com.sndi.model.TFonction;
import com.sndi.model.TMotdepasse;
import com.sndi.model.TOperateur;
import com.sndi.model.TTypeFonction;
import com.sndi.utilitaires.AutorityAction;
import com.sndi.utilitaires.AutorityClass;
import com.sndi.utilitaires.AutorityTraitement;

@org.springframework.stereotype.Service
public class UserService implements IUserService {

	private TOperateur operateur = new TOperateur();
	private List<TAssignation> listeAss = new ArrayList<TAssignation>();
	private TMotdepasse motdepasses = new TMotdepasse();
	private TFonction fonctions = new TFonction();
	private Date dateCons;
	private AutorityClass autoritys = new AutorityClass();
	
	
	
	@PostConstruct
	public void postConstru() {	
		System.out.println(" Initialisation de UserService ");
	}
	
	public List<TAssignation> getListeAssignations(TOperateur operateur){
		List<TAssignation> listeTAssignation = new ArrayList<TAssignation>();
		for(TAssignation ass: operateur.getTAssignations()){
			if(ass.getAssStatut()==true){// TODO and the assignation isn't expired
				listeTAssignation.add(ass);	
			}
		}
		//ordonner les TAssignations
		 Collections.sort(listeTAssignation, new Comparator<TAssignation>() {
		        @Override public int compare(TAssignation p1, TAssignation p2) {
		        	int n = p1.getTFonction().getFonCod().compareTo(p2.getTFonction().getFonCod());
		        	return n; //crs  
		        }

		    });
		//TODO faire un message d'erreur si aucune assignation est TRUE
		getListeAss().clear();
		getListeAss().addAll(listeTAssignation);
		return listeTAssignation;
	}
	
	
	
	public TMotdepasse getMotPasse(TOperateur operateur){
		TMotdepasse m = new TMotdepasse();
		for(TMotdepasse mdp: operateur.getTMotdepasses()){
			if(mdp.getMdpStatut()==true){
				setTMotdepasses(mdp);
			m = mdp;	
			}
		}
		return m;
	}
	
	
	public AutorityClass cleanAutority(){
		AutorityClass autority = new AutorityClass();
		setAutoritys(autority);
		return autority;
	}
	
	
	public AutorityClass getAutorisation(AutorityClass autority, TTypeFonction typeFonction){
		getModule(autority,typeFonction);
		getTraitement(autority,typeFonction);
		getAction(autority,typeFonction);
		
		setAutoritys(autority);
		return autority;
	}

public void getModule(AutorityClass autority, TTypeFonction typeFonction){
	//AutorityModule module = new AutorityModule();
	switch (typeFonction.getTyfCod()) {
	case "ADM":
		autority.getModules().setMenuADM(true);
		autority.getModules().setMenuMAJ(false);
		autority.getModules().setMenuGRF(false);
		autority.getModules().setMenuPRD(false);
		autority.getModules().setMenuAVG(false);
		autority.getModules().setMenuSGC(false);
		
		break;
	
	
	default:
		autority.getModules().setMenuADM(false);
		autority.getModules().setMenuMAJ(false);
		autority.getModules().setMenuGRF(false);
		autority.getModules().setMenuPRD(false);
		autority.getModules().setMenuAVG(false);
		autority.getModules().setMenuSGC(false);
		break;
	}
	
		//return autority;
	}

public AutorityTraitement getTraitement(AutorityClass autority, TTypeFonction typeFonction){
	AutorityTraitement traitement = new AutorityTraitement();
		
	
		return traitement;
	}

public AutorityAction getAction(AutorityClass autority, TTypeFonction typeFonction){
	AutorityAction action = new AutorityAction();
		
	
		return action;
	}

	public TOperateur getTOperateur() {
		return operateur;
	}

	public void setTOperateur(TOperateur operateur) {
		this.operateur = operateur;
	}

	public TMotdepasse getTMotdepasses() {
		return motdepasses;
	}

	public void setTMotdepasses(TMotdepasse motdepasses) {
		this.motdepasses = motdepasses;
	}

	public TFonction getTFonctions() {
		return fonctions;
	}

	public void setTFonctions(TFonction fonctions) {
		this.fonctions = fonctions;
	}

	public Date getDateCons() {
		return dateCons;
	}

	public void setDateCons(Date dateCons) {
		this.dateCons = dateCons;
	}

	public AutorityClass getAutoritys() {
		return autoritys;
	}

	public void setAutoritys(AutorityClass autoritys) {
		this.autoritys = autoritys;
	}

	public List<TAssignation> getListeAss() {
		return listeAss;
	}

	public void setListeAss(List<TAssignation> listeAss) {
		this.listeAss = listeAss;
	}


	
}
