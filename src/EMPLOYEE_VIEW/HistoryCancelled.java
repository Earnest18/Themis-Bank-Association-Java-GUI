/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package EMPLOYEE_VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public class HistoryCancelled extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public HistoryCancelled(CLIENT_VIEW.Frame frame) {
        initComponents();
        update();
        this.frame = frame;
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
    }
       
    public void update(){
        
        new javax.swing.Timer(0000, e -> {
            TABLE(CANCELLED);
        }).start();
    }
   
public void TABLE(JTable cancelled) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Account Number");
    model.addColumn("Username");
    model.addColumn("Mobile No.");
    model.addColumn("Queue Number");
    model.addColumn("Transaction Type");
    model.addColumn("Amount");
    model.addColumn("Date");
    model.addColumn("Time");

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            String sql = "SELECT Acc_number,Username, MobileNum, Que_Num, Transaction_Type, Amount, Date, Time FROM cancelled ORDER BY Transaction_Type AND Date  ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql);
                 java.sql.ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String acc = rs.getString("Acc_number");
                    
                    String username = rs.getString("Username");
                    
                    String mobilenum = rs.getString("MobileNum");
                    String quenum = rs.getString("Que_Num");
                    String amount = rs.getString("Amount");
                    String am = "";
                    if(amount.equals("-----") || amount.equals("0")){
                        am ="----";
                    }else{
                        am = rs.getString("Amount") + " Php";
                    
                    }
                    String transactionType = rs.getString("Transaction_Type"); 
                    String Date= rs.getString("Date");
                    String Time = rs.getString("Time");

                    model.addRow(new Object[]{acc,username, mobilenum, quenum, transactionType, am, Date, Time});
                }
            }
        }

        cancelled.setModel(model);

        cancelled.setOpaque(false);
        cancelled.setShowGrid(false);
        cancelled.getTableHeader().setBorder(null);
        JTableHeader header = cancelled.getTableHeader();
        header.setFont(new Font("Nimala UI", Font.BOLD, 12));

        header.setForeground(new Color(0x080525));
        header.setBackground(new Color(0xebb923));

        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false);

        for (int i = 0; i < cancelled.getColumnCount(); i++) {
            cancelled.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading onhold table: " + e.getMessage());
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HISTORY_COMPLETED = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CANCELLED = new javax.swing.JTable();
        BACK = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        HISTORY_COMPLETED.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        HISTORY_COMPLETED.setBorderPainted(false);
        HISTORY_COMPLETED.setContentAreaFilled(false);
        HISTORY_COMPLETED.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        HISTORY_COMPLETED.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        HISTORY_COMPLETED.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HISTORY_COMPLETEDActionPerformed(evt);
            }
        });
        add(HISTORY_COMPLETED);
        HISTORY_COMPLETED.setBounds(0, 275, 318, 90);

        CANCELLED.setModel(new javax.swing.table.DefaultTableModel(
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
        CANCELLED.setRowHeight(30);
        jScrollPane1.setViewportView(CANCELLED);

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_HCAN.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void HISTORY_COMPLETEDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HISTORY_COMPLETEDActionPerformed
       frame.setContentPane(new HistoryCompleted(frame));
       frame.revalidate();
    }//GEN-LAST:event_HISTORY_COMPLETEDActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JTable CANCELLED;
    private javax.swing.JButton HISTORY_COMPLETED;
    private javax.swing.JLabel background;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
