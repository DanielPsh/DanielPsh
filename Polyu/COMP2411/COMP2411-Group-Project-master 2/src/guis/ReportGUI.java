package guis;

import constants.CommonConstants;
import db.JDBC;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportGUI extends AdvanceForm {
    private DefaultTableModel tableModel;
    private JTextArea reportDescriptionArea;
    private JLabel reportTitleLabel;
    private JTable reportTable;

    public ReportGUI() {
        super("CMMS - Reports Dashboard");
        initializeGUI();
    }

    // Test method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportGUI reportGUI = new ReportGUI();
            reportGUI.setVisible(true);
        });
    }

    private void initializeGUI() {
        getContentPane().setBackground(CommonConstants.PRIMAR_COLOR);
        setLayout(new BorderLayout());

        // Create main panels
        JPanel controlPanel = createControlPanel();
        JPanel displayPanel = createDisplayPanel();

        // Create menu
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Setting");
        JMenuItem MainGUI = new JMenuItem("Home page");
        JMenuItem DataManageGUI = new JMenuItem("Data Management");
        JMenuItem CleaningGUI = new JMenuItem("Cleaning Activity");

        // MenuBar
        setJMenuBar(bar);
        bar.add(menu);
        menu.add(MainGUI);
        menu.add(DataManageGUI);
        menu.add(CleaningGUI);

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
        CleaningGUI.addActionListener(e -> {
            CleaningGUI cleaningGUI = new CleaningGUI();
            cleaningGUI.setVisible(true);
            setVisible(false);
        });

        // Add panels to frame
        add(controlPanel, BorderLayout.WEST);
        add(displayPanel, BorderLayout.CENTER);

        // Load initial report
        loadWorkerActivityReport();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CommonConstants.SECONDARY_COLOR);
        panel.setPreferredSize(new Dimension(300, 800));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Title
        JLabel titleLabel = new JLabel("REPORTS");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(CommonConstants.TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Worker Activity Reports Section
        panel.add(createSectionLabel("Worker Activity Analysis"));
        panel.add(createReportButton("Worker Activity Summary", "WORKER_ACTIVITY_SUMMARY"));
        panel.add(createReportButton("Activities by Worker Role", "ACTIVITY_BY_ROLE"));
        panel.add(createReportButton("Worker Assignment Details", "WORKER_ASSIGNMENT_DETAILS"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Location Reports Section
        panel.add(createSectionLabel("Location Analysis"));
        panel.add(createReportButton("Activities by Location", "ACTIVITY_BY_LOCATION"));
        panel.add(createReportButton("Maintenance Status Report", "MAINTENANCE_STATUS"));
        panel.add(createReportButton("Building Supervision", "BUILDING_SUPERVISION"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Activity Reports Section
        panel.add(createSectionLabel("Activity Analysis"));
        panel.add(createReportButton("Activity Status Overview", "ACTIVITY_STATUS"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Chemical Safety Reports Section
        panel.add(createSectionLabel("Safety & Chemical Reports"));
        panel.add(createReportButton("Chemical Usage Report", "CHEMICAL_USAGE"));
        panel.add(createReportButton("Hazardous Activities", "HAZARDOUS_ACTIVITIES"));
        panel.add(createReportButton("External Contractors", "EXTERNAL_CONTRACTORS"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Executive Summary Section
        panel.add(createSectionLabel("Executive Summary"));
        panel.add(createReportButton("Overall System Summary", "SYSTEM_SUMMARY"));
        panel.add(createReportButton("Performance Metrics", "PERFORMANCE_METRICS"));

        return panel;
    }

    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.BOLD, 14));
        label.setForeground(CommonConstants.TEXT_COLOR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        return label;
    }

    private JButton createReportButton(String text, String reportType) {
        JButton button = new JButton(text);
        button.setFont(new Font("Dialog", Font.PLAIN, 12));
        button.setBackground(CommonConstants.PRIMAR_COLOR);
        button.setForeground(CommonConstants.TEXT_COLOR);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(280, 35));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addActionListener(e -> generateReport(reportType));
        
        return button;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Report Title
        reportTitleLabel = new JLabel("Worker Activity Summary Report");
        reportTitleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        reportTitleLabel.setForeground(CommonConstants.TEXT_COLOR);
        reportTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(reportTitleLabel, BorderLayout.NORTH);

        // Report Description
        reportDescriptionArea = new JTextArea();
        reportDescriptionArea.setEditable(false);
        reportDescriptionArea.setLineWrap(true);
        reportDescriptionArea.setWrapStyleWord(true);
        reportDescriptionArea.setBackground(CommonConstants.SECONDARY_COLOR);
        reportDescriptionArea.setForeground(CommonConstants.TEXT_COLOR);
        reportDescriptionArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        reportDescriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane descriptionScrollPane = new JScrollPane(reportDescriptionArea);
        descriptionScrollPane.setPreferredSize(new Dimension(600, 80));
        panel.add(descriptionScrollPane, BorderLayout.SOUTH);

        // Report Table
        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        reportTable.setFont(new Font("Dialog", Font.PLAIN, 12));
        reportTable.setRowHeight(25);
        reportTable.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 14));
        reportTable.getTableHeader().setBackground(CommonConstants.SECONDARY_COLOR);
        reportTable.getTableHeader().setForeground(CommonConstants.TEXT_COLOR);
        
        JScrollPane tableScrollPane = new JScrollPane(reportTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panel.add(tableScrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void generateReport(String reportType) {
        try {
            String description;
            String title;
            DefaultTableModel model;

            switch (reportType) {
                case "WORKER_ACTIVITY_SUMMARY":
                    model = generateWorkerActivitySummary();
                    title = "Worker Activity Summary Report";
                    description = "Shows the number of workers involved in various activity types across different campus locations.";
                    break;
                    
                case "ACTIVITY_BY_ROLE":
                    model = generateActivityByRoleReport();
                    title = "Activities by Worker Role Report";
                    description = "Breakdown of activities assigned to different employee roles.";
                    break;
                    
                case "WORKER_ASSIGNMENT_DETAILS":
                    model = generateWorkerAssignmentDetails();
                    title = "Worker Assignment Details Report";
                    description = "Detailed view of all worker assignments including activity types and locations.";
                    break;
                    
                case "ACTIVITY_BY_LOCATION":
                    model = generateActivityByLocationReport();
                    title = "Activities by Location Report";
                    description = "Overview of activities distributed across different campus locations and building types.";
                    break;
                    
                case "MAINTENANCE_STATUS":
                    model = generateMaintenanceStatusReport();
                    title = "Maintenance Status Report";
                    description = "Current maintenance status of all locations with associated activity information.";
                    break;
                    
                case "BUILDING_SUPERVISION":
                    model = generateBuildingSupervisionReport();
                    title = "Building Supervision Report";
                    description = "Shows which managers are supervising which buildings and their associated activities.";
                    break;
                    
                case "ACTIVITY_STATUS":
                    model = generateActivityStatusReport();
                    title = "Activity Status Overview";
                    description = "Current status of all activities including completion rates and timelines.";
                    break;
                    
                case "CHEMICAL_USAGE":
                    model = generateChemicalUsageReport();
                    title = "Chemical Usage Report";
                    description = "Details of chemical products used in activities with brand information.";
                    break;
                    
                case "HAZARDOUS_ACTIVITIES":
                    model = generateHazardousActivitiesReport();
                    title = "Hazardous Activities Report";
                    description = "Activities that involve chemical products requiring special safety measures.";
                    break;
                    
                case "EXTERNAL_CONTRACTORS":
                    model = generateExternalContractorsReport();
                    title = "External Contractors Report";
                    description = "Overview of activities contracted to external companies and their contact information.";
                    break;
                    
                case "SYSTEM_SUMMARY":
                    model = generateSystemSummaryReport();
                    title = "Overall System Summary";
                    description = "Comprehensive summary of the entire CMMS including key metrics and statistics.";
                    break;
                    
                case "PERFORMANCE_METRICS":
                    model = generatePerformanceMetricsReport();
                    title = "Performance Metrics Report";
                    description = "Key performance indicators and metrics for system evaluation.";
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(this, "Unknown report type: " + reportType);
                    return;
            }

            reportTitleLabel.setText(title);
            reportDescriptionArea.setText(description);
            reportTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error generating report: " + ex.getMessage(), 
                "Report Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Unexpected error: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Fixed Report Generation Methods - UPDATED TO MATCH YOUR ACTUAL SCHEMA
    private DefaultTableModel generateWorkerActivitySummary() throws SQLException {
        String sql = "SELECT " +
                    "a.type AS activity_type, " +
                    "l.type AS location_type, " +
                    "l.name AS location_name, " +
                    "COUNT(DISTINCT ass.eid) AS worker_count, " +
                    "COUNT(DISTINCT a.aid) AS activity_count " +
                    "FROM activity a " +
                    "JOIN location l ON a.lid = l.lid " +
                    "LEFT JOIN assign ass ON a.aid = ass.aid " +
                    "GROUP BY a.type, l.type, l.name " +
                    "ORDER BY a.type, worker_count DESC";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateActivityByRoleReport() throws SQLException {
        String sql = "SELECT " +
                    "e.role AS employee_role, " +
                    "a.type AS activity_type, " +
                    "COUNT(DISTINCT ass.eid) AS employee_count, " +
                    "COUNT(DISTINCT a.aid) AS activity_count " +
                    "FROM employee e " +
                    "JOIN assign ass ON e.eid = ass.eid " +
                    "JOIN activity a ON ass.aid = a.aid " +
                    "GROUP BY e.role, a.type " +
                    "ORDER BY e.role, activity_count DESC";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateWorkerAssignmentDetails() throws SQLException {
        String sql = "SELECT " +
                    "e.name AS employee_name, " +
                    "e.role AS employee_role, " +
                    "a.type AS activity_type, " +
                    "l.name AS location_name, " +
                    "a.start AS start_date, " +
                    "a.end AS end_date " +
                    "FROM employee e " +
                    "JOIN assign ass ON e.eid = ass.eid " +
                    "JOIN activity a ON ass.aid = a.aid " +
                    "JOIN location l ON a.lid = l.lid " +
                    "ORDER BY e.role, e.name, a.start";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateActivityByLocationReport() throws SQLException {
        String sql = "SELECT " +
                    "l.type AS location_type, " +
                    "l.name AS location_name, " +
                    "l.status AS maintenance_status, " +
                    "a.type AS activity_type, " +
                    "COUNT(a.aid) AS activity_count " +
                    "FROM location l " +
                    "LEFT JOIN activity a ON l.lid = a.lid " +
                    "GROUP BY l.type, l.name, l.status, a.type " +
                    "ORDER BY l.type, activity_count DESC";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateMaintenanceStatusReport() throws SQLException {
        String sql = "SELECT " +
                    "l.name AS location_name, " +
                    "l.type AS location_type, " +
                    "l.status AS maintenance_status, " +
                    "COUNT(a.aid) AS active_activities " +
                    "FROM location l " +
                    "LEFT JOIN activity a ON l.lid = a.lid " +
                    "GROUP BY l.name, l.type, l.status " +
                    "ORDER BY l.status, l.type";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateBuildingSupervisionReport() throws SQLException {
        String sql = "SELECT " +
                    "e.name AS manager_name, " +
                    "l.name AS building_name, " +
                    "l.type AS location_type, " +
                    "COUNT(DISTINCT a.aid) AS supervised_activities " +
                    "FROM employee e " +
                    "JOIN supervise s ON e.eid = s.mid " +
                    "JOIN location l ON s.lid = l.lid " +
                    "LEFT JOIN activity a ON l.lid = a.lid " +
                    "WHERE e.role LIKE '%Manager%' " +
                    "GROUP BY e.name, l.name, l.type " +
                    "ORDER BY e.name, l.name";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateActivityStatusReport() throws SQLException {
        String sql = "SELECT " +
                    "type AS activity_type, " +
                    "COUNT(*) AS activity_count, " +
                    "MIN(start) AS earliest_start, " +
                    "MAX(end) AS latest_end " +
                    "FROM activity " +
                    "GROUP BY type " +
                    "ORDER BY type";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateChemicalUsageReport() throws SQLException {
        String sql = "SELECT " +
                    "a.type AS activity_type, " +
                    "l.name AS location_name, " +
                    "p.name AS chemical_product, " +
                    "p.brand AS brand, " +
                    "COUNT(DISTINCT a.aid) AS usage_count " +
                    "FROM activity a " +
                    "JOIN location l ON a.lid = l.lid " +
                    "JOIN use u ON a.aid = u.aid " +
                    "JOIN product p ON u.pid = p.pid " +
                    "GROUP BY a.type, l.name, p.name, p.brand " +
                    "ORDER BY p.name, usage_count DESC";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateHazardousActivitiesReport() throws SQLException {
        String sql = "SELECT " +
                    "a.aid AS activity_id, " +
                    "a.type AS activity_type, " +
                    "l.name AS location_name, " +
                    "a.start AS start_date, " +
                    "a.end AS end_date, " +
                    "GROUP_CONCAT(p.name) AS chemical_products " +
                    "FROM activity a " +
                    "JOIN location l ON a.lid = l.lid " +
                    "JOIN use u ON a.aid = u.aid " +
                    "JOIN product p ON u.pid = p.pid " +
                    "GROUP BY a.aid, a.type, l.name, a.start, a.end " +
                    "ORDER BY a.start";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateExternalContractorsReport() throws SQLException {
        String sql = "SELECT " +
                    "a.aid AS activity_id, " +
                    "a.type AS activity_type, " +
                    "l.name AS location_name, " +
                    "c.name AS company_name, " +
                    "c.contact AS contact_info, " +
                    "a.start AS start_date, " +
                    "a.end AS end_date " +
                    "FROM activity a " +
                    "JOIN location l ON a.lid = l.lid " +
                    "JOIN company c ON a.cid = c.cid " +
                    "ORDER BY a.start, c.name";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generateSystemSummaryReport() throws SQLException {
        String sql = "SELECT " +
                    "metric_name, " +
                    "metric_value " +
                    "FROM (" +
                    "SELECT 'Total Employees' AS metric_name, COUNT(*) AS metric_value FROM employee " +
                    "UNION ALL " +
                    "SELECT 'Total Activities', COUNT(*) FROM activity " +
                    "UNION ALL " +
                    "SELECT 'Total Locations', COUNT(*) FROM location " +
                    "UNION ALL " +
                    "SELECT 'Active Activities', COUNT(*) FROM activity WHERE end > date('now') " +
                    "UNION ALL " +
                    "SELECT 'Activities with Chemicals', COUNT(DISTINCT aid) FROM use " +
                    "UNION ALL " +
                    "SELECT 'Activities with Companies', COUNT(DISTINCT aid) FROM activity WHERE cid IS NOT NULL " +
                    ") ORDER BY metric_name";
        return executeReportQuery(sql);
    }

    private DefaultTableModel generatePerformanceMetricsReport() throws SQLException {
        String sql = "SELECT " +
                    "metric, " +
                    "value " +
                    "FROM (" +
                    "SELECT 'Average Activities per Worker' AS metric, " +
                    "ROUND(CAST(COUNT(DISTINCT ass.aid) AS FLOAT) / COUNT(DISTINCT ass.eid), 2) AS value " +
                    "FROM assign ass " +
                    "UNION ALL " +
                    "SELECT 'Active Activities Rate', " +
                    "ROUND(CAST(SUM(CASE WHEN a.end > date('now') THEN 1 ELSE 0 END) AS FLOAT) / COUNT(*) * 100, 2) || '%' AS value " +
                    "FROM activity a " +
                    "UNION ALL " +
                    "SELECT 'Locations with Active Maintenance', " +
                    "COUNT(*) AS value FROM location WHERE status > 1 " +
                    ")";
        return executeReportQuery(sql);
    }

    private DefaultTableModel executeReportQuery(String sql) throws SQLException {
        try {
            return JDBC.buildTable(sql);
        } catch (Exception e) {
            // Fallback to empty table with error message
            System.err.println("SQL Error: " + e.getMessage());
            System.err.println("Failed SQL: " + sql);
            return new DefaultTableModel(new Object[][]{{"Error", "Failed to load data"}}, new String[]{"Error", "Message"});
        }
    }

    private void loadWorkerActivityReport() {
        generateReport("WORKER_ACTIVITY_SUMMARY");
    }
}