package com.product.Product.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {
	
	@Id
	private String id;
	private String product;
	private int price;
	private String desc;

	private LocalDateTime createdAt ;
	private String createdBy;
	private LocalDateTime updatedAt ;
	private String updatedBy;

	public Product(String id,
				   String product,
				   int price,
				   String desc,
				   LocalDateTime createdAt,
				   String createdBy,
				   LocalDateTime updatedAt, String updatedBy) {
		this.id = id;
		this.product = product;
		this.price = price;
		this.desc = desc;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime createdAt()
	{
		return this.createdAt = LocalDateTime.now();
	}

	public LocalDateTime updatedAt()
	{
		return this.updatedAt = LocalDateTime.now();
	}
}
