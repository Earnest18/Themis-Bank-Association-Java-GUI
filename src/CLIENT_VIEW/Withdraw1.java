/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Carl joshua
 */
public class Withdraw1 extends javax.swing.JPanel {

     private Frame frame;
     public static  String raw;
    public static  String formatted;
    public static  int caretPos;
    public Withdraw1(Frame frame) {
        initComponents();
        setDoubleBuffered(true);  
        attachAmountFormatter();
                
        this.frame = frame;
        
    }
    private void attachAmountFormatter() {
    
    ((AbstractDocument) AMOUNT.getDocument()).setDocumentFilter(new DocumentFilter() {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string != null && string.matches("\\d+")) { // only digits
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text != null && text.matches("\\d+")) { // only digits
                super.replace(fb, offset, length, text, attrs);
            } else if (text == null || text.isEmpty()) { // allow delete
                super.replace(fb, offset, length, text, attrs);
            }
        }
    });

    // ✅ Formatter for Peso currency
    AMOUNT.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        private boolean isUpdating = false;

        private void formatText() {
            if (isUpdating) return;

            raw = AMOUNT.getText().replaceAll("[^\\d]", ""); // keep only digits
            if (raw.isEmpty()) {
                return;
            }

            try {
                long value = Long.parseLong(raw);

                NumberFormat pesoFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
                pesoFormat.setMaximumFractionDigits(0);

                String formatted = pesoFormat.format(value);
                formatted = formatted.replaceAll("(?i)PHP", "₱");

                if (!AMOUNT.getText().equals(formatted)) {
                    isUpdating = true;
                    String finalFormatted = formatted;

                    SwingUtilities.invokeLater(() -> {
                        // ⚡ bypass filter by directly replacing whole text
                        ((AbstractDocument) AMOUNT.getDocument()).setDocumentFilter(null);
                        AMOUNT.setText(finalFormatted);
                        AMOUNT.setCaretPosition(AMOUNT.getDocument().getLength()); // cursor at end
                        ((AbstractDocument) AMOUNT.getDocument()).setDocumentFilter(new NumericFilter());
                        isUpdating = false;
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            formatText();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            formatText();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            formatText();
        }
    });
}

// 👉 Separate filter so we can reattach after formatting
class NumericFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string != null && string.matches("\\d+")) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text != null && text.matches("\\d+")) {
            super.replace(fb, offset, length, text, attrs);
        } else if (text == null || text.isEmpty()) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BACK4 = new javax.swing.JButton();
        CANCEL = new javax.swing.JButton();
        CONFIRM = new javax.swing.JButton();
        AMOUNT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        BACK4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BU.png"))); // NOI18N
        BACK4.setBorderPainted(false);
        BACK4.setContentAreaFilled(false);
        BACK4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BC.png"))); // NOI18N
        BACK4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BC.png"))); // NOI18N
        BACK4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACK4ActionPerformed(evt);
            }
        });
        add(BACK4);
        BACK4.setBounds(24, 175, 57, 57);

        CANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLU.png"))); // NOI18N
        CANCEL.setBorderPainted(false);
        CANCEL.setContentAreaFilled(false);
        CANCEL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });
        add(CANCEL);
        CANCEL.setBounds(376, 529, 199, 51);

        CONFIRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFU.png"))); // NOI18N
        CONFIRM.setBorderPainted(false);
        CONFIRM.setContentAreaFilled(false);
        CONFIRM.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONFIRMActionPerformed(evt);
            }
        });
        add(CONFIRM);
        CONFIRM.setBounds(644, 529, 199, 51);

        AMOUNT.setBackground(new java.awt.Color(255, 255, 255));
        AMOUNT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AMOUNT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AMOUNT.setBorder(null);
        AMOUNT.setDoubleBuffered(true);
        AMOUNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AMOUNTActionPerformed(evt);
            }
        });
        add(AMOUNT);
        AMOUNT.setBounds(388, 440, 450, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AC_TRANSAC-WD.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void BACK4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACK4ActionPerformed
       Transactions transac = new Transactions(frame);
        frame.setContentPane(transac);
        frame.revalidate();
    }//GEN-LAST:event_BACK4ActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed
        
        AMOUNT.setText(null);
       

    }//GEN-LAST:event_CANCELActionPerformed

    private void CONFIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONFIRMActionPerformed
         int amount = Integer.parseInt(raw);
         
         if(amount >=500 && amount <= 10000000){
             
             if(amount %100 ==0){
                    int i = CLASS_PANES.showConfirmBox("Are you sure you want to withdraw an amount of " + AMOUNT.getText() + " Php on you account?");
                    if(i == JOptionPane.YES_OPTION){

                    Frame.bal=amount;
                    CLASS_PANES.showMessageBox("You may proceed to Step 2 for account verification");
                    System.out.println(amount);


                    frame.setContentPane(new Withdraw2(frame));
                    frame.revalidate();

             }
             }else{
                  CLASS_PANES.showMessageBox("You can not withdraw an amount that is not divisible of 100");
                  AMOUNT.setText(null);
             }
             
    
    
    }
         else if(amount <500){
                CLASS_PANES.showMessageBox("You cannot withdraw an amount " + amount + " Php.\n Minimum withdraw requirement: 500 Php");
                AMOUNT.setText(null);
         }else if(amount >10000000){
                CLASS_PANES.showMessageBox("You cannot withdraw an amount " + amount + " Php.\n Maximum withdraw requirement: 10,000,000 Php");
                AMOUNT.setText(null);
         }
        
    }//GEN-LAST:event_CONFIRMActionPerformed

    private void AMOUNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AMOUNTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AMOUNTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AMOUNT;
    private javax.swing.JButton BACK4;
    private javax.swing.JButton CANCEL;
    private javax.swing.JButton CONFIRM;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
