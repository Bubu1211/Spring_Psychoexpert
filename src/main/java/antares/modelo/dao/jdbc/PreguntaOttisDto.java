package antares.modelo.dao.jdbc;

public class PreguntaOttisDto {

    private int numero;
    private String respuesta;

    public PreguntaOttisDto(int numero, String respuesta) {
        this.numero = numero;
        this.respuesta = respuesta;
    }
    
    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "PreguntaOttisDto{" + "numero=" + numero + ", respuesta=" + respuesta + '}';
    }

    
    
}
