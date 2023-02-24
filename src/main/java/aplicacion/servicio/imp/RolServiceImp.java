package aplicacion.servicio.imp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import aplicacion.modelo.Rol;
import aplicacion.persistencia.RolRepo;
import aplicacion.servicio.interfaces.RolService;


@Service
public class RolServiceImp implements RolService {

	@Autowired
	private RolRepo rolRepo;

	@Override
	public Rol obtenerRolPorNombre(String nombre) {
		
		return rolRepo.findByNombre(nombre);
	}
	
	
}
