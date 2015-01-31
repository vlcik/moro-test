package cz.moro.sokrates.dao;

import java.util.List;

import cz.moro.sokrates.model.Book;
 
public interface IBookDAO {
 
    public void addBook(Book p);
    public void updateBook(Book b);
    public List<Book> listBooks();
    public Book getBookById(int id);
    public void removeBook(int id);
}