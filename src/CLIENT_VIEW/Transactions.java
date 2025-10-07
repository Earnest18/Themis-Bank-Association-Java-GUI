/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import CLIENT_VIEW.CLASS_QNUM_CONSTRUCTOR;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author reyes
 */
public class Transactions extends javax.swing.JPanel {

    private Frame frame;
    public static int queue_bal;
    public static String QN;
    public static String acc_num;

    public Transactions(Frame frame) {
        initComponents();
        setDoubleBuffered(true);
        background.setDoubleBuffered(true);
        this.frame = frame;

        KeyCombinations();
    }

    public String generateQueueNumber(String username, String transactionType) {
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
            String insertSql = "INSERT INTO quenum (Username, MobileNum, QueNum, Transaction_Type, Amount, Acc_number, Date, Time) VALUES (?, ?, ?, ?, ?, ?,?,?)";
            java.sql.PreparedStatement insertPstmt = con.prepareStatement(insertSql);
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            DateTimeFormatter d_format = DateTimeFormatter.ofPattern("MM-dd-YYYY");
            DateTimeFormatter t_format = DateTimeFormatter.ofPattern("hh:mm a");
            String mdy = date.format(d_format);
            String hms = time.format(t_format);
            insertPstmt.setString(1, username);
            insertPstmt.setString(2, "-----");
            insertPstmt.setString(3, newQueueNum);
            insertPstmt.setString(4, transactionType);
            insertPstmt.setString(5, "-----");
            insertPstmt.setString(6, "-----");
            insertPstmt.setString(7, mdy);
            insertPstmt.setString(8, hms);

            insertPstmt.executeUpdate();

            con.close();

            // 🔹 Store in QUE_NUM_CONSTRUCTOR so labels can use it
            ArrayList<String> info = new ArrayList<>();
            info.add(newQueueNum);      // Queue Number
            info.add("INQUIRE");  // Transaction Type
            info.add("1");      // Example counter (change if dynamic)

            CLASS_QNUM_CONSTRUCTOR.data = info;  // <-- Make sure `data` is static or accessible

            rs.close();
            con.close();
            insertPstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return newQueueNum;
    }

    /**
     *
     */
    public void KeyCombinations() {
        SwingUtilities.invokeLater(() -> {
            JRootPane RP = this.getRootPane();

            if (RP == null) {
                System.err.println("RootPane not initialized yet!");
                return;
            }

            RP.setFocusable(true);
            RP.requestFocusInWindow(); // force focus on root pane

            RP.addKeyListener(new KeyAdapter() {
                // admin shortcut
                private boolean shiftPressed = false;
                private boolean aPressed = false;
                private boolean dPressed = false;

                // employee shortcut
                private boolean ePressed = false;
                private boolean mPressed = false;

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                        shiftPressed = true;
                    }

                    // admin keys
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        aPressed = true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D) {
                        dPressed = true;
                        if (shiftPressed && aPressed && dPressed) {
                            System.out.print("You Entered admin view!");
                            frame.setContentPane(new ADMIN_VIEW.Login(frame));
                            System.out.print("You entered admin field");
                            frame.revalidate();
                            resetKeys();
                        }
                    }

                    // employee keys
                    if (e.getKeyCode() == KeyEvent.VK_E) {
                        ePressed = true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_M) {
                        mPressed = true;
                        if (shiftPressed && ePressed && mPressed) {
                            SwingUtilities.invokeLater(() -> {
                                System.out.print("\nYou entered employee field");
                                frame.setContentPane(new EMPLOYEE_VIEW.Log(frame));
                                frame.revalidate();
                                resetKeys();
                            });

                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                        shiftPressed = false;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        aPressed = false;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D) {
                        dPressed = false;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_E) {
                        ePressed = false;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_M) {
                        mPressed = false;
                    }
                }

                private void resetKeys() {
                    shiftPressed = false;
                    aPressed = false;
                    dPressed = false;
                    ePressed = false;
                    mPressed = false;
                }
            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LOAN = new javax.swing.JButton();
        DEPOSIT = new javax.swing.JButton();
        INQUIRE = new javax.swing.JButton();
        WITHDRAW = new javax.swing.JButton();
        FAQS = new javax.swing.JButton();
        CREATE_ACC = new javax.swing.JButton();
        ABT = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(null);

        LOAN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/LSU.png"))); // NOI18N
        LOAN.setBorderPainted(false);
        LOAN.setContentAreaFilled(false);
        LOAN.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/LSC.png"))); // NOI18N
        LOAN.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/LSC.png"))); // NOI18N
        LOAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOANActionPerformed(evt);
            }
        });
        add(LOAN);
        LOAN.setBounds(40, 434, 368, 95);

