/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.swing.model;

import static com.redis.casaviva.shop.dc.Product.FIELD_CLASSES;
import static com.redis.casaviva.shop.dc.Product.FIELD_COUNT;
import static com.redis.casaviva.shop.dc.Product.FIELD_LABELS;
import static com.redis.casaviva.shop.dc.Product.FIELD_LENGTH;
import com.redis.casaviva.shop.dc.Warehouse;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class ProductTableModel extends DefaultTableModel{
	
	private Warehouse warehouse;
	
	public ProductTableModel(){
		super(0, FIELD_COUNT);
	}

	public ProductTableModel(Warehouse warehouse) {
		super(warehouse.getProducts().size(), FIELD_COUNT);
		this.warehouse = warehouse;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
		this.notifyTableChanged();
	}
	
	

	@Override
	public int getRowCount() {
		return warehouse == null ? 0 : warehouse.getProducts().size();
	}

	@Override
	public int getColumnCount() {
		return FIELD_COUNT;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return FIELD_LABELS[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return FIELD_CLASSES[columnIndex];
	}
	
	public int getColumnWidth(int columnIndex){
		int maxLength = 0;
		
		for(int length : FIELD_LENGTH)
			maxLength += length;
		
		return 100 * FIELD_LENGTH[columnIndex] / maxLength;
	}
	
	public TableCellRenderer getColumnTableCellRenderer(int columnIndex){
		
		if(columnIndex >= 8){
			return new DefaultTableCellRenderer(){
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					
					
					if(value instanceof Number){
						setHorizontalAlignment(RIGHT);
						
						if(((Number) value).doubleValue() < -Double.MIN_VALUE)
							setText("<html><p color='red'>"+((Number) value).intValue()+"</html>");
						else if(((Number) value).doubleValue() > Double.MIN_VALUE)
							setText("<html>"+((Number) value).intValue()+"</html>");
						else 
							setText("<html><p color='orange'>" + 0 + "</html>");
					}
					
					return this;
				}
			};
		}
		
		else
			return new DefaultTableCellRenderer();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.warehouse.getProducts().get(rowIndex).toArray()[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}

	
	private final List<TableModelListener> listeners = new ArrayList<>();
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		this.listeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		this.listeners.remove(l);
	}
	
	public void notifyTableChanged(){
		this.listeners.forEach( l -> {
			l.tableChanged(new TableModelEvent(this));
		});
	}
	
}
