package cz.moro.sokrates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.dao.IBookDAO;
import cz.moro.sokrates.model.Book;
import cz.moro.sokrates.service.IBookService;

@Service
public class BookService implements IBookService {

	
	private IBookDAO bookDAO;
	 
	@Autowired
    public void setBookDAO(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
 
    @Transactional
    public void addBook(Book b) {
        this.bookDAO.addBook(b);
    }
 
    @Transactional
    public void updateBook(Book b) {
        this.bookDAO.updateBook(b);
    }
 
    @Override
    @Transactional
    public List<Book> listBooks() {
        return this.bookDAO.listBooks();
    }
 
    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.bookDAO.getBookById(id);
    }
 
    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDAO.removeBook(id);
    }
    
    public List<Book> getUserListBooks(int id) {
    	return this.bookDAO.getUserListBooks(id);
    }
}
