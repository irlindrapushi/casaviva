/*
 * Copyright (C) 2018 user
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
package com.redis.casaviva.shop.swing;

import com.redis.casaviva.shop.core.Article;
import com.redis.casaviva.shop.swing.labels.FrameLabels;
import com.redis.casaviva.shop.core.Stock;
import com.redis.utils.Dataset;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class FrameStock extends javax.swing.JFrame {
	
	private Stock stock;
	
	public FrameStock() {
		initComponents();
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
		
		this.setTitle("CASAVIVA SHPK - " + String.valueOf(stock));
	}
	
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jToolBar1 = new javax.swing.JToolBar();
          buttonLabels = new javax.swing.JButton();
          desktopPane = new javax.swing.JDesktopPane();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle(String.valueOf(this.stock));
          setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
          setPreferredSize(new java.awt.Dimension(1200, 700));

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);

          buttonLabels.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
          buttonLabels.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-price-32.png"))); // NOI18N
          java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("string"); // NOI18N
          buttonLabels.setText(bundle.getString("text_labels")); // NOI18N
          buttonLabels.setFocusable(false);
          buttonLabels.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          buttonLabels.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          buttonLabels.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonLabelsActionPerformed(evt);
               }
          });
          jToolBar1.add(buttonLabels);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);
          getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void buttonLabelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLabelsActionPerformed
          
		FrameLabels frame = new FrameLabels() {
			@Override
			public Dataset<Article> articles() {
				return FrameStock.this.stock.getArticles();
			}
		};
		this.desktopPane.add(frame);
		frame.setVisible(true);
		
		try {
			frame.setMaximum(true);
		} catch (PropertyVetoException ex) {
			Logger.getLogger(FrameStock.class.getName()).log(Level.SEVERE, null, ex);
		}
     }//GEN-LAST:event_buttonLabelsActionPerformed


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton buttonLabels;
     private javax.swing.JDesktopPane desktopPane;
     private javax.swing.JToolBar jToolBar1;
     // End of variables declaration//GEN-END:variables
}
