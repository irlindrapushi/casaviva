/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.sql;

import com.redis.stock.core.Item;
import com.redis.stock.core.Stock;
import com.redis.utils.sets.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class StockImpl implements Stock {
	
	private final String uid, name, location;

	public StockImpl(String uid, String name, String location) {
		this.uid = uid;
		this.name = name;
		this.location = location;
	}

	@Override
	public String getUid() {return uid;}

	@Override
	public String getName() {return name;}

	@Override
	public String getLocation() {return location;}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + Objects.hashCode(this.uid);
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
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final StockImpl other = (StockImpl) obj;
		return Objects.equals(this.uid, other.uid);
	}

	@Override
	public String toString() {return "[" + this.uid + "] " + this.name;}
	
	@Override
	public int compareTo(Stock stock) {return this.getUid().compareTo(stock.getUid());}	

//	@Override
//	public State getState() {
//		StateImpl stateImpl = new StateImpl();
//		
//		try(Connection connection = SQL.getConnection()) {
//			PreparedStatement preparedStatement = connection.prepareStatement("SELECT [item], [weight], [value] FROM [State] WHERE [stock] = ?");
//			preparedStatement.setString(1, this.uid);
//			
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			while(resultSet.next()) {
//				stateImpl.weights.put(resultSet.getString("item"), resultSet.getDouble("weight"));
//				stateImpl.values.put(resultSet.getString("item"), resultSet.getDouble("value"));
//			}
//		}
//		catch (SQLException ex) {
//			Logger.getLogger(StockImpl.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		
//		return stateImpl;
//	}

	@Override
	public Set<State> getStates() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	
	
	
	
	
	
	
	private static class StateImpl implements Stock.State {
		
		public final Map<String, Double> weights = new HashMap<>();
		
		public final Map<String, Double> values = new HashMap<>();

		@Override
		public Double getWeight(Item item) {
			return this.weights.get(item.getUid());
		}

		@Override
		public Double getValue(Item item) {
			return this.values.get(item.getUid());
		}

		@Override
		public Item getItem() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Double getWeight() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Double getValue() {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}
}
