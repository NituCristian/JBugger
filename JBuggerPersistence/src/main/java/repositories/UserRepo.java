
package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.User;
import utils.HibernateUtil;

@Stateless
public class UserRepo {

	public User findUserById(int userId) {
		Transaction transaction = null;
		User user = new User();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = session.get(User.class, userId);
			session.close();
			return user;
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return null;
	}

	public User findUserByUsername(String username) {
		Transaction transaction = null;
		User user = new User();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cr = cb.createQuery(User.class);
			Root<User> root = cr.from(User.class);
			cr.select(root).where(cb.equal(root.get("username"), username));

			Query<User> query = session.createQuery(cr);
			query.setMaxResults(1);

			List<User> result = query.getResultList();
			session.close();

			if (result.size() != 0) {
				return result.get(0);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return null;
	}

	public User findUserByUsernameAndPassword(User user) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM User WHERE username=:username AND password=:password");
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			User foundUser = (User) query.list().get(0);
			transaction.commit();
			session.close();
			return foundUser;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<User> findAll() {
		Transaction transaction = null;
		List<User> users = new ArrayList<User>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			users = session.createQuery("FROM User").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return users;
	}

	public User addUser(User user) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			session.close();
			return user;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public User updateUser(User user) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User existentUser = session.get(User.class, user.getUserId());

			existentUser.setMobile(user.getMobile());
			existentUser.setEmail(user.getEmail());
			existentUser.setPassword(user.getPassword());
			existentUser.setActivated(user.getActivated());
			existentUser.setRoles(user.getRoles());
			transaction.commit();
			session.save(existentUser);
			session.close();
			return existentUser;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deactivateUser(int userId) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User existentUser = session.get(User.class, userId);
			existentUser.setActivated((byte) 0);
			transaction.commit();
			session.save(existentUser);
			session.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean activateUser(int userId) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User existentUser = session.get(User.class, userId);

			existentUser.setActivated((byte) 1);
			transaction.commit();
			session.save(existentUser);
			session.close();
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void saveUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		session.close();
	}
}
