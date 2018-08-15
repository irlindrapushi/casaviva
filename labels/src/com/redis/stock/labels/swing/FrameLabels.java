/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.labels.swing;

import com.redis.stock.labels.core.Article;
import com.redis.stock.labels.core.BarcodeLabel;
import com.redis.stock.labels.mssql.ArticleSQL;
import com.redis.stock.labels.mssql.SQL;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Redjan Shabani
 */
public class FrameLabels extends javax.swing.JFrame {
	
	private final List<Article> articles = new ArrayList();
	
	public FrameLabels() {
		initComponents();
		
		
		
		this.jXTable1.getColumn(6).setCellRenderer(new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
				
				if(o instanceof Integer) {
					setHorizontalAlignment(JLabel.RIGHT);
					
					if((int) o == 0)
						setText("<html><b color='red'>"+o+"</b></html>");
					else
						setText("<html><b color='green'>"+o+"</b></html>");
				}
				
				return this;
			}
			
		});
	}
	
	private void reload() {
		
		
		
		
		
		
		Set<String> sectors = new TreeSet<>();
		
		DefaultTableModel model = (DefaultTableModel) this.jXTable1.getModel();
		model.setRowCount(0);		
		ArticleSQL.forEach(article -> {
			Object[] row = new Object[]{article.getCode(), article.getBarcode(), article.getSector(), article.getCategory(), article.getDescription(), article.getSupplier(), 0};
			model.addRow(row);
			
			if(article.getSector() != null)
				sectors.add(article.getSector());
		});
		
		
		
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode();
		sectors.forEach(sector -> node.add(new DefaultMutableTreeNode(sector)));
		this.jXTree1.setModel(new DefaultTreeModel(node));
		
		
		
		
		
		
		
		
		
		this.jXSearchField1.setText("");
		this.jXTree1.setSelectionRow(0);
		
	}
	
	
	
	private void filter() {
		Set<RowFilter<TableModel, Integer>> filters= new HashSet<>();
		
		filters.add(RowFilter.regexFilter("(?i)" + this.jXSearchField1.getText(), 0, 1, 2, 3, 4, 5));
		
		if(jXTree1.getSelectionPath() != null)
			filters.add(RowFilter.regexFilter("(?i)" + String.valueOf(jXTree1.getSelectionPath().getLastPathComponent()), 2));
		
		this.jXTable1.setRowFilter(RowFilter.andFilter(filters));
	}
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelArticles = new javax.swing.JPanel();
          jToolBar1 = new javax.swing.JToolBar();
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton3 = new javax.swing.JButton();
          jScrollPane1 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();
          jToolBar2 = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton2 = new javax.swing.JButton();
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTree1 = new org.jdesktop.swingx.JXTree();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle("CATALOG - LABELS");
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          setPreferredSize(new java.awt.Dimension(1600, 900));
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
               }
               public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
               }
          });

          panelArticles.setBorder(javax.swing.BorderFactory.createTitledBorder("ARTIKUJT"));
          panelArticles.setLayout(new java.awt.BorderLayout());

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);

          jXSearchField1.setColumns(25);
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setMaximumSize(new java.awt.Dimension(250, 2147483647));
          jXSearchField1.setPrompt("Kerko ...");
          jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jXSearchField1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jXSearchField1);
          jToolBar1.add(filler1);

          jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-broom-16.png"))); // NOI18N
          jButton3.setFocusable(false);
          jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton3);

          panelArticles.add(jToolBar1, java.awt.BorderLayout.PAGE_END);

          jXTable1.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {
                    "Kodi", "Barkodi", "Sektori", "Kategoria", "Pershkrimi", "Fornitori", "Sasia"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
          jXTable1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jXTable1MouseClicked(evt);
               }
          });
          jScrollPane1.setViewportView(jXTable1);
          if (jXTable1.getColumnModel().getColumnCount() > 0) {
               jXTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
               jXTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
               jXTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
               jXTable1.getColumnModel().getColumn(4).setPreferredWidth(450);
               jXTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
               jXTable1.getColumnModel().getColumn(6).setPreferredWidth(10);
          }

          panelArticles.add(jScrollPane1, java.awt.BorderLayout.CENTER);

          getContentPane().add(panelArticles, java.awt.BorderLayout.CENTER);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-synchronize-24.png"))); // NOI18N
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar2.add(jButton1);
          jToolBar2.add(filler2);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-barcode-scanner-24.png"))); // NOI18N
          jButton2.setToolTipText("PRINTO ETIKETAT");
          jButton2.setFocusable(false);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
               }
          });
          jToolBar2.add(jButton2);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

          jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("SEKTORET"));
          jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 324));

          javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
          jXTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
          jXTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
               public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    jXTree1ValueChanged(evt);
               }
          });
          jScrollPane2.setViewportView(jXTree1);

          getContentPane().add(jScrollPane2, java.awt.BorderLayout.WEST);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          this.reload();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		this.reload();
     }//GEN-LAST:event_formWindowOpened

     private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

     }//GEN-LAST:event_formWindowClosing

     private void jXTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable1MouseClicked
          int row = this.jXTable1.rowAtPoint(evt.getPoint());
		int col = this.jXTable1.columnAtPoint(evt.getPoint());
		if(row == -1 || col != 6)
			return;
		
		row = this.jXTable1.convertRowIndexToModel(row);
		DefaultTableModel model = (DefaultTableModel) this.jXTable1.getModel();
		
		if(SwingUtilities.isLeftMouseButton(evt)) {
			int count = (int) model.getValueAt(row, 6) + 1;
			
			model.setValueAt(count, row, 6);
		}
		
		if(SwingUtilities.isRightMouseButton(evt)) {
			int count = (int) model.getValueAt(row, 6);
			count = count <= 0 ? 0 : count - 1;
			model.setValueAt(count, row, 6);
		}
     }//GEN-LAST:event_jXTable1MouseClicked

     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          this.jXTree1.setSelectionRow(0);
		this.jXSearchField1.setText("");
		
		DefaultTableModel model = (DefaultTableModel) this.jXTable1.getModel();
		for(int idx = 0; idx < model.getRowCount(); idx++) {
			model.setValueAt(0, idx, 6);
		}
     }//GEN-LAST:event_jButton3ActionPerformed

     private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
          this.filter();
     }//GEN-LAST:event_jXSearchField1ActionPerformed

     private void jXTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jXTree1ValueChanged
          this.filter();
     }//GEN-LAST:event_jXTree1ValueChanged

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          List<BarcodeLabel> labels = new ArrayList<>();
		
		DefaultTableModel model = (DefaultTableModel) this.jXTable1.getModel();
		for(int idx = 0; idx < model.getRowCount(); idx++) {
			int counter = (int) model.getValueAt(idx, 6);
			for(int c = 0; c < counter; c++)
				labels.add(new BarcodeLabel((String) model.getValueAt(idx, 0), (String) model.getValueAt(idx, 1), (String) model.getValueAt(idx, 2)));
		}
		
		File file = BarcodeLabel.generatePDF(labels);
		
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException ex) {
			Logger.getLogger(FrameLabels.class.getName()).log(Level.SEVERE, null, ex);
		}
     }//GEN-LAST:event_jButton2ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		
		java.awt.EventQueue.invokeLater(() -> {
			new FrameLabels().setVisible(true);
		});
	}

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable jXTable1;
     private org.jdesktop.swingx.JXTree jXTree1;
     private javax.swing.JPanel panelArticles;
     // End of variables declaration//GEN-END:variables
}
