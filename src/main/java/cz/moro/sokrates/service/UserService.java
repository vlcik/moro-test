package cz.moro.sokrates.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.dao.IUserDAO;
import cz.moro.sokrates.model.User;

@Service
public class UserService implements IUserService {

	private IUserDAO UserDAO;
	 
    public void setUserDAO(IUserDAO UserDAO) {
        this.UserDAO = UserDAO;
    }
 
    @Transactional
    public void addUser(User p) {
        this.UserDAO.addUser(p);
    }
 
    @Transactional
    public void updateUser(User p) {
        this.UserDAO.updateUser(p);
    }
 
    @Override
    @Transactional
    public List<User> listUsers() {
        return this.UserDAO.listUsers();
    }
 
    @Override
    @Transactional
    public User getUserById(int id) {
        return this.UserDAO.getUserById(id);
    }
 
    @Override
    @Transactional
    public void removeUser(int id) {
        this.UserDAO.removeUser(id);
    }
}
