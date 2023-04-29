package antares.modelo.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PruebasOttisDao {
    
    public List<PruebasOttis> listarPruebas() throws SQLException{
        final String SELECT = "SELECT * FROM pruebas_ottis_selectView";
        
        var lista = new ArrayList<PruebasOttis>();
        
        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(SELECT);
        ResultSet rs = stmt.executeQuery();
        PruebasOttis p;
        while(rs.next()){
            p = new PruebasOttis();
            p.setId_prueba_ottis(rs.getInt("id_prueba_ottis"));
            p.setId_paciente(rs.getInt("id_paciente"));
            p.setNombre(rs.getString("nombre"));
            p.setEstado(rs.getString("estado"));
            p.setFecha_entrega(rs.getDate("fecha_entrega"));
            p.setTiempo(rs.getString("tiempo"));
            p.setPuntaje(rs.getInt("puntaje"));
            p.setId_archivo(rs.getInt("id_archivo"));
            lista.add(p);
        }
        ConexionJDBC.close(conn);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(rs);
        return lista;
    }
}
