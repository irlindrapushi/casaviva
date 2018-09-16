/*
 * Copyright (C) 2017 Redjan Shabani
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.redis.aza.stock.admin.core;

import com.redis.aza.stock.admin.utils.Dataset;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Redjan Shabani
 */
public interface Stock {
	
	public Dataset<String, Subject> suppliers();
	public Dataset<String, Subject> customer();
	public Dataset<String, ? extends Warehouse> warehouses();
	
	public Catalog getCatalog();
	
	
	public State getState();
	public State getState(Warehouse warehouse);
	
	
//	public boolean forEach(Consumer<Stock.Item> consumer);
	
	
	
	public static class Item extends HashMap<String, Float> {
		
		private final String code, barcode, description, unit;

		public Item(String code, String barcode, String description, String unit) {
			this.code = code;
			this.barcode = barcode;
			this.description = description;
			this.unit = unit;
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

		
		
		
		public Float getQuantity() {
			return ((Number)super.entrySet().stream().mapToDouble(Map.Entry::getValue).sum()).floatValue();
		}

		public Float getQuantity(String k) {
			return super.get(k);
		}
		
		public Float getQuantity(String... k) {
			Float value = 0.0f;
			for(String key : k)
				value += super.get(k);
			return value;
		}

		public Float setQuantity(String k, Float v) {
			return super.put(k, v);
		}
		
		
	}
}
