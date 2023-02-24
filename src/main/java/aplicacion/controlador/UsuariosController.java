package aplicacion.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Rol;
import aplicacion.modelo.Usuario;

import aplicacion.persistencia.UsuarioRepo;
import aplicacion.servicio.imp.UsuarioServiceImp;
import aplicacion.servicio.interfaces.UsuarioService;

@RequestMapping("/usuarios")
@Controller
public class UsuariosController {

	@Autowired
	UsuarioServiceImp UsuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = { "", "/" })
	String homeusuarios(Model model) {
		List<Usuario> misUsuarios = UsuarioService.listarUsuarios();
		model.addAttribute("listaUsuarios", misUsuarios);
		model.addAttribute("usuarioaEditar", new Usuario());
		model.addAttribute("usuarioNuevo", new Usuario());
		return "usuarios";
	}

	@PostMapping("/edit/{id}")
	public String editarUsuario(@PathVariable Integer id, @ModelAttribute("usuarioaEditar") Usuario usuarioEditado,
			BindingResult bidingresult) {

		UsuarioService.editarUsuario(id, usuarioEditado);
		return "redirect:/usuarios";
	}

	@GetMapping({ "/{id}" })
	String idUsuario(Model model, @PathVariable Integer id) {

		return "usuario";
	}

	@GetMapping({ "/delete/{id}" })
	String deleteUsuario(Model model, @PathVariable Integer id) {
		if (elUsuarioLogueadoEsAdmin()) {
			UsuarioService.eliminarUsuarioPorId(id);
		}
		return "redirect:/";
	}

	private boolean elUsuarioLogueadoEsAdmin() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;

		if (principal instanceof UserDetails) {

			userDetails = (UserDetails) principal;

			Usuario u = UsuarioService.obtenerUsuarioPorNombre(userDetails.getUsername());

			for (Rol r : u.getRoles()) {
				if (r.getNombre().compareTo("ROLE_ADMIN") == 0) {

					return true;
				}
			}
		}

		return false;
	}

	@GetMapping("/add")
	public String mostrarAddUsuario(Model model) {

		model.addAttribute("usuarioNuevo", new Usuario());

		return "/registro.html";
	}

	@PostMapping("/add")
	public String addUsuario(@ModelAttribute("usuarioNuevo") Usuario usuarioNew, BindingResult bindingResult) {

		// String hashClave = passwordEncoder.encode(usuarioNew.getPassword());
		// usuarioNew.setPassword(hashClave);

		UsuarioService.insertarUsuario(usuarioNew);
		return "redirect:/login";
	}
}
