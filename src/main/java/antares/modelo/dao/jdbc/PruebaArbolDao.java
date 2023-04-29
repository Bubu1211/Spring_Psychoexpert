package antares.modelo.dao.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PruebaArbolDao {

    private static final String LISTAR = "SELECT * FROM antares_tsontelistli.listar_tests_arbol;";
    private final String RUTA_ANALISIS = "C:/Users/Dell/Documents/antares/analisis_arbol";

    public List<PruebaArbol> listarPruebas() throws SQLException {
        var lista = new ArrayList<PruebaArbol>();
        PruebaArbol arbol;

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(LISTAR);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            arbol = new PruebaArbol();
            arbol.setIdTestArbol(rs.getInt("id_test_arbol"));
            arbol.setNombrePaciente(rs.getString("nombre_paciente"));
            arbol.setIdArchivoImg(rs.getInt("id_archivo_img"));
            arbol.setIdArchivoAnalisis(rs.getInt("id_archivo_analisis"));
            arbol.setEstado(rs.getString("estado"));
            arbol.setIdPaciente(rs.getInt("id_paciente"));
            lista.add(arbol);
        }

        ConexionJDBC.close(conn);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(rs);

        return lista;
    }

    public void guardarPrueba(int id, String ruta, String nombre) throws SQLException {
        String PROCEDURE = "CALL guardar_prueba_arbol(? , ? , ? ); ";

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(PROCEDURE);
        stmt.setInt(1, id);
        stmt.setString(2, ruta);
        stmt.setString(3, nombre);
        stmt.executeUpdate();

        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);
    }

    public boolean guardarAnalisis(int idPrueba, int idPaciente, List<String> s) throws FileNotFoundException, IOException, SQLException {
        
        final String SAVE = "CALL guardar_analisis_test_arbol(?, ?, ?, ?);";
        
        LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Mexico_City"));
        DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY_MM_dd_h_mm_ss");
        String nombre = idPaciente + "_" + idPrueba + "_"+date.format(f) + ".psychoexpert";
        
        ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(new File(RUTA_ANALISIS + "/"+nombre)));
        ob.writeObject(s);
        ob.close();

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(SAVE);
        
        stmt.setInt(1, idPrueba);
        stmt.setInt(2, idPaciente);
        stmt.setString(3, RUTA_ANALISIS);
        stmt.setString(4, nombre);
        
        boolean r = stmt.execute();
        
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);
        
        return r;
    }

    public List<String> obtenerCaracteristicas(String file) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ob = new ObjectInputStream(new FileInputStream(new File(file)));
        var caracteristicas = (ArrayList<String>) ob.readObject();
        ob.close();
        return caracteristicas;
    }
}
