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

package com.redis.aza.stock.admin.core;

import com.redis.aza.stock.admin.core.catalog.Article;
import java.time.Instant;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author Redjan Shabani
 */
public interface Catalog {
	
	public Article getArticle(String code);
	public void forEach(Consumer<Article> consumer);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String barcode(Item item);
	
	public Double buyPrice(Item item);
	public Double costPrice(Item item);
	public Double sellPrice(Item item);
	
	
	
	
	
	
	
	
	public Stream<String> sectors();
	public Stream<String> categories(String sector);
	
	public Stream<Item> items();
	public Stream<Item> items(String sector);
	public Stream<Item> items(String sector, String category);
	public Stream<Item> items(String... path);
	
	public Stream<Item> items(Object... path);
	
	
	
	
	
	
	
	public interface Node {
		public Stream<? extends Node> childs();
		public Stream<? extends Article> articles();
	}
	
	
		
	public interface Price {
		public Article parent();
		public Double value();
		public Double discount();
		public Instant instant();
	}
	
}
