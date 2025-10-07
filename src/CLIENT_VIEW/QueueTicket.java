/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

import CLIENT_VIEW.CLASS_QNUM_CONSTRUCTOR;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.time.LocalDate;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.text.DateFormatter;


public class QueueTicket extends javax.swing.JPanel {
   
    private Frame frame;
    public QueueTicket(Frame frame) {
        initComponents();
        this.frame = frame;
        
        bal.setText(String.valueOf(Frame.bal));
        Que_num.setText(CLASS_QNUM_CONSTRUCTOR.data.get(0));
        transaction.setText(CLASS_QNUM_CONSTRUCTOR.data.get(1));
        if(transaction.getText().equalsIgnoreCase("INQUIRE")){
        counter.setText(" Counter 4");}
        else{
         counter.setText("Counter "+CLASS_QNUM_CONSTRUCTOR.data.get(2));}
        MyTextPane.setVisible(false);
        receipt.setOpaque(false);
        
        LocalTime pht =  LocalTime.now();
        LocalDate phd =  LocalDate.now();
        
        DateTimeFormatter d_format =  DateTimeFormatter.ofPattern("MM dd, YYYY");
        DateTimeFormatter t_format =  DateTimeFormatter.ofPattern("hh:mm a");
        
        String D = phd.format(d_format);
        String T = pht.format(t_format);
        
        DATE.setText(D);
        TIME.setText(T);
        
        
        
        
    }
    
   
    public void showAndPrintReceipt(JPanel parent, JPanel panel, JTextField area, String text) {
        TranText.setText( CLASS_QNUM_CONSTRUCTOR.data.get(1));
    String test = TranText.getText();
    if(test.equalsIgnoreCase("INQUIRE")){
        area.setText(text);
        TranText.setText("INQUIRE");
        CounterText.setText("4");
        ACCNAME.setText("---------------------------------------------------");
        ACCNO.setText("---------------------------------------------------");
        bal.setText("---------------------------------------------------");
    }else{
        area.setText(text);
        TranText.setText( CLASS_QNUM_CONSTRUCTOR.data.get(1));
        CounterText.setText(CLASS_QNUM_CONSTRUCTOR.data.get(2));
        ACCNAME.setText(CLIENT_VIEW.Transactions.QN);
        ACCNO.setText(CLIENT_VIEW.Transactions.acc_num);
        bal.setText("" + CLIENT_VIEW.Transactions.queue_bal);
    }
   
    int[] yPosition = {-500}; 

    int x = parent.getWidth() - panel.getWidth() - 130; 

    panel.setLocation(x, yPosition[0]);

    Timer timer = new Timer(25, e -> {
        yPosition[0] += 5; // move downward
        panel.setLocation(x, yPosition[0]);

        if (yPosition[0] >= 170) { // stop sliding at y=20
            ((Timer) e.getSource()).stop();
        }
    });

    timer.start();
}
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BACK = new javax.swing.JButton();
        Que_num = new javax.swing.JLabel();
        transaction = new javax.swing.JLabel();
        counter = new javax.swing.JLabel();
        MyTextPane = new javax.swing.JPanel();
        TranText = new javax.swing.JLabel();
        CounterText = new javax.swing.JLabel();
        bal = new javax.swing.JLabel();
        receipt = new javax.swing.JTextField();
        ACCNAME = new javax.swing.JLabel();
        ACCNO = new javax.swing.JLabel();
        DATE = new javax.swing.JLabel();
        TIME = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setLayout(null);

