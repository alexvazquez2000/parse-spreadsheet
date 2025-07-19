package com.games.bean;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
@Entity
@Table(name = "users", indexes = {
		@Index(name = "idx_userid", columnList = "email")
})
public class User implements Serializable {
	/* */
	private static final long serialVersionUID = 4303719742764371645L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name", columnDefinition="varchar(100)")
	private String firstName;
	@Column(name = "last_name", columnDefinition="varchar(100)")
	private String lastName;
	@Column(columnDefinition="varchar(20)")
	private String phone;
	//Index on email - rows must be unique and used as the userid
	@Column(columnDefinition="varchar(100)", unique=true)
	private String email;
	@Column(columnDefinition="varchar(100)")
	private String passwd;
	
	//one-to-one
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coach_id", referencedColumnName = "id")
	private Coach coach;
	
	//one-to-one
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Parent parent;

	public User() {
		//empty constructor
	}

	public User(String firstName, String lastName, String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the coach
	 */
	public Coach getCoach() {
		return coach;
	}

	/**
	 * @param coach the coach to set
	 */
	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	/**
	 * @return the parent
	 */
	public Parent getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Parent parent) {
		this.parent = parent;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

}
