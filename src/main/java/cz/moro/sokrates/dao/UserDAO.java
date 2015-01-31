package cz.moro.sokrates.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.model.User;
 
 
@Repository
@Transactional
public class UserDAO implements IUserDAO {
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
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
        
        User user = (User) session.createCriteria(User.class).add(Restrictions.idEq(id)).uniqueResult();  
        Hibernate.initialize(user.getAccounts());  
      
        return user;
    }
 
    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new Integer(id));
        if(null != u){
            session.delete(u);
        }
    }
 
}
