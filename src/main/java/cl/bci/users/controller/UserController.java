package cl.bci.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.users.entity.User;
import cl.bci.users.service.UserService;
import lombok.extern.slf4j.Slf4j;

/** Controlador de las acciones sobre los usuarios.
 * 
 * @author Williams Gomez
 * @since 0.1
 * @version 0.1.0
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping
	public User crear(@RequestBody User user) throws Exception {
		log.info("Iniciando crear usuario");
		return userService.crear(user);
	}
	
	@PutMapping
	public User actualizar(@RequestBody User user) throws Exception {
		log.info("Iniciando actualizar usuario");
		return userService.actualizar(user);
	}
	
	@DeleteMapping
	public User eliminar(@RequestBody User user) throws Exception {
		log.info("Iniciando eliminar usuario");
		return userService.eliminar(user);
	}

	@GetMapping
	public List<User> obtenerTodos() throws Exception {
		log.info("Iniciando listar todos los usuarios");
		return userService.obtenerTodos();
	}
	
	@GetMapping("id")
	public User obtenerPorId(@RequestParam(name = "id") String id) throws Exception {
		log.info("Iniciando ver usuario por id");
		return userService.obtenerPorId(id);
	}
	
}
