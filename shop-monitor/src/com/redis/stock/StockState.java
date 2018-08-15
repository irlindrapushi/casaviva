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
public interface State {
	public Stock getStock();
	public Item getItem();
	public Double getWeight();
	public Double getPrice();
	public Double getValue();
}
