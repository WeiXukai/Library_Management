package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Book;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.service.BorrowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private BookMapper bookMapper;

    @Override
    public Result<?> borrowBook(Long userId, Long bookId) {
        // 检查图书是否存在
        Book book = bookMapper.selectById(bookId);
        if (book == null) return Result.error("-1", "图书不存在");
        // 检查是否已借出（可根据业务需求扩展）
        QueryWrapper<Borrow> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId).eq("status", 0);
        if (borrowMapper.selectCount(wrapper) > 0) {
            return Result.error("-1", "该图书已被借出");
        }
        // 借书
        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setBookId(bookId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        borrow.setBorrowTime(now);
        // 设置应还时间为借书时间+1个月
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(now);
        cal.add(java.util.Calendar.MONTH, 1);
        borrow.setDueTime(new Timestamp(cal.getTimeInMillis()));
        borrow.setStatus(0); // 0=借出
        borrowMapper.insert(borrow);
        // 更新图书借阅次数
        book.setBorrowCount(book.getBorrowCount() == null ? 1 : book.getBorrowCount() + 1);
        bookMapper.updateById(book);
        return Result.success();
    }

    @Override
    public Result<?> returnBook(Long userId, Long bookId) {
        QueryWrapper<Borrow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("book_id", bookId).eq("status", 0);
        Borrow borrow = borrowMapper.selectOne(wrapper);
        if (borrow == null) return Result.error("-1", "未找到借阅记录");
        borrow.setReturnTime(new Timestamp(System.currentTimeMillis()));
        borrow.setStatus(1); // 1=归还
        borrowMapper.updateById(borrow);
        return Result.success();
    }

    @Override
    public Result<?> myBorrowHistory(Long userId, String isbn, String title, Boolean onlyUnreturned) {
        QueryWrapper<Borrow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (onlyUnreturned != null && onlyUnreturned) {
            wrapper.eq("status", 0);
        }
        List<Long> bookIdList = null;
        if (title != null && !title.isEmpty()) {
            List<Book> books = bookMapper.selectList(Wrappers.<Book>lambdaQuery().like(Book::getTitle, title));
            bookIdList = new java.util.ArrayList<>();
            for (Book b : books) bookIdList.add(b.getId());
            if (bookIdList.isEmpty()) {
                return Result.success(new java.util.ArrayList<>());
            }
        }
        if (isbn != null && !isbn.isEmpty()) {
            Book book = bookMapper.selectOne(Wrappers.<Book>lambdaQuery().eq(Book::getIsbn, isbn));
            if (book != null) {
                if (bookIdList == null) bookIdList = new java.util.ArrayList<>();
                bookIdList.add(book.getId());
            } else {
                return Result.success(new java.util.ArrayList<>());
            }
        }
        if (bookIdList != null) {
            wrapper.in("book_id", bookIdList);
        }
        wrapper.orderByAsc("status").orderByDesc("borrow_time");
        List<Borrow> list = borrowMapper.selectList(wrapper);
        List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
        for (Borrow b : list) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", b.getId());
            map.put("userId", b.getUserId());
            map.put("bookId", b.getBookId());
            map.put("borrowTime", b.getBorrowTime());
            map.put("dueTime", b.getDueTime());
            map.put("returnTime", b.getReturnTime());
            map.put("status", b.getStatus());
            Book book = bookMapper.selectById(b.getBookId());
            map.put("isbn", book != null ? book.getIsbn() : "");
            map.put("title", book != null ? book.getTitle() : "");
            result.add(map);
        }
        return Result.success(result);
    }

    @Override
    public Result<?> allBorrowRecords(Integer pageNum, Integer pageSize, String searchUser, String searchBook, String searchTitle, Boolean onlyUnreturned) {
        QueryWrapper<Borrow> wrapper = new QueryWrapper<>();
        if (searchUser != null && !searchUser.isEmpty()) {
            wrapper.eq("user_id", searchUser);
        }
        if (onlyUnreturned != null && onlyUnreturned) {
            wrapper.eq("status", 0);
        }
        List<Long> bookIdList = null;
        if (searchTitle != null && !searchTitle.isEmpty()) {
            List<Book> books = bookMapper.selectList(Wrappers.<Book>lambdaQuery().like(Book::getTitle, searchTitle));
            bookIdList = new java.util.ArrayList<>();
            for (Book b : books) bookIdList.add(b.getId());
            if (bookIdList.isEmpty()) {
                Page<java.util.Map<String, Object>> resultPage = new Page<>();
                resultPage.setCurrent(pageNum);
                resultPage.setSize(pageSize);
                resultPage.setTotal(0);
                resultPage.setRecords(new java.util.ArrayList<>());
                return Result.success(resultPage);
            }
        }
        if (searchBook != null && !searchBook.isEmpty()) {
            Book book = bookMapper.selectOne(Wrappers.<Book>lambdaQuery().eq(Book::getIsbn, searchBook));
            if (book != null) {
                if (bookIdList == null) bookIdList = new java.util.ArrayList<>();
                bookIdList.add(book.getId());
            } else {
                Page<java.util.Map<String, Object>> resultPage = new Page<>();
                resultPage.setCurrent(pageNum);
                resultPage.setSize(pageSize);
                resultPage.setTotal(0);
                resultPage.setRecords(new java.util.ArrayList<>());
                return Result.success(resultPage);
            }
        }
        if (bookIdList != null) {
            wrapper.in("book_id", bookIdList);
        }
        wrapper.orderByAsc("status").orderByDesc("borrow_time");
        Page<Borrow> page = borrowMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        // 补全isbn和title
        java.util.List<java.util.Map<String, Object>> records = new java.util.ArrayList<>();
        for (Borrow b : page.getRecords()) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", b.getId());
            map.put("userId", b.getUserId());
            map.put("bookId", b.getBookId());
            map.put("borrowTime", b.getBorrowTime());
            map.put("dueTime", b.getDueTime());
            map.put("returnTime", b.getReturnTime());
            map.put("status", b.getStatus());
            Book book = bookMapper.selectById(b.getBookId());
            map.put("isbn", book != null ? book.getIsbn() : "");
            map.put("title", book != null ? book.getTitle() : "");
            records.add(map);
        }
        Page<java.util.Map<String, Object>> resultPage = new Page<>();
        resultPage.setCurrent(page.getCurrent());
        resultPage.setSize(page.getSize());
        resultPage.setTotal(page.getTotal());
        resultPage.setRecords(records);
        return Result.success(resultPage);
    }
} 