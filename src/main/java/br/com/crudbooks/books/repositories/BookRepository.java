package br.com.crudbooks.books.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.crudbooks.books.models.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>{
}
