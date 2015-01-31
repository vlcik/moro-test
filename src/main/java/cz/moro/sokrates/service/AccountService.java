package cz.moro.sokrates.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.dao.IAccountDAO;
import cz.moro.sokrates.model.Account;

@Service
public class AccountService implements IAccountService {

	private IAccountDAO AccountDAO;
	 
    public void setAccountDAO(IAccountDAO AccountDAO) {
        this.AccountDAO = AccountDAO;
    }
 
    @Transactional
    public void addAccount(Account a) {
        this.AccountDAO.addAccount(a);
    }
 
    @Transactional
    public void updateAccount(Account a) {
        this.AccountDAO.updateAccount(a);
    }
 
    @Override
    @Transactional
    public List<Account> listAccounts() {
        return this.AccountDAO.listAccounts();
    }
 
    @Override
    @Transactional
    public Account getAccountById(int id) {
        return this.AccountDAO.getAccountById(id);
    }
 
    @Override
    @Transactional
    public void removeAccount(int id) {
        this.AccountDAO.removeAccount(id);
    }
}
