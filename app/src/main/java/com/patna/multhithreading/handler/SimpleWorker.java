package com.patna.multhithreading.handler;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread{
    private static final String TAG = "SimpleWork";
    private AtomicBoolean alive = new AtomicBoolean(true);

    private ConcurrentLinkedQueue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    public SimpleWorker(){
        super(TAG);
        start();
    }

    @Override
    public void run() {
        while (alive.get()){
            Runnable task = taskQueue.poll();
            if (task!=null){
                task.run();
            }

            Log.i(TAG,"Simple work terminated");
        }
    }

    public SimpleWorker execute(Runnable task){
        taskQueue.add(task);
        return this;
    }
    public void quit(){
        alive.set(false);
    }
}
