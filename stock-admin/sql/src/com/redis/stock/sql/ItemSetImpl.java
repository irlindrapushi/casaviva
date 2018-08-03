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

import com.redis.stock.core.Item;
import com.redis.utils.sets.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

/**
 *
 * @author Redjan Shabani
 */
public class ItemSetImpl implements Set<Item> {

	@Override
	public void forEach(Consumer<Item> consumer) throws Exception {
		try(Connection connection = SQL.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT [uid], [code], [name], [unit] FROM [Item] ORDER BY [uid]");

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				consumer.accept(
					new ItemImpl(
						resultSet.getString("uid"), 
						resultSet.getString("code"), 
						resultSet.getString("name"), 
						resultSet.getString("unit")
					)
				);
			}
		}
	}
	
	



	
	
	

	
}
