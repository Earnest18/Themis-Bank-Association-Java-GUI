/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import CLIENT_VIEW.CLASS_PANES;
import CLIENT_VIEW.CLASS_QNUM_CONSTRUCTOR;
import static CLIENT_VIEW.CreateAccount1.AGE_VALUE;
import static CLIENT_VIEW.CreateAccount1.BD_VALUE;
import static CLIENT_VIEW.CreateAccount1.EMAIL_VALUE;
import static CLIENT_VIEW.CreateAccount1.MOBILENUM_VALUE;
import static CLIENT_VIEW.CreateAccount1.NAME_VALUE;
import static CLIENT_VIEW.CreateAccount1.PASSWORD_VALUE;
import static CLIENT_VIEW.CreateAccount1.DATE_VALUE;

import java.sql.DriverManager;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CreateAccount2 extends javax.swing.JPanel {

    private Frame frame;
    public static int start_bal;
    public static String raw;
    //public static  String formatted;
    public static int caretPos;

    public CreateAccount2(Frame frame) {
        initComponents();
        setDoubleBuffered(true);
        this.frame = frame;
        attachAmountFormatter();

    }

    private void attachAmountFormatter() {
        AMOUNT.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private boolean isUpdating = false;

            private void formatText() {
                if (isUpdating) {
                    return;
                }

                raw = AMOUNT.getText().replaceAll("[^\\d]", "");
                if (raw.isEmpty()) {
                    return;
                }

                try {
                    long value = Long.parseLong(raw);

                    NumberFormat pesoFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
                    pesoFormat.setMaximumFractionDigits(0);

                    String formatted = pesoFormat.format(value);
                    formatted = formatted.replaceAll("(?i)PHP", "₱");

                    if (!AMOUNT.getText().equals(formatted)) {
                        isUpdating = true; // mark updating
                        String finalFormatted = formatted; // 👈 make effectively final for lambda

                        SwingUtilities.invokeLater(() -> {
                            AMOUNT.setText(finalFormatted);
                            AMOUNT.setCaretPosition(AMOUNT.getDocument().getLength()); // cursor at end
                            isUpdating = false; // 👈 allowed now (field, not local var)
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                formatText();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                formatText();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                formatText();
            }
        });
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

            String prefix = transactionType.substring(0, 1).toUpperCase();

            newQueueNum = String.format("%s - %03d", prefix, nextNum);

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
            DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");
            String mdy = date.format(d_format);
            String hms = time.format(t_format);

            String insertSql = "INSERT INTO quenum (Username, MobileNum, QueNum, Transaction_Type, Amount, Acc_number, Date, Time) VALUES (?, ?, ?, ?, ?, ?,?,?)";
            java.sql.PreparedStatement insertPstmt = con.prepareStatement(insertSql);
            insertPstmt.setString(1, username);
            insertPstmt.setString(2, mb);
            insertPstmt.setString(3, newQueueNum);
            insertPstmt.setString(4, transactionType);
            insertPstmt.setString(5, String.valueOf(Frame.bal));
            insertPstmt.setString(6, " ");
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

    public void insertClientInfo(String username, String bday, String age, String mobileNum, String mail, String password, int AMOUNT) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tbaqs", "root", ""
            );

            String insertSql = "INSERT INTO pending_users ( Username, Birthday, Age, MobileNum, Password, Amount, Email, status) VALUES (?, ?, ?, ?, ?,?,?,?)";
            java.sql.PreparedStatement pstmt = con.prepareStatement(insertSql);
            pstmt.setString(1, NAME_VALUE);
            pstmt.setString(2, BD_VALUE);
            pstmt.setString(3, AGE_VALUE); // change its datatype sa database from int to varchar
            pstmt.setString(4, MOBILENUM_VALUE);
            pstmt.setString(5, PASSWORD_VALUE);  // change its datatype sa database from int to varchar
            pstmt.setInt(6, start_bal);
            pstmt.setString(7, EMAIL_VALUE);
            pstmt.setString(8, "unpaid");

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.print("\nUser in!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();

            CLASS_PANES.showMessageBox("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AMOUNT = new javax.swing.JTextField();
        CANCEL = new javax.swing.JButton();
        CONFIRM = new javax.swing.JButton();
        BACK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        AMOUNT.setBackground(new java.awt.Color(255, 255, 255));
        AMOUNT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AMOUNT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AMOUNT.setBorder(null);
        AMOUNT.setOpaque(true);
        AMOUNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AMOUNTActionPerformed(evt);
            }
        });
        add(AMOUNT);
        AMOUNT.setBounds(388, 440, 450, 40);

        CANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLU.png"))); // NOI18N
        CANCEL.setBorderPainted(false);
        CANCEL.setContentAreaFilled(false);
        CANCEL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });
        add(CANCEL);
        CANCEL.setBounds(376, 529, 198, 51);

        CONFIRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFU.png"))); // NOI18N
        CONFIRM.setBorderPainted(false);
        CONFIRM.setContentAreaFilled(false);
        CONFIRM.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        CONFIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONFIRMActionPerformed(evt);
            }
        });
        add(CONFIRM);
        CONFIRM.setBounds(643, 529, 198, 51);

        BACK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BU.png"))); // NOI18N
        BACK.setBorderPainted(false);
        BACK.setContentAreaFilled(false);
        BACK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BC.png"))); // NOI18N
        BACK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/BC.png"))); // NOI18N
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });
        add(BACK);
        BACK.setBounds(24, 175, 57, 57);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AC_CAC1.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void AMOUNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AMOUNTActionPerformed

    }//GEN-LAST:event_AMOUNTActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed

        AMOUNT.setText(null);
    }//GEN-LAST:event_CANCELActionPerformed

    private void CONFIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONFIRMActionPerformed
        CLASS_QNUM_CONSTRUCTOR result = null;
        start_bal = Integer.parseInt(raw);
        if (start_bal > 500 && start_bal <= 10000000) {

            if (start_bal % 100 == 0) {

                int choose = CLASS_PANES.showConfirmBox("Are you sure you want to deposit an amount of " + AMOUNT.getText() + " to your account?");

                if (choose == JOptionPane.YES_OPTION) {
                    ArrayList<String> data = new ArrayList<>();
                    data.add("I- 001");      // queue number
                    data.add("Inquire");     // type
                    data.add("4");            // counter
                    CLASS_QNUM_CONSTRUCTOR qc = new CLASS_QNUM_CONSTRUCTOR(data);

                    CLASS_PANES.showMessageBox("Thank you! You may now proceed to Counter 4 for the payment of your initial balance.");
                    insertClientInfo(NAME_VALUE, BD_VALUE, AGE_VALUE, MOBILENUM_VALUE, EMAIL_VALUE, PASSWORD_VALUE, start_bal);
                    String queNum = generateQueueNumber(NAME_VALUE, MOBILENUM_VALUE, CLASS_QNUM_CONSTRUCTOR.data.get(1));
                    ArrayList<String> info = new ArrayList<>();
                    info.add(queNum);      // Queue Number
                    info.add(CLASS_QNUM_CONSTRUCTOR.data.get(1));  // Transaction Type
                    info.add(CLASS_QNUM_CONSTRUCTOR.data.get(2));         // Counter (static for now)

                    result = new CLASS_QNUM_CONSTRUCTOR(info);

                    SwingUtilities.invokeLater(() -> {

                        frame.setContentPane(new QueueTicket(frame));
                        frame.revalidate();
                    });
                    NAME_VALUE = null;
                    BD_VALUE = null;
                    AGE_VALUE = null;
                    MOBILENUM_VALUE = null;
                    PASSWORD_VALUE = null;
                    EMAIL_VALUE = null;
                    DATE_VALUE = null;
                    start_bal = 0;

                    CLIENT_VIEW.CreateAccount1.NAME.setText(null);
                    CLIENT_VIEW.CreateAccount1.MOBILENUM.setText(null);
                    CLIENT_VIEW.CreateAccount1.PASSWORD.setText(null);
                    CLIENT_VIEW.CreateAccount1.BD.setDate(null);
                    CLIENT_VIEW.CreateAccount1.EMAIL.setText(null);

                }
            } else {
                CLASS_PANES.showMessageBox("Deposited amount should be divisible by 100 Php");

            }

        } else {
        }
    }//GEN-LAST:event_CONFIRMActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed

        frame.setContentPane(new CreateAccount1(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AMOUNT;
    private javax.swing.JButton BACK;
    private javax.swing.JButton CANCEL;
    private javax.swing.JButton CONFIRM;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
