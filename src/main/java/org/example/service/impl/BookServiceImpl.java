package org.example.service.impl;

import jakarta.annotation.Resource;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.mapper.BookMapper;
import org.example.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper mapper;
    @Override
    public List<Borrow> getBorrowList() {
        return mapper.getBorrowList();
    }

    @Override
    public Map<Book,Boolean> getBookList() {
        Set<Integer> set=new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBid()));
        Map<Book,Boolean> map=new LinkedHashMap<>();
       mapper.getBookList().forEach(book -> map.put(book,set.contains(book.getId())));
        return map;
    }

    @Override
    public List<Book> getActiveBook() {
        Set<Integer> set=new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBid()));
        return mapper.getBookList()
                .stream()
                .filter(book-> !set.contains(book))
                .toList();
    }

    @Override
    public void addBorrow(int student, int book) {
        mapper.addBorrow(student,book);
    }

    @Override
    public void returnBorrow(int id) {
        mapper.deleteBorrow(id);
    }

    @Override
    public void deleteBook(int id) {
        mapper.deleteBook(id);
    }

    @Override
    public void addBook(String title, String desc, double price) {
        mapper.addBook(title, desc, price);
    }


}
