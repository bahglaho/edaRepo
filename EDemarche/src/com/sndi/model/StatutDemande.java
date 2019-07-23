package com.sndi.model;
// Generated 23 juil. 2019 09:13:01 by Hibernate Tools 4.3.5.Final

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * StatutDemande generated by hbm2java
 */
@Entity
@Table(name = "STATUT_DEMANDE")
public class StatutDemande implements java.io.Serializable {

	private long stdId;
	private Demande demande;
	private Statut statut;
	private TFonction TFonction;
	private String stdObservation;
	private Serializable stdDate;

	public StatutDemande() {
	}

	public StatutDemande(long stdId) {
		this.stdId = stdId;
	}

	public StatutDemande(long stdId, Demande demande, Statut statut, TFonction TFonction, String stdObservation,
			Serializable stdDate) {
		this.stdId = stdId;
		this.demande = demande;
		this.statut = statut;
		this.TFonction = TFonction;
		this.stdObservation = stdObservation;
		this.stdDate = stdDate;
	}

	@Id
	@SequenceGenerator(name = "SEQ_STD_Sequence", sequenceName = "SEQ_STD", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STD_Sequence")
	@Column(name = "STD_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getStdId() {
		return this.stdId;
	}

	public void setStdId(long stdId) {
		this.stdId = stdId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEM_CODE")
	public Demande getDemande() {
		return this.demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATUT_CODE")
	public Statut getStatut() {
		return this.statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FON_COD")
	public TFonction getTFonction() {
		return this.TFonction;
	}

	public void setTFonction(TFonction TFonction) {
		this.TFonction = TFonction;
	}

	@Column(name = "STD_OBSERVATION", length = 500)
	public String getStdObservation() {
		return this.stdObservation;
	}

	public void setStdObservation(String stdObservation) {
		this.stdObservation = stdObservation;
	}

	@Column(name = "STD_DATE")
	public Serializable getStdDate() {
		return this.stdDate;
	}

	public void setStdDate(Serializable stdDate) {
		this.stdDate = stdDate;
	}

}