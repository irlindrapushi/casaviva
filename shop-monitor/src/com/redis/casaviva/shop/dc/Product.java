/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.dc;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public abstract class Product {

	private final String code, barcode, sector, category, description, unit;


	/**
	 * 
	 * @param code
	 * @param barcode
	 * @param sector
	 * @param category
	 * @param brand
	 * @param model
	 * @param description
	 * @param unit
	 * @param warranty
	 */
	public Product(String code, String barcode, String sector, String category, String description, String unit) {
		this.code = code;
		this.barcode = barcode;
		this.sector = sector;
		this.category = category;
		this.description = description;
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

	public String getUnit() {
		return unit;
	}

	public Double getPrice() {
		return getNewPrice() > 1 ? getNewPrice() : getOldPrice();
	}
	
	public abstract Double getNewPrice();
	
	public abstract Double getOldPrice();
	
	public Double getDiffPriceRatio(){
		return 1 - this.getNewPrice() / this.getOldPrice();
	}
	
	public abstract Instant getPriceInstant();

	public abstract Double getStock();
	
	public Map<String, Object> toMap(){
		
		
		String[] keys = FIELD_NAMES;
		Object[] values = this.toArray();
		
		Map<String, Object> map = new HashMap<>();
		
		for(int i=0; i<FIELD_COUNT; i++)
			map.put(keys[i], values[i]);
		
		
		return map;
	}
	
	public Object[] toArray(){
		return new Object[]{
			this.getCode(), this.getBarcode(), this.getSector(), this.getCategory(), this.getDescription(), this.getUnit(), this.getOldPrice(), this.getNewPrice(), this.getStock()
		};
	}
	
	
	
	
	
	public final static int FIELD_COUNT = 9;
	
	public final static String[] FIELD_NAMES = {
		"code", 
		"barcode", 
		"sector", 
		"category",
		"description", 
		"unit",
		"price",
		"offer",
		"stock"
	};
	
	public final static String[] FIELD_LABELS = {
		"Kodi", "Barkodi", "Sektori", "Kategoria", "Pershkrimi", "Njesia", "Cmimi", "Oferta", "Gjendja"
	};
	
	public final static Class[] FIELD_CLASSES = {
		String.class, 
		String.class, 
		String.class,
		String.class,
		String.class,
		String.class, 
		Double.class, 
		Double.class, 
		Double.class
	};
	
	public final static int[] FIELD_LENGTH = {
		8, 8, 16, 16, 64, 8, 8, 8, 8
	};
	
	
	
	
	
	
	public static class SpecialLabel{
		
		private final Product product;

		public SpecialLabel(Product product) {
			this.product = product;
		}

		public String getCode() {
			return product.getCode();
		}
		
		public String getBarcode() {
			return product.getBarcode();
		}

		public String getSector() {
			return product.getSector();
		}

		public String getCateogory() {
			return product.getCategory();
		}
		
		public String getDescription() {
			return product.getDescription();
		}

		public Double getNewPrice() {
			return product.getNewPrice();
		}

		public Double getOldPrice() {
			return product.getOldPrice();
		}

		public Instant getInstant() {
			return product.getPriceInstant();
		}
		
		public LocalDate getDate(){
			return product.getPriceInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		
		public LocalDateTime getDateTime(){
			return product.getPriceInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withSecond(0).withNano(0);
		}

		public Double getDiffPriceRatio() {
			return product.getDiffPriceRatio();
		}

		public Double getStock() {
			return product.getStock();
		}
		
		private boolean selected = false;
		public boolean isSelected(){
			return selected;
		}
		
		public void setSelected(boolean selected){
			this.selected = selected;
		}
		
		public Object[] toArray(){
			return new Object[]{
				this.getInstant(),
				this.getCode(),
				this.getBarcode(),
				this.getSector(),
				this.getDescription(),
				this.getCateogory(),
				this.getOldPrice(), 
				this.getNewPrice(), 
				this.getDiffPriceRatio(),
				this.getStock(),
				this.isSelected()
			};
		}
		
		
		public final static int FIELD_COUNT = 10;

		public final static String[] FIELD_LABELS = {
			"Publikimi", 
			"Kodi", 
			"Barkodi",
			"Sektori", 
			"Kategoria",
			"Pershkrimi",
			"Cmimi", 
			"Oferta", 
			"Skonto", 
			"Gjendje", 
			"",
		};

		public final static Class[] FIELD_CLASSES = {
			Instant.class, 
			String.class, 			
			String.class, 			
			String.class, 
			String.class,  
			String.class, 
			Double.class, 
			Double.class, 
			Double.class, 
			Double.class,
			Boolean.class
		};		
	}
	
	public static class PriceLabel extends HashMap<String, String>{
		private final Instant instant;
		private final String code, barcode, description;
		private final double price;
		private final double stock;

		public PriceLabel(Instant instant, String code, String barcode, String description, double price, double stock) {
			this.instant = instant;
			this.code = code;
			this.barcode = barcode;
			this.description = description;
			this.price = price;
			this.stock = stock;
		}

		public Instant getInstant() {return instant;}
		public LocalDateTime getDateTime() {return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());}
		public String getCode() {return code;}
		public String getBarcode() {return barcode;}
		public String getDescription() {return description;}
		public double getPrice() {return price;}
		public double getStock() {return stock;}
	}
}
