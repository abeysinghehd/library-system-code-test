package abeysinghe.hd.library.service;

import java.util.List;

import abeysinghe.hd.library.entity.Book;
import abeysinghe.hd.library.entity.Borrower;

public interface LibraryService {

	public void registerBorrower(Borrower borrower);

	public void registerBook(Book book);

	public void borrowBook(Long id);

	public void returnBook(Long id);

	public List<Book> getAllBooks();

}
