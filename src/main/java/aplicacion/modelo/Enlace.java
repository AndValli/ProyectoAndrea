package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "enlaces")
public class Enlace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "url")
	private String url;

	@Column(name = "imgurl")
	private String imgurl;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "enlaces_categorias", joinColumns = { @JoinColumn(name = "id_enlace") }, inverseJoinColumns = {
			@JoinColumn(name = "id_categoria") })
	@JsonIgnore
	private Set<Categoria> categorias;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Enlace(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.url = "";
	}

	public Enlace() {
		categorias = new HashSet<Categoria>();

	}

	public Enlace(String n, boolean v, double p) {
		nombre = n;
		categorias = new HashSet<Categoria>();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Enlace [nombre=" + nombre + ", categorias=" + categorias + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
