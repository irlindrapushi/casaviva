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
package com.redis.stock.admin.swing;

import com.redis.stock.core.Inventory;
import com.redis.stock.core.Stock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Redjan Shabani
 */
public abstract class FrameInventory extends javax.swing.JInternalFrame {
	
	public FrameInventory() {
		initComponents();
	}
	
	public abstract Inventory getInventory();
	
	private void reload() {		
		DefaultMutableTreeNode stockRoot = new DefaultMutableTreeNode("INVENTARI");
		try {
			this.getInventory().getStocks().forEach(stock -> {
				stockRoot.add(new DefaultMutableTreeNode(stock));
			});
		} 
		catch (Exception ex) {
			Logger.getLogger(FrameInventory.class.getName()).log(Level.SEVERE, null, ex);
		}
		this.treeStocks.setModel(new DefaultTreeModel(stockRoot));
		
		
		
		
		
	}
	
	private void stockSelected(Stock stock) {
		DefaultTableModel model = (DefaultTableModel) this.jXTable1.getModel();
		model.setRowCount(0);
		jFormattedTextField1.setValue(0.0d);
		
		Stock.State state = ((Stock) stock).getState();
		try {
			this.getInventory().getItems().forEach(item -> {
				model.addRow(new Object[]{
					item.getUid(),
					item.getCode(),
					item.getName(),
					item.getUnit(),
					state.getWeight(item),
					state.getValue(item) / state.getWeight(item),
					state.getValue(item)
				});
			});

			jFormattedTextField1.setValue(state.getValue());				


		}	catch (Exception ex) {
			Logger.getLogger(FrameInventory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
		
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jSplitPane1 = new javax.swing.JSplitPane();
          jScrollPane1 = new javax.swing.JScrollPane();
          treeStocks = new org.jdesktop.swingx.JXTree();
          jPanel1 = new javax.swing.JPanel();
          jToolBar2 = new javax.swing.JToolBar();
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jFormattedTextField1 = new javax.swing.JFormattedTextField();
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();
          jToolBar1 = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          setPreferredSize(new java.awt.Dimension(800, 450));

          jSplitPane1.setDividerLocation(220);

          javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Magazinat");
          treeStocks.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
          treeStocks.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
               public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    treeStocksValueChanged(evt);
               }
          });
          jScrollPane1.setViewportView(treeStocks);

          jSplitPane1.setLeftComponent(jScrollPane1);

          jPanel1.setLayout(new java.awt.BorderLayout());

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);

          jXSearchField1.setColumns(20);
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setMaximumSize(new java.awt.Dimension(220, 22));
          jXSearchField1.setMinimumSize(new java.awt.Dimension(220, 22));
          jXSearchField1.setPreferredSize(new java.awt.Dimension(220, 22));
          jToolBar2.add(jXSearchField1);
          jToolBar2.add(filler1);

          jFormattedTextField1.setEditable(false);
          jFormattedTextField1.setColumns(15);
          jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
          jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          jFormattedTextField1.setText("0.00");
          jFormattedTextField1.setMaximumSize(new java.awt.Dimension(126, 22));
          jFormattedTextField1.setMinimumSize(new java.awt.Dimension(126, 22));
          jFormattedTextField1.setPreferredSize(new java.awt.Dimension(126, 22));
          jToolBar2.add(jFormattedTextField1);

          jPanel1.add(jToolBar2, java.awt.BorderLayout.PAGE_END);

          jXTable1.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {
                    "Kodi", "Barkodi", "Emertimi", "Njesia", "Sasia", "Cmimi", "Vlera"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
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
          jScrollPane2.setViewportView(jXTable1);
          if (jXTable1.getColumnModel().getColumnCount() > 0) {
               jXTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
               jXTable1.getColumnModel().getColumn(2).setPreferredWidth(500);
               jXTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
          }

          jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

          jSplitPane1.setRightComponent(jPanel1);

          getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-refresh-16.png"))); // NOI18N
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton1);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          this.reload();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void treeStocksValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeStocksValueChanged
          TreePath path = evt.getNewLeadSelectionPath();
		System.out.println("Selected tree path: " + path);
		if(path != null) {			
			if(path.getPathCount() == 1) {//root node selected
				
			}
			
			if(path.getPathCount() == 2) {//stock node selected
				Object node = path.getLastPathComponent();
				if(node instanceof DefaultMutableTreeNode) {
					if(((DefaultMutableTreeNode) node).getUserObject() instanceof Stock) {
						Stock stock = (Stock) ((DefaultMutableTreeNode) node).getUserObject();
						this.stockSelected(stock);
					}
				}
			}
		}
		
		
		
		
		
		
		
		
     }//GEN-LAST:event_treeStocksValueChanged


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.JButton jButton1;
     private javax.swing.JFormattedTextField jFormattedTextField1;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable jXTable1;
     private org.jdesktop.swingx.JXTree treeStocks;
     // End of variables declaration//GEN-END:variables
}
