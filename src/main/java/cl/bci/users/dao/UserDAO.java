package cl.bci.users.dao;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import cl.bci.users.entity.Phone;
import cl.bci.users.entity.User;
import cl.bci.users.exception.ErrorCatalogue;
import cl.bci.users.exception.UserException;
import cl.bci.users.util.EncryptUtil;
import cl.bci.users.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDAO {

	public User agregar(User user) {

		user.setPassword(EncryptUtil.encrypt(user.getPassword()));

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;

	}

	public User actualizar(User user) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch (OptimisticLockException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new UserException(ErrorCatalogue.ERROR_ACTUALIZAR.getCode());
		}
		return user;

	}

	public User eliminar(User user) {

		

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		} catch (OptimisticLockException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new UserException(ErrorCatalogue.ERROR_ELIMINAR.getCode());
		}
		return user;

	}

	public List<User> obtenerTodos() {
		List<User> users = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM User");
			users = query.list();

			if (!users.isEmpty()) {
				for (User user : users) {
					String queryStr = "FROM Phone where user.id= :id";
					Query query2 = session.createQuery(queryStr);
					query2.setParameter("id", user.getId());
					List<Phone> phones = (List<Phone>) query2.list();
					user.setPhones(phones);
				}
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return users;

	}

	public User obtenerPorId(String id) {
		log.info("DAO Obtener por id");
		User user = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM User where id= :id");
			query.setParameter("id", id);
			user = (User) query.uniqueResult();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}

	public Long obtenerPorEmail(String email) {
		log.info("DAO Obtener por email");

		Long cantidad = 0l;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("select count(*) from User where email= :email");
			query.setParameter("email", email);
			cantidad = (Long) query.uniqueResult();

			log.info("Cantidad de correo encontrados {}", cantidad);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cantidad;
	}

}
