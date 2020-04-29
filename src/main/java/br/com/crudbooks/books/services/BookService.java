package br.com.crudbooks.books.services;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.crudbooks.books.models.Book;
import br.com.crudbooks.security.services.PermissionService;
import br.com.crudbooks.user.services.UserService;
import br.com.crudbooks.utils.exceptions.UserNotFoundException;
import org.aspectj.asm.IModelFilter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.crudbooks.books.dtos.BookDto;
import br.com.crudbooks.books.dtos.BookFormDto;
import br.com.crudbooks.books.repositories.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	private final UserService userService;
	private final ModelMapper mapper;
	private final PermissionService permissionService;

	public Page<BookDto> getBooks(Pageable pagination) {
		var books = bookRepository.findAll(pagination);
		return this.booksToPagesDto(books);
	}

	public BookDto saveBook(@Valid BookFormDto bookFormDto) throws UserNotFoundException {
		var book = bookFormDto.converter();
		book.setUser(userService.getCurrentUser());
		book = bookRepository.save(book);
		return this.bookToDto(book);
	}

	public Optional<BookDto> getBookById(Long id) {
		var optional = bookRepository.findById(id);
		return this.optionalBookToOptionalDto(optional);
	}

	public BookDto updateBook(Long id, @Valid BookFormDto bookFormDto) {
		var optional = bookRepository.findById(id);
		if (optional.isPresent()) {
			permissionService.verifyBookUser(optional.get().getUser().getId());
			var book = bookFormDto.update(optional.get(), id);
			book = bookRepository.save(book);
			return this.bookToDto(book);
		}
		return null;
	}

	public boolean destroyBook(Long id) {
		var optional = bookRepository.findById(id);

		if (optional.isPresent()) {
			permissionService.verifyBookUser(optional.get().getUser().getId());
			bookRepository.delete(optional.get());
			return true;
		}

		return false;
	}

	private Page<BookDto> booksToPagesDto(Page<Book> books) {
		return books.map(BookDto::new);
	}

	private Optional<BookDto> optionalBookToOptionalDto(Optional<Book> book) {
		return book.map(BookDto::new);
	}

	private BookDto bookToDto(Book book) {
		return mapper.map(book, BookDto.class);
	}

}
