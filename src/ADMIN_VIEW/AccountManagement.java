/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import ADMIN_VIEW.Dashboard;
import ADMIN_VIEW.RegisteredEmployees;
import static ADMIN_VIEW.RegisteredEmployees.REGISTERED;
import CLIENT_VIEW.CLASS_PANES;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author reyes
 */
public class AccountManagement extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public static String employeeID;
    public AccountManagement(CLIENT_VIEW.Frame frame) {
        initComponents();
        
        this.frame = frame;
         update();
        jScrollPane1.setOpaque(false);
           jScrollPane1.getViewport().setOpaque(false);
           jScrollPane1.setBorder(null);
    }
        public void update(){
        new javax.swing.Timer(0000, e -> {
        //load ques from database
        pending_users(PENDING);
        }).start();
        
    }
       
    public void pending_users(JTable pending) {
    // Define the table model
    DefaultTableModel model = new DefaultTableModel();
   
    model.addColumn("Username");
    model.addColumn("Age");
    model.addColumn("Birthday");
    model.addColumn("MobileNum");
    model.addColumn("Email Address");
    model.addColumn("Deposited Amount");

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            String sql = "SELECT Username, Age, Birthday, Password, MobileNum, Email, Amount FROM pending_users WHERE status ='paid'";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql);
                 java.sql.ResultSet rs = ps.executeQuery()) {

                // Loop through each row from DB and add to JTable
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String username = rs.getString("Username");
                    String age = rs.getString("Age");  
                    String birthday = rs.getString("Birthday");
                    String mobileNum = rs.getString("MobileNum");
                    String balance = rs.getString("Amount");

                    model.addRow(new Object[]{username, age, birthday, mobileNum, email, balance});
                }
            }
        }
        
        
pending.setModel(model);
pending.setOpaque(false);
pending.setShowGrid(false);
pending.getTableHeader().setBorder(null);
JTableHeader header = pending.getTableHeader();
header.setFont(new Font("Nimala UI", Font.BOLD, 12));

header.setForeground(new Color(0x080525));
header.setBackground(new Color(0xebb923));

header.setPreferredSize(new Dimension(header.getWidth(), 30));

DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
centerRenderer.setVerticalAlignment(JLabel.CENTER);
centerRenderer.setOpaque(false);

for (int i = 0; i < pending.getColumnCount(); i++) {
    pending.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}


    } catch (Exception e) {
        e.printStackTrace();
       CLASS_PANES.showMessageBox("Error loading pending");
 }
    }
       public void insert_user(int row){
        String PASS = "";
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tbaqs", "root", "")) {
            String sql = "SELECT Password FROM pending_users WHERE Email = ?";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql)){
                 ps.setString(1, PENDING.getValueAt(row,4).toString());
                 
                 try(java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PASS = rs.getString("Password");
                }
            }
            }
                String insertCompleted = "INSERT INTO new_registered_user (Username, Age, Birthday, Password, MobileNum, Acc_number, balance, status, Email) VALUES (?, ?, ?, ?, ?,?,?, ?,?)";
                    try (java.sql.PreparedStatement psInsert = con.prepareStatement(insertCompleted)) {
                        psInsert.setString(1, PENDING.getValueAt(row, 0).toString()); //username
                        psInsert.setString(2, PENDING.getValueAt(row, 1).toString()); //age
                        psInsert.setString(3, PENDING.getValueAt(row, 2).toString()); //birthday
                        psInsert.setString(4, PASS); //password
                        psInsert.setString(5, PENDING.getValueAt(row, 3).toString()); //mobilenum
                        Date date = new Date();
                        SimpleDateFormat Y = new SimpleDateFormat("YYYY");
                        SimpleDateFormat MD = new SimpleDateFormat("MMdd");
                          String countSql = "SELECT COUNT(*) AS total FROM new_registered_user WHERE Acc_number LIKE ?";
            int current_rows = 0;
            try (java.sql.PreparedStatement psCount = con.prepareStatement(countSql)) {
                psCount.setString(1, "%"); // e.g., "2025-%"
                try (java.sql.ResultSet rs = psCount.executeQuery()) {
                    if (rs.next()) {
                        current_rows = rs.getInt("total");
                    }
                }
            }

            // 3. Generate new employee ID
            int newID = current_rows + 1;
            employeeID = String.format("%03d", newID);

                         String nd = Y.format(date);
                           String rd = MD.format(date);
                         
                        String accno = "4103 - " + employeeID  + " - " +  nd + " - " + rd;
                        psInsert.setString(6, accno); 
                        psInsert.setString(7, PENDING.getValueAt(row, 5).toString()); //balance
                        psInsert.setString(8, "Member");
                        psInsert.setString(9, PENDING.getValueAt(row, 4).toString());
                        psInsert.executeUpdate();
                        
                       CLASS_PANES.showMessageBox("Account Name: " + PENDING.getValueAt(row, 0).toString() + "\nStatus: Account Application Approved!");
                    }
                    
                    String deleteSql = "DELETE FROM pending_users WHERE Username = ?";
                    try (java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                        psDelete.setString(1, PENDING.getValueAt(row, 0).toString());
                        psDelete.executeUpdate();
                    }
            }
            
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void delete_user(int row) {
    String deleteSql = "DELETE FROM pending_users WHERE Username = ?";
    try (java.sql.Connection con = java.sql.DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tbaqs", "root", "");
         java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSql)) {

        psDelete.setString(1, PENDING.getValueAt(row, 0).toString()); 
        psDelete.executeUpdate();

      

    } catch (Exception e) {
        e.printStackTrace();
       CLASS_PANES.showMessageBox("Error deleting row.");
    }
}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        REGISTER_AC = new javax.swing.JButton();
        BACK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PENDING = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setLayout(null);

        REGISTER_AC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        REGISTER_AC.setBorderPainted(false);
        REGISTER_AC.setContentAreaFilled(false);
        REGISTER_AC.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        REGISTER_AC.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        REGISTER_AC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTER_ACActionPerformed(evt);
            }
        });
        add(REGISTER_AC);
        REGISTER_AC.setBounds(0, 365, 318, 90);

        BACK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        BACK.setBorderPainted(false);
        BACK.setContentAreaFilled(false);
        BACK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_back.png"))); // NOI18N
        BACK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_back.png"))); // NOI18N
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });
        add(BACK);
        BACK.setBounds(0, 548, 318, 90);

        PENDING.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PENDING.setRowHeight(30);
        PENDING.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PENDINGMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(PENDING);

        add(jScrollPane1);
        jScrollPane1.setBounds(320, 153, 980, 487);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AD_AMAP.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void REGISTER_ACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGISTER_ACActionPerformed
        
        frame.setContentPane(new RegisteredEmployees(frame));
         frame.revalidate();
    }//GEN-LAST:event_REGISTER_ACActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed

    private void PENDINGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PENDINGMousePressed
          int x = PENDING.getSelectedRow();
        
        if (x != -1){
            String user = PENDING.getValueAt(x, 0).toString();
            String password = PENDING.getValueAt(x,3).toString();
            String[] options = {"Approve", "Dismiss", "Cancel"};
            int choice = CLASS_PANES.SelectionBox(options, "Do you want to approve the application of " + user +"?");
            if (choice == 0) {
                insert_user(x);
            } else if (choice == 1) {
               CLASS_PANES.showMessageBox("Account Name: " + user + "\nStatus: Account Application Declined!");
                delete_user(x);
            } else if (choice == 2) {
               
            }
        }
    }//GEN-LAST:event_PENDINGMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JTable PENDING;
    private javax.swing.JButton REGISTER_AC;
    private javax.swing.JLabel background;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
