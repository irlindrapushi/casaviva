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

import com.redis.casaviva.shop.core.Stock;

/**
 *
 * @author user
 */
public class FrameStock extends javax.swing.JFrame {
	
	private final Stock stock;
	
	public FrameStock(Stock stock) {
		this.stock = stock;
		initComponents();
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle(this.stock.toString());
          setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
          setPreferredSize(new java.awt.Dimension(1200, 700));

          pack();
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     // End of variables declaration//GEN-END:variables
}
