package com.pllug.course.ivankiv.courseproject.data.source.comments;

import com.pllug.course.ivankiv.courseproject.data.source.interfaces.CommentsDataSource;

/**
 * Created by iw97d on 01.02.2018.
 */

public class CommentsRepository implements CommentsDataSource {
    private RetrofitCommentsDataSource retrofitCommentsDataSource;
    private static CommentsRepository instance;

    public CommentsRepository () {
        retrofitCommentsDataSource = new RetrofitCommentsDataSource();
    }

    public static CommentsRepository getInstance() {
        if (instance == null) {
            instance = new CommentsRepository();
        }
        return instance;
    }
    @Override
    public void getComments(int postId, LoadedCommentsCallback callback) {
        retrofitCommentsDataSource.getComments(postId, callback);
    }
}
