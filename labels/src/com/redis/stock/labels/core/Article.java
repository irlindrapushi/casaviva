/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.labels.core;

/**
 *
 * @author Redjan Shabani
 */
public class Article {
	private final String code, barcode, sector, category, description, supplier, unit;

	public Article(String code, String barcode, String sector, String category, String description, String supplier, String unit) {
		this.code = code;
		this.barcode = barcode;
		this.sector = sector;
		this.category = category;
		this.description = description;
		this.supplier = supplier;
		this.unit = unit;
	}

	public String getCode() {
		return code;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getSector() {
		return sector;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getSupplier() {
		return supplier;
	}

	public String getUnit() {
		return unit;
	}
}
