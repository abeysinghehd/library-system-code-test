package abeysinghe.hd.library.service;

import java.util.List;
import java.util.Optional;
import abeysinghe.hd.library.entity.Book;
import abeysinghe.hd.library.entity.Borrower;
import abeysinghe.hd.library.exceptions.LibraryException;
import abeysinghe.hd.library.repository.BookRepository;
import abeysinghe.hd.library.repository.BorrowerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService{

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BorrowerRepository borrowerRepository;

	@Override
	public void registerBorrower(Borrower borrower) {
		try{
			borrowerRepository.save(borrower);
		} catch (DataIntegrityViolationException e){
			throw new LibraryException("Email already exists");
		}
	}

	@Override
	public void registerBook(Book book) {
		Book existingBook = bookRepository.findFirstByIsbn(book.getIsbn());
		if(existingBook!= null && (!book.getAuthor().equals(existingBook.getAuthor()) || !book.getTitle().equals(existingBook.getTitle()))){
			throw new LibraryException("Books with same ISBN must have same title and author");
		}
		book.setBorrowed(false);
		bookRepository.save(book);
	}

	@Override
	@Transactional
	public void borrowBook(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		book.ifPresentOrElse(
				(value) -> {
					if(value.getBorrowed()){
						throw new LibraryException("Book already borrowed");
					} else {
						value.setBorrowed(true);
						bookRepository.save(value);
					}
				},
				() -> {
					throw new LibraryException("Book is not found for the given id");
				}
		);
	}

	@Override
	public void returnBook(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		book.ifPresentOrElse(
				(value) -> {
					if(!value.getBorrowed()){
						throw new LibraryException("Book is not in borrowed state");
					} else {
						value.setBorrowed(false);
						bookRepository.save(value);
					}
				},
				() -> {
					throw new LibraryException("Book is not found for the given id");
				}
		);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

}
