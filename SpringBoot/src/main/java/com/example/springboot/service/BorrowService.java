package com.example.springboot.service;

import com.example.springboot.common.Result;


public interface BorrowService {
    Result<?> borrowBook(Long userId, Long bookId);
    Result<?> returnBook(Long userId, Long bookId);
    Result<?> myBorrowHistory(Long userId, String isbn, String title, Boolean onlyUnreturned);
    Result<?> allBorrowRecords(Integer pageNum, Integer pageSize, String searchUser, String searchBook, String searchTitle, Boolean onlyUnreturned);
} 