package com.codingdojo.bookclub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.User;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {

	List<Book> findAll();
	
	List<Book> findAllByBorrower(User user);
	
	List<Book> findAllByBorrower_Id(Long id);
	
	@Transactional
	@Modifying
	@Query("update Book b set b.borrower = :user where b.id = :id")
	void borrow(@Param(value = "id") long id, @Param(value = "user") User user);
	
	@Transactional
	@Modifying
	@Query("update Book b set b.borrower.id = :user_id where b.id = :id")
	void borrowId(@Param(value = "id") long id, @Param(value = "user_id") long user_id);
	
	@Transactional
	@Modifying
	@Query("update Book b set b.borrower = null where b.id = :id and b.borrower.id = :user_id")
	void returnBook(@Param(value = "id") long id, @Param(value = "user_id") long user_id);
	
}
