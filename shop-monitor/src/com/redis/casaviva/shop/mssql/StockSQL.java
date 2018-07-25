/*
 * Copyright (C) 2018 user
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
package com.redis.casaviva.shop.mssql;

import com.redis.casaviva.shop.core.Article;
import com.redis.casaviva.shop.core.Stock;
import com.redis.utils.Dataset;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;

/**
 *
 * @author user
 */
public class StockSQL implements Stock {
	
	private final String code, name, address, phone, email;

	public StockSQL(String code, String name, String address, String phone, String email) {
		this.code = code;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + Objects.hashCode(this.code);
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
		final StockSQL other = (StockSQL) obj;
		return Objects.equals(this.code, other.code);
	}
	
	@Override
	public String toString() {
		return "[" + this.code + "] " + this.name;
	}
	
	
	@Override
	public Dataset<Article> getArticles() {
		
		return new Dataset<Article>() {
			@Override
			public void forEach(Consumer<Article> consumer) {
				String query = "EXECUTE [readStockArticles] ?";
				try(Connection cn = SQL.getConnection()) {
					CallableStatement cs = cn.prepareCall(query);
					cs.setString(1, StockSQL.this.code);

					ResultSet rs = cs.executeQuery();

					while(rs.next()) {
						System.out.println(rs.getString("sellPriceTime"));
						System.out.println(rs.getTimestamp("sellPriceTime"));
						Article art = new ArticleSQL(
							rs.getString("code"), 
							rs.getString("barcode"), 
							rs.getString("sector"), 
							rs.getString("category"), 
							rs.getString("description"), 
							rs.getString("unit"), 
							rs.getDouble("quantity"),
							rs.getDouble("sellPrice"), 
							rs.getDouble("sellPriceTemp"), 
							rs.getTimestamp("sellPriceTime") == null ? null : rs.getTimestamp("sellPriceTime").toInstant()
						);

						consumer.accept(art);
						
						System.out.println(art);
					}
				} 
				catch (SQLException ex) {
					Logger.getLogger(ArticleSQL.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		};
		
		
		
	}
	
	
	
	
	
	
	
	
	
	private static final String SQL_SELECT = "SELECT TOP 1 code, name, address, phone, email FROM Stock WHERE code = ?";
	public static Stock select(String code) {
		
		Stock stock = null;
		
		try(Connection cn = SQL.getConnection()) {
			
			PreparedStatement ps = cn.prepareStatement(SQL_SELECT);
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				stock = new StockSQL(
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("address"), 
					rs.getString("phone"), 
					rs.getString("email")
				);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(StockSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stock;
	}	

	

	
}
