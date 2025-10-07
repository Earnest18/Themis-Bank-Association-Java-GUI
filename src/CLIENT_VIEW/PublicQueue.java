/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RepaintManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author reyes
 */
public class PublicQueue extends javax.swing.JPanel {

    private CLIENT_VIEW.FRAME_PUBQ frame;
    public PublicQueue(CLIENT_VIEW.FRAME_PUBQ frame) {
        initComponents();
       RepaintManager.currentManager(frame).setDoubleBufferingEnabled(true);

        
        this.frame = frame;
        setDoubleBuffered(true);  
       
            avg();
            loadQueueNumbers(WITHDRAW, "Withdraw");
            loadQueueNumbers(DEPOSIT, "Deposit");
            loadQueueNumbers(LOAN, "Loan");
            loadQueueNumbers(INQUIRY, "Inquire");
            new javax.swing.Timer(2000, e -> loadQueueNumbers(WITHDRAW, "Withdraw")).start();
            new javax.swing.Timer(2000, e -> loadQueueNumbers(DEPOSIT, "Deposit")).start();
            new javax.swing.Timer(2000, e -> loadQueueNumbers(LOAN, "Loan")).start();
            new javax.swing.Timer(2000, e -> loadQueueNumbers(INQUIRY, "Inquire")).start();
             running_time();
            new javax.swing.Timer(1000, e -> avg()).start();
                update();
    }
    
     
    private void running_time() {
        Timer timer = new Timer(1000, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat sdf2 = new SimpleDateFormat("M/dd/YYYY         EEEE");
                SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a"); 
                System.out.println(sdf.format(date));
               time.setText(sdf.format(date));
               dates.setText(sdf2.format(date).toUpperCase());
            }
        });
        timer.start();
    }

    public void avg(){
    
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            String avgSql = "SELECT AVG(duration_ms) AS avg_duration FROM avg_servetime";

            try (java.sql.PreparedStatement pst = con.prepareStatement(avgSql);
                 java.sql.ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    double avgDuration = rs.getDouble("avg_duration"); // use double for AVG
                    long minutes = (long) (avgDuration / 1000 / 60);
                    long seconds = (long) ((avgDuration / 1000) % 60);

                     String avgFormatted = String.format("%02dmins.%02d secs.", minutes, seconds);
                     AVG_TIME.setText( "" + avgFormatted);
                     System.out.println("Average Service Time: " + avgFormatted);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public void update() {
    new javax.swing.Timer(1000, e -> { // update every 1 second
        if (EMPLOYEE_VIEW.QueueManagement.t != null) {
            WITHDRAW_OG.setText(EMPLOYEE_VIEW.QueueManagement.t.getWithdraw());
            DEPOSIT_OG.setText(EMPLOYEE_VIEW.QueueManagement.t.getDeposit());
            LOAN_OG.setText(EMPLOYEE_VIEW.QueueManagement.t.getLoan());
            INQUIRY_OG.setText(EMPLOYEE_VIEW.QueueManagement.t.getInquire());
         
        }else if (ADMIN_VIEW.QueueManagement.t != null) {
            WITHDRAW_OG.setText(ADMIN_VIEW.QueueManagement.t.getWithdraw());
            DEPOSIT_OG.setText(ADMIN_VIEW.QueueManagement.t.getDeposit());
            LOAN_OG.setText(ADMIN_VIEW.QueueManagement.t.getLoan());
            INQUIRY_OG.setText(ADMIN_VIEW.QueueManagement.t.getInquire());
            
           
        }
    }).start();
}
    
    public static void styleTables(JTable tables) {
    // Transparent table & header
    tables.setOpaque(false);
    tables.setFillsViewportHeight(true); 
    tables.getTableHeader().setBorder(null);
    tables.getTableHeader().setOpaque(false);
    tables.getTableHeader().setVisible(false);
    tables.getTableHeader().setFont(new Font("Nimala UI", Font.BOLD, 16));
    tables.getTableHeader().setForeground(new Color(0x03021d));
    tables.getTableHeader().setPreferredSize(new Dimension(tables.getTableHeader().getWidth(),0));
    tables.getTableHeader().setBackground(new Color(0xebb923));
    
    
    tables.setShowHorizontalLines(false);
    tables.setShowVerticalLines(false);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // horizontal center
    centerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // vertical center
    centerRenderer.setOpaque(false);

    // Apply renderer to all columns AFTER model is set
    for (int i = 0; i < tables.getColumnModel().getColumnCount(); i++) {
        tables.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    // Scroll pane transparency
    Container parent = tables.getParent();
    if (parent instanceof JViewport) {
        JViewport viewport = (JViewport) parent;
        viewport.setOpaque(false);

        
        if (viewport.getParent() instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) viewport.getParent();

            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(null);

            // Transparent scrollbars
            scrollPane.getVerticalScrollBar().setOpaque(false);
            scrollPane.getHorizontalScrollBar().setOpaque(false);

            // Hide scrollbars
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
    }
}
    

    
// Load all queue numbers for a specific transaction type into a JTable
public void loadQueueNumbers(JTable table, String transactionType) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Queue Number");

    try {
        // 1. Load Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Connect to DB
        java.sql.Connection con = java.sql.DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tbaqs", "root", ""
        );

        // 3. SQL query (make sure your column names are correct!)
        String sql = "SELECT QueNum FROM quenum WHERE Transaction_Type = ? ORDER BY id ASC";
        java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, transactionType);

        // 4. Execute query
        java.sql.ResultSet rs = pstmt.executeQuery();

        // 5. Fill JTable
        while (rs.next()) {
            model.addRow(new Object[]{rs.getString("QueNum")});
        }
        table.setModel(model);
        styleTables(table);
        
        // 6. Close resources
        rs.close();
        pstmt.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dates = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        AVG_TIME = new javax.swing.JLabel();
        DEPOSIT_OG = new javax.swing.JLabel();
        LOAN_OG = new javax.swing.JLabel();
        INQUIRY_OG = new javax.swing.JLabel();
        WITHDRAW_OG = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        INQUIRY = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        DEPOSIT = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        LOAN = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        WITHDRAW = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setLayout(null);

        dates.setBackground(null);
        dates.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        dates.setForeground(new Color(0x041a46));
        dates.setBorder(null);
        add(dates);
        dates.setBounds(95, 154, 300, 30);

        time.setBackground(null);
        time.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        time.setForeground(new Color(0x041a46));
        time.setBorder(null);
        add(time);
        time.setBounds(95, 178, 290, 30);

        AVG_TIME.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        AVG_TIME.setForeground(new Color(0x080525)
        );
        AVG_TIME.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(AVG_TIME);
        AVG_TIME.setBounds(70, 310, 300, 70);

        DEPOSIT_OG.setFont(new java.awt.Font("Nirmala UI", 1, 65)); // NOI18N
        DEPOSIT_OG.setForeground(new Color(0x080525)
        );
        DEPOSIT_OG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(DEPOSIT_OG);
        DEPOSIT_OG.setBounds(880, 130, 250, 180);

        LOAN_OG.setFont(new java.awt.Font("Nirmala UI", 1, 65)); // NOI18N
        LOAN_OG.setForeground(new Color(0x080525)
        );
        LOAN_OG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(LOAN_OG);
        LOAN_OG.setBounds(440, 450, 250, 180);

        INQUIRY_OG.setFont(new java.awt.Font("Nirmala UI", 1, 65)); // NOI18N
        INQUIRY_OG.setForeground(new Color(0x080525)
        );
        INQUIRY_OG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(INQUIRY_OG);
        INQUIRY_OG.setBounds(880, 450, 250, 180);

        WITHDRAW_OG.setFont(new java.awt.Font("Nirmala UI", 1, 65)); // NOI18N
        WITHDRAW_OG.setForeground(new Color(0x080525)
        );
        WITHDRAW_OG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(WITHDRAW_OG);
        WITHDRAW_OG.setBounds(440, 130, 250, 180);

        INQUIRY.setBackground(null);
        INQUIRY.setFont(new java.awt.Font("Nirmala UI", 1, 19)); // NOI18N
        INQUIRY.setForeground(new Color(0x080525));
        INQUIRY.setModel(new javax.swing.table.DefaultTableModel(
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
        INQUIRY.setRowHeight(40);
        jScrollPane4.setViewportView(INQUIRY);

        add(jScrollPane4);
        jScrollPane4.setBounds(1140, 419, 160, 220);

        DEPOSIT.setBackground(null);
        DEPOSIT.setFont(new java.awt.Font("Nirmala UI", 1, 19)); // NOI18N
        DEPOSIT.setForeground(new Color(0x080525));
        DEPOSIT.setModel(new javax.swing.table.DefaultTableModel(
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
        DEPOSIT.setRowHeight(40);
        jScrollPane3.setViewportView(DEPOSIT);

        add(jScrollPane3);
        jScrollPane3.setBounds(1140, 98, 160, 220);

        LOAN.setBackground(null);
        LOAN.setFont(new java.awt.Font("Nirmala UI", 1, 19)); // NOI18N
        LOAN.setForeground(new Color(0x080525));
        LOAN.setModel(new javax.swing.table.DefaultTableModel(
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
        LOAN.setRowHeight(40);
        jScrollPane2.setViewportView(LOAN);

        add(jScrollPane2);
        jScrollPane2.setBounds(700, 419, 170, 220);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        WITHDRAW.setBackground(null);
        WITHDRAW.setFont(new java.awt.Font("Nirmala UI", 1, 19)); // NOI18N
        WITHDRAW.setForeground(new Color(0x080525));
        WITHDRAW.setModel(new javax.swing.table.DefaultTableModel(
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
        WITHDRAW.setRowHeight(40);
        jScrollPane1.setViewportView(WITHDRAW);

        add(jScrollPane1);
        jScrollPane1.setBounds(700, 98, 170, 220);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/QUEUEING.gif"))); // NOI18N
        background.setText("jLabel1");
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AVG_TIME;
    private javax.swing.JTable DEPOSIT;
    private javax.swing.JLabel DEPOSIT_OG;
    private javax.swing.JTable INQUIRY;
    private javax.swing.JLabel INQUIRY_OG;
    private javax.swing.JTable LOAN;
    private javax.swing.JLabel LOAN_OG;
    private javax.swing.JTable WITHDRAW;
    private javax.swing.JLabel WITHDRAW_OG;
    private javax.swing.JLabel background;
    private javax.swing.JLabel dates;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
