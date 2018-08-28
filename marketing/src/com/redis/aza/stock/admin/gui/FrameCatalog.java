/*
 * Copyright (C) 2017 Redjan Shabani
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

package com.redis.aza.stock.admin.gui;

import com.redis.aza.stock.admin.core.Catalog;
import com.redis.aza.stock.admin.sql.SqlCatalog;
import com.redis.utils.export.ExcelIO;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Redjan Shabani
 */
public class FrameCatalog extends javax.swing.JInternalFrame implements TableModelListener{
	
	private final Catalog catalog = SqlCatalog.getCatalog();
	
	public FrameCatalog() {
		initComponents();
		
		this.table.getModel().addTableModelListener(this);
		
		this.table.getColumn(5).setCellRenderer(new DefaultTableCellRenderer(){
			DecimalFormat formatter = new DecimalFormat("###,###");
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
				
				label.setHorizontalAlignment(RIGHT);
				
				Number val = (Number) jtable.getValueAt(i, i1 + 1);
				Number min = (Number) jtable.getValueAt(i, i1);
				if(val.floatValue() < min.floatValue()){
					super.setText("<html><font color='red'><b>" + formatter.format(min) + "</font></html>");
				}
				else if(val.floatValue() == min.floatValue()){
					super.setText("<html><font color='orange'><b>" + formatter.format(min) + "</font></html>");
				}
				else{
					super.setText("<html><b>" + formatter.format(min) + "</html>");
				}
								
				return label;
			}
			
		});
		this.table.getColumn(6).setCellRenderer(new DefaultTableCellRenderer(){
			DecimalFormat formatter = new DecimalFormat("###,###");
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
				
				label.setHorizontalAlignment(RIGHT);
				
				Number val = (Number) jtable.getValueAt(i, i1);
				Number min = (Number) jtable.getValueAt(i, i1-1);
				if(val.floatValue() < min.floatValue()){
					super.setText("<html><font color='red'><b>" + formatter.format(val) + "</font></html>");
				}
				else if(val.floatValue() == min.floatValue()){
					super.setText("<html><font color='orange'><b>" + formatter.format(val) + "</font></html>");
				}
				else{
					super.setText("<html><b>" + formatter.format(val) + "</html>");
				}
				
				
				return label;
			}
			
		});
		
		this.table.getColumn(7).setCellRenderer(new IntegerCellRenderer());
		this.table.getColumn(8).setCellRenderer(new IntegerCellRenderer());
		this.table.getColumn(9).setCellRenderer(new IntegerCellRenderer());
		this.table.getColumn(10).setCellRenderer(new IntegerCellRenderer());
		this.table.getColumn(11).setCellRenderer(new IntegerCellRenderer());
		this.table.getColumn(12).setCellRenderer(new IntegerCellRenderer());
		
		this.table.getColumn(12).setCellRenderer(new DefaultTableCellRenderer(){
			private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); 
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);				
				if(o instanceof Date) {
					long diff = Date.from(Instant.now()).getTime() - ((Date) o).getTime();
					long days = TimeUnit.MILLISECONDS.toDays(diff);
					
					if(days == 0)
						label.setText("<html>"  + " <b>[Sot]</b> " + formatter.format(o) + "</html>");
					else if( days == 1)
						label.setText("<html>"  + " <b>[Dje]</b> " + formatter.format(o) + "</html>");
					else{
						label.setText("<html>"  + " <b>[" + days + "d]</b> " + formatter.format(o) + "</html>");
					}
					label.setHorizontalAlignment(JLabel.RIGHT);
				}			
				return label;
			}		
		});
	}
	
	private void clear() {
		((DefaultTableModel) this.table.getModel()).setRowCount(0);
		
		this.searchField.setText("");
		
		this.checkMinimal.setSelected(false);
		
	}	
	
	private void reload() {		
		
		this.catalog.forEach( item -> {
			Object[] row = new Object[]{				
				item.getCode(),
				item.getBarcode(),
				item.getCategory(),
				item.getDescription(),
				item.getSupplier(),
				item.getMinStock(),
				item.getStock(),
				item.getCost(),
				item.getBuyin(),
				item.getWhoolsale(),
				item.getRetail(),
				item.getSpecial(),
				item.getLastSellout()
			};
			
			((DefaultTableModel) this.table.getModel()).addRow(row);
		});
	}
	
	private void filter() {
		List<RowFilter<TableModel, Integer>> filters = new ArrayList<>();
		
		filters.add(RowFilter.regexFilter("(?i)" + this.searchField.getText(), 0, 1, 2, 3, 4, 5, 6));
				
		if(checkMinimal.isSelected()) filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, 0.0f, 4));
		
		if(checkCritical.isSelected()) {
			RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>() {
				@Override
				public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
					int row = entry.getIdentifier();
					Number min = (Number) entry.getModel().getValueAt(row, 4);
					Number val = (Number) entry.getModel().getValueAt(row, 5);
					return val.doubleValue() <= min.doubleValue();				
				}
			};
			filters.add(filter);
		}
		
		this.table.setRowFilter(RowFilter.andFilter(filters));
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jToolBar1 = new javax.swing.JToolBar();
          jButton1 = new javax.swing.JButton();
          filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton2 = new javax.swing.JButton();
          jPanel1 = new javax.swing.JPanel();
          jScrollPane2 = new javax.swing.JScrollPane();
          table = new org.jdesktop.swingx.JXTable();
          jToolBar5 = new javax.swing.JToolBar();
          searchField = new org.jdesktop.swingx.JXSearchField();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          checkMinimal = new javax.swing.JCheckBox();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          checkCritical = new javax.swing.JCheckBox();

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
          setTitle("LISTA E ARTIKUJVE");
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
          jToolBar1.add(filler7);

          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/excel-16.png"))); // NOI18N
          jButton2.setFocusable(false);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton2);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

          jPanel1.setLayout(new java.awt.BorderLayout());

          table.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {
                    "Kodi", "Barkodi", "Kategoria", "Pershkrimi", "Fornitori", "Sasi Min.", "Sasia", "Kosto", "Blerje", "Shitje Sh.", "Cmim Shitje", "Cmim Oferte", "Shitja e Fundit"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
               };
               boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, true, false, false, false, false, false, false, false
               };

               public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
               }

               public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
               }
          });
          table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
          table.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          table.setSearchable(null);
          table.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tableMouseClicked(evt);
               }
          });
          jScrollPane2.setViewportView(table);
          if (table.getColumnModel().getColumnCount() > 0) {
               table.getColumnModel().getColumn(0).setPreferredWidth(50);
               table.getColumnModel().getColumn(2).setPreferredWidth(100);
               table.getColumnModel().getColumn(3).setPreferredWidth(300);
               table.getColumnModel().getColumn(4).setPreferredWidth(100);
               table.getColumnModel().getColumn(5).setPreferredWidth(50);
               table.getColumnModel().getColumn(6).setPreferredWidth(50);
               table.getColumnModel().getColumn(7).setPreferredWidth(50);
               table.getColumnModel().getColumn(8).setPreferredWidth(50);
               table.getColumnModel().getColumn(9).setPreferredWidth(50);
               table.getColumnModel().getColumn(10).setPreferredWidth(50);
               table.getColumnModel().getColumn(11).setPreferredWidth(50);
               table.getColumnModel().getColumn(12).setPreferredWidth(100);
          }

          jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

          getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

          jToolBar5.setFloatable(false);
          jToolBar5.setRollover(true);

          searchField.setColumns(25);
          searchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          searchField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          searchField.setMaximumSize(new java.awt.Dimension(242, 20));
          searchField.setPreferredSize(new java.awt.Dimension(242, 20));
          searchField.setPrompt("Kerko ...");
          searchField.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    searchFieldActionPerformed(evt);
               }
          });
          jToolBar5.add(searchField);
          jToolBar5.add(filler1);

          checkMinimal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          checkMinimal.setText("Sasi Minimale");
          checkMinimal.setFocusable(false);
          checkMinimal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          checkMinimal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          checkMinimal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          checkMinimal.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    checkMinimalStateChanged(evt);
               }
          });
          jToolBar5.add(checkMinimal);
          jToolBar5.add(filler2);

          checkCritical.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
          checkCritical.setForeground(java.awt.Color.red);
          checkCritical.setText("Sasi Kritike");
          checkCritical.setFocusable(false);
          checkCritical.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          checkCritical.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          checkCritical.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          checkCritical.addChangeListener(new javax.swing.event.ChangeListener() {
               public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    checkCriticalStateChanged(evt);
               }
          });
          jToolBar5.add(checkCritical);

          getContentPane().add(jToolBar5, java.awt.BorderLayout.PAGE_END);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          this.clear();
		this.reload();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        this.filter();
     }//GEN-LAST:event_searchFieldActionPerformed

     private void checkMinimalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkMinimalStateChanged
          this.filter();
     }//GEN-LAST:event_checkMinimalStateChanged

     private void checkCriticalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkCriticalStateChanged
          this.filter();
     }//GEN-LAST:event_checkCriticalStateChanged

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          ExcelIO excelIO = new ExcelIO();
          File file = excelIO.export("export_"+System.currentTimeMillis()+".xls", table);

          try {Desktop.getDesktop().open(file);}
          catch (IOException ex) {Logger.getLogger(FrameCatalog.class.getName()).log(Level.SEVERE, null, ex);}
     }//GEN-LAST:event_jButton2ActionPerformed

     private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
          if(evt.getClickCount() == 2) {
			int row = this.table.rowAtPoint(evt.getPoint());
			int col = this.table.columnAtPoint(evt.getPoint());
			if(row != -1 && col != 4) {
				String item = (String) this.table.getValueAt(row, 0);
				DialogCatalogItem dialog = new DialogCatalogItem((Frame) SwingUtilities.getWindowAncestor(this));
				dialog.setItemCode(item);
				dialog.setLocationRelativeTo(this);
				dialog.setVisible(true);
			}
		}
     }//GEN-LAST:event_tableMouseClicked

     private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
          this.reload();
     }//GEN-LAST:event_formInternalFrameOpened


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JCheckBox checkCritical;
     private javax.swing.JCheckBox checkMinimal;
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.Box.Filler filler7;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar5;
     private org.jdesktop.swingx.JXSearchField searchField;
     private org.jdesktop.swingx.JXTable table;
     // End of variables declaration//GEN-END:variables

	@Override
	public void tableChanged(TableModelEvent tme) {
		int row = tme.getFirstRow();
		int col = tme.getColumn();
		
		if(tme.getFirstRow() > -1 && tme.getFirstRow() == tme.getLastRow()){
			if(tme.getType() == TableModelEvent.UPDATE){
				if(tme.getColumn() == 5){
					TableModel model = (TableModel) tme.getSource();
					String columnName = model.getColumnName(col);
					Object data = model.getValueAt(row, col);

					System.out.println(columnName + "(" + row + ") = " + data);
					
					
					String item = (String) model.getValueAt(row, 0);
					Float value = ((Number) model.getValueAt(row, 5)).floatValue();
					boolean success = SqlCatalog.updateMinStock(item, value);
					if(!success)
						JOptionPane.showMessageDialog(this, "Gabim gjate ndryshimit te vleres!", "Error SQL", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
			
		
	}
}
