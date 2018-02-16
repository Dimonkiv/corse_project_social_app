package com.pllug.course.ivankiv.courseproject.data.source.todos;

import com.pllug.course.ivankiv.courseproject.data.source.interfaces.TodosDataSource;

/**
 * Created by iw97d on 04.02.2018.
 */

public class TodosRepository implements TodosDataSource {
    private RetrofitTodosDataSource todosDataSource;
    private static TodosRepository instance;

    public TodosRepository() {
        todosDataSource = new RetrofitTodosDataSource();
    }

    public static TodosRepository getInstance() {
        if(instance == null) {
            instance = new TodosRepository();
        }
        return instance;
    }
    @Override
    public void getTodos(int userId, LoadTodosCallback callback) {
        todosDataSource.getTodos(userId, callback);
    }
}
