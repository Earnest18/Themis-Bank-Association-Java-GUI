/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import javax.swing.SwingUtilities;

/**
 *
 * @author reyes
 */
public class Faqs extends javax.swing.JPanel {

    private Frame frame;

    public Faqs(Frame frame) {
        initComponents();
        setDoubleBuffered(true);

        this.frame = frame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ABT = new javax.swing.JButton();
        CREATE_ACC = new javax.swing.JButton();
        BACK = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        ABT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/ABU.png"))); // NOI18N
        ABT.setBorderPainted(false);
        ABT.setContentAreaFilled(false);
        ABT.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/ABC.png"))); // NOI18N
        ABT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/ABC.png"))); // NOI18N
        ABT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ABTActionPerformed(evt);
            }
        });
        add(ABT);
        ABT.setBounds(769, 117, 144, 35);

        CREATE_ACC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CAU.png"))); // NOI18N
        CREATE_ACC.setBorderPainted(false);
        CREATE_ACC.setContentAreaFilled(false);
        CREATE_ACC.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CAC.png"))); // NOI18N
        CREATE_ACC.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CAC.png"))); // NOI18N
        CREATE_ACC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CREATE_ACCActionPerformed(evt);
            }
        });
        add(CREATE_ACC);
        CREATE_ACC.setBounds(1058, 116, 212, 35);

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/FAQS.png"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void ABTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ABTActionPerformed
        SwingUtilities.invokeLater(() -> {

            frame.setContentPane(new CLIENT_VIEW.About(frame));
            frame.revalidate();
        });
    }//GEN-LAST:event_ABTActionPerformed

    private void CREATE_ACCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CREATE_ACCActionPerformed
        SwingUtilities.invokeLater(() -> {

            frame.setContentPane(new CreateAccount1(frame));
            frame.revalidate();
        });
    }//GEN-LAST:event_CREATE_ACCActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed

        SwingUtilities.invokeLater(() -> {
            frame.setContentPane(new CLIENT_VIEW.Transactions(frame));
            frame.revalidate();

        });
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ABT;
    private javax.swing.JButton BACK;
    private javax.swing.JButton CREATE_ACC;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
