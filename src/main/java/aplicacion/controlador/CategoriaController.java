package aplicacion.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Usuario;
import aplicacion.modelo.Enlace;
import aplicacion.modelo.Rol;
import aplicacion.modelo.Categoria;

import aplicacion.persistencia.UsuarioRepo;
import aplicacion.servicio.imp.CategoriaServiceImp;
import aplicacion.servicio.imp.EnlaceServiceImp;
import aplicacion.servicio.imp.UsuarioServiceImp;

import aplicacion.persistencia.EnlaceRepo;

import aplicacion.persistencia.CategoriaRepo;

@RequestMapping("/")
@Controller
public class CategoriaController {

	@Autowired
	private CategoriaServiceImp categoriaServiceImp;

	@Autowired
	private UsuarioServiceImp usuarioService;

	@Autowired
	private EnlaceServiceImp enlaceService;

	private Usuario logeado;

	@GetMapping(value = { "", "/" })
	String usuarios(Model model) {

		if (hayUsuarioLogueado()) {

			if (esAdmin(logeado)) {
				return "redirect:/usuarios";
			} else {
				List<Categoria> miscategorias = null;
				if (logeado.getCategorias().size() != 0) {
					miscategorias = categoriaServiceImp.listarCategoriasPorIdUsuario(logeado);
				} else {
					miscategorias = new ArrayList<Categoria>();

				}
				List<Usuario> misUsuarios = usuarioService.listarUsuarios();
				List<Enlace> misEnlaces = enlaceService.listarEnlaces();

				model.addAttribute("listaCategorias", miscategorias);
				// model.addAttribute("listaUsuarios", misUsuarios);
				// model.addAttribute("listaEnlaces", misEnlaces);

				model.addAttribute("categoriaNueva", new Categoria());
				model.addAttribute("enlaceNuevo", new Enlace());

				return "index";
			}
		} else {
			return "Welcome";
		}

	}

	private boolean esAdmin(Usuario logeado) {
		boolean res = false;
		for (Rol r : logeado.getRoles()) {
			if (r.getNombre().compareTo("ROLE_ADMIN") == 0) {
				res = true;
			}

		}
		return res;
	}

	@PostMapping("/addCategoria")
	public String addCategoria(@ModelAttribute("categoriaNueva") Categoria categoriaNueva, HttpSession session,
			BindingResult bindingResult) {

		if (hayUsuarioLogueado()) {
			categoriaNueva.setUsuario(logeado);
			logeado.getCategorias().add(categoriaNueva);

		}

		categoriaServiceImp.insertarCategoria(categoriaNueva);

		usuarioService.save(logeado);

		return "redirect:/";
	}

	private boolean hayUsuarioLogueado() {
		
		boolean res = false;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
			Usuario u = usuarioService.obtenerUsuarioPorNombre(userDetails.getUsername());

			if (u == null) {
				res = false;
			} else {
				logeado = u;
				res = true;
			}
		}
		return res;
	}

	@PostMapping({ "addEnlace/{id}" })
	String idCategoria(Model model, @ModelAttribute("enlaceNuevo") Enlace enlaceNuevo, @PathVariable Integer id) {

		Categoria categoriaDelEnlace = categoriaServiceImp.obtenerCategoriaPorId(id);

		enlaceNuevo.setId(null);
		enlaceNuevo.getCategorias().add(categoriaDelEnlace);

		/*
		try {
			Document doc = Jsoup.connect("http://" + enlaceNuevo.getUrl()).get();
			String imageUrl = null;
			Elements metaOgImage = doc.select("meta[property=og:image]");
			if (metaOgImage != null) {
				imageUrl = metaOgImage.attr("content");
				enlaceNuevo.setImgurl(imageUrl);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		enlaceNuevo.setImgurl("http://" + enlaceNuevo.getUrl() + "/favicon.ico");

		enlaceService.insertarEnlace(enlaceNuevo);

		categoriaDelEnlace.getEnlaces().add(enlaceNuevo);
		categoriaServiceImp.insertarCategoria(categoriaDelEnlace);

		return "redirect:/";
	}

	@PostMapping("/edit/{id}")
	public String editarCategoria(Model model, @PathVariable Integer id,
			@ModelAttribute("categoriaMostrar") Categoria categoriaEditado) {
		Usuario a = usuarioService.obtenerUsuarioPorId(categoriaEditado.getUsuario().getId());
		categoriaEditado.setUsuario(a);
		Categoria categoriaaEditar = categoriaServiceImp.obtenerCategoriaPorId(id);
		for (Enlace b : categoriaaEditar.getEnlaces()) {
			if (!categoriaEditado.getEnlaces().contains(b)) {
				b.getCategorias().remove(categoriaaEditar);
			}
		}
		for (Enlace b : categoriaEditado.getEnlaces()) {
			if (!categoriaaEditar.getEnlaces().contains(b)) {
				b.getCategorias().add(categoriaEditado);
			}
		}

		categoriaServiceImp.insertarCategoria(categoriaaEditar);
		return "redirect:/categorias";
	}

	@GetMapping({ "/buscar/{nombre}" })
	public String obtenerCategoria(@PathVariable String nombre) {
		return "categoria";
	}

	@GetMapping({ "/enlaces/delete/{id}" })
	String deleteEnlace(Model model, @PathVariable Integer id) {

		enlaceService.eliminarEnlacePorId(id);

		return "redirect:/";

	}

	@GetMapping({ "/delete/{id}" })
	String deleteCategoria(Model model, @PathVariable Integer id) {

		categoriaServiceImp.eliminarCategoriaPorId(id);

		return "redirect:/";

	}

}
