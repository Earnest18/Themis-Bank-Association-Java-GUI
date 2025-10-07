/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package EMPLOYEE_VIEW;

import javax.swing.JOptionPane;
import ADMIN_VIEW.Dashboard;
import CLIENT_VIEW.CLASS_PANES;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author reyes
 */

public class Log extends javax.swing.JPanel {
         public static  String tellername;
    private CLIENT_VIEW.Frame frame;
    public Log(CLIENT_VIEW.Frame frame) {
        initComponents();
        limits();
            setDoubleBuffered(true);
        this.frame = frame;
    }
    public boolean validateEmployeeLogin(String username, String password) {
    boolean isValid = false;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            String sql = "SELECT * FROM new_registered_employee WHERE Username = ? AND Password = ?";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);

                try (java.sql.ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
             frame.TRANSACTION = rs.getString("Position"); ///THIS ONE
                        isValid = true; 
              String insertSql = "INSERT INTO logs (Employee_ID, Date, Time, logging) VALUES (?, ?, ?, ?)";
            try (java.sql.PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                        tellername = rs.getString("Name").substring(0,rs.getString("Name").indexOf(" "));
                        System.out.print(tellername);
                        Date date = new Date();
                        SimpleDateFormat d = new SimpleDateFormat("MMMM dd, YYYY");
                        SimpleDateFormat t = new SimpleDateFormat("hh:mm a");
                        
                        String DATE = d.format(date);
                        String TIME = t.format(date);
                        String Employee =   rs.getString("Employee_ID");
                         
                        psInsert.setString(1, Employee);
                        psInsert.setString(2, DATE);
                        psInsert.setString(3, TIME);
                        psInsert.setString(4, "logged in");
                         
                     
                        frame.setContentPane(new EMPLOYEE_VIEW.Dashboard(frame));
                        frame.revalidate();
                psInsert.executeUpdate();
                psInsert.close();
            }

                     
                        
                    } else {
                        NAME.setText(null); PASSWORD.setText(null);
                      CLASS_PANES.showMessageBox("User not found.");
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
      CLASS_PANES.showMessageBox("Error validating login: " + e.getMessage());
    }

    return isValid;
}
    
    public void limits() {
    // USERNAME: allow only letters, 3–15 characters
    

    // PASSWORD: exactly 5 digits (numbers only)
    PASSWORD.setDocument(new javax.swing.text.PlainDocument() {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException {
            if (str == null) return;

            String newText = getText(0, getLength()) + str;

            if (newText.length() <= 8 && newText.matches("[0-9]*")) {
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

        NAME = new javax.swing.JTextField();
        LOGIN1 = new javax.swing.JButton();
        CLEAR = new javax.swing.JButton();
        LOGIN = new javax.swing.JButton();
        PASSWORD = new javax.swing.JPasswordField();
        background = new javax.swing.JLabel();

        setLayout(null);

        NAME.setBackground(java.awt.Color.white);
        NAME.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        NAME.setBorder(null);
        add(NAME);
        NAME.setBounds(450, 366, 400, 30);

        LOGIN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/EU.png"))); // NOI18N
        LOGIN1.setBorderPainted(false);
        LOGIN1.setContentAreaFilled(false);
        LOGIN1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/EC.png"))); // NOI18N
        LOGIN1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/EC.png"))); // NOI18N
        LOGIN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGIN1ActionPerformed(evt);
            }
        });
        add(LOGIN1);
        LOGIN1.setBounds(24, 175, 57, 57);

        CLEAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLU.png"))); // NOI18N
        CLEAR.setBorderPainted(false);
        CLEAR.setContentAreaFilled(false);
        CLEAR.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CLEAR.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARActionPerformed(evt);
            }
        });
        add(CLEAR);
        CLEAR.setBounds(440, 517, 198, 51);

        LOGIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFU.png"))); // NOI18N
        LOGIN.setBorderPainted(false);
        LOGIN.setContentAreaFilled(false);
        LOGIN.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        LOGIN.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        LOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINActionPerformed(evt);
            }
        });
        add(LOGIN);
        LOGIN.setBounds(665, 517, 198, 51);

        PASSWORD.setBackground(java.awt.Color.white);
        PASSWORD.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        PASSWORD.setBorder(null);
        add(PASSWORD);
        PASSWORD.setBounds(450, 460, 400, 20);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_LOG.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void LOGIN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGIN1ActionPerformed
        frame.setContentPane(new CLIENT_VIEW.Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_LOGIN1ActionPerformed

    private void CLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARActionPerformed
        NAME.setText(null); PASSWORD.setText(null);
    }//GEN-LAST:event_CLEARActionPerformed

    private void LOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINActionPerformed
        //add login method
        EMPLOYEE_VIEW.Dashboard.NA = NAME.getText();
         EMPLOYEE_VIEW.Dashboard.PA = PASSWORD.getText();
        validateEmployeeLogin(NAME.getText(), PASSWORD.getText());
    }//GEN-LAST:event_LOGINActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CLEAR;
    private javax.swing.JButton LOGIN;
    private javax.swing.JButton LOGIN1;
    private javax.swing.JTextField NAME;
    private javax.swing.JPasswordField PASSWORD;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
