package com.sndi.model;
// Generated 23 juil. 2019 09:13:01 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Structure generated by hbm2java
 */
@Entity
@Table(name = "STRUCTURE")
public class Structure implements java.io.Serializable {

	private String strCode;
	private String strLibelle;
	private String strDescription;
	private Set<TFonction> TFonctions = new HashSet<TFonction>(0);
	private Set<TypeDemande> typeDemandes = new HashSet<TypeDemande>(0);

	public Structure() {
	}

	public Structure(String strCode) {
		this.strCode = strCode;
	}

	public Structure(String strCode, String strLibelle, String strDescription, Set<TFonction> TFonctions,
			Set<TypeDemande> typeDemandes) {
		this.strCode = strCode;
		this.strLibelle = strLibelle;
		this.strDescription = strDescription;
		this.TFonctions = TFonctions;
		this.typeDemandes = typeDemandes;
	}

	@Id

	@Column(name = "STR_CODE", unique = true, nullable = false, length = 10)
	public String getStrCode() {
		return this.strCode;
	}

	public void setStrCode(String strCode) {
		this.strCode = strCode;
	}

	@Column(name = "STR_LIBELLE", length = 250)
	public String getStrLibelle() {
		return this.strLibelle;
	}

	public void setStrLibelle(String strLibelle) {
		this.strLibelle = strLibelle;
	}

	@Column(name = "STR_DESCRIPTION", length = 250)
	public String getStrDescription() {
		return this.strDescription;
	}

	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "structure")
	public Set<TFonction> getTFonctions() {
		return this.TFonctions;
	}

	public void setTFonctions(Set<TFonction> TFonctions) {
		this.TFonctions = TFonctions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "structure")
	public Set<TypeDemande> getTypeDemandes() {
		return this.typeDemandes;
	}

	public void setTypeDemandes(Set<TypeDemande> typeDemandes) {
		this.typeDemandes = typeDemandes;
	}

}