package com.library.libraryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.libraryservice.model.Book;

public interface BookRepository extends MongoRepository<Book, Long>{

}
