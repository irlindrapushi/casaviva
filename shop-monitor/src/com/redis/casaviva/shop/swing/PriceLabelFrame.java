/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.swing;

import com.redis.casaviva.shop.dc.Feature;
import com.redis.casaviva.shop.dc.Product;
import com.redis.casaviva.shop.dc.Warehouse;
import com.redis.casaviva.shop.export.LabelHTMLExport;
import com.redis.utils.export.ExcelIO;
import java.awt.Component;
import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.RowFilter;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author Redjan Shabani
 */
public class PriceLabelFrame extends javax.swing.JInternalFrame {
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
	
//	private final Set<Feature> features = new TreeSet<>();
	private final List<Product> products = new ArrayList<>();
	Map<String, Map<String,String>> features = new HashMap();
		
	public PriceLabelFrame() {
		initComponents();
		
		this.table.setDefaultRenderer(Instant.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
				
				if(o instanceof Instant) {
					this.setHorizontalAlignment(CENTER);
					this.setText(DATE_FORMAT.format(Date.from((Instant) o)));
				}
				
				
				return this;
			}
			
		});
		
		
		this.table.setDefaultRenderer(Number.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
				
				if(o instanceof Number) {
					this.setHorizontalAlignment(RIGHT);
					this.setText(DECIMAL_FORMAT.format(o));
				}
				
				
				return this;
			}
			
		});
		
		this.tree.setCellRenderer(new DefaultTreeCellRenderer(){
			@Override
			public Component getTreeCellRendererComponent(JTree jtree, Object o, boolean bln, boolean bln1, boolean bln2, int i, boolean bln3) {
				super.getTreeCellRendererComponent(jtree, o, bln, bln1, bln2, i, bln3);
				
				if(o instanceof Instant)
					setText(DATE_FORMAT.format(Date.from((Instant) o)));
				
				
				return this;
			}
		});
	}
	
	private void reload() {
		
		
		
		products.addAll(Warehouse.getInstance().getProducts());
		System.out.println(products.size());
//		features.addAll(Feature.read());
		
		Feature.read().forEach(feature -> {
			features.putIfAbsent(feature.getCode(), new HashMap<>());
			features.get(feature.getCode()).put(feature.getName(), feature.getValue());
		});
		
		
		this.table.setModel(new TableModel());
		
		
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("- Te Gjitha -");
		products.stream().map(Product::getPriceInstant).distinct().sorted(Collections.reverseOrder()).forEach(instant -> {
			treeNode.add(new DefaultMutableTreeNode(instant){
				@Override
				public String toString() {
					return DATE_FORMAT.format(Date.from(instant));
				}
			});
		});
		this.tree.setModel(new DefaultTreeModel(treeNode));
		
		
		
		
		
		
		
		this.filterTableRows();
	}
	
	
	private void filterTableRows(){
		List<RowFilter<TableModel, Integer>> filter = new ArrayList<>();
		
		filter.add(RowFilter.regexFilter("(?i)" + jXSearchField1.getText(), 0, 1));
		
		if(jCheckBox3.isSelected()) 
			filter.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, Double.MIN_VALUE, 6));
		
		if(tree.getSelectionPath()!=null && tree.getSelectionPath().getPathCount() == 2){
			Instant selectedInstant = (Instant) ((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent()).getUserObject();
			filter.add(new RowFilter<TableModel, Integer>() {
				@Override
				public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
					Instant instant = (Instant) entry.getModel().getValueAt(entry.getIdentifier(), 5);
					return selectedInstant.equals(instant);
				}
			});
		}
				
		this.table.setRowFilter(RowFilter.andFilter(filter));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	String[] colNames =  new String [] {"Kodi", "Barkodi", "Pershkrimi", "Detajet", "Cmimi", "Data/Ora", "Gjendje", ""};
	Class[] colClasses = new Class [] {String.class,String.class,String.class,String.class,Double.class,Instant.class,Double.class,Boolean.class};
	boolean[] colEdits = new boolean [] {false, false, false, false, false, false, false, true};
	
	private class TableModel extends DefaultTableModel {

		public TableModel() {
			super(colNames, products.size());
		}
		
		@Override
		public Class getColumnClass(int columnIndex) {
			return colClasses[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return colEdits[columnIndex];
		}

		@Override
		public Object getValueAt(int i, int i1) {
			Product p = products.get(i);
			switch(i1){
				case 0: return p.getCode();
				case 1: return p.getBarcode();
				case 2: return p.getDescription();
				case 3: return features.getOrDefault(p.getCode(), new HashMap());
				case 4: return p.getPrice();
				case 5: return p.getPriceInstant();
				case 6: return p.getStock();
				case 7: return super.getValueAt(i, i1) == null ? false : super.getValueAt(i, i1);
			}
			return super.getValueAt(i, i1);
		}
		
		public boolean isSelected(int i) {
			return (boolean) this.getValueAt(i, 7);
		}
		
		public Product.PriceLabel getLabel(int row) {
			Product.PriceLabel label = new Product.PriceLabel(
				(Instant) this.getValueAt(row, 5),
				(String) this.getValueAt(row, 0), 
				(String) this.getValueAt(row, 1), 
				(String) this.getValueAt(row, 2), 
				(double)this.getValueAt(row, 4),
				(double) this.getValueAt(row, 6)
			);
			
			Map<String, String> features = (Map<String, String>) this.getValueAt(row, 3);
			features.forEach((k,v) -> {
				label.put(k, v);
			});
			
			return label;
		}
		
		public List<Product.PriceLabel> getPriceLabels() {
			List<Product.PriceLabel> labels = new ArrayList<>();
			
			for(int row = 0; row < this.getRowCount(); row++)
				if(isSelected(row))
					labels.add(this.getLabel(row));
			
			return labels;
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jToolBar3 = new javax.swing.JToolBar();
          jButton2 = new javax.swing.JButton();
          jProgressBar1 = new javax.swing.JProgressBar();
          filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton1 = new javax.swing.JButton();
          jButton3 = new javax.swing.JButton();
          jButton6 = new javax.swing.JButton();
          jButton7 = new javax.swing.JButton();
          jSplitPane1 = new javax.swing.JSplitPane();
          jPanel1 = new javax.swing.JPanel();
          jScrollPane2 = new javax.swing.JScrollPane();
          table = new org.jdesktop.swingx.JXTable();
          jToolBar5 = new javax.swing.JToolBar();
          filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton5 = new javax.swing.JButton();
          jButton4 = new javax.swing.JButton();
          jToolBar4 = new javax.swing.JToolBar();
          jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jCheckBox3 = new javax.swing.JCheckBox();
          jPanel2 = new javax.swing.JPanel();
          jScrollPane3 = new javax.swing.JScrollPane();
          tree = new org.jdesktop.swingx.JXTree();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          setTitle("ETIKETAT E CMIMEVE");
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

          jToolBar3.setFloatable(false);
          jToolBar3.setRollover(true);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/Synchronize-24.png"))); // NOI18N
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
          jButton3.setText("140mm X 63mm");
          jButton3.setFocusable(false);
          jButton3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
               }
          });
          jToolBar3.add(jButton3);

          jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/print_24.png"))); // NOI18N
          jButton6.setText("70mm X 38mm");
          jButton6.setFocusable(false);
          jButton6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton6.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
               }
          });
          jToolBar3.add(jButton6);

          jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/print_24.png"))); // NOI18N
          jButton7.setText("70mm X 75mm");
          jButton7.setFocusable(false);
          jButton7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton7.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton7ActionPerformed(evt);
               }
          });
          jToolBar3.add(jButton7);

          getContentPane().add(jToolBar3, java.awt.BorderLayout.PAGE_START);

          jSplitPane1.setDividerLocation(250);

          jPanel1.setLayout(new java.awt.BorderLayout());

          table.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {

               }
          ));
          table.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          table.setHighlighters(HighlighterFactory.createSimpleStriping());
          table.setShowVerticalLines(false);
          jScrollPane2.setViewportView(table);

          jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

          jToolBar5.setFloatable(false);
          jToolBar5.setRollover(true);
          jToolBar5.add(filler5);

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
          jToolBar5.add(jButton5);

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
          jToolBar5.add(jButton4);

          jPanel1.add(jToolBar5, java.awt.BorderLayout.PAGE_START);

          jToolBar4.setFloatable(false);
          jToolBar4.setRollover(true);

          jXSearchField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          jXSearchField1.setMaximumSize(new java.awt.Dimension(200, 20));
          jXSearchField1.setPreferredSize(new java.awt.Dimension(198, 18));
          jXSearchField1.setPrompt("Kerko ...");
          jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jXSearchField1ActionPerformed(evt);
               }
          });
          jToolBar4.add(jXSearchField1);
          jToolBar4.add(filler1);

          jCheckBox3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          jCheckBox3.setForeground(new java.awt.Color(0, 151, 0));
          jCheckBox3.setSelected(true);
          jCheckBox3.setText("Gj. Poz.");
          jCheckBox3.setToolTipText("Listo artikujt me gjendje pozitive!");
          jCheckBox3.setFocusable(false);
          jCheckBox3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jCheckBox3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox3ActionPerformed(evt);
               }
          });
          jToolBar4.add(jCheckBox3);

          jPanel1.add(jToolBar4, java.awt.BorderLayout.PAGE_END);

          jSplitPane1.setRightComponent(jPanel1);

          jPanel2.setLayout(new java.awt.BorderLayout());

          javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
          tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
          tree.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
               public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    treeValueChanged(evt);
               }
          });
          jScrollPane3.setViewportView(tree);

          jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

          jSplitPane1.setLeftComponent(jPanel2);

          getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

          pack();
     }// </editor-fold>//GEN-END:initComponents

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
			
			if(evt1.getNewValue().equals(SwingWorker.StateValue.DONE)){
				this.table.getColumn(0).setPreferredWidth(50);
				this.table.getColumn(1).setPreferredWidth(50);
				this.table.getColumn(2).setPreferredWidth(250);
				this.table.getColumn(3).setPreferredWidth(250);
				this.table.getColumn(4).setPreferredWidth(30);
				this.table.getColumn(5).setPreferredWidth(100);
				this.table.getColumn(6).setPreferredWidth(30);
				this.table.getColumn(7).setPreferredWidth(10);
			}
		});
		
		worker.execute();
		
		

     }//GEN-LAST:event_jButton2ActionPerformed

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          ExcelIO excelIO = new ExcelIO();
          File file = excelIO.export("label_export.xls", table);

          try {Desktop.getDesktop().open(file);}
          catch (IOException ex) {Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);}

     }//GEN-LAST:event_jButton1ActionPerformed

     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          TableModel tableModel = (TableModel) table.getModel();
		File file = LabelHTMLExport.exportHtmlFromPriceLables3x2("./temp/price_label_export.html", tableModel.getPriceLabels());
          try {
               Desktop.getDesktop().open(file);
          } catch (IOException ex) {
               Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
     }//GEN-LAST:event_jButton3ActionPerformed

     private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
          this.filterTableRows();
     }//GEN-LAST:event_jCheckBox3ActionPerformed

     private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
          this.filterTableRows();
     }//GEN-LAST:event_jXSearchField1ActionPerformed

     private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
          for(int i=0; i<this.table.getRowCount();i++)
          table.setValueAt(true, i, 7);
     }//GEN-LAST:event_jButton5ActionPerformed

     private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          for(int i=0; i<this.table.getRowCount();i++)
          table.setValueAt(false, i, 7);
     }//GEN-LAST:event_jButton4ActionPerformed

     private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
          this.jButton2ActionPerformed(null);
     }//GEN-LAST:event_formInternalFrameOpened

     private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
          if(evt.getPath() == null){
               tree.setSelectionRow(0);
               return;
          }
          jXSearchField1.setText("");

          jCheckBox3.setSelected(true);
