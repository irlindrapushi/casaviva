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
package com.redis.stock.gui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Redjan Shabani
 */
public abstract class FrameApp extends javax.swing.JFrame {
	
	public FrameApp() {
		this.initComponents();
	}	
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelFooter = new javax.swing.JPanel();
          jXLabel2 = new org.jdesktop.swingx.JXLabel();
          toolBarHeader = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jXLabel1 = new org.jdesktop.swingx.JXLabel();
          desktopPane = new javax.swing.JDesktopPane();
          jMenuBar1 = new javax.swing.JMenuBar();
          jMenu1 = new javax.swing.JMenu();
          jMenuItem2 = new javax.swing.JMenuItem();
          jMenuItem1 = new javax.swing.JMenuItem();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle("REDIS STOCK");
          setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowClosed(java.awt.event.WindowEvent evt) {
                    formWindowClosed(evt);
               }
               public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
               }
          });

          panelFooter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

          java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("text_res_alb"); // NOI18N
          jXLabel2.setText(bundle.getString("footer_signature")); // NOI18N
          panelFooter.add(jXLabel2);

          getContentPane().add(panelFooter, java.awt.BorderLayout.PAGE_END);

          toolBarHeader.setFloatable(false);
          toolBarHeader.setRollover(true);

          jButton1.setText("Artikujt");
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          toolBarHeader.add(jButton1);
          toolBarHeader.add(filler1);

          jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jXLabel1.setText("01:36:51");
          jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
          jXLabel1.addAncestorListener(new javax.swing.event.AncestorListener() {
               public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
               }
               public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                    jXLabel1AncestorAdded(evt);
               }
               public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
               }
          });
          toolBarHeader.add(jXLabel1);

          getContentPane().add(toolBarHeader, java.awt.BorderLayout.PAGE_START);
          getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

          jMenu1.setText("File");

          jMenuItem2.setText("Konfigurime");
          jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem2ActionPerformed(evt);
               }
          });
          jMenu1.add(jMenuItem2);

          jMenuItem1.setText("Dalje");
          jMenu1.add(jMenuItem1);

          jMenuBar1.add(jMenu1);

          setJMenuBar(jMenuBar1);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jXLabel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jXLabel1AncestorAdded
          new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				jXLabel1.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			}
		}, 0, 1000);
     }//GEN-LAST:event_jXLabel1AncestorAdded

     private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         
		
     }//GEN-LAST:event_jMenuItem2ActionPerformed

     private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		
		
     }//GEN-LAST:event_formWindowOpened

     private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
		
		
     }//GEN-LAST:event_formWindowClosed

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          
		
     }//GEN-LAST:event_jButton1ActionPerformed
	
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JDesktopPane desktopPane;
     private javax.swing.Box.Filler filler1;
     private javax.swing.JButton jButton1;
     private javax.swing.JMenu jMenu1;
     private javax.swing.JMenuBar jMenuBar1;
     private javax.swing.JMenuItem jMenuItem1;
     private javax.swing.JMenuItem jMenuItem2;
     private org.jdesktop.swingx.JXLabel jXLabel1;
     private org.jdesktop.swingx.JXLabel jXLabel2;
     private javax.swing.JPanel panelFooter;
     private javax.swing.JToolBar toolBarHeader;
     // End of variables declaration//GEN-END:variables
}
