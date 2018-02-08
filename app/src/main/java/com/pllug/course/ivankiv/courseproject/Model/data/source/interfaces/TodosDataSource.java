package com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces;

import com.pllug.course.ivankiv.courseproject.Model.data.model.TodoModel;

import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public interface TodosDataSource {
    interface LoadTodosCallback {

        void onTodosLoaded(List<TodoModel> todos);

        void onFailure();
    }

    void getTodos(int userId, LoadTodosCallback callback);
}
