package br.com.iblue.last.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.iblue.last.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Usuario findByEmailAndPassword(String email, String senha);
}