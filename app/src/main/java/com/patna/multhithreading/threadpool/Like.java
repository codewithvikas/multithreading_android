package com.patna.multhithreading.threadpool;

import androidx.annotation.NonNull;

import java.util.Date;

public class Like implements Activity{

    Date createdAt;
    Like(Date createdAt){
        this.createdAt = createdAt;
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
