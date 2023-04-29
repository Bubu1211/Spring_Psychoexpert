package antares.modelo.servicios;

import antares.modelo.dao.PacienteDao;
import antares.modelo.dominio.Paciente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 
 */
@Service
public class PacienteService implements IService<Paciente>{

    @Autowired
    private PacienteDao dao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listarPacientes() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Paciente e) {
        dao.save(e);
    }

    @Override
    @Transactional
    public void eliminar(Paciente e) {
        dao.delete(e);
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findById(Paciente e) {
        return dao.findById(e.getIdPaciente()).orElse(new Paciente());
    }
    
}
