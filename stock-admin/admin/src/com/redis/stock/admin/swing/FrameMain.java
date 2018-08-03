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
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public abstract class FrameMain extends javax.swing.JFrame {
	
	public FrameMain() {
		initComponents();
	}
	
	public abstract Inventory getInventory();	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          jXLabel1 = new org.jdesktop.swingx.JXLabel();
          jDesktopPane1 = new javax.swing.JDesktopPane();
          jToolBar1 = new javax.swing.JToolBar();
          jButton2 = new javax.swing.JButton();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton1 = new javax.swing.JButton();
          jMenuBar1 = new javax.swing.JMenuBar();
          jMenu1 = new javax.swing.JMenu();
          jMenuItem1 = new javax.swing.JMenuItem();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
          setPreferredSize(new java.awt.Dimension(1200, 675));
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
               }
               public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
               }
          });

          jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

          jXLabel1.setText("<html>\nCopyright &copy; 2017 redis-it.com<br/>\nMundesuar nga R. Shabani nen lishencen GNU GPL v3.0<br/>\nCel.: <a href='tel:00355692048755'>(+355) 69 20 48 755</a> Email: <a href='mailto:redjan.shabani@gmail.com'>redjan.shabani@gmail.com</a><br/>\n</html>");
          jPanel1.add(jXLabel1);

          getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);
          getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

          jToolBar1.setFloatable(false);
          jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
          jToolBar1.setRollover(true);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-warehouse-32.png"))); // NOI18N
          java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("text_res_alb"); // NOI18N
          jButton2.setText(bundle.getString("warehouses")); // NOI18N
          jButton2.setToolTipText(bundle.getString("inventory")); // NOI18N
          jButton2.setFocusable(false);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton2);
          jToolBar1.add(filler2);
          jToolBar1.add(filler1);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-shutdown-32.png"))); // NOI18N
          jButton1.setText(bundle.getString("exit")); // NOI18N
          jButton1.setToolTipText(bundle.getString("exit")); // NOI18N
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jToolBar1.add(jButton1);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.WEST);

          jMenu1.setText("File");

          jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.CTRL_MASK));
          jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-shutdown-16.png"))); // NOI18N
          jMenuItem1.setText("Dil!");
          jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
               }
          });
          jMenu1.add(jMenuItem1);

          jMenuBar1.add(jMenu1);

          setJMenuBar(jMenuBar1);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		
     }//GEN-LAST:event_formWindowOpened

     private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		
		
     }//GEN-LAST:event_formWindowClosing

     private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
          this.dispose();
     }//GEN-LAST:event_jMenuItem1ActionPerformed

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          FrameInventory frame = new FrameInventory() {
			@Override
			public Inventory getInventory() {
				return FrameMain.this.getInventory();
			}
		};
		this.jDesktopPane1.add(frame);
		
		frame.setVisible(true);
		try {
			frame.setMaximum(true);
		} 
		catch (PropertyVetoException ex) {
			Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
		}
     }//GEN-LAST:event_jButton2ActionPerformed
	
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JDesktopPane jDesktopPane1;
     private javax.swing.JMenu jMenu1;
     private javax.swing.JMenuBar jMenuBar1;
     private javax.swing.JMenuItem jMenuItem1;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JToolBar jToolBar1;
     private org.jdesktop.swingx.JXLabel jXLabel1;
     // End of variables declaration//GEN-END:variables
	
	
	
	
	
}
