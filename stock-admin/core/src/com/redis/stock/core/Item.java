/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.core;

/**
 *
 * @author Redjan Shabani
 */
public interface Item {	
	public String getUid();	
	public String getCode();	
	public String getName();	
	public String getUnit();
}
