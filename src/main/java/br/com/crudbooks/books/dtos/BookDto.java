package br.com.crudbooks.books.dtos;

import java.util.Date;
import java.util.Optional;

import br.com.crudbooks.user.dtos.UserDto;
import br.com.crudbooks.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Page;

import br.com.crudbooks.books.models.Book;
import lombok.Getter;

@Getter
public class BookDto {

	private Long id;
	private String title;
	private String description;
	private Date publishDate;
	@JsonIgnore
	private Long userId;

	public BookDto () {}

	public BookDto(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.description = book.getDescription();
		this.publishDate = book.getPublishDate();
		this.userId = book.getUser().getId();
	}
}
