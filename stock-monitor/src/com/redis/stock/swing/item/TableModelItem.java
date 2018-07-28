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
package com.redis.stock.swing.item;

import com.redis.stock.core.Item;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Redjan Shabani
 */
public class TableModelItem implements TableModel {
	
	private final List<Item> items;
	private final List<TableModelListener> listeners;
	
	public TableModelItem() {
		this.items = new ArrayList<>();
		this.listeners = new ArrayList<>();
	}
	
	public void insert(Item item) {
		int idx = this.items.size();
		this.items.add(idx, item);
		
		this.listeners.forEach( listener -> {listener.tableChanged(new TableModelEvent(this, idx, idx, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));});
	}
	
	public void clear() {
		this.items.clear();
		
		this.listeners.forEach(listener -> {listener.tableChanged(new TableModelEvent(this));});
	}

	@Override
	public int getRowCount() {
		return this.items.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int i) {
		switch(i) {
			case 0: return "Kodi";
			case 1: return "Emertimi";
			case 2: return "Njesia";
			default: return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int i) {
		switch(i) {
			case 0: return String.class;
			case 1: return String.class;
			case 2: return String.class;
			default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int i, int i1) {
		switch(i) {
			default: return Boolean.FALSE;
		}
	}

	@Override
	public Object getValueAt(int i, int i1) {
		switch(i1) {
			case 0: return this.items.get(i).getUid();
			case 1: return this.items.get(i).getName();
			case 2: return this.items.get(i).getUnit();
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object o, int i, int i1) {
		switch(i1) {
			default: return;
		}
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
