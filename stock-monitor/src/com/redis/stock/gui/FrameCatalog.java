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

/**
 *
 * @author Redjan Shabani
 */
public abstract class FrameCatalog extends javax.swing.JInternalFrame {
	
	public FrameCatalog() {
		initComponents();
	}
	
//	public abstract Catalog getCatalog();
//	
//	private void reload() {
//		Catalog.Root root = this.getCatalog().root();
//		
//		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root);
//		root.childs().forEach(sector -> {
//			DefaultMutableTreeNode sectorNode = new DefaultMutableTreeNode(sector);
//			
//			sector.childs().forEach(category -> {
//				DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category);
//				
//				sectorNode.add(categoryNode);
//			});
//			
//			rootNode.add(sectorNode);
//		});
//		
//		this.jXTree1.setModel(new DefaultTreeModel(rootNode));
//	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jSplitPane1 = new javax.swing.JSplitPane();
          jPanel2 = new javax.swing.JPanel();
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTree1 = new org.jdesktop.swingx.JXTree();
          jToolBar1 = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          setPreferredSize(new java.awt.Dimension(800, 450));

          jSplitPane1.setDividerLocation(300);

          jPanel2.setLayout(new java.awt.BorderLayout());

          jScrollPane2.setViewportView(jXTree1);

          jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

          jSplitPane1.setLeftComponent(jPanel2);

          getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

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

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//          this.reload();
     }//GEN-LAST:event_jButton1ActionPerformed


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton jButton1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JToolBar jToolBar1;
     private org.jdesktop.swingx.JXTree jXTree1;
     // End of variables declaration//GEN-END:variables
}
