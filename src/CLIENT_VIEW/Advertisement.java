/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CLIENT_VIEW;

/**
 *
 * @author reyes
 */
public class Advertisement extends javax.swing.JPanel {

    private Frame frame;
    public Advertisement(Frame frame) {
        initComponents();
        
        this.frame = frame;
         setDoubleBuffered(true);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JButton();

        setLayout(null);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG'S/FINAL BANK QUEUE.gif"))); // NOI18N
        background.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundActionPerformed(evt);
            }
        });
        add(background);
        background.setBounds(0, 0, 1300, 700);
    }// </editor-fold>//GEN-END:initComponents

    private void backgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundActionPerformed
        frame.setContentPane(new Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_backgroundActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton background;
    // End of variables declaration//GEN-END:variables
}
