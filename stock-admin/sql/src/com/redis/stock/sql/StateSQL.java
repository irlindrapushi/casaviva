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
package com.redis.stock.sql;

import com.redis.utils.sets.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

/**
 *
 * @author Redjan Shabani
 */
public class StateSQL {
	
	public static void getState() {
		
	}
	
	private static class Entry implements Comparable<Entry>{
		public final String stock;
		public final String item;
		public final Double weight;
		public final Double value;

		public Entry(String stock, String item, Double weight, Double value) {
			this.stock = stock;
			this.item = item;
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Entry t) {
			return (stock + item).compareTo(t.stock + t.item);
		}
	}
	
	private static class EntrySet implements Set<Entry> {

		@Override
		public void forEach(Consumer<Entry> consumer) throws Exception {
			try(Connection connection = SQL.getConnection()) {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT [stock], [item], [weight], [value] FROM [State]");

				ResultSet resultSet = preparedStatement.executeQuery();

				while(resultSet.next()) {
					Entry entry = new Entry(
						resultSet.getString("stock"), 
						resultSet.getString("item"), 
						resultSet.getDouble("weight"), 
						resultSet.getDouble("value")
					);

					consumer.accept(entry);
				}
			}	
		}
		
	}
}
