package br.com.iblue.last.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.iblue.last.entity.Usuario;
import br.com.iblue.last.repository.UsuarioRepository;

@ResponseBody
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class UsuarioController {

	@Autowired
	UsuarioRepository dao;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Usuario usuario) {
		try {
			Usuario resp = dao.save(usuario);
			if (resp == null) {
				throw new Exception("Deu Erro");
			}
			return ResponseEntity.status(200).body(resp);
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("Error", ex.getMessage());
			return ResponseEntity.status(400).body(mapa);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		try {
			Usuario resp = dao.findByEmailAndPassword(usuario.getEmail(), usuario.getPassword());
			if (resp == null) {
				throw new Exception("Deu Ruim no Login");
			}
			return ResponseEntity.status(200).body(resp);
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("Error", ex.getMessage());
			return ResponseEntity.status(400).body(mapa);
		}
	}

	@GetMapping("/listall")
	public List<Usuario> listAll() {
		return dao.findAll();
	}

	@PutMapping("/update/{uuid}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, 
									@PathVariable("uuid") String uuid) {
		try {
			Usuario resposta = dao.findById(uuid).get();

			if (resposta == null) {
				throw new Exception("Deu Ruim no Login");
			}
			usuario.setUsername(resposta.getUsername());
			usuario.setEmail(resposta.getEmail());
			usuario.setPassword(resposta.getPassword());
			usuario.setFoto(resposta.getFoto());
			dao.saveAndFlush(usuario);
			return ResponseEntity.status(200).body(usuario);
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("Error", ex.getMessage());
			return ResponseEntity.status(400).body(mapa);
		}
	}

	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
		try {
			dao.findById(uuid).get();
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("Excluido", "Excluido com Sucesso");
			return ResponseEntity.status(200).body(mapa);
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("Error", ex.getMessage());
			return ResponseEntity.status(400).body(mapa);
		}
	}
}