package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Book;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName:BookServiceImpl
 * Package: com.example.springboot.service.impl
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 9:48
 * @Version 1.0
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    /**
     * 添加图书信息，并校验ISBN是否重复
     */
    @Override
    public Result<?> save(Book book) {
        LambdaQueryWrapper<Book> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Book::getIsbn, book.getIsbn());
        if (bookMapper.selectOne(wrapper) != null) {
            return Result.error("-1", "图书编号已存在!");
        }
        bookMapper.insert(book);
        return Result.success();
    }

    /**
     * 更新图书信息，排除本身ISBN冲突
     */
    @Override
    public Result<?> update(Book book) {
        LambdaQueryWrapper<Book> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Book::getIsbn, book.getIsbn()).ne(Book::getId, book.getId());
        if (bookMapper.selectOne(wrapper) != null) {
            return Result.error("-1", "图书编号已存在!");
        }
        bookMapper.updateById(book);
        return Result.success();
    }

    /**
     * 删除单本图书，先判断是否在借中
     */
    @Override
    @Transactional
    public Result<?> delete(Long id) {
        Book book = bookMapper.selectById(id);
        if (book == null) {
            return Result.error("-1", "图书不存在");
        }
        bookMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除图书，需校验是否有借阅记录
     */
    @Override
    @Transactional
    public Result<?> deleteBatch(List<Long> ids) {
        bookMapper.deleteBatchIds(ids);
        return Result.success();
    }

    /**
     * 多条件模糊查询分页功能
     */
    @Override
    public Result<?> findPage(Integer pageNum, Integer pageSize, String search1, String search2, String search3) {
        LambdaQueryWrapper<Book> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search1)) {
            wrapper.like(Book::getIsbn, search1);
        }
        if (StringUtils.isNotBlank(search2)) {
            wrapper.like(Book::getTitle, search2);
        }
        if (StringUtils.isNotBlank(search3)) {
            wrapper.like(Book::getAuthor, search3);
        }
        wrapper.orderByDesc(Book::getBorrowCount);
        Page<Book> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(bookPage);
    }
}