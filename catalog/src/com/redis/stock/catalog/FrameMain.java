/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redis.stock.catalog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

/**
 *
 * @author Redjan Shabani
 */
public class FrameMain extends javax.swing.JFrame {
	
	private List<Article> articles = new ArrayList<>();
	private List<Stock> stocks = new ArrayList<>();
	
	public FrameMain() {
		initComponents();	
		
		
	}
	
	private void reload() {
		this.jToggleButton1.setSelected(false);
		this.searchField.setText("");
		this.jCheckBox1.setSelected(false);
		this.jCheckBox2.setSelected(false);
		
		
		
		
		
		this.articles = Article.geProductList();
		this.stocks = Stock.getStockList();
		
		List headerList = new ArrayList<>(Arrays.asList("Kodi", "Sektori", "Kateogoria", "Emertimi", "Shitje", "Oferte", "Data/Ora"));
		
		
		this.stocks.forEach( stock -> {
			headerList.add(stock);
		});
		headerList.add("TOT");
		
		System.out.println(stocks);
		Object[] header = headerList.toArray(new Object[0]);
		
		DefaultTableModel tableModel = new DefaultTableModel(header, this.articles.size()){

			@Override
			public boolean isCellEditable(int i, int i1) {
				switch(i1){
					case 1: return jToggleButton1.isSelected();
					case 2: return jToggleButton1.isSelected();
					case 3: return jToggleButton1.isSelected();
					case 4: return jToggleButton1.isSelected();
					case 5: return jToggleButton1.isSelected();
					case 6: return jToggleButton1.isSelected();
					default: return false;
				}
			}

			@Override
			public Class<?> getColumnClass(int i) {
				switch(i){
					case 0: return String.class;
					case 1: return String.class;
					case 2: return String.class;
					case 3: return String.class;
					case 4: return Double.class;
					case 5: return Double.class;
					case 6: return Instant.class;
					default: return Float.class;
				}
			}	

			@Override
			public Object getValueAt(int i, int i1) {
				Article p = articles.get(i);
				switch(i1){
					case 0: return p.getCode();
					case 1: return p.getSector();
					case 2: return p.getCategory();
					case 3: return p.getName();
					case 4: return p.getSellPrice();
					case 5: return p.getSellPriceTemp();
					case 6: return p.getInstant();
					default: return super.getValueAt(i, i1);
				}
			}			

			@Override
			public void setValueAt(Object o, int i, int i1) {
				Article p = articles.get(i);
				switch(i1){
					case 1: if(o instanceof String) p.setSector((String) o); break;
					case 2: if(o instanceof String) p.setCategory((String) o); break;
					case 3: if(o instanceof String) p.setName((String) o); break;
					case 4: if(o instanceof Number) p.setSellPrice(((Number) o).doubleValue()); break;
					case 5: if(o instanceof Number) p.setSellPriceTemp(((Number) o).doubleValue()); break;
					default: super.setValueAt(o, i, i1); return;
				}
				super.fireTableRowsUpdated(i, i);
			}
		};
				
		this.jXTable1.setModel(tableModel);
		
		
		this.jXTable1.setDefaultRenderer(Instant.class, new DefaultTableRenderer(){
			
			private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if(value instanceof Instant) {
					label.setHorizontalAlignment(JLabel.CENTER);
					label.setText(dateFormat.format(Date.from((Instant) value)));
				}
				
				
				return label;
			}
			
		});
				
		
		
		
		for(int row = 0; row<tableModel.getRowCount(); row++){
			String code = (String) tableModel.getValueAt(row, 0);
			float sum = 0.0f;
			for(int col = 7; col < 7 + this.stocks.size(); col ++) {
				Float val = this.stocks.get(col-7).getQuantity(code);
				tableModel.setValueAt(val, row, col);
				sum += val;
				
			}
			tableModel.setValueAt(sum, row, 9);
		}
		
		for(int col = 0; col < this.jXTable1.getColumnCount(); col++){
			switch (col) {
				case 1:
				case 2:
				case 6:
					this.jXTable1.packColumn(col, 150);
					break;
				case 3:
					this.jXTable1.packColumn(col, 300);
					break;
				default:
					this.jXTable1.packColumn(col, 50);
					break;
			}
		}
		
		
		this.jXTable1.setDefaultRenderer(Float.class, new DefaultTableCellRenderer(){
			DecimalFormat formatter = new DecimalFormat("###,###");
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				JLabel label = new JLabel();
				
				label.setText(String.valueOf(value));
				label.setHorizontalAlignment(RIGHT);
				label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 10));
				label.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
				label.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
				if(value instanceof Number)
					if(((Number) value).doubleValue() <= 0)
						label.setForeground(Color.RED);

