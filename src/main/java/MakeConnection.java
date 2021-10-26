import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {

    public static Connection getConnection() {
        /*I do a safe connection without needing to use a server certificate */
        try {
            final String url = "jdbc:mysql://localhost:3306/jdbc_introduction?verifyServerCertificate=false&useSSL=true";
            final String user = "h4rryp0tt3r";
            final String password = "Horcrux4life!";

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
          throw new RuntimeException();
        }
    }
}
