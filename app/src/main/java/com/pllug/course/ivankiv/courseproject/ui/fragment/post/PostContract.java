package com.pllug.course.ivankiv.courseproject.ui.fragment.post;

import com.pllug.course.ivankiv.courseproject.data.model.Post;

import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public interface PostContract {

    interface View {
        void setPosts(List<Post> posts);

        void showProgress();

        void hideProgress();

        int getUserId();
    }

    interface Presenter {
        void onGetPosts();
    }
}
