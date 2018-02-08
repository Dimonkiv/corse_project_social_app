package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.CommentModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.CommentsRepository;
import com.pllug.course.ivankiv.courseproject.Presenter.CommentsPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Interface.CommentsContract;
import com.pllug.course.ivankiv.courseproject.View.adapter.CommentAdapter;

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

    private List<CommentModel> commentList;
    private CommentAdapter commentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.comments_layout, container, false);

        initView();
        initData();
        initPresenter();
        initAdapter();
        presenter.onGetComments();
        return root;
    }

    private void initData() {
        repository = CommentsRepository.getInstance();
    }

    private void initView() {
        listView = root.findViewById(R.id.list_view);
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
    public void setComments(List<CommentModel> comments) {
        commentList.addAll(comments);
        commentAdapter.notifyDataSetChanged();
    }
    private void showProgressLoaderWithBackground(boolean visibility, String text) {
        if (text == null)
            text = "";
        ((TextView)root.findViewById(R.id.progress_bar_text)).setText(text);

        root.findViewById(R.id.container_progress_bar).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgress() {
        showProgressLoaderWithBackground(true, " loading data...");
    }

    @Override
    public void hideProgress() {
        showProgressLoaderWithBackground(false, " loading data...");
    }

    @Override
    public int getPostId() {
        return getPostIdFromPostFragment();
    }
}
