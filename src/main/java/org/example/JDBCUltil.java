package org.example;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUltil {
    public static Connection getConnection(String connectString) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Cac thong so
            String[] temp = connectString.split("\\?");
            String url = temp[0];
            String username = temp[1].split("&")[0].split("=")[1];
            String password = temp[1].split("&")[1].split("=").length > 1 ? temp[1].split("&")[1].split("=")[1]:"";

            // Tao ket noi
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
