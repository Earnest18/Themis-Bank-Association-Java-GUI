/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import CLIENT_VIEW.CLASS_PANES;
import static EMPLOYEE_VIEW.QueueManagement.styleTable;
import EMPLOYEE_VIEW.CLASS_TRANSACTIONS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author reyes
 */
public class QueueManagement extends javax.swing.JPanel {

    public static CLASS_TRANSACTIONS t;
    public int hoveredRow = -1;
    ;
    public static String ref_no;
    public static String acc_no;
    public static String amount = "";
    public static String DAT = "";
    public static String username = "";
    public static String mobile = "";
    public static String curbal = "";
    public static String new_bal = "";
    public static int id = -1;
    public static boolean isTrue = true;
    public static boolean isFalse = false;
    public static long start_q;
    public static long end_q;
    public static long total_time;

    private final String url = "jdbc:mysql://localhost:3306/tbaqs";
    private final String user = "root";
    private final String pass = "";
    private CLIENT_VIEW.Frame frame;

    public QueueManagement(CLIENT_VIEW.Frame frame) {
        initComponents();
        update();

        this.frame = frame;
        ETIX.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(true);

        determiner(jComboBox2.getSelectedIndex());
        C5.setForeground(new Color(0x080525));
        C6.setForeground(new Color(0x080525));
        C7.setForeground(new Color(0x080525));
        C8.setForeground(new Color(0x080525));

        disable_buttons(isFalse);
      

    }


    public void disable_buttons(boolean choose) {
        S5.setEnabled(choose);
        S6.setEnabled(choose);
        S7.setEnabled(choose);
        S8.setEnabled(choose);
        CO1.setEnabled(choose);
        CO2.setEnabled(choose);
        CO3.setEnabled(choose);
        CO4.setEnabled(choose);

    }

    public void determiner(int counter) {

        if (counter == 0) {
            JOptionPane.showMessageDialog(null, "Please Select a Transaction");
        }
        if (counter == 1) {
            T5.setVisible(true);
            T6.setVisible(false);
            T7.setVisible(false);
            T8.setVisible(false);
            C5.setVisible(true);
            C6.setVisible(false);
            C7.setVisible(false);
            C8.setVisible(false);
            S5.setVisible(true);
            S6.setVisible(false);
            S7.setVisible(false);
            S8.setVisible(false);
            N5.setVisible(true);
            N6.setVisible(false);
            N7.setVisible(false);
            N8.setVisible(false);
            CO1.setVisible(true);
            CO2.setVisible(false);
            CO3.setVisible(false);
            CO4.setVisible(false);

        } else if (counter == 2) {
            T5.setVisible(false);
            T6.setVisible(true);
            T7.setVisible(false);
            T8.setVisible(false);
            C5.setVisible(false);
            C6.setVisible(true);
            C7.setVisible(false);
            C8.setVisible(false);
            S5.setVisible(false);
            S6.setVisible(true);
            S7.setVisible(false);
            S8.setVisible(false);
            N5.setVisible(false);
            N6.setVisible(true);
            N7.setVisible(false);
            N8.setVisible(false);
            CO1.setVisible(false);
            CO2.setVisible(true);
            CO3.setVisible(false);
            CO4.setVisible(false);

        } else if (counter == 3) {
            T5.setVisible(false);
            T6.setVisible(false);
            T7.setVisible(true);
            T8.setVisible(false);
            C5.setVisible(false);
            C6.setVisible(false);
            C7.setVisible(true);
            C8.setVisible(false);
            S5.setVisible(false);
            S6.setVisible(false);
            S7.setVisible(true);
            S8.setVisible(false);
            N5.setVisible(false);
            N6.setVisible(false);
            N7.setVisible(true);
            N8.setVisible(false);
            CO1.setVisible(false);
            CO2.setVisible(false);
            CO3.setVisible(true);
            CO4.setVisible(false);

        } else if (counter == 4) {
            T5.setVisible(false);
            T6.setVisible(false);
            T7.setVisible(false);
            T8.setVisible(true);
            C5.setVisible(false);
            C6.setVisible(false);
            C7.setVisible(false);
            C8.setVisible(true);
            S5.setVisible(false);
            S6.setVisible(false);
            S7.setVisible(false);
            S8.setVisible(true);
            N5.setVisible(false);
            N6.setVisible(false);
            N7.setVisible(false);
            N8.setVisible(true);
            CO1.setVisible(false);
            CO2.setVisible(false);
            CO3.setVisible(false);
            CO4.setVisible(true);

        }

    }

    public void update() {

        new javax.swing.Timer(0000, e -> {
            // load ques from database
            bridge();
            styleTable(T6);
            styleTable(T7);
            styleTable(T8);
            styleTable(T5);
            styleTable(ONHOLD);
            loadQueueNumbers(T5, "Withdraw");
            loadQueueNumbers(T6, "Deposit");
            loadQueueNumbers(T7, "Loan");
            loadQueueNumbers(T8, "Inquire");
            loadOnHoldTable(ONHOLD);

            bridge();
        }).start();
    }

    public void bridge() {
        ArrayList<String> values = new ArrayList<>();
        values.add(C5.getText()); // withdraw
        values.add(C6.getText()); // deposit
        values.add(C7.getText()); // loan
        values.add(C8.getText()); // inquire

        t = new CLASS_TRANSACTIONS(values);
    }

