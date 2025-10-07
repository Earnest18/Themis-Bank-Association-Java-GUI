/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author reyes
 */
public class QueueMonitoring extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public QueueMonitoring(CLIENT_VIEW.Frame frame) {
        initComponents();
        update();
        this.frame = frame;
        PENDING.setOpaque(false);
        ON_HOLD.setOpaque(false);
        COMPLETED.setOpaque(false);
        PENDING.setVisible(true);
        ON_HOLD.setVisible(false);
        COMPLETED.setVisible(false);
        
        skipped.setVisible(false);
       settled.setVisible(false);
       jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setBorder(null);
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane3.setBorder(null);
    }
    
    public void update(){
        new javax.swing.Timer(0000, e -> {
        //update tables
        styleTable(PENDING);
        styleTable(ON_HOLD);
        styleTable(COMPLETED);
        Update_Table(PENDING, ON_HOLD, COMPLETED);
        }).start();
    }
    
    public static void styleTable(JTable table) {
        // Transparent table
        table.setOpaque(false);
        table.getTableHeader().setOpaque(false);

        // ✅ Show only horizontal lines (row separators)
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
       table.setGridColor(new Color(0x041a46)); // line color (customize as needed)

                //                   
        table.setOpaque(false);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setOpaque(false);
    table.setFillsViewportHeight(true); 
    table.getTableHeader().setBorder(null);
    table.getTableHeader().setOpaque(false);
    table.getTableHeader().setFont(new Font("Nimala UI", Font.BOLD, 14));
    table.getTableHeader().setForeground(Color.WHITE);
    table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(),30));
    table.getTableHeader().setBackground(new Color(0x041a46));
    
    

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

            Container parent = table.getParent();
            if (parent instanceof JViewport) {
                JViewport viewport = (JViewport) parent;
                viewport.setOpaque(false);
                if (viewport.getParent() instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) viewport.getParent();
                    scrollPane.setOpaque(false);
                    scrollPane.setBorder(null);
                    scrollPane.getViewport().setOpaque(false);
                    scrollPane.getVerticalScrollBar().setOpaque(false);
                    scrollPane.getHorizontalScrollBar().setOpaque(false);

                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                }
            }
}
  public void Update_Table(JTable pending, JTable On_hold, JTable Completed) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            // 1️⃣ Pending Table
            DefaultTableModel pendingModel = new DefaultTableModel();
            pendingModel.addColumn("Queue No.");
            pendingModel.addColumn("Name");
            pendingModel.addColumn(" Transaction Type");

            String sqlPending = "SELECT QueNum, Username, Transaction_Type FROM quenum ORDER BY Transaction_Type ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sqlPending)){
                 try(java.sql.ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    pendingModel.addRow(new Object[]{
                            rs.getString("QueNum"),
                            rs.getString("Username"),
                            rs.getString("Transaction_Type")
                    });
                }
            }}

            pending.setModel(pendingModel);
            

            // 2️⃣ On_Hold Table
            DefaultTableModel onHoldModel = new DefaultTableModel();
            onHoldModel.addColumn("Queue");
            onHoldModel.addColumn("Name");
            onHoldModel.addColumn(" Transaction Type");

            String sqlOnHold = "SELECT Que_Num, Username, Transaction_Type FROM cancelled ORDER BY Date AND Time ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sqlOnHold)){
                 try(java.sql.ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    onHoldModel.addRow(new Object[]{
                            rs.getString("Que_Num"),
                            rs.getString("Username"),
                            rs.getString("Transaction_Type")
                    });
                }
            }}
            On_hold.setModel(onHoldModel);
          

            // 3️⃣ Completed Table
            DefaultTableModel completedModel = new DefaultTableModel();
            completedModel.addColumn("Queue No.");
            completedModel.addColumn("Name");
            completedModel.addColumn("Transaction Type");

            String sqlCompleted = "SELECT Que_Num, Username, Transaction_Type FROM completed ORDER BY Date AND Time ASC";
             try (java.sql.PreparedStatement ps = con.prepareStatement(sqlCompleted)){
                 try(java.sql.ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    completedModel.addRow(new Object[]{
                            rs.getString("Que_Num"),
                            rs.getString("Username"),
                            rs.getString("Transaction_Type")
                    });
                }
            }}

            Completed.setModel(completedModel);
          
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading tables: " + e.getMessage());
    }
}


    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MANAGEMENT = new javax.swing.JButton();
        settled = new javax.swing.JLabel();
        skipped = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        active = new javax.swing.JLabel();
        BACK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PENDING = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ON_HOLD = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        COMPLETED = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        MANAGEMENT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        MANAGEMENT.setBorderPainted(false);
        MANAGEMENT.setContentAreaFilled(false);
        MANAGEMENT.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        MANAGEMENT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        MANAGEMENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MANAGEMENTActionPerformed(evt);
            }
        });
        add(MANAGEMENT);
        MANAGEMENT.setBounds(0, 273, 318, 90);

        settled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/SETTLED.png"))); // NOI18N
        add(settled);
        settled.setBounds(774, 263, 380, 43);

        skipped.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/SKIP.png"))); // NOI18N
        add(skipped);
        skipped.setBounds(772, 263, 390, 43);

        jComboBox1.setFont(new java.awt.Font("Nirmala UI", 1, 15)); // NOI18N
        jComboBox1.setForeground(new Color(0x046a41));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVE", "SKIPPED", "COMPLETED" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(410, 258, 173, 43);

        active.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/ACTIVE.png"))); // NOI18N
        add(active);
        active.setBounds(783, 263, 370, 43);

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
        BACK.setBounds(0, 550, 318, 90);

        PENDING.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        PENDING.setRowHeight(30);
        jScrollPane1.setViewportView(PENDING);

        add(jScrollPane1);
        jScrollPane1.setBounds(418, 322, 775, 303);

        ON_HOLD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        ON_HOLD.setRowHeight(30);
        jScrollPane2.setViewportView(ON_HOLD);

        add(jScrollPane2);
        jScrollPane2.setBounds(418, 322, 775, 303);

        COMPLETED.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        COMPLETED.setRowHeight(30);
        jScrollPane3.setViewportView(COMPLETED);

        add(jScrollPane3);
        jScrollPane3.setBounds(418, 322, 775, 303);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_QMON.gif"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void MANAGEMENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MANAGEMENTActionPerformed
        frame.setContentPane(new QueueManagement(frame));
        frame.revalidate();
    }//GEN-LAST:event_MANAGEMENTActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        if(jComboBox1.getSelectedIndex() ==0){
            active.setVisible(true);settled.setVisible(false);skipped.setVisible(false);
            PENDING.setVisible(true);COMPLETED.setVisible(false);ON_HOLD.setVisible(false);

        }
        if(jComboBox1.getSelectedIndex() ==1){
            active.setVisible(false);settled.setVisible(false);skipped.setVisible(true);
            PENDING.setVisible(false);COMPLETED.setVisible(false);ON_HOLD.setVisible(true);

        }
        if(jComboBox1.getSelectedIndex() ==2){
            active.setVisible(false);settled.setVisible(true);skipped.setVisible(false);
            PENDING.setVisible(false);COMPLETED.setVisible(true);ON_HOLD.setVisible(false);

        }
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JTable COMPLETED;
    private javax.swing.JButton MANAGEMENT;
    private javax.swing.JTable ON_HOLD;
    private javax.swing.JTable PENDING;
    private javax.swing.JLabel active;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel settled;
    private javax.swing.JLabel skipped;
    // End of variables declaration//GEN-END:variables
}
