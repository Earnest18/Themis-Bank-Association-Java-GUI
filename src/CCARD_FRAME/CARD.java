/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CCARD_FRAME;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Carl joshua
 */
public class CARD extends javax.swing.JFrame {
    public static String USERNAME;
    public static String ACCOUNT;
    
    private int cardY = -60;
    private Timer timer;
    public CARD() {
        initComponents();
        setVisible(false);
        this.setLocation(1200,550);
        setSize(530,380);
    }


    
   public void CreditCard(String Username, String Account) {
    username.setText(Username);
    accnum.setText(Account);

    // Make sure layout allows manual movement
    credit.getParent().setLayout(null);

    timer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            cardY += 4; // Move downward
            credit.setBounds(52, cardY, 396, 228);
          
            if (cardY >= 100) {
                timer.stop();
            }
            credit.getParent().repaint();
            credit.getParent().revalidate();
        }
    });

    timer.start();
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        exert = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        credit = new javax.swing.JPanel();
        accnum = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CREDIT CARD");
        setMaximumSize(new java.awt.Dimension(500, 350));
        setMinimumSize(new java.awt.Dimension(500, 350));
        setPreferredSize(new java.awt.Dimension(500, 350));
        setResizable(false);
        getContentPane().setLayout(null);

        main.setMaximumSize(new java.awt.Dimension(500, 350));
        main.setPreferredSize(new java.awt.Dimension(500, 350));
        main.setLayout(null);

        exert.setOpaque(false);
        exert.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/CARD_EXERT.png"))); // NOI18N
        exert.add(jLabel2);
        jLabel2.setBounds(0, 0, 500, 96);

        main.add(exert);
        exert.setBounds(0, 0, 500, 96);

        credit.setOpaque(false);
        credit.setLayout(null);

        accnum.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 26)); // NOI18N
        accnum.setForeground(new Color(0x041a46));
        accnum.setText("0123 - 4567 -8910 -1112");
        credit.add(accnum);
        accnum.setBounds(35, 160, 310, 50);

        username.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 20)); // NOI18N
        username.setForeground(accnum.getForeground());
        username.setText("Kclei Umadhay");
        credit.add(username);
        username.setBounds(115, 100, 250, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/CCARD.png"))); // NOI18N
        credit.add(jLabel3);
        jLabel3.setBounds(0, 0, 396, 228);

        main.add(credit);
        credit.setBounds(52, 90, 396, 228);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/CARD_BG.gif"))); // NOI18N
        main.add(jLabel1);
        jLabel1.setBounds(0, 0, 500, 350);

        getContentPane().add(main);
        main.setBounds(0, 0, 500, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CARD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accnum;
    private javax.swing.JPanel credit;
    private javax.swing.JPanel exert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel main;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
