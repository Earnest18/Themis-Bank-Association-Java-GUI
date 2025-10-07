/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.DriverManager;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author reyes
 */
public class RegisteredEmployees extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public RegisteredEmployees(CLIENT_VIEW.Frame frame) {
        initComponents();
       
           this.frame = frame;
           
           jScrollPane1.setOpaque(false);
           jScrollPane1.getViewport().setOpaque(false);
           jScrollPane1.setBorder(null);
   


 update();
     
    }
    
    public void update(){
        new javax.swing.Timer(0000, e -> {
            //update table
            Registered_user(REGISTERED);
        }).start();
        
        
       
    }
    
    
    
    public void Registered_user(JTable REGISTERED) {
    // Define the table model
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Employee ID");
    model.addColumn("Name");
    model.addColumn("Username");
    model.addColumn("Age");
    model.addColumn("Birthday");
    model.addColumn("Mobile Number");
    model.addColumn("Email Address");
    model.addColumn("Position");
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "");
             java.sql.PreparedStatement ps = con.prepareStatement(
                "SELECT Employee_ID, Name, Username, Age, Birthday, Gender, MobileNum, Email, Position FROM new_registered_employee ORDER by Username ASC"); // ✅ fixed table name
             java.sql.ResultSet rs = ps.executeQuery()) {

            // Loop through each row from DB and add to JTable
            while (rs.next()) {
                String name = rs.getString("Name");
                String uname = rs.getString("Username");
                String em = rs.getString("Employee_ID"); 
                String  age = rs.getString("Age");
                String bd = rs.getString("Birthday");
                String mobile = rs.getString("MobileNum");
                String ema = rs.getString("Email");
                String pos = rs.getString("Position") ;
                

                model.addRow(new Object[]{em,name, uname, age, bd, mobile, ema, pos});
            }
        }

        
REGISTERED.setModel(model);
REGISTERED.setOpaque(false);
REGISTERED.setShowGrid(false);
JTableHeader header = REGISTERED.getTableHeader();
header.setFont(new Font("Nimala UI", Font.BOLD, 12));

header.setForeground(new Color(0x080525));
header.setBackground(new Color(0xebb923));

header.setPreferredSize(new Dimension(header.getWidth(), 30));

// Disable auto-resize so widths are respected
REGISTERED.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Center-align all cells
DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
centerRenderer.setVerticalAlignment(JLabel.CENTER);
centerRenderer.setOpaque(false);

for (int i = 0; i < REGISTERED.getColumnCount(); i++) {
    REGISTERED.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}

// Example: set custom widths
REGISTERED.getColumnModel().getColumn(0).setPreferredWidth(100);   // Employee ID
REGISTERED.getColumnModel().getColumn(1).setPreferredWidth(160);  // Name
REGISTERED.getColumnModel().getColumn(2).setPreferredWidth(100);  // Username
REGISTERED.getColumnModel().getColumn(3).setPreferredWidth(40);   // Age
REGISTERED.getColumnModel().getColumn(4).setPreferredWidth(90);  // Birthday
REGISTERED.getColumnModel().getColumn(5).setPreferredWidth(100);  // Mobile
REGISTERED.getColumnModel().getColumn(6).setPreferredWidth(205);  // Email
REGISTERED.getColumnModel().getColumn(7).setPreferredWidth(182);  // Position


    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading employees: " + e.getMessage());
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BACK = new javax.swing.JButton();
        ACC_HOLDER = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        REGISTERED = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setLayout(null);

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

        ACC_HOLDER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        ACC_HOLDER.setBorderPainted(false);
        ACC_HOLDER.setContentAreaFilled(false);
        ACC_HOLDER.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        ACC_HOLDER.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        ACC_HOLDER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACC_HOLDERActionPerformed(evt);
            }
        });
        add(ACC_HOLDER);
        ACC_HOLDER.setBounds(0, 365, 318, 90);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

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
        REGISTERED.setShowGrid(true);
        jScrollPane1.setViewportView(REGISTERED);

        add(jScrollPane1);
        jScrollPane1.setBounds(320, 153, 980, 487);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AD_ARE.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void ACC_HOLDERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACC_HOLDERActionPerformed
        frame.setContentPane(new RegisteredAccholders(frame));
        frame.revalidate();
    }//GEN-LAST:event_ACC_HOLDERActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new AccountManagement(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton ACC_HOLDER;
    public static javax.swing.JButton BACK;
    public static javax.swing.JTable REGISTERED;
    public static javax.swing.JLabel background;
    public static javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
