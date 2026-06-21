import db.JDBC;
import guis.LoginFormGUI;

public class AppLauncher {
    public static void main(String[] args) {
        try {
            // Test connection
            JDBC.testConnection();

            System.out.println("CMMS database is connected!");

            // Show all current users (for debugging)
            JDBC.viewAllUsers();

            // Launch your login form
            new LoginFormGUI().setVisible(true);

        } catch (Exception e) {
            System.err.println("Failed to start CMMS application: " + e.getMessage());
        }
    }
}