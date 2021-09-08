package com.patna.multhithreading.threadpool;

import androidx.annotation.NonNull;

import java.util.Date;

public class Post implements Activity{
    Date createdAt;
    Post(Date date){
        this.createdAt = date;
    }
    @Override
    public Date getCreatedAt() {
        return createdAt;
    }
    @NonNull
    @Override
    public String toString() {
        return "Comment { createdAt "+createdAt+"}";
    }
}
