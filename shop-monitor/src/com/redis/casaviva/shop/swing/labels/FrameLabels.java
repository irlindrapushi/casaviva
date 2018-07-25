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
package com.redis.casaviva.shop.swing.labels;

import com.redis.casaviva.shop.core.Article;
import com.redis.utils.Dataset;

/**
 *
 * @author Redjan Shabani
 */
public abstract class FrameLabels extends javax.swing.JInternalFrame {
	
	public FrameLabels() {
		initComponents();
	}
	
	public abstract Dataset<Article> articles();
	
	private void clear() {
		this.tableModel.clear();
	}
	
	private void load() {
		this.articles().forEach(article -> {
			this.tableModel.insert(article);
		});
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          tableModel = new com.redis.casaviva.shop.swing.labels.TableModelLabels();
          jScrollPane1 = new javax.swing.JScrollPane();
          jXTree1 = new org.jdesktop.swingx.JXTree();
          jPanel1 = new javax.swing.JPanel();
          jToolBar1 = new javax.swing.JToolBar();
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          checkBoxPozitiveQuantity = new javax.swing.JCheckBox();
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();
          jToolBar2 = new javax.swing.JToolBar();
          jButton3 = new javax.swing.JButton();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton1 = new javax.swing.JButton();
          jButton2 = new javax.swing.JButton();

          setPreferredSize(new java.awt.Dimension(800, 450));

          jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 324));
          jScrollPane1.setViewportView(jXTree1);

          getContentPane().add(jScrollPane1, java.awt.BorderLayout.LINE_START);

          jPanel1.setLayout(new java.awt.BorderLayout());

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);

          jXSearchField1.setColumns(25);
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setMaximumSize(new java.awt.Dimension(220, 22));
          jXSearchField1.setMinimumSize(new java.awt.Dimension(220, 22));
          jXSearchField1.setPreferredSize(new java.awt.Dimension(220, 22));
          java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("string"); // NOI18N
          jXSearchField1.setPrompt(bundle.getString("text_search")); // NOI18N
          jToolBar1.add(jXSearchField1);
          jToolBar1.add(filler2);

          checkBoxPozitiveQuantity.setSelected(true);
          checkBoxPozitiveQuantity.setText(bundle.getString("text_pozitive_quantity")); // NOI18N
          checkBoxPozitiveQuantity.setFocusable(false);
          checkBoxPozitiveQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          checkBoxPozitiveQuantity.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jToolBar1.add(checkBoxPozitiveQuantity);

          jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_END);

          jXTable1.setModel(tableModel);
          jXTable1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jXTable1MouseClicked(evt);
               }
          });
          jScrollPane2.setViewportView(jXTable1);
          if (jXTable1.getColumnModel().getColumnCount() > 0) {
               jXTable1.getColumnModel().getColumn(0).setHeaderValue("Foto");
               jXTable1.getColumnModel().getColumn(1).setHeaderValue("Kodi");
               jXTable1.getColumnModel().getColumn(2).setHeaderValue("Barkodi");
               jXTable1.getColumnModel().getColumn(3).setHeaderValue("Sektori");
               jXTable1.getColumnModel().getColumn(4).setHeaderValue("Kategoria");
               jXTable1.getColumnModel().getColumn(5).setHeaderValue("Emertimi");
               jXTable1.getColumnModel().getColumn(6).setHeaderValue("Njesia");
               jXTable1.getColumnModel().getColumn(7).setHeaderValue("Sasia");
               jXTable1.getColumnModel().getColumn(8).setHeaderValue("Cmimi");
               jXTable1.getColumnModel().getColumn(9).setHeaderValue("Oferta");
               jXTable1.getColumnModel().getColumn(10).setHeaderValue("Skonto");
               jXTable1.getColumnModel().getColumn(11).setHeaderValue("Data/Ora");
          }

          jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

          getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);

          jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-synchronize-24.png"))); // NOI18N
          jButton3.setFocusable(false);
          jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
               }
          });
          jToolBar2.add(jButton3);
          jToolBar2.add(filler1);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-checked-checkbox-24.png"))); // NOI18N
          jButton1.setToolTipText(bundle.getString("text_check_all")); // NOI18N
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jToolBar2.add(jButton1);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-unchecked-checkbox-24.png"))); // NOI18N
          jButton2.setToolTipText(bundle.getString("text_select_none")); // NOI18N
          jButton2.setFocusable(false);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jToolBar2.add(jButton2);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          this.clear();
		this.load();
     }//GEN-LAST:event_jButton3ActionPerformed

     private void jXTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable1MouseClicked
          int row = this.jXTable1.rowAtPoint(evt.getPoint());
		
		if(row > -1) {
			int col = this.jXTable1.columnAtPoint(evt.getPoint());
			if(col == 12) {
				this.tableModel.check(this.jXTable1.convertRowIndexToModel(row));
			}
		}
     }//GEN-LAST:event_jXTable1MouseClicked


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JCheckBox checkBoxPozitiveQuantity;
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable jXTable1;
     private org.jdesktop.swingx.JXTree jXTree1;
     private com.redis.casaviva.shop.swing.labels.TableModelLabels tableModel;
     // End of variables declaration//GEN-END:variables
}
