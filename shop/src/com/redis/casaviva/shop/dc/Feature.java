/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.dc;

import com.redis.casaviva.shop.remote.SQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class Feature implements Comparable<Feature>{
	private final String code;
	private final String name;
	private final String value;

	public Feature(String code, String name, String value) {
		this.code = code;
		this.name = name;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return name + ": " + value;
	}
	
	@Override
	public int compareTo(Feature feature) {
		return (this.code + this.name).compareTo(feature.code + feature.name);
	}
	
	public static Set<Feature> read() {
		Set<Feature> features = new TreeSet<>();
		
		try(Connection conn = SQL.getConnection()){
			ResultSet rs = conn.prepareStatement("SELECT [item],[name],[value] FROM [Feature]").executeQuery();
			while(rs.next()) {
				features.add(new Feature(rs.getString("item"), rs.getString("name"), rs.getString("value")));
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(Feature.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return features;
	}
}
