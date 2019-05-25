package programmingSchoolDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionCreator {
        static String DBURL = "jdbc:mysql://localhost:3306/cinemas_ex?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
        static String user = "root";
        static String password = "coderslab";
    public static Connection getConnection() throws SQLException{
        try (Connection connection = DriverManager.getConnection(DBURL, user, password)){
            return connection;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}
