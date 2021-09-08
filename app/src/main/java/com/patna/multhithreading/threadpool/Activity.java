package com.patna.multhithreading.threadpool;

import java.util.Date;

public interface Activity {
    Date getCreatedAt();

    @Override
    public String toString();
}
