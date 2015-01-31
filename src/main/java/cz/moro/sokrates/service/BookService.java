package cz.moro.sokrates.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.moro.sokrates.dao.IBookDAO;
import cz.moro.sokrates.model.Book;

@Service
public class BookService implements IBookService {

	private IBookDAO BookDAO;
	 
    public void setBookDAO(IBookDAO BookDAO) {
        this.BookDAO = BookDAO;
    }
 
    @Transactional
    public void addBook(Book b) {
        this.BookDAO.addBook(b);
    }
 
    @Transactional
    public void updateBook(Book b) {
        this.BookDAO.updateBook(b);
    }
 
    @Override
    @Transactional
    public List<Book> listBooks() {
        return this.BookDAO.listBooks();
    }
 
    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.BookDAO.getBookById(id);
    }
 
    @Override
    @Transactional
    public void removeBook(int id) {
        this.BookDAO.removeBook(id);
    }
}
