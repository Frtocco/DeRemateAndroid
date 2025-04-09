package com.example.deremate.activities.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deremate.R;
import com.example.deremate.activities.login.LogInActivity;
import com.example.deremate.activities.menu.MenuActivity;
import com.example.deremate.data.api.UserApi;
import com.example.deremate.data.api.model.UserModel;
import com.example.deremate.data.repository.token.TokenRepository;

import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AndroidEntryPoint
public class UserRegisterActivity extends AppCompatActivity {

    @Inject
    UserApi userApi;

    @Inject
    TokenRepository tokenRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView toLogIn = findViewById(R.id.tvToLogIn);
        Button buttonRegister = findViewById(R.id.bRegister);
        EditText usernameText = findViewById(R.id.etUsername);
        EditText passwordText = findViewById(R.id.etPassword);
        EditText emailText = findViewById(R.id.etEmail);


        toLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(UserRegisterActivity.this, LogInActivity.class);
            startActivity(intent);
        });

        buttonRegister.setOnClickListener(v -> {
            // Register de usuario, pasar datos a la API y cargar usuario
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();
            String email = emailText.getText().toString();

            UserModel userModel = new UserModel(username, email, password);

            if(username.length() < 4 || password.length() < 4 || !isValidEmail(emailText)){
                new AlertDialog.Builder(this)
                        .setTitle("Atención")
                        .setMessage("El nombre de usuario y contraseña deben tener mas de 4 caracteres.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }else{
                userApi.createUser(userModel).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if(response.isSuccessful() && response.body() != null ){
                            String token = response.body().getJwt();
                            tokenRepository.saveToken(token);
                            Intent intent = new Intent(UserRegisterActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }else{
                            System.out.println("Error en la respuesta: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }

        });

    }

    public boolean isValidEmail(EditText editText) {
        String email = editText.getText().toString().trim();
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}