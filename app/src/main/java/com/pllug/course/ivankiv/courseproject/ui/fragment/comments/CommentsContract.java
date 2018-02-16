package com.pllug.course.ivankiv.courseproject.ui.fragment.comments;


import com.pllug.course.ivankiv.courseproject.data.model.Comment;

import java.util.List;

/**
 * Created by iw97d on 01.02.2018.
 */

public interface CommentsContract {
    interface View {
        void setComments(List<Comment> comments);

        void showProgress();

        void hideProgress();

        int getPostId();
    }

    interface Presenter {
        void onGetComments();
    }
}
