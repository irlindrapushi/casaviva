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

import com.redis.aza.stock.admin.core.Stock;
import com.redis.aza.stock.admin.sql.SqlStock;
import com.redis.utils.export.ExcelIO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Redjan Shabani
 */
public class FrameStock extends javax.swing.JInternalFrame {
	private final Stock stock = SqlStock.getStock();
	
	public FrameStock() {
		initComponents();
		
		this.table.setDefaultRenderer(Float.class, new IntegerCellRenderer());
	}
	
	private void reload() {
		
		this.jXSearchField1.setText("");
		this.checkZero.setSelected(true);
		
		
		
		
		
		
		
		
		
		
		
		DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();
		tableModel.setRowCount(0);		
		boolean success =stock.forEach(item -> {
			Object[] row = new Object[]{
				item.getCode(),
				item.getDescription(),
				item.getUnit(),
				item.getQuantity(),
				item.getQuantity("M01"),
				item.getQuantity("M02")
			};

			tableModel.addRow(row);		
		});
		
		if(!success){
			JOptionPane.showMessageDialog(this, "Gabim gjate leximit nga serveri!", "Gabim Serveri!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void filter() {
		List<RowFilter<TableModel, Integer>> filters = new ArrayList<>();
		
		filters.add(RowFilter.regexFilter("(?i)" + this.jXSearchField1.getText(), 0, 1, 2));
		
		if(!checkZero.isSelected()) filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, 0.0f, 4));
		
		this.table.setRowFilter(RowFilter.andFilter(filters));
	}
	
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jToolBar1 = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton2 = new javax.swing.JButton();
          jButton3 = new javax.swing.JButton();
          jScrollPane1 = new javax.swing.JScrollPane();
          table = new org.jdesktop.swingx.JXTable();
          jToolBar2 = new javax.swing.JToolBar();
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          checkZero = new javax.swing.JCheckBox();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
               public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                    formInternalFrameOpened(evt);
               }
          });

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh-16.png"))); // NOI18N
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton1);
          jToolBar1.add(filler1);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/excel-16.png"))); // NOI18N
          jButton2.setFocusable(false);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton2);

          jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/info-16.png"))); // NOI18N
          jButton3.setFocusable(false);
          jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton3);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

          jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GJENDJA E ARTIKUJVE NE MAGAZINA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

          table.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {
                    "Kodi", "Pershkrimi", "Njesia", "Sasia", "QENDRA", "PAJTONI (TR)"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
               };
               boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
               };

               public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
               }

               public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
               }
          });
          table.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          table.setShowVerticalLines(false);
          jScrollPane1.setViewportView(table);
          if (table.getColumnModel().getColumnCount() > 0) {
               table.getColumnModel().getColumn(0).setPreferredWidth(50);
               table.getColumnModel().getColumn(1).setPreferredWidth(300);
               table.getColumnModel().getColumn(2).setPreferredWidth(50);
               table.getColumnModel().getColumn(3).setPreferredWidth(50);
               table.getColumnModel().getColumn(4).setPreferredWidth(50);
               table.getColumnModel().getColumn(5).setPreferredWidth(50);
          }

          getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);

          jXSearchField1.setColumns(25);
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jXSearchField1.setMaximumSize(new java.awt.Dimension(242, 20));
          jXSearchField1.setMinimumSize(new java.awt.Dimension(242, 20));
          jXSearchField1.setPreferredSize(new java.awt.Dimension(242, 20));
          jXSearchField1.setPrompt("Kerko ...");
          jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jXSearchField1ActionPerformed(evt);
               }
          });
          jToolBar2.add(jXSearchField1);
          jToolBar2.add(filler2);

          checkZero.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          checkZero.setForeground(java.awt.Color.red);
          checkZero.setSelected(true);
          checkZero.setText("Sasi -/0");
          checkZero.setFocusable(false);
          checkZero.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          checkZero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          checkZero.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    checkZeroStateChanged(evt);
               }
          });
          jToolBar2.add(checkZero);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_END);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
		this.reload();
     }//GEN-LAST:event_formInternalFrameOpened

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          this.reload();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
          this.filter();
     }//GEN-LAST:event_jXSearchField1ActionPerformed

     private void checkZeroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkZeroStateChanged
          this.filter();
     }//GEN-LAST:event_checkZeroStateChanged

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          ExcelIO excelIO = new ExcelIO();
		File file = excelIO.export("export_"+System.currentTimeMillis()+".xls", table);
		
		try {Desktop.getDesktop().open(file);}
		catch (IOException ex) {Logger.getLogger(FrameStock.class.getName()).log(Level.SEVERE, null, ex);}
     }//GEN-LAST:event_jButton2ActionPerformed

     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          String txt = ""
			+ "M01:  MAGAZINA CASAVIVA\n"
			+ "M02:  DYQANI TIRANE\n";
		
		JOptionPane.showMessageDialog(this, txt,"Magazinat", JOptionPane.PLAIN_MESSAGE);
     }//GEN-LAST:event_jButton3ActionPerformed


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JCheckBox checkZero;
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable table;
     // End of variables declaration//GEN-END:variables
}
