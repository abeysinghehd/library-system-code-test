package abeysinghe.hd.library.repository;

import abeysinghe.hd.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
		public Book findFirstByIsbn(String isbn);

}
