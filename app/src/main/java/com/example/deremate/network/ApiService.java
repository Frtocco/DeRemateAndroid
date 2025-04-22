package com.example.deremate.network;

import com.example.deremate.models.EmailRequest;
import com.example.deremate.models.ResponseMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("users/forgot-password") // Asegúrate de que esta URL coincida con la del backend
    Call<ResponseMessage> sendRecoveryEmail(@Body EmailRequest emailRequest);

}
