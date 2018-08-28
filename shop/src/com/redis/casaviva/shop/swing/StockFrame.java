package com.redis.casaviva.shop.swing;

import com.redis.casaviva.shop.dc.Stock;
import com.redis.casaviva.shop.remote.sql.SqlStock;
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
public class StockFrame extends javax.swing.JInternalFrame {
	
	private List entries;
	
	public StockFrame() {
		initComponents();
	}
	
	public void reload(){
		SwingWorker<List<Stock>, Void> worker = new SwingWorker<List<Stock>, Void>() {
			@Override
			protected List<Stock> doInBackground() throws Exception {
				return SqlStock.read();
			}			
		};
		
		worker.addPropertyChangeListener((PropertyChangeEvent evt) -> {
			
			if(evt.getNewValue().equals(SwingWorker.StateValue.STARTED)){
				reloadStarted();
			}
			
			if(evt.getNewValue().equals(SwingWorker.StateValue.DONE)){
				try {
					entries = worker.get();
				} 
				catch (InterruptedException | ExecutionException ex) {
					Logger.getLogger(StockFrame.class.getName()).log(Level.SEVERE, null, ex);
				}
				reloadFinished();
			}
		});		
		
		worker.execute();
	}
	
	private void reloadStarted(){
		jProgressBar1.setVisible(true);
		
		jButton1.setEnabled(false); jButton2.setEnabled(false);
		
		
		jXSearchField1.setText(""); jXSearchField1.setEnabled(false);
	}
	
	private void reloadFinished(){
		
		this.viewStockTableModel1.setEntries(entries);
		
		jButton1.setEnabled(true); jButton2.setEnabled(true);
		
		jXSearchField1.setEnabled(true);
		
		jProgressBar1.setVisible(false);
	}
	
	private void filterTableRows(){
		List<RowFilter<ProductTableModel, Integer>> filter = new ArrayList<>();
		
		filter.add(RowFilter.regexFilter("(?i)" + jXSearchField1.getText(), 0, 1, 2, 3));
		
		this.jXTable1.setRowFilter(RowFilter.andFilter(filter));
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          viewStockTableModel1 = new com.redis.casaviva.shop.swing.model.ViewStockTableModel();
          jToolBar1 = new javax.swing.JToolBar();
          filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
          jScrollPane1 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();
          jToolBar2 = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();
          jProgressBar1 = new javax.swing.JProgressBar();
          filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton2 = new javax.swing.JButton();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          setTitle("GJENDJET");
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

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);
          jToolBar1.add(filler4);

          jXSearchField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jXSearchField1.setColumns(20);
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setMaximumSize(new java.awt.Dimension(244, 24));
          jXSearchField1.setMinimumSize(new java.awt.Dimension(244, 24));
          jXSearchField1.setPrompt("Kerko ...");
          jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jXSearchField1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jXSearchField1);
          jToolBar1.add(filler1);
          jToolBar1.add(filler2);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);

          jXTable1.setModel(viewStockTableModel1);
          jXTable1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jXTable1.setHighlighters(HighlighterFactory.createSimpleStriping());
          jXTable1.setRowHeight(24);
          jScrollPane1.setViewportView(jXTable1);
          if (jXTable1.getColumnModel().getColumnCount() > 0) {
               jXTable1.getColumnModel().getColumn(0).setHeaderValue("Kodi");
               jXTable1.getColumnModel().getColumn(1).setHeaderValue("Barkodi");
               jXTable1.getColumnModel().getColumn(2).setHeaderValue("Pershkrimi");
               jXTable1.getColumnModel().getColumn(3).setHeaderValue("Njesia");
               jXTable1.getColumnModel().getColumn(4).setHeaderValue("Qendra");
               jXTable1.getColumnModel().getColumn(5).setHeaderValue("Pajtoni");
               jXTable1.getColumnModel().getColumn(6).setHeaderValue("Total");
          }
          this.jXTable1.setDefaultRenderer(Integer.class, new com.redis.casaviva.shop.swing.renderer.IntegerCellRenderer());

          this.jXTable1.packColumn(0, 100);
          this.jXTable1.packColumn(1, 100);
          this.jXTable1.packColumn(2, 500);
          this.jXTable1.packColumn(3, 50);
          this.jXTable1.packColumn(4, 50);
          this.jXTable1.packColumn(5, 50);
          this.jXTable1.packColumn(6, 50);

          getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/Synchronize-24.png"))); // NOI18N
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
          jToolBar2.add(jButton1);

          jProgressBar1.setBorderPainted(false);
          jProgressBar1.setIndeterminate(true);
          jToolBar2.add(jProgressBar1);
          jToolBar2.add(filler3);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/excel_24.png"))); // NOI18N
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
          jToolBar2.add(jButton2);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          this.reload();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		ExcelIO excelIO = new ExcelIO();
		File file = excelIO.export("stock_export.xls", jXTable1);
		
		try {Desktop.getDesktop().open(file);}
		catch (IOException ex) {Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);}
     }//GEN-LAST:event_jButton2ActionPerformed

     private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
          this.jButton1ActionPerformed(null);
     }//GEN-LAST:event_formInternalFrameOpened

     private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
          this.jXTable1.setRowFilter(RowFilter.regexFilter("(?i)" + jXSearchField1.getText(), 0, 1, 2));
     }//GEN-LAST:event_jXSearchField1ActionPerformed

	


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.Box.Filler filler3;
     private javax.swing.Box.Filler filler4;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JProgressBar jProgressBar1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable jXTable1;
     private com.redis.casaviva.shop.swing.model.ViewStockTableModel viewStockTableModel1;
     // End of variables declaration//GEN-END:variables
}
