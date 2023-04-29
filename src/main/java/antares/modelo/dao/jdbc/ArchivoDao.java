package antares.modelo.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchivoDao {
    
    public static final int ID_DEFECTO = 11;
    
    public Archivo findFile(int id) throws SQLException{
        Archivo a = null;
        final String FIND = "SELECT * FROM archivos WHERE id_archivo = ?";
        
        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(FIND);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            a = new Archivo();
            a.setIdArchivo(rs.getInt("id_archivo"));
            a.setRuta(rs.getString("ruta"));
            a.setNombre(rs.getString("nombre"));
        }
        
        ConexionJDBC.close(conn);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(rs);
        
        return a;
    }
    
    
}
