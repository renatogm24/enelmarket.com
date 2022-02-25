package com.codingdojo.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;

@Controller
public class BookController {
	
	@Autowired	
	private BookService bookService;
	
	@Autowired	
	private UserService userService;


	@RequestMapping("/books")
	 public String index(Model model, HttpSession session) {
        List<Book> books = bookService.allBooks();	
        model.addAttribute("books", books);
        Long id = (Long) session.getAttribute("userid");
        User user = userService.getUserSession(id);
		model.addAttribute("user", user);
        return "/dashboard.jsp";
    }
	
	@RequestMapping("/bookmarket")
	 public String bookmarket(Model model, HttpSession session) {
       
       Long id = (Long) session.getAttribute("userid");
       User user = userService.getUserSession(id);
       
       List<Book> booksAvailable = bookService.allBooksAvailable();
       //List<Book> booksBorrowedBy = bookService.allBooksBorrowedBy(user);
       List<Book> booksBorrowedBy = bookService.allBooksBorrowedById(id);
       
       model.addAttribute("booksAvailable", booksAvailable);
       model.addAttribute("booksBorrowedBy", booksBorrowedBy);
       
       model.addAttribute("user", user);
       return "/books/market.jsp";
   }
	
	@GetMapping("/books/{id}/borrow")
	public String borrowBook(@PathVariable("id") Long book_id, Model model, HttpSession session) {		
		Long user_id = (Long) session.getAttribute("userid");
		User user = userService.getUserSession(user_id);
		
		Book bookAux = bookService.findBook(book_id);
		Long iduser = bookAux.getUser().getId();
		
		if (user_id.equals(iduser)) {
			return "redirect:/bookmarket";
		}else {
			bookService.borrowBookId(book_id, user_id);
			return "redirect:/bookmarket";	
		}
		
		//bookService.borrowBook(book_id, user);
		//bookService.borrowBookId(book_id, user_id);
		//return "redirect:/bookmarket";	
	}
	
	
	@GetMapping("/books/{id}/return")
	public String returnBook(@PathVariable("id") Long book_id, Model model, HttpSession session) {
		Long user_id = (Long) session.getAttribute("userid");
		bookService.returnBook(book_id, user_id);
		return "redirect:/bookmarket";			
	}
	

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model, HttpSession session) {
		
		if(result.hasErrors()) {
			model.addAttribute("books", bookService.allBooks());
			return "/books/new.jsp";
		}
		
		book.setUser(userService.getUserSession((Long) session.getAttribute("userid")));
		book.setBorrower(null);
		
		bookService.createBook(book);
		return "redirect:/books";
	}
	
	@RequestMapping("/books/new")
	 public String newBook(@ModelAttribute("book") Book book) {
       return "/books/new.jsp";
   }

	@RequestMapping("/books/{id}")
	public String show(Model model, @PathVariable("id") Long id) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "/books/show.jsp";
	}
	
	@GetMapping("/books/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		Book bookAux = bookService.findBook(id);
		Long idsession = (Long) session.getAttribute("userid");
		Long iduser = (Long) bookAux.getUser().getId();
		if (iduser.equals(idsession)) {
			model.addAttribute("book", bookAux);
			return "/books/edit.jsp";
		}else {
			System.out.println("Holaaa");
			return "redirect:/books";	
		}		
	}

	@PutMapping("/books/{id}")
	public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "/books/edit.jsp";
		} else {			
			bookService.updateBook(book);
			return "redirect:/books";
		}
	}
	
	@GetMapping("/books/{id}/delete")
    public String destroy(@PathVariable("id") Long id, HttpSession session) {
		Book bookAux = bookService.findBook(id);
		Long idsession = (Long) session.getAttribute("userid");
		Long iduser = (Long) bookAux.getUser().getId();
		if (iduser.equals(idsession)) {
			bookService.deleteBook(id);
			return "redirect:/books";	
		}else {
			System.out.println("Holaaa");
			return "redirect:/books";	
		}	
    }
}
