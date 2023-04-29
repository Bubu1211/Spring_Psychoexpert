package antares.modelo.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TerapeutaDao {
    
    public Terapeuta iniciarSesion(String correo) throws SQLException{
        final String FIND = "SELECT * FROM antares_tsontelistli.terapeutas WHERE correo=?;";
        Terapeuta terapeuta = null;
        
        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(FIND);
        stmt.setString(1, correo);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            terapeuta = new Terapeuta();
            terapeuta.setId_terapeuta(rs.getInt("id_terapeuta"));
            terapeuta.setNombre(rs.getString("nombre"));
            terapeuta.setApellidos(rs.getString("apellidos"));
            terapeuta.setTelefono(rs.getString("telefono"));
            terapeuta.setFechaIngreso(rs.getDate("fecha_ingreso"));
            terapeuta.setPassword(rs.getString("password"));
            terapeuta.setCorreo(rs.getString("correo"));
            terapeuta.setEspecialidad(rs.getString("especialidad"));
        }
        
        ConexionJDBC.close(rs);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);
        
        return terapeuta;
    }
    
    public boolean guardarTerapeuta(Terapeuta t) throws SQLException{
        
        final String SAVE = "INSERT INTO `antares_tsontelistli`.`terapeutas` "
                + "(`nombre`, `apellidos`, `especialidad`, `telefono`, `correo`, `password`, `fecha_ingreso`)"
                + " VALUES (?, ?, ?, ?, ?, ?,?);";
        
        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(SAVE);
        
        stmt.setString(1, t.getNombre());
        stmt.setString(2, t.getApellidos());
        stmt.setString(3, t.getEspecialidad());
        stmt.setString(4, t.getTelefono());
        stmt.setString(5, t.getCorreo());
        stmt.setString(6, t.getPassword());
        stmt.setDate(7, t.getFechaIngreso());
        
        boolean r = stmt.execute();
        
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);
        
        return r;
    }
    
    public boolean modificarTerapeuta(Terapeuta t) throws SQLException{
        final String SAVE = "UPDATE terapeutas SET nombre = ?, apellido = ?, especialidad = ?, "
                + "telefono = ?, correo = ?, password = ?, fecha_ingreso= ? WHERE id_terapeuta = ?";
        
        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(SAVE);
        
        stmt.setString(1, t.getNombre());
        stmt.setString(2, t.getApellidos());
        stmt.setString(3, t.getEspecialidad());
        stmt.setString(4, t.getTelefono());
        stmt.setString(5, t.getCorreo());
        stmt.setString(6, t.getPassword());
        stmt.setDate(7, t.getFechaIngreso());
        stmt.setInt(8, t.getId_terapeuta());
        
        boolean r = stmt.execute();
        
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);
        
        return r;
    }
}
