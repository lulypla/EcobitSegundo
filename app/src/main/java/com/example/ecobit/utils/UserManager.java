package com.example.ecobit.utils;

import com.example.ecobit.Model.User;

public class UserManager {

    private static User user;

    public static User init(User user) {
        if(UserManager.user == null) UserManager.user = user;
        return user;
    }

    public static User replace(User user) {
        UserManager.user = user;
        return user;
    }

    public static synchronized User get() {
        return UserManager.user;
    }
}
