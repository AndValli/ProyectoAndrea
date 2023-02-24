package aplicacion.servicio.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import aplicacion.modelo.Categoria;
import aplicacion.modelo.Usuario;
import aplicacion.modelo.Categoria;

public interface CategoriaService {
	
	public Categoria editarCategoria (int id, Categoria categoria);
	
	public Categoria insertarCategoria (Categoria categoria);
	
	public List<Categoria> listarCategorias();
	
	public List<Categoria> listarCategoriasPorIdUsuario(Usuario usuarioId);
	
	public Categoria obtenerCategoriaPorId (Integer id);
	
	public Categoria obtenerCategoriaPorNombre (String nombre);
	
	public void eliminarCategoria (Categoria categoria) ;
	
	public void eliminarCategoriaPorId (Integer id);
	 
}
