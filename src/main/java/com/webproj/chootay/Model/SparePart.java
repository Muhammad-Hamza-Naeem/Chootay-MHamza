package com.webproj.chootay.Model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "sparepart")
public class SparePart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "partname", nullable = false)
	private String partName;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "partnumber", nullable = false)
	private int partNumber;

	@Column(name = "sale_prize", nullable = false)
	private int salePrize;

	@Column(name = "purchase_prize", nullable = false)
	private int purchasePrize;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdate", nullable = false)
	private Date cDate;

	@Column(name = "updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public int getSalePrize() {
		return salePrize;
	}

	public void setSalePrize(int salePrize) {
		this.salePrize = salePrize;
	}

	public int getPurchasePrize() {
		return purchasePrize;
	}

	public void setPurchasePrize(int purchasePrize) {
		this.purchasePrize = purchasePrize;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
