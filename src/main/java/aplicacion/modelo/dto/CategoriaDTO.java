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

public class CategoriaDTO {

	
	private String nombre;
	private Usuario usuario;
	
	public CategoriaDTO(String nombre, Usuario usuario) {
		this.nombre = nombre;
		this.usuario = usuario;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
	