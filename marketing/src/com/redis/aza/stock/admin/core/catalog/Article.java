/*
 * Copyright (C) 2018 Redjan Shabani
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
package com.redis.aza.stock.admin.core.catalog;

import com.redis.aza.stock.admin.core.Item;
import com.redis.aza.stock.admin.core.Warehouse;
import java.time.Instant;

/**
 *
 * @author Redjan Shabani
 */
public interface Article extends Item {
	public String getSector();
	public String getCategory();
	public String getSupplier();
	public String getBarcode();
	public Double getBuyPrice();
	public Double getCostPrice();
	public Double getSellPrice();
	public Double getSellPriceDiscount();
	public Instant getSellPriceInstant();
	
	
	public State getState();
	
	
	public interface State {
		public Double getWeight();
		public Double getPrice();
		public Double getValue();
		
		public Double getWeight(Warehouse warehouse);
		public Double getPrice(Warehouse warehouse);
		public Double getValue(Warehouse warehouse);
	}
	
	
	
	
	
}
