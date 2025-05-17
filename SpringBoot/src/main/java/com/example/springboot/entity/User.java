package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;

/**
 * ClassName:User
 * Package: com.example.springboot.entity
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/9 15:45
 * @Version 1.0
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer role;
    private Integer status;
    private Timestamp createTime;
}
