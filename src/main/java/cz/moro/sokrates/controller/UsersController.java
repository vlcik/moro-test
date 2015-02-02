package cz.moro.sokrates.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import cz.moro.sokrates.dao.editor.AccountEditor;
import cz.moro.sokrates.dao.editor.BookEditor;
import cz.moro.sokrates.exception.UserNotFoundException;
import cz.moro.sokrates.model.Account;
import cz.moro.sokrates.model.Book;
import cz.moro.sokrates.model.User;
import cz.moro.sokrates.service.IAccountService;
import cz.moro.sokrates.service.IBookService;
import cz.moro.sokrates.service.IUserService;
import cz.moro.sokrates.validation.UserValidator;

/**
 * Controller ser
 * 
 * @author Juraj Vlk
 *
 */
@Controller
@Transactional
public class UsersController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBookService bookService;
	
	@Autowired
	private IAccountService accountService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory
			.getLogger(UsersController.class);

	@ExceptionHandler(UserNotFoundException.class)
	public String handleResourceNotFoundException() {
		return "elements/ItemNotFound";
	}

	@RequestMapping(value = "/admin/users/view/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable Integer id, Model model) {
		User user = userService.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		model.addAttribute("user", user);
		logger.debug(user.toString());
		return "users/view";
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	@Transactional
	public String listUsers(Model model) {
		List<User> users = userService.listUsers();
		model.addAttribute("users", users);
		logger.debug(users.toString());
		return "users/index";
	}

	@RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("isNew", true);
		model.addAttribute("user", new User());
		return "users/addEditUser";
	}

	@RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)
	public String processCreationForm(@ModelAttribute("user") @Valid User user,
			BindingResult result, SessionStatus status) {
		new UserValidator().validate(user, result);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (result.hasErrors()) {
			return "users/addEditUser";
		} else {
			this.userService.addUser(user);
			status.setComplete();
			return "redirect:/admin/users";
		}
	}

	@RequestMapping(value = "/admin/users/edit/{id}", method = RequestMethod.GET)
	public String initUpdateForm(@PathVariable Integer id, Model model) {
		model.addAttribute("isNew", false);
		logger.debug(bookService.getUserListBooks(id).toString());
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("books", bookService.getUserListBooks(id));
		model.addAttribute("accounts", accountService.getUserListAccounts(id));
		return "users/addEditUser";
	}

	@RequestMapping(value = "/admin/users/edit/{id}", method = {
			RequestMethod.PUT, RequestMethod.POST })
	public String processUpdateForm(@PathVariable Integer id,
			@ModelAttribute("user") @Valid User user, BindingResult result,
			SessionStatus status, Model model) {
		user.setId(id);
		logger.debug(user.toString());
		// phase 3
		new UserValidator().validate(user, result);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		logger.debug(result.toString());
		if (result.hasErrors()) {
			model.addAttribute("books", bookService.getUserListBooks(id));
			model.addAttribute("accounts", accountService.getUserListAccounts(id));
			return "users/addEditUser";
		} else {
			this.userService.updateUser(user);
			status.setComplete();
			return "redirect:/admin/users";
		}
	}

	@RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException();
		} else {
			userService.removeUser(id);
		}
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(
			@RequestParam(value = "login_error", required = false) Integer error,
			Model model) {
		if ((error != null) && (error == 1)) {
			model.addAttribute("error", "true");
		}
		return "login";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Book.class, "favouriteBook", new BookEditor(bookService));
		binder.registerCustomEditor(Account.class, "favouriteAccount", new AccountEditor(accountService));
	}
}
