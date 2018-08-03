/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.swing.model;

import com.redis.casaviva.shop.dc.Product;
import static com.redis.casaviva.shop.dc.Product.SpecialLabel.FIELD_CLASSES;
import static com.redis.casaviva.shop.dc.Product.SpecialLabel.FIELD_COUNT;
import static com.redis.casaviva.shop.dc.Product.SpecialLabel.FIELD_LABELS;
import java.awt.Component;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SpecialLabelTableModel extends DefaultTableModel{
	
	private List<Product.SpecialLabel> labels;
	
	public SpecialLabelTableModel(){
		super(0, FIELD_COUNT);
		labels = new ArrayList<>();
	}

	public List<Product.SpecialLabel> getLabels() {
		return labels;
	}	

	public void setLabels(List<Product.SpecialLabel> labels) {
		this.labels = labels;
		super.setRowCount(labels.size());
	}
	
	
	

	@Override
	public String getColumnName(int columnIndex) {
		return FIELD_LABELS[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return FIELD_CLASSES[columnIndex];
	}
	
	
	public TableCellRenderer getColumnTableCellRenderer(int columnIndex){
		
		return new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


				if(value instanceof Number){
					
					setHorizontalAlignment(RIGHT);
					
					if(column == 5 || column == 6){
						setText(NumberFormat.getNumberInstance().format(value));
					}
					if(column == 7){
						NumberFormat.getPercentInstance().format(value);
						setText(NumberFormat.getPercentInstance().format(value));
					}
					if(column==8){
						if(((Number) value).doubleValue() < -Double.MIN_VALUE)
							setText("<html><p color='red'>"+((Number) value).intValue()+"</html>");
						else if(((Number) value).doubleValue() > Double.MIN_VALUE)
							setText("<html>"+((Number) value).intValue()+"</html>");
						else 
							setText("<html><p color='orange'>" + 0 + "</html>");
					}
				}
				
				if(value instanceof Instant){
					setHorizontalAlignment(CENTER);
					setText(
						((Instant) value)
						.atZone(ZoneId.systemDefault())
						.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
					);
				}

				return this;
			}
		};
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex < FIELD_COUNT - 1)
			return false;
		return super.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex < FIELD_COUNT)
			return this.labels.get(rowIndex).toArray()[columnIndex];
		
		return super.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if(column == FIELD_COUNT - 1){
			this.labels.get(row).setSelected((boolean) aValue);
			super.fireTableCellUpdated(row, column);
		}
	}
	
	
	
	
}
