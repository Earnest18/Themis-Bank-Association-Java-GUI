/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author reyes
 */
public class RegisterEmployee extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    public static String uname;
    public static String employeeID;
    public RegisterEmployee(CLIENT_VIEW.Frame frame) {
        initComponents();
        limits();
        this.frame = frame;
        GENDER.setBorder(null);STATUS.setBorder(null);POSITION.setBorder(null);
    }
    
    public void registerEmployee(String YEAR, String name, String uname, String age, String bd, String gen, String mbn, String ema, String stat, String pass, String pos) {
        
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tbaqs", "root", "")) {

            // 1. Get current year
            //int year = java.time.Year.now().getValue();

            // 2. Count how many employees exist for this year
            String countSql = "SELECT COUNT(*) AS total FROM new_registered_employee WHERE employee_id LIKE ?";
            int current_rows = 0;
            try (java.sql.PreparedStatement psCount = con.prepareStatement(countSql)) {
                psCount.setString(1, YEAR + "-%"); // e.g., "2025-%"
                try (java.sql.ResultSet rs = psCount.executeQuery()) {
                    if (rs.next()) {
                        current_rows = rs.getInt("total");
                    }
                }
            }

            // 3. Generate new employee ID
            int newID = current_rows + 1;
            employeeID = YEAR + "-" + String.format("%03d", newID);

        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
   
    try {
        // Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tbaqs", "root", "");
             java.sql.PreparedStatement ps = con.prepareStatement(
                "INSERT INTO new_registered_employee (Employee_ID,Name, Username, Age, Birthday, Gender, MobileNum, Email, Status, Password, Position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, employeeID);
            ps.setString(2, name);
            ps.setString(3, uname);
            ps.setString(4, age);
            ps.setString(5, bd);
            ps.setString(6, gen);
            ps.setString(7, mbn);
            ps.setString(8, ema);
            ps.setString(9, stat);
            ps.setString(10, pass);
            ps.setString(11,pos);

            // Execute insert
            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Employee registered successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed!");
            }
        }

    } catch (Exception p) {
        p.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + p.getMessage());
    }
}

    public void limits() {
    
    // USERNAME: allow only letters, 3–15 characters
    EMAILADD.setDocument(new javax.swing.text.PlainDocument() {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException {
            if (str == null) return;

            String newText = getText(0, getLength()) + str;

            if (newText.length() <= 30) {
                super.insertString(offs, str, a);
            } else {
              
            }
        }
    });

    // PASSWORD: exactly 5 digits (numbers only)
    PINCODE.setDocument(new javax.swing.text.PlainDocument() {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException {
            if (str == null) return;

            String newText = getText(0, getLength()) + str;

            if (newText.length() <= 8 && newText.matches("[0-9]*")) {
                super.insertString(offs, str, a);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Password must be exactly 5 digits (numbers only).");
            }
        }
    });
    
    // PASSWORD: exactly 5 digits (numbers only)
    MOBILENO.setDocument(new javax.swing.text.PlainDocument() {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException {
            if (str == null) return;

            String newText = getText(0, getLength()) + str;

            if (newText.length() <= 11 && newText.matches("[0-9]*")) {
                super.insertString(offs, str, a);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Mobile no. should be exactly 11 digits.");
            }
        }
    });
}


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MOBILENO = new javax.swing.JTextField();
        EMAILADD = new javax.swing.JTextField();
        NAME = new javax.swing.JTextField();
        CLEAR1 = new javax.swing.JButton();
        LOGIN = new javax.swing.JButton();
        BACK = new javax.swing.JButton();
        PINCODE = new javax.swing.JPasswordField();
        GENDER = new javax.swing.JComboBox<>();
        STATUS = new javax.swing.JComboBox<>();
        POSITION = new javax.swing.JComboBox<>();
        BD = new com.toedter.calendar.JDateChooser();
        background = new javax.swing.JLabel();

        setLayout(null);

        MOBILENO.setBackground(null);
        MOBILENO.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        MOBILENO.setForeground(new java.awt.Color(0, 0, 0));
        MOBILENO.setBorder(null);
        add(MOBILENO);
        MOBILENO.setBounds(313, 473, 290, 27);

        EMAILADD.setBackground(null);
        EMAILADD.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        EMAILADD.setForeground(new java.awt.Color(0, 0, 0));
        EMAILADD.setBorder(null);
        add(EMAILADD);
        EMAILADD.setBounds(707, 473, 420, 27);

        NAME.setBackground(null);
        NAME.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        NAME.setForeground(new java.awt.Color(0, 0, 0));
        NAME.setBorder(null);
        add(NAME);
        NAME.setBounds(313, 373, 810, 27);

        CLEAR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLU.png"))); // NOI18N
        CLEAR1.setBorderPainted(false);
        CLEAR1.setContentAreaFilled(false);
        CLEAR1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CLEAR1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CLC.png"))); // NOI18N
        CLEAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEAR1ActionPerformed(evt);
            }
        });
        add(CLEAR1);
        CLEAR1.setBounds(697, 585, 198, 51);

        LOGIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFU.png"))); // NOI18N
        LOGIN.setBorderPainted(false);
        LOGIN.setContentAreaFilled(false);
        LOGIN.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        LOGIN.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/CFC.png"))); // NOI18N
        LOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINActionPerformed(evt);
            }
        });
        add(LOGIN);
        LOGIN.setBounds(966, 585, 198, 51);

        BACK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HU.png"))); // NOI18N
        BACK.setBorderPainted(false);
        BACK.setContentAreaFilled(false);
        BACK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HC.png"))); // NOI18N
        BACK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/HC.png"))); // NOI18N
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });
        add(BACK);
        BACK.setBounds(23, 175, 57, 57);

        PINCODE.setBackground(null);
        PINCODE.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        PINCODE.setForeground(new java.awt.Color(0, 0, 0));
        PINCODE.setBorder(null);
        add(PINCODE);
        PINCODE.setBounds(707, 523, 420, 27);

        GENDER.setBackground(null);
        GENDER.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        GENDER.setForeground(new java.awt.Color(0, 0, 0));
        GENDER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Male", "Female", "Prefer not to say" }));
        GENDER.setBorder(null);
        add(GENDER);
        GENDER.setBounds(313, 424, 130, 27);

        STATUS.setBackground(null);
        STATUS.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        STATUS.setForeground(new java.awt.Color(0, 0, 0));
        STATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Single", "Married", "Widowed", "Separated" }));
        STATUS.setBorder(null);
        add(STATUS);
        STATUS.setBounds(553, 424, 220, 27);

        POSITION.setBackground(null);
        POSITION.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        POSITION.setForeground(new java.awt.Color(0, 0, 0));
        POSITION.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Withdraw | Counter 1", "Deposit | Counter 2", "Loan Service | Counter 3", "Inquiry | Counter 4" }));
        POSITION.setBorder(null);
        add(POSITION);
        POSITION.setBounds(313, 523, 260, 27);

        BD.setForeground(new java.awt.Color(0, 0, 0));
        BD.setFont(new java.awt.Font("Nirmala UI", 0, 15)); // NOI18N
        add(BD);
        BD.setBounds(926, 424, 200, 27);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AD_AAE.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void CLEAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEAR1ActionPerformed
        NAME.setText(null); PINCODE.setText(null);
    }//GEN-LAST:event_CLEAR1ActionPerformed

    private void LOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINActionPerformed
    
        if(GENDER.getSelectedIndex() !=0){
                if(STATUS.getSelectedIndex() !=0){      
                    if(POSITION.getSelectedIndex() !=0){
                                                        if(EMAILADD.getText().contains("@gmail.com")){
                                                    LocalDate today = LocalDate.now();
                                                    Date selectedDate = BD.getDate();  
                                                    LocalDate birthDate = selectedDate.toInstant()
                                                                                      .atZone(ZoneId.systemDefault())
                                                                                      .toLocalDate();
                                                    SimpleDateFormat formatdate = new SimpleDateFormat("MM-dd-YYYY");

                                                    // Convert JDateChooser value to LocalDate
                                                    if (selectedDate == null) {
                                                        JOptionPane.showMessageDialog(null, "Please select a date of birth.");
                                                        return;
                                                    }


                                                    // Check if DOB is after today (future date = invalid)
                                                     if (!birthDate.isAfter(today)) {
                                                          int age = Period.between(birthDate, today).getYears();
                                                    if (age >= 18) {
                                                        
                                                            int choose = JOptionPane.showConfirmDialog(null, " Are you sure you want to register this employee?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
                                           
                                                            if(choose == JOptionPane.YES_OPTION){
                                                            String year = String.valueOf(today.getYear());
                                                            String bd = formatdate.format(selectedDate);
                                                            String inputname = NAME.getText();
                                                            String inputgen = String.valueOf(GENDER.getSelectedItem());
                                                            String inputBD = bd;
                                                            String agee = String.valueOf(age);
                                                            String mobile = MOBILENO.getText();
                                                            String stats = String.valueOf(STATUS.getSelectedItem());
                                                            String email = EMAILADD.getText();
                                                            String position = String.valueOf(POSITION.getSelectedItem());
                                                            String pin = PINCODE.getText();
                                                            String username = inputname.substring(0, inputname.indexOf(" "))  + bd.substring(6);
                                                            registerEmployee(year,inputname, username,agee,inputBD, inputgen, mobile, email, stats, pin, position);
                                                            }else{
                                                            
                                                                
                                                                
                                                            
                                                            
                                                            
                                                            
                                                            }




                                                    }else{
                                                        JOptionPane.showMessageDialog(null, "Your age is not eligible to open an account!");
                                                        return;
                                                    }


                                                    }
                                                    if (birthDate.isAfter(today)) {
                                                        JOptionPane.showMessageDialog(null, "Invalid Date! Birthdate cannot be in the future.");
                                                        return;
                                                    }
                        }else{
                         System.out.print("Invalid gmail format");                         
                     }
                    }else{
                      System.out.print("Please select position");
                    }
                }else{
                System.out.print("Please select status");
                }
        }else{
            System.out.print("Please select gender");
        
        }
    }//GEN-LAST:event_LOGINActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private com.toedter.calendar.JDateChooser BD;
    private javax.swing.JButton CLEAR1;
    private javax.swing.JTextField EMAILADD;
    private javax.swing.JComboBox<String> GENDER;
    private javax.swing.JButton LOGIN;
    private javax.swing.JTextField MOBILENO;
    private javax.swing.JTextField NAME;
    private javax.swing.JPasswordField PINCODE;
    private javax.swing.JComboBox<String> POSITION;
    private javax.swing.JComboBox<String> STATUS;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
