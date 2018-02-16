package com.pllug.course.ivankiv.courseproject.data.source.api;




import com.pllug.course.ivankiv.courseproject.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 30.01.2018.
 */

public interface UserApi {
    @GET("/users/")
    Call<List<User>> loadUser(@Query("id") int userId);
    @GET("/users/")
    Call<List<User>> loadUsers();
}
