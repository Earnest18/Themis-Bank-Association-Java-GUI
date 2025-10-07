/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import javax.swing.JOptionPane;

/**
 *
 * @author reyes
 */
public class Login extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public Login(CLIENT_VIEW.Frame frame) {
        initComponents();
        limits();
        this.frame = frame;
    }
    
    public void limits() {
    
    // PASSWORD: exactly 5 digits (numbers only)
    PASSWORD.setDocument(new javax.swing.text.PlainDocument() {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException {
            if (str == null) return;

            String newText = getText(0, getLength()) + str;

            if (newText.length() <= 5 && newText.matches("[0-9]*")) {
                super.insertString(offs, str, a);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Password must be exactly 5 digits (numbers only).");
            }
        }
    });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PASSWORD = new javax.swing.JPasswordField();
        CLEAR1 = new javax.swing.JButton();
        LOGIN1 = new javax.swing.JButton();
        LOGIN2 = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        PASSWORD.setBackground(java.awt.Color.white);
        PASSWORD.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        PASSWORD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PASSWORD.setBorder(null);
        add(PASSWORD);
        PASSWORD.setBounds(450, 410, 400, 30);

        CLEAR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLU.png"))); // NOI18N
        CLEAR1.setBorderPainted(false);
        CLEAR1.setContentAreaFilled(false);
        CLEAR1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CLEAR1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CLEAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEAR1ActionPerformed(evt);
            }
        });
        add(CLEAR1);
        CLEAR1.setBounds(440, 525, 198, 51);

        LOGIN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFU.png"))); // NOI18N
        LOGIN1.setBorderPainted(false);
        LOGIN1.setContentAreaFilled(false);
        LOGIN1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        LOGIN1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        LOGIN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGIN1ActionPerformed(evt);
            }
        });
        add(LOGIN1);
        LOGIN1.setBounds(665, 525, 198, 51);

        LOGIN2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/EU.png"))); // NOI18N
        LOGIN2.setBorderPainted(false);
        LOGIN2.setContentAreaFilled(false);
        LOGIN2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/EC.png"))); // NOI18N
        LOGIN2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/EC.png"))); // NOI18N
        LOGIN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGIN2ActionPerformed(evt);
            }
        });
        add(LOGIN2);
        LOGIN2.setBounds(24, 175, 57, 57);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AD_LOG-ezgif.com-optimize.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void CLEAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEAR1ActionPerformed
       PASSWORD.setText(null);
    }//GEN-LAST:event_CLEAR1ActionPerformed

    private void LOGIN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGIN1ActionPerformed
        if (PASSWORD.getText().equals("000")){
            System.out.print("LOGIN SUCCESSFULLY!");
            frame.setContentPane(new Dashboard(frame));
            frame.revalidate();
        }

    }//GEN-LAST:event_LOGIN1ActionPerformed

    private void LOGIN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGIN2ActionPerformed
        frame.setContentPane(new CLIENT_VIEW.Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_LOGIN2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CLEAR1;
    private javax.swing.JButton LOGIN1;
    private javax.swing.JButton LOGIN2;
    private javax.swing.JPasswordField PASSWORD;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
