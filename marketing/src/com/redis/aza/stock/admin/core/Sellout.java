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

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Redjan Shabani
 */
public interface Sellout {
	
	public List<Story> getStories();
	public List<Story> getStories(Date minDate, Date maxDate);
	public List<Event> getEvents(String itemCode);
	
	public State getState();
	
	public interface State {
//		private final Map<String, Double> weights = new HashMap<>();
//		private final Map<String, Double> values = new HashMap<>();
//		private final Map<String, Instant> firstEvent = new HashMap<>();
//		private final Map<String, Instant> lastEvent = new HashMap<>();
		
		
		
		public Double getWeight();
		public Double getValue();
		
		public Double getWeight(Item item);
		public Double getValue(Item item);
		public Instant getFirstEvent(Item item);
		public Instant getLastEvent(Item item);
	}
	
	
	
	public class Story {
		private final String code, description, unit;
		private final Float sumQuantity, sumValue;
		private final Date minDate, maxDate;

		public Story(String code, String description, String unit, Float sumQuantity, Float sumValue, Date minDate, Date maxDate) {
			this.code = code;
			this.description = description;
			this.unit = unit;
			this.sumQuantity = sumQuantity;
			this.sumValue = sumValue;
			this.minDate = minDate;
			this.maxDate = maxDate;
		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		public String getUnit() {
			return unit;
		}

		public Float getSumQuantity() {
			return sumQuantity;
		}
		
		public Float getAvgPrice() {
			return sumQuantity == 0 ? Float.NaN : this.sumValue / this.sumQuantity;
		}

		public Float getSumValue() {
			return sumValue;
		}

		public Date getMinDate() {
			return minDate;
		}

		public Date getMaxDate() {
			return maxDate;
		}
	}
	
	public class Event  {	
		private final Date date;
		private final String warehouse, client;
		private final String code, description, unit;
		private final Float quantity, price, value;

		public Event(Date date, String warehouse, String client, String code, String description, String unit, Float quantity, Float price, Float value) {
			this.date = date;
			this.warehouse = warehouse;
			this.client = client;
			this.code = code;
			this.description = description;
			this.unit = unit;
			this.quantity = quantity;
			this.price = price;
			this.value = value;
		}

		public Date getDate() {
			return date;
		}

		public String getWarehouse() {
			return warehouse;
		}

		public String getClient() {
			return client;
		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		public String getUnit() {
			return unit;
		}

		public Float getQuantity() {
			return quantity;
		}

		public Float getPrice() {
			return price;
		}
		
		public Float getValue() {
			return value;
		}
	}
	
	public static class DateSellout {
		public final Date date;
		public final Float value;

		public DateSellout(Date date, Float value) {
			this.date = date;
			this.value = value;
		}
		
	}
}
