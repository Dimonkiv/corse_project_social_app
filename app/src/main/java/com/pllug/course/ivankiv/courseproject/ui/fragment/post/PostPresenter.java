package com.pllug.course.ivankiv.courseproject.ui.fragment.post;

import com.pllug.course.ivankiv.courseproject.data.model.Post;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.PostDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.posts.PostsRepository;
import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public class PostPresenter implements PostContract.Presenter {
    private PostsRepository postsRepository;
    private PostContract.View view;

    public PostPresenter(PostsRepository postsRepository, PostContract.View view) {
        this.postsRepository = postsRepository;
        this.view = view;
    }
    @Override
    public void onGetPosts() {
        int userId = view.getUserId();
        view.showProgress();
        postsRepository.getPosts(userId, new PostDataSource.LoadPostsCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
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
