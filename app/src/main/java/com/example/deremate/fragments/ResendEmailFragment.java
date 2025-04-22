package com.example.deremate.fragments;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deremate.R;
import com.example.deremate.activities.login.LogInActivity;
import com.example.deremate.activities.menu.MenuActivity;
import com.example.deremate.data.api.UserApi;
import com.example.deremate.data.api.model.TokenModel;
import com.example.deremate.data.api.model.UserModel;
import com.example.deremate.data.repository.token.TokenRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ResendEmailFragment extends Fragment {
    @Inject
    public UserApi userApi;
    @Inject
    public TokenRepository tokenRepository;

    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resend_email, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        String savedToken = tokenRepository.getToken();
        TokenModel tokenModel = new TokenModel(savedToken);

        TextView displayEmail = view.findViewById(R.id.etConfirmacion);
        Button resendButton = view.findViewById(R.id.btnResend);

        userApi.checkUserToken(tokenModel).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful() && response.body() != null){
                    UserModel userModel = response.body();
                    displayEmail.setText("Usted no esta verificado todavia, reenvie el correo de verificacion para que pueda verificarse, este sera enviado a " + userModel.getEmail());

                    resendButton.setOnClickListener(v -> {
                        // Agregar logica de llamado de API para reenviar el email.
                        getParentFragmentManager().popBackStack();
                    });

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


        return view;
    }



}