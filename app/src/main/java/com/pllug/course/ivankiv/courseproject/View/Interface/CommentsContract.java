package com.pllug.course.ivankiv.courseproject.View.Interface;


import com.pllug.course.ivankiv.courseproject.Model.data.model.CommentModel;

import java.util.List;

/**
 * Created by iw97d on 01.02.2018.
 */

public interface CommentsContract {
    interface View {
        void setComments(List<CommentModel> comments);

        void showProgress();

        void hideProgress();

        int getPostId();
    }

    interface Presenter {
        void onGetComments();
    }
}
