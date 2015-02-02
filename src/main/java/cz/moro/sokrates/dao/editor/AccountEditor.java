package cz.moro.sokrates.dao.editor;

import java.beans.PropertyEditorSupport;

import cz.moro.sokrates.model.Account;
import cz.moro.sokrates.service.IAccountService;

/**
 * Converter between String representation of ID and Account entity
 * @author Juraj Vlk
 *
 */
public class AccountEditor extends PropertyEditorSupport {

	private IAccountService service;

	public AccountEditor(IAccountService service) {
		super();
		this.service = service;
	}

	public void setAsText(String text) {
		Account account = new Account();
		account = service.getAccountById(Integer.parseInt(text));
		setValue(account);
	}

	public String getAsText() {
		Account account = (Account) this.getValue();
		if (account != null)
			return String.valueOf(account.getId());
		return null;
	}
}
