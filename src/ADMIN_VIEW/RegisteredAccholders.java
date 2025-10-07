/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.DriverManager;
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
public class RegisteredAccholders extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public RegisteredAccholders(CLIENT_VIEW.Frame frame) {
        initComponents();
        update();
          
           jScrollPane1.setOpaque(false);
           jScrollPane1.getViewport().setOpaque(false);
           jScrollPane1.setBorder(null);
   
        this.frame = frame;
    }

    public void update(){
        new javax.swing.Timer(0000, e -> {
            //update table
            Registered_user(REGISTERED);
        }).start();
    }
    
    public void Registered_user(JTable pending) {
    // Define the table model
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Account Number");
    model.addColumn("Account Name");
    model.addColumn("Age");
    model.addColumn("Birthday");
    model.addColumn("Mobile No.");
    model.addColumn("Email");
    model.addColumn("Account Balance");

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            String sql = "SELECT Username, Age, Birthday, Password, MobileNum, Acc_number, balance, Email FROM new_registered_user  ORDER by Acc_number ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql);
                 java.sql.ResultSet rs = ps.executeQuery()) {

                // Loop through each row from DB and add to JTable
                while (rs.next()) {
                    String username = rs.getString("Username");
                    int age = rs.getInt("Age");
                    String birthday = rs.getString("Birthday");
                   String email = rs.getString("Email");
                    String mobileNum = rs.getString("MobileNum");
                   String balancee =  rs.getString("balance");
                    String bal;
                    if(balancee.equals("-----")){
                         bal= "-----";
                    }else if(balancee.equals("0")){
                         bal= "-----";
                    }
                    else{
                         bal = rs.getString("balance") + " Php";
                    }
                   
                     String ACC = rs.getString("Acc_number");
                    

                    model.addRow(new Object[]{ACC,username, age, birthday, mobileNum, email, bal});
                }
            }
        }
        
REGISTERED.setModel(model);
REGISTERED.setOpaque(false);
REGISTERED.setShowGrid(false);
REGISTERED.getTableHeader().setBorder(null);
JTableHeader header = REGISTERED.getTableHeader();
header.setFont(new Font("Nimala UI", Font.BOLD, 12));

header.setForeground(new Color(0x080525));
header.setBackground(new Color(0xebb923));

header.setPreferredSize(new Dimension(header.getWidth(), 30));

DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
centerRenderer.setVerticalAlignment(JLabel.CENTER);
centerRenderer.setOpaque(false);

for (int i = 0; i < REGISTERED.getColumnCount(); i++) {
    REGISTERED.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
REGISTERED.getColumnModel().getColumn(0).setPreferredWidth(200);  
REGISTERED.getColumnModel().getColumn(1).setPreferredWidth(160); 
REGISTERED.getColumnModel().getColumn(2).setPreferredWidth(40); 
REGISTERED.getColumnModel().getColumn(3).setPreferredWidth(90);   
REGISTERED.getColumnModel().getColumn(4).setPreferredWidth(100);  
REGISTERED.getColumnModel().getColumn(5).setPreferredWidth(205);  
REGISTERED.getColumnModel().getColumn(6).setPreferredWidth(100);  




    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading pending");
 }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EMPLOYEES = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        REGISTERED = new javax.swing.JTable();
        BACK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        EMPLOYEES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        EMPLOYEES.setBorderPainted(false);
        EMPLOYEES.setContentAreaFilled(false);
        EMPLOYEES.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        EMPLOYEES.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        EMPLOYEES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMPLOYEESActionPerformed(evt);
            }
        });
        add(EMPLOYEES);
        EMPLOYEES.setBounds(0, 275, 318, 90);

        REGISTERED.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        REGISTERED.setModel(new javax.swing.table.DefaultTableModel(
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
        REGISTERED.setRowHeight(30);
        jScrollPane1.setViewportView(REGISTERED);

        add(jScrollPane1);
        jScrollPane1.setBounds(320, 153, 980, 487);

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AD_ARA.gif"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void EMPLOYEESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMPLOYEESActionPerformed
        frame.setContentPane(new RegisteredEmployees(frame));
        frame.revalidate();
    }//GEN-LAST:event_EMPLOYEESActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new AccountManagement(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JButton EMPLOYEES;
    private javax.swing.JTable REGISTERED;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
