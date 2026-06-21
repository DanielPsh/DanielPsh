package guis;

import constants.CommonConstants;
import db.JDBC;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends AdvanceForm {

    public MainGUI() {
        super("MainGUI");
        addGuiComponents();
    }

    // test MainGUI
    public static void main(String[] args){
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
    }

    private void addGuiComponents() {
        // Basic attributions in MainGUI
        getContentPane().setBackground(CommonConstants.PRIMAR_COLOR);
        setSize(1000, 800);

        // create main label (something should be included)
        JLabel WelcomeLabel = new JLabel("Campus Maintenance and Management System");
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Setting");
        JMenuItem ReportGUI = new JMenuItem("System Report");
        JMenuItem DataManageGUI = new JMenuItem("Data Management");
        JMenuItem CleaningGUI = new JMenuItem("Cleaning Activity");
        JLabel SQLLabel = new JLabel("SQL:  ");
        JTextField SQLField = new JTextField();
        JButton SQLRun = new JButton("Run");
        JTable result = new JTable();
        JScrollPane scrollPane = new JScrollPane(result);

        // MenuBar
        setJMenuBar(bar);
        bar.add(menu);
        menu.add(ReportGUI);
        menu.add(DataManageGUI);
        menu.add(CleaningGUI);

        // Open GUI
        ReportGUI.addActionListener(e -> {
            if (JDBC.role() == 1) {
                ReportGUI reportGUI = new ReportGUI();
                reportGUI.setVisible(true);
                setVisible(false);
            } else JOptionPane.showMessageDialog(this, "You do not have permission to access this GUI.");
        });
        DataManageGUI.addActionListener(e ->{
            DataManageGUI dataManageGUI = new DataManageGUI();
            dataManageGUI.setVisible(true);
            setVisible(false);
        });
        CleaningGUI.addActionListener(e -> {
            CleaningGUI cleaningGUI = new CleaningGUI();
            cleaningGUI.setVisible(true);
            setVisible(false);
        });

        // WelcomeLabel
        WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        WelcomeLabel.setBounds(0, 0, 1000, 100);
        WelcomeLabel.setForeground(CommonConstants.TEXT_COLOR);
        WelcomeLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        // SQLLabel
        SQLLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        SQLLabel.setBounds(0, 100, 200, 50);
        SQLLabel.setForeground(CommonConstants.TEXT_COLOR);
        SQLLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        // SQLField
        SQLField.setHorizontalAlignment(SwingConstants.LEFT);
        SQLField.setBounds(200, 100, 600, 50);
        SQLField.setForeground(CommonConstants.TEXT_COLOR);
        SQLField.setFont(new Font("Dialog", Font.BOLD, 20));

        // ResultTable
        scrollPane.setBounds(100, 200, 800, 500);

        // SQLRun
        SQLRun.setHorizontalAlignment(SwingConstants.LEFT);
        SQLRun.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SQLRun.setBackground(CommonConstants.TEXT_COLOR);
        SQLRun.setBounds(800, 100, 75, 50);
        SQLRun.setForeground(Color.CYAN);
        SQLRun.setFont(new Font("Dialog", Font.PLAIN, 20));
        SQLRun.addActionListener(e -> {
            String SQLCommand = SQLField.getText().trim();

            // Validate input
            if (SQLCommand.isEmpty()) {
                JOptionPane.showMessageDialog(MainGUI.this,
                        "Please enter SQL!");
                return;
            }

            if(validSQL(SQLCommand)){
                // execute SQL
                result.setModel(JDBC.buildTable(SQLCommand));
                SQLField.setText("");

            } else {
                JOptionPane.showMessageDialog(MainGUI.this, "Wrong SQL query!\nIt only allows to query the CMMS.");
            }
        });

        //

        // add the components
        add(WelcomeLabel);
        add(SQLLabel);
        add(SQLField);
        add(SQLRun);
        add(scrollPane);

    }

    // add the functions
    private boolean validSQL(String sql) {
        if (sql == null || sql.trim().isEmpty()) {
            return false;
        }

        // Check the First word in SQL
        String SQL = sql.trim();
        return SQL.startsWith("SELECT");
    }

}
