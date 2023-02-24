package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne // (cascade = { CascadeType.ALL },optional = true)
	@JoinColumn(name = "id_usuario", nullable = true)
	@JsonIgnore
	private Usuario usuario;

	@ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
	private Set<Enlace> enlaces;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria() {
		enlaces = new HashSet<Enlace>();
	}

	public Categoria(Usuario a) {
		usuario = a;
		enlaces = new HashSet<Enlace>();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Enlace> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(Set<Enlace> enlaces) {
		this.enlaces = enlaces;
	}

	@Override
	public String toString() {
		return "Categoria [nombre=" + nombre + ", usuario=" + usuario + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void imprimir() {
		// TODO Auto-generated method stub

	}
	
	public String getImagen() {
	    if (nombre.toLowerCase().contains("banco")) {
	        return "https://cdn.mises.org/styles/social_media_1200_x_1200/s3/ank2.PNG";
	    }
	    else {
	        if (nombre.toLowerCase().contains("streaming")) {
	            return "/img/streaming.png";
	        }
	        else {
	        	if (nombre.toLowerCase().contains("redes sociales")) {
	                return "/img/redes.png";
	            }
	            else {
	                return "https://mike.miracomosehace.com/uploads/images/content/image_1591233538.jpg";
	            }
	        }
	    }
	}
}
	