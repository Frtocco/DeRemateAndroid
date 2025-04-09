package com.example.deremate.data.api;

import com.example.deremate.data.api.model.*;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface UserApi {
    @GET("users") // Get a todos los usuarios
    Call<List<UserModel>> getUsers();

    @GET("users/:id") //Get users por id
    Call<UserModel> getUserById();

    @POST("users")
    Call<TokenModel> createUser(@Body UserModel user);

    @POST("users/login") //Login
    Call<TokenModel> login(@Body UserLogIn user);

    @POST("users/jwt") //Chequear usuario con JWT guardado
    Call<UserModel> checkUserToken(@Body TokenModel token);
}
