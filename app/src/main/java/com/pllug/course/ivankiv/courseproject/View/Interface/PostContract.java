package com.pllug.course.ivankiv.courseproject.View.Interface;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;

import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public interface PostContract {

    interface View {
        void setPosts(List<PostModel> posts);

        void showProgress();

        void hideProgress();

        int getUserId();
    }

    interface Presenter {
        void onGetPosts();
    }
}
