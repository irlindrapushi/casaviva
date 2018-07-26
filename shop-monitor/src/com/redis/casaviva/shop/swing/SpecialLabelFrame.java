package com.redis.casaviva.shop.swing;

import com.redis.casaviva.shop.dc.Product;
import com.redis.casaviva.shop.dc.Warehouse;
import com.redis.casaviva.shop.export.LabelHTMLExport;
import com.redis.casaviva.shop.swing.model.ProductTableModel;
import com.redis.utils.export.ExcelIO;
import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SpecialLabelFrame extends javax.swing.JInternalFrame {
	
	private Warehouse warehouse;
	
	public SpecialLabelFrame() {
		initComponents();
	}
	
	
	public void reload(){
		this.warehouse = Warehouse.getInstance();
				
		this.treeModel.setWarehouse(warehouse);
		this.tree.setSelectionRow(0);
	}
	
	private void filterTableRows(){
		List<RowFilter<ProductTableModel, Integer>> filter = new ArrayList<>();
		
		filter.add(RowFilter.regexFilter("(?i)" + jXSearchField1.getText(), 1, 2, 3, 4, 5, 6));
		
		if(jCheckBox3.isSelected()) 
			filter.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, Double.MIN_VALUE, 9));
		
		this.table.setRowFilter(RowFilter.andFilter(filter));
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          tableModel = new com.redis.casaviva.shop.swing.model.SpecialLabelTableModel();
          treeModel = new com.redis.casaviva.shop.swing.model.SpecialLabelTreeModel();
          jSplitPane1 = new javax.swing.JSplitPane();
          jPanel1 = new javax.swing.JPanel();
          jScrollPane2 = new javax.swing.JScrollPane();
          tree = new org.jdesktop.swingx.JXTree();
          jPanel2 = new javax.swing.JPanel();
          jToolBar1 = new javax.swing.JToolBar();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(32767, 0));
          jCheckBox3 = new javax.swing.JCheckBox();
          filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
          jScrollPane1 = new javax.swing.JScrollPane();
          table = new org.jdesktop.swingx.JXTable();
          jToolBar2 = new javax.swing.JToolBar();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton5 = new javax.swing.JButton();
          jButton4 = new javax.swing.JButton();
          jToolBar3 = new javax.swing.JToolBar();
          jButton2 = new javax.swing.JButton();
          jProgressBar1 = new javax.swing.JProgressBar();
          filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton1 = new javax.swing.JButton();
          jButton3 = new javax.swing.JButton();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          setTitle("ETIKETAT E OFERTAVE");
          setPreferredSize(new java.awt.Dimension(800, 450));
          addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
               public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
               }
               public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                    formInternalFrameOpened(evt);
               }
          });

          jSplitPane1.setDividerLocation(250);

          jPanel1.setLayout(new java.awt.BorderLayout());

          tree.setModel(treeModel);
          tree.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
               public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    treeValueChanged(evt);
               }
          });
          jScrollPane2.setViewportView(tree);

          jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

          jSplitPane1.setLeftComponent(jPanel1);

          jPanel2.setLayout(new java.awt.BorderLayout());

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);
          jToolBar1.add(filler2);

          jXSearchField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jXSearchField1.setColumns(20);
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jXSearchField1.setMaximumSize(new java.awt.Dimension(200, 20));
          jXSearchField1.setPrompt("Kerko ...");
          jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jXSearchField1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jXSearchField1);
          jToolBar1.add(filler3);

          jCheckBox3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          jCheckBox3.setForeground(new java.awt.Color(0, 151, 0));
          jCheckBox3.setSelected(true);
          jCheckBox3.setText("Gj. Poz.");
          jCheckBox3.setToolTipText("Listo artikujt me gjendje pozitive!");
          jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jCheckBox3.setFocusable(false);
          jCheckBox3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jCheckBox3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jCheckBox3.setIconTextGap(1);
          jCheckBox3.setOpaque(false);
          jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
               public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jCheckBox3ItemStateChanged(evt);
               }
          });
          jToolBar1.add(jCheckBox3);
          jToolBar1.add(filler5);

          jPanel2.add(jToolBar1, java.awt.BorderLayout.SOUTH);

          table.setModel(tableModel);
          table.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          table.setHighlighters(HighlighterFactory.createSimpleStriping());
          table.setRowHeight(24);
          table.setShowVerticalLines(false);
          jScrollPane1.setViewportView(table);
          if (table.getColumnModel().getColumnCount() > 0) {
               table.getColumnModel().getColumn(0).setHeaderValue("Publikimi");
               table.getColumnModel().getColumn(1).setHeaderValue("Kodi");
               table.getColumnModel().getColumn(2).setHeaderValue("Kategoria");
               table.getColumnModel().getColumn(3).setHeaderValue("Tipi");
               table.getColumnModel().getColumn(4).setHeaderValue("Marka");
               table.getColumnModel().getColumn(5).setHeaderValue("Modeli");
               table.getColumnModel().getColumn(6).setHeaderValue("Cmimi");
               table.getColumnModel().getColumn(7).setHeaderValue("Oferta");
               table.getColumnModel().getColumn(8).setHeaderValue("Skonto");
               table.getColumnModel().getColumn(9).setHeaderValue("Gjendje");
               table.getColumnModel().getColumn(10).setHeaderValue("");
          }

          int width = this.getPreferredSize().width;
          for(int col = 0; col < Product.SpecialLabel.FIELD_COUNT; col++){
               table.packColumn(col, width * tableModel.getColumnWidth(col));

          }

          table.getColumn(0).setCellRenderer(tableModel.getColumnTableCellRenderer(0));
          table.getColumn(6).setCellRenderer(tableModel.getColumnTableCellRenderer(6));
          table.getColumn(7).setCellRenderer(tableModel.getColumnTableCellRenderer(7));
          table.getColumn(8).setCellRenderer(tableModel.getColumnTableCellRenderer(8));
          table.getColumn(9).setCellRenderer(tableModel.getColumnTableCellRenderer(9));
          //this.jXTable1.setHighlighters(HighlighterFactory.createSimpleStriping());

          jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);
          jToolBar2.add(filler1);

          jButton5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/icons8-Check All-16.png"))); // NOI18N
          jButton5.setFocusable(false);
          jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton5.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
               }
          });
          jToolBar2.add(jButton5);

          jButton4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/icons8-Uncheck All-16.png"))); // NOI18N
          jButton4.setFocusable(false);
          jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton4.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
               }
          });
          jToolBar2.add(jButton4);

          jPanel2.add(jToolBar2, java.awt.BorderLayout.NORTH);

          jSplitPane1.setRightComponent(jPanel2);

          getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

          jToolBar3.setFloatable(false);
          jToolBar3.setRollover(true);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/Synchronize-24.png"))); // NOI18N
          jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jButton2.setFocusable(false);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton2.setOpaque(false);
          jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
               }
          });
          jToolBar3.add(jButton2);

          jProgressBar1.setBorderPainted(false);
          jProgressBar1.setIndeterminate(true);
          jToolBar3.add(jProgressBar1);
          jToolBar3.add(filler4);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/excel_24.png"))); // NOI18N
          jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setOpaque(false);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar3.add(jButton1);

          jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/print_24.png"))); // NOI18N
          jButton3.setFocusable(false);
          jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
               }
          });
          jToolBar3.add(jButton3);

          getContentPane().add(jToolBar3, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
          this.filterTableRows();
		
     }//GEN-LAST:event_jXSearchField1ActionPerformed

     private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
		if(evt.getPath() == null){
			tree.setSelectionRow(0);
			return;
		}
		jXSearchField1.setText("");
		
		jCheckBox3.setSelected(true);
		this.tableModel.setLabels(this.treeModel.getLabels((DefaultMutableTreeNode) evt.getPath().getLastPathComponent()));
		
		filterTableRows();
     }//GEN-LAST:event_treeValueChanged

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		jXSearchField1.setText("");
		
		jCheckBox3.setSelected(true);
		
		
		SwingWorker worker = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				reload();
				return null;
			}
		};
		
		worker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
			jProgressBar1.setVisible(!evt1.getNewValue().equals(SwingWorker.StateValue.DONE));
			jButton1.setEnabled(evt1.getNewValue().equals(SwingWorker.StateValue.DONE));
			jButton2.setEnabled(evt1.getNewValue().equals(SwingWorker.StateValue.DONE));
			
			jXSearchField1.setEnabled(evt1.getNewValue().equals(SwingWorker.StateValue.DONE));
			
			
			jCheckBox3.setEnabled(evt1.getNewValue().equals(SwingWorker.StateValue.DONE));
		});
		
		worker.execute();
		
     }//GEN-LAST:event_jButton2ActionPerformed

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          ExcelIO excelIO = new ExcelIO();
		File file = excelIO.export("label_export.xls", table);
		
		try {Desktop.getDesktop().open(file);}
		catch (IOException ex) {Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);}
		
     }//GEN-LAST:event_jButton1ActionPerformed

     private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
          this.jButton2ActionPerformed(null);
     }//GEN-LAST:event_formInternalFrameOpened

     private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
          
		filterTableRows();
     }//GEN-LAST:event_jCheckBox3ItemStateChanged

     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          File file = LabelHTMLExport.exportHtml("label_export.html", tableModel.getLabels());
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException ex) {
			Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
     }//GEN-LAST:event_jButton3ActionPerformed

     private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          for(int i=0; i<this.table.getRowCount();i++)
          table.setValueAt(false, i, 10);
     }//GEN-LAST:event_jButton4ActionPerformed

     private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
          for(int i=0; i<this.table.getRowCount();i++)
          table.setValueAt(true, i, 10);
     }//GEN-LAST:event_jButton5ActionPerformed

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.Box.Filler filler3;
     private javax.swing.Box.Filler filler4;
     private javax.swing.Box.Filler filler5;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JButton jButton4;
     private javax.swing.JButton jButton5;
     private javax.swing.JCheckBox jCheckBox3;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JProgressBar jProgressBar1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private javax.swing.JToolBar jToolBar3;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable table;
     private com.redis.casaviva.shop.swing.model.SpecialLabelTableModel tableModel;
     private org.jdesktop.swingx.JXTree tree;
     private com.redis.casaviva.shop.swing.model.SpecialLabelTreeModel treeModel;
     // End of variables declaration//GEN-END:variables
}
