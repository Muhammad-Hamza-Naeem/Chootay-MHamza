package com.webproj.chootay.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.List;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
import org.json.simple.JSONObject;
import javax.validation.constraints.Email;

@Entity
@Table(name = "bike")
public class Bike {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID bike_id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "year", nullable = false)
	private int year;

	@Column(name = "sale_prize", nullable = false)
	private String salePrize;

	@Column(name = "purchase_prize", nullable = false)
	private String purchasePrize;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getBike_id() {
		return bike_id;
	}

	public void setBike_id(UUID bike_id) {
		this.bike_id = bike_id;
	}

	public String getSalePrize() {
		return salePrize;
	}

	public void setSalePrize(String salePrize) {
		this.salePrize = salePrize;
	}

	public String getPurchasePrize() {
		return purchasePrize;
	}

	public void setPurchasePrize(String purchasePrize) {
		this.purchasePrize = purchasePrize;
	}

}
