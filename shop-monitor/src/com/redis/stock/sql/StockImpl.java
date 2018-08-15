/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.sql;

import com.redis.stock.StockState;
import com.redis.stock.Stock;

/**
 *
 * @author Redjan Shabani
 */
public class StockImpl implements Stock{
	
	private final String code;

	public StockImpl(String code) {
		this.code = code;
	}
	

	@Override
	public String getCode() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public StockState getState() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	
}
