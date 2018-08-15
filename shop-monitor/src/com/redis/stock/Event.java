/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock;

import java.time.Instant;

/**
 *
 * @author Redjan Shabani
 */
public interface Event {
	public Instant getInstant();
	public Stock getStock();
	public Item getItem();
	public Double getWeight();
	public Double getPrice();
	public Double getValue();
}
