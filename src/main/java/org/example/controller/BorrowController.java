package org.example.controller;

import jakarta.annotation.Resource;
import org.example.service.BookService;
import org.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BorrowController {
    @Resource
    BookService service;
    @Resource
    UserService userService;
    @GetMapping({"/","/borrow"})
    public String borrow(Model model){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname",user.getUsername());
        model.addAttribute("borrow_list",service.getBorrowList());
        model.addAttribute("student_count",userService.getStudentList().size());
        model.addAttribute("book_count",service.getBookList().size());
        return "index";
    }
    @GetMapping("/add-borrow")
    public String addBorrow(Model model){

        model.addAttribute("book_list",service.getActiveBook());
        model.addAttribute("student_list",userService.getStudentList());
        return "add-borrow";
    }
    @PostMapping("/add-borrow")
    public String addBorrowPost(int student ,int book){
        service.addBorrow(student,book);
        return "redirect:borrow";
    }
    @GetMapping("/return-book")
    public String returnBorrow(int id){
        service.returnBorrow(id);
        return "redirect:borrow";
    }
}
