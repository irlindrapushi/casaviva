/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.core;

import com.redis.utils.sets.Set;

/**
 *
 * @author Redjan Shabani
 */
public interface Stock extends Comparable<Stock> {
	public String getUid();
	public String getName();
	public String getLocation();
	
	public Set<Stock.State> getStates();
	
	public interface State {
		public Item getItem();
		public Double getWeight();
		public Double getValue();
	}
}

