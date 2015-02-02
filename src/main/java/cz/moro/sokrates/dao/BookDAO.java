package cz.moro.sokrates.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.model.Book;

@Repository
@Transactional
public class BookDAO implements IBookDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addBook(Book b) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(b);
	}

	@Override
	public void updateBook(Book b) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(b);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBooks() {

		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Book").list();
	}

	@SuppressWarnings("unchecked")
	public List<Book> getUserListBooks(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(Book.class)
				.add(Restrictions.eq("user.id", id)).list();
	}

	@Override
	public Book getBookById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Book b = (Book) session.get(Book.class, new Integer(id));
		return b;
	}

	@Override
	public void removeBook(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Book b = (Book) session.load(Book.class, new Integer(id));
		if (null != b) {
			session.delete(b);
		}
	}

}
