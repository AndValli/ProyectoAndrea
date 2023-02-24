package aplicacion.servicio.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import aplicacion.modelo.Enlace;
import aplicacion.modelo.Usuario;

public interface EnlaceService {
	
	public Enlace editarEnlace ( Enlace enlace);
	
	public Enlace insertarEnlace (Enlace enlace);
	
	public List<Enlace> listarEnlaces();
	public Enlace obtenerEnlacePorId (Integer id);
	
	public Enlace obtenerEnlacePorNombre (String nombre);
	
	public void eliminarEnlace (Enlace enlace) ;
	
	public void eliminarEnlacePorId (Integer id);
	 
	
}
