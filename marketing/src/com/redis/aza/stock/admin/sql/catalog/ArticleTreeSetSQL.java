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
package com.redis.aza.stock.admin.sql.catalog;

import com.redis.aza.stock.admin.core.catalog.Article;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author Redjan Shabani
 */
public class ArticleTreeSetSQL extends HashSet<Article>{
	
	private final Set<? extends Article> articles;

	public ArticleTreeSetSQL() {
		this.articles = new HashSet<>();
	}
	
	public Stream<? extends Article> articles() {
		return super.stream();
	}
	
	public Stream<? extends Article> articles(String sector) {
		return articles().filter(article -> sector.equals(article.getSector()));
	}
	
	public Stream<? extends Article> articles(String sector, String category) {
		return articles(sector).filter(article -> category.equals(article.getCategory()));
	}
	
//	public Catalog.Node getRoot() {
//		return new RootNodeSQL();
//	}
//	
//	
//	
//	
//	
//	
//	private class RootNodeSQL implements Catalog.Node{
//
//		public RootNodeSQL() {
//			
//		}
//		
//		@Override
//		public Stream<? extends Catalog.Node> childs() {
//			return this.articles().map(Article::getSector).distinct().sorted().map(SectorNodeSQL::new);
//		}
//		
//		@Override
//		public Stream<? extends Article> articles() {
//			return ArticleTreeSetSQL.this.articles();
//		}
//	}
//	
//	
//	
//	
//	private class SectorNodeSQL implements Catalog.Node {
//		
//		private final String sector;
//
//		public SectorNodeSQL(String sector) {
//			this.sector = sector;
//		}
//		
//		@Override
//		public Stream<? extends Catalog.Node> childs() {
//			return this.articles().map(Article::getCategory).distinct().sorted().map(CategoryNodeSQL::new);
//		}
//		
//		@Override
//		public Stream<? extends Article> articles() {
//			return ArticleTreeSetSQL.this.articles(sector);
//		}
//	}
//	
//	private class CategoryNodeSQL implements Catalog.Node{
//		
//		private final String sector;
//		private final String category;
//
//		public CategoryNodeSQL(String sector, String category) {
//			this.sector = sector;
//			this.category = category;
//		}
//		
//		@Override
//		public Stream<? extends Catalog.Node> childs() {
//			return new HashSet<Catalog.Node>().stream();
//		}
//		
//		@Override
//		public Stream<? extends Article> articles() {
//			return ArticleTreeSetSQL.this.articles(sector, category);
//		}
//	}
}
