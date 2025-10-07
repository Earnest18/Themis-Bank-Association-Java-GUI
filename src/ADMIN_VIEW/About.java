/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import CLIENT_VIEW.Frame;
import CLIENT_VIEW.*;

/**
 *
 * @author reyes
 */
public class About extends javax.swing.JPanel {

    private Frame frame;
    public About(Frame frame) {
        initComponents();
        
        this.frame = frame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FAQS = new javax.swing.JButton();
        ADD_EM = new javax.swing.JButton();
        BACK = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        FAQS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/FAQU.png"))); // NOI18N
        FAQS.setBorderPainted(false);
        FAQS.setContentAreaFilled(false);
        FAQS.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/FAQC.png"))); // NOI18N
        FAQS.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/FAQC.png"))); // NOI18N
        FAQS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FAQSActionPerformed(evt);
            }
        });
        add(FAQS);
        FAQS.setBounds(937, 117, 101, 35);

        ADD_EM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/FINAL BANK QUEUE (13).png"))); // NOI18N
        ADD_EM.setBorderPainted(false);
        ADD_EM.setContentAreaFilled(false);
        ADD_EM.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/FINAL BANK QUEUE (14).png"))); // NOI18N
        ADD_EM.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/FINAL BANK QUEUE (14).png"))); // NOI18N
        ADD_EM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADD_EMActionPerformed(evt);
            }
        });
        add(ADD_EM);
        ADD_EM.setBounds(1053, 114, 230, 40);

        BACK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HU.png"))); // NOI18N
        BACK.setBorderPainted(false);
        BACK.setContentAreaFilled(false);
        BACK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HC.png"))); // NOI18N
        BACK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HC.png"))); // NOI18N
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });
        add(BACK);
        BACK.setBounds(23, 175, 57, 57);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/ABOUTUS.png"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void FAQSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FAQSActionPerformed
        frame.setContentPane(new ADMIN_VIEW.FAQS(frame));
        frame.revalidate();
    }//GEN-LAST:event_FAQSActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed

    private void ADD_EMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADD_EMActionPerformed
        frame.setContentPane(new RegisterEmployee(frame));
        frame.revalidate();
    }//GEN-LAST:event_ADD_EMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD_EM;
    private javax.swing.JButton BACK;
    private javax.swing.JButton FAQS;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