				return label;
			}
	
		});
		
		
	}
	
	private void filter() {
		List<RowFilter<TableModel, Integer>> filters = new ArrayList();
		
		filters.add(RowFilter.regexFilter("(?i)" + this.searchField.getText(), 0, 1, 2));
		
		if(this.jCheckBox1.isSelected()) filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, 1.0f, 4));
		
		if(this.jCheckBox2.isSelected()) filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, 0.0f, 9));
		
		
		String sector = "";
		String category = "";
		if(this.jXTree1.getSelectionPath() != null) {
			TreePath path = this.jXTree1.getSelectionPath();
			if(path.getPathCount() > 1) {
				sector = String.valueOf(path.getPathComponent(1));
				System.out.println(sector);
			}
			if(path.getPathCount() > 2) {
				category = String.valueOf(path.getPathComponent(2));
			}
		}
		filters.add(RowFilter.regexFilter(sector, 1));
		filters.add(RowFilter.regexFilter(category, 2));
		
		
		
		
		
		this.jXTable1.setRowFilter(RowFilter.andFilter(filters));
	}
	
	
	
	
	
	
	private void realodTree() {
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Artikujt"){
			@Override
			public boolean isLeaf() {
				return false;
			}
		};
		
		this.articles.stream().map(Article::getSector).distinct().sorted().forEach(sector -> {
			if(!sector.isEmpty()) {
				DefaultMutableTreeNode sectorNode = new DefaultMutableTreeNode(sector){
					@Override
					public boolean isLeaf() {
						return false;
					}
				};

				this.articles.stream().filter((Article t) -> Objects.equals(sector, t.getSector())).map(Article::getCategory).distinct().sorted().forEach(category -> {
					if(!category.isEmpty()) {
						DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category){
						@Override
						public boolean isLeaf() {
							return true;
						}
					};

						sectorNode.add(categoryNode);
					}
				});
			
				rootNode.add(sectorNode);
			}
		});
		
		
		
		this.jXTree1.setModel(new DefaultTreeModel(rootNode));
	}
		
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          jLabel1 = new javax.swing.JLabel();
          jToolBar1 = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jToggleButton1 = new javax.swing.JToggleButton();
          jPanel2 = new javax.swing.JPanel();
          jToolBar2 = new javax.swing.JToolBar();
          filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          searchField = new org.jdesktop.swingx.JXSearchField();
          filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jCheckBox1 = new javax.swing.JCheckBox();
          filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          jCheckBox2 = new javax.swing.JCheckBox();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          jScrollPane1 = new javax.swing.JScrollPane();
          jXTable1 = new org.jdesktop.swingx.JXTable();
          jScrollPane2 = new javax.swing.JScrollPane();
          jXTree1 = new org.jdesktop.swingx.JXTree();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle("AZA KATALOG");
          setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
               }
          });

          jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

          jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel1.setText("<html>\nCopyright &copy; 2017 redis-it.com<br/>\nMundesuar nga R. Shabani nen lishencen GNU GPL v3.0<br/>\nCel.: <a href='tel:00355698493238'>(+355) 69 84 238</a> \nEmail: <a href='mailto:redjan.shabani@gmail.com'>redjan.shabani@gmail.com</a><br/></html>\"");
          jPanel1.add(jLabel1);

          getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

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
          jToolBar1.add(filler1);

          jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit-locked-16.png"))); // NOI18N
          jToggleButton1.setFocusable(false);
          jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jToggleButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit-unlocked-16.png"))); // NOI18N
          jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jToolBar1.add(jToggleButton1);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

          jPanel2.setLayout(new java.awt.BorderLayout());

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);
          jToolBar2.add(filler5);

          searchField.setColumns(25);
          searchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          searchField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          searchField.setMaximumSize(new java.awt.Dimension(240, 20));
          searchField.setPrompt("Kerko ...");
          searchField.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    searchFieldActionPerformed(evt);
               }
          });
          jToolBar2.add(searchField);
          jToolBar2.add(filler4);

          jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jCheckBox1.setText("Cmim Oferte");
          jCheckBox1.setFocusable(false);
          jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jCheckBox1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    jCheckBox1StateChanged(evt);
               }
          });
          jToolBar2.add(jCheckBox1);
          jToolBar2.add(filler3);

          jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jCheckBox2.setText("Sasi Pozitive");
          jCheckBox2.setFocusable(false);
          jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jCheckBox2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    jCheckBox2StateChanged(evt);
               }
          });
          jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox2ActionPerformed(evt);
               }
          });
          jToolBar2.add(jCheckBox2);
          jToolBar2.add(filler2);

          jPanel2.add(jToolBar2, java.awt.BorderLayout.PAGE_END);

          jXTable1.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {

               }
          ));
          jXTable1.setColumnSelectionAllowed(true);
          jXTable1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jXTable1.setShowVerticalLines(false);
          jXTable1.setSortable(false);
          jScrollPane1.setViewportView(jXTable1);
          jXTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

          jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

          jScrollPane2.setPreferredSize(new java.awt.Dimension(240, 324));

          javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
          jXTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
          jXTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
               public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    jXTree1ValueChanged(evt);
               }
          });
          jScrollPane2.setViewportView(jXTree1);

          jPanel2.add(jScrollPane2, java.awt.BorderLayout.LINE_START);

          getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          this.reload();
		this.realodTree();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
          this.filter();
     }//GEN-LAST:event_searchFieldActionPerformed

     private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
          this.filter();
     }//GEN-LAST:event_jCheckBox1StateChanged

     private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged
          
     }//GEN-LAST:event_jCheckBox2StateChanged

     private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
          this.jButton1ActionPerformed(null);
     }//GEN-LAST:event_formWindowOpened

     private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
          this.filter();
     }//GEN-LAST:event_jCheckBox2ActionPerformed

     private void jXTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jXTree1ValueChanged
          this.filter();
     }//GEN-LAST:event_jXTree1ValueChanged

	
	public static void main(String args[]) {
		
		try {
			UIManager.setLookAndFeel(com.jtattoo.plaf.smart.SmartLookAndFeel.class.getName());
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		java.awt.EventQueue.invokeLater(() -> {
			new FrameMain().setVisible(true);
		});
	}

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.Box.Filler filler3;
     private javax.swing.Box.Filler filler4;
     private javax.swing.Box.Filler filler5;
     private javax.swing.JButton jButton1;
     private javax.swing.JCheckBox jCheckBox1;
     private javax.swing.JCheckBox jCheckBox2;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JToggleButton jToggleButton1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXTable jXTable1;
     private org.jdesktop.swingx.JXTree jXTree1;
     private org.jdesktop.swingx.JXSearchField searchField;
     // End of variables declaration//GEN-END:variables

	
}
