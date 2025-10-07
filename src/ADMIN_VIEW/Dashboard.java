/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import CLIENT_VIEW.CLASS_PANES;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author reyes
 */
public class Dashboard extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public static String DATE = "";
    public static String TIME = "";
    public static String Employee = "";
    public static int nextNo = 1;
    public static int x;

    public Dashboard(CLIENT_VIEW.Frame frame) {
        initComponents();
        running_time();

        this.frame = frame;
        analytics();
        update();
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setBorder(null);
    }

    private void running_time() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat sdf2 = new SimpleDateFormat("M/dd/YYYY");
                SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
                System.out.println(sdf.format(date));
                time.setText(sdf.format(date));
                dates.setText(sdf2.format(date));
            }
        });
        timer.start();
    }

    public void update() {
        
            TABLE(C_SUPPORT);
            LOG(LOGS);
      

    }

    private void analytics() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tbaqs", "root", "")) {
                String com_c = "SELECT COUNT(*) AS total FROM completed";
                int complete = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(com_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            complete = rs.getInt("total");

                            completed.setText("" + complete);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }
                String can_c = "SELECT COUNT(*) AS total FROM cancelled";
                int cancel = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(can_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            cancel = rs.getInt("total");

                            cancelled.setText("" + cancel);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }
                String ah_c = "SELECT COUNT(*) AS total FROM new_registered_user";
                int ahc = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(ah_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            ahc = rs.getInt("total");

                            total_ac.setText("" + ahc);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }
                String em_c = "SELECT COUNT(*) AS total FROM new_registered_employee";
                int emc = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(em_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            emc = rs.getInt("total");

                            total_em.setText("" + emc);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }
                String wd_c = "SELECT (SELECT COUNT(*) FROM quenum WHERE Transaction_Type = 'Withdraw') + (SELECT COUNT(*) FROM onhold WHERE Transaction_Type = 'Withdraw')   AS total";
                int wdc = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(wd_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            wdc = rs.getInt("total");

                            active_w.setText("" + wdc);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }
                String dp_c = "SELECT (SELECT COUNT(*) FROM quenum WHERE Transaction_Type = 'Deposit') + (SELECT COUNT(*) FROM onhold WHERE Transaction_Type = 'Deposit')   AS total";
                int dpc = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(dp_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            dpc = rs.getInt("total");

                            active_d.setText("" + dpc);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }
                String ls_c = "SELECT (SELECT COUNT(*) FROM quenum WHERE Transaction_Type = 'Loan') + (SELECT COUNT(*) FROM onhold WHERE Transaction_Type = 'Loan')   AS total";
                int lsc = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(ls_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            lsc = rs.getInt("total");

                            active_l.setText("" + lsc);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }
                String iq_c = "SELECT (SELECT COUNT(*) FROM quenum WHERE Transaction_Type = 'Inquire') + (SELECT COUNT(*) FROM onhold WHERE Transaction_Type = 'Inquire')   AS total";
                int iqc = 0;
                try (java.sql.PreparedStatement psCount = con.prepareStatement(iq_c)) {

                    try (java.sql.ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            iqc = rs.getInt("total");

                            active_i.setText("" + iqc);

                        } else {
                           CLASS_PANES.showMessageBox("no record");

                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void TABLE(JTable completed) {
        nextNo =1;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Email");
        model.addColumn("Inquiry");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                String sql = "SELECT * FROM customer_service ";
                try (java.sql.PreparedStatement ps = con.prepareStatement(sql); java.sql.ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                       
                        String mail = rs.getString("Gmail");
                        String feedback = rs.getString("feedbacks");

                        model.addRow(new Object[]{nextNo,mail, feedback});
                        nextNo++;
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
            completed.getColumnModel().getColumn(0).setPreferredWidth(30);
            completed.getColumnModel().getColumn(1).setPreferredWidth(130);
            completed.getColumnModel().getColumn(2).setPreferredWidth(380);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hello");
           CLASS_PANES.showMessageBox("Error loading onhold table: " + e.getMessage());
        }
    }

    public void LOG(JTable log) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Employee");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                String sql = "SELECT Employee_ID, Date, Time, logging FROM logs ORDER BY Date AND Time ASC";
                try (java.sql.PreparedStatement ps = con.prepareStatement(sql); java.sql.ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String emp = rs.getString("Employee_ID");
                        String date = rs.getString("Date");
                        String time = rs.getString("Time");
                        String logg = rs.getString("logging");
                        String statement = "Employee " + emp + "  " + logg + " on " + date + " at " + time + ".";
                        model.addRow(new Object[]{statement});
                    }
                }
            }

            log.setModel(model);

            log.setOpaque(false);
            log.setShowGrid(false);
            log.getTableHeader().setBorder(null);
            JTableHeader header = log.getTableHeader();
            header.setFont(new Font("Nimala UI", Font.BOLD, 12));

            header.setForeground(new Color(0x080525));
            header.setBackground(new Color(0xebb923));
            header.setVisible(false);

            header.setPreferredSize(new Dimension(header.getWidth(), 0));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.LEFT);
            centerRenderer.setVerticalAlignment(JLabel.CENTER);
            centerRenderer.setOpaque(false);

            for (int i = 0; i < log.getColumnCount(); i++) {
                log.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            log.getColumnModel().getColumn(0).setPreferredWidth(350);

            JScrollPane scroll = new JScrollPane();
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            jScrollPane2.add(scroll);

        } catch (Exception e) {
            e.printStackTrace();
           CLASS_PANES.showMessageBox("Error loading onhold table: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        active_w = new javax.swing.JLabel();
        active_d = new javax.swing.JLabel();
        active_l = new javax.swing.JLabel();
        active_i = new javax.swing.JLabel();
        total_em = new javax.swing.JLabel();
        total_ac = new javax.swing.JLabel();
        cancelled = new javax.swing.JLabel();
        completed = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        dates = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LOGS = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        C_SUPPORT = new javax.swing.JTable();
        QUEUING = new javax.swing.JButton();
        HISTORY1 = new javax.swing.JButton();
        FAQS2 = new javax.swing.JButton();
        LOGOUT = new javax.swing.JButton();
        ACC_MANAGEMENT = new javax.swing.JButton();
        CLEAR = new javax.swing.JButton();
        ADD_EM = new javax.swing.JButton();
        ABT = new javax.swing.JButton();
        FAQS = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        active_w.setBackground(null);
        active_w.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        active_w.setForeground(new Color(0x041a46));
        active_w.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        active_w.setBorder(null);
        add(active_w);
        active_w.setBounds(1100, 286, 50, 40);

        active_d.setBackground(null);
        active_d.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        active_d.setForeground(new Color(0x041a46));
        active_d.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        active_d.setBorder(null);
        add(active_d);
        active_d.setBounds(1200, 286, 50, 40);

        active_l.setBackground(null);
        active_l.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        active_l.setForeground(new Color(0x041a46));
        active_l.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        active_l.setBorder(null);
        add(active_l);
        active_l.setBounds(1100, 350, 50, 40);

        active_i.setBackground(null);
        active_i.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        active_i.setForeground(new Color(0x041a46));
        active_i.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        active_i.setBorder(null);
        add(active_i);
        active_i.setBounds(1200, 350, 50, 40);

        total_em.setBackground(null);
        total_em.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        total_em.setForeground(new Color(0x041a46));
        total_em.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_em.setBorder(null);
        add(total_em);
        total_em.setBounds(800, 275, 130, 40);

        total_ac.setBackground(null);
        total_ac.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        total_ac.setForeground(new Color(0x041a46));
        total_ac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_ac.setBorder(null);
        add(total_ac);
        total_ac.setBounds(800, 350, 130, 40);

        cancelled.setBackground(null);
        cancelled.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        cancelled.setForeground(new Color(0x041a46));
        cancelled.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancelled.setBorder(null);
        add(cancelled);
        cancelled.setBounds(485, 350, 130, 40);

        completed.setBackground(null);
        completed.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        completed.setForeground(new Color(0x041a46));
        completed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completed.setBorder(null);
        add(completed);
        completed.setBounds(485, 287, 130, 40);

        time.setBackground(null);
        time.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        time.setForeground(new Color(0x041a46));
        time.setBorder(null);
        add(time);
        time.setBounds(1130, 200, 120, 20);

        dates.setBackground(null);
        dates.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        dates.setForeground(new Color(0x041a46));
        dates.setBorder(null);
        add(dates);
        dates.setBounds(1130, 175, 120, 20);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        LOGS.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        LOGS.setModel(new javax.swing.table.DefaultTableModel(
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
        LOGS.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        LOGS.setRowHeight(30);
        LOGS.setShowGrid(false);
        LOGS.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(LOGS);

        add(jScrollPane2);
        jScrollPane2.setBounds(975, 457, 300, 165);

        C_SUPPORT.setModel(new javax.swing.table.DefaultTableModel(
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
        C_SUPPORT.setRowHeight(30);
        C_SUPPORT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C_SUPPORTMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(C_SUPPORT);

        add(jScrollPane1);
        jScrollPane1.setBounds(349, 457, 613, 136);

        QUEUING.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        QUEUING.setBorderPainted(false);
        QUEUING.setContentAreaFilled(false);
        QUEUING.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        QUEUING.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        QUEUING.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QUEUINGActionPerformed(evt);
            }
        });
        add(QUEUING);
        QUEUING.setBounds(-10, 280, 318, 90);

        HISTORY1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        HISTORY1.setBorderPainted(false);
        HISTORY1.setContentAreaFilled(false);
        HISTORY1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        HISTORY1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        HISTORY1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HISTORY1ActionPerformed(evt);
            }
        });
        add(HISTORY1);
        HISTORY1.setBounds(0, 365, 318, 90);

        FAQS2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/announce_u.png"))); // NOI18N
        FAQS2.setBorderPainted(false);
        FAQS2.setContentAreaFilled(false);
        FAQS2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/announce_c.png"))); // NOI18N
        FAQS2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/announce_c.png"))); // NOI18N
        FAQS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FAQS2ActionPerformed(evt);
            }
        });
        add(FAQS2);
        FAQS2.setBounds(831, 594, 120, 28);

        LOGOUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        LOGOUT.setBorderPainted(false);
        LOGOUT.setContentAreaFilled(false);
        LOGOUT.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_back.png"))); // NOI18N
        LOGOUT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_back.png"))); // NOI18N
        LOGOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGOUTActionPerformed(evt);
            }
        });
        add(LOGOUT);
        LOGOUT.setBounds(0, 548, 318, 90);

        ACC_MANAGEMENT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        ACC_MANAGEMENT.setBorderPainted(false);
        ACC_MANAGEMENT.setContentAreaFilled(false);
        ACC_MANAGEMENT.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        ACC_MANAGEMENT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        ACC_MANAGEMENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACC_MANAGEMENTActionPerformed(evt);
            }
        });
        add(ACC_MANAGEMENT);
        ACC_MANAGEMENT.setBounds(0, 457, 318, 90);

        CLEAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/clear_u.png"))); // NOI18N
        CLEAR.setBorderPainted(false);
        CLEAR.setContentAreaFilled(false);
        CLEAR.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/clear_c.png"))); // NOI18N
        CLEAR.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/clear_c.png"))); // NOI18N
        CLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARActionPerformed(evt);
            }
        });
        add(CLEAR);
        CLEAR.setBounds(740, 594, 80, 30);

        ADD_EM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/FINAL BANK QUEUE (13).png"))); // NOI18N
        ADD_EM.setBorderPainted(false);
        ADD_EM.setContentAreaFilled(false);
        ADD_EM.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/FINAL BANK QUEUE (14).png"))); // NOI18N
        ADD_EM.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/FINAL BANK QUEUE (14).png"))); // NOI18N
        ADD_EM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADD_EMActionPerformed(evt);
            }
        });
        add(ADD_EM);
        ADD_EM.setBounds(1053, 114, 230, 40);

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
        ABT.setBounds(770, 116, 144, 35);

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
        FAQS.setBounds(937, 117, 101, 35);

        background.setBackground(null);
        background.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        background.setForeground(new Color(0x041a46));
        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AD_DASH2.png"))); // NOI18N
        background.setBorder(null);
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void QUEUINGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QUEUINGActionPerformed
        frame.setContentPane(new QueueManagement(frame));
        frame.revalidate();
    }//GEN-LAST:event_QUEUINGActionPerformed

    private void HISTORY1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HISTORY1ActionPerformed
        frame.setContentPane(new HistoryCompleted(frame));
        frame.revalidate();
    }//GEN-LAST:event_HISTORY1ActionPerformed

    private void ACC_MANAGEMENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACC_MANAGEMENTActionPerformed
        frame.setContentPane(new AccountManagement(frame));
        frame.revalidate();
    }//GEN-LAST:event_ACC_MANAGEMENTActionPerformed

    private void ABTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ABTActionPerformed

        frame.revalidate();
    }//GEN-LAST:event_ABTActionPerformed

    private void FAQSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FAQSActionPerformed

        frame.revalidate();
    }//GEN-LAST:event_FAQSActionPerformed

    private void LOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGOUTActionPerformed
        frame.setContentPane(new CLIENT_VIEW.Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_LOGOUTActionPerformed

    private void ADD_EMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADD_EMActionPerformed
        frame.setContentPane(new RegisterEmployee(frame));
        frame.revalidate();
    }//GEN-LAST:event_ADD_EMActionPerformed

    private void CLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARActionPerformed

        int choice =CLASS_PANES.showConfirmBox("Are you sure you want to remove all the data inside Customer Support?");

        if (choice == JOptionPane.YES_OPTION) {
            
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                          
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                       CLASS_PANES.showMessageBox("Error deleting records from completed");
                    }
        }
    }//GEN-LAST:event_CLEARActionPerformed

    private void FAQS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FAQS2ActionPerformed
           frame.setContentPane(new Announcement(frame));
               frame.revalidate();
    }//GEN-LAST:event_FAQS2ActionPerformed
