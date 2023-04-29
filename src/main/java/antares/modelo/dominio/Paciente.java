package antares.modelo.dominio;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
import lombok.Data;

/**
 *
 */
@Data
@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable{
    
    private static Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private long idPaciente;
    
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellidos;
    
    @NotNull
    private String telefono;
    
    @NotNull
    private String direccion;
    
    @NotNull
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    
    @NotNull
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @NotNull
    private String password;
    
    @NotNull
    @Email
    private String correo;
    
    @OneToOne
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;
    
    @OneToOne
    @JoinColumn(name = "id_terapeuta")
    private Terapeuta terapeuta;
    
    public Paciente(){
        this.nombre = "sin_nombre";
        this.apellidos = "sin apellidos";
        this.correo = "sin correo";
        this.direccion = "sin dirección";
        this.fechaInicio = new Date();
        this.fechaNacimiento = new Date();
        this.idPaciente = 0;
        this.idPaciente = 2;
        this.password = "sin password";
        this.telefono = "sin telefono";
    }
}
