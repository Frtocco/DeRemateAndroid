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
import com.example.deremate.data.api.model.TokenModel;
import com.example.deremate.data.api.model.UserModel;
import com.example.deremate.data.repository.token.TokenRepository;

import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.ResponseBody;
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
                // Mensaje de error para verificar porque no se pudo crear la cuenta
                new AlertDialog.Builder(this)
                        .setTitle("Atención")
                        .setMessage("El nombre de usuario y contraseña deben tener mas de 4 caracteres.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }else{
                userApi.createUser(userModel).enqueue(new Callback<TokenModel>() {
                    @Override
                    public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                        if(response.isSuccessful() && response.body() != null ){
                            // Al crear un usuario, se guarda un token de persistencia para que sea mantenido
                            TokenModel tokenModel = response.body();
                            System.out.println(tokenModel.getToken());

                            String token = tokenModel.getToken();
                            tokenRepository.saveToken(token);
                            userApi.sendEmailVerification(userModel).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.isSuccessful()){
                                        new AlertDialog.Builder(UserRegisterActivity.this)
                                                .setTitle("Atención")
                                                .setMessage("Enviamos un email a " + email + " para que lo confirme")
                                                .setPositiveButton("OK", (dialog, which) -> {
                                                    dialog.dismiss();

                                                    Intent intent = new Intent(UserRegisterActivity.this, LogInActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                })
                                                .show();
                                    }
                                    System.out.println(response.isSuccessful());
                                    System.out.println(userModel.getEmail());
                                    System.out.println(userModel.getUsername());
                                    System.out.println(userModel.getPassword());
                                }


                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    new AlertDialog.Builder(UserRegisterActivity.this)
                                            .setTitle("Atención")
                                            .setMessage("No pudimos enviar un email a " + email + " verifique que haya sido escrito de manera correcta")
                                            .setPositiveButton("OK", (dialog, which) -> {
                                                dialog.dismiss();
                                            })
                                            .show();
                                }
                            });


                        }else{
                            new AlertDialog.Builder(UserRegisterActivity.this)
                                    .setTitle("Atención")
                                    .setMessage("El email o nombre de usuario ya estan en uso.")
                                    .setPositiveButton("OK", (dialog, which) -> {
                                        dialog.dismiss();
                                    })
                                    .show();
                        }
                    }


                    @Override
                    public void onFailure(Call<TokenModel> call, Throwable t) {
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