        BACK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/PCU.png"))); // NOI18N
        BACK.setBorderPainted(false);
        BACK.setContentAreaFilled(false);
        BACK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/PCC.png"))); // NOI18N
        BACK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/BUTTONS/PCC.png"))); // NOI18N
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });
        add(BACK);
        BACK.setBounds(500, 571, 199, 51);

        Que_num.setBackground(null);
        Que_num.setFont(new java.awt.Font("Nirmala UI", 1, 60)); // NOI18N
        Que_num.setForeground(new Color(0x041a46));
        Que_num.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Que_num.setText("WEHHH");
        add(Que_num);
        Que_num.setBounds(220, 320, 260, 90);

        transaction.setBackground(null);
        transaction.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        transaction.setForeground(new Color(0x041a46));
        transaction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        transaction.setText("DAPAT BLUE");
        add(transaction);
        transaction.setBounds(70, 485, 220, 50);

        counter.setBackground(null);
        counter.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        counter.setForeground(new Color(0x041a46));
        counter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counter.setText("BAT DI BLUE");
        add(counter);
        counter.setBounds(400, 485, 230, 50);

        MyTextPane.setLayout(null);

        TranText.setFont(new java.awt.Font("Nirmala UI", 1, 8)); // NOI18N
        TranText.setForeground(new java.awt.Color(0, 0, 0));
        TranText.setText("WITHDRAW");
        MyTextPane.add(TranText);
        TranText.setBounds(110, 260, 190, 20);

        CounterText.setFont(new java.awt.Font("Nirmala UI", 1, 8)); // NOI18N
        CounterText.setForeground(new java.awt.Color(0, 0, 0));
        CounterText.setText("COUNTER");
        MyTextPane.add(CounterText);
        CounterText.setBounds(227, 194, 80, 20);

        bal.setFont(new java.awt.Font("Nirmala UI", 1, 8)); // NOI18N
        bal.setForeground(new java.awt.Color(0, 0, 0));
        bal.setText("10,000,000");
        MyTextPane.add(bal);
        bal.setBounds(70, 273, 190, 20);

        receipt.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        receipt.setForeground(new Color(0x041a46));
        receipt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        receipt.setText("W - 001");
        receipt.setBorder(null);
        MyTextPane.add(receipt);
        receipt.setBounds(90, 136, 160, 50);

        ACCNAME.setFont(new java.awt.Font("Nirmala UI", 1, 8)); // NOI18N
        ACCNAME.setForeground(new java.awt.Color(0, 0, 0));
        ACCNAME.setText("Earnest Rayleigh L. Reyes");
        MyTextPane.add(ACCNAME);
        ACCNAME.setBounds(93, 236, 140, 20);

        ACCNO.setFont(new java.awt.Font("Nirmala UI", 1, 8)); // NOI18N
        ACCNO.setForeground(new java.awt.Color(0, 0, 0));
        ACCNO.setText("1234-5678-9876");
        MyTextPane.add(ACCNO);
        ACCNO.setBounds(103, 247, 140, 20);

        DATE.setFont(new java.awt.Font("Nirmala UI", 1, 8)); // NOI18N
        DATE.setForeground(new java.awt.Color(0, 0, 0));
        DATE.setText("8835");
        MyTextPane.add(DATE);
        DATE.setBounds(57, 194, 80, 20);

        TIME.setFont(new java.awt.Font("Nirmala UI", 1, 8)); // NOI18N
        TIME.setForeground(new java.awt.Color(0, 0, 0));
        TIME.setText("11:11");
        MyTextPane.add(TIME);
        TIME.setBounds(57, 206, 80, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/FINAL TIX.png"))); // NOI18N
        MyTextPane.add(jLabel1);
        jLabel1.setBounds(0, 0, 340, 430);

        add(MyTextPane);
        MyTextPane.setBounds(810, 170, 340, 430);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/QUE_NUM.gif"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed

        Timer timer = new Timer(1000, e -> {
            //frame.setContentPane(new TRANSACTIONS(frame));
            MyTextPane.setVisible(true);
            showAndPrintReceipt(this,MyTextPane, receipt, Que_num.getText());
            frame.revalidate();
            frame.repaint();
        });
        timer.setRepeats(false); 
        timer.start();
    
        Timer timer2 = new Timer(7000, e -> {
          
            frame.setContentPane(new Transactions(frame));
            frame.revalidate();
            frame.repaint();
        });
        timer2.setRepeats(false); 
        timer2.start();
        
    }//GEN-LAST:event_BACKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ACCNAME;
    private javax.swing.JLabel ACCNO;
    private javax.swing.JButton BACK;
    private javax.swing.JLabel CounterText;
    private javax.swing.JLabel DATE;
    private javax.swing.JPanel MyTextPane;
    public static javax.swing.JLabel Que_num;
    private javax.swing.JLabel TIME;
    private javax.swing.JLabel TranText;
    private javax.swing.JLabel background;
    private javax.swing.JLabel bal;
    public static javax.swing.JLabel counter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField receipt;
    public static javax.swing.JLabel transaction;
    // End of variables declaration//GEN-END:variables
}
