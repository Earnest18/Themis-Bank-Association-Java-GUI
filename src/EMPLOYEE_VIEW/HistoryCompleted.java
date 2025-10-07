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
public class HistoryCompleted extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public HistoryCompleted(CLIENT_VIEW.Frame frame) {
        initComponents();
        update();
        this.frame = frame;
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
    }

    
    public void update(){
        
        new javax.swing.Timer(0000, e -> {
            TABLE(COMPLETED);
        }).start();
    }
   
public void TABLE(JTable completed) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Reference Number");
    model.addColumn("Account Number");
    model.addColumn("Username");
    model.addColumn("Que Number");
    model.addColumn(" Transaction Type");
    model.addColumn("Amount");
    model.addColumn("Date");
    model.addColumn("Time");

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            String sql = "SELECT * FROM completed ORDER BY Transaction_Type AND Date ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql);
                 java.sql.ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String ref = rs.getString("reference_no");
                    String acc = rs.getString("Acc_number");
                    String username = rs.getString("Username");
                    String quenum = rs.getString("Que_Num");
                 
                    String transactionType = rs.getString("Transaction_Type");
                    String amount = rs.getString("Amount"); 
                    String date = rs.getString("Date"); 
                     String time = rs.getString("Time"); 

                    model.addRow(new Object[]{ref, acc, username, quenum , transactionType, amount, date, time});
                }
            }
        }

        completed.setModel(model);

              
completed.setModel(model);
completed.setOpaque(false);
completed.setShowGrid(false);
completed.getTableHeader().setBorder(null);
JTableHeader header = completed.getTableHeader();
header.setFont(new Font("Nimala UI", Font.BOLD, 12));

header.setForeground(new Color(0x080525));
header.setBackground(new Color(0xebb923));

header.setPreferredSize(new Dimension(header.getWidth(), 30));

DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
centerRenderer.setVerticalAlignment(JLabel.CENTER);
centerRenderer.setOpaque(false);

for (int i = 0; i < completed.getColumnCount(); i++) {
    completed.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
completed.getColumnModel().getColumn(0).setPreferredWidth(140);  
completed.getColumnModel().getColumn(1).setPreferredWidth(200); 
completed.getColumnModel().getColumn(2).setPreferredWidth(160); 
completed.getColumnModel().getColumn(3).setPreferredWidth(80);   
completed.getColumnModel().getColumn(4).setPreferredWidth(130);  
completed.getColumnModel().getColumn(5).setPreferredWidth(80);  
completed.getColumnModel().getColumn(6).setPreferredWidth(80);  
completed.getColumnModel().getColumn(7).setPreferredWidth(80);  

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading onhold table: " + e.getMessage());
    }
}

   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HISTORY_CANCELLED = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        COMPLETED = new javax.swing.JTable();
        BACK = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        HISTORY_CANCELLED.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        HISTORY_CANCELLED.setBorderPainted(false);
        HISTORY_CANCELLED.setContentAreaFilled(false);
        HISTORY_CANCELLED.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        HISTORY_CANCELLED.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        HISTORY_CANCELLED.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HISTORY_CANCELLEDActionPerformed(evt);
            }
        });
        add(HISTORY_CANCELLED);
        HISTORY_CANCELLED.setBounds(0, 365, 318, 90);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 402));

        COMPLETED.setModel(new javax.swing.table.DefaultTableModel(
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
        COMPLETED.setRowHeight(30);
        jScrollPane1.setViewportView(COMPLETED);

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_HCOM.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void HISTORY_CANCELLEDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HISTORY_CANCELLEDActionPerformed
        frame.setContentPane(new HistoryCancelled(frame));
        frame.revalidate();
    }//GEN-LAST:event_HISTORY_CANCELLEDActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JTable COMPLETED;
    private javax.swing.JButton HISTORY_CANCELLED;
    private javax.swing.JLabel background;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
