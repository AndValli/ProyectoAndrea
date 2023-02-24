package aplicacion.controlador;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
 

@Controller
public class WebApplication {

	
	@GetMapping("/categorias")
	String homecategoria() {
		 
		return "categorias";
			
	}
	

	
	  
}
