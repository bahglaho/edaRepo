package com.sndi.model;
// Generated 31 oct. 2018 10:01:02 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * VOperateur generated by hbm2java
 */
@Entity
@Immutable
@Table(name = "v_operateur")
public class VOperateur implements java.io.Serializable {



	private String opeNom;
	private String opeLogin;
	private String fonLibelle;
	private String mdpMotdepasse;

	public VOperateur(String opeNom, String opeLogin, String fonLibelle, String mdpMotdepasse) {
		this.opeNom = opeNom;
		this.opeLogin = opeLogin;
		this.fonLibelle = fonLibelle;
		this.mdpMotdepasse = mdpMotdepasse;
	}
	@Id
	@Column(name = "ope_nom")
	public String getOpeNom() {
		return this.opeNom;
	}

	public void setOpeNom(String opeNom) {
		this.opeNom = opeNom;
	}

	@Column(name = "ope_login", length = 50)
	public String getOpeLogin() {
		return this.opeLogin;
	}

	public void setOpeLogin(String opeLogin) {
		this.opeLogin = opeLogin;
	}

	@Column(name = "fon_libelle", length = 240)
	public String getFonLibelle() {
		return this.fonLibelle;
	}

	public void setFonLibelle(String fonLibelle) {
		this.fonLibelle = fonLibelle;
	}

	@Column(name = "mdp_motdepasse", length = 250)
	public String getMdpMotdepasse() {
		return this.mdpMotdepasse;
	}

	public void setMdpMotdepasse(String mdpMotdepasse) {
		this.mdpMotdepasse = mdpMotdepasse;
	}

}