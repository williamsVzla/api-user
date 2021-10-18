package cl.bci.users.service;

import java.util.List;

import cl.bci.users.entity.User;

public interface UserService {

	User crear(User user) throws Exception;
	
	List<User> obtenerTodos() throws Exception;

	User obtenerPorId(String id);

	User actualizar(User user);

	User eliminar(User user);

}
