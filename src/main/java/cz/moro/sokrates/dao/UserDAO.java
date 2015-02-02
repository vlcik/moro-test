package cz.moro.sokrates.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.model.User;

@Repository
@Transactional
public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
	}

	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from User").list();
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		//Left join fetch
		Query query = session
				.createQuery("from User user left join fetch user.books where user.id = :id").setInteger("id", id);
		User user = (User) query.uniqueResult();
		
		// It loads accounts collection eagerly even when it's configured to be loaded lazily. Mysterious :)
		// Help me if you can :)
		
		if (user != null) {
			Hibernate.initialize(user.getAccounts());
			//Hibernate.initialize(user.getFavouriteBook());
		}
		
		logger.debug(user.toString());
		return user;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Integer(id));
		if (null != u) {
			session.delete(u);
		}
	}

	@Override
	public Long getUserCount() {
		return (Long) this.sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setProjection(Projections.rowCount()).uniqueResult();
	}

}
