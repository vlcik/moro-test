package cz.moro.sokrates.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Model class representing account entities in table/application
 * 
 * @author Juraj Vlk
 *
 */

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "user_id")
	@ManyToOne (fetch=FetchType.LAZY)
	private User user;
	
	@Column(length = 4, name = "account_prefix")
	@Pattern(regexp = "[0-9]{0,4}", message="Prefix must be numbers.")
	private String prefix;

	@Column(name = "account_number")
	@NotNull
	@NotEmpty(message="Account number cannot be empty.")
	@Size(min = 8, max = 10, message="Length of account number must be between 8 and 10 numbers.")
	@Pattern(regexp = "[0-9]{8,10}", message="Account number must only contain numbers.")
	private String number;
	
	@Column(length = 4, name = "bank_code")
	@NotNull
	@NotEmpty(message="Bank code cannot be empty.")
	@Size(min = 2, max = 4, message="Length of bank code must be between 2 and 4 letters.")
	@Pattern(regexp = "[0-9]{2,4}", message="Bank code must be numbers.")
	private String bankCode;

	public Account() {
		super();
	}

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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String accountPrefix) {
		this.prefix = accountPrefix;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String accountNumber) {
		this.number = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + user.getName() + ", prefix="
				+ prefix + ", number=" + number
				+ ", bankCode=" + bankCode + "]";
	}

	
}
