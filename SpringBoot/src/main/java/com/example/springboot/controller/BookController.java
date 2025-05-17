/**
 * ClassName: BookController
 * Package: com.example.springboot.controller
 * Description: 图书控制器，处理图书信息的新增、修改、删除、分页查询等操作。
 *
 * @Author WXK
 * @Create 2025/4/9 16:50
 * @Version 1.0
 */

package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Book;
import com.example.springboot.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图书控制器，处理图书的增删改查与借阅前校验逻辑。
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        return bookService.delete(id);
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Long> ids) {
        return bookService.deleteBatch(ids);
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                              @RequestParam(defaultValue = "") String search2,
                              @RequestParam(defaultValue = "") String search3) {
        return bookService.findPage(pageNum, pageSize, search1, search2, search3);
    }
}