package com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;

import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public interface PostDataSource {
    interface LoadPostsCallback {

        void onPostsLoaded(List<PostModel> posts);

        void onFailure();
    }

    void getPosts(int userId, LoadPostsCallback callback);
}
