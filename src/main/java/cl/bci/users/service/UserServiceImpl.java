package cl.bci.users.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bci.users.dao.UserDAOImpl;
import cl.bci.users.entity.User;
import cl.bci.users.exception.MessageCatalogue;
import cl.bci.users.exception.UserException;
import cl.bci.users.util.EncryptUtil;
import cl.bci.users.util.ValidateUtil;
import lombok.extern.slf4j.Slf4j;

/** Service de las acciones sobre los usuarios.
 * 
 * @author Williams Gomez
 * @since 0.1
 * @version 0.1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAOImpl userDao;

	@Override
	public User crear(User user) throws Exception {
		log.info("Business crear usuario");
		
		String email = user.getEmail().trim();
		String password = user.getPassword();
		
		
		if(!ValidateUtil.validaEmail(email)) {
			throw new UserException(MessageCatalogue.CORREO_INVALIDO.getCode());
		}
		
		if(!ValidateUtil.validaPassword(password)) {
			throw new UserException(MessageCatalogue.CLAVE_NO_VALIDA.getCode());
		}
		
		validarEmailRegistrado(email);
		completarDatosCreacion(user);
		return userDao.agregar(user);
	}


	@Override
	public List<User> obtenerTodos() throws Exception {
		return userDao.obtenerTodos();
	}
	
	@Override
	public User obtenerPorId(String id) {
		log.info("Iniciando consultar usuario");
		User user = userDao.obtenerPorId(id);
		
		if(user == null) {
			throw new UserException(MessageCatalogue.ERROR_CONSULTA.getCode());
		}
		return user;
	}
	
	@Override
	public User actualizar(User user) {
		
		String email = user.getEmail().trim();
		
		validarEmailRegistrado(email);
		log.info("Iniciando actualizar usuario");
		completarDatosActualizacion(user);
		return userDao.actualizar(user);
	}
	
	@Override
	public User eliminar(User user) {
		log.info("Iniciando eliminar usuario");
		return userDao.eliminar(user);
	}
	
	
	
	
	private void completarDatosCreacion(User user) {
		
		Date fechaActual = new Date();
		user.setCreated(fechaActual);
		user.setLast_login(fechaActual);
		user.setIsactive(Boolean.TRUE);
		user.setPassword(EncryptUtil.encrypt(user.getPassword()));
	}
	
	private void completarDatosActualizacion(User user) {
		
		Date fechaActual = new Date();
		user.setModified(fechaActual);

	}
	
	private void validarEmailRegistrado(String email) {
		Long cantidad = userDao.obtenerPorEmail(email);
		
		if(cantidad > 0) {
			throw new UserException(MessageCatalogue.CORREO_REGISTRADO.getCode());
		}
	}
	
}
