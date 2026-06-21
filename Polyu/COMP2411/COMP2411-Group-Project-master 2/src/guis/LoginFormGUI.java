package guis;

import constants.CommonConstants;
import db.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class LoginFormGUI extends BasicForm {

    public LoginFormGUI() {
        super("Login");
        addGuiComponents();
    }

    private void addGuiComponents() {
        // Basic attributes in LoginFormGUI
        getContentPane().setBackground(CommonConstants.PRIMAR_COLOR);

        // create login labels
        JLabel WelcomeLabel = new JLabel("Campus Maintenance and Management System");
        JLabel loginLabel = new JLabel("CMMS");
        JLabel usernameLabel = new JLabel("UserName:");
        JLabel passwordLabel = new JLabel("Password:");
        JButton loginButton = new JButton("Login");
        JLabel registerLabel = new JLabel("If can't login, Register Here");

        // create text field
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        // Setting the interface from the top to the bottom

        // Welcome
        WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        WelcomeLabel.setBounds(0,50,520,100);
        WelcomeLabel.setForeground(CommonConstants.TEXT_COLOR);
        WelcomeLabel.setFont(new Font("Dialog", Font.BOLD, 17));

        // Login
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(0,140,520,100);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setFont(new Font("Dialog",Font.BOLD, 40));

        // username
        usernameLabel.setBounds(30, 225,410,25 );
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN,15));

        // usernameField
        usernameField.setBounds(30,270,440,40);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog",Font.PLAIN,24));
        usernameField.setCaretColor(CommonConstants.CURSOR_COLOR);

        // password
        passwordLabel.setBounds(30,350,410,25);
        passwordLabel.setForeground((CommonConstants.TEXT_COLOR));
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,15));

        // passwordField
        passwordField.setBounds(30, 395,440,40);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog",Font.PLAIN,24));
        passwordField.setCaretColor(CommonConstants.CURSOR_COLOR);

        // login button
        loginButton.setFont(new Font("Dialog",Font.PLAIN,24));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setBounds(100,540,290,40);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Validate input
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginFormGUI.this, "Please enter both username and password!");
            }

            if(JDBC.validLogin(username, password)) {
                // record username and password
                CommonConstants.username = username;
                CommonConstants.password = password;

                // switch login to main GUI
                LoginFormGUI.this.dispose();
                MainGUI mainGUI = new MainGUI();
                mainGUI.setVisible(true);
            } else {
                usernameField.setText("");
                passwordField.setText("");
                JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Failed. Please check your credentials or register.");
            }
        });

        // register label
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setBounds(125,600,250,30);

        // switch to register GUI
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFormGUI.this.dispose();
                new RegisterFormGUI().setVisible(true);
            }
        });

        // add the components
        add(loginLabel);
        add(WelcomeLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerLabel);
    }
}