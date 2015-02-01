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

import cz.moro.sokrates.exception.BookNotFoundException;
import cz.moro.sokrates.model.Book;
import cz.moro.sokrates.model.User;
import cz.moro.sokrates.service.IBookService;
import cz.moro.sokrates.service.IUserService;

/**
 * Controller ser
 * @author Juraj Vlk
 *
 */
@Controller
@RequestMapping("/admin/users/{user_id}/books/")
public class BooksController {

	@Autowired
	private IBookService bookService;
	
	@Autowired
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

	@ExceptionHandler(BookNotFoundException.class)
	public String handleResourceNotFoundException() {
		return "elements/ItemNotFound";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("user_id") Integer userId, Model model) {
		model.addAttribute("isNew", true);
		model.addAttribute("book", new Book());
		model.addAttribute("user", userService.getUserById(userId));
        return "books/addEditBook";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String processCreationForm(@PathVariable("user_id") Integer userId, @ModelAttribute("book") @Valid Book book, BindingResult result, SessionStatus status, Model model) {
		book.setUser(new User(userId));
		logger.info(result.toString());
        if (result.hasErrors()) {
        	model.addAttribute("user", userService.getUserById(userId));
            return "books/addEditBook";
        } 
        else {
            this.bookService.addBook(book);
            status.setComplete();
            return "redirect:/admin/users/view/" + userId;
        }
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("user_id") Integer userId, @PathVariable("id") Integer id, Model model) {
		model.addAttribute("isNew", false);
		model.addAttribute("book", bookService.getBookById(id));
		model.addAttribute("user", userService.getUserById(userId));
        return "books/addEditBook";
    }
	
	@RequestMapping(value = "edit/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
	public String processUpdateForm(@PathVariable("user_id") Integer userId, @PathVariable Integer id, @ModelAttribute("book") @Valid Book book, BindingResult result, SessionStatus status, Model model) {
		book.setId(id);
		book.setUser(new User(userId));
		logger.info(result.toString());
        if (result.hasErrors()) {
        	model.addAttribute("user", userService.getUserById(userId));
            return "books/addEditBook";
        } 
        else {
            this.bookService.updateBook(book);
            status.setComplete();
            return "redirect:/admin/users/view/" + userId;
        }
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("user_id") Integer userId, @PathVariable("id") Integer id) {
		Book Book = bookService.getBookById(id);	
		if (Book == null){
			throw new BookNotFoundException();
		}
		else {
			bookService.removeBook(id);
		}
		return "redirect:/admin/users/view/" + userId;
    }
}
