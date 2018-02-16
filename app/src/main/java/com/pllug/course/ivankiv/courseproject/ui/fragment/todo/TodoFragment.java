package com.pllug.course.ivankiv.courseproject.ui.fragment.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.data.model.Todo;
import com.pllug.course.ivankiv.courseproject.data.source.todos.TodosRepository;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.ui.adapter.TodoAdapter;
import com.pllug.course.ivankiv.courseproject.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public class TodoFragment extends Fragment implements TodoContract.View {
    private View root;
    private int userId;
    private ListView listView;

    private TodoContract.Presenter presenter;
    private TodosRepository todosRepository;
    private TodoAdapter todoAdapter;
    private List<Todo> todosList;
    private BaseFragment baseFragment;
    private LinearLayout progressConteiner;
    private TextView progressText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.todos_fragment, container, false);

        initView();
        initBaseFragment();
        getUserIdFromPersonActivity();
        initAdapter();
        initData();
        initPresenter();
        presenter.onGetTodos();

        return root;
    }

    private void initView() {
        listView = root.findViewById(R.id.todos_list_view);
        progressText = root.findViewById(R.id.progress_bar_text);
        progressConteiner = root.findViewById(R.id.container_progress_bar);
    }

    private void initBaseFragment() {
        baseFragment = new BaseFragment(progressText, progressConteiner);
    }

    private void getUserIdFromPersonActivity() {
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId");
    }

    private void initAdapter() {
        todosList = new ArrayList<>();

        todoAdapter = new TodoAdapter(getActivity(), todosList);
        listView.setAdapter(todoAdapter);

    }

    private void initData() {
        todosRepository = TodosRepository.getInstance();
    }

    private void initPresenter() {
        presenter = new TodoPresenter(todosRepository, this);
    }

    @Override
    public void setTodos(List<Todo> todos) {
        todosList.addAll(todos);
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public int getUserId() {
        return userId;
    }


    @Override
    public void showProgress() {
        baseFragment.showProgress();
    }

    @Override
    public void hideProgress() {
        baseFragment.hideProgress();
    }
}
