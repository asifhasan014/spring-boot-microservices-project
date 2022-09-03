package com.library.libraryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryservice.model.Book;
import com.library.libraryservice.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBookMongo")
	public Book createEmployee(@RequestBody Book book) {
		return bookService.saveMongo(book);
	}
	
	@GetMapping("/getAllBook")
	public List<Book> getAllBooks(){
		return bookService.getAll();
	}
}