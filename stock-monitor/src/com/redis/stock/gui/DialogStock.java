/*
 * Copyright (C) 2018 Redjan Shabani
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
package com.redis.stock.gui;


/**
 *
 * @author Redjan Shabani
 */
public abstract class DialogStock extends javax.swing.JDialog {
		
	public DialogStock(java.awt.Frame parent) {
		super(parent, true);
		initComponents();
	}
	
	
	
	
//	public void submit() {
//		this.getStock().setName(this.fieldName.getText());
//		this.getStock().setAddress(this.fieldAddress.getText());
//		this.getStock().setPhone(this.fieldPhone.getText());
//		this.getStock().setWebsite(this.fieldWebsite.getText());
//		this.getStock().setEmail(this.fieldEmail.getText());
//		this.getStock().setUrl(this.fieldUrl.getText());
//		this.getStock().setUsername(this.fieldUsername.getText());
//		this.getStock().setPassword(this.fieldPassword.getText());
//		
//		this.dispose();
//	}
//	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jToolBar1 = new javax.swing.JToolBar();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jButton1 = new javax.swing.JButton();
          jPanel2 = new javax.swing.JPanel();
          fieldCode = new org.jdesktop.swingx.JXTextField();
          jXLabel4 = new org.jdesktop.swingx.JXLabel();
          jXLabel5 = new org.jdesktop.swingx.JXLabel();
          fieldName = new org.jdesktop.swingx.JXTextField();
          jXLabel6 = new org.jdesktop.swingx.JXLabel();
          fieldAddress = new org.jdesktop.swingx.JXTextField();
          jXLabel7 = new org.jdesktop.swingx.JXLabel();
          fieldPhone = new org.jdesktop.swingx.JXTextField();
          jXLabel8 = new org.jdesktop.swingx.JXLabel();
          fieldWebsite = new org.jdesktop.swingx.JXTextField();
          jXLabel9 = new org.jdesktop.swingx.JXLabel();
          fieldEmail = new org.jdesktop.swingx.JXTextField();
          jXLabel10 = new org.jdesktop.swingx.JXLabel();
          fieldUrl = new org.jdesktop.swingx.JXTextField();
          jXLabel11 = new org.jdesktop.swingx.JXLabel();
          fieldUsername = new org.jdesktop.swingx.JXTextField();
          jXLabel12 = new org.jdesktop.swingx.JXLabel();
          fieldPassword = new javax.swing.JPasswordField();
          jSeparator1 = new javax.swing.JSeparator();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setResizable(false);
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
               }
          });

          jToolBar1.setFloatable(false);
          jToolBar1.add(filler1);

          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-save-16.png"))); // NOI18N
          java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("text_res_alb"); // NOI18N
          jButton1.setText(bundle.getString("label_save")); // NOI18N
          jButton1.setFocusable(false);
          jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton1);

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);

          jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_info"))); // NOI18N

          fieldCode.setEditable(false);
          fieldCode.setColumns(5);

          jXLabel4.setText("Kodi");

          jXLabel5.setText("Emertimi");

          fieldName.setColumns(20);

          jXLabel6.setText("Adresa");

          fieldAddress.setColumns(20);

          jXLabel7.setText("Telefon");

          fieldPhone.setColumns(20);

          jXLabel8.setText("Website");

          fieldWebsite.setColumns(20);

          jXLabel9.setText("Email");

          fieldEmail.setColumns(20);

          jXLabel10.setText(bundle.getString("label_database")); // NOI18N

          fieldUrl.setColumns(30);

          jXLabel11.setText(bundle.getString("label_username")); // NOI18N

          fieldUsername.setColumns(10);

          jXLabel12.setText(bundle.getString("label_password")); // NOI18N

          fieldPassword.setColumns(10);

          javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
          jPanel2.setLayout(jPanel2Layout);
          jPanel2Layout.setHorizontalGroup(
               jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jSeparator1)
                         .addGroup(jPanel2Layout.createSequentialGroup()
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(fieldName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(fieldAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(fieldWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addGroup(jPanel2Layout.createSequentialGroup()
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(22, 22, 22)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(fieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jXLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fieldPassword))
                                   .addComponent(fieldUrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap())
          );
          jPanel2Layout.setVerticalGroup(
               jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel2Layout.createSequentialGroup()
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                   .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                   .addComponent(fieldWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                   .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(fieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addGroup(jPanel2Layout.createSequentialGroup()
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                   .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                   .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(fieldUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(fieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jXLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
          );

          getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//          this.submit();
     }//GEN-LAST:event_jButton1ActionPerformed

     private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//          this.fieldCode.setText(this.getStock().getCode());
//		this.fieldName.setText(this.getStock().getName());
//		this.fieldUrl.setText(this.getStock().getUrl());
//		this.fieldUsername.setText(this.getStock().getUsername());
//		this.fieldPassword.setText(this.getStock().getPassword());
     }//GEN-LAST:event_formWindowOpened
	
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private org.jdesktop.swingx.JXTextField fieldAddress;
     private org.jdesktop.swingx.JXTextField fieldCode;
     private org.jdesktop.swingx.JXTextField fieldEmail;
     private org.jdesktop.swingx.JXTextField fieldName;
     private javax.swing.JPasswordField fieldPassword;
     private org.jdesktop.swingx.JXTextField fieldPhone;
     private org.jdesktop.swingx.JXTextField fieldUrl;
     private org.jdesktop.swingx.JXTextField fieldUsername;
     private org.jdesktop.swingx.JXTextField fieldWebsite;
     private javax.swing.Box.Filler filler1;
     private javax.swing.JButton jButton1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JSeparator jSeparator1;
     private javax.swing.JToolBar jToolBar1;
     private org.jdesktop.swingx.JXLabel jXLabel10;
     private org.jdesktop.swingx.JXLabel jXLabel11;
     private org.jdesktop.swingx.JXLabel jXLabel12;
     private org.jdesktop.swingx.JXLabel jXLabel4;
     private org.jdesktop.swingx.JXLabel jXLabel5;
     private org.jdesktop.swingx.JXLabel jXLabel6;
     private org.jdesktop.swingx.JXLabel jXLabel7;
     private org.jdesktop.swingx.JXLabel jXLabel8;
     private org.jdesktop.swingx.JXLabel jXLabel9;
     // End of variables declaration//GEN-END:variables
}
