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
@Table(name = "customers")
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID cus_id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "sale_prize", nullable = false)
	private String salePrize;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

	public UUID getCus_id() {
		return cus_id;
	}

	public void setCus_id(UUID cus_id) {
		this.cus_id = cus_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalePrize() {
		return salePrize;
	}

	public void setSalePrize(String salePrize) {
		this.salePrize = salePrize;
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

}
