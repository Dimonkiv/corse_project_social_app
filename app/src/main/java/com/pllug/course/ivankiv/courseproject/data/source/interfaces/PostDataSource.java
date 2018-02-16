package com.pllug.course.ivankiv.courseproject.data.source.interfaces;

import com.pllug.course.ivankiv.courseproject.data.model.Post;

import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public interface PostDataSource {
    interface LoadPostsCallback {

        void onPostsLoaded(List<Post> posts);

        void onFailure();
    }

    void getPosts(int userId, LoadPostsCallback callback);
}
