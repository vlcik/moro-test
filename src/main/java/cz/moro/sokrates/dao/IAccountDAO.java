package cz.moro.sokrates.dao;

import java.util.List;

import cz.moro.sokrates.model.Account;
 
public interface IAccountDAO {
 
    public void addAccount(Account a);
    public void updateAccount(Account a);
    public List<Account> listAccounts();
    public Account getAccountById(int id);
    public void removeAccount(int id);
}