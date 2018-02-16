package com.pllug.course.ivankiv.courseproject.ui.fragment.todo;

import com.pllug.course.ivankiv.courseproject.data.model.Todo;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.TodosDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.todos.TodosRepository;

import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public class TodoPresenter implements TodoContract.Presenter {
    private TodosRepository todosRepository;
    private TodoContract.View view;

    public TodoPresenter(TodosRepository todosRepository, TodoContract.View view) {
        this.todosRepository = todosRepository;
        this.view = view;
    }

    @Override
    public void onGetTodos() {
        int userId = view.getUserId();
        view.showProgress();
        todosRepository.getTodos(userId, new TodosDataSource.LoadTodosCallback() {
            @Override
            public void onTodosLoaded(List<Todo> todos) {
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
