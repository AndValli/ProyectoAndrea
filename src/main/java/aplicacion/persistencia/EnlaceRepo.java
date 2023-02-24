package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
 
import aplicacion.modelo.Enlace;

public interface EnlaceRepo extends JpaRepository<Enlace, Integer> {


	
}
