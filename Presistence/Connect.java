package CuoiKy.Presistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection getConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String conURL = "jdbc:sqlserver://LAPTOP-I9NMFM5N\\MSSQLUM:1433;databaseName=QuanLiKho";
            String user = "sa";
            String pass = "M@1th01910";
            con = DriverManager.getConnection(conURL, user, pass);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;
    }
}
