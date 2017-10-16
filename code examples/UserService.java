package ru.myaround.egorshashkov.around.services;

import android.util.Log;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.models.Reply;
import ru.myaround.egorshashkov.around.models.User;
import ru.myaround.egorshashkov.around.services.methods.UserMethods;
import ru.myaround.egorshashkov.around.services.methods.results.UserResults;
import ru.myaround.egorshashkov.around.storage.DataStorageInterface;

import static android.content.ContentValues.TAG;

/**
 * Created by egorshashkov on 17/04/2017.
 */

public class UserService implements UserServiceInterface {
    UserMethods user;
    @Inject
    DataStorageInterface dataStorage;
    public UserService(MainActivity activity) {
        user = UserMethods.retrofit.create(UserMethods.class);
        activity.getMainActivityComponent().inject(this);
    }

    public void getMyUserInfo(UserResults result){
        String token = dataStorage.getToken();
        Call<User> call = user.getMyUserWithData(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: ");
                result.performWithUsers(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void getUserInfo(int userId, UserResults result){
        String token = dataStorage.getToken();
        Call<User> call = user.getUserWithData(token,userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @Override
    public void getMyReplies(int userId, String page, UserResults results) {
        String token = dataStorage.getToken();
        Call<Reply[]> call = user.getReplies(token, userId, page);
        call.enqueue(new Callback<Reply[]>() {
            @Override
            public void onResponse(Call<Reply[]> call, Response<Reply[]> response) {
                results.performWithReplies(response.body());
            }

            @Override
            public void onFailure(Call<Reply[]> call, Throwable t) {

            }
        });
    }


    @Override
    public void getMySubscribers(int userId,  String type, String page, UserResults results){
        String token = dataStorage.getToken();
        Call<User[]> call = user.getSubs(token, userId, type, page);
        call.enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                results.performWithUsers(response.body());
            }

            @Override
            public void onFailure(Call<User[]> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }



}
