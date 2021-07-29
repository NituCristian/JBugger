package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Bug;
import utils.HibernateUtil;

@Stateless
public class BugRepo {

	public List<Bug> findAll() {
		Transaction transaction = null;
		List<Bug> bugs = new ArrayList<Bug>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			bugs = session.createQuery("FROM Bug").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return bugs;
	}

	public Bug findBugById(int bugId) {
		Transaction transaction = null;
		Bug bug = new Bug();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			bug = session.get(Bug.class, bugId);
			session.close();
			return bug;
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return null;
	}

	public Bug addBug(Bug bug) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(bug);
			transaction.commit();
			session.close();
			return bug;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Bug updateBug(Bug bug) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Bug existentBug = session.get(Bug.class, bug.getBugId());

			existentBug.setTitle(bug.getTitle());
			existentBug.setDescription(bug.getDescription());
			existentBug.setVersion(bug.getVersion());
			existentBug.setFixedInVersion(bug.getFixedInVersion());
			existentBug.setSeverity(bug.getSeverity());
			existentBug.setAssignedTo(bug.getAssignedTo());
			existentBug.setAttachment(bug.getAttachment());
			existentBug.setStatus(bug.getStatus());
			transaction.commit();
			session.save(existentBug);
			session.close();
			return existentBug;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Bug updateStatus(int bugId, String status) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Bug existentBug = session.get(Bug.class, bugId);
			existentBug.setStatus(status);
			transaction.commit();
			session.save(existentBug);
			session.close();
			return existentBug;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Bug> bugsAssignedToUser(int userId) {
		Transaction transaction = null;
		List<Bug> bugs = new ArrayList<Bug>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Bug WHERE assignedTo=:userId AND status='closed'");
			query.setParameter("userId", userId);
			bugs = query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println(e.getMessage());
				transaction.rollback();
			}
		}
		return bugs;
	}
}