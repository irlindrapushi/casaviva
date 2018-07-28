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
package com.redis.stock.swing.item;

import com.redis.stock.core.Item;
import com.redis.stock.utils.Set;
import javax.swing.RowFilter;

/**
 *
 * @author Redjan Shabani
 */
public abstract class DialogItems extends javax.swing.JDialog {
	
	public DialogItems(java.awt.Frame parent) {
		super(parent, true);
		initComponents();
	}
	
	public abstract Set<Item> getItems();
	
	private void reload() {
		//clear
		this.tableModelItem1.clear();
		
		
		
		
		//populate
		this.getItems().forEach(item -> {
			System.out.println(item);
			this.tableModelItem1.insert(item);
		});
	}
	
	private void filter() {
		this.jXTable1.setRowFilter(RowFilter.regexFilter("(?i)" + this.jXSearchField1.getText(), 0, 1, 2));
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          tableModelItem1 = new com.redis.stock.swing.item.TableModelItem();
          webToolBar1 = new com.alee.laf.toolbar.WebToolBar();
          webButton1 = new com.alee.laf.button.WebButton();
          jToolBar1 = new javax.swing.JToolBar();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setPreferredSize(new java.awt.Dimension(480, 640));

          webToolBar1.setFloatable(false);
          webToolBar1.setRollover(true);

          webButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-refresh-16.png"))); // NOI18N
          webButton1.setFocusable(false);
          webButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          webButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          webButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    webButton1ActionPerformed(evt);
               }
          });
          webToolBar1.add(webButton1);

          getContentPane().add(webToolBar1, java.awt.BorderLayout.PAGE_START);

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);
          jToolBar1.add(filler2);

          jXSearchField1.setColumns(20);
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setMaximumSize(new java.awt.Dimension(180, 22));
          jXSearchField1.setMinimumSize(new java.awt.Dimension(180, 22));
          jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jXSearchField1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jXSearchField1);
          jToolBar1.add(filler1);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);

          jXTable1.setModel(tableModelItem1);
          jScrollPane2.setViewportView(jXTable1);
          if (jXTable1.getColumnModel().getColumnCount() > 0) {
               jXTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
               jXTable1.getColumnModel().getColumn(0).setHeaderValue("Kodi");
               jXTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
               jXTable1.getColumnModel().getColumn(1).setHeaderValue("Emertimi");
               jXTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(2).setHeaderValue("Njesia");
          }

          getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
          this.reload();
     }//GEN-LAST:event_webButton1ActionPerformed

     private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
          this.filter();
     }//GEN-LAST:event_jXSearchField1ActionPerformed

	

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JToolBar jToolBar1;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable jXTable1;
     private com.redis.stock.swing.item.TableModelItem tableModelItem1;
     private com.alee.laf.button.WebButton webButton1;
     private com.alee.laf.toolbar.WebToolBar webToolBar1;
     // End of variables declaration//GEN-END:variables
}
