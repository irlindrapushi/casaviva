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

import com.redis.stock.core.Item;
import com.redis.stock.utils.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class ItemSQL implements Set<Item>{
	
	private final Connection connection;

	public ItemSQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void forEach(Consumer<Item> consumer) throws SQLException {
		String query = "SELECT uid, name, unit FROM Item ORDER BY uid ASC";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					consumer.accept(new ItemImpl(rs.getString("uid"), rs.getString("name"), rs.getString("unit")));
				}
			}
		}
	}
	
	
	
	private static class ItemImpl implements Item {
		private final String uid, name, unit;

		public ItemImpl(String uid, String name, String unit) {
			this.uid = uid;
			this.name = name;
			this.unit = unit;
		}

		@Override
		public String getUid() {
			return uid;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getUnit() {
			return unit;
		}

		@Override
		public int hashCode() {
			int hash = 3;
			hash = 79 * hash + Objects.hashCode(this.uid);
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
			final ItemImpl other = (ItemImpl) obj;
			return Objects.equals(this.uid, other.uid);
		}

		@Override
		public String toString() {
			return "[" + uid + "]" + name;
		}
	}
}