        DEPOSIT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/DPU.png"))); // NOI18N
        DEPOSIT.setBorderPainted(false);
        DEPOSIT.setContentAreaFilled(false);
        DEPOSIT.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/DPC.png"))); // NOI18N
        DEPOSIT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/DPC.png"))); // NOI18N
        DEPOSIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DEPOSITActionPerformed(evt);
            }
        });
        add(DEPOSIT);
        DEPOSIT.setBounds(891, 275, 368, 95);

        INQUIRE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/IQU.png"))); // NOI18N
        INQUIRE.setBorderPainted(false);
        INQUIRE.setContentAreaFilled(false);
        INQUIRE.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/IQC.png"))); // NOI18N
        INQUIRE.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/IQC.png"))); // NOI18N
        INQUIRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INQUIREActionPerformed(evt);
            }
        });
        add(INQUIRE);
        INQUIRE.setBounds(891, 434, 368, 95);

        WITHDRAW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/WDU.png"))); // NOI18N
        WITHDRAW.setBorderPainted(false);
        WITHDRAW.setContentAreaFilled(false);
        WITHDRAW.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/WDC.png"))); // NOI18N
        WITHDRAW.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/WDC.png"))); // NOI18N
        WITHDRAW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WITHDRAWActionPerformed(evt);
            }
        });
        add(WITHDRAW);
        WITHDRAW.setBounds(40, 275, 368, 95);

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

        CREATE_ACC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CAU.png"))); // NOI18N
        CREATE_ACC.setBorderPainted(false);
        CREATE_ACC.setContentAreaFilled(false);
        CREATE_ACC.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CAC.png"))); // NOI18N
        CREATE_ACC.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CAC.png"))); // NOI18N
        CREATE_ACC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CREATE_ACCActionPerformed(evt);
            }
        });
        add(CREATE_ACC);
        CREATE_ACC.setBounds(1058, 116, 212, 35);

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
        ABT.setBounds(769, 117, 144, 35);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AC_MAIN.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void ABTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ABTActionPerformed
        SwingUtilities.invokeLater(() -> {
            frame.setContentPane(new CLIENT_VIEW.About(frame));
            frame.revalidate();
        });
    }//GEN-LAST:event_ABTActionPerformed

    private void FAQSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FAQSActionPerformed
        SwingUtilities.invokeLater(() -> {
            frame.setContentPane(new CLIENT_VIEW.Faqs(frame));
            frame.revalidate();
        });

    }//GEN-LAST:event_FAQSActionPerformed

    private void CREATE_ACCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CREATE_ACCActionPerformed
        SwingUtilities.invokeLater(() -> {

            frame.setContentPane(new CreateAccount1(frame));
            frame.revalidate();
        });

    }//GEN-LAST:event_CREATE_ACCActionPerformed

    private void WITHDRAWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WITHDRAWActionPerformed
        SwingUtilities.invokeLater(() -> {
            ArrayList<String> data = new ArrayList<>();
            data.add("W - 001");      // queue number
            data.add("Withdraw");     // type
            data.add("1");            // counter

            CLASS_QNUM_CONSTRUCTOR qc = new CLASS_QNUM_CONSTRUCTOR(data);

            Timer t = new Timer(50, e -> {
                frame.setContentPane(new Withdraw1(frame));
                frame.revalidate();
                frame.repaint();
            });
            t.setRepeats(false);
            t.start();

        });

    }//GEN-LAST:event_WITHDRAWActionPerformed

    private void DEPOSITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DEPOSITActionPerformed
        SwingUtilities.invokeLater(() -> {
            ArrayList<String> data = new ArrayList<>();
            data.add("D- 001");      // queue number
            data.add("Deposit");     // type
            data.add("2");            // counter

            CLASS_QNUM_CONSTRUCTOR qc = new CLASS_QNUM_CONSTRUCTOR(data);

            frame.setContentPane(new Deposit1(frame));
            frame.revalidate();
        });

    }//GEN-LAST:event_DEPOSITActionPerformed

    private void LOANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOANActionPerformed
        SwingUtilities.invokeLater(() -> {
            ArrayList<String> data = new ArrayList<>();
            data.add("L- 001");      // queue number
            data.add("Loan");     // type
            data.add("3");            // counter

            CLASS_QNUM_CONSTRUCTOR qc = new CLASS_QNUM_CONSTRUCTOR(data);

            frame.setContentPane(new LoanService(frame));
            frame.revalidate();
        });

    }//GEN-LAST:event_LOANActionPerformed

    private void INQUIREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INQUIREActionPerformed
        SwingUtilities.invokeLater(() -> {
            generateQueueNumber("----", "Inquire");

            frame.setContentPane(new QueueTicket(frame));
            frame.revalidate();
        });

    }//GEN-LAST:event_INQUIREActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ABT;
    private javax.swing.JButton CREATE_ACC;
    private javax.swing.JButton DEPOSIT;
    private javax.swing.JButton FAQS;
    private javax.swing.JButton INQUIRE;
    private javax.swing.JButton LOAN;
    private javax.swing.JButton WITHDRAW;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
