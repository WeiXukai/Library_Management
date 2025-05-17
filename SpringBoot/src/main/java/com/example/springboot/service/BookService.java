package com.example.springboot.service;

/**
 * ClassName:BookService
 * Package: com.example.springboot.service
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 9:46
 * @Version 1.0
 */



import com.example.springboot.common.Result;
import com.example.springboot.entity.Book;

import java.util.List;

public interface BookService {

    Result<?> save(Book book);

    Result<?> update(Book book);

    Result<?> delete(Long id);

    Result<?> deleteBatch(List<Long> ids);

    Result<?> findPage(Integer pageNum, Integer pageSize, String search1, String search2, String search3);
}