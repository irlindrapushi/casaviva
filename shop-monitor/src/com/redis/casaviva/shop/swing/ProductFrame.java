package com.redis.casaviva.shop.swing;

import com.redis.casaviva.shop.dc.Warehouse;
import com.redis.casaviva.shop.swing.model.ProductTableModel;
import com.redis.utils.export.ExcelIO;
import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class ProductFrame extends javax.swing.JInternalFrame {
	
	private Warehouse warehouse;
	
	public ProductFrame() {
		initComponents();
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
		
	public void reload(){
		SwingWorker<Warehouse, Void> worker = new SwingWorker<Warehouse, Void>() {
			@Override
			protected Warehouse doInBackground() throws Exception {
				return Warehouse.getInstance();
			}			
		};
		
		worker.addPropertyChangeListener((PropertyChangeEvent evt) -> {
			
			if(evt.getNewValue().equals(SwingWorker.StateValue.STARTED)){
				reloadStarted();
			}
			
			if(evt.getNewValue().equals(SwingWorker.StateValue.DONE)){
				try {
					warehouse = worker.get();
					reloadFinished();
				} 
				catch (InterruptedException | ExecutionException ex) {
					Logger.getLogger(ProductFrame.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				
			}
		});		
		
		worker.execute();
	}
	
	private void reloadStarted(){
		jProgressBar1.setVisible(true);
		
		this.productTableModel1.setWarehouse(null);
		this.productTreeModel1.setWarehouse(null);
		
		jButton1.setEnabled(false); jButton2.setEnabled(false);
		
		jCheckBox1.setEnabled(false); jCheckBox2.setEnabled(false); jCheckBox3.setEnabled(false); 
		
		jCheckBox1.setSelected(false); jCheckBox2.setSelected(false); jCheckBox3.setSelected(true);
		
		jXSearchField1.setText(""); jXSearchField1.setEnabled(false);
	}
	
	private void reloadFinished(){
		this.productTableModel1.setWarehouse(warehouse);
		this.filterTableRows();
		
		
		this.productTreeModel1.setWarehouse(warehouse);
		this.jXTree1.collapseAll();
		this.jXTree1.expandRow(0);
		this.jXTree1.setSelectionRow(0);
		
		jButton1.setEnabled(true); jButton2.setEnabled(true);
		
		jCheckBox1.setEnabled(true);	jCheckBox2.setEnabled(true);	jCheckBox3.setEnabled(true);
		
		jXSearchField1.setEnabled(true);
		
		jProgressBar1.setVisible(false);
	}
	
	
	
	private void filterTableRows(){
		List<RowFilter<ProductTableModel, Integer>> filter = new ArrayList<>();
		
		filter.add(RowFilter.regexFilter("(?i)" + jXSearchField1.getText(), 0, 1, 6));
		
		if(!selectedCategory.isEmpty()) filter.add(RowFilter.regexFilter("^" + selectedCategory + "$", 2));
		if(!selectedType.isEmpty()) filter.add(RowFilter.regexFilter("^" + selectedType + "$", 3));
		if(!selectedBrand.isEmpty()) filter.add(RowFilter.regexFilter("^" + selectedBrand + "$", 4));
		if(!selectedModel.isEmpty()) filter.add(RowFilter.regexFilter("^" + selectedModel + "$", 5));
		
		if(!jCheckBox1.isSelected()) filter.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, -Double.MIN_VALUE, 11));
		if(!jCheckBox2.isSelected()) filter.add(RowFilter.numberFilter(RowFilter.ComparisonType.NOT_EQUAL, 0, 11));
		if(!jCheckBox3.isSelected()) filter.add(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, Double.MIN_VALUE, 11));
		
		this.jXTable1.setRowFilter(RowFilter.andFilter(filter));
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          productTableModel1 = new com.redis.casaviva.shop.swing.model.ProductTableModel();
          productTreeModel1 = new com.redis.casaviva.shop.swing.model.ProductTreeModel();
          jSplitPane1 = new javax.swing.JSplitPane();
          jPanel1 = new javax.swing.JPanel();
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTree1 = new org.jdesktop.swingx.JXTree();
          jPanel2 = new javax.swing.JPanel();
          jToolBar1 = new javax.swing.JToolBar();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(32767, 0));
          jCheckBox1 = new javax.swing.JCheckBox();
          filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          jCheckBox2 = new javax.swing.JCheckBox();
          filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          jCheckBox3 = new javax.swing.JCheckBox();
          filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
          jScrollPane1 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();
          jToolBar3 = new javax.swing.JToolBar();
          jButton2 = new javax.swing.JButton();
          jProgressBar1 = new javax.swing.JProgressBar();
          filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton1 = new javax.swing.JButton();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          setTitle("KATALOGU");
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

          jXTree1.setModel(productTreeModel1);
          jXTree1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jXTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
               public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    jXTree1ValueChanged(evt);
               }
          });
          jScrollPane2.setViewportView(jXTree1);

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

          jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          jCheckBox1.setForeground(java.awt.Color.red);
          jCheckBox1.setText("Gj. Neg.");
          jCheckBox1.setToolTipText("Listo artikujt me gjendje negative!");
          jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jCheckBox1.setFocusable(false);
          jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jCheckBox1.setIconTextGap(1);
          jCheckBox1.setOpaque(false);
          jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    jCheckBox1StateChanged(evt);
               }
          });
          jToolBar1.add(jCheckBox1);
          jToolBar1.add(filler6);

          jCheckBox2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          jCheckBox2.setForeground(java.awt.Color.orange);
          jCheckBox2.setText("Gj. Zero");
          jCheckBox2.setToolTipText("Listo artikujt me gjendje zero");
          jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jCheckBox2.setFocusable(false);
          jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jCheckBox2.setIconTextGap(1);
          jCheckBox2.setOpaque(false);
          jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    jCheckBox2StateChanged(evt);
               }
          });
          jToolBar1.add(jCheckBox2);
          jToolBar1.add(filler7);

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
          jCheckBox3.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    jCheckBox3StateChanged(evt);
               }
          });
          jToolBar1.add(jCheckBox3);
          jToolBar1.add(filler5);

          jPanel2.add(jToolBar1, java.awt.BorderLayout.PAGE_END);

          jXTable1.setModel(productTableModel1);
          jXTable1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jXTable1.setHighlighters(HighlighterFactory.createSimpleStriping());
          jScrollPane1.setViewportView(jXTable1);
          int width = this.getPreferredSize().width;
          for(int col = 0; col < productTableModel1.getColumnCount(); col++){
               jXTable1.packColumn(col, width * productTableModel1.getColumnWidth(col));
               jXTable1.getColumn(col).setCellRenderer(productTableModel1.getColumnTableCellRenderer(col));
          }

          //this.jXTable1.setHighlighters(HighlighterFactory.createSimpleStriping());

          jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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
          jProgressBar1.setOpaque(false);
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

          getContentPane().add(jToolBar3, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
          this.filterTableRows();
		
     }//GEN-LAST:event_jXSearchField1ActionPerformed

	private String selectedCategory = "", selectedType = "", selectedBrand = "", selectedModel = "";
     private void jXTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jXTree1ValueChanged
		if(evt.getPath() == null){
			jXTree1.setSelectionRow(0);
			return;
		}
		
		if(evt.getPath().getPathCount() > 0){
			selectedCategory = "";
			selectedType = "";
			selectedBrand = "";
			selectedModel = "";
		}
		if(evt.getPath().getPathCount() > 1){
			selectedCategory = String.valueOf(evt.getPath().getPathComponent(1));
		}
		if(evt.getPath().getPathCount() > 2){
			selectedType = String.valueOf(evt.getPath().getPathComponent(2));
		}
		if(evt.getPath().getPathCount() > 3){
			selectedBrand = String.valueOf(evt.getPath().getPathComponent(3));
		}
		if(evt.getPath().getPathCount() > 4){
			selectedModel = String.valueOf(evt.getPath().getPathComponent(4));
		}
		
		filterTableRows();
     }//GEN-LAST:event_jXTree1ValueChanged

     private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
          filterTableRows();
     }//GEN-LAST:event_jCheckBox1StateChanged

     private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged
          filterTableRows();
     }//GEN-LAST:event_jCheckBox2StateChanged

     private void jCheckBox3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox3StateChanged
          filterTableRows();
     }//GEN-LAST:event_jCheckBox3StateChanged

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          this.reload();
     }//GEN-LAST:event_jButton2ActionPerformed

     private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
          this.reload();
     }//GEN-LAST:event_formInternalFrameOpened

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		ExcelIO excelIO = new ExcelIO();
		File file = excelIO.export("product_export.xls", jXTable1);
		
		try {Desktop.getDesktop().open(file);}
		catch (IOException ex) {Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);}
     }//GEN-LAST:event_jButton1ActionPerformed

	


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler2;
     private javax.swing.Box.Filler filler3;
     private javax.swing.Box.Filler filler4;
     private javax.swing.Box.Filler filler5;
     private javax.swing.Box.Filler filler6;
     private javax.swing.Box.Filler filler7;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JCheckBox jCheckBox1;
     private javax.swing.JCheckBox jCheckBox2;
     private javax.swing.JCheckBox jCheckBox3;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JProgressBar jProgressBar1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar3;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable jXTable1;
     private org.jdesktop.swingx.JXTree jXTree1;
     private com.redis.casaviva.shop.swing.model.ProductTableModel productTableModel1;
     private com.redis.casaviva.shop.swing.model.ProductTreeModel productTreeModel1;
     // End of variables declaration//GEN-END:variables
	
	
	
	
}
