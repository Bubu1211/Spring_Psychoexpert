package antares.modelo.dao.jdbc;

import java.sql.Date;

public class PruebasOttis {
    private int id_prueba_ottis;
    private int id_paciente;
    private String nombre;
    private String estado;
    private Date fecha_entrega;
    private String tiempo;
    private int puntaje;
    private int id_archivo;

    public int getId_prueba_ottis() {
        return id_prueba_ottis;
    }

    public void setId_prueba_ottis(int id_prueba_ottis) {
        this.id_prueba_ottis = id_prueba_ottis;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(int id_archivo) {
        this.id_archivo = id_archivo;
    }

    @Override
    public String toString() {
        return "PruebasOttis{" + "id_prueba_ottis=" + id_prueba_ottis + ", id_paciente=" + id_paciente + ", nombre=" + nombre + ", estado=" + estado + ", fecha_entrega=" + fecha_entrega + ", tiempo=" + tiempo + ", puntaje=" + puntaje + ", id_archivo=" + id_archivo + '}';
    }
    
    
}
