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

import com.redis.aza.stock.admin.core.Sellout;
import com.redis.aza.stock.admin.sql.SqlSellout;
import com.redis.utils.export.ExcelIO;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Redjan Shabani
 */
public class FrameSelloutStories extends javax.swing.JInternalFrame {

	private final Sellout sellout = SqlSellout.getSellout();
	
	public FrameSelloutStories() {
		initComponents();
		
		this.table.setDefaultRenderer(Float.class, new IntegerCellRenderer());
		
		this.table.getColumn(6).setCellRenderer(new DefaultTableCellRenderer(){
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
	
	private void reload() {
		this.searchField.setText("");
		
		DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();
		tableModel.setRowCount(0);
		sellout.getStories().forEach( story -> {
			Object[] row = new Object[]{
				story.getCode(),
				story.getDescription(),
				story.getUnit(),
				story.getSumQuantity().intValue(),
				story.getAvgPrice().intValue(),
				story.getSumValue().intValue(),
				story.getMaxDate()				
			};
			
			tableModel.addRow(row);
		});
	}	
	
	private void filter() {
		List<RowFilter<TableModel, Integer>> filters = new ArrayList<>();
		
		filters.add(RowFilter.regexFilter("(?i)" + this.searchField.getText(), 0, 1));
		
		this.table.setRowFilter(RowFilter.andFilter(filters));
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jToolBar1 = new javax.swing.JToolBar();
          jButton3 = new javax.swing.JButton();
          filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton1 = new javax.swing.JButton();
          jButton2 = new javax.swing.JButton();
          jScrollPane1 = new javax.swing.JScrollPane();
          table = new org.jdesktop.swingx.JXTable();
          jToolBar2 = new javax.swing.JToolBar();
          searchField = new org.jdesktop.swingx.JXSearchField();
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

          setClosable(true);
          setIconifiable(true);
          setMaximizable(true);
          setResizable(true);
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

          jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh-16.png"))); // NOI18N
          jButton3.setFocusable(false);
          jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton3);
          jToolBar1.add(filler3);
          jToolBar1.add(filler1);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart-16.png"))); // NOI18N
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton1);

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

          table.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {

               },
               new String [] {
                    "Kodi", "Pershkrimi", "Njesia", "Sasia e Shitjeve", "Cmimi Mesatar", "Vlera e Shitjeve", "Shitja e Fundit"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
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
          table.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          table.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tableMouseClicked(evt);
               }
          });
          jScrollPane1.setViewportView(table);
          if (table.getColumnModel().getColumnCount() > 0) {
               table.getColumnModel().getColumn(0).setPreferredWidth(50);
               table.getColumnModel().getColumn(1).setPreferredWidth(500);
               table.getColumnModel().getColumn(2).setPreferredWidth(50);
               table.getColumnModel().getColumn(3).setPreferredWidth(50);
               table.getColumnModel().getColumn(4).setPreferredWidth(50);
               table.getColumnModel().getColumn(5).setPreferredWidth(50);
               table.getColumnModel().getColumn(6).setPreferredWidth(150);
          }

          getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);

          searchField.setColumns(25);
          searchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          searchField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          searchField.setMaximumSize(new java.awt.Dimension(242, 20));
          searchField.setMinimumSize(new java.awt.Dimension(242, 20));
          searchField.setPreferredSize(new java.awt.Dimension(242, 20));
          searchField.setPrompt("Kerko ...");
          searchField.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    searchFieldActionPerformed(evt);
               }
          });
          jToolBar2.add(searchField);
          jToolBar2.add(filler2);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_END);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
          this.filter();
     }//GEN-LAST:event_searchFieldActionPerformed

     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          this.reload();
     }//GEN-LAST:event_jButton3ActionPerformed

     private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
          if(evt.getClickCount() == 2){
			int row = this.table.rowAtPoint(evt.getPoint());
			if(row != -1){
				String itemCode = String.valueOf(this.table.getValueAt(row, 0));
				DialogSelloutEvents dialog = new DialogSelloutEvents((Frame) SwingUtilities.getWindowAncestor(this));
				dialog.setEvents(sellout.getEvents(itemCode));
				dialog.setLocationRelativeTo(this);
				dialog.setVisible(true);
			}
		}
     }//GEN-LAST:event_tableMouseClicked

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          ExcelIO excelIO = new ExcelIO();
		File file = excelIO.export("export_"+System.currentTimeMillis()+".xls", table);
		
		try {Desktop.getDesktop().open(file);}
		catch (IOException ex) {Logger.getLogger(FrameSelloutStories.class.getName()).log(Level.SEVERE, null, ex);}
     }//GEN-LAST:event_jButton2ActionPerformed

     private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
          this.reload();
     }//GEN-LAST:event_formInternalFrameOpened

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          DialogDateSellouts dialog = new DialogDateSellouts((Frame) SwingUtilities.windowForComponent(this));
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
     }//GEN-LAST:event_jButton1ActionPerformed


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.Box.Filler filler3;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private org.jdesktop.swingx.JXSearchField searchField;
     private org.jdesktop.swingx.JXTable table;
     // End of variables declaration//GEN-END:variables
}
