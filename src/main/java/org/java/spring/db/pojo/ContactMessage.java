package org.java.spring.db.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class ContactMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Message cannot be empty")
    @Size(min = 10, max = 500, message = "Message must be between 10 and 500 characters")
	private String message;
	
	// CONSTRUCTORS
	
	public ContactMessage() {}
	public ContactMessage(String email, String message) {
		setEmail(email);
    	setMessage(message);
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "] " + getEmail() + " - " + getMessage();
	}
	
	
	
}
