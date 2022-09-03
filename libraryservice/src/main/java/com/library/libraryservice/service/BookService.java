package com.library.libraryservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryservice.model.Book;
import com.library.libraryservice.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book saveMongo(Book book) {
		bookRepository.save(book);
		return book;
	}
	
	public List<Book> getAll() {
		List<Book> bookList = bookRepository.findAll();
		return bookList;
	}
	
}