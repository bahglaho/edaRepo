package com.sndi.model;
// Generated 7 sept. 2017 08:46:33 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TTypeFonction generated by hbm2java
 */
@Entity
@Table(name = "T_TYPE_FONCTION")
public class TTypeFonction implements java.io.Serializable {

	private String tyfCod;
	private String tyfLibelle;
	private Set<TFonction> TFonctions = new HashSet<TFonction>(0);
	

	public TTypeFonction() {
	}

	public TTypeFonction(String tyfCod) {
		this.tyfCod = tyfCod;
	}

	public TTypeFonction(String tyfCod, String tyfLibelle, Set<TFonction> TFonctions) {
		this.tyfCod = tyfCod;
		this.tyfLibelle = tyfLibelle;
		this.TFonctions = TFonctions;
	}

	@Id

	@Column(name = "TYF_COD", unique = true, nullable = false, length = 3)
	public String getTyfCod() {
		return this.tyfCod;
	}

	public void setTyfCod(String tyfCod) {
		this.tyfCod = tyfCod;
	}

	@Column(name = "TYF_LIBELLE", length = 100)
	public String getTyfLibelle() {
		return this.tyfLibelle;
	}

	public void setTyfLibelle(String tyfLibelle) {
		this.tyfLibelle = tyfLibelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TTypeFonction")
	public Set<TFonction> getTFonctions() {
		return this.TFonctions;
	}

	public void setTFonctions(Set<TFonction> TFonctions) {
		this.TFonctions = TFonctions;
	}


}
