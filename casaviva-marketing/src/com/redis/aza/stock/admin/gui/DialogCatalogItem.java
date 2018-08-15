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
import com.redis.aza.stock.admin.core.Stock;
import com.redis.aza.stock.admin.sql.SqlCatalog;
import com.redis.aza.stock.admin.sql.SqlServer;
import com.redis.aza.stock.admin.sql.SqlStock;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Redjan Shabani
 */
public class DialogCatalogItem extends javax.swing.JDialog {
	
	public DialogCatalogItem(java.awt.Frame parent) {
		super(parent, true);
		initComponents();
	}
	
	public void setItemCode(String itemCode) {
		try(Connection cn = SqlServer.getConnection()){
			
			Catalog.Item catalogItem = SqlCatalog.selectItem(cn, itemCode);
			this.fieldCode.setText(catalogItem.getCode());
			this.fieldDescription.setText(catalogItem.getDescription());
			this.fieldSupplier.setText(catalogItem.getSupplier());
			this.fieldCategory.setText(catalogItem.getCategory());
			this.fieldAvgCost.setValue(catalogItem.getCost());
			this.fieldBuyin.setValue(catalogItem.getBuyin());
			this.fieldRetail.setValue(catalogItem.getRetail());
			this.fieldWholsale.setValue(catalogItem.getWhoolsale());
			this.fieldSpecial.setValue(catalogItem.getSpecial());

			Stock.Item stockItem = SqlStock.select(cn, itemCode);
			DefaultPieDataset dataset = new DefaultPieDataset( );
			stockItem.entrySet().forEach( e -> { if(e.getValue() > 0.0f)dataset.setValue(e.getKey(), e.getValue());});		
			JFreeChart chart = ChartFactory.createPieChart( null, dataset, true, true, false);
			ChartPanel chartPanel = new ChartPanel(chart);
			this.panelPieHost.add(chartPanel);
		}
		catch (SQLException ex) {
			Logger.getLogger(DialogCatalogItem.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		
	}
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          fieldCode = new org.jdesktop.swingx.JXTextField();
          jLabel4 = new javax.swing.JLabel();
          jLabel3 = new javax.swing.JLabel();
          fieldCategory = new org.jdesktop.swingx.JXTextField();
          jLabel2 = new javax.swing.JLabel();
          fieldDescription = new org.jdesktop.swingx.JXTextField();
          fieldSupplier = new org.jdesktop.swingx.JXTextField();
          jLabel1 = new javax.swing.JLabel();
          jLabel5 = new javax.swing.JLabel();
          fieldBuyin = new javax.swing.JFormattedTextField();
          jLabel10 = new javax.swing.JLabel();
          fieldAvgCost = new javax.swing.JFormattedTextField();
          jLabel6 = new javax.swing.JLabel();
          jLabel11 = new javax.swing.JLabel();
          jLabel12 = new javax.swing.JLabel();
          fieldWholsale = new javax.swing.JFormattedTextField();
          jLabel7 = new javax.swing.JLabel();
          jLabel8 = new javax.swing.JLabel();
          fieldRetail = new javax.swing.JFormattedTextField();
          jLabel13 = new javax.swing.JLabel();
          jLabel9 = new javax.swing.JLabel();
          fieldSpecial = new javax.swing.JFormattedTextField();
          jLabel14 = new javax.swing.JLabel();
          panelPieHost = new javax.swing.JPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setPreferredSize(new java.awt.Dimension(450, 450));

          jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

          fieldCode.setEditable(false);
          fieldCode.setColumns(10);
          fieldCode.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

          jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel4.setText("Fornitori");

          jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel3.setText("Pershkrimi");

          fieldCategory.setEditable(false);
          fieldCategory.setColumns(20);
          fieldCategory.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

          jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel2.setText("Kategoria");

          fieldDescription.setEditable(false);
          fieldDescription.setColumns(30);
          fieldDescription.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

          fieldSupplier.setEditable(false);
          fieldSupplier.setColumns(20);
          fieldSupplier.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

          jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel1.setText("Kodi");

          jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel5.setText("Blerje");

          fieldBuyin.setEditable(false);
          fieldBuyin.setColumns(7);
          fieldBuyin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
          fieldBuyin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          fieldBuyin.setText("0");

          jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel10.setText("Kosto");

          fieldAvgCost.setEditable(false);
          fieldAvgCost.setColumns(7);
          fieldAvgCost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
          fieldAvgCost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          fieldAvgCost.setText("0");

          jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel6.setText("ALL");

          jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel11.setText("ALL");

          jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel12.setText("ALL");

          fieldWholsale.setEditable(false);
          fieldWholsale.setColumns(7);
          fieldWholsale.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
          fieldWholsale.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          fieldWholsale.setText("0");

          jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel7.setText("Shumica");

          jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel8.setText("Pakica");

          fieldRetail.setEditable(false);
          fieldRetail.setColumns(7);
          fieldRetail.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
          fieldRetail.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          fieldRetail.setText("0");

          jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel13.setText("ALL");

          jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel9.setText("Oferta");

          fieldSpecial.setEditable(false);
          fieldSpecial.setColumns(7);
          fieldSpecial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
          fieldSpecial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          fieldSpecial.setText("0");

          jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel14.setText("ALL");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(fieldCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel2))
                              .addGap(0, 0, Short.MAX_VALUE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(jLabel1)
                                             .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(jLabel3)
                                             .addComponent(fieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(jLabel4)
                                             .addComponent(fieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                             .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(fieldBuyin, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                             .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(fieldAvgCost, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel11))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                             .addComponent(fieldWholsale, javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(jLabel8)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(fieldRetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel13)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(fieldSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel14))
                                             .addComponent(jLabel9))))
                              .addContainerGap())))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(fieldCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                             .addComponent(jLabel3)
                                             .addComponent(jLabel1))
                                        .addGap(1, 1, 1)
                                        .addComponent(fieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, 0)
                                        .addComponent(fieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addComponent(fieldAvgCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jLabel11)))))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(jLabel5)
                              .addGap(0, 0, 0)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(fieldBuyin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(0, 0, 0)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(fieldWholsale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(fieldRetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(jLabel13))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(fieldSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(jLabel14)))
                    .addContainerGap())
          );

          panelPieHost.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gjendja ne magazina", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
          panelPieHost.setLayout(new java.awt.BorderLayout());

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(panelPieHost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelPieHost, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addContainerGap())
          );

          pack();
     }// </editor-fold>//GEN-END:initComponents
	
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JFormattedTextField fieldAvgCost;
     private javax.swing.JFormattedTextField fieldBuyin;
     private org.jdesktop.swingx.JXTextField fieldCategory;
     private org.jdesktop.swingx.JXTextField fieldCode;
     private org.jdesktop.swingx.JXTextField fieldDescription;
     private javax.swing.JFormattedTextField fieldRetail;
     private javax.swing.JFormattedTextField fieldSpecial;
     private org.jdesktop.swingx.JXTextField fieldSupplier;
     private javax.swing.JFormattedTextField fieldWholsale;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel14;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JLabel jLabel7;
     private javax.swing.JLabel jLabel8;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel panelPieHost;
     // End of variables declaration//GEN-END:variables
}
