package com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces;

import com.pllug.course.ivankiv.courseproject.Model.data.model.CommentModel;

import java.util.List;

/**
 * Created by iw97d on 01.02.2018.
 */

public interface CommentsDataSource {
    interface LoadedCommentsCallback {

        void onCommentsLoaded(List<CommentModel> comments);

        void onFailure();
    }

    void getComments(int postId, LoadedCommentsCallback callback);
}
