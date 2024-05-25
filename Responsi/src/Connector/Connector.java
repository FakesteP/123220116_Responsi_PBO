package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {
    private String username;
    private String password;

    public Connector(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String db = "university";
    private static String url = "jdbc:mysql://localhost:3306/" + db;
    private static String user = "root";
    private static String pass = "";

    public static Connection Connect() {
        Connection conn = null;
        try {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("MySQL Connected");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Connection Failed: " + exception.getLocalizedMessage());
        }
        return conn;
    }

    public boolean checkLogin() {
        String query = "SELECT * FROM mahasiswa WHERE email = ? AND password = ?";
        try (Connection conn = Connect();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet hasil = preparedStatement.executeQuery();

            if (hasil.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
