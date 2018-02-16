package com.pllug.course.ivankiv.courseproject.ui.fragment.todo;

import com.pllug.course.ivankiv.courseproject.data.model.Todo;

import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public interface TodoContract {
    interface View{

        void setTodos(List<Todo> todos);

        void showProgress();

        void hideProgress();

        int getUserId();
    }

    interface Presenter {
        void onGetTodos();
    }
}
