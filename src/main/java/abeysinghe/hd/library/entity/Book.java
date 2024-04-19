package abeysinghe.hd.library.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Book {

	public Book() {
	}

	public Book(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}

	public Book(String isbn, String title, String author, Boolean borrowed) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.borrowed = borrowed;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(hidden = true)
	private Long bookId;

	@Column(nullable = false)
	@NotBlank(message = "ISBN is mandatory")
	private String isbn;

	@Column(nullable = false)
	@NotBlank(message = "Title is mandatory")
	private String title;

	@Column(nullable = false)
	@NotBlank(message = "Author is mandatory")
	private String author;

	@Schema(hidden = true)
	@Column(nullable = false)
	private Boolean borrowed;

	@Schema(hidden = true)
	@Version
	private Integer version;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(Boolean borrowed) {
		this.borrowed = borrowed;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
