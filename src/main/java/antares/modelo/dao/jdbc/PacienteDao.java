package antares.modelo.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    public Paciente iniciarSesion(String correo) throws SQLException {
        final String FIND = "SELECT id_paciente, p.nombre, p.apellidos, p.telefono, p.direccion, p.fecha_nacimiento, p.fecha_inicio, p.password, p.correo, sexo, terapeutas.nombre\n"
                + " FROM antares_tsontelistli.pacientes AS p, antares_tsontelistli.sexos, antares_tsontelistli.terapeutas\n"
                + " WHERE p.id_sexo = sexos.id_sexo AND\n"
                + " p.id_terapeuta = terapeutas.id_terapeuta AND\n"
                + " p.correo = ?;";
        Paciente paciente = null;

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(FIND);
        stmt.setString(1, correo);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paciente = new Paciente();
            paciente.setId_paciente(rs.getInt("id_paciente"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setApellido(rs.getString("apellidos"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setDireccion(rs.getString("direccion"));
            paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            paciente.setFechaInicio(rs.getDate("fecha_inicio"));
            paciente.setPassword(rs.getString("password"));
            paciente.setCorreo(rs.getString("correo"));
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTerapeuta(rs.getString("terapeutas.nombre"));
        }

        ConexionJDBC.close(rs);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);

        return paciente;
    }

    public Paciente find(int parseInt) throws SQLException {
        final String FIND = "SELECT id_paciente, p.nombre, p.apellidos, p.telefono, p.direccion, p.fecha_nacimiento, p.fecha_inicio, p.password, p.correo, sexo, terapeutas.nombre\n"
                + "  FROM antares_tsontelistli.pacientes AS p, sexos, terapeutas WHERE p.id_sexo = sexos.id_sexo AND\n"
                + " p.id_terapeuta = terapeutas.id_terapeuta AND\n"
                + " id_paciente = ?;";
        Paciente paciente = null;

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(FIND);
        stmt.setInt(1, parseInt);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paciente = new Paciente();
            paciente.setId_paciente(rs.getInt("id_paciente"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setApellido(rs.getString("apellidos"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setDireccion(rs.getString("direccion"));
            paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            paciente.setFechaInicio(rs.getDate("fecha_inicio"));
            paciente.setPassword(rs.getString("password"));
            paciente.setCorreo(rs.getString("correo"));
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTerapeuta(rs.getString("terapeutas.nombre"));
        }

        ConexionJDBC.close(rs);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);

        return paciente;
    }

    public List<Paciente> listarPacientes(int idTerapeuta) throws SQLException {
        final String FIND = "SELECT id_paciente, p.nombre, p.apellidos, p.telefono, p.direccion, p.fecha_nacimiento, p.fecha_inicio,\n"
                + " p.password, p.correo, sexo, concat(terapeutas.nombre, \" \", terapeutas.apellidos) as terapeuta_nombre\n"
                + " FROM antares_tsontelistli.pacientes AS p, sexos, terapeutas WHERE p.id_sexo = sexos.id_sexo AND\n"
                + "p.id_terapeuta = terapeutas.id_terapeuta AND p.id_terapeuta = ?;";
        Paciente paciente = null;
        var listado = new ArrayList<Paciente>();

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(FIND);
        stmt.setInt(1, idTerapeuta);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paciente = new Paciente();
            paciente.setId_paciente(rs.getInt("id_paciente"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setApellido(rs.getString("apellidos"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setDireccion(rs.getString("direccion"));
            paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            paciente.setFechaInicio(rs.getDate("fecha_inicio"));
            paciente.setPassword(rs.getString("password"));
            paciente.setCorreo(rs.getString("correo"));
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTerapeuta(rs.getString("terapeuta_nombre"));
            listado.add(paciente);
        }

        ConexionJDBC.close(rs);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);

        return listado;
    }

    public boolean guardarPaciente(Paciente p, int sexo) throws SQLException {
        final String SAVE = "CALL guardar_paciente(?,?,?,?,?,?,?,?,?);";

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(SAVE);
        //nombre, apellidos, telefono, direccion, fecha_nacimiento, 
        //fecha_inicio, password, correo, id_sexo, id_terapeuta
        stmt.setString(1, p.getNombre());
        stmt.setString(2, p.getApellido());
        stmt.setString(3, p.getTelefono());
        stmt.setString(4, p.getDireccion());
        stmt.setDate(5, p.getFechaNacimiento());
        stmt.setDate(6, p.getFechaInicio());
        stmt.setString(7, p.getPassword());
        stmt.setString(8, p.getCorreo());
        stmt.setInt(9, sexo);

        boolean r = stmt.execute();

        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);

        return r;
    }

    public boolean modificarPaciente(Paciente p, int sexo) throws SQLException {
        final String SAVE = "CALL modificar_paciente(?,?,?,?,?,?,?,?,?,?);";

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(SAVE);
        //nombre, apellidos, telefono, direccion, fecha_nacimiento, 
        //fecha_inicio, password, correo, id_sexo, id_terapeuta
        stmt.setString(1, p.getNombre());
        stmt.setString(2, p.getApellido());
        stmt.setString(3, p.getTelefono());
        stmt.setString(4, p.getDireccion());
        stmt.setDate(5, p.getFechaNacimiento());
        stmt.setDate(6, p.getFechaInicio());
        stmt.setString(7, p.getPassword());
        stmt.setString(8, p.getCorreo());
        stmt.setInt(9, sexo);
        stmt.setInt(10, p.getId_paciente());

        boolean r = stmt.execute();

        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);

        return r;
    }

    public List<Paciente> listarAllPacientes() throws SQLException {
        String SELECT = " SELECT * FROM listar_all_pacientes;";
        var listado = new ArrayList<Paciente>();

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(SELECT);
        ResultSet rs = stmt.executeQuery();
        Paciente paciente;
        while (rs.next()) {
            paciente = new Paciente();
            paciente.setId_paciente(rs.getInt("id_paciente"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setApellido(rs.getString("apellidos"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setDireccion(rs.getString("direccion"));
            paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            paciente.setFechaInicio(rs.getDate("fecha_inicio"));
            paciente.setPassword(rs.getString("password"));
            paciente.setCorreo(rs.getString("correo"));
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTerapeuta(rs.getString("terapeuta_nombre"));
            listado.add(paciente);
        }

        ConexionJDBC.close(rs);
        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);

        return listado;
    }

    public int asignarTerapeuta(int idTerapeuta, Paciente paciente) throws SQLException {

        String ASGINAR = "CALL asignar_terapeuta(?, ?);";

        Connection conn = ConexionJDBC.getConexion();
        int res;
        try ( PreparedStatement stmt = conn.prepareStatement(ASGINAR)) {
            stmt.setInt(1, idTerapeuta);
            stmt.setInt(2, paciente.getId_paciente());
            res = stmt.executeUpdate();
            ConexionJDBC.close(stmt);
        }
        ConexionJDBC.close(conn);

        return res;
    }
}
