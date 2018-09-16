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

import com.redis.aza.stock.admin.core.Catalog;
import com.redis.aza.stock.admin.core.State;
import com.redis.aza.stock.admin.core.Stock;
import com.redis.aza.stock.admin.sql.CatalogSQL;
import com.redis.aza.stock.admin.sql.SqlStock;
import com.redis.utils.export.ExcelIO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Redjan Shabani
 */
public class FrameStock extends javax.swing.JInternalFrame {
	Catalog catalog = CatalogSQL.getCatalog();
	private final Stock stock = SqlStock.getStock();
	
	
	
	public FrameStock() {
		initComponents();
		
		this.table.setDefaultRenderer(Float.class, new IntegerCellRenderer());
	}
	
	
	private void reload() {
		this.jXSearchField1.setText("");
		this.checkZero.setSelected(true);
		
		refreshTree();
		refreshTable();
		
	}
	
	private void refreshTree() {
		
		DefaultMutableTreeNode catalogNode = new DefaultMutableTreeNode();
		this.catalog.sectors().forEach(sector -> {
			DefaultMutableTreeNode sectorNode = new DefaultMutableTreeNode(sector);
			
			this.catalog.categories(sector).forEach(category -> {
				DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category);
				
				sectorNode.add(categoryNode);
			});
			
			catalogNode.add(sectorNode);
		});
		this.jXTree1.setModel(new DefaultTreeModel(catalogNode));
		this.jXTree1.expandRow(0);
		this.jXTree1.setSelectionRow(0);
	}
	
	
	
	
	
	private void refreshTable() {
		DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();
		tableModel.setRowCount(0);
		List<Object> header = new ArrayList<>();
		header.addAll(Arrays.asList("Barkodi", "Kodi", "Emertimi", "Njesia"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		List<State> states = new ArrayList<>();		
		states.add(stock.getState());
		header.add("Sasia");
		
		this.stock.warehouses().forEach(warehouse -> {
			header.add(warehouse);
			states.add(warehouse.getState());
		});
		
		tableModel.setColumnIdentifiers(header.toArray());
		
		
		
		
		
		
		Object[] path = this.jXTree1.getSelectionPath().getPath();	
		catalog.items(path).forEach(item -> {
			List<Object> row = new ArrayList<>();
			row.add(catalog.barcode(item));
			row.add(item.getCode());
			row.add(item.getName());
			row.add(item.getUnit());
			
			states.forEach(state -> row.add(state.getWeight(item)));

			tableModel.addRow(row.toArray());		
		});
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(12);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(10);
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
          jSplitPane1 = new javax.swing.JSplitPane();
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTree1 = new org.jdesktop.swingx.JXTree();
          jPanel1 = new javax.swing.JPanel();
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

          jSplitPane1.setDividerLocation(220);

          jXTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
               public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    jXTree1ValueChanged(evt);
               }
          });
          jScrollPane2.setViewportView(jXTree1);

          jSplitPane1.setLeftComponent(jScrollPane2);

          jPanel1.setLayout(new java.awt.BorderLayout());

          jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GJENDJA E ARTIKUJVE NE MAGAZINA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

          table.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {
                    "Kodi", "Barkodi", "Pershkrimi", "Njesia", "Sasia", "QENDRA", "PAJTONI (TR)"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
               };
               boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
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
               table.getColumnModel().getColumn(0).setPreferredWidth(10);
               table.getColumnModel().getColumn(1).setPreferredWidth(12);
               table.getColumnModel().getColumn(2).setPreferredWidth(350);
               table.getColumnModel().getColumn(3).setPreferredWidth(10);
               table.getColumnModel().getColumn(4).setPreferredWidth(10);
               table.getColumnModel().getColumn(5).setPreferredWidth(0);
               table.getColumnModel().getColumn(6).setPreferredWidth(10);
          }

          jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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

          jPanel1.add(jToolBar2, java.awt.BorderLayout.PAGE_END);

          jSplitPane1.setRightComponent(jPanel1);

          getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

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

     private void jXTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jXTree1ValueChanged
          this.refreshTable();
     }//GEN-LAST:event_jXTree1ValueChanged


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JCheckBox checkZero;
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTree jXTree1;
     private org.jdesktop.swingx.JXTable table;
     // End of variables declaration//GEN-END:variables
}
