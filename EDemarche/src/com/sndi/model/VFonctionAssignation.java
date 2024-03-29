package com.sndi.model;
// Generated 2 nov. 2018 10:05:36 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * VFonctionAssignation generated by hbm2java
 */
@Entity
@Immutable
@Table(name = "v_fonction_assignation")
public class VFonctionAssignation implements java.io.Serializable {


	
	private String fonCod;
	private String fonLibelle;
	private String opeMatricule;

	public VFonctionAssignation() {
	}

	public VFonctionAssignation( String fonCod, String fonLibelle, String opeMatricule) {
		
		this.fonCod = fonCod;
		this.fonLibelle = fonLibelle;
		this.opeMatricule = opeMatricule;
	}
	

	@Id

	@Column(name = "fon_cod", length = 12)
	public String getFonCod() {
		return this.fonCod;
	}

	public void setFonCod(String fonCod) {
		this.fonCod = fonCod;
	}

	@Column(name = "fon_libelle", length = 240)
	public String getFonLibelle() {
		return this.fonLibelle;
	}

	public void setFonLibelle(String fonLibelle) {
		this.fonLibelle = fonLibelle;
	}

	@Column(name = "ope_matricule", length = 25)
	public String getOpeMatricule() {
		return opeMatricule;
	}

	public void setOpeMatricule(String opeMatricule) {
		this.opeMatricule = opeMatricule;
	}

}
