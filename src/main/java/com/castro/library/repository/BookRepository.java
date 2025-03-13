package com.castro.library.repository;

import com.castro.library.model.Author;
import com.castro.library.model.Book;
import com.castro.library.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<List<Book>> findByAuthor(Author author);

    Optional<List<Book>> findByTitle(String title);

    Optional<Book> findByIsbn(String isbn);

    @Query(" select b from Book as b order by b.title, b.price ")
    Optional<List<Book>> listAllOrderByTitleAndPrice();

    @Query(" select a from Book b join b.author a ")
    Optional<List<Author>> listAuthorsWithBooks();

    @Query(" select distinct b.title from Book b ")
    Optional<List<Book>> listDistinctByTitle();

    @Query("""
                select b.genre
                from Book b
                join b.author a
                where a.nationality = 'Brazilian'
                order by b.genre
            """)
    Optional<List<String>> listGenresFromBrazilianAuthors();

    @Query("select b from Book b where b.genre = :genre order by :orderParam")
    Optional<List<Book>> findByGenre(BookGenre genre, @Param("orderParam") String propertyName);
}
