/**
 * ClassName: LendRecord
 * Package: com.example.springboot.entity
 * Description: 图书借还记录实体类，映射 lend_record 数据表。
 *
 * @Author WXK
 * @Create 2025/4/9 17:25
 * @Version 1.0
 */

package com.example.springboot.entity;

import java.sql.Timestamp;

public class Borrow {
    private Long id;
    private Long userId;
    private Long bookId;
    private Timestamp borrowTime;
    private Timestamp dueTime;
    private Timestamp returnTime;
    private Integer status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public Timestamp getBorrowTime() { return borrowTime; }
    public void setBorrowTime(Timestamp borrowTime) { this.borrowTime = borrowTime; }

    public Timestamp getDueTime() { return dueTime; }
    public void setDueTime(Timestamp dueTime) { this.dueTime = dueTime; }

    public Timestamp getReturnTime() { return returnTime; }
    public void setReturnTime(Timestamp returnTime) { this.returnTime = returnTime; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
