package com.pllug.course.ivankiv.courseproject.Presenter;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PostsRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.PostDataSource;
import com.pllug.course.ivankiv.courseproject.View.Interface.PostContract;

import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public class PostsPresenter implements PostContract.Presenter {
    private PostsRepository postsRepository;
    private PostContract.View view;

    public PostsPresenter (PostsRepository postsRepository, PostContract.View view) {
        this.postsRepository = postsRepository;
        this.view = view;
    }
    @Override
    public void onGetPosts() {
        int userId = view.getUserId();
        view.showProgress();
        postsRepository.getPosts(userId, new PostDataSource.LoadPostsCallback() {
            @Override
            public void onPostsLoaded(List<PostModel> posts) {
                view.hideProgress();
                view.setPosts(posts);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
            }
        });

    }
}
