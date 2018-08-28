/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.dc;

import com.redis.casaviva.shop.Main;
import com.redis.casaviva.shop.remote.sql.SqlWarehouse;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public abstract class Warehouse {
	
	private final String code;
	private final String name;

	public Warehouse(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public abstract List<Product> getProducts();

	
	
	
	public List<Product.SpecialLabel> getLabelList(){
		List<Product.SpecialLabel> labels = new ArrayList<>();
		
		this.getProducts().forEach( (Product p) -> {
			if(p.getNewPrice() > 1 && p.getNewPrice() < p.getOldPrice())
				labels.add(new Product.SpecialLabel(p));
		});
		
		labels.sort((Product.SpecialLabel o1, Product.SpecialLabel o2) -> o2.getInstant().compareTo(o1.getInstant()));
		
		return labels;
	}

	@Override
	public String toString() {
		return "[" + code + "] " + name;
	}
	
	
	

	
	public static Warehouse getInstance(){
		String code = Preferences.userNodeForPackage(Main.class).node("warehouse").get("code", "");
		System.out.println("Warehouse::getInstance -> code = " + code);
		return Warehouse.getInstance(code);
	}
	
	
	private static Warehouse getInstance(String code){
		return SqlWarehouse.read(code);
	}
}
