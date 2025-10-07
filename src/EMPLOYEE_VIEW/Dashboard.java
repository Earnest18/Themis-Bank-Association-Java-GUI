/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package EMPLOYEE_VIEW;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author reyes
 */
public class Dashboard extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public static String NA;
    public static String PA;
    public Dashboard(CLIENT_VIEW.Frame frame) {
        initComponents();
        
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
                        isValid = true; 
              String insertSql = "INSERT INTO logs (Employee_ID, Date, Time, logging) VALUES (?, ?, ?, ?)";
            try (java.sql.PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                
                        Date date = new Date();
                        SimpleDateFormat d = new SimpleDateFormat("MMMM dd, YYYY");
                        SimpleDateFormat t = new SimpleDateFormat("hh:mm a");
                        
                        String DATE = d.format(date);
                        String TIME = t.format(date);
                        String Employee =   rs.getString("Employee_ID");
                         
                        psInsert.setString(1, Employee);
                        psInsert.setString(2, DATE);
                        psInsert.setString(3, TIME);
                        psInsert.setString(4, "logged out");
                        frame.setContentPane(new Dashboard(frame));
                        frame.revalidate();
                psInsert.executeUpdate();
                psInsert.close();
            }
                    } 
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error validating login: " + e.getMessage());
    }

    return isValid;
}
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ACCOUNT_APPROVAL = new javax.swing.JButton();
        HISTORY = new javax.swing.JButton();
        LOGOUT = new javax.swing.JButton();
        QUEUING = new javax.swing.JButton();
        ABT = new javax.swing.JButton();
        FAQS = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        ACCOUNT_APPROVAL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/AAU.png"))); // NOI18N
        ACCOUNT_APPROVAL.setBorderPainted(false);
        ACCOUNT_APPROVAL.setContentAreaFilled(false);
        ACCOUNT_APPROVAL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/AAC.png"))); // NOI18N
        ACCOUNT_APPROVAL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/AAC.png"))); // NOI18N
        ACCOUNT_APPROVAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACCOUNT_APPROVALActionPerformed(evt);
            }
        });
        add(ACCOUNT_APPROVAL);
        ACCOUNT_APPROVAL.setBounds(891, 275, 368, 95);

        HISTORY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HIU.png"))); // NOI18N
        HISTORY.setBorderPainted(false);
        HISTORY.setContentAreaFilled(false);
        HISTORY.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HIC.png"))); // NOI18N
        HISTORY.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HIC.png"))); // NOI18N
        HISTORY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HISTORYActionPerformed(evt);
            }
        });
        add(HISTORY);
        HISTORY.setBounds(40, 434, 368, 95);

        LOGOUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/LOU.png"))); // NOI18N
        LOGOUT.setBorderPainted(false);
        LOGOUT.setContentAreaFilled(false);
        LOGOUT.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/LOC.png"))); // NOI18N
        LOGOUT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/LOC.png"))); // NOI18N
        LOGOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGOUTActionPerformed(evt);
            }
        });
        add(LOGOUT);
        LOGOUT.setBounds(891, 434, 368, 95);

        QUEUING.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/QUU.png"))); // NOI18N
        QUEUING.setBorderPainted(false);
        QUEUING.setContentAreaFilled(false);
        QUEUING.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/QUC.png"))); // NOI18N
        QUEUING.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/QUC.png"))); // NOI18N
        QUEUING.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QUEUINGActionPerformed(evt);
            }
        });
        add(QUEUING);
        QUEUING.setBounds(40, 275, 368, 95);

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
        ABT.setBounds(937, 115, 144, 35);

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
        FAQS.setBounds(1150, 116, 101, 35);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_MAIN.gif"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void HISTORYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HISTORYActionPerformed
        frame.setContentPane(new HistoryCompleted(frame));
        frame.revalidate();
    }//GEN-LAST:event_HISTORYActionPerformed

    private void LOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGOUTActionPerformed
        validateEmployeeLogin(NA,PA);
        frame.setContentPane(new CLIENT_VIEW.Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_LOGOUTActionPerformed

    private void QUEUINGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QUEUINGActionPerformed
        frame.setContentPane(new QueueManagement(frame));
        frame.revalidate();
    }//GEN-LAST:event_QUEUINGActionPerformed

    private void ACCOUNT_APPROVALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACCOUNT_APPROVALActionPerformed
        frame.setContentPane(new AccApproval_Pending(frame));
        frame.revalidate();
    }//GEN-LAST:event_ACCOUNT_APPROVALActionPerformed

    private void ABTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ABTActionPerformed
        frame.setContentPane(new About(frame));
        frame.revalidate();
    }//GEN-LAST:event_ABTActionPerformed

    private void FAQSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FAQSActionPerformed
       frame.setContentPane(new Faqs(frame));
        frame.revalidate();
    }//GEN-LAST:event_FAQSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ABT;
    private javax.swing.JButton ACCOUNT_APPROVAL;
    private javax.swing.JButton FAQS;
    private javax.swing.JButton HISTORY;
    private javax.swing.JButton LOGOUT;
    private javax.swing.JButton QUEUING;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
