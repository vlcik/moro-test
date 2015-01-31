package cz.moro.sokrates.service;

import java.util.List;

import cz.moro.sokrates.model.User;
 
public interface IUserService {
 
    public void addUser(User u);
    public void updateUser(User u);
    public List<User> listUsers();
    public User getUserById(int id);
    public void removeUser(int id);
     
}
