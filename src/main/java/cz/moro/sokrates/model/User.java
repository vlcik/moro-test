package cz.moro.sokrates.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Model class representing account user in table/application
 * 
 * @author Juraj Vlk
 *
 */

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 255)
	@NotEmpty(message = "Name of a user cannot be empty.")
	@Size(min = 2, max = 255, message = "Length of a name must be between 2 and 255 letters.")
	private String name;

	@Column(length = 255)
	@NotEmpty(message = "Username cannot be empty.")
	@Size(min = 2, max = 255, message = "Length of a username must be between 2 and 255 characters.")
	private String username;

	@Column(name = "password")
	@NotEmpty(message = "Password cannot be empty.")
	@Size(min = 6, message = "Password must contain at least 6 characters.")
	private String password;

	@Transient
	private String verifiedPassword;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Book> books;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Account> accounts;

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	@JoinColumn(name = "favourite_book_id")
	private Book favouriteBook;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	@JoinColumn(name = "favourite_account_id")
	private Account favouriteAccount;
	
	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVerifiedPassword() {
		return verifiedPassword;
	}

	public void setVerifiedPassword(String verifiedPassword) {
		this.verifiedPassword = verifiedPassword;
	}

	public Book getFavouriteBook() {
		return favouriteBook;
	}

	public void setFavouriteBook(Book favouriteBook) {
		this.favouriteBook = favouriteBook;
	}

	public Account getFavouriteAccount() {
		return favouriteAccount;
	}

	public void setFavouriteAccount(Account favouriteAccount) {
		this.favouriteAccount = favouriteAccount;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username
				+ ", password=" + password + ", verifiedPassword="
				+ verifiedPassword + ", books=" + books + ", accounts="
				+ accounts + ", favouriteBook=" + favouriteBook
				+ ", favouriteAccount=" + favouriteAccount + "]";
	}

}
