/*
 * Copyright (C) 2018 Redjan Shabani
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.redis.aza.stock.admin.sql.state;

import com.redis.aza.stock.admin.core.Item;
import com.redis.aza.stock.admin.core.State;
import com.redis.aza.stock.admin.sql.SQL;
import com.redis.aza.stock.admin.utils.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class SqlState implements State {
	
	
	
	private final Map<String, Double> itemWeight;
	private final Map<String, Double> itemValues;
	
	public final Map<String, Double> itemMinWeight;
	
	public SqlState(Map<String, Double> itemWeight, Map<String, Double> itemValues, Map<String, Double> itemMinWeight) {
		this.itemWeight = itemWeight;
		this.itemValues = itemValues;
		
		this.itemMinWeight = itemMinWeight;
	}
	

	public SqlState() {
		this.itemValues = new HashMap<>();
		this.itemWeight = new HashMap<>();
		this.itemMinWeight = new HashMap<>();
	}
	
	public void add(String item, Double weight, Double value) {
		this.itemWeight.merge(item, weight, Double::sum);
		this.itemValues.merge(item, value, Double::sum);
	}
	
	@Override
	public Double getWeight(Item item) {
		return this.itemWeight.getOrDefault(item.getCode(), 0.0d);
	}

	@Override
	public Double getPrice(Item item) {
		return this.getValue(item) / this.getWeight(item);
	}

	@Override
	public Double getValue(Item item) {
		return this.itemWeight.getOrDefault(item.getCode(), 0.0d);
	}

	@Override
	public Object setMinWeight(Item item, Double weight) {
		String query = "UPDATE StockMin SET [weight] = ? WHERE [item] = ?";
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(query);
			
			ps.execute();
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlState.class.getName()).log(Level.SEVERE, null, ex);
		}
		return this.itemMinWeight.put(item.getCode(), weight);
	}

	@Override
	public Object getMinWeight(Item item) {
		return this.itemMinWeight.getOrDefault(item.getCode(), 0.0d);
	}
	
	private static void forEachMinWeight(BiConsumer<String, Double> consumer) {
		String query = "SELECT [item], [value] FROM StockMin";
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				consumer.accept(rs.getString("item"), rs.getDouble("value"));
			}
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlState.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
	public static class Entry {
		public final String item;
		public final Double weight;
		public final Double value;
		
		
		private Double minWeight;
		
		public Entry(String item, Double weight, Double value) {
			this.item = item;
			this.weight = weight;
			this.value = value;
		}
		
		public String getItem() {return item;}

		public Double getWeight() {return weight;}

		public Double getValue() {return value;}

		public Double getMinWeight() {
			return minWeight;
		}
	}
	
	
	
	
	
	
	
	
	
	private static Set<Entry> entries() {
		return (Consumer<Entry> consumer) -> {
			String query = "SELECT [item], SUM([weight]) AS [weight], SUM([value]) AS [value] FROM [Event] WHERE [instant] <= GETDATE() GROUP BY [item]";
			try(Connection cn = SQL.getConnection()) {
				PreparedStatement ps = cn.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Entry entry = new Entry(
						rs.getString("item"), 
						rs.getDouble("weight"), 
						rs.getDouble("value")
					);
					
					consumer.accept(entry);
				}
			}
			catch (SQLException ex) {
				Logger.getLogger(SqlState.class.getName()).log(Level.SEVERE, null, ex);
			}
		};
	}
	
	private static Set<Entry> entries(Instant instant) {
		return (Consumer<Entry> consumer) -> {
			String query = "SELECT [item], SUM([weight]) AS [weight], SUM([value]) AS [value] FROM [Event] WHERE ([instant] <= ? GROUP BY [item]";
			try(Connection cn = SQL.getConnection()) {
				PreparedStatement ps = cn.prepareStatement(query);
				
				ps.setTimestamp(1, java.sql.Timestamp.from(instant));
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Entry entry = new Entry(
						rs.getString("item"), 
						rs.getDouble("weight"), 
						rs.getDouble("value")
					);
					
					consumer.accept(entry);
				}
			}
			catch (SQLException ex) {
				Logger.getLogger(SqlState.class.getName()).log(Level.SEVERE, null, ex);
			}
		};
	}
	
	private static Set<Entry> entries(String stock) {
		return (Consumer<Entry> consumer) -> {
			String query = "SELECT [item], SUM([weight]) AS [weight], SUM([value]) AS [value] FROM [Event] WHERE [instant] <= GETDATE() AND [stock] = ?  GROUP BY [item]";
			try(Connection cn = SQL.getConnection()) {
				PreparedStatement ps = cn.prepareStatement(query);
				ps.setString(1, stock);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Entry entry = new Entry(
						rs.getString("item"), 
						rs.getDouble("weight"), 
						rs.getDouble("value")
					);
					
					consumer.accept(entry);
				}
			}
			catch (SQLException ex) {
				Logger.getLogger(SqlState.class.getName()).log(Level.SEVERE, null, ex);
			}
		};
	}
	
	private static Set<Entry> entries(Instant instant, String stock) {
		return (Consumer<Entry> consumer) -> {
			String query = "SELECT [item], SUM([weight]) AS [weight], SUM([value]) AS [value] FROM [Event] WHERE ([instant] <= ? AND [stock] = ? GROUP BY [item]";
			try(Connection cn = SQL.getConnection()) {
				PreparedStatement ps = cn.prepareStatement(query);
				
				ps.setTimestamp(1, java.sql.Timestamp.from(instant));
				ps.setString(2, stock);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Entry entry = new Entry(
						rs.getString("item"), 
						rs.getDouble("weight"), 
						rs.getDouble("value")
					);
					
					consumer.accept(entry);
				}
			}
			catch (SQLException ex) {
				Logger.getLogger(SqlState.class.getName()).log(Level.SEVERE, null, ex);
			}
		};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static State getState() {
		SqlState stateSQL = new SqlState();
		SqlState.entries().forEach(entry -> {
			stateSQL.add(entry.item, entry.weight , entry.value);
		});
		
		forEachMinWeight((item, value) -> stateSQL.itemMinWeight.put(item, value));
		
		return stateSQL;
	}
	
	public static State getState(Instant instant) {
		SqlState stateSQL = new SqlState();
		SqlState.entries(instant).forEach(entry -> {
			stateSQL.add(entry.item, entry.weight , entry.value);
		});
		return stateSQL;
	}
	
	public static State getState(String stock) {
		SqlState stateSQL = new SqlState();
		SqlState.entries(stock).forEach(entry -> {
			stateSQL.add(entry.item, entry.weight , entry.value);
		});
		return stateSQL;
	}
}
