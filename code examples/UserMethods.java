package ru.myaround.egorshashkov.around.services.methods;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.myaround.egorshashkov.around.models.Reply;
import ru.myaround.egorshashkov.around.models.User;
import ru.myaround.egorshashkov.around.services.general.ServerConfiguration;

/**
 * Created by egorshashkov on 17/04/2017.
 */

public interface UserMethods {

    static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServerConfiguration.serverAddres)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("get_user_by_id")
    Call<User> getUserWithData(@Query("access_token") String token, @Query("user_id") int userId);

    @GET("userbyusername")
    Call<User> getMyUserWithData(@Query("access_token") String token);

    @GET("userbyusername")
    Call<User> getUserWithUsername(@Query("access_token") String token, @Query("username") String username);

    @GET("getsubs")
    Call<User[]> getSubs(@Query("access_token") String token, @Query("id") int id, @Query("type") String type, @Query("page") String page);

    @GET("replies")
    Call<Reply[]> getReplies(@Query("access_token") String token, @Query("id") int id, @Query("page") String page);

}
