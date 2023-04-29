package antares.modelo.dao.jdbc;

public class Archivo {
    private int idArchivo;
    private String ruta;
    private String nombre;
    public static final int FILE_DEFAULT = 11;

    /**
     * Retorna la cadena que representa la ruta del archivo
     * @return cadena representativa del archvo : PATH
     */
    public String fileString(){
        return ruta + "\\" + nombre;
    }
    
    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Archivo{" + "idArchivo=" + idArchivo + ", ruta=" + ruta + ", nombre=" + nombre + '}';
    }
}
