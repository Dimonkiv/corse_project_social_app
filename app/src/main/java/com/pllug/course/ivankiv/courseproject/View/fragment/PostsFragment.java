package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.User;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PostsRepository;
import com.pllug.course.ivankiv.courseproject.Presenter.PostsPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Interface.PostContract;
import com.pllug.course.ivankiv.courseproject.View.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public class PostsFragment extends Fragment implements PostContract.View ,
                                       PostAdapter.PostAdapterEventListener{

    private View root;
    private PostsRepository postsRepository;
    private PostContract.Presenter presenter;
    private ListView listView;
    private String name;
    private String imageUrl;
    private int userId;

    private List<PostModel> postList;
    private PostAdapter postAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.posts_fragment, container, false);

        initView();
        initData();
        initPresenter();
        getDataFromPersonActivity();
        initAdapter();
        presenter.onGetPosts();

        return root;
    }

    private void initView() {
        listView = root.findViewById(R.id.listViewForPost);
    }

    private void initData() {
        postsRepository = PostsRepository.getInstance();
    }

    private void initPresenter() {
        presenter = new PostsPresenter(postsRepository, this);
    }

    private void initAdapter() {
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(getActivity(), postList, name, imageUrl);
        postAdapter.addListener(this);
        listView.setAdapter(postAdapter);

    }

    private void getDataFromPersonActivity() {
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId");
        name = bundle.getString("name");
        imageUrl = bundle.getString("url");
    }


    @Override
    public void setPosts(List<PostModel> posts) {
        for (PostModel post : posts) {
            Log.d("postsFragment", "title - " + post.getTitle());
        }
        postList.addAll(posts);
        postAdapter.notifyDataSetChanged();
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
    public int getUserId() {
        return userId;
    }

    @Override
    public void openComments(int position) {
        long postId = ( postAdapter.getItem(position)).getId();
        CommentsFragment commentsFragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("post_id", (int)postId);
        commentsFragment.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frame_for_person_fragment, commentsFragment)
                .commit();
        Toast.makeText(getActivity(), "Post - " + postId, Toast.LENGTH_SHORT).show();
    }
}
