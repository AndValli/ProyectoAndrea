package aplicacion.persistencia;
 

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
 
import aplicacion.modelo.Enlace;
import aplicacion.modelo.Usuario;
import aplicacion.modelo.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
	   List<Categoria> findByUsuario(Usuario usuarioId);
	
	
}
