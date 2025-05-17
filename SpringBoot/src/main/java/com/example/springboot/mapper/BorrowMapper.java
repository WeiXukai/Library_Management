/**
 * ClassName: LendRecordMapper
 * Package: com.example.springboot.mapper
 * Description: 借阅记录表（lend_record）的数据库访问接口，继承 MyBatis-Plus 的 BaseMapper。
 *
 * @Author WXK
 * @Create 2025/4/9 17:30
 * @Version 1.0
 */

package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper 接口：负责对 lend_record 表的基本 CRUD 操作。
 * 继承 BaseMapper<LendRecord> 后拥有以下方法：
 * - insert(record)
 * - deleteById(id)
 * - updateById(record)
 * - selectById(id)
 * - selectList(...)
 * - selectPage(...) 等
 */
@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
}
