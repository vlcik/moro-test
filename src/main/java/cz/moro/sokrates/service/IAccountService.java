package cz.moro.sokrates.service;

import java.util.List;

import cz.moro.sokrates.model.Account;
 
public interface IAccountService {
 
    public void addAccount(Account a);
    public void updateAccount(Account a);
    public List<Account> listAccounts();
    public Account getAccountById(int id);
    public void removeAccount(int id);
    public List<Account> getUserListAccounts(int id);
}
