package com.ganjx.cinema.entity;

// Generated 2015-10-14 10:59:00 by Hibernate Tools 4.0.0

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.vion.core.domain.entity.IEntity;

/**
 * Cinema generated by hbm2java
 */
@Entity
@Table(name = "cinema")
@DynamicUpdate
public class Cinema implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cinemaCode;
	private String cinemaName;
	private BigDecimal cinemaLng;
	private BigDecimal cinemaLat;
	private String theatreChain;
	private String address;
	private String aliasName;
	private String addCode;
	/**
	 * 说明：可信度
	 */
	private Integer confidence;

	public Cinema() {
	}

	public Cinema(String cinemaCode, String cinemaName, BigDecimal cinemaLng,
			BigDecimal cinemaLat, String theatreChain, String address,
			String aliasName, String addCode) {
		this.cinemaCode = cinemaCode;
		this.cinemaName = cinemaName;
		this.cinemaLng = cinemaLng;
		this.cinemaLat = cinemaLat;
		this.theatreChain = theatreChain;
		this.address = address;
		this.aliasName = aliasName;
		this.addCode = addCode;
	}

	@Id
//	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "cinema_code")
	public String getCinemaCode() {
		return this.cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	@Column(name = "cinema_name")
	public String getCinemaName() {
		return this.cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	@Column(name = "cinema_lng", precision = 9, scale = 6)
	public BigDecimal getCinemaLng() {
		return this.cinemaLng;
	}

	public void setCinemaLng(BigDecimal cinemaLng) {
		this.cinemaLng = cinemaLng;
	}

	@Column(name = "cinema_lat", precision = 9, scale = 6)
	public BigDecimal getCinemaLat() {
		return this.cinemaLat;
	}

	public void setCinemaLat(BigDecimal cinemaLat) {
		this.cinemaLat = cinemaLat;
	}

	@Column(name = "theatre_chain", length = 50)
	public String getTheatreChain() {
		return this.theatreChain;
	}

	public void setTheatreChain(String theatreChain) {
		this.theatreChain = theatreChain;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "alias_name")
	public String getAliasName() {
		return this.aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	@Column(name = "add_code", length = 30)
	public String getAddCode() {
		return this.addCode;
	}

	public void setAddCode(String addCode) {
		this.addCode = addCode;
	}

	@Column(name = "confidence")
	public Integer getConfidence() {
		return confidence;
	}

	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}

}
