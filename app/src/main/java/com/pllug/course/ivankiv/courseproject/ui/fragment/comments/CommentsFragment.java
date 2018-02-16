package com.pllug.course.ivankiv.courseproject.ui.fragment.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.data.model.Comment;
import com.pllug.course.ivankiv.courseproject.data.source.comments.CommentsRepository;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.ui.adapter.CommentAdapter;
import com.pllug.course.ivankiv.courseproject.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 01.02.2018.
 */

public class CommentsFragment extends Fragment implements CommentsContract.View{
    View root;
    private ListView listView;
    private CommentsContract.Presenter presenter;
    private CommentsRepository repository;
    private TextView progressText;
    private LinearLayout progressContainer;
    private BaseFragment baseFragment;

    private List<Comment> commentList;
    private CommentAdapter commentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.comments_layout, container, false);

        initView();
        initBaseFragment();
        initData();
        initPresenter();
        initAdapter();
        presenter.onGetComments();
        return root;
    }

    private void initView() {
        listView = root.findViewById(R.id.list_view);
        progressText = root.findViewById(R.id.progress_bar_text);
        progressContainer = root.findViewById(R.id.container_progress_bar);
    }

    private void initBaseFragment() {
        baseFragment = new BaseFragment(progressText, progressContainer);
    }

    private void initData() {
        repository = CommentsRepository.getInstance();
    }

    private void initPresenter() {
        presenter = new CommentsPresenter(repository, this);
    }

    private void initAdapter() {
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(getActivity(), commentList);
        listView.setAdapter(commentAdapter);
    }


    private int getPostIdFromPostFragment() {
        Bundle bundle = getArguments();
        int postId = 0;
        if (bundle != null) {
             postId = bundle.getInt("post_id");
        }
        return postId;
    }

    @Override
    public void setComments(List<Comment> comments) {
        commentList.addAll(comments);
        commentAdapter.notifyDataSetChanged();
    }


    @Override
    public void showProgress() {
        baseFragment.showProgress();
    }

    @Override
    public void hideProgress() {
        baseFragment.hideProgress();
    }

    @Override
    public int getPostId() {
        return getPostIdFromPostFragment();
    }
}
