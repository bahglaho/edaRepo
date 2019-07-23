package com.sndi.model;
// Generated 23 juil. 2019 09:13:01 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TypeDemande generated by hbm2java
 */
@Entity
@Table(name = "TYPE_DEMANDE")
public class TypeDemande implements java.io.Serializable {

	private String tydCode;
	private Structure structure;
	private String tydLibelle;
	private String tydDescription;
	private Long tydMontant;
	private Set<Demande> demandes = new HashSet<Demande>(0);

	public TypeDemande() {
	}

	public TypeDemande(String tydCode) {
		this.tydCode = tydCode;
	}

	public TypeDemande(String tydCode, Structure structure, String tydLibelle, String tydDescription, Long tydMontant,
			Set<Demande> demandes) {
		this.tydCode = tydCode;
		this.structure = structure;
		this.tydLibelle = tydLibelle;
		this.tydDescription = tydDescription;
		this.tydMontant = tydMontant;
		this.demandes = demandes;
	}

	@Id

	@Column(name = "TYD_CODE", unique = true, nullable = false, length = 5)
	public String getTydCode() {
		return this.tydCode;
	}

	public void setTydCode(String tydCode) {
		this.tydCode = tydCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STR_CODE")
	public Structure getStructure() {
		return this.structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	@Column(name = "TYD_LIBELLE", length = 500)
	public String getTydLibelle() {
		return this.tydLibelle;
	}

	public void setTydLibelle(String tydLibelle) {
		this.tydLibelle = tydLibelle;
	}

	@Column(name = "TYD_DESCRIPTION", length = 2000)
	public String getTydDescription() {
		return this.tydDescription;
	}

	public void setTydDescription(String tydDescription) {
		this.tydDescription = tydDescription;
	}

	@Column(name = "TYD_MONTANT", precision = 10, scale = 0)
	public Long getTydMontant() {
		return this.tydMontant;
	}

	public void setTydMontant(Long tydMontant) {
		this.tydMontant = tydMontant;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeDemande")
	public Set<Demande> getDemandes() {
		return this.demandes;
	}

	public void setDemandes(Set<Demande> demandes) {
		this.demandes = demandes;
	}

}