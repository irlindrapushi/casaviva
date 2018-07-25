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
package com.redis.casaviva.shop.swing.labels;

import com.redis.casaviva.shop.core.Article;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Redjan Shabani
 */
public class TableModelLabels implements TableModel {
	
	private final Set<TableModelListener> listeners = new HashSet<>();
	
	private final List<Article> articles = new ArrayList();
	private final Map<Article, Boolean> checks = new HashMap<>();
	
	public void insert(Article article) {
		int idx = this.articles.size();
		this.articles.add(idx, article);
		this.checks.put(article, Boolean.FALSE);
		
		this.listeners.forEach( listener -> {
			listener.tableChanged(new TableModelEvent(this, idx, idx, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
		});
	}
	
	public void clear() {
		this.articles.clear();
		this.checks.clear();
	}
	
	public void check(int idx) {		
		this.setValueAt(!(Boolean) this.getValueAt(idx, 12), idx, 12);
	}
	
	
	

	@Override
	public int getRowCount() {
		return this.articles.size();
	}

	@Override
	public int getColumnCount() {
		return 13;
	}

	@Override
	public String getColumnName(int i) {
		switch(i) {
			case 0: return "Foto";
			case 1: return "Kodi";
			case 2: return "Barkodi";
			case 3: return "Sektori";
			case 4: return "Kategoria";
			case 5: return "Emertimi";
			case 6: return "Njesia";
			case 7: return "Sasia";
			case 8: return "Cmimi";
			case 9: return "Oferta";
			case 10: return "Skonto";
			case 11: return "Data/Ora";
			case 12: return "#";
			default: return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int i) {
		switch(i) {
			case 0: return Object.class;
			case 1: return String.class;
			case 2: return String.class;
			case 3: return String.class;
			case 4: return String.class;
			case 5: return String.class;
			case 6: return String.class;
			case 7: return Double.class;
			case 8: return Double.class;
			case 9: return Double.class;
			case 10: return Double.class;
			case 11: return Instant.class;
			case 12: return Boolean.class;
			default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int i, int i1) {
		switch(i) {
			case 12: return Boolean.TRUE;
			default: return Boolean.FALSE;
		}
	}

	@Override
	public Object getValueAt(int i, int i1) {
		Article article = this.articles.get(i);
		switch(i1) {
			case 0: return null;
			case 1: return article.getCode();
			case 2: return article.getBarcode();
			case 3: return article.getSector();
			case 4: return article.getCategory();
			case 5: return article.getDescription();
			case 6: return article.getUnit();
			case 7: return article.getQuantity();
			case 8: return article.getSellPrice();
			case 9: return article.getSellPriceTemp();
			case 10: return ( 1 - article.getSellPriceTemp() / article.getSellPrice());
			case 11: return article.getSellPriceTime();
			case 12: return this.checks.getOrDefault(article.getCode(), Boolean.FALSE);
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object o, int i, int i1) {
		Article article = this.articles.get(i);
		switch(i1) {
			case 12:
				if(o instanceof Boolean) {
					this.checks.put(article, (Boolean) o);
					break;
				}
			default: return;
		}
		
		this.listeners.forEach( listener -> {
			listener.tableChanged(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS));
		});
	}

	@Override
	public void addTableModelListener(TableModelListener tl) {
		this.listeners.add(tl);
	}

	@Override
	public void removeTableModelListener(TableModelListener tl) {
		this.listeners.remove(tl);
	}
	
}
