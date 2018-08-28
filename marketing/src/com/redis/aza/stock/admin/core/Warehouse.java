/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redis.aza.stock.admin.core;

/**
 *
 * @author Redjan Shabani
 */
public class Warehouse {
	private final String code, name, address, phone, email;

	public Warehouse(String code, String name, String address, String phone, String email) {
		this.code = code;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public String getCode() {return code;}
	public String getName() {return name;}
	public String getAddress() {return address;}
	public String getPhone() {return phone;}
	public String getEmail() {return email;}

	@Override
	public String toString() {return code;
	}
}