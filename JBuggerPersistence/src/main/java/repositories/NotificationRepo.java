package repositories;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Notification;
import utils.HibernateUtil;

@Stateless
public class NotificationRepo {

	public ArrayList<Notification> findNotificationByUserId(int userId) {
		Transaction transaction = null;

		Notification notification = new Notification();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Notification> cr = cb.createQuery(Notification.class);
			Root<Notification> root = cr.from(Notification.class);
			cr.select(root).where(cb.equal(root.get("userId"), userId));

			Query<Notification> query = session.createQuery(cr);

			ArrayList<Notification> result = (ArrayList<Notification>) query.getResultList();

			session.close();

			if (result.size() != 0) {
				return result;
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}

		return null;
	}

	public void addNotification(Notification notification) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(notification);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
