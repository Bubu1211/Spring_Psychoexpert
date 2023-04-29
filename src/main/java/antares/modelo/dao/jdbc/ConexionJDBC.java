package antares.modelo.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ConexionJDBC {

    private static final String BD_URL = "jdbc:mysql://localhost:3306/antares_tsontelistli?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String BD_USER = "root";
    private static final String BD_PASSWORD = "admin";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(BD_URL, BD_USER, BD_PASSWORD);
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    public static void close(Connection cn) throws SQLException {
        cn.close();
    }
}
