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
package com.redis.stock.admin.swing.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import com.redis.stock.core.State;

/**
 *
 * @author Redjan Shabani
 */
public class TableModelStockState implements TableModel {
	
	private final List<State> states;

	public TableModelStockState() {
		this.states = new ArrayList<>();
	}

	public void add(State e) {
		states.add(e);
	}
	
	public void tableChanged() {
		this.listeners.forEach(l -> l.tableChanged(new TableModelEvent(this)));
	}
	

	@Override
	public int getRowCount() {
		return this.states.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	private static final String[] COL_NAMES = {"Kodi", "Barkodi", "Emertimi", "Njesia", "Sasia", "Cmimi", "Vlera"};
	@Override
	public String getColumnName(int i) {
		return COL_NAMES[i];
	}

	private static final Class[] COL_TYPES = {String.class, String.class, String.class, String.class, Double.class, Double.class, Double.class};
	@Override
	public Class<?> getColumnClass(int i) {
		return COL_TYPES[i];
	}

	@Override
	public boolean isCellEditable(int i, int i1) {
		return Boolean.FALSE;
	}

	@Override
	public Object getValueAt(int i, int i1) {
		State state = this.states.get(i);
		switch(i1) {
			case 0: return state.getItem().getUid();
			case 1: return state.getItem().getCode();
			case 2: return state.getItem().getName();
			case 3: return state.getItem().getUnit();
			case 4: return state.getWeight();
			case 5: return state.getValue() / state.getWeight();
			case 6: return state.getValue();
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object o, int i, int i1) {
		
	}
	
	private final HashSet<TableModelListener> listeners = new HashSet<>();

	@Override
	public void addTableModelListener(TableModelListener tl) {
		this.listeners.add(tl);
	}

	@Override
	public void removeTableModelListener(TableModelListener tl) {
		this.listeners.remove(tl);
	}
	
}
