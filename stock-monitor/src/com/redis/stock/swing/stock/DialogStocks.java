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
package com.redis.stock.swing.stock;

import com.redis.stock.core.Stock;
import com.redis.stock.utils.Set;

/**
 *
 * @author Redjan Shabani
 */
public abstract class DialogStocks extends javax.swing.JDialog {
	
	public DialogStocks(java.awt.Frame parent) {
		super(parent, true);
		initComponents();
	}
	
	public abstract Set<Stock> getStocks();
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jScrollPane1 = new javax.swing.JScrollPane();
          webTable1 = new com.alee.laf.table.WebTable();
          webToolBar1 = new com.alee.laf.toolbar.WebToolBar();
          webButton1 = new com.alee.laf.button.WebButton();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setPreferredSize(new java.awt.Dimension(480, 640));

          webTable1.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
               },
               new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
               }
          ));
          jScrollPane1.setViewportView(webTable1);

          getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

          webToolBar1.setFloatable(false);
          webToolBar1.setRollover(true);

          webButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-refresh-16.png"))); // NOI18N
          webButton1.setFocusable(false);
          webButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          webButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          webToolBar1.add(webButton1);

          getContentPane().add(webToolBar1, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JScrollPane jScrollPane1;
     private com.alee.laf.button.WebButton webButton1;
     private com.alee.laf.table.WebTable webTable1;
     private com.alee.laf.toolbar.WebToolBar webToolBar1;
     // End of variables declaration//GEN-END:variables
}