    public static void styleTable(JTable table) {
        // Transparent table & header
        table.setOpaque(false);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0, 0, 0, 0)); // fully transparent

        // Grid lines
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setGridColor(Color.LIGHT_GRAY);

        table.setOpaque(false);
        table.setShowGrid(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Nimala UI", Font.BOLD, 12));

        header.setForeground(new Color(0x080525));
        header.setBackground(new Color(0xebb923));

        header.setPreferredSize(new Dimension(header.getWidth(), 30));

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

                scrollPane.getVerticalScrollBar().setOpaque(false);
                scrollPane.getHorizontalScrollBar().setOpaque(false);

                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            }
        }
    }

    // Load all queue numbers for a specific transaction type
    public void loadQueueNumbers(JTable table, String transactionType) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Queue");
        model.addColumn("Name");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "");

            // Adjusted column names to match quenum table
            String sql = "SELECT QueNum, Username FROM quenum WHERE Transaction_Type = ? ORDER BY id ASC";
            java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, transactionType);
            java.sql.ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String queNum = rs.getString("QueNum");
                String username = rs.getString("Username");
                model.addRow(new Object[]{queNum, username});
            }

            table.setModel(model);
            pstmt.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error: " + e.getMessage());
        }
    }

    public void callNext(JTable table, JLabel lblCurrent, String transactionType) {
        ETIX.setVisible(false);
        if (start_q != 0) {
            System.out.println("Complete Queue already ongoing.\n" + start_q);
        } else {
            System.out.println("new time set.\n" + start_q);
            start_q = System.currentTimeMillis();

        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                // 2️⃣ Get next client from quenum (first row)
                String selectNextSql = "SELECT * FROM quenum WHERE Transaction_Type = ? ORDER BY id ASC LIMIT 1";
                try (java.sql.PreparedStatement psNext = con.prepareStatement(selectNextSql)) {
                    psNext.setString(1, transactionType);
                    try (java.sql.ResultSet rsNext = psNext.executeQuery()) {
                        if (rsNext.next()) {
                            String nextQueNum = rsNext.getString("QueNum");
                            lblCurrent.setText(nextQueNum); // Show next client in JLabel
                        } else {
                            lblCurrent.setText("");
                            CLASS_PANES.showMessageBox("No more clients in queue!");
                        }

                    }
                    psNext.close();
                }

                // 3️⃣ Refresh table
                loadQueueNumbers(table, transactionType);

                con.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error processing client: " + e.getMessage());
        }
    }

    public void CompleteQueue(JTable table, JLabel lblCurrent, String transactionType) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                String currentQueNum = lblCurrent.getText();
                System.out.println(currentQueNum);
                System.out.println(transactionType);
                end_q = System.currentTimeMillis();
                total_time = end_q - start_q;

                java.sql.PreparedStatement pst = con
                        .prepareStatement("INSERT INTO avg_servetime (duration_ms) VALUES (?)");
                pst.setLong(1, total_time);
                pst.executeUpdate();
                end_q = 0;
                start_q = 0;
                total_time = 0;

                if (!currentQueNum.isEmpty()) {
                    String selectSql = "SELECT * FROM quenum WHERE QueNum = ? AND Transaction_Type = ?";

                    try (java.sql.PreparedStatement ps = con.prepareStatement(selectSql)) {
                        ps.setString(1, currentQueNum);
                        ps.setString(2, transactionType);
                        try (java.sql.ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                id = rs.getInt("id");
                                acc_no = rs.getString("Acc_number");
                                username = rs.getString("Username");
                                amount = rs.getString("Amount");
                                // age = rs.getInt("Age");
                                mobile = rs.getString("MobileNum");

                            } else {
                                System.out.println("User not found. :P");

                            }
                        }
                        ps.close();
                    }
                    if (id != -1) {

                        // Insert into completed
                        String insertCompleted = "INSERT INTO completed (reference_no, Username, Acc_number, Que_Num, ID, Transaction_Type, Amount,Date, Time ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        try (java.sql.PreparedStatement psInsert = con.prepareStatement(insertCompleted)) {

                            String updSQL = "SELECT * FROM new_registered_user WHERE Username = ? and Acc_number = ?";
                            java.sql.PreparedStatement pstmt = con.prepareStatement(updSQL);
                            pstmt.setString(1, username);
                            pstmt.setString(2, acc_no);
                            java.sql.ResultSet rs = pstmt.executeQuery();

                            if (rs.next()) {

                                curbal = rs.getString("balance");
                                Random e = new Random();
                                int receipt = e.nextInt(100000);
                                int refno = e.nextInt(1000000000);
                                String rf = String.format("%09d", refno);
                                ref_no = rf;
                                String rn = String.format("%05d", receipt);

                                LocalDate d = LocalDate.now();
                                LocalTime t = LocalTime.now();
                                DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
                                DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");

                                String mdy = d.format(d_format);
                                String hms = t.format(t_format);

                                teller.setText("ADMIN");
                                dates.setText(mdy);
                                times.setText(hms);

                                receipt_no.setText(rn);
                                acna.setText(username);
                                acno.setText(acc_no);
                                trans.setText(transactionType);
                                rfno.setText(ref_no);
                                amt.setText(amount);
                                availbal.setText(curbal);

                                System.out.println("AMOUNT TO BE ");

                                if (transactionType.equalsIgnoreCase("WITHDRAW")) {
                                    int nb = Integer.parseInt(curbal) - Integer.parseInt(amount);
                                    newbal.setText("" + nb);
                                    new_bal = String.valueOf(nb);
                                    psInsert.setString(1, rfno.getText());
                                    psInsert.setString(2, username);
                                    psInsert.setString(3, acc_no);
                                    psInsert.setString(4, currentQueNum);
                                    psInsert.setInt(5, id);
                                    psInsert.setString(6, transactionType);
                                    psInsert.setString(7, amount);
                                    psInsert.setString(8, mdy);
                                    psInsert.setString(9, hms);
                                    psInsert.executeUpdate();
                                    System.out.println("AMOUNT TO BE WITHDRAW:  " + amount);
                                    System.out.println("CURRENT BALANCE:  " + curbal);
                                    System.out.println("NEW BALANCE: " + new_bal);
                                    insert_notif("withdraw");
                                    UPDBAL(username, acc_no, new_bal);
                                    ETIX.setVisible(true);
                                } else if (transactionType.equalsIgnoreCase("DEPOSIT")) {
                                    int nbd = Integer.parseInt(curbal) + Integer.parseInt(amount);
                                    newbal.setText("" + nbd);
                                    new_bal = String.valueOf(nbd);
                                    psInsert.setString(1, ref_no);
                                    psInsert.setString(2, username);
                                    psInsert.setString(3, acc_no);
                                    psInsert.setString(4, currentQueNum);
                                    psInsert.setInt(5, id);
                                    psInsert.setString(6, transactionType);
                                    psInsert.setString(7, amount);
                                    psInsert.setString(8, mdy);
                                    psInsert.setString(9, hms);
                                    psInsert.executeUpdate();
                                    insert_notif("deposit");
                                    UPDBAL(username, acc_no, new_bal);
                                    ETIX.setVisible(true);
                                } else if (transactionType.equalsIgnoreCase("LOAN SERVICE")
                                        || transactionType.equalsIgnoreCase("INQUIRE")) {
                                    newbal.setText("");
                                    psInsert.setString(1, ref_no);
                                    psInsert.setString(2, username);
                                    psInsert.setString(3, acc_no);
                                    psInsert.setString(4, currentQueNum);
                                    psInsert.setInt(5, id);
                                    psInsert.setString(6, transactionType);
                                    psInsert.setString(7, "---");
                                    psInsert.setString(8, mdy);
                                    psInsert.setString(9, hms);
                                    insert_notif("loan");
                                    psInsert.executeUpdate();
                                    ETIX.setVisible(true);

                                } else {
                                    CLASS_PANES.showMessageBox("User not found!");
                                }
                            } else {
                                if (transactionType.equalsIgnoreCase("INQUIRE")) {

                                    String insertCompleted2 = "INSERT INTO completed (reference_no, Username, Acc_number, Que_Num, ID, Transaction_Type, Amount,Date, Time ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                    try (java.sql.PreparedStatement psInsert2 = con
                                            .prepareStatement(insertCompleted2)) {
                                        System.out.print("I'm working");
                                        String pend = "SELECT * FROM pending_users WHERE Username = ? and MobileNum = ?";
                                        java.sql.PreparedStatement pstmt2 = con.prepareStatement(pend);
                                        pstmt2.setString(1, username);
                                        pstmt2.setString(2, mobile);
                                        java.sql.ResultSet rs2 = pstmt2.executeQuery();

                                        if (rs2.next()) {

                                            Random e = new Random();
                                            int receipt = e.nextInt(100000);
                                            int refno = e.nextInt(1000000000);
                                            String rf = String.format("%09d", refno);
                                            ref_no = rf;
                                            String rn = String.format("%05d", receipt);

                                            LocalDate d = LocalDate.now();
                                            LocalTime t = LocalTime.now();
                                            DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
                                            DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");

                                            String mdy = d.format(d_format);
                                            String hms = t.format(t_format);

                                            dates.setText(mdy);
                                            times.setText(hms);

                                            receipt_no.setText(rn);
                                            acna.setText(username);
                                            acno.setText(acc_no);
                                            trans.setText(transactionType);
                                            rfno.setText(ref_no);
                                            amt.setText(amount);
                                            availbal.setText(curbal);
                                            newbal.setText("");
                                            psInsert2.setString(1, ref_no);
                                            psInsert2.setString(2, username);
                                            psInsert2.setString(3, acc_no);
                                            psInsert2.setString(4, currentQueNum);
                                            psInsert2.setInt(5, id);
                                            psInsert2.setString(6, transactionType);
                                            psInsert2.setString(7, "---");
                                            psInsert2.setString(8, mdy);
                                            psInsert2.setString(9, hms);
                                            psInsert2.executeUpdate();
                                            insert_notif("inquiry");
                                            ETIX.setVisible(true);

                                            String pendd = "UPDATE pending_users SET status = ? WHERE Username = ? AND MobileNum = ?";
                                            try (java.sql.PreparedStatement pstmt3 = con.prepareStatement(pendd)) {
                                                pstmt3.setString(1, "paid");
                                                pstmt3.setString(2, username);
                                                pstmt3.setString(3, mobile);
                                                int rowsUpdated = pstmt3.executeUpdate();
                                                if (rowsUpdated > 0) {
                                                    CLASS_PANES.showMessageBox("Status Updated Successfully!.");
                                                    String deleteSql = "DELETE FROM quenum WHERE id = ?";
                                                    try (java.sql.PreparedStatement psDelete = con
                                                            .prepareStatement(deleteSql)) {
                                                        psDelete.setInt(1, id);
                                                        psDelete.executeUpdate();
                                                        psDelete.close();
                                                    }
                                                } else {
                                                    CLASS_PANES.showMessageBox("No record found with Account Number");
                                                }
                                            }
                                        }

                                    }

                                }
                            }

                            String deleteSql = "DELETE FROM quenum WHERE id = ?";
                            try (java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                                psDelete.setInt(1, id);
                                psDelete.executeUpdate();
                                psDelete.close();

                            }
                        }

                        lblCurrent.setText("");
                        disable_buttons(false);
                    }

                    loadQueueNumbers(table, transactionType);

                    con.close();

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error processing client: " + e.getMessage());
        }

    }

    public void UPDBAL(String un, String mb, String newAmount) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                String sql = "UPDATE new_registered_user SET balance = ? WHERE Username = ? AND Acc_number = ?";
                try (java.sql.PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setString(1, newAmount); // new amount as String
                    pstmt.setString(2, un);
                    pstmt.setString(3, mb); // client ID

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        CLASS_PANES.showMessageBox("Amount updated successfully!");

                        String deleteSql = "DELETE FROM quenum WHERE id = ?";
                        try (java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                            psDelete.setInt(1, id);
                            psDelete.executeUpdate();
                            psDelete.close();
                        }
                    } else {
                        CLASS_PANES.showMessageBox("No record found with Account Number");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error updating amount: " + e.getMessage());
        }
    }

    public void insert_notif(String transaction_type) {
        String notifSql = "INSERT INTO notifications (Username, title, message, type, created_at) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection con = java.sql.DriverManager.getConnection(url, user, pass); java.sql.PreparedStatement psNotif = con.prepareStatement(notifSql)) {

            // Insert into notifications
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String DATETIME = LocalDateTime.now().format(formatter);

            psNotif.setString(1, username);
            psNotif.setString(2, "Transaction Completed");
            psNotif.setString(3, "Your transaction " + transaction_type + " has been completed.");
            psNotif.setString(4, "completed");
            psNotif.setString(5, DATETIME);
            int rowsAffected = psNotif.executeUpdate();

        } catch (java.sql.SQLException lol) {
            lol.printStackTrace();
            CLASS_PANES.showMessageBox("❌ Notification insert failed: " + lol.getMessage());
        }

    }

    // Skip Button (move current number to Skipped DB)
    public void skipQueue(JTable table, JLabel lblCurrent, String transactionType) {
        String queNum = lblCurrent.getText(); // get the queue number from JLabel

        if (queNum.isEmpty()) {
            CLASS_PANES.showMessageBox("No client selected to skip!");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                // 1️⃣ Fetch client details from quenum using queNum
                String selectSql = "SELECT * FROM quenum WHERE QueNum = ? AND Transaction_Type = ?";
                String acc = "";
                String username = "";
                String mobile = "";
                int id = -1;
                 String date ="";
                String time ="";

                try (java.sql.PreparedStatement selectPstmt = con.prepareStatement(selectSql)) {
                    selectPstmt.setString(1, queNum);
                    selectPstmt.setString(2, transactionType);

                    try (java.sql.ResultSet rs = selectPstmt.executeQuery()) {
                        if (rs.next()) {
                            acc = rs.getString("Acc_number");
                            username = rs.getString("Username");
                            amount = rs.getString("Amount");
                            mobile = rs.getString("MobileNum");
                            id = rs.getInt("id");
                              date = rs.getString("Date");
                            time = rs.getString("Time");

                        } else {
                            CLASS_PANES.showMessageBox("Client not found in queue!");
                            return;
                        }
                    }
                }

                String insertSql = "INSERT INTO onhold (Username, MobileNum, Que_Num, Transaction_Type, ID, Amount, Acc_number, Date, Time) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
                try (java.sql.PreparedStatement insertPstmt = con.prepareStatement(insertSql)) {
                    insertPstmt.setString(1, username);
                    insertPstmt.setString(2, mobile);
                    insertPstmt.setString(3, queNum);
                    insertPstmt.setString(4, transactionType);
                    insertPstmt.setInt(5, id);
                    insertPstmt.setString(6, amount);
                    insertPstmt.setString(7, acc);    
                    insertPstmt.setString(8, date);
                    insertPstmt.setString(9, time);
                    
                    insertPstmt.executeUpdate();
                }

                String deleteSql = "DELETE FROM quenum WHERE id = ?";
                try (java.sql.PreparedStatement deletePstmt = con.prepareStatement(deleteSql)) {
                    deletePstmt.setInt(1, id);
                    deletePstmt.executeUpdate();
                }

                loadQueueNumbers(table, transactionType);

                lblCurrent.setText("");

                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error skipping client: " + e.getMessage());
        }
    }

    public void recallSkipped(String queNum, String transactionType) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                // 1️⃣ Fetch all data for the skipped client
                String selectSql = "SELECT * FROM onhold WHERE Que_Num = ? AND Transaction_Type = ?";
                int id = -1;
                String acc = "";
                String username = "";
                // int age = 0;
                String yep;
                String mobile = "";
                String date = "";
                String time = "";

                try (java.sql.PreparedStatement ps = con.prepareStatement(selectSql)) {
                    ps.setString(1, queNum);
                    ps.setString(2, transactionType);

                    try (java.sql.ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            acc = rs.getString("Acc_number");
                            id = rs.getInt("ID");
                            username = rs.getString("Username");
                            yep = rs.getString("Amount");
                            mobile = rs.getString("MobileNum");
                            date = rs.getString("Date");
                            time = rs.getString("Time");
                        } else {
                            CLASS_PANES.showMessageBox("Skipped client not found!");
                            return;
                        }
                    }
                }

                // 2️⃣ Insert client back into quenum (do NOT set ID manually)
                String insertSql = "INSERT INTO quenum (Username, MobileNum, QueNum, Transaction_Type, Amount, Acc_number, id, Date, Time) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
                try (java.sql.PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                    psInsert.setString(1, username);
                    psInsert.setString(2, mobile);
                    psInsert.setString(3, queNum);
                    psInsert.setString(4, transactionType);
                    psInsert.setString(5, yep);
                    psInsert.setString(6, acc);
                    psInsert.setInt(7, id);
                    psInsert.setString(8, date);
                    psInsert.setString(9, time);
                    psInsert.executeUpdate();
                }

                // 3️⃣ Delete client from onhold
                String deleteSql = "DELETE FROM onhold WHERE ID = ?";
                try (java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                    psDelete.setInt(1, id);
                    psDelete.executeUpdate();
                }

                // Feedback message
                CLASS_PANES.showMessageBox("Skipped client returned to the queue successfully.");

                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error recalling skipped client: " + e.getMessage());
        }
    }

    public void Skipped(String queNum, String transactionType) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                // 1️⃣ Fetch all data for the skipped client
                String selectSql = "SELECT * FROM onhold WHERE Que_Num = ? AND Transaction_Type = ?";
                int id = -1;
                String username = "";
                String amount;
                String mobile = "";
                String accc = "";

                try (java.sql.PreparedStatement ps = con.prepareStatement(selectSql)) {
                    ps.setString(1, queNum);
                    ps.setString(2, transactionType);

                    try (java.sql.ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            accc = rs.getString("Acc_number");
                            id = rs.getInt("ID");
                            username = rs.getString("Username");
                            amount = rs.getString("Amount");
                            mobile = rs.getString("MobileNum");
                        } else {
                            CLASS_PANES.showMessageBox("Skipped client not found!");
                            return;
                        }
                    }
                }

                String insertSql = "INSERT INTO cancelled (Username, MobileNum, Que_Num, Transaction_Type, Amount, Acc_number, Date, Time) VALUES (?, ?, ?, ?, ?, ?,?,?)";
                try (java.sql.PreparedStatement psInsert = con.prepareStatement(insertSql)) {

                    LocalDate d = LocalDate.now();
                    LocalTime t = LocalTime.now();
                    DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
                    DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");

                    String mdy = d.format(d_format);
                    String hms = t.format(t_format);
                    psInsert.setString(6, accc);
                    psInsert.setString(1, username);
                    psInsert.setString(2, mobile);
                    psInsert.setString(3, queNum);
                    psInsert.setString(4, transactionType);
                    psInsert.setString(5, amount);
                    psInsert.setString(7, mdy);
                    psInsert.setString(8, hms);

                    psInsert.executeUpdate();
                    psInsert.close();
                }

                // 3️⃣ Delete client from onhold
                String deleteSql = "DELETE FROM onhold WHERE ID = ?";
                try (java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                    psDelete.setInt(1, id);
                    psDelete.executeUpdate();
                }
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error recalling skipped client: " + e.getMessage());
        }
    }

    public void loadOnHoldTable(JTable onHoldTable) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Account Number");
        model.addColumn("Name");
        model.addColumn("Mobile No.");
        model.addColumn("Que_Num");
        model.addColumn("Type");
        model.addColumn("Amount");
        model.addColumn("Date");
        model.addColumn("Time");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

                String sql = "SELECT Username, MobileNum, Que_Num, Transaction_Type, Amount, Acc_number, Date, Time FROM onhold ORDER BY id ASC";
                try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
                    try (java.sql.ResultSet rs = ps.executeQuery()) {

                        while (rs.next()) {
                            String accc = rs.getString("Acc_number");
                            String username = rs.getString("Username");
                            String amount = rs.getString("Amount");
                            String mobile = rs.getString("MobileNum");
                            String queNum = rs.getString("Que_Num");
                            String transactionType = rs.getString("Transaction_Type");
                            String DATE = rs.getString("Date");
                            String time = rs.getString("Time");

                            model.addRow(new Object[]{accc, username, mobile, queNum, transactionType, amount, DATE, time});
                        }
                    }
                    ps.close();

                }

                con.close();
            }

            onHoldTable.setModel(model);
            onHoldTable.setOpaque(false);
            onHoldTable.setShowGrid(false);
            onHoldTable.getTableHeader().setBorder(null);
            JTableHeader header = onHoldTable.getTableHeader();
            header.setFont(new Font("Nimala UI", Font.BOLD, 12));

            header.setForeground(new Color(0x080525));
            header.setBackground(new Color(0xebb923));

            header.setPreferredSize(new Dimension(header.getWidth(), 30));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            centerRenderer.setVerticalAlignment(JLabel.CENTER);
            centerRenderer.setOpaque(false);

            for (int i = 0; i < onHoldTable.getColumnCount(); i++) {
                onHoldTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            onHoldTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            onHoldTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            onHoldTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            onHoldTable.getColumnModel().getColumn(3).setPreferredWidth(80);
            onHoldTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            onHoldTable.getColumnModel().getColumn(5).setPreferredWidth(50);
            onHoldTable.getColumnModel().getColumn(6).setPreferredWidth(60);
            onHoldTable.getColumnModel().getColumn(7).setPreferredWidth(60);

        } catch (Exception e) {
            e.printStackTrace();
            CLASS_PANES.showMessageBox("Error loading onhold table: " + e.getMessage());
        }
    }

    public void setupOnHoldTable(JTable ONHOLD, int statusColumnIndex) {
        ONHOLD.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Get the value in the status column
                Object statusValue = table.getValueAt(row, statusColumnIndex);

                if (statusValue != null && statusValue.toString().equalsIgnoreCase("On Hold")) {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE); // optional, better readability
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }

                // Preserve selection color
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                }

                return c;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MONITORING1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        ETIX = new javax.swing.JPanel();
        dates = new javax.swing.JLabel();
        times = new javax.swing.JLabel();
        teller = new javax.swing.JLabel();
        receipt_no = new javax.swing.JLabel();
        acna = new javax.swing.JLabel();
        acno = new javax.swing.JLabel();
        trans = new javax.swing.JLabel();
        rfno = new javax.swing.JLabel();
        amt = new javax.swing.JLabel();
        availbal = new javax.swing.JLabel();
        newbal = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        C5 = new javax.swing.JLabel();
        C6 = new javax.swing.JLabel();
        C7 = new javax.swing.JLabel();
        C8 = new javax.swing.JLabel();
        N5 = new javax.swing.JButton();
        N6 = new javax.swing.JButton();
        N7 = new javax.swing.JButton();
        N8 = new javax.swing.JButton();
        S5 = new javax.swing.JButton();
        S6 = new javax.swing.JButton();
        S7 = new javax.swing.JButton();
        S8 = new javax.swing.JButton();
        CO1 = new javax.swing.JButton();
        CO2 = new javax.swing.JButton();
        CO3 = new javax.swing.JButton();
        CO4 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        T5 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        T6 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        T7 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        T8 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        BACK1 = new javax.swing.JButton();
        jComcoBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ONHOLD = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        MONITORING1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        MONITORING1.setBorderPainted(false);
        MONITORING1.setContentAreaFilled(false);
        MONITORING1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        MONITORING1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        MONITORING1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MONITORING1ActionPerformed(evt);
            }
        });
        add(MONITORING1);
        MONITORING1.setBounds(0, 365, 318, 90);

        jComboBox2.setFont(new java.awt.Font("Nirmala UI", 1, 15)); // NOI18N
        jComboBox2.setForeground(new Color(0x046a41));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT COUNTER", "WITHDRAW", "DEPOSIT", "LOAN SERVICE", "INQUIRY" }));
        jComboBox2.setSelectedItem("WITHDRAW");
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        add(jComboBox2);
        jComboBox2.setBounds(596, 168, 230, 35);

        ETIX.setLayout(null);

        dates.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        dates.setText("jLabel5");
        ETIX.add(dates);
        dates.setBounds(78, 148, 80, 13);

        times.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        times.setText("jLabel5");
        ETIX.add(times);
        times.setBounds(78, 164, 80, 13);

        teller.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        teller.setText("ADMIN");
        ETIX.add(teller);
        teller.setBounds(226, 148, 80, 13);

        receipt_no.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        receipt_no.setText("224");
        ETIX.add(receipt_no);
        receipt_no.setBounds(226, 164, 80, 13);

        acna.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        acna.setText("jLabel5");
        ETIX.add(acna);
        acna.setBounds(120, 193, 80, 13);

        acno.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        acno.setText("jLabel5");
        ETIX.add(acno);
        acno.setBounds(110, 210, 150, 13);

        trans.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        trans.setText("jLabel5");
        ETIX.add(trans);
        trans.setBounds(135, 226, 80, 13);

        rfno.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        rfno.setText("jLabel5");
        ETIX.add(rfno);
        rfno.setBounds(118, 244, 80, 13);

        amt.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        amt.setText("jLabel5");
        ETIX.add(amt);
        amt.setBounds(92, 272, 80, 13);

        availbal.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        availbal.setText("jLabel5");
        ETIX.add(availbal);
        availbal.setBounds(137, 289, 32, 13);

        newbal.setFont(new java.awt.Font("Nirmala UI", 1, 9)); // NOI18N
        newbal.setText("jLabel5");
        ETIX.add(newbal);
        newbal.setBounds(115, 319, 32, 13);

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/E_TIX.png"))); // NOI18N
        ETIX.add(BG);
        BG.setBounds(0, 0, 360, 465);

        add(ETIX);
        ETIX.setBounds(900, 165, 360, 465);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        C5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        C5.setForeground(java.awt.Color.black);
        C5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(C5);
        C5.setBounds(50, 80, 240, 80);

        C6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        C6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(C6);
        C6.setBounds(50, 80, 240, 80);

        C7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        C7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(C7);
        C7.setBounds(50, 80, 240, 80);

        C8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        C8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(C8);
        C8.setBounds(50, 80, 240, 80);

        N5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N5.setBorderPainted(false);
        N5.setContentAreaFilled(false);
        N5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N5ActionPerformed(evt);
            }
        });
        jPanel3.add(N5);
        N5.setBounds(35, 380, 274, 46);

        N6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N6.setBorderPainted(false);
        N6.setContentAreaFilled(false);
        N6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N6ActionPerformed(evt);
            }
        });
        jPanel3.add(N6);
        N6.setBounds(35, 380, 274, 46);

        N7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N7.setBorderPainted(false);
        N7.setContentAreaFilled(false);
        N7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N7ActionPerformed(evt);
            }
        });
        jPanel3.add(N7);
        N7.setBounds(35, 380, 274, 46);

        N8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N8.setBorderPainted(false);
        N8.setContentAreaFilled(false);
        N8.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N8ActionPerformed(evt);
            }
        });
        jPanel3.add(N8);
        N8.setBounds(35, 380, 274, 46);

        S5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S5.setToolTipText("");
        S5.setBorderPainted(false);
        S5.setContentAreaFilled(false);
        S5.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S5ActionPerformed(evt);
            }
        });
        jPanel3.add(S5);
        S5.setBounds(324, 62, 178, 46);

        S6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S6.setToolTipText("");
        S6.setBorderPainted(false);
        S6.setContentAreaFilled(false);
        S6.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S6ActionPerformed(evt);
            }
        });
        jPanel3.add(S6);
        S6.setBounds(324, 62, 178, 46);

        S7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S7.setToolTipText("");
        S7.setBorderPainted(false);
        S7.setContentAreaFilled(false);
        S7.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S7ActionPerformed(evt);
            }
        });
        jPanel3.add(S7);
        S7.setBounds(324, 62, 178, 46);

        S8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S8.setToolTipText("");
        S8.setBorderPainted(false);
        S8.setContentAreaFilled(false);
        S8.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S8.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S8ActionPerformed(evt);
            }
        });
        jPanel3.add(S8);
        S8.setBounds(324, 62, 178, 46);

        CO1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete u.png"))); // NOI18N
        CO1.setToolTipText("");
        CO1.setBorderPainted(false);
        CO1.setContentAreaFilled(false);
        CO1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete d.png"))); // NOI18N
        CO1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CO1ActionPerformed(evt);
            }
        });
        jPanel3.add(CO1);
        CO1.setBounds(324, 119, 178, 46);

        CO2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete u.png"))); // NOI18N
        CO2.setToolTipText("");
        CO2.setBorderPainted(false);
        CO2.setContentAreaFilled(false);
        CO2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete d.png"))); // NOI18N
        CO2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CO2ActionPerformed(evt);
            }
        });
        jPanel3.add(CO2);
        CO2.setBounds(324, 119, 178, 46);

        CO3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete u.png"))); // NOI18N
        CO3.setToolTipText("");
        CO3.setBorderPainted(false);
        CO3.setContentAreaFilled(false);
        CO3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete d.png"))); // NOI18N
        CO3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CO3ActionPerformed(evt);
            }
        });
        jPanel3.add(CO3);
        CO3.setBounds(324, 119, 178, 46);

        CO4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete u.png"))); // NOI18N
        CO4.setToolTipText("");
        CO4.setBorderPainted(false);
        CO4.setContentAreaFilled(false);
        CO4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete d.png"))); // NOI18N
        CO4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/complete c.png"))); // NOI18N
        CO4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CO4ActionPerformed(evt);
            }
        });
        jPanel3.add(CO4);
        CO4.setBounds(324, 119, 178, 46);

        T5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        T5.setRowHeight(30);
        jScrollPane6.setViewportView(T5);

        jPanel3.add(jScrollPane6);
        jScrollPane6.setBounds(50, 207, 440, 160);

        T6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        T6.setRowHeight(30);
        jScrollPane7.setViewportView(T6);

        jPanel3.add(jScrollPane7);
        jScrollPane7.setBounds(50, 207, 440, 160);

        T7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        T7.setRowHeight(30);
        jScrollPane8.setViewportView(T7);

        jPanel3.add(jScrollPane8);
        jScrollPane8.setBounds(50, 207, 440, 160);

        T8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        T8.setRowHeight(30);
        jScrollPane9.setViewportView(T8);

        jPanel3.add(jScrollPane9);
        jScrollPane9.setBounds(50, 207, 440, 160);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/NEW_QMEP.png"))); // NOI18N
        jLabel4.setText("jLabel2");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(0, 0, 543, 431);

        add(jPanel3);
        jPanel3.setBounds(325, 210, 543, 431);

        BACK1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        BACK1.setBorderPainted(false);
        BACK1.setContentAreaFilled(false);
        BACK1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_back.png"))); // NOI18N
        BACK1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_back.png"))); // NOI18N
        BACK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACK1ActionPerformed(evt);
            }
        });
        add(BACK1);
        BACK1.setBounds(0, 548, 318, 90);

        jComcoBox1.setFont(new java.awt.Font("Nirmala UI", 1, 15)); // NOI18N
        jComcoBox1.setForeground(new Color(0x046a41));
        jComcoBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING TRANSACTIONS", "ON-HOLD TRANSACTIONS" }));
        jComcoBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComcoBox1ActionPerformed(evt);
            }
        });
        add(jComcoBox1);
        jComcoBox1.setBounds(363, 168, 230, 35);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        ONHOLD.setModel(new javax.swing.table.DefaultTableModel(
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
        ONHOLD.setRowHeight(30);
        ONHOLD.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ONHOLDMouseMoved(evt);
            }
        });
        ONHOLD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ONHOLDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ONHOLDMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(ONHOLD);

        jPanel2.add(jScrollPane4);
        jScrollPane4.setBounds(97, 108, 772, 308);

        search.setBackground(java.awt.Color.white);
        search.setForeground(java.awt.Color.black);
        search.setBorder(null);
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        jPanel2.add(search);
        search.setBounds(120, 59, 256, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/ON-HOLD.png"))); // NOI18N
        jPanel2.add(jLabel3);
        jLabel3.setBounds(0, 0, 946, 431);

        add(jPanel2);
        jPanel2.setBounds(325, 210, 946, 431);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_QMP.gif"))); // NOI18N
        jLabel1.setText("]");
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void jComcoBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComcoBox1ActionPerformed

        if (jComcoBox1.getSelectedIndex() == 0) {

            jPanel3.setVisible(true);
            jPanel2.setVisible(false);
            jComboBox2.setVisible(true);

        } else {
            jPanel2.setVisible(true);
            jPanel3.setVisible(false);
            jComboBox2.setSelectedIndex(1);
            jComboBox2.setVisible(false);
            ETIX.setVisible(false);
        }

    }//GEN-LAST:event_jComcoBox1ActionPerformed

    private void MONITORING1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MONITORING1ActionPerformed
        frame.setContentPane(new QueueMonitoring(frame));
        frame.revalidate();
    }//GEN-LAST:event_MONITORING1ActionPerformed

    private void BACK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACK1ActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACK1ActionPerformed

    private void ONHOLDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ONHOLDMousePressed

        int row = ONHOLD.getSelectedRow();

        if (row != -1) {

            String queNum = ONHOLD.getValueAt(row, 3).toString();
            String transactionType = ONHOLD.getValueAt(row, 4).toString();

            Object[] options = {"Recover", "Skipped", "Cancel"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Do you want to recover this user?",
                    "Recover User",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                recallSkipped(queNum, transactionType);
                System.out.println("Recover chosen");
            } else if (choice == 1) {
                Skipped(queNum, transactionType);
                System.out.println("Skipped chosen");
            } else if (choice == 2) {

                System.out.println("Cancel chosen");
            }
        }
    }//GEN-LAST:event_ONHOLDMousePressed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        DefaultTableModel ob = (DefaultTableModel) ONHOLD.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        ONHOLD.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(search.getText()));
    }//GEN-LAST:event_searchKeyReleased

    private void ONHOLDMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ONHOLDMouseMoved
        int row = ONHOLD.rowAtPoint(evt.getPoint());
        if (row != hoveredRow) {
            hoveredRow = row;
            ONHOLD.repaint(); // update hover
        }
    }//GEN-LAST:event_ONHOLDMouseMoved

    private void ONHOLDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ONHOLDMouseExited
        // Reset hovered row when mouse leaves the table
        hoveredRow = -1;
        ONHOLD.repaint(); // repaint table to remove hover color
    }//GEN-LAST:event_ONHOLDMouseExited

    private void N5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N5ActionPerformed
        disable_buttons(isTrue);
        callNext(T5, C5, "Withdraw");
    }//GEN-LAST:event_N5ActionPerformed

    private void N6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N6ActionPerformed
        disable_buttons(isTrue);
        callNext(T6, C6, "Deposit");
    }//GEN-LAST:event_N6ActionPerformed

    private void N7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N7ActionPerformed
        disable_buttons(isTrue);
        callNext(T7, C7, "Loan");
    }//GEN-LAST:event_N7ActionPerformed

    private void N8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N8ActionPerformed
        disable_buttons(isTrue);
        callNext(T8, C8, "Inquire");
    }//GEN-LAST:event_N8ActionPerformed

    private void S5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S5ActionPerformed
        skipQueue(T5, C5, "Withdraw");
    }//GEN-LAST:event_S5ActionPerformed

    private void S6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S6ActionPerformed
        skipQueue(T6, C6, "Deposit");
    }//GEN-LAST:event_S6ActionPerformed

    private void S7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S7ActionPerformed
        skipQueue(T7, C7, "Loan");
    }//GEN-LAST:event_S7ActionPerformed

    private void S8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S8ActionPerformed
        skipQueue(T8, C8, "Inquire");
    }//GEN-LAST:event_S8ActionPerformed

    private void CO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO1ActionPerformed
        CompleteQueue(T5, C5, "Withdraw");

    }//GEN-LAST:event_CO1ActionPerformed

    private void CO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO2ActionPerformed
        CompleteQueue(T6, C6, "Deposit");
    }//GEN-LAST:event_CO2ActionPerformed

    private void CO3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO3ActionPerformed
        CompleteQueue(T7, C7, "Loan");
    }//GEN-LAST:event_CO3ActionPerformed

    private void CO4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO4ActionPerformed
        CompleteQueue(T8, C8, "Inquire");
    }//GEN-LAST:event_CO4ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        determiner(jComboBox2.getSelectedIndex());
    }//GEN-LAST:event_jComboBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK1;
    private javax.swing.JLabel BG;
    public static javax.swing.JLabel C5;
    public static javax.swing.JLabel C6;
    public static javax.swing.JLabel C7;
    public static javax.swing.JLabel C8;
    public static javax.swing.JButton CO1;
    public static javax.swing.JButton CO2;
    public static javax.swing.JButton CO3;
    public static javax.swing.JButton CO4;
    private javax.swing.JPanel ETIX;
    private javax.swing.JButton MONITORING1;
    public static javax.swing.JButton N5;
    public static javax.swing.JButton N6;
    public static javax.swing.JButton N7;
    public static javax.swing.JButton N8;
    private javax.swing.JTable ONHOLD;
    public static javax.swing.JButton S5;
    public static javax.swing.JButton S6;
    public static javax.swing.JButton S7;
    public static javax.swing.JButton S8;
    private javax.swing.JTable T5;
    private javax.swing.JTable T6;
    private javax.swing.JTable T7;
    private javax.swing.JTable T8;
    private javax.swing.JLabel acna;
    private javax.swing.JLabel acno;
    private javax.swing.JLabel amt;
    private javax.swing.JLabel availbal;
    private javax.swing.JLabel dates;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComcoBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel newbal;
    private javax.swing.JLabel receipt_no;
    private javax.swing.JLabel rfno;
    private javax.swing.JTextField search;
    private javax.swing.JLabel teller;
    private javax.swing.JLabel times;
    private javax.swing.JLabel trans;
    // End of variables declaration//GEN-END:variables
}
