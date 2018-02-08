package com.pllug.course.ivankiv.courseproject.View.Interface;

import com.pllug.course.ivankiv.courseproject.Model.data.model.TodoModel;

import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public interface TodosContract {
    interface View{

        void setTodos(List<TodoModel> todos);

        void showProgress();

        void hideProgress();

        int getUserId();
    }

    interface Presenter {
        void onGetTodos();
    }
}
