package com.pllug.course.ivankiv.courseproject.Model.data.source;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.UserDataSource;

/**
 * Created by iw97d on 30.01.2018.
 */

public class UserRepository implements UserDataSource {

    private static UserRepository instance;
    private RetrofitUserDataSource retrofitUserDataSource;

    public UserRepository() {
        retrofitUserDataSource = new RetrofitUserDataSource();
    }

    public static  UserRepository getInstance() {
        if(instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    @Override
    public void getUser(int userId, LoadUserCallback callback) {
        retrofitUserDataSource.getUser(userId, callback);
        Log.d("myLog", "UserRepository зайшло у getUser");
    }

    @Override
    public void getUsers(LoadUserCallback callback) {
        retrofitUserDataSource.getUsers(callback);
    }
}
