package com.example.deremate.data.api;

import com.example.deremate.data.api.model.UserLogIn;
import com.example.deremate.data.api.model.UserModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface UserApi {
    @GET("users")
    Call<List<UserModel>> getUsers();

    @GET("users/:id")
    Call<UserModel> getUserById();

    @POST("users")
    Call<UserModel> createUser(@Body UserModel user);

    @POST("users/login")
    Call<ResponseBody> login(UserLogIn user);
}
