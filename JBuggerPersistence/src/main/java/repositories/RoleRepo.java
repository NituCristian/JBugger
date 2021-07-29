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

import entities.Role;
import utils.HibernateUtil;

@Stateless
public class RoleRepo {

	public Role findRoleById(int roleId) {
		Transaction transaction = null;
		Role role = new Role();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			role = session.get(Role.class, roleId);
			session.close();
			return role;
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return null;
	}

	public List<Role> findAll() {
		Transaction transaction = null;
		List<Role> roles = new ArrayList<Role>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			roles = session.createQuery("FROM Role").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return roles;
	}

	public void saveRole(Role role) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.update(role);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Role findRoleByName(String name) {
		Transaction transaction = null;
		Role role = new Role();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Role> cr = cb.createQuery(Role.class);
			Root<Role> root = cr.from(Role.class);
			cr.select(root).where(cb.equal(root.get("name"), name));

			Query<Role> query = session.createQuery(cr);
			query.setMaxResults(1);

			List<Role> result = query.getResultList();
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

}
