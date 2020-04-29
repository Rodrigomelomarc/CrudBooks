package br.com.crudbooks.books.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.crudbooks.security.services.PermissionService;
import br.com.crudbooks.user.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.crudbooks.books.dtos.BookDto;
import br.com.crudbooks.books.dtos.BookFormDto;
import br.com.crudbooks.books.services.BookService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;


	@GetMapping
	public Page<BookDto> index(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {
		return bookService.getBooks(pagination);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<BookDto> store(@RequestBody @Valid BookFormDto bookFormDto, UriComponentsBuilder uriBuilder) {
		var bookDto = bookService.saveBook(bookFormDto);
		var uri = uriBuilder.path("api/v1/books/{id}").buildAndExpand(bookDto.getId()).toUri();
		return ResponseEntity.created(uri).body(bookDto);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		var optional = bookService.getBookById(id);
		if (optional.isPresent())
			return ResponseEntity.ok(optional.get());
		return ResponseEntity.notFound().build();
	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid BookFormDto bookFormDto) {
		var bookDto = bookService.updateBook(id, bookFormDto);

		if (bookDto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(bookDto);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> destroy(@PathVariable("id") Long id) {
		var deleted = bookService.destroyBook(id);
		if (deleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
