package antares.web;

import antares.modelo.dominio.Paciente;
import antares.modelo.servicios.PacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerapeutaControlador {
    
    @Autowired
    private PacienteService pacienteService;
    
    @GetMapping("/pacientes")   
    public List<Paciente> getPacientes(){
        return pacienteService.listarPacientes();
    }
    
    @PostMapping("/guardar/paciente")
    public void guardarPaciente(@RequestBody Paciente paciente){
        pacienteService.guardar(paciente);
    }
}
