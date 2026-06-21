package db;

import constants.CommonConstants;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class JDBC {
    // Connection setting
    private static final String DB_URL = "jdbc:sqlite:cmms_database.db";
    private static Connection connection;

    // Connect functions
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void testConnection() {
        try {
            // Always check if connection is valid before returning
            if (connection == null || connection.isClosed()) {
                // Load SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");

                // Create new connection
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found!");
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
        }

    }

    // register new
    public static boolean register(String username, String password, String registerKey) {
        try (Connection connection = getConnection()) {
            int role;
            if (registerKey.isEmpty())
                role = 2;
            else role = 1;

            System.out.println("Attempting to register user: " + username);

            // check if user already exists
            PreparedStatement checkUserExist = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            checkUserExist.setString(1, username);
            ResultSet resultSet = checkUserExist.executeQuery();

            // check next tuple from resultSet
            if (resultSet.next()) {
                System.out.println("User already exists: " + username);
                return false;
            }

            // Insert new user
            PreparedStatement insertUser = connection.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
            insertUser.setString(1, username);
            insertUser.setString(2, password);
            insertUser.setInt(3, role);
            insertUser.executeUpdate();

            // Verify the user was actually inserted
            // Show all users after registration
            viewAllUsers();

            return true;

        } catch (SQLException e) {
            System.err.println("Registration failed for user: " + username);
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Validate login
    public static boolean validLogin(String username, String password) {
        try (Connection connection = getConnection()) {
            PreparedStatement validateUser = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    // role from username
    public static int role() {
        try (Connection connection = getConnection()) {
            PreparedStatement validateUser = connection.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?");
            validateUser.setString(1, CommonConstants.username);
            validateUser.setString(2, CommonConstants.password);

            ResultSet resultSet = validateUser.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("role");
            else return -1;

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return -1;
        }
    }

    // change SQL command into DefaultTableModel (used: MainGUI Module B)
    public static DefaultTableModel buildTable(String command) {
        try (Connection connection = getConnection()) {
            PreparedStatement queryData = connection.prepareStatement(command);
            ResultSet resultSet = queryData.executeQuery();

            return toTable(resultSet);

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return new DefaultTableModel();
        }
    }

    //ResultSet -> DefaultTableModel
    public static DefaultTableModel toTable(ResultSet resultSet) throws SQLException {
        // Structure of table
        ResultSetMetaData metaData = resultSet.getMetaData();
        int column = metaData.getColumnCount();

        // Get names in table
        Vector<String> names = new Vector<>();
        for (int i = 1; i <= column; i++) {
            names.add(metaData.getColumnName(i));
        }

        // Get records in table
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= column; i++)
                row.add(resultSet.getObject(i));
            data.add(row);
        }

        return new DefaultTableModel(data, names);
    }

    // get the cleaning activities schedule from database into CleaningGUI
    public static DefaultTableModel getCleaningActivities(String startDate, String endDate, String selectBuilding) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = getConnection();
            String sql = "SELECT l.name AS Location, l.bid AS Building, " +
                    "a.start AS Start_Time, a.end AS End_Time, l.status AS Status, p.name AS Harmful_Chemical " +
                    "FROM activity a " +
                    "JOIN location l ON a.lid = l.lid " +
                    "JOIN use u ON a.aid = u.aid " +
                    "JOIN product p ON u.pid = p.pid " +
                    "WHERE a.start <= ? AND a.end >= ? AND l.name = ? " +
                    "ORDER BY a.start";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, endDate);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, selectBuilding);

            resultSet = preparedStatement.executeQuery();
            return toTable(resultSet);

        } catch (SQLException e) {
            return new DefaultTableModel();
        }
    }

    // view all users (for testing)
    public static void viewAllUsers() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM users")) {

            System.out.println("All the users in database:");
            // check whether contain the users in database
            boolean contain = false;

            while (result.next()) {
                contain = true;
                String role = "";
                if (result.getInt("role") == 1)
                    role = "admin";
                else if (result.getInt("role") == 2)
                    role = "user";
                System.out.println("ID: " + result.getInt("uid") +
                        ", Username: " + result.getString("username") +
                        ", Password: " + result.getString("password") +
                        ", Role: " + role);
            }
            if (!contain) {
                System.out.println("No users found in database.");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing users: " + e.getMessage());
        }
    }
}