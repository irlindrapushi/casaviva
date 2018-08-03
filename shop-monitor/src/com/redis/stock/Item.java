/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Item {
	private final String code, name, unit;

	public Item(String code, String name, String unit) {
		this.code = code;
		this.name = name;
		this.unit = unit;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + Objects.hashCode(this.code);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Item other = (Item) obj;
		return Objects.equals(this.code, other.code);
	}

	@Override
	public String toString() {
		return "[" + this.code + "] " + this.name;
	}
}
