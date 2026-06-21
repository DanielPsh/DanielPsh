package guis;

import constants.CommonConstants;
import db.JDBC;


import javax.swing.*;
import java.awt.*;

import static db.JDBC.getCleaningActivities;

public class CleaningGUI extends AdvanceForm {

    public CleaningGUI() {
        super("Cleaning Schedule");
        addGUIComponents();
    }

    public static void main(String[] args) {
        CleaningGUI cleaningGUI = new CleaningGUI();
        cleaningGUI.setVisible(true);
    }


    private JComboBox<String> startYear, startMonth, startDay;
    private JComboBox<String> endYear, endMonth, endDay;
    private JComboBox<String> building;
    private JTable dataTable;

    private void addGUIComponents() {

        // Create menu
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Setting");
        JMenuItem MainGUI = new JMenuItem("Home page");
        JMenuItem DataManageGUI = new JMenuItem("Data Management");
        JMenuItem ReportGUI = new JMenuItem("System Report");

        // MenuBar
        setJMenuBar(bar);
        bar.add(menu);
        menu.add(MainGUI);
        menu.add(DataManageGUI);
        menu.add(ReportGUI);

        // Open GUI
        MainGUI.addActionListener(e ->{
            MainGUI mainGUI = new MainGUI();
            mainGUI.setVisible(true);
            setVisible(false);
        });
        DataManageGUI.addActionListener(e -> {
            DataManageGUI dataManageGUI = new DataManageGUI();
            dataManageGUI.setVisible(true);
            setVisible(false);
        });
        ReportGUI.addActionListener(e -> {
            if (JDBC.role() == 1) {
                ReportGUI reportGUI = new ReportGUI();
                reportGUI.setVisible(true);
                setVisible(false);
            } else JOptionPane.showMessageDialog(this, "You do not have permission to access this GUI.");
        });


        // titleLabel
        JLabel titleLabel = new JLabel("Cleaning Activities Schedule");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(CommonConstants.TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(100,30,800,50);

        // title of the panel
        JLabel name = new JLabel("Select the period");
        name.setFont(new Font("Dialog", Font.BOLD, 24));
        name.setForeground(CommonConstants.TEXT_COLOR);
        name.setBounds(100,80,200,40);

        JPanel TimePanel = createTimePanel();
        TimePanel.setBounds(100,125,800,40);

        // Select button
        JButton SelectButton = new JButton("Select");
        TimePanel.add(SelectButton, BorderLayout.EAST);
        SelectButton.addActionListener(e -> findCleaningData());

        // data panel
        JPanel dataPanel = createTable();
        dataPanel.setBounds(100,180,800,570);

        // add components in GUI
        add(titleLabel);
        add(name);
        add(TimePanel);
        add(dataPanel);

        // lock the size
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JPanel createTimePanel() {
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        timePanel.setBorder(BorderFactory.createTitledBorder(""));
        timePanel.setBackground(CommonConstants.TEXT_COLOR);

        // Initialize the time panel
        startYear = createYearComboBox();
        startMonth = createMonthComboBox();
        startDay = createDayComboBox();
        endYear = createYearComboBox();
        endMonth = createMonthComboBox();
        endDay = createDayComboBox();
        building = SelectBuildings();

        timePanel.add(new JLabel("Start:"));
        timePanel.add(startYear);
        timePanel.add(new JLabel("/"));
        timePanel.add(startMonth);
        timePanel.add(new JLabel("/"));
        timePanel.add(startDay);
        // make same distances between start and end
        timePanel.add(new JLabel("  End:"));
        timePanel.add(endYear);
        timePanel.add(new JLabel("/"));
        timePanel.add(endMonth);
        timePanel.add(new JLabel("/"));
        timePanel.add(endDay);
        timePanel.add(new JLabel("   Building:"));
        timePanel.add(building);

        return timePanel;
    }

    private JComboBox<String> SelectBuildings() {
        String[] items = {"Core A", "Core B","Core C","Core D","Core E","Core F","Core G","Core H",
                "Core J","Block L","Block M","Block N",
                "Core P","Core Q","Wing QR","Wing PQ","Core S","Core T","Block X","Block Z"};
        JComboBox<String> Buildings = new JComboBox<>(items);
        Buildings.setPreferredSize(new Dimension(100, 25));
        return Buildings;
    }


    private JPanel createTable() {
        // setting tablePanel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Cleaning Data"));

        // the column of the data table
        String[] columnNames = {"Location", "Building", "Start_Time", "End_Time","Status","Harmful_Chemical"};

        // the data will show in table
        Object[][] data = { {"Polyu", "Block A", "2025/11/27", "2025/11/30", "Finished", "Yes"}
        };

        // setting data table
        dataTable = new JTable(data, columnNames);
        dataTable.setFillsViewportHeight(true);
        dataTable.setRowHeight(25);
        dataTable.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        dataTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        // ScrollPane for data table
        JScrollPane scrollPane = new JScrollPane(dataTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setPreferredSize(new Dimension(800, 300));

        return tablePanel;
    }

    private JComboBox<String> createYearComboBox() {
        JComboBox<String> yearCombo = new JComboBox<>();
        int Year = 2025;
        // the year is between 30 years
        for (int i = Year - 15; i <= Year + 15; i++) {
            yearCombo.addItem(Integer.toString(i));
        }
        yearCombo.setSelectedItem(Integer.toString(Year));
        yearCombo.setPreferredSize(new Dimension(70, 25));
        return yearCombo;
    }

    private JComboBox<String> createMonthComboBox() {
        JComboBox<String> monthCombo = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthCombo.addItem(String.format("%02d", i));
        }
        monthCombo.setSelectedItem(String.format("%02d", 1));
        monthCombo.setPreferredSize(new Dimension(50, 25));
        return monthCombo;
    }

    private JComboBox<String> createDayComboBox() {
        JComboBox<String> dayCombo = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayCombo.addItem(String.format("%02d", i));
        }
        dayCombo.setSelectedItem(String.format("%02d", 1));
        dayCombo.setPreferredSize(new Dimension(50, 25));
        return dayCombo;
    }

    // SQL Cleaning Activity Data
    private void findCleaningData() {
        // get the time data
        String start = startYear.getSelectedItem() + "-" +
                startMonth.getSelectedItem() + "-" +
                startDay.getSelectedItem();

        String end = endYear.getSelectedItem() + "-" +
                endMonth.getSelectedItem() + "-" +
                endDay.getSelectedItem();

        // get the buildings
        String selectBuilding = (String) building.getSelectedItem();

        dataTable.setModel(getCleaningActivities(start, end, selectBuilding));
        dataTable.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        dataTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        dataTable.setRowHeight(25);
        dataTable.revalidate();
        dataTable.repaint();

    }
}
