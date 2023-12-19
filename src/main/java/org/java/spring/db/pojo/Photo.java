package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.java.spring.auth.db.pojo.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min = 2, max = 50, message = "Photo's title must be between 2 and 50 characters.")
	private String title;
	
	@Column(columnDefinition = "TEXT")
	@Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
	
    private String url;

    private boolean visible;
    
    @ManyToMany
	private List<Category> categories;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    //CONSTRUCTOR
    
    public Photo() {}
    public Photo(String title, String description, String url, boolean visible, Category...categories) {
    	setTitle(title);
    	setDescription(description);
    	setUrl(url);
    	setVisible(visible);
    	setCategories(categories);
    	
    }
    
    // GETTER & SETTER
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonProperty("categories")
	public List<Category> getCategories() {
		return categories;
	}
	@JsonIgnore
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void setCategories(Category... categories) {	
		setCategories(Arrays.asList(categories));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "] " + getTitle() + " - " 
		+ getDescription() + " (" + getUrl() + ")" + " - "
		+ isVisible();
	}
    
    
    }
