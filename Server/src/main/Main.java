package main;

import function.Client;
import function.Method;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

public class Main extends JFrame {
    private JButton cmdStart;
    private JButton cmdStop;
    private JScrollPane jScrollPane;
    private JLabel lbStatus;
    private JTextArea txt;
    private ServerSocket server;
    private Thread run;

    public Main() {
        initComponents();
    }

    private void initComponents() {

        cmdStart = new JButton();
        cmdStop = new JButton();
        jScrollPane = new JScrollPane();
        txt = new JTextArea();
        lbStatus = new JLabel();
        JPanel customPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(0, 205, 205);
                Color color2 = new Color(255, 248, 50);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        // Set the content pane of the JFrame to be the custom panel
        setContentPane(customPanel);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("icon.png"))).getImage());
        setTitle("Server");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cmdStart.setBackground(Color.GREEN);
        cmdStart.setText("Start Server");
        cmdStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdStartActionPerformed(evt);
            }
        });

        cmdStop.setBackground(Color.RED);
        cmdStop.setText("Stop Server");
        cmdStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdStopActionPerformed(evt);
            }
        });

        txt.setEditable(false);
        txt.setBackground(Color.LIGHT_GRAY);
        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane.setViewportView(txt);

        lbStatus.setForeground(Color.RED);
        lbStatus.setText("Server is stop");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdStart)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdStop)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbStatus, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 330, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lbStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdStart)
                        .addComponent(cmdStop)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void startServer() throws Exception {
        Method.setClients(new ArrayList<>());
        File f = new File("data");

        if (!f.exists()) {
            try  {
                f.mkdirs();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        for (File fs : f.listFiles()) {
            fs.delete();
        }

        run = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new ServerSocket(5000);
                    lbStatus.setForeground(Color.GREEN);
                    lbStatus.setText("Server now running...");
                    Method.setTxt(txt);
                    txt.setText("Server now running...\n");
                    while (true) {
                        new Client(server.accept());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(Main.this, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        run.start();
    }

    private void stopServer() throws Exception {
        int c = JOptionPane.showConfirmDialog(this, "Are you sure to stop server now", "Stop Server", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (c == JOptionPane.YES_OPTION) {
            lbStatus.setForeground(Color.RED);
            lbStatus.setText("Server now stopped...");
            txt.setText("Server now stopped...");
            run.interrupt();
            server.close();
        }
    }
    private void cmdStartActionPerformed(ActionEvent evt) {
        try {
            int c = JOptionPane.showConfirmDialog(this, "File in data will be delete when server is start", "Start Server", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (c == JOptionPane.YES_OPTION) {
                startServer();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cmdStopActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            stopServer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
