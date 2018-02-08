package com.pllug.course.ivankiv.courseproject.Presenter;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.model.TodoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.TodosRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.TodosDataSource;
import com.pllug.course.ivankiv.courseproject.View.Interface.TodosContract;

import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public class TodosPresenter implements TodosContract.Presenter {
    private TodosRepository todosRepository;
    private TodosContract.View view;

    public TodosPresenter(TodosRepository todosRepository, TodosContract.View view) {
        this.todosRepository = todosRepository;
        this.view = view;
    }

    @Override
    public void onGetTodos() {
        int userId = view.getUserId();
        view.showProgress();
        todosRepository.getTodos(userId, new TodosDataSource.LoadTodosCallback() {
            @Override
            public void onTodosLoaded(List<TodoModel> todos) {
                view.hideProgress();
                view.setTodos(todos);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
            }
        });

    }
}
