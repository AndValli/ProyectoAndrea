package aplicacion.servicio.imp;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplicacion.modelo.Rol;
import aplicacion.modelo.Usuario;
import aplicacion.modelo.dto.UsuarioDTO;
import aplicacion.persistencia.RolRepo;
import aplicacion.persistencia.UsuarioRepo;
import aplicacion.servicio.interfaces.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private RolRepo rolRepo;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// recibe ususario bien de BBDD
		// debuguear->loguear->f7 hasta que user=null
		Optional<Usuario> usuario = usuarioRepo.findByUsername(username);

		if (usuario.isPresent()) {

			Usuario springUserMio = usuario.get();
			// springUserMio.setRoles(mapearAutoridadesRoles(usuario.get().getRoles()));
			return springUserMio;
		} else
			throw new UsernameNotFoundException("Usuario o password incorrecto");
	}

	@Override
	public Usuario insertarUsuario(Usuario user) {
		Usuario nuevoUsuario = new Usuario(user.getNombre(), user.getUsername(),
				passwordEncoder.encode(user.getPassword()));
		Rol nuevoRol = rolRepo.findByNombre("ROLE_USER");
		nuevoUsuario.getRoles().add(nuevoRol);
		return usuarioRepo.save(nuevoUsuario);

	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario obtenerUsuarioPorId(Integer id) {
		return usuarioRepo.findById(id).get();
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombre) {
		//antes era: return usuarioRepo.findByUsername(nombre).get();
		return usuarioRepo.findByUsername(nombre).orElse(null);
	}

	@Override
	public void eliminarUsuario(Usuario user) {

		usuarioRepo.delete(user);

	}

	@Override
	public void eliminarUsuarioPorId(Integer id) {
		usuarioRepo.delete(usuarioRepo.findById(id).get());

	}

	@Transactional
	@Override
	public Usuario editarUsuario(int id, Usuario user) {

		Optional<Usuario> optUserDb = usuarioRepo.findById(id);
		if (optUserDb.isPresent()) {
			Usuario userDb = optUserDb.get();
			userDb.setNombre(user.getNombre());
			return userDb;
		}
		return null;
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());

	}

	public void save(Usuario usuarioAutenticado) {
		// TODO Auto-generated method stub

	}
}
