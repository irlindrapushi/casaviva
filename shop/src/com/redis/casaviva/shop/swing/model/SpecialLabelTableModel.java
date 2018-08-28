/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.swing.model;

import com.redis.casaviva.shop.dc.Product;
import java.awt.Component;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SpecialLabelTableModel implements TableModel{
	
	private List<Product.SpecialLabel> labels = new ArrayList<>();
	
	public Collection<Product.SpecialLabel> getLabels() {
		return new ArrayList<>(labels);
	}
	
	public void setLabels(Collection<Product.SpecialLabel> labels) {
		this.labels.clear();
		this.labels.addAll(labels);
		this.listeners.forEach(listener -> {
			listener.tableChanged(new TableModelEvent(this));
		});
	}
	
	
	@Override
	public int getRowCount() {
		return this.labels.size();
	}
	
	@Override
	public int getColumnCount() {
		return 12;
	}
	
	@Override
	public String getColumnName(int i) {
		switch(i) {
			case 0: return "Data/Ora";
			case 1: return "Kodi";
			case 2: return "Barkodi";
			case 3: return "Sektori";
			case 4: return "Kategoria";
			case 5: return "Pershkrimi";
			case 6: return "Njesia";
			case 7: return "Sasia";
			case 8: return "Cmimi";
			case 9: return "Oferta";
			case 10: return "Skonto";
			case 11: return "";
			default: return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int i) {
		switch(i) {
			case 0: return Instant.class;
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
			case 11: return Boolean.class;
			default: return null;
		}
	}

	
	@Override
	public boolean isCellEditable(int i, int i1) {
		switch(i1) {
			case 11: return true;
			default: return false;
		}
	}

	@Override
	public Object getValueAt(int i, int i1) {
		Product.SpecialLabel label = this.labels.get(i);
		switch(i1) {
			case 0: return label.getInstant();
			case 1: return label.getCode();
			case 2: return label.getBarcode();
			case 3: return label.getSector();
			case 4: return label.getCateogory();
			case 5: return label.getDescription();
			case 6: return label.getUnit();
			case 7: return label.getStock();
			case 8: return label.getOldPrice();
			case 9: return label.getNewPrice();
			case 10: return label.getDiffPriceRatio();
			case 11: return label.isSelected();
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object o, int i, int i1) {
		Product.SpecialLabel label = this.labels.get(i);
		switch(i1) {
			case 11: label.setSelected((boolean) o); break;
			default: return;
		}
		
		//TO DO NOTIFY 
		this.listeners.forEach(listener -> {
			listener.tableChanged(new TableModelEvent(this, i, i, i1));
		});
	}

	private final Set<TableModelListener> listeners = new HashSet<>();
	
	
	@Override
	public void addTableModelListener(TableModelListener tl) {
		this.listeners.add(tl);
	}

	@Override
	public void removeTableModelListener(TableModelListener tl) {
		this.listeners.remove(tl);
	}
	
	
	
	public TableCellRenderer getColumnTableCellRenderer(int columnIndex){
		
		return new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


				if(value instanceof Number){
					
					setHorizontalAlignment(RIGHT);
					
					if(column == 10){
						NumberFormat.getPercentInstance().format(value);
						setText(NumberFormat.getPercentInstance().format(value));
					}
					else {
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

	

	
	
}
