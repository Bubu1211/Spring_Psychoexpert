/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antares.modelo.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Dell
 */
@Data
@Entity
@Table(name = "terapeutas")
public class Terapeuta implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_terapeuta")
    private Long idTerapeuta;
    
    @NotNull
    private String nombre; 
    @NotNull
    private String apellidos;
    @NotNull
    private String especialidad;
    @NotNull
    private String telefono;
    @NotNull
    private String correo;
    @NotNull
    private String password;
    
    @NotNull
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;
}
