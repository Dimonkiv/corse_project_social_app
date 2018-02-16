package com.pllug.course.ivankiv.courseproject.ui.fragment.post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.data.model.Post;
import com.pllug.course.ivankiv.courseproject.data.source.posts.PostsRepository;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.ui.adapter.PostAdapter;
import com.pllug.course.ivankiv.courseproject.ui.fragment.BaseFragment;
import com.pllug.course.ivankiv.courseproject.ui.fragment.comments.CommentsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public class PostFragment extends Fragment implements PostContract.View ,
                                       PostAdapter.PostAdapterEventListener{

    private View root;
    private PostsRepository postsRepository;
    private PostContract.Presenter presenter;
    private ListView listView;
    private String name;
    private String imageUrl;
    private int userId;

    private List<Post> postList;
    private PostAdapter postAdapter;
    private BaseFragment baseFragment;
    private TextView progressText;
    private LinearLayout progressConteiner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.posts_fragment, container, false);

        initView();
        initBaseFragment();
        initData();
        initPresenter();
        getDataFromPersonActivity();
        initAdapter();
        presenter.onGetPosts();

        return root;
    }

    private void initView() {
        listView = root.findViewById(R.id.listViewForPost);
        progressText = root.findViewById(R.id.progress_bar_text);
        progressConteiner = root.findViewById(R.id.container_progress_bar);
    }

    private void initBaseFragment() {
        baseFragment = new BaseFragment(progressText, progressConteiner);
    }

    private void initData() {
        postsRepository = PostsRepository.getInstance();
    }

    private void initPresenter() {
        presenter = new PostPresenter(postsRepository, this);
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
    public void setPosts(List<Post> posts) {
        postList.addAll(posts);
        postAdapter.notifyDataSetChanged();
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
