package cz.moro.sokrates.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.model.Account;

@Repository
@Transactional
public class AccountDao implements IAccountDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addAccount(Account a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(a);
	}

	@Override
	public void updateAccount(Account a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> listAccounts() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Account").list();
	}

	@SuppressWarnings("unchecked")
	public List<Account> getUserListAccounts(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(Account.class)
				.add(Restrictions.eq("user.id", id)).list();
	}

	@Override
	public Account getAccountById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Account) session.get(Account.class, new Integer(id));
	}

	@Override
	public void removeAccount(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Account a = (Account) session.load(Account.class, new Integer(id));
		if (null != a) {
			session.delete(a);
		}
	}

}
