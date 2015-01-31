package cz.moro.sokrates.service;

import java.util.List;

import cz.moro.sokrates.model.Book;
 
public interface IBookService {
 
    public void addBook(Book b);
    public void updateBook(Book b);
    public List<Book> listBooks();
    public Book getBookById(int id);
    public void removeBook(int id);
     
}
