package br.com.crudbooks.books.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.crudbooks.books.models.Book;
import lombok.Data;

@Data
public class BookFormDto {

	@NotNull
	@Length(min = 3)
	@NotBlank
	private String title;
	@NotNull
	@Length(min = 5)
	@NotBlank
	private String description;
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date publishDate;

	public Book converter() {
		return new Book(title, description, publishDate);
	}

	public Book update(Book book, Long id) {
		book.setId(id);
		book.setTitle(this.title);
		book.setDescription(this.description);
		book.setPublishDate(this.publishDate);
		return book;
	}

}
