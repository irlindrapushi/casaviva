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

import java.util.Date;
import java.util.function.Consumer;

/**
 *
 * @author Redjan Shabani
 */
public interface Catalog {
		
	public boolean forEach(Consumer<Item> consumer);
	
	public class Item {
		private final String code;
		private final String category;
		private final String description;
		private final String supplier;
		private final Float minStock;
		private final Float stock;
		private final Float cost;
		private final Float buyin;
		private final Float whoolsale;
		private final Float retail;
		private final Float special;
		private final Date lastSellout;

		public Item(String code, String category, String description, String supplier, Float minStock,Float stock, Float cost, Float buyin, Float whoolsale, Float retail, Float special, Date lastSellout) {
			this.code = code;
			this.category = category;
			this.description = description;
			this.supplier = supplier;
			this.minStock = minStock;
			this.stock = stock;
			this.cost = cost;
			this.buyin = buyin;
			this.whoolsale = whoolsale;
			this.retail = retail;
			this.special = special;
			this.lastSellout = lastSellout;
		}

		public String getCode() {
			return code;
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

		public Float getMinStock() {
			return minStock;
		}

		public Float getStock() {
			return stock;
		}

		public Float getCost() {
			return cost;
		}

		public Float getBuyin() {
			return buyin;
		}

		public Float getWhoolsale() {
			return whoolsale;
		}

		public Float getRetail() {
			return retail;
		}

		public Float getSpecial() {
			return special;
		}

		public Date getLastSellout() {
			return lastSellout;
		}	
		
	}
}
