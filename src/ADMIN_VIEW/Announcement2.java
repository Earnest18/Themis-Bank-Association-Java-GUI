/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ADMIN_VIEW;

import CLIENT_VIEW.CLASS_PANES;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Announcement2 extends javax.swing.JPanel {

    private CLIENT_VIEW.Frame frame;
    private final String url = "jdbc:mysql://localhost:3306/tbaqs";
    private final String user = "root";
    private final String pass = "";

    public Announcement2(CLIENT_VIEW.Frame frame) {
        initComponents();
        this.frame = frame;
        Sender.setText(Dashboard.EMAIL);
        messagebox.setText(Dashboard.MESSAGE);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BACK = new javax.swing.JButton();
        send = new javax.swing.JButton();
        Sender = new javax.swing.JLabel();
        messagebox = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        bg = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(null);

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

        send.setBorderPainted(false);
        send.setContentAreaFilled(false);
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        add(send);
        send.setBounds(1058, 530, 100, 33);

        Sender.setFont(new java.awt.Font("Nirmala UI", 1, 13)); // NOI18N
        Sender.setForeground(new Color(0x041a46));
        Sender.setText("cj11062006@gmail.com");
        add(Sender);
        Sender.setBounds(200, 301, 390, 20);

        messagebox.setFont(new java.awt.Font("Nirmala UI", 1, 13)); // NOI18N
        messagebox.setForeground(new Color(0x041a46));
        messagebox.setText("PUUUUUUUUUUTAAAAAAAAAAAAANNNNNNNNNNNNGGGIIIIIINAAAAAAAAAAAAAAAA NIIIIIIYPNNNNNNG LAAAHAAAAAAAAATTTTTTTTTTTT!!!!!!!!!");
        add(messagebox);
        messagebox.setBounds(138, 345, 1020, 32);

        jScrollPane1.setBackground(null);
        jScrollPane1.setBorder(null);
        jScrollPane1.setDoubleBuffered(true);

        jTextArea1.setBackground(null);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1);
        jScrollPane1.setBounds(140, 390, 1020, 140);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PANEL IMAGES/AD_CA_REP.gif"))); // NOI18N
        add(bg);
        bg.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_BACKActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed

        String email = Sender.getText().toString();
        System.out.print(email);
        String feedback = messagebox.getText().toString();
        String reply = jTextArea1.getText().toString();
        String usernames;
        System.out.print(reply);
        if (!reply.equals(null)) {

            String getusername = "SELECT Username FROM new_registered_user WHERE Email = ?";
            String notifSql = "INSERT INTO notifications (Username, title, message, type, created_at) VALUES (?, ?, ?, ?, ?)";

            int choice = CLASS_PANES.showConfirmBox("Are you sure you want to send a message to this account holder");
            if (choice == JOptionPane.YES_OPTION) {
                System.out.print("I'm Working");
                try (java.sql.Connection con = java.sql.DriverManager.getConnection(url, user, pass); java.sql.PreparedStatement psget = con.prepareStatement(getusername)) {
                    psget.setString(1, email);
                    try (java.sql.ResultSet rs = psget.executeQuery()) {
                        while (rs.next()) {
                            usernames = rs.getString("Username");
                            System.out.print("I'm Working :3");
                            java.sql.PreparedStatement psNotif = con.prepareStatement(notifSql);

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String DATETIME = LocalDateTime.now().format(formatter);

                            psNotif.setString(1, usernames);
                            psNotif.setString(2, "Announcement");
                            psNotif.setString(3, reply);
                            psNotif.setString(4, "info");
                            psNotif.setString(5, DATETIME);
                            int rowsAffected = psNotif.executeUpdate();
                            System.out.print("m Workingg");
                            CLASS_PANES.showMessageBox("The message has been sent to" + usernames + ".");
                            Sender.setText(null);
                            messagebox.setText(null);
                            jTextArea1.setText(null);

                            String sql = "DELETE FROM customer_service WHERE feedbacks = ? AND Gmail = ?";

                            try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

                                ps.setString(1, feedback);
                                ps.setString(2, email);
                                int rows = ps.executeUpdate();

                                if (rows > 0) {
                                } else {
                                    CLASS_PANES.showMessageBox("No matching records found to delete.");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                CLASS_PANES.showMessageBox("Error deleting record: " + e.getMessage());
                            }

                            frame.setContentPane(new ADMIN_VIEW.Dashboard(frame));
                            frame.revalidate();

                        }
                    }

                } catch (java.sql.SQLException lol) {
                    lol.printStackTrace();
                    CLASS_PANES.showMessageBox("❌ Notification insert failed: " + lol.getMessage());
                }
            } else if (choice == JOptionPane.NO_OPTION) {
                System.out.print("I'm Working Too");
            }

        } else {
            CLASS_PANES.showMessageBox("Message can not be empty");
        }

    }//GEN-LAST:event_sendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    public static javax.swing.JLabel Sender;
    private javax.swing.JLabel bg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel messagebox;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
}
