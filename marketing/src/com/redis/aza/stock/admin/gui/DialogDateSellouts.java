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

import com.redis.aza.stock.admin.sql.SqlSellout;
import com.redis.utils.export.ExcelIO;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author Redjan Shabani
 */
public class DialogDateSellouts extends javax.swing.JDialog {

	
	final TimeSeries series = new TimeSeries( "Shitje Ditore" );
	final ChartPanel chartPanel = new ChartPanel( 
			ChartFactory.createTimeSeriesChart(             
				null, 
				"Data",              
				"Vlera (ALL)",              
				new TimeSeriesCollection(series),             
				false,              
				false,              
				false
		));  
	
	public DialogDateSellouts(java.awt.Frame parent) {
		super(parent, true);
		initComponents();
		
		this.datePickerMin.setDate(Date.from(Year.now().atDay(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		this.datePickerMax.setDate(Date.from(Instant.now()));		
		
		//		   
		chartPanel.setMouseZoomable( true , false );   
		this.panelChartHost.add(chartPanel);
		this.pack();
		
		
		
		
		this.table.setDefaultRenderer(Float.class, new IntegerCellRenderer());
		
		this.table.getColumn(6).setCellRenderer(new DefaultTableCellRenderer(){
			private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); 
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);				
				if(o instanceof Date) {
//					long diff = Date.from(Instant.now()).getTime() - ((Date) o).getTime();
//					long days = TimeUnit.MILLISECONDS.toDays(diff);
//					
//					if(days == 0)
//						label.setText("<html>"  + "[Sot] " + formatter.format(o) + "</html>");
//					else if( days == 1)
//						label.setText("<html>"  + " [Dje] " + formatter.format(o) + "</html>");
//					else{
//						label.setText("<html>"  + "[" + days + "d] " + formatter.format(o) + "</html>");
//					}
					label.setText(formatter.format(o));
					label.setHorizontalAlignment(JLabel.CENTER);
				}			
				return label;
			}		
		});
		
	}
		
	private void reload() {		
		this.searchField.setText("");		
		this.series.clear();		
		((DefaultTableModel) this.table.getModel()).setRowCount(0);
		
		
		
		
		
		
		Date minDate = this.datePickerMin.getDate();
		Date maxDate = this.datePickerMax.getDate();
		
		SqlSellout.select(minDate, maxDate).forEach( ds -> {
			series.add(
				new org.jfree.data.time.Day(ds.date), 
				ds.value);
		});
		this.series.fireSeriesChanged();
		
		SqlSellout.getSellout().getStories(minDate, maxDate).forEach( story -> {
			Object[] row = new Object[]{
				story.getCode(),
				story.getDescription(),
				story.getUnit(),
				story.getSumQuantity().intValue(),
				story.getAvgPrice().intValue(),
				story.getSumValue().intValue(),
				story.getMaxDate()				
			};
			
			((DefaultTableModel) this.table.getModel()).addRow(row);
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
          filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          datePickerMin = new org.jdesktop.swingx.JXDatePicker();
          jLabel1 = new javax.swing.JLabel();
          datePickerMax = new org.jdesktop.swingx.JXDatePicker();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
          jButton1 = new javax.swing.JButton();
          filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton2 = new javax.swing.JButton();
          jSplitPane1 = new javax.swing.JSplitPane();
          jScrollPane1 = new javax.swing.JScrollPane();
          table = new org.jdesktop.swingx.JXTable();
          panelChartHost = new javax.swing.JPanel();
          jToolBar2 = new javax.swing.JToolBar();
          filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          searchField = new org.jdesktop.swingx.JXSearchField();
          filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setTitle("ARTIKUJT: STATISTIKA SHITJE");
          setUndecorated(true);
          setPreferredSize(new java.awt.Dimension(800, 450));
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
               }
          });

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);
          jToolBar1.add(filler2);

          datePickerMin.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          datePickerMin.setFormats("dd.MM.yyyy");
          datePickerMin.setMaximumSize(new java.awt.Dimension(105, 20));
          datePickerMin.setMinimumSize(new java.awt.Dimension(105, 20));
          datePickerMin.setPreferredSize(new java.awt.Dimension(105, 20));
          jToolBar1.add(datePickerMin);

          jLabel1.setText("-");
          jToolBar1.add(jLabel1);

          datePickerMax.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          datePickerMax.setFormats("dd.MM.yyyy");
          datePickerMax.setMaximumSize(new java.awt.Dimension(105, 20));
          datePickerMax.setMinimumSize(new java.awt.Dimension(105, 20));
          datePickerMax.setPreferredSize(new java.awt.Dimension(105, 20));
          jToolBar1.add(datePickerMax);
          jToolBar1.add(filler1);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/play-16.png"))); // NOI18N
          jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jButton1.setFocusable(false);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton1);
          jToolBar1.add(filler4);

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

          jSplitPane1.setDividerLocation(150);
          jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

          table.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null}
               },
               new String [] {
                    "Kodi", "Pershkrimi", "Njesia", "Sasia", "Cmimi", "Vlera", "E Fundit"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
               };
               boolean[] canEdit = new boolean [] {
                    true, false, false, false, false, false, false
               };

               public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
               }

               public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
               }
          });
          jScrollPane1.setViewportView(table);
          if (table.getColumnModel().getColumnCount() > 0) {
               table.getColumnModel().getColumn(0).setPreferredWidth(50);
               table.getColumnModel().getColumn(1).setPreferredWidth(400);
               table.getColumnModel().getColumn(2).setPreferredWidth(50);
               table.getColumnModel().getColumn(3).setPreferredWidth(50);
               table.getColumnModel().getColumn(4).setPreferredWidth(50);
               table.getColumnModel().getColumn(5).setPreferredWidth(50);
               table.getColumnModel().getColumn(6).setPreferredWidth(100);
          }

          jSplitPane1.setBottomComponent(jScrollPane1);

          panelChartHost.setLayout(new java.awt.BorderLayout());
          jSplitPane1.setLeftComponent(panelChartHost);

          getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);
          jToolBar2.add(filler5);

          searchField.setColumns(25);
          searchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          searchField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          searchField.setMaximumSize(new java.awt.Dimension(240, 20));
          searchField.setPreferredSize(new java.awt.Dimension(240, 20));
          searchField.setPrompt("Kerko ...");
          searchField.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    searchFieldActionPerformed(evt);
               }
          });
          jToolBar2.add(searchField);
          jToolBar2.add(filler3);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_END);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		reload();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
          this.filter();
     }//GEN-LAST:event_searchFieldActionPerformed

     private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
          reload();
     }//GEN-LAST:event_formWindowOpened

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          ExcelIO excelIO = new ExcelIO();
		File file = excelIO.export("export_"+System.currentTimeMillis()+".xls", table);
		
		try {Desktop.getDesktop().open(file);}
		catch (IOException ex) {Logger.getLogger(FrameSelloutStories.class.getName()).log(Level.SEVERE, null, ex);}
     }//GEN-LAST:event_jButton2ActionPerformed

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private org.jdesktop.swingx.JXDatePicker datePickerMax;
     private org.jdesktop.swingx.JXDatePicker datePickerMin;
     private javax.swing.Box.Filler filler1;
     private javax.swing.Box.Filler filler2;
     private javax.swing.Box.Filler filler3;
     private javax.swing.Box.Filler filler4;
     private javax.swing.Box.Filler filler5;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     private javax.swing.JPanel panelChartHost;
     private org.jdesktop.swingx.JXSearchField searchField;
     private org.jdesktop.swingx.JXTable table;
     // End of variables declaration//GEN-END:variables
}
