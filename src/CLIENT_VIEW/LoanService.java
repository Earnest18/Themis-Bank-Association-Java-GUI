/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import CLIENT_VIEW.CLASS_PANES;
import CLIENT_VIEW.CLASS_QNUM_CONSTRUCTOR;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author reyes
 */
public class LoanService extends javax.swing.JPanel {
    
    
    private Frame frame;
    public static String acc;
    
    public LoanService(Frame frame) {
        initComponents();
        limits();
        setDoubleBuffered(true);  
        this.frame = frame;
    }
    
    public void forward(String Username, String Password){
       
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tbaqs", "root", ""
        );

        String sql = "SELECT Acc_number FROM New_Registered_User WHERE Username = ? AND Password = ?";
        java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, Username);
        pstmt.setString(2, Password);

        java.sql.ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("I'm working");
            System.out.println("USER FOUND");

                 acc = rs.getString("Acc_number");
           
                 CLIENT_VIEW.Transactions.acc_num = acc;
                 CLIENT_VIEW.Transactions.queue_bal = Frame.bal;
                 CLIENT_VIEW.Transactions.QN= NAME.getText();
                
                 verifyUser(NAME.getText(), PASSWORD.getText());
                 System.out.println(CLIENT_VIEW.Transactions.QN);
                 System.out.println(CLIENT_VIEW.Transactions.acc_num);
                 
                 Frame.bal =0;
                 
                 
          
               
            
        } else {
            System.out.println("USER NOT FOUND!");
           CLASS_PANES.showMessageBox("Invalid Username or Password!");
        }
        
           rs.close();
          pstmt.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
       CLASS_PANES.showMessageBox("Error: " + e.getMessage());
    }
    }
    
    
    public String generateQueueNumber(String username, String mb, String transactionType) {
    String newQueueNum = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tbaqs", "root", ""
        );

        // 🔹 Get last queue number but only for this transaction type
        String sql = "SELECT QueNum FROM quenum WHERE Transaction_Type = ? ORDER BY ID DESC LIMIT 1";
        java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, transactionType);
        java.sql.ResultSet rs = pstmt.executeQuery();

        int nextNum = 1;
        if (rs.next()) {
            String lastQue = rs.getString("QueNum"); // e.g. "W - 011"
            if (lastQue != null && lastQue.contains(" - ")) {
                String[] parts = lastQue.split(" - ");
                nextNum = Integer.parseInt(parts[1]) + 1;
            }
        }
        
        

        // 🔹 Get prefix based on transaction type (W for Withdraw, L for Loan)
        String prefix = transactionType.substring(0, 1).toUpperCase();

        // 🔹 Format new queue number
        newQueueNum = String.format("%s - %03d", prefix, nextNum);

        // 🔹 Insert new record into table
        
          
         LocalDate date = LocalDate.now();
         LocalTime time = LocalTime.now();
         DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
         DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");
         String mdy = date.format(d_format);
         String hms = time.format(t_format);
        String insertSql = "INSERT INTO quenum (Username, MobileNum, QueNum, Transaction_Type, Amount, Acc_number, Date, Time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.PreparedStatement insertPstmt = con.prepareStatement(insertSql);
        insertPstmt.setString(1, username);
        insertPstmt.setString(2, mb);
        insertPstmt.setString(3, newQueueNum);
        insertPstmt.setString(4, transactionType);
        insertPstmt.setString(5,String.valueOf(Frame.bal));
        insertPstmt.setString(6, acc);
        insertPstmt.setString(7, mdy);
        insertPstmt.setString(8, hms);
        
        insertPstmt.executeUpdate();
        

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
       CLASS_PANES.showMessageBox("Error: " + e.getMessage());
    }

    return newQueueNum;
}


