package guis;

import constants.CommonConstants;
import db.JDBC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/**
 * Module A: Data Management GUI
 *
 * Schema:
 * users(uid, username, password, role)
 * employee(eid, name, sex, role, birth, contact, sid)
 * location(lid, name, type, status, bid)
 * product(pid, name, brand)
 * cas(pid, cas)
 * company(cid, name, contact)
 * activity(aid, type, start, end, lid, cid)
 * supervise(mid, lid, start)
 * assign(eid, aid)
 * use(aid, pid)
 *
 * This GUI provides tuple-level operations:
 * - Insert / Update / Delete / Load for:
 *   employee, location, activity, product, company, supervise
 * - Insert / Delete / Load for:
 *   assign, use, cas
 * - Set-based insertion for:
 *   employee, location, activity, product, company,
 *   supervise, assign, use, cas
 */
public class DataManageGUI extends AdvanceForm {

    // Employee
    private JTextField empEidField, empNameField, empSexField,
            empRoleField, empBirthField, empContactField, empSidField;
    private JTable empTable;

    // Location
    private JTextField locLidField, locNameField, locTypeField,
            locStatusField, locBidField;
    private JTable locTable;

    // Activity
    private JTextField actAidField, actTypeField, actStartField,
            actEndField, actLidField, actCidField;
    private JTable actTable;

    // Product
    private JTextField prodPidField, prodNameField, prodBrandField;
    private JTable prodTable;

    // Company
    private JTextField compCidField, compNameField, compContactField;
    private JTable compTable;

    // Supervise relation: supervise(mid, lid, start)
    private JTextField supMidField, supLidField, supStartField;
    private JTable supTable;

    // Assign relation: assign(eid, aid)
    private JTextField asnEidField, asnAidField;
    private JTable asnTable;

    // Use relation: use(aid, pid)
    private JTextField useAidField, usePidField;
    private JTable useTable;

    // CAS relation: cas(pid, cas)
    private JTextField casPidField, casCasField;
    private JTable casTable;

    // Set-based insertion
    private JComboBox<String> setTableCombo;
    private JTextArea setInputArea;
    private JLabel setFormatLabel;

    public DataManageGUI() {
        super("CMMS - Data Management (Module A)");
        getRootPane().setDefaultButton(null);
        initializeGUI();
    }

    private void initializeGUI() {
        getContentPane().setBackground(CommonConstants.PRIMAR_COLOR);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Campus Maintenance and Management System - Module A (Data Management)");
        title.setForeground(CommonConstants.TEXT_COLOR);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(CommonConstants.PRIMAR_COLOR);
        tabbedPane.setForeground(CommonConstants.TEXT_COLOR);

        tabbedPane.addTab("Employee", createEmployeePanel());
        tabbedPane.addTab("Location", createLocationPanel());
        tabbedPane.addTab("Activity", createActivityPanel());
        tabbedPane.addTab("Product", createProductPanel());
        tabbedPane.addTab("Company", createCompanyPanel());
        tabbedPane.addTab("Supervise", createSupervisePanel());
        tabbedPane.addTab("Assign", createAssignPanel());
        tabbedPane.addTab("Use", createUsePanel());
        tabbedPane.addTab("CAS", createCasPanel());
        tabbedPane.addTab("Set-based Insert", createSetBasedPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    /* =====================================================================
     * Employee tab
     * ===================================================================== */

    private JPanel createEmployeePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 7, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "employee(eid, name, sex, role, birth, contact, sid)"
        ));

        empEidField = createTextField();
        empNameField = createTextField();
        empSexField = createTextField();
        empRoleField = createTextField();
        empBirthField = createTextField();
        empContactField = createTextField();
        empSidField = createTextField();

        form.add(createLabel("eid (PK)"));
        form.add(createLabel("name"));
        form.add(createLabel("sex"));
        form.add(createLabel("role"));
        form.add(createLabel("birth"));
        form.add(createLabel("contact"));
        form.add(createLabel("sid (supervisor eid, optional)"));

        form.add(empEidField);
        form.add(empNameField);
        form.add(empSexField);
        form.add(empRoleField);
        form.add(empBirthField);
        form.add(empContactField);
        form.add(empSidField);

        panel.add(form, BorderLayout.NORTH);

        empTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(empTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM employee", empTable)));
        btnPanel.add(createActionButton("Insert", this::insertEmployee));
        btnPanel.add(createActionButton("Update", this::updateEmployee));
        btnPanel.add(createActionButton("Delete", this::deleteEmployee));
        btnPanel.add(createActionButton("Clear", e -> clearEmployeeFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertEmployee(ActionEvent e) {
        String sql = "INSERT INTO employee (eid, name, sex, role, birth, contact, sid) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(empEidField.getText().trim()));
            ps.setString(2, empNameField.getText().trim());
            ps.setString(3, empSexField.getText().trim());
            ps.setString(4, empRoleField.getText().trim());
            ps.setString(5, empBirthField.getText().trim());
            ps.setString(6, empContactField.getText().trim());

            String sidText = empSidField.getText().trim();
            if (sidText.isEmpty()) {
                ps.setNull(7, Types.INTEGER);
            } else {
                ps.setInt(7, Integer.parseInt(sidText));
            }

            int rows = ps.executeUpdate();
            showInfo(rows + " employee(s) inserted.");
            loadTable("SELECT * FROM employee", empTable);

        } catch (Exception ex) {
            showError("Employee insert failed: " + ex.getMessage());
        }
    }

    private void updateEmployee(ActionEvent e) {
        String sql = "UPDATE employee SET name=?, sex=?, role=?, birth=?, contact=?, sid=? WHERE eid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empNameField.getText().trim());
            ps.setString(2, empSexField.getText().trim());
            ps.setString(3, empRoleField.getText().trim());
            ps.setString(4, empBirthField.getText().trim());
            ps.setString(5, empContactField.getText().trim());

            String sidText = empSidField.getText().trim();
            if (sidText.isEmpty()) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, Integer.parseInt(sidText));
            }

            ps.setInt(7, Integer.parseInt(empEidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " employee(s) updated.");
            loadTable("SELECT * FROM employee", empTable);

        } catch (Exception ex) {
            showError("Employee update failed: " + ex.getMessage());
        }
    }

