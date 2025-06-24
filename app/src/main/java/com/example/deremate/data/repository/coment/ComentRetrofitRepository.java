package com.example.deremate.data.repository.coment;

import android.util.Log;

import com.example.deremate.data.api.ComentApi;
import com.example.deremate.data.api.model.ComentModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentRetrofitRepository implements ComentRepository {

    private final ComentApi comentApi;

    @Inject
    public ComentRetrofitRepository(ComentApi comentApi) {
        this.comentApi = comentApi;
    }

    @Override
    public void getCoemnt(String orederId, ComentServiceCallback callback) {
        comentApi.getComent(orederId).enqueue(new Callback<ComentModel>() {
            @Override
            public void onResponse(Call<ComentModel> call, Response<ComentModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Exception("Error fetching comment"));
                }
            }

            @Override
            public void onFailure(Call<ComentModel> call, Throwable t) {
                Log.e("API_ERROR", "Fallo la llamada HTTP: " + t.getMessage(), t);
                callback.onError(t);
            }
        });
    }
}
