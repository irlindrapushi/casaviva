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

import com.redis.aza.stock.admin.core.Catalog;
import com.redis.aza.stock.admin.core.Item;
import com.redis.aza.stock.admin.core.catalog.Article;
import com.redis.aza.stock.admin.sql.catalog.ArticleSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Redjan Shabani
 */
public class CatalogSQL implements Catalog {
	
	
	
	
	@Override
	public Article getArticle(String code) {
		Article article = null;
		
		try {
			article = ArticleSQL.select(code);
		} 
		catch (SQLException ex) {
			Logger.getLogger(CatalogSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return article;
	}
	
	@Override
	public void forEach(Consumer<Article> consumer) {
		try {
			ArticleSQL.forEach(consumer);
		} 
		catch (SQLException ex) {
			Logger.getLogger(CatalogSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	private final Map<String, Entry> entries;
	
	public CatalogSQL() {
		this.entries = new HashMap<>();
	}
	
	
	
	
	
	
	
	
	
	@Override
	public String barcode(Item item) {
		return this.entries.get(item.getCode()).barcode;
	}

	@Override
	public Double buyPrice(Item item) {
		return this.entries.get(item.getCode()).buyPrice;
	}

	@Override
	public Double costPrice(Item item) {
		return this.entries.get(item.getCode()).costPrice;
	}

	@Override
	public Double sellPrice(Item item) {
		return this.entries.get(item.getCode()).sellPrice;
	}
		
	@Override
	public Stream<String> sectors() {return this.entries().map(Entry::getSector).distinct().sorted();}
	@Override
	public Stream<String> categories(String sector) {return this.entries(sector).map(Entry::getCategory).distinct().sorted();}
	
	
	
	@Override
	public Stream<Item> items() {return entries().map(Entry::toItem);}
	@Override
	public Stream<Item> items(String sector) {return entries(sector).map(Entry::toItem);}
	@Override
	public Stream<Item> items(String sector, String category) {return entries(sector, category).map(Entry::toItem);}

	@Override
	public Stream<Item> items(String... path) {
		switch (path.length) {
			case 0:
				return items();
			case 1:
				return items(path[0]);
			case 2:
				return items(path[0], path[1]);
			default:
				return new HashSet<Item>().stream();
		}
	}
	
	@Override
	public Stream<Item> items(Object... path) {
		switch (path.length) {
			case 1:
				return items();
			case 2:
				return items(String.valueOf(path[1]));
			case 3:
				return items(String.valueOf(path[1]), String.valueOf(path[2]));
			default:
				return new HashSet<Item>().stream();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Stream<Entry> entries() {		
		return this.entries.values().stream();
	}
	
	private Stream<Entry> entries(String sector) {		
		return this.entries.values().stream().filter(entry ->  Objects.equals(entry.sector, sector));
	}
	
	private Stream<Entry> entries(String sector, String category) {		
		return this.entries.values().stream().filter(entry ->  Objects.equals(entry.sector, sector) && Objects.equals(entry.category, category));
	}

	

	

	

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static class ItemImpl implements Item{
		
		private final String code;
		private final String name;
		private final String unit;

		public ItemImpl(String code, String name, String unit) {
			this.code = code;
			this.name = name;
			this.unit = unit;
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
		public String getUnit() {
			return unit;
		}

		@Override
		public int hashCode() {
			int hash = 3;
			hash = 71 * hash + Objects.hashCode(this.code);
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
			return Objects.equals(this.code, other.code);
		}
	}
	
	public static class Entry implements Item {
		
		private final String code;
		private final String name;
		private final String unit;
		
		private final String sector;
		private final String category;
		private final String supplier;
		private final String barcode;
		
		private final Double buyPrice;
		private final Double costPrice;
		private final Double sellPrice;

		public Entry(String code, String name, String unit, String sector, String category, String supplier, String barcode, Double buyPrice, Double costPrice, Double sellPrice) {
			this.code = code;
			this.name = name;
			this.unit = unit;
			this.sector = sector;
			this.category = category;
			this.supplier = supplier;
			this.barcode = barcode;
			this.buyPrice = buyPrice;
			this.costPrice = costPrice;
			this.sellPrice = sellPrice;
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
		public String getUnit() {
			return unit;
		}
		
		public String getSector() {
			return sector;
		}

		public String getCategory() {
			return category;
		}

		public String getSupplier() {
			return supplier;
		}

		public String getBarcode() {
			return barcode;
		}

		public Double getBuyPrice() {
			return buyPrice;
		}

		public Double getCostPrice() {
			return costPrice;
		}

		public Double getSellPrice() {
			return sellPrice;
		}	
		
		
		public Item toItem() {
			
			return new ItemImpl(code, name, unit);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Catalog getCatalog(){
		CatalogSQL catalog = new CatalogSQL();
		CatalogSQL.forEachEntry(entry -> {
			catalog.entries.put(entry.code, entry);
		});
		return catalog;
	}
	
	
	
	
	
	
	
		
	private static boolean forEachEntry(Consumer<Entry> consumer) {
		boolean success = false;
		
		try(Connection cn = SQL.getConnection()){
			PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT code, name, unit, weight, price, value, sector, category, supplier, barcode, buy_price, cost_price, sell_price, temp_sell_price, temp_sell_instant "
				+ "FROM catalog_entry "
				+ "ORDER BY code ASC");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				
				
				Entry item = new Entry(
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("unit"),
					rs.getString("sector"),
					rs.getString("category"),
					rs.getString("supplier"),
					rs.getString("barcode"),
					rs.getDouble("buy_price"),
					rs.getDouble("cost_price"),
					rs.getDouble("sell_price")
				);
				
				consumer.accept(item);
			}
			
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(CatalogSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return success;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean updateMinStock(String item, Float value) {
		boolean success = false;
		
		try(Connection cn = SQL.getConnection()){
			PreparedStatement ps = cn.prepareStatement("UPDATE StockMin SET value = ? WHERE item = ?");
			ps.setFloat(1, value);
			ps.setString(2, item);
			
			if(ps.executeUpdate() == 0){
				ps = cn.prepareStatement("INSERT INTO StockMin (item, value) VALUES (?,?)");
				ps.setString(1, item);
				ps.setFloat(2, value);
				ps.execute();
			}
			
			success = true;
		}
		catch (SQLException ex) {
			Logger.getLogger(CatalogSQL.class.getName()).log(Level.SEVERE, null, ex);
		}		
		
		return success;
	}
}


