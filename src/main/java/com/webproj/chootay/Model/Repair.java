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
@Table(name = "repair")
public class Repair {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID rs_id;

	@Column(name = "item", nullable = false) // reapired item
	private String item;

	@Column(name = "bill", nullable = false)
	private String bill;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cus_id")
	private Customers customer;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private SparePart sparepart;

	public UUID getRs_id() {
		return rs_id;
	}

	public void setRs_id(UUID rs_id) {
		this.rs_id = rs_id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
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

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public SparePart getSparepart() {
		return sparepart;
	}

	public void setSparepart(SparePart sparepart) {
		this.sparepart = sparepart;
	}

}
