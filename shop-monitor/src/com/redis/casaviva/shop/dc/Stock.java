/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.dc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class Stock {
	private final String code, barcode, description, unit;
	private final Map<String,Double> quantities;

	public Stock(String code, String barcode, String description, String unit) {
		this.code = code;
		this.barcode = barcode;
		this.description = description;
		this.unit = unit;
		this.quantities = new HashMap<>();
	}

	public String getCode() {
		return code;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getDescription() {
		return description;
	}

	public String getUnit() {
		return unit;
	}
	
	public Double get(String key) {
		return quantities.get(key);
	}

	public Double put(String key, Double value) {
		return quantities.put(key, value);
	}

	public Object[] toArray() {
		return new Object[]{
			this.code, 
			this.barcode, 
			this.description,
			this.unit,
			this.quantities.getOrDefault("M01", 0.0d),
			this.quantities.getOrDefault("M02", 0.0d),
			this.quantities.values().stream().mapToDouble(Double::valueOf).sum()
		};
	}
	
	
	
	
}
