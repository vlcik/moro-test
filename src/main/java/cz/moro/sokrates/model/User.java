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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import cz.moro.sokrates.validation.annotation.FieldsMatch;

/**
 * Model class representing account user in table/application
 * 
 * @author Juraj Vlk
 *
 */

@Entity
@Table(name = "users")
@FieldsMatch.List({
    @FieldsMatch(first = "password", second = "verifiedPassword", message = "The password fields must match")
})
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
	@Size(min = 2, max = 255, message = "Length of a username must be between 2 and 255 letters.")
	private String username;

	@Column(name = "password")
	@NotEmpty(message = "Password cannot be empty.")
	private String password;

	@Transient
	private String verifiedPassword;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Book> books;

	@OneToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "user_id")
	private List<Account> accounts;

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


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username
				+ ", password=" + password + ", verifiedPassword="
				+ verifiedPassword + ", books=" + books + ", accounts="
				+ accounts + "]";
	}

}
