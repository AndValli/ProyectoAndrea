package aplicacion.servicio.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import aplicacion.modelo.Rol;
import aplicacion.modelo.Usuario;
import aplicacion.modelo.dto.UsuarioDTO;

public interface RolService {


	
	public Rol obtenerRolPorNombre (String nombre);
	
}
