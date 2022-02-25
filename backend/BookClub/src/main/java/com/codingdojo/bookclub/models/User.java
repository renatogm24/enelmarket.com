package com.codingdojo.bookclub.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
    
@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="¡Se requiere Nombre!")
    @Size(min=3, max=30, message="Nombre debe tener entre 3 y 30 caracteres")
    private String name;
    
    @NotEmpty(message="¡Se requiere Email!")
    @Email(message="¡Ingrese un Email válido!")
    private String email;
    
    @NotEmpty(message="¡Se requiere contraseña!")
    @Size(min=8, max=128, message="La contraseña debe tener entre 8 y 128 caracteres")
    private String password;
    
    @Transient
    @NotEmpty(message="¡Se requiere validar la contraseña!")
    @Size(min=8, max=128, message="La contraseña debe tener entre 8 y 128 caracteres")
    private String confirm;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Book> books;
    
    @OneToMany(mappedBy="borrower", fetch = FetchType.LAZY)
    private List<Book> borrowbooks;
  
    public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBorrowbooks() {
		return borrowbooks;
	}

	public void setBorrowbooks(List<Book> borrowbooks) {
		this.borrowbooks = borrowbooks;
	}
    
	
	
}