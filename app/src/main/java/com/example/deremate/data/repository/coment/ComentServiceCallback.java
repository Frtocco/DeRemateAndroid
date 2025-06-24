package com.example.deremate.data.repository.coment;

import com.example.deremate.Model.Order;
import com.example.deremate.data.api.model.ComentModel;

import java.util.List;

public interface ComentServiceCallback {
    void onSuccess(ComentModel coment);
    void onError(Throwable error);
}