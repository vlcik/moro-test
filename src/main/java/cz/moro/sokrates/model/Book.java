package cz.moro.sokrates.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Model class representing book entities in table/application
 * 
 * @author Juraj Vlk
 *
 */
@Entity
@Table(name = "books")
public class Book {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	
	@NotNull
	@Column(name = "description")
	@NotEmpty(message="Description of a book cannot be empty.")
	private String description;

	@Column(length = 255, name = "title")
	@NotNull
	@NotEmpty(message="Title of a book cannot be empty.")
	@Size(min = 2, max = 255, message="Length of a title must be between 2 and 255 letters.")
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", description="
				+ description + ", title=" + title + "]";
	}
}
