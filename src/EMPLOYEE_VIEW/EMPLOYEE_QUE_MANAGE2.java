/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package EMPLOYEE_VIEW;
import static ADMIN_VIEW.QueueManagement.username;
import CLIENT_VIEW.CLASS_PANES;
import CLIENT_VIEW.CLASS_QNUM_CONSTRUCTOR;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Random;
import javax.swing.table.JTableHeader;
/**
 *
 * @author reyes
 */
public class EMPLOYEE_QUE_MANAGE2 extends javax.swing.JPanel {
    public static CLASS_TRANSACTIONS t;
    public int hoveredRow = -1;;
    public static String ref_no;
    public static String acc_no;
    public static String amount ="";
    public static String DAT ="";
    public static String username ="";
    public static String mobile ="";
    public static String curbal ="";
    public static String new_bal="";
    public static  int id = -1;
    public static String show_only;
    public static long start_q;
    public static long end_q;
    public static long total_time;
    public static String tellers = Log.tellername;
      private final String url = "jdbc:mysql://localhost:3306/tbaqs";
    private final String user = "root";
    private final String pass = "";
    private CLIENT_VIEW.Frame frame;
    public EMPLOYEE_QUE_MANAGE2(CLIENT_VIEW.Frame frame) {
        initComponents();
         this.frame = frame;
        determiner(frame.TRANSACTION);
        update();
        ETIX.setVisible(false);
               jPanel2.setVisible(false);
        jPanel1.setVisible(true);
        C1.setForeground(new Color(0x080525));
        C2.setForeground(new Color(0x080525));
        C3.setForeground(new Color(0x080525));
        C4.setForeground(new Color(0x080525));
        disable_buttons(false);
    }
    public void disable_buttons(boolean choose){
        S1.setEnabled(choose);S2.setEnabled(choose);S3.setEnabled(choose);S4.setEnabled(choose);
        CO1.setEnabled(choose);CO2.setEnabled(choose);CO3.setEnabled(choose);CO4.setEnabled(choose);
    }
        public void determiner(String counter){
            String window =counter.substring(0,counter.indexOf(" "));
            if(window.equalsIgnoreCase("Withdraw")){
                T1.setVisible(true);T2.setVisible(false); T3.setVisible(false);  T4.setVisible(false);
                C1.setVisible(true);C2.setVisible(false);C3.setVisible(false); C4.setVisible(false);
                S1.setVisible(true); S2.setVisible(false);S3.setVisible(false);S4.setVisible(false);
                N1.setVisible(true); N2.setVisible(false);N3.setVisible(false);N4.setVisible(false);
                CO1.setVisible(true);CO2.setVisible(false); CO3.setVisible(false);CO4.setVisible(false);
                show_only = "Withdraw";
            }else  if(window.equalsIgnoreCase("Deposit")){
                T1.setVisible(false);T2.setVisible(true); T3.setVisible(false);  T4.setVisible(false);
                C1.setVisible(false);C2.setVisible(true);C3.setVisible(false); C4.setVisible(false);
                S1.setVisible(false); S2.setVisible(true);S3.setVisible(false);S4.setVisible(false);
                N1.setVisible(false); N2.setVisible(true);N3.setVisible(false);N4.setVisible(false);
                CO1.setVisible(false);CO2.setVisible(true); CO3.setVisible(false);CO4.setVisible(false);
                show_only = "Deposit";
            }else  if(window.equalsIgnoreCase("Loan")){
                T1.setVisible(false);T2.setVisible(false); T3.setVisible(true);  T4.setVisible(false);
                C1.setVisible(false);C2.setVisible(false);C3.setVisible(true); C4.setVisible(false);
                S1.setVisible(false); S2.setVisible(false);S3.setVisible(true);S4.setVisible(false);
                N1.setVisible(false); N2.setVisible(false);N3.setVisible(true);N4.setVisible(false);
                CO1.setVisible(false);CO2.setVisible(false); CO3.setVisible(true);CO4.setVisible(false);
                show_only = "Loan";
            }else  if(window.equalsIgnoreCase("Inquiry")){
               T1.setVisible(false);T2.setVisible(false); T3.setVisible(false);  T4.setVisible(true);
                C1.setVisible(false);C2.setVisible(false);C3.setVisible(false); C4.setVisible(true);
                S1.setVisible(false); S2.setVisible(false);S3.setVisible(false);S4.setVisible(true);
                N1.setVisible(false); N2.setVisible(false);N3.setVisible(false);N4.setVisible(true);
                CO1.setVisible(false);CO2.setVisible(false); CO3.setVisible(false);CO4.setVisible(true);
                show_only = "Inquire";
            }
        }
     public void update(){
        new javax.swing.Timer(0000, e -> {
        //load ques from database
        bridge();
        styleTable(T1);
        styleTable(T2);
        styleTable(T3);
        styleTable(T4);
        styleTable(ONHOLD);
        loadQueueNumbers(T1, "Withdraw");
        loadQueueNumbers(T2, "Deposit");
        loadQueueNumbers(T3, "Loan");
        loadQueueNumbers(T4, "Inquire");
        loadOnHoldTable(show_only,ONHOLD);
        bridge();
        }).start();
    }
     public void bridge() {
        ArrayList<String> values = new ArrayList<>();
        values.add(C1.getText()); // withdraw
        values.add(C2.getText()); // deposit
        values.add(C3.getText()); // loan
        values.add(C4.getText()); // inquire
        t = new CLASS_TRANSACTIONS(values);
    }
    public static void styleTable(JTable table) {
    // Transparent table & header
    table.setOpaque(false);
    table.setFillsViewportHeight(true);
    table.getTableHeader().setOpaque(false);
    table.getTableHeader().setBackground(new Color(0,0,0,0)); // fully transparent
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
            "jdbc:mysql://localhost:3306/tbaqs", "root", ""
        );
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
// Call next in queue, move to ongoing, remove from quenum
//complete
public void callNext(JTable table, JLabel lblCurrent, String transactionType) {
    ETIX.setVisible(false);
     if(start_q !=0){
         System.out.println("Complete Queue already ongoing.\n" + start_q);
     }else{
          System.out.println("new time set.\n" + start_q);
         start_q= System.currentTimeMillis();
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
public void CompleteQueue(JTable table, JLabel lblCurrent, String transactionType){
try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {
            String currentQueNum = lblCurrent.getText();
            System.out.println(currentQueNum);
            System.out.println(transactionType);
             end_q = System.currentTimeMillis();
             total_time =end_q - start_q;
             java.sql.PreparedStatement pst = con.prepareStatement("INSERT INTO avg_servetime (duration_ms) VALUES (?)");
             pst.setLong(1, total_time);
             pst.executeUpdate();
             end_q =0;
             start_q = 0;
             total_time =0;
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
                            amount =rs.getString("Amount");
                            //age = rs.getInt("Age");
                            mobile = rs.getString("MobileNum");
                        }else{
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
                                if(rs.next()){
                          curbal =  rs.getString("balance");
                         Random e = new Random();
                         int receipt = e.nextInt(100000);
                         int refno =e.nextInt(1000000000);
                         String rf = String.format("%09d", refno);
                         ref_no = rf;
                         String rn = String.format("%05d", receipt);
                        LocalDate d = LocalDate.now();
                        LocalTime t = LocalTime.now();
                        DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
                        DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");
                        String mdy = d.format(d_format);
                        String hms =t.format(t_format);
                        teller.setText(""+tellers);
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
                        
                       if(transactionType.equalsIgnoreCase("WITHDRAW")){
                           int nb = Integer.parseInt(curbal ) - Integer.parseInt(amount);
                           newbal.setText(""+nb);
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
                          UPDBAL(username,acc_no,new_bal);
                          ETIX.setVisible(true);
                       } else if(transactionType.equalsIgnoreCase("DEPOSIT")){
                           int nbd= Integer.parseInt(curbal ) + Integer.parseInt(amount);
                           newbal.setText(""+nbd);
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
                           UPDBAL(username,acc_no, new_bal);
                           ETIX.setVisible(true);
                       }else if(transactionType.equalsIgnoreCase("LOAN SERVICE") || transactionType.equalsIgnoreCase("INQUIRE")){
                          newbal.setText("");
                           psInsert.setString(1, rfno.getText());
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
                       }else{
                                CLASS_PANES.showMessageBox("User not found!");
                                }
                    }else{
                          if(transactionType.equalsIgnoreCase("INQUIRE")){
                                String insertCompleted2 = "INSERT INTO completed (reference_no, Username, Acc_number, Que_Num, ID, Transaction_Type, Amount,Date, Time ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (java.sql.PreparedStatement psInsert2 = con.prepareStatement(insertCompleted2)) {
                            System.out.print("I'm working");
                          String pend = "SELECT * FROM pending_users WHERE Username = ? and MobileNum = ?";
                                java.sql.PreparedStatement pstmt2 = con.prepareStatement(pend);
                                pstmt2.setString(1, username);
                                pstmt2.setString(2, mobile);
                                java.sql.ResultSet rs2 = pstmt2.executeQuery();
                                if(rs2.next()){
                                     Random e = new Random();
                         int receipt = e.nextInt(100000);
                         int refno =e.nextInt(1000000000);
                         String rf = String.format("%09d", refno);
                         ref_no = rf;
                         String rn = String.format("%05d", receipt);
                        LocalDateTime time = LocalDateTime.now();
                        LocalDate d = LocalDate.now();
                        LocalTime t = LocalTime.now();
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-YYYY || hh:mm a");
                        DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
                        DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");
                        String mdy = d.format(d_format);
                        String hms =t.format(t_format);
                        String dat = time.format(format);
                        
                        //updating
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
                        
                        //inserting
                        psInsert2.setString(1, ref_no);
                        psInsert2.setString(2, username);
                        psInsert2.setString(3, acc_no);
                        psInsert2.setString(4, currentQueNum);
                        psInsert2.setInt(5, id);
                        psInsert2.setString(6, transactionType);
                        psInsert2.setString(7, "---");
                        psInsert2.setString(8, mdy);
                        psInsert2.setString(9, hms);
                        psInsert2 .executeUpdate();
                            insert_notif("inquiry");
                        ETIX.setVisible(true);
                        String pendd = "UPDATE pending_users SET status = ? WHERE Username = ? AND MobileNum = ?";
                                try (java.sql.PreparedStatement pstmt3 = con.prepareStatement(pendd)) {
                                    pstmt3.setString(1, "paid"); // new amount as String
                                    pstmt3.setString(2, username); 
                                    pstmt3.setString(3, mobile);
                                    int rowsUpdated = pstmt3.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        CLASS_PANES.showMessageBox("status updated successfully!");
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
                       }  
                                /*  
                                /*
                         */
                                }
                }
                        String deleteSql = "DELETE FROM quenum WHERE id = ?";
                    try (java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                        psDelete.setInt(1, id);
                        psDelete.executeUpdate();
                         psDelete.close();
                    }
                       }
                lblCurrent.setText(""); // Clear after storing
            }
            loadQueueNumbers(table, transactionType);
con.close();
        }
    } }catch (Exception e) {
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
                pstmt.setString(3, mb );  // client ID 
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    CLASS_PANES.showMessageBox("Amount updated successfully!");
                    //CLIENT_VIEW.PUBLIC_QUE.
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
            String acc="";
            String username = "";
            String mobile = "";
            int id = -1;
            try (java.sql.PreparedStatement selectPstmt = con.prepareStatement(selectSql)) {
                selectPstmt.setString(1, queNum);
                selectPstmt.setString(2, transactionType);
                try (java.sql.ResultSet rs = selectPstmt.executeQuery()) {
                    if (rs.next()) {
                        acc = rs.getString("Acc_number");
                        username = rs.getString("Username");
                         amount =rs.getString("Amount");
                        mobile = rs.getString("MobileNum");
                        id = rs.getInt("id");
                    } else {
                        CLASS_PANES.showMessageBox("Client not found in queue!");
                        return;
                    }
                }
            }
            String insertSql = "INSERT INTO onhold (Username, MobileNum, Que_Num, Transaction_Type, ID, Amount, Acc_number) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (java.sql.PreparedStatement insertPstmt = con.prepareStatement(insertSql)) {
                insertPstmt.setString(1, username);
                insertPstmt.setString(2, mobile);
                insertPstmt.setString(3, queNum);
                insertPstmt.setString(4, transactionType);
                insertPstmt.setInt(5, id);
                 insertPstmt.setString(6, amount);
                 insertPstmt.setString(7, acc);
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
            String acc ="";
            String username = "";
            //int age = 0;
             String yep;
            String mobile = "";
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
                    } else {
                        CLASS_PANES.showMessageBox("Skipped client not found!");
                        return;
                    }
                }
            }
            // 2️⃣ Insert client back into quenum (do NOT set ID manually)
            String insertSql = "INSERT INTO quenum (Username, MobileNum, QueNum, Transaction_Type, Amount, Acc_number, id,) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (java.sql.PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                psInsert.setString(1, username);
                psInsert.setString(2, mobile);
                psInsert.setString(3, queNum);
                psInsert.setString(4, transactionType);
                psInsert.setString(5, yep);
                psInsert.setString(6, acc);
                psInsert.setInt(7, id);
                //psInsert.setString(8,)
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
            String  amount;
            String mobile = "";
            String accc = "";
            try (java.sql.PreparedStatement ps = con.prepareStatement(selectSql)) {
                ps.setString(1, queNum);
                ps.setString(2, transactionType);
                try (java.sql.ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        accc =rs.getString("Acc_number");
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
                        String hms =t.format(t_format);
                        psInsert.setString(6, accc);
                        psInsert.setString(1, username);
                        psInsert.setString(2, mobile);
                        psInsert.setString(3, queNum);
                        psInsert.setString(4, transactionType);
                        psInsert.setString(5, amount);
                        psInsert.setString(7,mdy);
                        psInsert.setString(8,hms);
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
public void loadOnHoldTable(String show,JTable onHoldTable) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Account Number");
    model.addColumn("Name");
    model.addColumn("Mobile Number");
    model.addColumn("Que_Num");
    model.addColumn("Transaction Type");
    model.addColumn("Amount");
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "")) {
            String sql = "SELECT Username, MobileNum, Que_Num, Transaction_Type, Amount, Acc_number FROM onhold WHERE Transaction_Type = ? ORDER BY id ASC";
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql)){
                    ps.setString(1,show);
                 try(java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String accc = rs.getString("Acc_number");
                    String username = rs.getString("Username");
                    String amount = rs.getString("Amount");
                    String mobile = rs.getString("MobileNum");
                    String queNum = rs.getString("Que_Num");
                    String transactionType = rs.getString("Transaction_Type"); // full type
                    model.addRow(new Object[]{accc,username,mobile,queNum,transactionType, amount});
                }
            }ps.close();
           }
            con.close();
        }
        onHoldTable.setModel(model);
    } catch (Exception e) {
        e.printStackTrace();
        CLASS_PANES.showMessageBox("Error loading onhold table: " + e.getMessage());
    }
}
// Call this after initializing your table and loading data
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
        MONITORING = new javax.swing.JButton();
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
        jPanel1 = new javax.swing.JPanel();
        C1 = new javax.swing.JLabel();
        C2 = new javax.swing.JLabel();
        C3 = new javax.swing.JLabel();
        C4 = new javax.swing.JLabel();
        N1 = new javax.swing.JButton();
        N2 = new javax.swing.JButton();
        N3 = new javax.swing.JButton();
        N4 = new javax.swing.JButton();
        S1 = new javax.swing.JButton();
        S2 = new javax.swing.JButton();
        S3 = new javax.swing.JButton();
        S4 = new javax.swing.JButton();
        CO1 = new javax.swing.JButton();
        CO2 = new javax.swing.JButton();
        CO3 = new javax.swing.JButton();
        CO4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        T4 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        T3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        T2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        T1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        BACK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ONHOLD = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        setLayout(null);
        MONITORING.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/fill.png"))); // NOI18N
        MONITORING.setBorderPainted(false);
        MONITORING.setContentAreaFilled(false);
        MONITORING.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        MONITORING.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/BUTTON IMAGES/hover_trans.png"))); // NOI18N
        MONITORING.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MONITORINGActionPerformed(evt);
            }
        });
        add(MONITORING);
        MONITORING.setBounds(0, 365, 316, 90);
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
        teller.setText("224");
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
        acno.setBounds(110, 210, 80, 13);
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
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);
        C1.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        C1.setForeground(java.awt.Color.black);
        C1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(C1);
        C1.setBounds(50, 80, 240, 80);
        C2.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        C2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(C2);
        C2.setBounds(50, 80, 240, 80);
        C3.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        C3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(C3);
        C3.setBounds(50, 80, 240, 80);
        C4.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        C4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(C4);
        C4.setBounds(50, 80, 240, 80);
        N1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N1.setBorderPainted(false);
        N1.setContentAreaFilled(false);
        N1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N1ActionPerformed(evt);
            }
        });
        jPanel1.add(N1);
        N1.setBounds(35, 380, 274, 46);
        N2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N2.setBorderPainted(false);
        N2.setContentAreaFilled(false);
        N2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N2ActionPerformed(evt);
            }
        });
        jPanel1.add(N2);
        N2.setBounds(35, 380, 274, 46);
        N3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N3.setBorderPainted(false);
        N3.setContentAreaFilled(false);
        N3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N3ActionPerformed(evt);
            }
        });
        jPanel1.add(N3);
        N3.setBounds(35, 380, 274, 46);
        N4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call u.png"))); // NOI18N
        N4.setBorderPainted(false);
        N4.setContentAreaFilled(false);
        N4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/call c.png"))); // NOI18N
        N4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                N4ActionPerformed(evt);
            }
        });
        jPanel1.add(N4);
        N4.setBounds(35, 380, 274, 46);
        S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S1.setToolTipText("");
        S1.setBorderPainted(false);
        S1.setContentAreaFilled(false);
        S1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S1ActionPerformed(evt);
            }
        });
        jPanel1.add(S1);
        S1.setBounds(324, 62, 178, 46);
        S2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S2.setToolTipText("");
        S2.setBorderPainted(false);
        S2.setContentAreaFilled(false);
        S2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S2ActionPerformed(evt);
            }
        });
        jPanel1.add(S2);
        S2.setBounds(324, 62, 178, 46);
        S3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S3.setToolTipText("");
        S3.setBorderPainted(false);
        S3.setContentAreaFilled(false);
        S3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S3ActionPerformed(evt);
            }
        });
        jPanel1.add(S3);
        S3.setBounds(324, 62, 178, 46);
        S4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button u.png"))); // NOI18N
        S4.setToolTipText("");
        S4.setBorderPainted(false);
        S4.setContentAreaFilled(false);
        S4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button d.png"))); // NOI18N
        S4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/hold_button c.png"))); // NOI18N
        S4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S4ActionPerformed(evt);
            }
        });
        jPanel1.add(S4);
        S4.setBounds(324, 62, 178, 46);
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
        jPanel1.add(CO1);
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
        jPanel1.add(CO2);
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
        jPanel1.add(CO3);
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
        jPanel1.add(CO4);
        CO4.setBounds(324, 119, 178, 46);
        T4.setModel(new javax.swing.table.DefaultTableModel(
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
        T4.setRowHeight(30);
        jScrollPane5.setViewportView(T4);
        jPanel1.add(jScrollPane5);
        jScrollPane5.setBounds(50, 207, 440, 160);
        T3.setModel(new javax.swing.table.DefaultTableModel(
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
        T3.setRowHeight(30);
        jScrollPane3.setViewportView(T3);
        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(50, 207, 440, 160);
        T2.setModel(new javax.swing.table.DefaultTableModel(
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
        T2.setRowHeight(30);
        jScrollPane2.setViewportView(T2);
        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(50, 207, 440, 160);
        T1.setModel(new javax.swing.table.DefaultTableModel(
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
        T1.setRowHeight(30);
        jScrollPane1.setViewportView(T1);
        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(50, 207, 440, 160);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/NEW_QMEP.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 543, 431);
        add(jPanel1);
        jPanel1.setBounds(325, 210, 543, 431);
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
        BACK.setBounds(0, 548, 316, 90);
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ONHOLDMouseEntered(evt);
            }
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
        jComboBox1.setFont(new java.awt.Font("Nirmala UI", 1, 15)); // NOI18N
        jComboBox1.setForeground(new Color(0x041a46));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING TRANSACTIONS", "ON-HOLD TRANSACTIONS" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(363, 168, 230, 35);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/EM_QMP.gif"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents
    private void MONITORINGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MONITORINGActionPerformed
        frame.setContentPane(new QueueMonitoring(frame));
        frame.revalidate();
    }//GEN-LAST:event_MONITORINGActionPerformed
    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed
    private void N1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N1ActionPerformed
        disable_buttons(true);
        callNext(T1, C1, "Withdraw");
    }//GEN-LAST:event_N1ActionPerformed
    private void S1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S1ActionPerformed
        disable_buttons(true);
        skipQueue(T1, C1, "Withdraw");
    }//GEN-LAST:event_S1ActionPerformed
    private void N2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N2ActionPerformed
        disable_buttons(true);
        callNext(T2, C2, "Deposit");
    }//GEN-LAST:event_N2ActionPerformed
    private void N3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N3ActionPerformed
        disable_buttons(true);
        callNext(T3, C3, "Loan");
    }//GEN-LAST:event_N3ActionPerformed
    private void N4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_N4ActionPerformed
        disable_buttons(true);
        callNext(T4, C4, "Inquire");
    }//GEN-LAST:event_N4ActionPerformed
    private void S2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S2ActionPerformed
        skipQueue(T2, C2, "Deposit");
    }//GEN-LAST:event_S2ActionPerformed
    private void S3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S3ActionPerformed
        skipQueue(T3, C3, "Loan");
    }//GEN-LAST:event_S3ActionPerformed
    private void S4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S4ActionPerformed
        skipQueue(T4, C4, "Inquire");
    }//GEN-LAST:event_S4ActionPerformed
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedIndex() ==0){
            jPanel2.setVisible(false);
            jPanel1.setVisible(true);
        }else{
            jPanel2.setVisible(true);
            jPanel1.setVisible(false);
            ETIX.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    private void ONHOLDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ONHOLDMousePressed
        int row = ONHOLD.getSelectedRow();
        if (row != -1){
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
       DefaultTableModel ob = (DefaultTableModel)ONHOLD.getModel();
       TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
       ONHOLD.setRowSorter(obj);
       obj.setRowFilter(RowFilter.regexFilter(search.getText()));
    }//GEN-LAST:event_searchKeyReleased
    private void ONHOLDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ONHOLDMouseEntered
    }//GEN-LAST:event_ONHOLDMouseEntered
    private void ONHOLDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ONHOLDMouseExited
        // Reset hovered row when mouse leaves the table
        hoveredRow = -1;
        ONHOLD.repaint(); // repaint table to remove hover color
    }//GEN-LAST:event_ONHOLDMouseExited
    private void ONHOLDMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ONHOLDMouseMoved
        int row = ONHOLD.rowAtPoint(evt.getPoint());
        if (row != hoveredRow) {
            hoveredRow = row;
            ONHOLD.repaint(); // update hover
        }
    }//GEN-LAST:event_ONHOLDMouseMoved
    private void CO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO1ActionPerformed
        CompleteQueue(T1, C1, "Withdraw");
    }//GEN-LAST:event_CO1ActionPerformed
    private void CO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO2ActionPerformed
        CompleteQueue(T2, C2, "Deposit");
    }//GEN-LAST:event_CO2ActionPerformed
    private void CO3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO3ActionPerformed
         CompleteQueue(T3, C3, "Loan");
    }//GEN-LAST:event_CO3ActionPerformed
    private void CO4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CO4ActionPerformed
        CompleteQueue(T4, C4, "Inquire");
    }//GEN-LAST:event_CO4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JLabel BG;
    public static javax.swing.JLabel C1;
    public static javax.swing.JLabel C2;
    public static javax.swing.JLabel C3;
    public static javax.swing.JLabel C4;
    public static javax.swing.JButton CO1;
    public static javax.swing.JButton CO2;
    public static javax.swing.JButton CO3;
    public static javax.swing.JButton CO4;
    private javax.swing.JPanel ETIX;
    private javax.swing.JButton MONITORING;
    public static javax.swing.JButton N1;
    public static javax.swing.JButton N2;
    public static javax.swing.JButton N3;
    public static javax.swing.JButton N4;
    private javax.swing.JTable ONHOLD;
    public static javax.swing.JButton S1;
    public static javax.swing.JButton S2;
    public static javax.swing.JButton S3;
    public static javax.swing.JButton S4;
    private javax.swing.JTable T1;
    private javax.swing.JTable T2;
    private javax.swing.JTable T3;
    private javax.swing.JTable T4;
    private javax.swing.JLabel acna;
    private javax.swing.JLabel acno;
    private javax.swing.JLabel amt;
    private javax.swing.JLabel availbal;
    private javax.swing.JLabel dates;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel newbal;
    private javax.swing.JLabel receipt_no;
    private javax.swing.JLabel rfno;
    private javax.swing.JTextField search;
    private javax.swing.JLabel teller;
    private javax.swing.JLabel times;
    private javax.swing.JLabel trans;
    // End of variables declaration//GEN-END:variables
}
