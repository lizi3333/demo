package org.example.service;

import org.example.entity.Book;
import org.example.entity.Borrow;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();
    Map<Book, Boolean> getBookList();
    List<Book> getActiveBook();
    void addBorrow(int student,int book);
    void returnBorrow(int id);
    void deleteBook(int id);
    void addBook(String title,String desc,double price);
}