public static String EMAIL;
public static String MESSAGE;
    private void C_SUPPORTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C_SUPPORTMousePressed
        int x = C_SUPPORT.getSelectedRow();

        if (x != -1) {
             EMAIL = C_SUPPORT.getValueAt(x, 1).toString();
            MESSAGE = C_SUPPORT.getValueAt(x, 2).toString();
            String[] options = {"Reply", "Cancel"};
            int choice = CLASS_PANES.SelectionBox(options, "Do you want to reply to " + EMAIL + "?");
            if (choice == 0) {
              
               frame.setContentPane(new Announcement2(frame));
               frame.revalidate();
                
               
            } else if (choice == 1) {
                
            }

        }

    }//GEN-LAST:event_C_SUPPORTMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ABT;
    private javax.swing.JButton ACC_MANAGEMENT;
    private javax.swing.JButton ADD_EM;
    private javax.swing.JButton CLEAR;
    public static javax.swing.JTable C_SUPPORT;
    private javax.swing.JButton FAQS;
    private javax.swing.JButton FAQS2;
    private javax.swing.JButton HISTORY1;
    private javax.swing.JButton LOGOUT;
    public static javax.swing.JTable LOGS;
    private javax.swing.JButton QUEUING;
    private javax.swing.JLabel active_d;
    private javax.swing.JLabel active_i;
    private javax.swing.JLabel active_l;
    private javax.swing.JLabel active_w;
    private javax.swing.JLabel background;
    private javax.swing.JLabel cancelled;
    private javax.swing.JLabel completed;
    private javax.swing.JLabel dates;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel time;
    private javax.swing.JLabel total_ac;
    private javax.swing.JLabel total_em;
    // End of variables declaration//GEN-END:variables
}
