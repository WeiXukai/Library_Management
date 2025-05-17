package com.example.springboot.entity;

import java.sql.Timestamp;

public class Rating {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer score;
    private Timestamp rateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Timestamp getRateTime() { return rateTime; }
    public void setRateTime(Timestamp rateTime) { this.rateTime = rateTime; }
} 