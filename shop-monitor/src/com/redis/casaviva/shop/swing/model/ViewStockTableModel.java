/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.swing.model;

import com.redis.casaviva.shop.dc.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class ViewStockTableModel extends DefaultTableModel{
	
	public static final int COLUMN_COUNT = 17;
	
	public static final String[] COLUMN_LABELS = {
		"Kodi", 
		"Barkodi", 
		"Pershkrimi",
		"Qendra", 
		"Fieri", 
		"Tirana1", 
		"Elbasani", 
		"Kavaja", 
		"Vlora", 
		"Durresi", 
		"Shkodra", 
		"Tirana 2", 
		"Lushnja", 
		"Korca",
		"Servisi", 
		"Shumica",
		"Total"
	};
	
	public static final Class[] COLUMN_CLASSES = {
		String.class,
		String.class,
		String.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class,
		Integer.class
	};
	
	private final List<Stock> entries;
	
	public ViewStockTableModel(){
		super(0, COLUMN_COUNT);
		this.entries = new ArrayList<>();
	}
	
	public void setEntries(List entries){
		this.entries.clear();
		this.entries.addAll(entries);
		super.fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_LABELS[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	@Override
	public Object getValueAt(int row, int column) {
		return this.entries.get(row).toArray()[column];
	}

	@Override
	public int getRowCount() {
		return this.entries==null ? 0 : this.entries.size();
	}
	
	
	
}
