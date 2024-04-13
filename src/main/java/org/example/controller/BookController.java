package org.example.controller;

import jakarta.annotation.Resource;
import org.example.service.BookService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class BookController {
    @Resource
    BookService service;
    @GetMapping("/books")
    public String book(Model model){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname",user.getUsername());
        model.addAttribute("book_list",service.getBookList().keySet());
        model.addAttribute("book_list_status",new ArrayList<>(service.getBookList().values()));
        return "books";
    }
    @GetMapping("/delete-book")
    public String deleteBook(int bid){
        service.deleteBook(bid);
        return "redirect:books";
    }
    @GetMapping("/add-book")
    public String addBook(){
        return "add-book";
    }
    @PostMapping("/add-book")
    public String addBookPost(String title,String desc,double price){
        service.addBook(title, desc, price);
        return "redirect:books";
    }
}
