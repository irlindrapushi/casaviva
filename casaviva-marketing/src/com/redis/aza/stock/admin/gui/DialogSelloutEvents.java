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

package com.redis.aza.stock.admin.gui;

import com.redis.aza.stock.admin.core.Sellout.Event;
import com.redis.utils.export.ExcelIO;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Redjan Shabani
 */
public class DialogSelloutEvents extends javax.swing.JDialog {	
		
	public DialogSelloutEvents(java.awt.Frame parent) {
		super(parent, true);
		
		initComponents();
		
		//
		this.jXTable1.getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
			private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); 
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);				
				if(o instanceof Date) {
					long diff = Date.from(Instant.now()).getTime() - ((Date) o).getTime();
					label.setText(formatter.format(o));
					label.setHorizontalAlignment(JLabel.CENTER);
				}			
				return label;
			}		
		});
	}
	
	public void setEvents(Collection<Event> events) {
		events.stream().forEach(event -> {
			((DefaultTableModel) this.jXTable1.getModel()).addRow(
				new Object[]{
					event.getDate(),
					event.getWarehouse(),
					event.getClient(),
					event.getCode(),
					event.getDescription(),
					event.getUnit(),
					event.getQuantity(),
					event.getPrice(),
					event.getValue()				
				}
			);
		});
		
		events.stream().map(Event::getWarehouse).distinct().sorted().forEach(
			whouse -> {
				this.comboWarehouse.addItem(whouse);
			}
		);
				
		events.stream().map(Event::getCode).distinct().sorted().forEach(
			whouse -> {
				this.comboCode.addItem(whouse);
			}
		);
		
		this.fieldValue.setValue(0.0f);
		this.fieldValue.setValue(events.stream().mapToDouble(Event::getValue).sum());
		
		
	}
	
	private void filter() {
		List<RowFilter<TableModel, Integer>> filters = new ArrayList<>();
		
		if(this.comboWarehouse.getSelectedIndex() > 0)
			filters.add(RowFilter.regexFilter('^' + String.valueOf(this.comboWarehouse.getSelectedItem()) + '$', 1));
				
		if(this.comboCode.getSelectedIndex() > 0)
			filters.add(RowFilter.regexFilter('^' + String.valueOf(this.comboCode.getSelectedItem()) + '$', 3));
		
		this.jXTable1.setRowFilter(RowFilter.andFilter(filters));
		
		
		Float value = 0.0f;
		for(int row = 0; row < this.jXTable1.getRowCount(); row++){
			value += (Float) this.jXTable1.getValueAt(row, 8);
		}
		this.fieldValue.setValue(value);
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jScrollPane1 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();
          jToolBar1 = new javax.swing.JToolBar();
          comboWarehouse = new javax.swing.JComboBox<String>();
          comboCode = new javax.swing.JComboBox<String>();
          filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          fieldValue = new javax.swing.JFormattedTextField();
          jToolBar2 = new javax.swing.JToolBar();
          filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton2 = new javax.swing.JButton();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          jXTable1.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {
                    "Data", "Mag.", "Klienti", "Kodi", "Pershkrimi", "Njesia", "Sasia", "Cmimi", "Vlera"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
               };
               boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false
               };

               public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
               }

               public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
               }
          });
          jXTable1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jXTable1.setShowHorizontalLines(false);
          jXTable1.setShowVerticalLines(false);
          jScrollPane1.setViewportView(jXTable1);
          if (jXTable1.getColumnModel().getColumnCount() > 0) {
               jXTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
               jXTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
               jXTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(4).setPreferredWidth(300);
               jXTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(8).setPreferredWidth(50);
          }

          getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);

          comboWarehouse.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          comboWarehouse.setMaximumRowCount(10);
          comboWarehouse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Magazina -" }));
          comboWarehouse.setMaximumSize(new java.awt.Dimension(150, 20));
          comboWarehouse.setPreferredSize(new java.awt.Dimension(150, 20));
          comboWarehouse.addItemListener(new java.awt.event.ItemListener() {
               public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    comboWarehouseItemStateChanged(evt);
               }
          });
          jToolBar1.add(comboWarehouse);

          comboCode.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          comboCode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Artikulli -" }));
          comboCode.setMaximumSize(new java.awt.Dimension(150, 20));
          comboCode.setPreferredSize(new java.awt.Dimension(150, 20));
          comboCode.addItemListener(new java.awt.event.ItemListener() {
               public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    comboCodeItemStateChanged(evt);
               }
          });
          jToolBar1.add(comboCode);
          jToolBar1.add(filler3);

          fieldValue.setEditable(false);
          fieldValue.setColumns(10);
          fieldValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
          fieldValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          fieldValue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          fieldValue.setMaximumSize(new java.awt.Dimension(100, 20));
          fieldValue.setMinimumSize(new java.awt.Dimension(100, 20));
          fieldValue.setValue(0.0f);
          jToolBar1.add(fieldValue);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);
          jToolBar2.add(filler4);
          jToolBar2.add(filler1);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/excel-16.png"))); // NOI18N
          jButton2.setFocusable(false);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
               }
          });
          jToolBar2.add(jButton2);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void comboWarehouseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboWarehouseItemStateChanged
          this.filter();
     }//GEN-LAST:event_comboWarehouseItemStateChanged

     private void comboCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCodeItemStateChanged
          this.filter();
     }//GEN-LAST:event_comboCodeItemStateChanged

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          ExcelIO excelIO = new ExcelIO();
          File file = excelIO.export("export_"+System.currentTimeMillis()+".xls", jXTable1);

          try {Desktop.getDesktop().open(file);}
          catch (IOException ex) {Logger.getLogger(DialogSelloutEvents.class.getName()).log(Level.SEVERE, null, ex);}
     }//GEN-LAST:event_jButton2ActionPerformed

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JComboBox<String> comboCode;
     private javax.swing.JComboBox<String> comboWarehouse;
     private javax.swing.JFormattedTextField fieldValue;
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler3;
     private javax.swing.Box.Filler filler4;
     private javax.swing.JButton jButton2;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXTable jXTable1;
     // End of variables declaration//GEN-END:variables
}
