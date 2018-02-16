package com.pllug.course.ivankiv.courseproject.data.source.posts;

import com.pllug.course.ivankiv.courseproject.data.source.interfaces.PostDataSource;

/**
 * Created by iw97d on 31.01.2018.
 */

public class PostsRepository implements PostDataSource {

    private static PostsRepository instance;
    private RetrofitPostsDataSource retrofitPostsDataSource;

    public PostsRepository () {
        retrofitPostsDataSource = new RetrofitPostsDataSource();
    }

    public static PostsRepository getInstance() {
        if (instance == null) {
            instance = new PostsRepository();
        }

        return instance;
    }
    @Override
    public void getPosts(int userId, LoadPostsCallback callback) {
        retrofitPostsDataSource.getPosts(userId, callback);
    }
}
