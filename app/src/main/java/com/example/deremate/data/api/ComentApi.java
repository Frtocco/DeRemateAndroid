package com.example.deremate.data.api;

import com.example.deremate.data.api.model.ComentModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ComentApi {

    @GET("coments/{riderId}")
    Call<ComentModel> getComent(@Path("riderId") String orderId);
}
