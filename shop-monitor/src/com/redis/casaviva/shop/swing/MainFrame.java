package com.redis.casaviva.shop.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.Timer;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class MainFrame extends javax.swing.JFrame {
	
	
	public MainFrame() {
		initComponents();
	}
	
	
	
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jToolBar1 = new javax.swing.JToolBar();
          jButton4 = new javax.swing.JButton();
          jButton5 = new javax.swing.JButton();
          jButton2 = new javax.swing.JButton();
          jButton1 = new javax.swing.JButton();
          filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
          jLabel1 = new javax.swing.JLabel();
          jToolBar2 = new javax.swing.JToolBar();
          jLabel2 = new javax.swing.JLabel();
          jDesktopPane1 = new javax.swing.JDesktopPane();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle("AZA SHOP MONITOR");
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
               }
               public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
               }
          });

          jToolBar1.setFloatable(false);
          jToolBar1.setRollover(true);

          jButton4.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
          jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/book-32.png"))); // NOI18N
          jButton4.setText("Artikujt");
          jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jButton4.setFocusable(false);
          jButton4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton4.setOpaque(false);
          jButton4.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton4);

          jButton5.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
          jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/Shop-32.png"))); // NOI18N
          jButton5.setText("Gjedjet");
          jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jButton5.setFocusable(false);
          jButton5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton5.setOpaque(false);
          jButton5.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton5);

          jButton2.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
          jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/price-label-32.png"))); // NOI18N
          jButton2.setText("Etiketat");
          jButton2.setFocusable(false);
          jButton2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton2.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton2);

          jButton1.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
          jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ic/Discount-32.png"))); // NOI18N
          jButton1.setText("Ofertat");
          jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
          jButton1.setFocusable(false);
          jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          jButton1.setOpaque(false);
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          jToolBar1.add(jButton1);
          jToolBar1.add(filler1);

          jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
          jLabel1.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " [" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "]");
          jToolBar1.add(jLabel1);
          int delay = 1000;
          ActionListener timeUpdateTaskPerformer = new ActionListener() {
               public void actionPerformed(ActionEvent evt) {
                    jLabel1.setText(
                         LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + 
                         " [" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "]"
                    );
               }
          };
          new Timer(delay, timeUpdateTaskPerformer).start();

          getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

          jToolBar2.setFloatable(false);
          jToolBar2.setRollover(true);

          jLabel2.setFont(new java.awt.Font("Lucida Console", 0, 10)); // NOI18N
          jLabel2.setText("<html>\nCopyright &copy; 2017 redis-it.com<br/>\nMundesuar nga R. Shabani nen lishencen GNU GPL v3.0<br/>\nCel.: <a href='tel:00355692048755'>(+355) 69 20 48 755</a> Email: <a href='mailto:redjan.shabani@gmail.com'>redjan.shabani@gmail.com</a><br/>\n</html>");
          jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
          jToolBar2.add(jLabel2);

          getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_END);

          jDesktopPane1.setBackground(new java.awt.Color(176, 176, 255));
          jDesktopPane1.setPreferredSize(new java.awt.Dimension(800, 450));

          javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
          jDesktopPane1.setLayout(jDesktopPane1Layout);
          jDesktopPane1Layout.setHorizontalGroup(
               jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 690, Short.MAX_VALUE)
          );
          jDesktopPane1Layout.setVerticalGroup(
               jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 306, Short.MAX_VALUE)
          );

          getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          for(JInternalFrame frame : this.jDesktopPane1.getAllFrames()){
               if(frame instanceof ProductFrame){
                    frame.moveToFront();
                    return;
               }
          }

          ProductFrame frame = new ProductFrame();
          this.jDesktopPane1.add(frame);
          frame.setVisible(true);
     }//GEN-LAST:event_jButton4ActionPerformed

     private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
          for(JInternalFrame frame : this.jDesktopPane1.getAllFrames()){
               if(frame instanceof StockFrame){
                    frame.moveToFront();
                    return;
               }
          }

          StockFrame frame = new StockFrame();
          this.jDesktopPane1.add(frame);
          frame.setVisible(true);
     }//GEN-LAST:event_jButton5ActionPerformed

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          for(JInternalFrame frame : this.jDesktopPane1.getAllFrames()){
               if(frame instanceof SpecialLabelFrame){
                    frame.moveToFront();
                    return;
               }
          }

          SpecialLabelFrame frame = new SpecialLabelFrame();
          this.jDesktopPane1.add(frame);
          frame.setVisible(true);
     }//GEN-LAST:event_jButton1ActionPerformed

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          for(JInternalFrame frame : this.jDesktopPane1.getAllFrames()){
               if(frame instanceof PriceLabelFrame){
                    frame.moveToFront();
                    return;
               }
          }

          PriceLabelFrame frame = new PriceLabelFrame();
          this.jDesktopPane1.add(frame);
          frame.setVisible(true);
     }//GEN-LAST:event_jButton2ActionPerformed

     private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
          File temp = new File("./temp");
		temp.mkdirs();
     }//GEN-LAST:event_formWindowOpened

     private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
          File temp = new File("./temp");
		String[]entries = temp.list();
		for(String s: entries){
		    File currentFile = new File(temp.getPath(), s);
		    currentFile.delete();
		}
		temp.delete();
     }//GEN-LAST:event_formWindowClosing

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.Box.Filler filler1;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton4;
     private javax.swing.JButton jButton5;
     private javax.swing.JDesktopPane jDesktopPane1;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JToolBar jToolBar2;
     // End of variables declaration//GEN-END:variables
}
