package cz.moro.sokrates.dao;

import java.util.List;

import cz.moro.sokrates.model.User;
 
public interface IUserDAO {
 
    public void addUser(User p);
    public void updateUser(User p);
    public List<User> listUsers();
    public User getUserById(int id);
    public void removeUser(int id);
}