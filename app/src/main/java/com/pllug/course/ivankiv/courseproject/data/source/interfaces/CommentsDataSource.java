package com.pllug.course.ivankiv.courseproject.data.source.interfaces;

import com.pllug.course.ivankiv.courseproject.data.model.Comment;

import java.util.List;

/**
 * Created by iw97d on 01.02.2018.
 */

public interface CommentsDataSource {
    interface LoadedCommentsCallback {

        void onCommentsLoaded(List<Comment> comments);

        void onFailure();
    }

    void getComments(int postId, LoadedCommentsCallback callback);
}
