package com.pllug.course.ivankiv.courseproject.data.source.interfaces;



import com.pllug.course.ivankiv.courseproject.data.model.User;

import java.util.List;

/**
 * Created by iw97d on 30.01.2018.
 */

public interface UserDataSource {
    interface LoadUserCallback {
        void onUserLoaded(List<User> users);

        void onFailure();
    }


    void getUser(int userId, LoadUserCallback callback);
    void getUsers(LoadUserCallback callback);
}
