/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock;

import com.redis.utils.sets.Set;

/**
 *
 * @author Redjan Shabani
 */
public interface Stock {
	public String getCode();
	
	public Set<Item> getItems();
}
