package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    @PostMapping("/borrowBook")
    public Result<?> borrowBook(@RequestBody Map<String, Object> params) {
        Long userId = Long.valueOf(params.get("userId").toString());
        Long bookId = Long.valueOf(params.get("bookId").toString());
        return borrowService.borrowBook(userId, bookId);
    }

    @PostMapping("/returnBook")
    public Result<?> returnBook(@RequestBody Map<String, Object> params) {
        Long userId = Long.valueOf(params.get("userId").toString());
        Long bookId = Long.valueOf(params.get("bookId").toString());
        return borrowService.returnBook(userId, bookId);
    }

    @GetMapping("/myBorrowHistory")
    public Result<?> myBorrowHistory(@RequestParam Long userId, @RequestParam(required = false) String isbn, @RequestParam(required = false) String title, @RequestParam(required = false) Boolean onlyUnreturned) {
        return borrowService.myBorrowHistory(userId, isbn, title, onlyUnreturned);
    }

    @GetMapping("/allBorrowRecords")
    public Result<?> allBorrowRecords(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(required = false) String searchUser,
                                      @RequestParam(required = false) String searchBook,
                                      @RequestParam(required = false) String searchTitle,
                                      @RequestParam(required = false) Boolean onlyUnreturned) {
        return borrowService.allBorrowRecords(pageNum, pageSize, searchUser, searchBook, searchTitle, onlyUnreturned);
    }
} 