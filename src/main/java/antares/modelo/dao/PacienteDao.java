package antares.modelo.dao;

import antares.modelo.dominio.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteDao extends JpaRepository<Paciente, Long>{
    
}
