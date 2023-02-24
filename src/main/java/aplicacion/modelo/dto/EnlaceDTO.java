package aplicacion.modelo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import aplicacion.modelo.Enlace;
import aplicacion.modelo.Usuario;

public class EnlaceDTO {

	
	
	
	private String nombre;
	private String url;
	
	public EnlaceDTO(String nombre, String url) {
		this.nombre = nombre;
		this.url = url;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}

	
