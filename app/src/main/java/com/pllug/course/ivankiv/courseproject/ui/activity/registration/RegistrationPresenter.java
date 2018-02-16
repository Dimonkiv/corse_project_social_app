package com.pllug.course.ivankiv.courseproject.ui.activity.registration;


import com.pllug.course.ivankiv.courseproject.data.model.User;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.UserDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 19.01.2018.
 */

public class RegistrationPresenter implements RegistrationContract.Presenter {
    private final RegistrationContract.View view;
    private UserRepository userRepository;
    private List<User> usersList;

    public RegistrationPresenter(UserRepository userRepository, RegistrationContract.View view) {
        this.view = view;
        this.userRepository = userRepository;
    }

    @Override
    public void onUsersLoaded() {
        usersList = new ArrayList<>();
        userRepository.getUsers(new UserDataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(List<User> users) {
                usersList.addAll(users);
            }
            @Override
            public void onFailure() {

            }
        });
    }


    private int getUserId(String login) {
        int userId = 0;

        for (User user : usersList) {
            String username = user.getUsername();
            //Якщо login співпадає з username у списку users
            //то передаємо userId
            if (login.equals(username)) {
                return user.getId();
            }
        }
        //Інакше повертаємо 0
        return userId;
    }

    @Override
    public void checkSignInData(String login, String password) {
        int id = 0;
        // Якщо пароль і логін однакові, то викликаємо
        //метод для перевірки даного логіна на сервері
        if (login.equals(password)) {
            id = getUserId(login);
        }

        //Якщо знайдено userId != 0
        //то переходимо до профілю,передаюч userId
        if (id != 0) {
            view.showToast("Successful sign in!");
            view.goToProfileActivity(id);
        } else {
            view.showToast("Incorect login or password!");
        }
    }


}
