package aplicacion.servicio.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aplicacion.modelo.Enlace;
import aplicacion.persistencia.EnlaceRepo;
import aplicacion.servicio.interfaces.EnlaceService;

@Service
public class EnlaceServiceImp implements EnlaceService {

	@Autowired
	private EnlaceRepo enlaceRepo;

	@Override
	public List<Enlace> listarEnlaces() {
		
		return enlaceRepo.findAll();
	}

	@Override
	public Enlace obtenerEnlacePorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enlace obtenerEnlacePorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarEnlace(Enlace enlace) {
		enlaceRepo.delete(enlace);
	}

	@Override
	public void eliminarEnlacePorId(Integer id) {
		enlaceRepo.deleteById(id);
	}

	@Override
	public Enlace editarEnlace(Enlace enlace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enlace insertarEnlace(Enlace enlace) {
		// TODO Auto-generated method stub
		return enlaceRepo.save(enlace);
	}

	

	
}
