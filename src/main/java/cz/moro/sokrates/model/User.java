package cz.moro.sokrates.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 255)
	@NotEmpty(message="Name of a user cannot be empty.")
	@Size(min = 2, max = 255, message="Length of a name must be between 2 and 255 letters.")
	private String name;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Book> books;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Account> accounts;

	public User(){}
	
	public User(int id){
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", books=" + books
				+ ", accounts=" + accounts + "]";
	}
}
