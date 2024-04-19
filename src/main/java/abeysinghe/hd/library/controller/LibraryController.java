package abeysinghe.hd.library.controller;

import java.util.List;
import abeysinghe.hd.library.entity.Book;
import abeysinghe.hd.library.entity.Borrower;
import abeysinghe.hd.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("library")
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	@Tag(name = "Books")
	@Operation(summary = "Register a new book")
	@PostMapping("book")
	public ResponseEntity<String> registerBook(@Valid @RequestBody Book book){
		libraryService.registerBook(book);
		return new ResponseEntity<>("Book registered", HttpStatus.CREATED);
	}

	@Tag(name = "Books")
	@Operation(summary = "Get all books")
	@GetMapping("book")
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = libraryService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@Tag(name = "Borrowing")
	@Operation(summary = "Register a new borrower")
	@PostMapping("borrower")
	public ResponseEntity<String> registerBorrower(@Valid @RequestBody Borrower borrower){
		libraryService.registerBorrower(borrower);
		return new ResponseEntity<>("Borrower registered", HttpStatus.CREATED);
	}

	@Tag(name = "Borrowing")
	@Operation(summary = "Borrow a book")
	@PostMapping("book/{id}/borrow")
	public ResponseEntity<String> borrowBook(@PathVariable Long id){
		libraryService.borrowBook(id);
		return new ResponseEntity<>("Book borrowed", HttpStatus.OK);
	}

	@Tag(name = "Borrowing")
	@Operation(summary = "Return a book")
	@PostMapping("book/{id}/return")
	public ResponseEntity<String> returnBook(@PathVariable Long id){
		libraryService.returnBook(id);
		return new ResponseEntity<>("Book returned", HttpStatus.OK);
	}

}
