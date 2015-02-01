package cz.moro.sokrates.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import cz.moro.sokrates.exception.AccountNotFoundException;
import cz.moro.sokrates.model.Account;
import cz.moro.sokrates.model.User;
import cz.moro.sokrates.service.IAccountService;
import cz.moro.sokrates.service.IUserService;

/**
 * Controller ser
 * @author Juraj Vlk
 *
 */
@Controller
@RequestMapping("/admin/users/{user_id}/accounts/")
public class AccountsController {

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

	@ExceptionHandler(AccountNotFoundException.class)
	public String handleResourceNotFoundException() {
		return "elements/ItemNotFound";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("user_id") Integer userId, Model model) {
		model.addAttribute("isNew", true);
		model.addAttribute("account", new Account());
		model.addAttribute("user", userService.getUserById(userId));
        return "accounts/addEditAccount";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String processCreationForm(@PathVariable("user_id") Integer userId, @ModelAttribute("account") @Valid Account account, BindingResult result, SessionStatus status, Model model) {
		account.setUser(new User(userId));
		logger.debug(result.toString());
        if (result.hasErrors()) {
        	model.addAttribute("user", userService.getUserById(userId));
            return "accounts/addEditAccount";
        } 
        else {
            this.accountService.addAccount(account);
            status.setComplete();
            return "redirect:/admin/users/view/" + userId;
        }
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("user_id") Integer userId, @PathVariable("id") Integer id, Model model) {
		model.addAttribute("isNew", false);
		model.addAttribute("account", accountService.getAccountById(id));
		model.addAttribute("user", userService.getUserById(userId));
        return "accounts/addEditAccount";
    }
	
	@RequestMapping(value = "edit/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
	public String processUpdateForm(@PathVariable("user_id") Integer userId, @PathVariable Integer id, @ModelAttribute("account") @Valid Account account, BindingResult result, SessionStatus status, Model model) {
		logger.debug(result.toString());
		account.setId(id);
		account.setUser(new User(userId));
        if (result.hasErrors()) {
        	model.addAttribute("user", userService.getUserById(userId));
            return "accounts/addEditAccount";
        } 
        else {
            this.accountService.updateAccount(account);
            status.setComplete();
            return "redirect:/admin/users/view/" + userId;
        }
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable("user_id") Integer userId, @PathVariable("id") Integer id) {
		Account account = accountService.getAccountById(id);	
		if (account == null){
			throw new AccountNotFoundException();
		}
		else {
			accountService.removeAccount(id);
		}
		return "redirect:/admin/users/view/" + userId;
    }
}
