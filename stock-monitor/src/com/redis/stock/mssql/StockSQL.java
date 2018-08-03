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
package com.redis.stock.mssql;

import com.redis.stock.core.Stock;
import com.redis.stock.utils.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 *
 * @author Redjan Shabani
 */
public class StockSQL implements Set<Stock>{
	
	private final Connection connection;

	public StockSQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void forEach(Consumer<Stock> consumer) throws SQLException {
		String query = "SELECT uid, name, unit FROM Item ORDER BY uid ASC";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					
				}
			}
		}
	}
	
}
