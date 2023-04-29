package antares.modelo.dao.jdbc;

import java.sql.Date;

public class Terapeuta {
    
    private int id_terapeuta;
    private String nombre;
    private String apellidos;
    private String telefono;
    private Date fechaIngreso;
    private String password;
    private String correo;
    private String especialidad;

    public int getId_terapeuta() {
        return id_terapeuta;
    }

    public void setId_terapeuta(int id_terapeuta) {
        this.id_terapeuta = id_terapeuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
