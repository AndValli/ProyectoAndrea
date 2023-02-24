package aplicacion.servicio.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aplicacion.modelo.Categoria;
import aplicacion.modelo.Usuario;
import aplicacion.persistencia.CategoriaRepo;
import aplicacion.servicio.interfaces.CategoriaService;
import aplicacion.servicio.interfaces.UsuarioService;

@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	private CategoriaRepo categoriaRepo;

	@Override
	public Categoria editarCategoria(int id, Categoria categoria) {
		
		return categoriaRepo.save(categoria);
	}

	@Override
	public Categoria insertarCategoria(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}

	@Override
	public List<Categoria> listarCategorias() {
		return categoriaRepo.findAll();
	}

	@Override
	public Categoria obtenerCategoriaPorId(Integer id) {
		return categoriaRepo.findById(id).get();
	}

	@Override
	public Categoria obtenerCategoriaPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCategoria(Categoria categoria) {
		categoriaRepo.delete(categoria);
	}

	@Override
	public void eliminarCategoriaPorId(Integer id) {
		categoriaRepo.deleteById(id);
	}

	@Override
	public List<Categoria> listarCategoriasPorIdUsuario(Usuario  usuario) {
		List<Categoria> res = categoriaRepo.findByUsuario( usuario);
		return res;
	}


	

	
		

}
