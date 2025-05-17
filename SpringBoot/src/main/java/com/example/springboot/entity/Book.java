/**
 * ClassName: Book
 * Package: com.example.springboot.entity
 * Description: 图书实体类，对应数据库中的 book 表。
 *
 * @Author WXK
 * @Create 2025/4/9 16:30
 * @Version 1.0
 */

package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 图书实体类，映射至数据库中的 book 表。
 * 包含图书编号、书名、作者、出版社、价格、借阅次数等字段。
 */
@Data
@TableName("book") // 指定对应的数据库表名
public class Book {

    /**
     * 主键 ID，自增策略
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 图书 ISBN 编号（唯一）
     */
    private String isbn;

    /**
     * 图书名称
     */
    private String title;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 出版社名称
     */
    private String publisher;

    /**
     * 图书出版时间（日期格式化）
     */
    private Date publishDate;

    /**
     * 图书类别
     */
    private String category;

    /**
     * 图书封面 URL
     */
    private String coverUrl;

    /**
     * 图书描述
     */
    private String description;

    /**
     * 图书总借阅次数
     */
    private Integer borrowCount;

    /**
     * 图书评分次数
     */
    private Integer ratingCount;

    /**
     * 图书评分总和
     */
    private Integer ratingSum;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 推荐指数（协同过滤时为兴趣分，热门推荐时为借阅量）
     * 非数据库字段，仅用于前端展示
     */
    @TableField(exist = false)
    private Double recommendIndex;
}
