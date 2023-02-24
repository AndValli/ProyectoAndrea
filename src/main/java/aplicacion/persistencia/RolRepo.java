package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Rol;
import aplicacion.modelo.Usuario;


public interface RolRepo extends JpaRepository<Rol, Integer> {
	
	public Rol findByNombre(String nombre);
	
}
