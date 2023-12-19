package org.java.spring.db.pojo;

import org.java.spring.auth.db.pojo.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class ContactMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String email;
	
	
	private String message;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
    private User user;
	
	// CONSTRUCTORS
	
	public ContactMessage() {}
	public ContactMessage(String email, String message) {
		setEmail(email);
    	setMessage(message);
    	setUser(user);
	}
	
	//GETTER & SETTER
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "] " + getEmail() + " - " + getMessage();
	}
	
	
	
}
