package com.example.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;
/**
 * ClassName:UserMapper
 * Package: com.example.springboot.mapper
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/9 15:46
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