public CLASS_QNUM_CONSTRUCTOR verifyUser(String username, String password) {
    CLASS_QNUM_CONSTRUCTOR result = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tbaqs", "root", ""
        );

        String sql = "SELECT * FROM New_Registered_User WHERE Username = ? AND Password = ?";
        java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        java.sql.ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("USER FOUND");
            CLASS_PANES.showMessageBox("Verification Successful! You can now get your Queue Number.");
            String mobile =rs.getString("MobileNum");
            
            String queNum = generateQueueNumber(username,mobile, CLASS_QNUM_CONSTRUCTOR.data.get(1));

            ArrayList<String> info = new ArrayList<>();
            info.add(queNum);      // Queue Number
            info.add(CLASS_QNUM_CONSTRUCTOR.data.get(1));  // Transaction Type
            info.add(CLASS_QNUM_CONSTRUCTOR.data.get(2));         // Counter (static for now)

            result = new CLASS_QNUM_CONSTRUCTOR(info);
            
            Timer timer = new Timer(3000, e -> {
            frame.setContentPane(new QueueTicket(frame));
            frame.revalidate();
            });
            timer.setRepeats(false); 
            timer.start();
                 NAME.setText(null); PASSWORD.setText(null);
        } else {
            System.out.println("USER NOT FOUND!");
           CLASS_PANES.showMessageBox("Invalid Username or Password!");
            return null; 
        }

        con.close();
    } catch (Exception e) {
        e.printStackTrace();
       CLASS_PANES.showMessageBox("Error: " + e.getMessage());
    }

    return result; 
}


    //this limits all the input (Acts as guide for the user
    public void limits(){
    
            PASSWORD.setDocument(new javax.swing.text.PlainDocument() {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException {
            if (str == null) return;

            String newText = getText(0, getLength()) + str;

            if (newText.length() <= 8 && newText.matches("[0-9]*")) {
                super.insertString(offs, str, a);
            } else if(! newText.matches("[0-9]*")) {
                CLASS_PANES.showMessageBox("DIGITS ONLY");
            } 
        }
    });
     NAME.setDocument(new javax.swing.text.PlainDocument() {
    @Override
    public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
            throws javax.swing.text.BadLocationException {
        if (str == null) return;

        String newText = getText(0, getLength());
        newText = newText.substring(0, offs) + str + newText.substring(offs);

        // ✅ Fixed regex — allows letters, spaces, commas, dashes, and only one dot
        if (newText.matches("^[A-Za-z,\\-\\s]*\\.?[A-Za-z,\\-\\s]*$")) {
            super.insertString(offs, str, a);
        } else {
         
        }
    }
});

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BACK = new javax.swing.JButton();
        CANCEL = new javax.swing.JButton();
        CONFIRM = new javax.swing.JButton();
        NAME = new javax.swing.JTextField();
        PASSWORD = new javax.swing.JPasswordField();
        background = new javax.swing.JLabel();

        setLayout(null);

        BACK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BU.png"))); // NOI18N
        BACK.setBorderPainted(false);
        BACK.setContentAreaFilled(false);
        BACK.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BC.png"))); // NOI18N
        BACK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BC.png"))); // NOI18N
        BACK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BC.png"))); // NOI18N
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });
        add(BACK);
        BACK.setBounds(24, 175, 57, 57);

        CANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLU.png"))); // NOI18N
        CANCEL.setBorderPainted(false);
        CANCEL.setContentAreaFilled(false);
        CANCEL.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });
        add(CANCEL);
        CANCEL.setBounds(377, 529, 199, 51);

        CONFIRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFU.png"))); // NOI18N
        CONFIRM.setBorderPainted(false);
        CONFIRM.setContentAreaFilled(false);
        CONFIRM.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONFIRMActionPerformed(evt);
            }
        });
        add(CONFIRM);
        CONFIRM.setBounds(644, 529, 199, 51);

        NAME.setBackground(new java.awt.Color(255, 255, 255));
        NAME.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        NAME.setBorder(null);
        NAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAMEActionPerformed(evt);
            }
        });
        add(NAME);
        NAME.setBounds(547, 434, 350, 25);

        PASSWORD.setBackground(new java.awt.Color(255, 255, 255));
        PASSWORD.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        PASSWORD.setBorder(null);
        add(PASSWORD);
        PASSWORD.setBounds(547, 482, 350, 25);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AC_VERIFY.png"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed

    private void CONFIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONFIRMActionPerformed
         
        
        String name = NAME.getText();
        String pass = PASSWORD.getText();
        
         try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tbaqs", "root", ""
        );

        String sql = "SELECT * FROM quenum WHERE Username = ? ";
        java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
       
        java.sql.ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
           CLASS_PANES.showMessageBox("       You already have an active transaction.\nPlease finish it before getting another transaction.");
            NAME.setText(null);PASSWORD.setText(null);
            NAME.setEditable(false);PASSWORD.setEditable(false);
            CONFIRM.setEnabled(false);CANCEL.setEnabled(false);BACK.setEnabled(false);
            
            Timer timer2 = new Timer(4000, e -> {
                frame.setContentPane(new Transactions(frame));
                frame.revalidate();
            });
            timer2.setRepeats(false); 
            timer2.start();

            
        } else {
                forward(NAME.getText(), PASSWORD.getText());
        }
        rs.close();
        pstmt.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
       CLASS_PANES.showMessageBox("Error: " + e.getMessage());
    }

        
        
    }//GEN-LAST:event_CONFIRMActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed
       
        NAME.setText(null); PASSWORD.setText(null); 
        
    }//GEN-LAST:event_CANCELActionPerformed

    private void NAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NAMEActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JButton CANCEL;
    private javax.swing.JButton CONFIRM;
    private javax.swing.JTextField NAME;
    private javax.swing.JPasswordField PASSWORD;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}

// instead of username, age, mobilenum, quenum, transac_type, and id and ilalagay sa database
// this nalang: username, account no., quenum, transac_type, amount, and id nalang


