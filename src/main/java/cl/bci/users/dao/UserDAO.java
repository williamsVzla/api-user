package cl.bci.users.dao;

import java.util.List;

import cl.bci.users.entity.User;

public interface UserDAO {

	User agregar(User user);

	Long obtenerPorEmail(String email);

	User obtenerPorId(String id);

	List<User> obtenerTodos();

	User actualizar(User user);

	User eliminar(User user);

}