//          this.tableModel.setLabels(this.treeModel.getLabels((DefaultMutableTreeNode) evt.getPath().getLastPathComponent()));

          filterTableRows();
     }//GEN-LAST:event_treeValueChanged

     private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
          TableModel tableModel = (TableModel) table.getModel();
		File file = LabelHTMLExport.exportHtmlFromPriceLables2("./temp/price_label_export_2x6.html", tableModel.getPriceLabels());
          try {
               Desktop.getDesktop().open(file);
          } catch (IOException ex) {
               Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
     }//GEN-LAST:event_jButton6ActionPerformed

     private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
          TableModel tableModel = (TableModel) table.getModel();
		File file = LabelHTMLExport.exportHtmlFromPriceLables3("./temp/price_label_export_2x3.html", tableModel.getPriceLabels());
          try {
               Desktop.getDesktop().open(file);
          } catch (IOException ex) {
               Logger.getLogger(SpecialLabelFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
     }//GEN-LAST:event_jButton7ActionPerformed


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler4;
     private javax.swing.Box.Filler filler5;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JButton jButton4;
     private javax.swing.JButton jButton5;
     private javax.swing.JButton jButton6;
     private javax.swing.JButton jButton7;
     private javax.swing.JCheckBox jCheckBox3;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JProgressBar jProgressBar1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JScrollPane jScrollPane3;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JToolBar jToolBar3;
     private javax.swing.JToolBar jToolBar4;
     private javax.swing.JToolBar jToolBar5;
     private org.jdesktop.swingx.JXSearchField jXSearchField1;
     private org.jdesktop.swingx.JXTable table;
     private org.jdesktop.swingx.JXTree tree;
     // End of variables declaration//GEN-END:variables
}
