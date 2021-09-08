package com.patna.multhithreading.threadpool;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class RemoteService {

    private static int cores = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService = Executors.newFixedThreadPool(cores+1);

    public void stop(){
        executorService.shutdown();
    }

    public void getRecentActivities(ResultCallBack resultCallBack){
        executorService.execute(()->{
            List<Like> likes = new ArrayList<>();
            List<Comment> comments = new ArrayList<>();
            List<Post> posts = new ArrayList<>();
            List<Friend> friends = new ArrayList<>();

            Future<List<Like>> futureLikes = executorService.submit(getLikes("https://www/dummydata.com/likes"));
            Future<List<Comment>> futureComments =executorService.submit(getComments("https://www/dummydata.com/comments"));
            Future<List<Post>> futurePosts =executorService.submit(getPosts("https://www/dummydata.com/posts"));
            Future<List<Friend>> futureFriends =executorService.submit(getFriends("https://www/dummydata.com/friends"));

            try {
                likes = futureLikes.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            try {
                comments = futureComments.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            try {
                posts = futurePosts.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            try {
                friends = futureFriends.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            List<Activity> activities = new ArrayList<>();
            activities.addAll(likes);
            activities.addAll(comments);
            activities.addAll(posts);
            activities.addAll(friends);

            Collections.sort(activities,
                    (activity1,activity2)-> activity1.getCreatedAt().compareTo(activity2.getCreatedAt()));

            resultCallBack.onResult(activities);
        });

    }
    private Callable<List<Like>> getLikes(String url){
        return ()->{
            Log.i(RemoteService.class.getSimpleName(),"get Likes");
            Thread.sleep(1000);
            return Arrays.asList(new Like(new Date(1631035335723L)),new Like(new Date(1641035335723L)));
        };

    }
    private Callable<List<Comment>> getComments(String url){
        return ()->{
            Log.i(RemoteService.class.getSimpleName(),"get Comments");
            Thread.sleep(1000);
            return Arrays.asList(new Comment(new Date(1633035335723L)),new Comment(new Date(1641135335723L)));
        };

    }
    private Callable<List<Friend>> getFriends(String url){
        return ()->{
            Log.i(RemoteService.class.getSimpleName(),"get Friends");
            Thread.sleep(1000);
            return Arrays.asList(new Friend(new Date(1631335335723L)),new Friend(new Date(1641055335723L)));
        };

    }
    private Callable<List<Post>> getPosts(String url){
        return ()->{
            Log.i(RemoteService.class.getSimpleName(),"get Posts");
            Thread.sleep(1000);
            return Arrays.asList(new Post(new Date(1681035335723L)),new Post(new Date(1649035335723L)));
        };

    }

}
