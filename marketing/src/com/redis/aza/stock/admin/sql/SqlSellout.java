/*
 * Copyright (C) 2017 Redjan Shabani
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

package com.redis.aza.stock.admin.sql;

import com.redis.aza.stock.admin.core.Item;
import com.redis.aza.stock.admin.core.Sellout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class SqlSellout implements Sellout {

	public static Sellout getSellout() {
		return new SqlSellout();
	}
	
	
	public class StateImpl implements Sellout.State {
		
		Map<String, Double> weights = new HashMap<>();
		Map<String, Double> values = new HashMap<>();
		Map<String, Instant> firstEvent = new HashMap<>();
		Map<String, Instant> lastEvent = new HashMap<>();

		@Override
		public Double getWeight(Item item) {
			return weights.getOrDefault(item.getCode(), 0.0d);
		}

		@Override
		public Double getValue(Item item) {
			return this.values.getOrDefault(item.getCode(), 0.0d);
		}

		@Override
		public Instant getFirstEvent(Item item) {
			return this.firstEvent.get(item.getCode());
		}

		@Override
		public Instant getLastEvent(Item item) {
			return this.lastEvent.get(item.getCode());
		}
		
		public void add(String item, Double weight, Double value, Instant first, Instant last) {
			this.weights.merge(item, weight, Double::sum);
			this.values.merge(item, value, Double::sum);
			
			if(first.isBefore(this.firstEvent.getOrDefault(item, Instant.MAX)))
				this.firstEvent.put(item, first);
			
			if(last.isAfter(this.lastEvent.getOrDefault(item, Instant.MIN)))
				this.lastEvent.put(item, first);
		}

		@Override
		public Double getWeight() {
			return this.weights.entrySet().stream().mapToDouble(Map.Entry::getValue).sum();
		}

		@Override
		public Double getValue() {
			return this.values.entrySet().stream().mapToDouble(Map.Entry::getValue).sum();
		}
	}
	
	@Override
	public State getState() {
		StateImpl stateImpl = new StateImpl();
		
		try(Connection cn = SQL.getConnection()) {
			String sql = ""
				+ "SELECT [item], [weight], [value], [vatValue], [totValue], [firstEvent], [lastEvent] "
				+ "FROM [sellout_state]";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				stateImpl.add(
					rs.getString("item"), 
					rs.getDouble("weight"), 
					rs.getDouble("totValue"), 
					rs.getTimestamp("firstEvent") == null ? null : rs.getTimestamp("firstEvent").toInstant(),
					rs.getTimestamp("lastEvent") == null ? null : rs.getTimestamp("lastEvent").toInstant()
				);
				
			}			
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlSellout.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stateImpl;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<Story> getStories() {
		List<Story> stories = new ArrayList();
		
		try(Connection cn = SQL.getConnection()) {
			String sql = ""
				+ "SELECT [ss].[code], [ss].[description], [ss].[unit], [ss].[sumQuantity], [ss].[sumValue], [ss].[minDate], [ss].[maxDate] "
				+ "FROM [SelloutStory] [ss]"
				+ "ORDER BY [ss].[code] ASC";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Story story = new Story(
					rs.getString("code"), 
					rs.getString("description"), 
					rs.getString("unit"), 
					rs.getFloat("sumQuantity"), 
					rs.getFloat("sumValue"),
					rs.getDate("minDate"),
					rs.getDate("maxDate")
				);
				
				stories.add(story);
			}			
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlSellout.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stories;
	}
	
	@Override
	public List<Story> getStories(Date minDate, Date maxDate) {
		List<Story> stories = new ArrayList();
		
		try(Connection cn = SQL.getConnection()) {
			String sql = ""
				+ "SELECT i.code, i.description, i.unit, SUM(s.quantity) AS sumQuantity, SUM(s.totValue) AS sumValue, MIN(s.date) AS minDate, MAX(s.date) AS maxDate "
				+ "FROM Item AS i INNER JOIN Sellout AS s ON i.code = s.item "
				+ "WHERE date >= ? AND date <= ? "
				+ "GROUP BY i.code, i.description, i.unit "
				+ "order by i.code asc";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(minDate.getTime()));
			ps.setDate(2, new java.sql.Date(maxDate.getTime()));
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Story story = new Story(
					rs.getString("code"), 
					rs.getString("description"), 
					rs.getString("unit"), 
					rs.getFloat("sumQuantity"), 
					rs.getFloat("sumValue"),
					rs.getDate("minDate"),
					rs.getDate("maxDate")
				);
				
				stories.add(story);
			}			
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlSellout.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stories;
	}
	
	@Override
	public List<Event> getEvents(String itemCode) {
		List<Event> events = new ArrayList();
		
		try(Connection cn = SQL.getConnection()) {
			String sql = ""
				+ "SELECT s.date, s.warehouse, s.client, i.code, i.description, i.unit, s.quantity, s.totValue / s.quantity AS price, s.totValue AS value "
				+ "FROM Sellout s LEFT JOIN Item i ON s.item = i.code "
				+ "WHERE s.item = ? AND s.quantity <> 0 "
				+ "ORDER BY s.date DESC";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, itemCode);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Event event = new Event(
					rs.getDate("date"),
					rs.getString("warehouse"), 
					rs.getString("client"),
					rs.getString("code"), 
					rs.getString("description"), 
					rs.getString("unit"), 
					rs.getFloat("quantity"), 
					rs.getFloat("price"),
					rs.getFloat("value")
				);
				events.add(event);
			}
			
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlSellout.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return events;
	}	
	
	
	public static List<Sellout.DateSellout> select(Date minDate, Date maxDate) {
		List<Sellout.DateSellout> dateSellouts = new ArrayList();
		
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT [date], SUM([totValue]) AS value "
				+ "FROM [Sellout] "
				+ "WHERE [date] >= ?  AND [date] <= ? "
				+ "GROUP BY [date] "
				+ "ORDER BY [date] ASC");
			ps.setTimestamp(1, Timestamp.from(minDate.toInstant()));
			ps.setTimestamp(2, Timestamp.from(maxDate.toInstant()));
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				dateSellouts.add(new DateSellout(rs.getDate("date"), rs.getFloat("value")));
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlSellout.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return dateSellouts;
	}

	
}
