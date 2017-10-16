package ru.myaround.egorshashkov.around.services;

import ru.myaround.egorshashkov.around.services.methods.results.UserResults;

/**
 * Created by egorshashkov on 17/04/2017.
 */

public interface UserServiceInterface {
    void getMyUserInfo(UserResults result);
    void getMySubscribers(int userId,  String type, String page, UserResults results);
    void getUserInfo(int userId, UserResults result);
    void getMyReplies(int userId, String page, UserResults results);

}
