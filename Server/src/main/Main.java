package main;

import function.Client;
import function.Method;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.ServerSocket;
import java.util.ArrayList;
import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        cmdStart = new JButton();
        cmdStop = new JButton();
        jScrollPane1 = new JScrollPane();
        txt = new JTextArea();
        lbStatus = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cmdStart.setBackground(new Color(102, 255, 102));
        cmdStart.setText("Start Server");
        cmdStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdStartActionPerformed(evt);
            }
        });

        cmdStop.setBackground(new Color(255, 153, 153));
        cmdStop.setText("Stop Server");
        cmdStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdStopActionPerformed(evt);
            }
        });

        txt.setEditable(false);
        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        lbStatus.setForeground(new Color(255, 51, 51));
        lbStatus.setText("Server is Stop");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdStart)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdStop)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbStatus, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 332, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private ServerSocket server;
    private Thread run;

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
                    Method.setTxt(txt);
                    txt.setText("Server now Starting ...\n");
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
            lbStatus.setForeground(new Color(255, 51, 51));
            txt.setText("Server now Stoped ...");
            run.interrupt();
            server.close();
        }
    }
    private void cmdStartActionPerformed(java.awt.event.ActionEvent evt) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private JButton cmdStart;
    private JButton cmdStop;
    private JScrollPane jScrollPane1;
    private JLabel lbStatus;
    private JTextArea txt;
}
