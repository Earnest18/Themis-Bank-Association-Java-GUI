package CLIENT_VIEW;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class CLASS_PANES {

    private static Timer timer;
    private static int index;

    public static void showMessageBox(String message) {
        UIManager.put("OptionPane.background", new Color(0, 0, 0, 0));
        UIManager.put("Panel.background", new Color(0, 0, 0, 0));

        JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setSize(400, 240);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0xf2d24b),
                        0, h, new Color(0x041a46)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // HEADER
        JPanel header = new JPanel(null);
        header.setBackground(new Color(0x041a46));
        header.setBounds(0, 0, 400, 40);

        JLabel title = new JLabel("THEMIS BANK ASSOCIATION", SwingConstants.CENTER);
        title.setFont(new Font("Felix Titling", Font.BOLD, 14));
        title.setForeground(new Color(0xf2d24b));
        title.setBounds(0, 10, 400, 20);

        header.add(title);

        // BODY
        JPanel body = new JPanel(null);
        body.setOpaque(false);
        body.setBounds(0, 40, 400, 160);

        // Background image
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Carl joshua\\Documents\\NetBeansProjects\\Practice\\TESTS\\TBAQS_FINAL\\src\\PANEL IMAGES\\FINAL_TEXTPANEL.png");
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, 400, 160);

        // Text label (on top of background)
        JLabel text = new JLabel("<html><div style='text-align:center;'></div></html>", SwingConstants.CENTER);
        text.setForeground(Color.BLACK);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Nirmala UI", Font.BOLD, 14));
        text.setBounds(40, 50, 320, 80);
        text.setOpaque(false);

        // Ensure text appears ABOVE background
        body.add(bgLabel);
        body.add(text);
        body.setComponentZOrder(text, 0); // Text always on top

        // OK button
        JButton okButton = new JButton("OK");
        okButton.setBounds(160, 197, 80, 30);
        okButton.setBackground(new Color(0xf2d24b));
        okButton.setBorder(null);
        okButton.setFocusable(false);
        okButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        okButton.addActionListener(e -> {
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
            dialog.dispose();
        });

        mainPanel.add(header);
        mainPanel.add(body);
        mainPanel.add(okButton);
        dialog.setContentPane(mainPanel);
 text.setText("<html><div style='text-align:center;'>" + message + "</div></html>");
       
        dialog.setVisible(true);
    }

    public static int showConfirmBox(String message) {

        UIManager.put("OptionPane.background", new Color(0, 0, 0, 0));
        UIManager.put("Panel.background", new Color(0, 0, 0, 0));

        JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setSize(400, 260);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0xf2d24b),
                        0, h, new Color(0x041a46)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel header = new JPanel(null);
        header.setBackground(new Color(0x041a46));
        header.setBounds(0, 0, 400, 40);

        ImageIcon logo = new ImageIcon("C:\\Users\\Carl joshua\\Documents\\NetBeansProjects\\Practice\\TESTS\\TBAQS_FINAL\\src\\PANEL IMAGES\\LOGOO.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(5, 5, 30, 30);

        JLabel title = new JLabel("Themis Bank Association");
        title.setFont(new Font("Felix Titling", Font.BOLD, 14));
        title.setForeground(new Color(0xf2d24b));
        title.setBounds(40, 10, 300, 20);

        header.add(logoLabel);
        header.add(title);

        JPanel body = new JPanel(null);
        body.setOpaque(false);
        body.setBounds(0, 40, 400, 180);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Carl joshua\\Documents\\NetBeansProjects\\Practice\\TESTS\\TBAQS_FINAL\\src\\PANEL IMAGES\\FINAL_TEXTPANEL.png");
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, 400, 200);

        JLabel text = new JLabel("<html><div style='text-align:center;'>" + message + "</div></html>");
        text.setBounds(50, 60, 300, 80);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Nirmala UI", Font.BOLD, 14));

        body.add(text);
        body.add(bgLabel);
        body.setComponentZOrder(bgLabel, body.getComponentCount() - 1);

        JButton yesButton = new JButton("Yes");
        yesButton.setBounds(110, 220, 80, 30);
        yesButton.setBorder(null);
        yesButton.setBackground(new Color(0xf2d24b));
        yesButton.setFocusable(false);
        yesButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));

        JButton noButton = new JButton("No");
        noButton.setBounds(210, 220, 80, 30);
        noButton.setBorder(null);
        noButton.setBackground(new Color(0xf2d24b));
        noButton.setFocusable(false);
        noButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));

        final int[] result = {JOptionPane.CLOSED_OPTION};

        yesButton.addActionListener(e -> {
            result[0] = JOptionPane.YES_OPTION;
            dialog.dispose();
        });

        noButton.addActionListener(e -> {
            result[0] = JOptionPane.NO_OPTION;
            dialog.dispose();
        });

        mainPanel.add(header);
        mainPanel.add(body);
        mainPanel.add(yesButton);
        mainPanel.add(noButton);
        dialog.setContentPane(mainPanel);

        dialog.setVisible(true);

        return result[0];
    }

    public static int SelectionBox(String[] options, String message) {

        UIManager.put("OptionPane.background", new Color(0, 0, 0, 0));
        UIManager.put("Panel.background", new Color(0, 0, 0, 0));

        JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setSize(400, 260);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0xf2d24b),
                        0, h, new Color(0x041a46)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel header = new JPanel(null);
        header.setBackground(new Color(0x041a46));
        header.setBounds(0, 0, 400, 40);

        ImageIcon logo = new ImageIcon("C:\\Users\\Carl joshua\\Documents\\NetBeansProjects\\Practice\\TESTS\\TBAQS_FINAL\\src\\PANEL IMAGES\\LOGOO.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(5, 5, 30, 30);

        JLabel title = new JLabel("Themis Bank Association");
        title.setFont(new Font("Felix Titling", Font.BOLD, 14));
        title.setForeground(new Color(0xf2d24b));
        title.setBounds(40, 10, 300, 20);

        header.add(logoLabel);
        header.add(title);

        JPanel body = new JPanel(null);
        body.setOpaque(false);
        body.setBounds(0, 40, 400, 180);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Carl joshua\\Documents\\NetBeansProjects\\Practice\\TESTS\\TBAQS_FINAL\\src\\PANEL IMAGES\\FINAL_TEXTPANEL.png");
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, 400, 200);

        JLabel text = new JLabel("<html><div style='text-align:center;'>" + message + "</div></html>");
        text.setBounds(50, 60, 300, 80);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Nirmala UI", Font.BOLD, 14));

        body.add(text);
        body.add(bgLabel);
        body.setComponentZOrder(bgLabel, body.getComponentCount() - 1);

        final int[] result = {JOptionPane.CLOSED_OPTION};
        if (options.length == 2) {

            JButton yesButton = new JButton(options[0].toString());
            yesButton.setBounds(110, 220, 80, 30);
            //yesButton.setBorder(null);
            yesButton.setBackground(new Color(0xf2d24b));
            yesButton.setFocusable(false);
            yesButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));

            JButton noButton = new JButton(options[1].toString());
            noButton.setBounds(210, 220, 80, 30);
            //noButton.setBorder(null);
            noButton.setBackground(new Color(0xf2d24b));
            noButton.setFocusable(false);
            noButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));

            yesButton.addActionListener(e -> {
                result[0] = JOptionPane.YES_OPTION;
                dialog.dispose();
            });

            noButton.addActionListener(e -> {
                result[0] = JOptionPane.NO_OPTION;
                dialog.dispose();
            });

            mainPanel.add(header);
            mainPanel.add(body);
            mainPanel.add(yesButton);
            mainPanel.add(noButton);

            dialog.setContentPane(mainPanel);
            dialog.setVisible(true);

            return result[0];

        }

        if (options.length == 3) {
            JButton yesButton = new JButton(options[0].toString());
            yesButton.setBounds(40, 220, 80, 30);
            //yesButton.setBorder(null);
            yesButton.setBackground(new Color(0xf2d24b));
            yesButton.setFocusable(false);
            yesButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));

            JButton noButton = new JButton(options[1].toString());
            noButton.setBounds(160, 220, 80, 30);
            //noButton.setBorder(null);
            noButton.setBackground(new Color(0xf2d24b));
            noButton.setFocusable(false);
            noButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));

            JButton cancelButton = new JButton(options[2].toString());
            cancelButton.setBounds(280, 220, 80, 30);
            //cancelButton.setBorder(null);
            cancelButton.setBackground(new Color(0xf2d24b));
            cancelButton.setFocusable(false);
            cancelButton.setFont(new Font("Nirmala UI", Font.BOLD, 12));

            yesButton.addActionListener(e -> {
                result[0] = JOptionPane.YES_OPTION;
                dialog.dispose();
            });

            noButton.addActionListener(e -> {
                result[0] = JOptionPane.NO_OPTION;
                dialog.dispose();
            });

            cancelButton.addActionListener(e -> {
                result[0] = JOptionPane.CANCEL_OPTION;
                dialog.dispose();
            });

            mainPanel.add(header);
            mainPanel.add(body);
            mainPanel.add(yesButton);
            mainPanel.add(noButton);
            mainPanel.add(cancelButton);

            dialog.setContentPane(mainPanel);
            dialog.setVisible(true);

        }
        return result[0];
    }
}
