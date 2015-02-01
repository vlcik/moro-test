package cz.moro.sokrates.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.moro.sokrates.dao.IUserDAO;
import cz.moro.sokrates.model.User;

@Service
public class UserService implements IUserService {

	private IUserDAO userDao;
	 
    public void setUserDao(IUserDAO userDao) {
        this.userDao = userDao;
    }
 
    public void addUser(User p) {
        this.userDao.addUser(p);
    }
 
    public void updateUser(User p) {
        this.userDao.updateUser(p);
    }
 
    @Override
    public List<User> listUsers() {
        return this.userDao.listUsers();
    }
 
    @Override
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }
 
    @Override
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

	@Override
	public Long getUserCount() {
		return this.userDao.getUserCount();
	}
}
