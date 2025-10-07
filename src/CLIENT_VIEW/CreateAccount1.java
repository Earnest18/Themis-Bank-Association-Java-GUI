/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.*;
import java.awt.*;
import java.util.Date;

import CLIENT_VIEW.CLASS_PANES;
import javax.swing.SwingUtilities;

/**
 *
 * @author reyes
 */
public class CreateAccount1 extends javax.swing.JPanel {

    private Frame frame;
    public static Date DATE_VALUE;
    public static int amount;
    public static String NAME_VALUE;
    public static String AGE_VALUE;
    public static String BD_VALUE;
    public static String MOBILENUM_VALUE;
    public static String PASSWORD_VALUE;
    public static String EMAIL_VALUE;

    //public static String date_today;
    public static String date_input;

    public CreateAccount1(Frame frame) {
        initComponents();
        limits();
        setDoubleBuffered(true);
        this.frame = frame;

        BD.setDate(DATE_VALUE);
        MOBILENUM.setText(MOBILENUM_VALUE);
        PASSWORD.setText(PASSWORD_VALUE);
        EMAIL.setText(EMAIL_VALUE);
        NAME.setText(NAME_VALUE);

    }

    //this helps to insert user in database
    public void limits() {

        MOBILENUM.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                    throws javax.swing.text.BadLocationException {
                if (str == null) {
                    return;
                }

                String newText = getText(0, getLength()) + str;

                if (newText.length() <= 11 && newText.matches("[0-9]*")) {
                    super.insertString(offs, str, a);
                } else {

                }
            }
        });
        PASSWORD.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                    throws javax.swing.text.BadLocationException {
                if (str == null) {
                    return;
                }

                String newText = getText(0, getLength()) + str;

                if (newText.length() <= 8 && newText.matches("[0-9]*")) {
                    super.insertString(offs, str, a);
                } else if (!newText.matches("[0-9]*")) {
                    CLASS_PANES.showMessageBox("DIGITS ONLY");
                }
            }
        });
        NAME.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                    throws javax.swing.text.BadLocationException {
                if (str == null) {
                    return;
                }

                String newText = getText(0, getLength());
                newText = newText.substring(0, offs) + str + newText.substring(offs);

                // ✅ Fixed regex — allows letters, spaces, commas, dashes, and only one dot
                if (newText.matches("^[A-Za-z,\\-\\s]*\\.?[A-Za-z,\\-\\s]*$")) {
                    super.insertString(offs, str, a);
                } else {

                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CONFIRM = new javax.swing.JButton();
        CANCEL = new javax.swing.JButton();
        BACK = new javax.swing.JButton();
        PASSWORD = new javax.swing.JPasswordField();
        EMAIL = new javax.swing.JTextField();
        MOBILENUM = new javax.swing.JTextField();
        NAME = new javax.swing.JTextField();
        ABT = new javax.swing.JButton();
        FAQS = new javax.swing.JButton();
        BD = new com.toedter.calendar.JDateChooser();
        background = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setDoubleBuffered(false);
        setLayout(null);

        CONFIRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFU.png"))); // NOI18N
        CONFIRM.setBorderPainted(false);
        CONFIRM.setContentAreaFilled(false);
        CONFIRM.setDoubleBuffered(true);
        CONFIRM.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONFIRMActionPerformed(evt);
            }
        });
        add(CONFIRM);
        CONFIRM.setBounds(950, 534, 198, 51);

        CANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLU.png"))); // NOI18N
        CANCEL.setBorderPainted(false);
        CANCEL.setContentAreaFilled(false);
        CANCEL.setDoubleBuffered(true);
        CANCEL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });
        add(CANCEL);
        CANCEL.setBounds(683, 534, 198, 51);

        BACK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HU.png"))); // NOI18N
        BACK.setBorderPainted(false);
        BACK.setContentAreaFilled(false);
        BACK.setDoubleBuffered(true);
        BACK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HC.png"))); // NOI18N
        BACK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HC.png"))); // NOI18N
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });
        add(BACK);
        BACK.setBounds(23, 175, 57, 57);

        PASSWORD.setBackground(new java.awt.Color(255, 255, 255));
        PASSWORD.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        PASSWORD.setBorder(null);
        PASSWORD.setDoubleBuffered(true);
        add(PASSWORD);
        PASSWORD.setBounds(305, 495, 340, 25);

        EMAIL.setBackground(new java.awt.Color(255, 255, 255));
        EMAIL.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        EMAIL.setBorder(null);
        EMAIL.setDoubleBuffered(true);
        EMAIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMAILActionPerformed(evt);
            }
        });
        add(EMAIL);
        EMAIL.setBounds(732, 451, 410, 25);

        MOBILENUM.setBackground(new java.awt.Color(255, 255, 255));
        MOBILENUM.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        MOBILENUM.setBorder(null);
        MOBILENUM.setDoubleBuffered(true);
        MOBILENUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOBILENUMActionPerformed(evt);
            }
        });
        add(MOBILENUM);
        MOBILENUM.setBounds(305, 451, 320, 25);

        NAME.setBackground(new java.awt.Color(255, 255, 255));
        NAME.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        NAME.setBorder(null);
        NAME.setDoubleBuffered(true);
        add(NAME);
        NAME.setBounds(305, 404, 450, 25);

        ABT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/ABU.png"))); // NOI18N
        ABT.setBorderPainted(false);
        ABT.setContentAreaFilled(false);
        ABT.setDoubleBuffered(true);
        ABT.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/ABC.png"))); // NOI18N
        ABT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/ABC.png"))); // NOI18N
        ABT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ABTActionPerformed(evt);
            }
        });
        add(ABT);
        ABT.setBounds(769, 117, 144, 35);

        FAQS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/FAQU.png"))); // NOI18N
        FAQS.setBorderPainted(false);
        FAQS.setContentAreaFilled(false);
        FAQS.setDoubleBuffered(true);
        FAQS.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/FAQC.png"))); // NOI18N
        FAQS.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/FAQC.png"))); // NOI18N
        FAQS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FAQSActionPerformed(evt);
            }
        });
        add(FAQS);
        FAQS.setBounds(937, 117, 101, 35);

        BD.setBackground(new java.awt.Color(255, 255, 255));
        BD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(BD);
        BD.setBounds(907, 404, 240, 25);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AC_CAC2.png"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed

    private void CONFIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONFIRMActionPerformed

        String mail = EMAIL.getText();
        String name = NAME.getText();
        String number = MOBILENUM.getText();
        String pass = PASSWORD.getText();

        // Get today's date as LocalDate
        LocalDate today = LocalDate.now();
        Date selectedDate = BD.getDate();
        LocalDate birthDate = selectedDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        SimpleDateFormat formatdate = new SimpleDateFormat("MM-dd-YYYY");
        if (!NAME.getText().isEmpty()) {
            // Convert JDateChooser value to LocalDate
            if (selectedDate == null) {
            CLASS_PANES.showMessageBox("Please select a date of birth.");
                return;
            }

            // Check if DOB is after today (future date = invalid)
            if (!birthDate.isAfter(today)) {
                int age = Period.between(birthDate, today).getYears();

                // Check if user is under 18
                if (age >= 18) {

                    if (PASSWORD.getText().length() >= 8 && PASSWORD.getText().length() <= 20) {
                        // Confirmation

                        if (MOBILENUM.getText().length() == 11) {
                            if (!EMAIL.getText().isEmpty()) {
                                if (EMAIL.getText().contains("@gmail.com")) {
                                    int choose = CLASS_PANES.showConfirmBox("Are you sure you inputted the right information");

                                    if (choose == JOptionPane.YES_OPTION) {

                                        String bd = formatdate.format(selectedDate);

                                        CLASS_PANES.showMessageBox("You're personal information will be processed. You may proceed to step 2 for depositing your starting balance as required.");

                                        SwingUtilities.invokeLater(() -> {

                                            frame.setContentPane(new CreateAccount2(frame));
                                            frame.revalidate();
                                        });

                                        DATE_VALUE = selectedDate;
                                        NAME_VALUE = name;
                                        AGE_VALUE = String.valueOf(age);
                                        BD_VALUE = bd;
                                        MOBILENUM_VALUE = number;
                                        PASSWORD_VALUE = pass;
                                        EMAIL_VALUE = mail;

                                    }
                                } else {

                                    CLASS_PANES.showMessageBox("Use your Gmail Account.");

                                }
                            } else {

                                CLASS_PANES.showMessageBox("Please enter your Gmail Account.");

                            }
                        } else {

                            CLASS_PANES.showMessageBox("Mobile number should be exactly 11 digits long.");

                        }

                    } else if (PASSWORD.getText().length() < 8) {

                        CLASS_PANES.showMessageBox("Password must be 8 digits only.");

                        return;
                    } else if (PASSWORD.getText().length() > 20) {

                        CLASS_PANES.showMessageBox("Password is too long.");

                        return;
                    }

                } else {

                    CLASS_PANES.showMessageBox("Your age is not eligible to open an account!");

                    return;
                }

            }
            if (birthDate.isAfter(today)) {

                CLASS_PANES.showMessageBox("Invalid Date! Birthdate cannot be in the future.");

                return;
            }
        } else {

            CLASS_PANES.showMessageBox("Please enter your name.");

        }


    }//GEN-LAST:event_CONFIRMActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed
        NAME.setText(null);
        PASSWORD.setText(null);
        MOBILENUM.setText(null);
    }//GEN-LAST:event_CANCELActionPerformed

    private void ABTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ABTActionPerformed
        frame.setContentPane(new About(frame));
        frame.revalidate();
    }//GEN-LAST:event_ABTActionPerformed

    private void FAQSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FAQSActionPerformed
        frame.setContentPane(new Faqs(frame));
        frame.revalidate();
    }//GEN-LAST:event_FAQSActionPerformed

    private void MOBILENUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOBILENUMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOBILENUMActionPerformed

    private void EMAILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMAILActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMAILActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ABT;
    private javax.swing.JButton BACK;
    public static com.toedter.calendar.JDateChooser BD;
    private javax.swing.JButton CANCEL;
    private javax.swing.JButton CONFIRM;
    public static javax.swing.JTextField EMAIL;
    private javax.swing.JButton FAQS;
    public static javax.swing.JTextField MOBILENUM;
    public static javax.swing.JTextField NAME;
    public static javax.swing.JPasswordField PASSWORD;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
