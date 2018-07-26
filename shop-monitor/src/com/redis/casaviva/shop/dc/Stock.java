/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.dc;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class Stock {
	private final String code, barcode, description;
	private final Map<String,Double> quantities;

	public Stock(String code, String barcode, String description) {
		this.code = code;
		this.barcode = barcode;
		this.description = description;
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
			this.quantities.getOrDefault("M1", 0.0d),
			this.quantities.getOrDefault("M5", 0.0d),
			this.quantities.getOrDefault("M6", 0.0d),
			this.quantities.getOrDefault("M7", 0.0d),
			this.quantities.getOrDefault("M8", 0.0d),
			this.quantities.getOrDefault("M9", 0.0d),
			this.quantities.getOrDefault("M10", 0.0d),
			this.quantities.getOrDefault("M11", 0.0d),
			this.quantities.getOrDefault("M12", 0.0d),
			this.quantities.getOrDefault("M13", 0.0d),
			this.quantities.getOrDefault("M14", 0.0d),
			this.quantities.getOrDefault("M15", 0.0d),
			this.quantities.getOrDefault("M16", 0.0d),
			this.quantities.getOrDefault("M0", 0.0d)
		};
	}
	
	
	
	
}
