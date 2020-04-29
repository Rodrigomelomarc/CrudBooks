package br.com.crudbooks.books.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.crudbooks.user.models.User;
import lombok.Data;

@Entity
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	@ManyToOne
	private User user;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date publishDate;

	public Book() {
	}

	public Book(String title, String description, Date publishDate) {
		this.title = title;
		this.description = description;
		this.publishDate = publishDate;
	}

}
