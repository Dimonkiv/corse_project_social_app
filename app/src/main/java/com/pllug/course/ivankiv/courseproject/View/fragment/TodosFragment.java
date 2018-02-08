package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.Model.data.model.TodoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.TodosRepository;
import com.pllug.course.ivankiv.courseproject.Presenter.TodosPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Interface.TodosContract;
import com.pllug.course.ivankiv.courseproject.View.adapter.TodoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public class TodosFragment extends Fragment implements TodosContract.View {
    private View root;
    private int userId;
    private ListView listView;

    private TodosContract.Presenter presenter;
    private TodosRepository todosRepository;
    private TodoAdapter todoAdapter;
    private List<TodoModel> todosList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.todos_fragment, container, false);
        initView();
        getUserIdFromPersonActivity();
        initAdapter();
        initData();
        initPresenter();
        presenter.onGetTodos();
        return root;
    }

    private void initView() {
        listView = root.findViewById(R.id.todos_list_view);
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
        presenter = new TodosPresenter(todosRepository, this);
    }

    @Override
    public void setTodos(List<TodoModel> todos) {
        todosList.addAll(todos);
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public int getUserId() {
        return userId;
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
}
