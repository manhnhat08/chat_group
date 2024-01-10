package main;

import function.FileChooser;
import function.Method;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        open();
    }

    private void open() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icon/icon.png"))).getImage());
        Method.setTextFieldSyle(txtUser, "User Name");
        Method.setTextFieldSyle(txtIP, "IP Address");
        showStatus(ms);
    }

    private void initComponents() {

        JPanel jPanel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(0, 205, 205);
                Color color2 = new Color(255, 255, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        txtIP = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        my_swing.Button cmdLogin = new my_swing.Button();
        JLayeredPane jLayeredPane1 = new JLayeredPane();
        JLabel border = new JLabel();
        profile = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log in");
        setResizable(false);

        txtIP.setBackground(new Color(0, 0, 0, 0));
        txtIP.setBorder(new CompoundBorder(
                new EmptyBorder(0, 5, 0, 5),
                new LineBorder(Color.BLACK)
        ));
        txtIP.setFont(new java.awt.Font("Khmer SBBIC Serif", Font.BOLD, 18)); // NOI18N
        txtIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIP.setSelectionColor(new java.awt.Color(131, 188, 227));
        txtIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIPKeyTyped(evt);
            }
        });

        txtUser.setBackground(new Color(0, 0, 0, 0));
        txtUser.setBorder(new CompoundBorder(
                new EmptyBorder(0, 5, 0, 5),
                new LineBorder(Color.BLACK)

        ));
        txtUser.setFont(new java.awt.Font("Khmer SBBIC Serif", Font.BOLD, 18)); // NOI18N
        txtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUser.setSelectionColor(new java.awt.Color(131, 188, 227));
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserKeyTyped(evt);
            }
        });

        cmdLogin.setBackground(new java.awt.Color(101, 167, 232));
        cmdLogin.setForeground(new java.awt.Color(255, 255, 255));
        cmdLogin.setText("Log in");
        cmdLogin.setColorClick(new java.awt.Color(152, 196, 239));
        cmdLogin.setColorOver(new java.awt.Color(31, 121, 208));
        cmdLogin.setFillBorder(20);
        cmdLogin.setFocusable(false);
        cmdLogin.setFont(new java.awt.Font("sanserif", Font.BOLD, 14)); // NOI18N
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed();
            }
        });

        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/icon/us.png")))); // NOI18N
        profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        jLayeredPane1.add(profile);

        lbStatus.setFont(new java.awt.Font("Khmer SBBIC Serif", Font.PLAIN, 14)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(204, 0, 0));
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(84, 84, 84))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(170, 170, 170)
                                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(cmdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void cmdLoginActionPerformed() {
        try {
            if (txtUser.getText().isEmpty() || !txtUser.getName().equals("have")) {
                txtUser.grabFocus();
                showStatus("Please input your user name");
            } else {
                if (txtUser.getText().trim().length() > 15) {
                    txtUser.grabFocus();
                    showStatus("User name must less than 15 character");
                } else {
                    String IP = txtIP.getText().trim();
                    if (txtIP.getText().isEmpty() || !txtIP.getName().equals("have")) {
                        IP = "localhost";
                        System.err.println("have");
                    }
                    String userName = txtUser.getText().trim();
                    Method.connect(profile_pic, userName, IP);
                    this.dispose();
                    Main.main(null);
                }

            }
        } catch (UnknownHostException | java.rmi.UnknownHostException e) {
            showStatus("Unknown host : " + txtIP.getText());
        } catch (ConnectException | java.rmi.ConnectException e) {
            showStatus("Server not found");
        } catch (Exception e) {
            showStatus("Network is unreachable : connect");
            System.out.println(e);
        }

    }

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
            JFileChooser ch = new JFileChooser();
            FileChooser preview = new FileChooser();
            ch.setAccessory(preview);
            ch.addPropertyChangeListener(preview);
            ch.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    String name = file.getName();
                    return file.isDirectory() || name.endsWith(".png") || name.endsWith(".PNG") || name.endsWith("jpg") || name.endsWith("JPG");
                }

                @Override
                public String getDescription() {
                    return "png,jpg";
                }
            });
            int opt = ch.showOpenDialog(this);
            if (opt == JFileChooser.APPROVE_OPTION) {
                ImageIcon image = new ImageIcon(ch.getSelectedFile().getAbsolutePath());
                Image img;
                if (image.getIconWidth() > image.getIconHeight()) {
                    img = image.getImage().getScaledInstance(100, -1, Image.SCALE_SMOOTH);
                } else {
                    img = image.getImage().getScaledInstance(-1, 100, Image.SCALE_SMOOTH);
                }
                profile_pic = new ImageIcon(img);
                profile.setIcon(profile_pic);
            }
        }
    }

    private void txtUserKeyTyped(java.awt.event.KeyEvent evt) {
        if (evt.getKeyChar() == 10) {
            txtIP.grabFocus();
        }
        if (txtUser.getText().trim().length() >= 15) {
            evt.consume();
        }
    }

    private void txtIPKeyTyped(java.awt.event.KeyEvent evt) {
        if (evt.getKeyChar() == 10) {
            cmdLoginActionPerformed();
        }
    }

    private ImageIcon profile_pic;
    private final Timer timer = new Timer(5000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            lbStatus.setText("");
            timer.stop();
        }
    });

    private void showStatus(String error) {
        if (timer.isRunning()) {
            lbStatus.setText("");
            timer.stop();
        }
        timer.start();
        lbStatus.setText(error);
    }

    private static String ms = "";

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (args.length == 1) {
                    ms = args[0];
                }
                new Login().setVisible(true);
            }
        });
    }

    private my_swing.Button cmdLogin;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel profile;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtUser;
}
