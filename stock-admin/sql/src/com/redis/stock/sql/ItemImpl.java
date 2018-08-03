/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.sql;

import com.redis.stock.core.Item;

/**
 *
 * @author Redjan Shabani
 */
public class ItemImpl implements Item {
	private final String uid, code, name, unit;

	public ItemImpl(String uid, String code, String name, String unit) {
		this.uid = uid;
		this.code = code;
		this.name = name;
		this.unit = unit;
	}

	@Override
	public String getUid() {
		return uid;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUnit() {
		return unit;
	}
}
