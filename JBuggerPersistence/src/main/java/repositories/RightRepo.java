package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Right;
import utils.HibernateUtil;

@Stateless
public class RightRepo {

	public Right findRightById(int rightId) {
		Transaction transaction = null;
		Right right = new Right();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			right = session.get(Right.class, rightId);
			session.close();
			return right;
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return null;
	}
	public List<Right> findAll() {
		Transaction transaction = null;
		List<Right> rights = new ArrayList<Right>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			rights = session.createQuery("FROM Right").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}

		return rights;
	}

	public void addRight(Right right) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(right);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRight(int rightId, String name) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Right existentRight = session.get(Right.class, rightId);

			existentRight.setName(name);
			transaction.commit();
			session.save(existentRight);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteRight(int rightId) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Right existentRight = session.get(Right.class, rightId);
			session.delete(existentRight);

			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
