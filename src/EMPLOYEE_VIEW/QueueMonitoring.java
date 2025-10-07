/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package EMPLOYEE_VIEW;

import CLIENT_VIEW.CLASS_PANES;
import static EMPLOYEE_VIEW.QueueManagement.show_only;
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
import javax.swing.table.JTableHeader;

/**
 *
 * @author reyes
 */
public class QueueMonitoring extends javax.swing.JPanel {
    public int hoveredRow = -1;
    public static  String deter;
    private CLIENT_VIEW.Frame frame;
    public QueueMonitoring(CLIENT_VIEW.Frame frame) {
        initComponents();
         this.frame = frame;
        update();
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
        deter = QueueManagement.show_only;
        System.out.print(deter);
    }
    
    public void update(){
        new javax.swing.Timer(0000, e -> {
        //update tables
        styleTable(PENDING);
        styleTable(ON_HOLD);
        styleTable(COMPLETED);
        Update_Table(PENDING, ON_HOLD, COMPLETED, deter);
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
  public void Update_Table(JTable pending, JTable On_hold, JTable Completed, String deter) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            // 1️⃣ Pending Table
            DefaultTableModel pendingModel = new DefaultTableModel();
            pendingModel.addColumn("Queue No.");
            pendingModel.addColumn("Name");
            pendingModel.addColumn(" Transaction Type");

            String sqlPending = "SELECT QueNum, Username, Transaction_Type FROM quenum WHERE Transaction_Type = ?  UNION ALL  SELECT Que_Num, Username, Transaction_Type FROM onhold WHERE Transaction_Type = ? ORDER BY Transaction_Type ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sqlPending)){
                    ps.setString(1, deter);
                    ps.setString(2, deter);
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

            String sqlOnHold = "SELECT Que_Num, Username, Transaction_Type FROM cancelled WHERE Transaction_Type = ? ORDER BY Date,Time ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sqlOnHold)){
                    ps.setString(1, deter);
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

            String sqlCompleted = "SELECT Que_Num, Username, Transaction_Type FROM completed WHERE Transaction_Type = ? ORDER BY Date,Time ASC";
             try (java.sql.PreparedStatement ps = con.prepareStatement(sqlCompleted)){
                    ps.setString(1, deter);
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
        CLASS_PANES.showMessageBox("Error loading tables: " + e.getMessage());
    }
}


    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        settled = new javax.swing.JLabel();
        active = new javax.swing.JLabel();
        skipped = new javax.swing.JLabel();
        BACK = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ON_HOLD = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        COMPLETED = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        PENDING = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(0, 273, 318, 90);

        settled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/SETTLED.png"))); // NOI18N
        add(settled);
        settled.setBounds(774, 263, 380, 43);

        active.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/ACTIVE.png"))); // NOI18N
        add(active);
        active.setBounds(783, 263, 370, 43);

        skipped.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/SKIP.png"))); // NOI18N
        add(skipped);
        skipped.setBounds(772, 263, 390, 43);

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

        jComboBox1.setFont(new java.awt.Font("Nirmala UI", 1, 15)); // NOI18N
        jComboBox1.setForeground(new Color(0x041a46));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING", "SKIPPED", "COMPLETED" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(410, 258, 173, 43);

        ON_HOLD.setBackground(null);
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
        ON_HOLD.setRowHeight(40);
        ON_HOLD.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ON_HOLDMouseMoved(evt);
            }
        });
        ON_HOLD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ON_HOLDMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(ON_HOLD);

        add(jScrollPane2);
        jScrollPane2.setBounds(418, 322, 775, 303);

        COMPLETED.setBackground(null);
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
        COMPLETED.setRowHeight(40);
        COMPLETED.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                COMPLETEDMouseMoved(evt);
            }
        });
        COMPLETED.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                COMPLETEDMouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(COMPLETED);

        add(jScrollPane3);
        jScrollPane3.setBounds(418, 322, 775, 303);

        PENDING.setBackground(null);
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
        PENDING.setRowHeight(40);
        PENDING.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PENDINGMouseMoved(evt);
            }
        });
        PENDING.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PENDINGMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(PENDING);

        add(jScrollPane1);
        jScrollPane1.setBounds(418, 322, 775, 303);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_QMON.gif"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frame.setContentPane(new QueueManagement(frame));
        frame.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed

    private void PENDINGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PENDINGMouseExited
        // TODO add your handling code here:
         // Reset hovered row when mouse leaves the table
        hoveredRow = -1;
        PENDING.repaint(); // repaint table to remove hover color
        
    }//GEN-LAST:event_PENDINGMouseExited

    private void PENDINGMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PENDINGMouseMoved

        int row = PENDING .rowAtPoint(evt.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    PENDING.repaint(); // update hover
                }

    }//GEN-LAST:event_PENDINGMouseMoved

    private void ON_HOLDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ON_HOLDMouseExited
        // Reset hovered row when mouse leaves the table
        hoveredRow = -1;
        ON_HOLD.repaint(); // repaint table to remove hover color
    }//GEN-LAST:event_ON_HOLDMouseExited

    private void ON_HOLDMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ON_HOLDMouseMoved
       int row = ON_HOLD .rowAtPoint(evt.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    ON_HOLD.repaint(); // update hover
                }
    }//GEN-LAST:event_ON_HOLDMouseMoved

    private void COMPLETEDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMPLETEDMouseExited
        hoveredRow = -1;
        COMPLETED.repaint(); // repaint table to remove hover color
        
    }//GEN-LAST:event_COMPLETEDMouseExited

    private void COMPLETEDMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMPLETEDMouseMoved
          int row = COMPLETED .rowAtPoint(evt.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    COMPLETED.repaint(); // update hover
                }
    }//GEN-LAST:event_COMPLETEDMouseMoved

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
    private javax.swing.JTable ON_HOLD;
    private javax.swing.JTable PENDING;
    private javax.swing.JLabel active;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel settled;
    private javax.swing.JLabel skipped;
    // End of variables declaration//GEN-END:variables
}
