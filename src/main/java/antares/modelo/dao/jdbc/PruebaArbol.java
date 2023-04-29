package antares.modelo.dao.jdbc;


public class PruebaArbol {
    
    private int idTestArbol;
    private String nombrePaciente;
    private int idPaciente;
    private int idArchivoImg;
    private int idArchivoAnalisis;
    private String estado;
//    private final Button botonImagen;
//    private final Button botonAnalisis;
    
    public PruebaArbol(){
//        botonImagen = new Button("Ver");
//        botonImagen.setPrefWidth(35);
//        botonImagen.setPrefHeight(20);
//        botonImagen.setStyle("-fx-background-color:  #dac9df");
//        botonImagen.setStyle("-fx-border-radius: 12");
//        botonAnalisis = new Button("Ver");
//        botonAnalisis.setPrefWidth(35);
//        botonAnalisis.setPrefHeight(20);
//        botonAnalisis.setStyle("-fx-background-color:  #dac9df");
//        botonAnalisis.setStyle("-fx-border-radius: 12");
    }
//
//    public Button getBotonImagen() {
//        return botonImagen;
//    }
//
//    public Button getBotonAnalisis() {
//        return botonAnalisis;
//    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    
    
//    public void addEventoBotonImg(EventHandler<ActionEvent> eh){
//        botonImagen.setOnAction(eh);
//    }
//    
//    public void addEventoBotonAn(EventHandler<ActionEvent> eh){
//        botonImagen.setOnAction(eh);
//    }

    public int getIdTestArbol() {
        return idTestArbol;
    }

    public void setIdTestArbol(int idTestArbol) {
        this.idTestArbol = idTestArbol;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public int getIdArchivoImg() {
        return idArchivoImg;
    }

    public void setIdArchivoImg(int idArchivoImg) {
        this.idArchivoImg = idArchivoImg;
    }

    public int getIdArchivoAnalisis() {
        return idArchivoAnalisis;
    }

    public void setIdArchivoAnalisis(int idArchivoAnalisis) {
        this.idArchivoAnalisis = idArchivoAnalisis;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PruebaArbolDto{" + "idTestArbol=" + idTestArbol + ", nombrePaciente=" + nombrePaciente + ", idArchivoImg=" + idArchivoImg + ", idArchivoAnalisis=" + idArchivoAnalisis + ", estado=" + estado + '}';
    }
    
    
}
