package com.patna.multhithreading.threadpool;

import androidx.annotation.NonNull;

import java.util.Date;

public class Friend implements Activity{
    Date createdAt;
    Friend(Date date){
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
