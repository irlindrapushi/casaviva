/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.aza.stock.admin.gui;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class IntegerCellRenderer extends DefaultTableCellRenderer{
	DecimalFormat formatter = new DecimalFormat("###,###");
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if(value instanceof Number) {
			Number num = (Number) value;

			setHorizontalAlignment(RIGHT);

			if(num.intValue() < 0){
				super.setText("<html><font color='red'><b>" + formatter.format(num) + "</font></html>");
			}
			else if(num.intValue() == 0){
				super.setText("<html><font color='orange'><b>" + formatter.format(num) + "</font></html>");
			}
			else{
				super.setText("<html><b>" + formatter.format(num) + "</html>");
			}
		}
		
		return this;
	}
	
}
