package com.example.deremate.data.api;

import com.example.deremate.data.api.model.UserModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface UserApi {
    @GET("users")
    Call<List<UserModel>> getUsers();

    @GET("users/:id")
    Call<UserModel> getUserById();

    @POST("users")
    Call<UserModel> createUser();
}
