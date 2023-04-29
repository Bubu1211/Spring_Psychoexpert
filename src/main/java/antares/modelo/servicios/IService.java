package antares.modelo.servicios;

import java.util.List;

public interface IService <E>{
    public List<E> listarPacientes();
    public void guardar(E e);
    public void eliminar(E e);
    public E findById(E e);
}
