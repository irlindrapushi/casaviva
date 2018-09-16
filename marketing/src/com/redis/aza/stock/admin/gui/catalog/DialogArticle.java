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

package com.redis.aza.stock.admin.gui.catalog;

import com.redis.aza.stock.admin.core.catalog.Article;

/**
 *
 * @author Redjan Shabani
 */
public class DialogArticle extends javax.swing.JDialog {
	
	
	
	public DialogArticle(java.awt.Frame parent) {
		super(parent, true);
		initComponents();
	}
	
	public void setArticle(Article article) {
		this.fieldCode.setText(article.getCode());
		this.fieldCode1.setText(article.getBarcode());
		this.fieldDescription.setText(article.getName());
		this.fieldSupplier.setText(article.getSupplier());
		this.fieldCategory.setText("./" + article.getSector() + "/" + article.getCategory());
		this.fieldAvgCost.setValue(article.getCostPrice());
		this.fieldBuyin.setValue(article.getBuyPrice());
		this.fieldSellPrice0.setValue(article.getSellPrice());
		this.fieldSellPrice1.setValue(article.getSellPrice() * article.getSellPriceDiscount());
		
		
		Double price = article.getSellPriceDiscount() > 0 ? article.getSellPrice() * article.getSellPriceDiscount() : article.getSellPrice();
		Double cost = article.getCostPrice();
		this.fieldSellPrice.setValue(price);
		this.fieldSellPriceMarge.setValue((price - cost) / price);
		
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
          jLabel8 = new javax.swing.JLabel();
          fieldSellPrice = new javax.swing.JFormattedTextField();
          fieldSellPrice0 = new javax.swing.JFormattedTextField();
          jLabel16 = new javax.swing.JLabel();
          fieldCode1 = new org.jdesktop.swingx.JXTextField();
          fieldSellPriceMarge = new javax.swing.JFormattedTextField();
          fieldSellPrice1 = new javax.swing.JFormattedTextField();
          jLabel6 = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
          jLabel2.setText("Klasifikimi");

          fieldDescription.setEditable(false);
          fieldDescription.setColumns(30);
          fieldDescription.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

          fieldSupplier.setEditable(false);
          fieldSupplier.setColumns(20);
          fieldSupplier.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

          jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel1.setText("Kodi");

          jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          jLabel5.setText("Blerje");

          fieldBuyin.setEditable(false);
          fieldBuyin.setColumns(7);
          fieldBuyin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
          fieldBuyin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

          jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          jLabel10.setText("Kosto K");

          fieldAvgCost.setEditable(false);
          fieldAvgCost.setColumns(7);
          fieldAvgCost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
          fieldAvgCost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

          jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          jLabel8.setText("Shitje S");

          fieldSellPrice.setEditable(false);
          fieldSellPrice.setColumns(7);
          fieldSellPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
          fieldSellPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

          fieldSellPrice0.setEditable(false);
          fieldSellPrice0.setColumns(7);
          fieldSellPrice0.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
          fieldSellPrice0.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

          jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel16.setText("Barkodi");

          fieldCode1.setEditable(false);
          fieldCode1.setColumns(15);
          fieldCode1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

          fieldSellPriceMarge.setEditable(false);
          fieldSellPriceMarge.setColumns(5);
          fieldSellPriceMarge.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00%"))));
          fieldSellPriceMarge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
          fieldSellPriceMarge.setText("0");

          fieldSellPrice1.setEditable(false);
          fieldSellPrice1.setColumns(7);
          fieldSellPrice1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
          fieldSellPrice1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

          jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
          jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          jLabel6.setText("(S-K)/S");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(fieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jLabel1)
                                   .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jLabel16)
                                   .addComponent(fieldCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jLabel3)
                                   .addComponent(fieldCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel2)
                                   .addComponent(jLabel4)
                                   .addComponent(fieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                             .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(fieldBuyin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(fieldAvgCost))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(fieldSellPrice0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(fieldSellPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                       .addComponent(fieldSellPrice)
                                                       .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addComponent(fieldSellPriceMarge, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(jLabel6))))))
                              .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(fieldCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(jLabel1)
                                   .addComponent(jLabel16))
                              .addGap(20, 20, 20)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addGap(0, 0, 0)
                    .addComponent(fieldCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3)
                    .addGap(1, 1, 1)
                    .addComponent(fieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(7, 7, 7)
                    .addComponent(jLabel4)
                    .addGap(0, 0, 0)
                    .addComponent(fieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jLabel5)
                         .addComponent(jLabel10)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jLabel8)
                              .addComponent(jLabel6)))
                    .addGap(0, 0, 0)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(fieldBuyin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(fieldAvgCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(fieldSellPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(fieldSellPriceMarge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(fieldSellPrice0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(fieldSellPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE))
          );

          getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

          pack();
     }// </editor-fold>//GEN-END:initComponents
	
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JFormattedTextField fieldAvgCost;
     private javax.swing.JFormattedTextField fieldBuyin;
     private org.jdesktop.swingx.JXTextField fieldCategory;
     private org.jdesktop.swingx.JXTextField fieldCode;
     private org.jdesktop.swingx.JXTextField fieldCode1;
     private org.jdesktop.swingx.JXTextField fieldDescription;
     private javax.swing.JFormattedTextField fieldSellPrice;
     private javax.swing.JFormattedTextField fieldSellPrice0;
     private javax.swing.JFormattedTextField fieldSellPrice1;
     private javax.swing.JFormattedTextField fieldSellPriceMarge;
     private org.jdesktop.swingx.JXTextField fieldSupplier;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel16;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JLabel jLabel8;
     private javax.swing.JPanel jPanel1;
     // End of variables declaration//GEN-END:variables
}
