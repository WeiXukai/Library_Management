/**
 * ClassName: BookMapper
 * Package: com.example.springboot.mapper
 * Description: 图书表的数据库操作接口，继承 MyBatis-Plus 提供的 BaseMapper。
 *
 * @Author WXK
 * @Create 2025/4/9 16:35
 * @Version 1.0
 */

package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图书数据访问层接口。
 * 继承 BaseMapper<Book> 后自动拥有：
 * - 插入：insert
 * - 删除：deleteById
 * - 更新：updateById
 * - 查询：selectById / selectList / 分页查询 等等
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    // 若有自定义 SQL，可在这里添加 @Select / @Update 等注解方法
}
