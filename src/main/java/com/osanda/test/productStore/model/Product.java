package com.osanda.test.productStore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
@Table(name = "product", schema = "store_data")
public class Product extends BaseModel {

	private static final long serialVersionUID = 5985730429007872956L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 100)
	private String name;

	private Integer cartonUnits;

	private Double cartonPrice;

	private String image;

}
