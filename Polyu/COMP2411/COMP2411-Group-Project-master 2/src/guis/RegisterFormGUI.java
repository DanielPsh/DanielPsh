package guis;

import constants.CommonConstants;
import db.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends BasicForm{
    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
    }

    private void addGuiComponents(){
        // Basic attributes in RegisterFormGUI
        getContentPane().setBackground(CommonConstants.PRIMAR_COLOR);

        // create Register labels
        JLabel WelcomeLabel = new JLabel("Campus Maintenance and Management System");
        JLabel SystemLabel = new JLabel("CMMS");
        JLabel usernameLabel = new JLabel("UserName:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel registerKeyLabel = new JLabel("Register valid key: ");
        JButton registerButton = new JButton("Register");
        JLabel loginLabel = new JLabel("Go back to login here!");

        // create text field
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField registerKeyField = new JTextField(); // Changed to regular text field

        // Setting the interface from the top to the bottom

        // Welcome
        WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        WelcomeLabel.setBounds(0,50,520,100);
        WelcomeLabel.setForeground(CommonConstants.TEXT_COLOR);
        WelcomeLabel.setFont(new Font("Dialog", Font.BOLD, 17));

        // System Label
        SystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        SystemLabel.setBounds(0,140,520,100);
        SystemLabel.setForeground(CommonConstants.TEXT_COLOR);
        SystemLabel.setFont(new Font("Dialog",Font.BOLD, 40));

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
        passwordLabel.setBounds(30,320,410,25);
        passwordLabel.setForeground((CommonConstants.TEXT_COLOR));
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,15));

        // passwordField
        passwordField.setBounds(30, 365,440,40);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog",Font.PLAIN,24));
        passwordField.setCaretColor(CommonConstants.CURSOR_COLOR);

        // register key
        registerKeyLabel.setBounds(30,415,410,25);
        registerKeyLabel.setForeground((CommonConstants.TEXT_COLOR));
        registerKeyLabel.setFont(new Font("Dialog", Font.PLAIN,15));

        // registerKeyField
        registerKeyField.setBounds(30, 465,440,40);
        registerKeyField.setBackground(CommonConstants.SECONDARY_COLOR);
        registerKeyField.setForeground(CommonConstants.TEXT_COLOR);
        registerKeyField.setFont(new Font("Dialog",Font.PLAIN,24));
        registerKeyField.setCaretColor(CommonConstants.CURSOR_COLOR);

        // register button
        registerButton.setFont(new Font("Dialog",Font.PLAIN,24));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setBounds(100,540,290,40);
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String registerKey = registerKeyField.getText().trim();

            if (password.contains(" ")) {
                JOptionPane.showMessageDialog(RegisterFormGUI.this, "Register failed. Password contains space.");
                passwordField.setText("");
                return;
            }

            if (!isValidRegisterKey(registerKey)) {
                registerKeyField.setText("");
                JOptionPane.showMessageDialog(RegisterFormGUI.this, "Register failed. Wrong register key.");
                return;
            }

            if(validUserInput(username, password)){
                if(JDBC.register(username, password, registerKey)) {
                    // switch register to login GUI
                    RegisterFormGUI.this.dispose();
                    LoginFormGUI loginFormGUI = new LoginFormGUI();
                    loginFormGUI.setVisible(true);

                    // show register success
                    JOptionPane.showMessageDialog(RegisterFormGUI.this, "Register Successful! Please login with your new account.");
                } else {
                    JOptionPane.showMessageDialog(RegisterFormGUI.this, "Register failed. Username might already exist.");
                    usernameField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(RegisterFormGUI.this, "Please enter valid data!\n- All fields are required");
            }
        });

        // login label
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setBounds(125,600,250,30);

        // switch to login GUI
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();
                new LoginFormGUI().setVisible(true);
            }
        });

        // add the components
        add(SystemLabel);
        add(WelcomeLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(registerKeyLabel);
        add(registerKeyField);
        add(registerButton);
        add(loginLabel);
    }

    private boolean validUserInput(String username, String password){
        return !username.isEmpty() && !password.isEmpty();
    }

    public static boolean isValidRegisterKey(String registerKey) {
        // Check against valid keys
        return registerKey.equals(CommonConstants.VALID_REGISTER_KEYS) || registerKey.isEmpty();

    }
}