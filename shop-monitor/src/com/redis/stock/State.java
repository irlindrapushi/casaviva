/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock;

import com.redis.casaviva.shop.dc.Stock;

/**
 *
 * @author user
 */
public class State {
	
	
	
	public static class Entry {
		private final Stock stock;
		private final Item item;
		private final Double weight;
		private final Double value;

		public Entry(Stock stock, Item item, Double weight, Double value) {
			this.stock = stock;
			this.item = item;
			this.weight = weight;
			this.value = value;
		}

		public Stock getStock() {
			return stock;
		}

		public Item getItem() {
			return item;
		}

		public Double getWeight() {
			return weight;
		}

		public Double getValue() {
			return value;
		}
	}
}
