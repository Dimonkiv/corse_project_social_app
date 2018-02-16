package com.pllug.course.ivankiv.courseproject.ui.fragment.comments;

import com.pllug.course.ivankiv.courseproject.data.model.Comment;
import com.pllug.course.ivankiv.courseproject.data.source.comments.CommentsRepository;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.CommentsDataSource;

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
            public void onCommentsLoaded(List<Comment> comments) {
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
