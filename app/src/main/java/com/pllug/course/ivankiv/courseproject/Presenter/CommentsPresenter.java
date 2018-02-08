package com.pllug.course.ivankiv.courseproject.Presenter;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.model.CommentModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.CommentsRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.CommentsDataSource;
import com.pllug.course.ivankiv.courseproject.View.Interface.CommentsContract;

import java.util.List;

/**
 * Created by iw97d on 01.02.2018.
 */

public class CommentsPresenter implements CommentsContract.Presenter {
    private CommentsRepository commentsRepository;
    private CommentsContract.View view;

    public CommentsPresenter (CommentsRepository commentsRepository, CommentsContract.View view) {
        this.commentsRepository = commentsRepository;
        this.view = view;
    }

    @Override
    public void onGetComments() {
        int postId = view.getPostId();
        view.showProgress();
        commentsRepository.getComments(postId, new CommentsDataSource.LoadedCommentsCallback() {
            @Override
            public void onCommentsLoaded(List<CommentModel> comments) {
                view.hideProgress();
                view.setComments(comments);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
            }
        });

    }
}
