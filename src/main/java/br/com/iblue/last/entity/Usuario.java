package br.com.iblue.last.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Usuario")
public class Usuario {

	@Id
	@JsonProperty("uuid")
	@Column(name = "uuid", unique = true)
	private String uuid;

	@JsonProperty("username")
	@Column(name = "username")
	private String username;

	@JsonProperty("email")
	@Column(name = "email", length = 250, unique = true)
	private String email;

	@JsonProperty("password")
	@Column(name = "password")
	private String password;

	@JsonProperty("foto")
	@Column(name = "foto")
	private String foto;

	{
		uuid = UUID.randomUUID().toString();
	}

	public Usuario() {
	}

	public Usuario(String uuid, String username, String email, String password, String foto) {
		super();
		this.uuid = uuid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [uuid=" + uuid + ", username=" + username + ", email=" + email +
				", foto=" + foto + "]";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}