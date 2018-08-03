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
package com.redis.stock.swing;

import com.redis.stock.core.Enterprise;
import com.redis.stock.core.Item;
import com.redis.stock.swing.item.DialogItems;
import com.redis.stock.utils.Set;

/**
 *
 * @author Redjan Shabani
 */
public class FrameEnterprise extends javax.swing.JFrame {
	
	private final Enterprise enterprise;
	
	public FrameEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
		initComponents();
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}
	
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          webToolBar1 = new com.alee.laf.toolbar.WebToolBar();
          webButton1 = new com.alee.laf.button.WebButton();
          webDesktopPane1 = new com.alee.laf.desktoppane.WebDesktopPane();
          webPanel1 = new com.alee.laf.panel.WebPanel();
          webLabel1 = new com.alee.laf.label.WebLabel();
          webMenuBar1 = new com.alee.laf.menu.WebMenuBar();
          jMenu1 = new javax.swing.JMenu();
          jMenu2 = new javax.swing.JMenu();
          webMenuItem1 = new com.alee.laf.menu.WebMenuItem();
          webMenuItem2 = new com.alee.laf.menu.WebMenuItem();
          webMenuItem3 = new com.alee.laf.menu.WebMenuItem();
          webMenuItem4 = new com.alee.laf.menu.WebMenuItem();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle(String.valueOf(this.enterprise));
          setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
          setPreferredSize(new java.awt.Dimension(1200, 675));

          webToolBar1.setFloatable(false);
          webToolBar1.setRollover(true);

          webButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-user-manual-32.png"))); // NOI18N
          java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("text_resources_al"); // NOI18N
          webButton1.setText(bundle.getString("text_catalog")); // NOI18N
          webButton1.setFocusable(false);
          webButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
          webButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          webButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          webToolBar1.add(webButton1);

          getContentPane().add(webToolBar1, java.awt.BorderLayout.PAGE_START);
          getContentPane().add(webDesktopPane1, java.awt.BorderLayout.CENTER);

          webPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

          webLabel1.setText(bundle.getString("text_main_frame_header")); // NOI18N
          webPanel1.add(webLabel1);

          getContentPane().add(webPanel1, java.awt.BorderLayout.PAGE_END);

          jMenu1.setText("File");
          webMenuBar1.add(jMenu1);

          jMenu2.setText(bundle.getString("text_structure")); // NOI18N

          webMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
          webMenuItem1.setText(bundle.getString("text_items")); // NOI18N
          webMenuItem1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    webMenuItem1ActionPerformed(evt);
               }
          });
          jMenu2.add(webMenuItem1);

          webMenuItem2.setText(bundle.getString("text_stocks")); // NOI18N
          jMenu2.add(webMenuItem2);

          webMenuItem3.setText(bundle.getString("text_suppliers")); // NOI18N
          jMenu2.add(webMenuItem3);

          webMenuItem4.setText(bundle.getString("text_customers")); // NOI18N
          jMenu2.add(webMenuItem4);

          webMenuBar1.add(jMenu2);

          setJMenuBar(webMenuBar1);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void webMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webMenuItem1ActionPerformed
          DialogItems dialog = new DialogItems(this) {
			@Override
			public Set<Item> getItems() {
				return enterprise.getItems();
			}
		};
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
     }//GEN-LAST:event_webMenuItem1ActionPerformed
	
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JMenu jMenu1;
     private javax.swing.JMenu jMenu2;
     private com.alee.laf.button.WebButton webButton1;
     private com.alee.laf.desktoppane.WebDesktopPane webDesktopPane1;
     private com.alee.laf.label.WebLabel webLabel1;
     private com.alee.laf.menu.WebMenuBar webMenuBar1;
     private com.alee.laf.menu.WebMenuItem webMenuItem1;
     private com.alee.laf.menu.WebMenuItem webMenuItem2;
     private com.alee.laf.menu.WebMenuItem webMenuItem3;
     private com.alee.laf.menu.WebMenuItem webMenuItem4;
     private com.alee.laf.panel.WebPanel webPanel1;
     private com.alee.laf.toolbar.WebToolBar webToolBar1;
     // End of variables declaration//GEN-END:variables
}
