package aplicacion.modelo.dto;

public class UsuarioDTO {

	private String nombre;
	private String username;
	private String password;

	public UsuarioDTO(String nombre, String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UsuarioDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