    private void deleteEmployee(ActionEvent e) {
        String sql = "DELETE FROM employee WHERE eid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(empEidField.getText().trim()));
            int rows = ps.executeUpdate();
            showInfo(rows + " employee(s) deleted.");
            loadTable("SELECT * FROM employee", empTable);

        } catch (Exception ex) {
            showError("Employee delete failed: " + ex.getMessage());
        }
    }

    private void clearEmployeeFields() {
        empEidField.setText("");
        empNameField.setText("");
        empSexField.setText("");
        empRoleField.setText("");
        empBirthField.setText("");
        empContactField.setText("");
        empSidField.setText("");
    }

    /* =====================================================================
     * Location tab
     * ===================================================================== */

    private JPanel createLocationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 5, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "location(lid, name, type, status, bid)"
        ));

        locLidField = createTextField();
        locNameField = createTextField();
        locTypeField = createTextField();
        locStatusField = createTextField();
        locBidField = createTextField();

        form.add(createLabel("lid (PK)"));
        form.add(createLabel("name (UNIQUE)"));
        form.add(createLabel("type"));
        form.add(createLabel("status (INTEGER, default 1)"));
        form.add(createLabel("bid (parent lid, optional)"));

        form.add(locLidField);
        form.add(locNameField);
        form.add(locTypeField);
        form.add(locStatusField);
        form.add(locBidField);

        panel.add(form, BorderLayout.NORTH);

        locTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(locTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM location", locTable)));
        btnPanel.add(createActionButton("Insert", this::insertLocation));
        btnPanel.add(createActionButton("Update", this::updateLocation));
        btnPanel.add(createActionButton("Delete", this::deleteLocation));
        btnPanel.add(createActionButton("Clear", e -> clearLocationFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertLocation(ActionEvent e) {
        String sql = "INSERT INTO location (lid, name, type, status, bid) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(locLidField.getText().trim()));
            ps.setString(2, locNameField.getText().trim());
            ps.setString(3, locTypeField.getText().trim());

            String statusText = locStatusField.getText().trim();
            if (statusText.isEmpty()) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, Integer.parseInt(statusText));
            }

            String bidText = locBidField.getText().trim();
            if (bidText.isEmpty()) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, Integer.parseInt(bidText));
            }

            int rows = ps.executeUpdate();
            showInfo(rows + " location(s) inserted.");
            loadTable("SELECT * FROM location", locTable);

        } catch (Exception ex) {
            showError("Location insert failed: " + ex.getMessage());
        }
    }

    private void updateLocation(ActionEvent e) {
        String sql = "UPDATE location SET name=?, type=?, status=?, bid=? WHERE lid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, locNameField.getText().trim());
            ps.setString(2, locTypeField.getText().trim());

            String statusText = locStatusField.getText().trim();
            if (statusText.isEmpty()) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, Integer.parseInt(statusText));
            }

            String bidText = locBidField.getText().trim();
            if (bidText.isEmpty()) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, Integer.parseInt(bidText));
            }

            ps.setInt(5, Integer.parseInt(locLidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " location(s) updated.");
            loadTable("SELECT * FROM location", locTable);

        } catch (Exception ex) {
            showError("Location update failed: " + ex.getMessage());
        }
    }

    private void deleteLocation(ActionEvent e) {
        String sql = "DELETE FROM location WHERE lid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(locLidField.getText().trim()));
            int rows = ps.executeUpdate();
            showInfo(rows + " location(s) deleted.");
            loadTable("SELECT * FROM location", locTable);

        } catch (Exception ex) {
            showError("Location delete failed: " + ex.getMessage());
        }
    }

    private void clearLocationFields() {
        locLidField.setText("");
        locNameField.setText("");
        locTypeField.setText("");
        locStatusField.setText("");
        locBidField.setText("");
    }

    /* =====================================================================
     * Activity tab
     * ===================================================================== */

    private JPanel createActivityPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 6, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "activity(aid, type, start, end, lid, cid)"
        ));

        actAidField = createTextField();
        actTypeField = createTextField();
        actStartField = createTextField();
        actEndField = createTextField();
        actLidField = createTextField();
        actCidField = createTextField();

        form.add(createLabel("aid (PK)"));
        form.add(createLabel("type"));
        form.add(createLabel("start"));
        form.add(createLabel("end"));
        form.add(createLabel("lid (FK to location.lid)"));
        form.add(createLabel("cid (FK to company.cid)"));

        form.add(actAidField);
        form.add(actTypeField);
        form.add(actStartField);
        form.add(actEndField);
        form.add(actLidField);
        form.add(actCidField);

        panel.add(form, BorderLayout.NORTH);

        actTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(actTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM activity", actTable)));
        btnPanel.add(createActionButton("Insert", this::insertActivity));
        btnPanel.add(createActionButton("Update", this::updateActivity));
        btnPanel.add(createActionButton("Delete", this::deleteActivity));
        btnPanel.add(createActionButton("Clear", e -> clearActivityFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertActivity(ActionEvent e) {
        String sql = "INSERT INTO activity (aid, type, start, end, lid, cid) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(actAidField.getText().trim()));
            ps.setString(2, actTypeField.getText().trim());
            ps.setString(3, actStartField.getText().trim());
            ps.setString(4, actEndField.getText().trim());

            String lidText = actLidField.getText().trim();
            if (lidText.isEmpty()) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, Integer.parseInt(lidText));
            }

            String cidText = actCidField.getText().trim();
            if (cidText.isEmpty()) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, Integer.parseInt(cidText));
            }

            int rows = ps.executeUpdate();
            showInfo(rows + " activity(ies) inserted.");
            loadTable("SELECT * FROM activity", actTable);

        } catch (Exception ex) {
            showError("Activity insert failed: " + ex.getMessage());
        }
    }

    private void updateActivity(ActionEvent e) {
        String sql = "UPDATE activity SET type=?, start=?, end=?, lid=?, cid=? WHERE aid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, actTypeField.getText().trim());
            ps.setString(2, actStartField.getText().trim());
            ps.setString(3, actEndField.getText().trim());

            String lidText = actLidField.getText().trim();
            if (lidText.isEmpty()) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, Integer.parseInt(lidText));
            }

            String cidText = actCidField.getText().trim();
            if (cidText.isEmpty()) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, Integer.parseInt(cidText));
            }

            ps.setInt(6, Integer.parseInt(actAidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " activity(ies) updated.");
            loadTable("SELECT * FROM activity", actTable);

        } catch (Exception ex) {
            showError("Activity update failed: " + ex.getMessage());
        }
    }

    private void deleteActivity(ActionEvent e) {
        String sql = "DELETE FROM activity WHERE aid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(actAidField.getText().trim()));
            int rows = ps.executeUpdate();
            showInfo(rows + " activity(ies) deleted.");
            loadTable("SELECT * FROM activity", actTable);

        } catch (Exception ex) {
            showError("Activity delete failed: " + ex.getMessage());
        }
    }

    private void clearActivityFields() {
        actAidField.setText("");
        actTypeField.setText("");
        actStartField.setText("");
        actEndField.setText("");
        actLidField.setText("");
        actCidField.setText("");
    }

    /* =====================================================================
     * Product tab
     * ===================================================================== */

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 3, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "product(pid, name, brand)"
        ));

        prodPidField = createTextField();
        prodNameField = createTextField();
        prodBrandField = createTextField();

        form.add(createLabel("pid (PK)"));
        form.add(createLabel("name"));
        form.add(createLabel("brand"));

        form.add(prodPidField);
        form.add(prodNameField);
        form.add(prodBrandField);

        panel.add(form, BorderLayout.NORTH);

        prodTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(prodTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM product", prodTable)));
        btnPanel.add(createActionButton("Insert", this::insertProduct));
        btnPanel.add(createActionButton("Update", this::updateProduct));
        btnPanel.add(createActionButton("Delete", this::deleteProduct));
        btnPanel.add(createActionButton("Clear", e -> clearProductFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertProduct(ActionEvent e) {
        String sql = "INSERT INTO product (pid, name, brand) VALUES (?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(prodPidField.getText().trim()));
            ps.setString(2, prodNameField.getText().trim());
            ps.setString(3, prodBrandField.getText().trim());

            int rows = ps.executeUpdate();
            showInfo(rows + " product(s) inserted.");
            loadTable("SELECT * FROM product", prodTable);

        } catch (Exception ex) {
            showError("Product insert failed: " + ex.getMessage());
        }
    }

    private void updateProduct(ActionEvent e) {
        String sql = "UPDATE product SET name=?, brand=? WHERE pid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodNameField.getText().trim());
            ps.setString(2, prodBrandField.getText().trim());
            ps.setInt(3, Integer.parseInt(prodPidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " product(s) updated.");
            loadTable("SELECT * FROM product", prodTable);

        } catch (Exception ex) {
            showError("Product update failed: " + ex.getMessage());
        }
    }

    private void deleteProduct(ActionEvent e) {
        String sql = "DELETE FROM product WHERE pid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(prodPidField.getText().trim()));
            int rows = ps.executeUpdate();
            showInfo(rows + " product(s) deleted.");
            loadTable("SELECT * FROM product", prodTable);

        } catch (Exception ex) {
            showError("Product delete failed: " + ex.getMessage());
        }
    }

    private void clearProductFields() {
        prodPidField.setText("");
        prodNameField.setText("");
        prodBrandField.setText("");
    }

    /* =====================================================================
     * Company tab
     * ===================================================================== */

    private JPanel createCompanyPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 3, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "company(cid, name, contact)"
        ));

        compCidField = createTextField();
        compNameField = createTextField();
        compContactField = createTextField();

        form.add(createLabel("cid (PK)"));
        form.add(createLabel("name"));
        form.add(createLabel("contact"));

        form.add(compCidField);
        form.add(compNameField);
        form.add(compContactField);

        panel.add(form, BorderLayout.NORTH);

        compTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(compTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM company", compTable)));
        btnPanel.add(createActionButton("Insert", this::insertCompany));
        btnPanel.add(createActionButton("Update", this::updateCompany));
        btnPanel.add(createActionButton("Delete", this::deleteCompany));
        btnPanel.add(createActionButton("Clear", e -> clearCompanyFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertCompany(ActionEvent e) {
        String sql = "INSERT INTO company (cid, name, contact) VALUES (?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(compCidField.getText().trim()));
            ps.setString(2, compNameField.getText().trim());
            ps.setString(3, compContactField.getText().trim());

            int rows = ps.executeUpdate();
            showInfo(rows + " company(ies) inserted.");
            loadTable("SELECT * FROM company", compTable);

        } catch (Exception ex) {
            showError("Company insert failed: " + ex.getMessage());
        }
    }

    private void updateCompany(ActionEvent e) {
        String sql = "UPDATE company SET name=?, contact=? WHERE cid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, compNameField.getText().trim());
            ps.setString(2, compContactField.getText().trim());
            ps.setInt(3, Integer.parseInt(compCidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " company(ies) updated.");
            loadTable("SELECT * FROM company", compTable);

        } catch (Exception ex) {
            showError("Company update failed: " + ex.getMessage());
        }
    }

    private void deleteCompany(ActionEvent e) {
        String sql = "DELETE FROM company WHERE cid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(compCidField.getText().trim()));
            int rows = ps.executeUpdate();
            showInfo(rows + " company(ies) deleted.");
            loadTable("SELECT * FROM company", compTable);

        } catch (Exception ex) {
            showError("Company delete failed: " + ex.getMessage());
        }
    }

    private void clearCompanyFields() {
        compCidField.setText("");
        compNameField.setText("");
        compContactField.setText("");
    }

    /* =====================================================================
     * Supervise tab
     * supervise(mid, lid, start)
     * ===================================================================== */

    private JPanel createSupervisePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 3, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "supervise(mid, lid, start)"
        ));

        supMidField = createTextField();
        supLidField = createTextField();
        supStartField = createTextField();

        form.add(createLabel("mid (manager eid)"));
        form.add(createLabel("lid (location id)"));
        form.add(createLabel("start (date)"));

        form.add(supMidField);
        form.add(supLidField);
        form.add(supStartField);

        panel.add(form, BorderLayout.NORTH);

        supTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(supTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM supervise", supTable)));
        btnPanel.add(createActionButton("Insert", this::insertSupervise));
        btnPanel.add(createActionButton("Update", this::updateSupervise));
        btnPanel.add(createActionButton("Delete", this::deleteSupervise));
        btnPanel.add(createActionButton("Clear", e -> clearSuperviseFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertSupervise(ActionEvent e) {
        String sql = "INSERT INTO supervise (mid, lid, start) VALUES (?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(supMidField.getText().trim()));
            ps.setInt(2, Integer.parseInt(supLidField.getText().trim()));
            ps.setString(3, supStartField.getText().trim());

            int rows = ps.executeUpdate();
            showInfo(rows + " supervise relation(s) inserted.");
            loadTable("SELECT * FROM supervise", supTable);

        } catch (Exception ex) {
            showError("Supervise insert failed: " + ex.getMessage());
        }
    }

    private void updateSupervise(ActionEvent e) {
        // Only update 'start'; (mid,lid) is PK
        String sql = "UPDATE supervise SET start=? WHERE mid=? AND lid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, supStartField.getText().trim());
            ps.setInt(2, Integer.parseInt(supMidField.getText().trim()));
            ps.setInt(3, Integer.parseInt(supLidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " supervise relation(s) updated.");
            loadTable("SELECT * FROM supervise", supTable);

        } catch (Exception ex) {
            showError("Supervise update failed: " + ex.getMessage());
        }
    }

    private void deleteSupervise(ActionEvent e) {
        String sql = "DELETE FROM supervise WHERE mid=? AND lid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(supMidField.getText().trim()));
            ps.setInt(2, Integer.parseInt(supLidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " supervise relation(s) deleted.");
            loadTable("SELECT * FROM supervise", supTable);

        } catch (Exception ex) {
            showError("Supervise delete failed: " + ex.getMessage());
        }
    }

    private void clearSuperviseFields() {
        supMidField.setText("");
        supLidField.setText("");
        supStartField.setText("");
    }

    /* =====================================================================
     * Assign tab
     * assign(eid, aid)
     * ===================================================================== */

    private JPanel createAssignPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "assign(eid, aid)"
        ));

        asnEidField = createTextField();
        asnAidField = createTextField();

        form.add(createLabel("eid (employee id)"));
        form.add(createLabel("aid (activity id)"));

        form.add(asnEidField);
        form.add(asnAidField);

        panel.add(form, BorderLayout.NORTH);

        asnTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(asnTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM assign", asnTable)));
        btnPanel.add(createActionButton("Insert", this::insertAssign));
        btnPanel.add(createActionButton("Delete", this::deleteAssign));
        btnPanel.add(createActionButton("Clear", e -> clearAssignFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertAssign(ActionEvent e) {
        String sql = "INSERT INTO assign (eid, aid) VALUES (?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(asnEidField.getText().trim()));
            ps.setInt(2, Integer.parseInt(asnAidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " assign relation(s) inserted.");
            loadTable("SELECT * FROM assign", asnTable);

        } catch (Exception ex) {
            showError("Assign insert failed: " + ex.getMessage());
        }
    }

    private void deleteAssign(ActionEvent e) {
        String sql = "DELETE FROM assign WHERE eid=? AND aid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(asnEidField.getText().trim()));
            ps.setInt(2, Integer.parseInt(asnAidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " assign relation(s) deleted.");
            loadTable("SELECT * FROM assign", asnTable);

        } catch (Exception ex) {
            showError("Assign delete failed: " + ex.getMessage());
        }
    }

    private void clearAssignFields() {
        asnEidField.setText("");
        asnAidField.setText("");
    }

    /* =====================================================================
     * Use tab
     * use(aid, pid)
     * ===================================================================== */

    private JPanel createUsePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "use(aid, pid)"
        ));

        useAidField = createTextField();
        usePidField = createTextField();

        form.add(createLabel("aid (activity id)"));
        form.add(createLabel("pid (product id)"));

        form.add(useAidField);
        form.add(usePidField);

        panel.add(form, BorderLayout.NORTH);

        useTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(useTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM use", useTable)));
        btnPanel.add(createActionButton("Insert", this::insertUse));
        btnPanel.add(createActionButton("Delete", this::deleteUse));
        btnPanel.add(createActionButton("Clear", e -> clearUseFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertUse(ActionEvent e) {
        String sql = "INSERT INTO use (aid, pid) VALUES (?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(useAidField.getText().trim()));
            ps.setInt(2, Integer.parseInt(usePidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " use relation(s) inserted.");
            loadTable("SELECT * FROM use", useTable);

        } catch (Exception ex) {
            showError("Use insert failed: " + ex.getMessage());
        }
    }

    private void deleteUse(ActionEvent e) {
        String sql = "DELETE FROM use WHERE aid=? AND pid=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(useAidField.getText().trim()));
            ps.setInt(2, Integer.parseInt(usePidField.getText().trim()));

            int rows = ps.executeUpdate();
            showInfo(rows + " use relation(s) deleted.");
            loadTable("SELECT * FROM use", useTable);

        } catch (Exception ex) {
            showError("Use delete failed: " + ex.getMessage());
        }
    }

    private void clearUseFields() {
        useAidField.setText("");
        usePidField.setText("");
    }

    /* =====================================================================
     * CAS tab
     * cas(pid, cas)
     * ===================================================================== */

    private JPanel createCasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        form.setBackground(CommonConstants.SECONDARY_COLOR);
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR),
                "cas(pid, cas)"
        ));

        casPidField = createTextField();
        casCasField = createTextField();

        form.add(createLabel("pid (product id)"));
        form.add(createLabel("cas (CAS number)"));

        form.add(casPidField);
        form.add(casCasField);

        panel.add(form, BorderLayout.NORTH);

        casTable = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(casTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(CommonConstants.PRIMAR_COLOR);
        btnPanel.add(createActionButton("Load All", e -> loadTable("SELECT * FROM cas", casTable)));
        btnPanel.add(createActionButton("Insert", this::insertCas));
        btnPanel.add(createActionButton("Delete", this::deleteCas));
        btnPanel.add(createActionButton("Clear", e -> clearCasFields()));

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void insertCas(ActionEvent e) {
        String sql = "INSERT INTO cas (pid, cas) VALUES (?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(casPidField.getText().trim()));
            ps.setString(2, casCasField.getText().trim());

            int rows = ps.executeUpdate();
            showInfo(rows + " cas relation(s) inserted.");
            loadTable("SELECT * FROM cas", casTable);

        } catch (Exception ex) {
            showError("CAS insert failed: " + ex.getMessage());
        }
    }

    private void deleteCas(ActionEvent e) {
        String sql = "DELETE FROM cas WHERE pid=? AND cas=?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(casPidField.getText().trim()));
            ps.setString(2, casCasField.getText().trim());

            int rows = ps.executeUpdate();
            showInfo(rows + " cas relation(s) deleted.");
            loadTable("SELECT * FROM cas", casTable);

        } catch (Exception ex) {
            showError("CAS delete failed: " + ex.getMessage());
        }
    }

    private void clearCasFields() {
        casPidField.setText("");
        casCasField.setText("");
    }

    /* =====================================================================
     * Set-based insertion tab
     * ===================================================================== */

    private JPanel createSetBasedPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CommonConstants.PRIMAR_COLOR);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(CommonConstants.PRIMAR_COLOR);

        setTableCombo = new JComboBox<>(new String[]{
                "employee",
                "location",
                "activity",
                "product",
                "company",
                "supervise",
                "assign",
                "use",
                "cas"
        });
        setTableCombo.addActionListener(e -> updateSetFormatLabel());

        setFormatLabel = createLabel("");
        updateSetFormatLabel();

        top.add(createLabel("Target relation:"));
        top.add(setTableCombo);
        top.add(setFormatLabel);

        panel.add(top, BorderLayout.NORTH);

        setInputArea = new JTextArea(15, 80);
        setInputArea.setBackground(CommonConstants.SECONDARY_COLOR);
        setInputArea.setForeground(CommonConstants.TEXT_COLOR);
        setInputArea.setCaretColor(CommonConstants.CURSOR_COLOR);
        setInputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(setInputArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(CommonConstants.PRIMAR_COLOR);

        JButton exampleBtn = createActionButton("Fill Example", e -> fillSetExample());
        JButton insertBtn = createActionButton("Insert Lines", e -> performSetInsert());

        bottom.add(exampleBtn);
        bottom.add(insertBtn);

        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void updateSetFormatLabel() {
        String table = (String) setTableCombo.getSelectedItem();
        String text;
        if ("employee".equals(table)) {
            text = "Format: eid,name,sex,role,birth,contact,sid";
        } else if ("location".equals(table)) {
            text = "Format: lid,name,type,status,bid";
        } else if ("activity".equals(table)) {
            text = "Format: aid,type,start,end,lid,cid";
        } else if ("product".equals(table)) {
            text = "Format: pid,name,brand";
        } else if ("company".equals(table)) {
            text = "Format: cid,name,contact";
        } else if ("supervise".equals(table)) {
            text = "Format: mid,lid,start";
        } else if ("assign".equals(table)) {
            text = "Format: eid,aid";
        } else if ("use".equals(table)) {
            text = "Format: aid,pid";
        } else if ("cas".equals(table)) {
            text = "Format: pid,cas";
        } else {
            text = "";
        }
        setFormatLabel.setText(text);
    }

    private void fillSetExample() {
        String table = (String) setTableCombo.getSelectedItem();
        String example;
        if ("employee".equals(table)) {
            example = "1,John Doe,M,Manager,1980-01-01,1234 5678,\n" +
                    "2,Jane Chan,F,Worker,1995-03-10,9876 5432,1";
        } else if ("location".equals(table)) {
            example = "101,Block A,Building,1,\n" +
                    "102,Room 201,Room,1,101";
        } else if ("activity".equals(table)) {
            example = "1001,Cleaning,2025-01-01,2025-01-02,102,\n" +
                    "1002,Renovation,2025-02-01,2025-02-10,101,1";
        } else if ("product".equals(table)) {
            example = "1,Bleach,BrandA\n" +
                    "2,Detergent,BrandB";
        } else if ("company".equals(table)) {
            example = "1,CleanCo,+852-1234-5678\n" +
                    "2,FixIt Ltd,+852-2345-6789";
        } else if ("supervise".equals(table)) {
            example = "1,101,2025-01-01\n" +
                    "1,102,2025-01-01";
        } else if ("assign".equals(table)) {
            example = "2,1001\n" +
                    "2,1002";
        } else if ("use".equals(table)) {
            example = "1001,1\n" +
                    "1001,2";
        } else if ("cas".equals(table)) {
            example = "1,123-45-6\n" +
                    "2,789-01-2";
        } else {
            example = "";
        }
        setInputArea.setText(example);
    }

    private void performSetInsert() {
        String table = (String) setTableCombo.getSelectedItem();
        String text = setInputArea.getText();
        if (text.trim().isEmpty()) {
            showError("Input area is empty.");
            return;
        }

        String[] lines = text.split("\\R");
        int successCount = 0;
        int failCount = 0;

        try (Connection conn = JDBC.getConnection()) {
            conn.setAutoCommit(false);

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                try {
                    if ("employee".equals(table)) {
                        if (parts.length != 7) throw new IllegalArgumentException("Expected 7 values");
                        insertEmployeeLine(conn, parts);
                    } else if ("location".equals(table)) {
                        if (parts.length != 5) throw new IllegalArgumentException("Expected 5 values");
                        insertLocationLine(conn, parts);
                    } else if ("activity".equals(table)) {
                        if (parts.length != 6) throw new IllegalArgumentException("Expected 6 values");
                        insertActivityLine(conn, parts);
                    } else if ("product".equals(table)) {
                        if (parts.length != 3) throw new IllegalArgumentException("Expected 3 values");
                        insertProductLine(conn, parts);
                    } else if ("company".equals(table)) {
                        if (parts.length != 3) throw new IllegalArgumentException("Expected 3 values");
                        insertCompanyLine(conn, parts);
                    } else if ("supervise".equals(table)) {
                        if (parts.length != 3) throw new IllegalArgumentException("Expected 3 values");
                        insertSuperviseLine(conn, parts);
                    } else if ("assign".equals(table)) {
                        if (parts.length != 2) throw new IllegalArgumentException("Expected 2 values");
                        insertAssignLine(conn, parts);
                    } else if ("use".equals(table)) {
                        if (parts.length != 2) throw new IllegalArgumentException("Expected 2 values");
                        insertUseLine(conn, parts);
                    } else if ("cas".equals(table)) {
                        if (parts.length != 2) throw new IllegalArgumentException("Expected 2 values");
                        insertCasLine(conn, parts);
                    }
                    successCount++;
                } catch (Exception exLine) {
                    failCount++;
                    System.err.println("Line failed: \"" + line + "\"");
                    exLine.printStackTrace();
                }
            }

            conn.commit();
            showInfo("Set-based insertion finished. Success: " + successCount + ", Failed: " + failCount);

        } catch (SQLException ex) {
            showError("Set-based insertion failed: " + ex.getMessage());
        }
    }

    // Helpers for set-based insert

    private void insertEmployeeLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO employee (eid, name, sex, role, birth, contact, sid) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setString(2, p[1]);
            ps.setString(3, p[2]);
            ps.setString(4, p[3]);
            ps.setString(5, p[4]);
            ps.setString(6, p[5]);
            if (p[6].isEmpty()) {
                ps.setNull(7, Types.INTEGER);
            } else {
                ps.setInt(7, Integer.parseInt(p[6]));
            }
            ps.executeUpdate();
        }
    }

    private void insertLocationLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO location (lid, name, type, status, bid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setString(2, p[1]);
            ps.setString(3, p[2]);
            if (p[3].isEmpty()) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, Integer.parseInt(p[3]));
            }
            if (p[4].isEmpty()) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, Integer.parseInt(p[4]));
            }
            ps.executeUpdate();
        }
    }

    private void insertActivityLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO activity (aid, type, start, end, lid, cid) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setString(2, p[1]);
            ps.setString(3, p[2]);
            ps.setString(4, p[3]);
            if (p[4].isEmpty()) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, Integer.parseInt(p[4]));
            }
            if (p[5].isEmpty()) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, Integer.parseInt(p[5]));
            }
            ps.executeUpdate();
        }
    }

    private void insertProductLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO product (pid, name, brand) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setString(2, p[1]);
            ps.setString(3, p[2]);
            ps.executeUpdate();
        }
    }

    private void insertCompanyLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO company (cid, name, contact) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setString(2, p[1]);
            ps.setString(3, p[2]);
            ps.executeUpdate();
        }
    }

    private void insertSuperviseLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO supervise (mid, lid, start) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setInt(2, Integer.parseInt(p[1]));
            ps.setString(3, p[2]);
            ps.executeUpdate();
        }
    }

    private void insertAssignLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO assign (eid, aid) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setInt(2, Integer.parseInt(p[1]));
            ps.executeUpdate();
        }
    }

    private void insertUseLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO use (aid, pid) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setInt(2, Integer.parseInt(p[1]));
            ps.executeUpdate();
        }
    }

    private void insertCasLine(Connection conn, String[] p) throws SQLException {
        String sql = "INSERT INTO cas (pid, cas) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(p[0]));
            ps.setString(2, p[1]);
            ps.executeUpdate();
        }
    }

    /* =====================================================================
     * Common helpers
     * ===================================================================== */

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setBackground(CommonConstants.SECONDARY_COLOR);
        field.setForeground(CommonConstants.TEXT_COLOR);
        field.setCaretColor(CommonConstants.CURSOR_COLOR);
        field.setFont(new Font("Dialog", Font.PLAIN, 14));
        return field;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(CommonConstants.TEXT_COLOR);
        label.setFont(new Font("Dialog", Font.PLAIN, 12));
        return label;
    }

    private JButton createActionButton(String text, java.util.function.Consumer<ActionEvent> handler) {
        JButton button = new JButton(text);
        button.setBackground(CommonConstants.TEXT_COLOR);
        button.setForeground(Color.BLACK);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Dialog", Font.PLAIN, 14));
        button.addActionListener(e -> handler.accept(e));
        return button;
    }

    private void loadTable(String sql, JTable table) {
        try (Connection conn = JDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            DefaultTableModel model = buildTableModel(rs);
            table.setModel(model);

        } catch (SQLException ex) {
            showError("Load failed: " + ex.getMessage());
        }
    }

    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = meta.getColumnName(i);
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }
        return model;
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Optional main for local testing
    public static void main(String[] args) {
        JDBC.testConnection();
        SwingUtilities.invokeLater(() -> {
            DataManageGUI gui = new DataManageGUI();
            gui.setSize(1000, 700);
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        });
    }
}
