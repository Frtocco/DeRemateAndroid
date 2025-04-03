package com.example.deremate.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deremate.R;
import com.example.deremate.activities.forgotpassword.ForgotPasswordActivity;
import com.example.deremate.activities.register.UserRegisterActivity;
import com.example.deremate.data.api.UserApi;
import com.example.deremate.data.api.model.UserModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class LogInActivity extends AppCompatActivity {
    @Inject
    UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_log_in);
        Button bLogIn = findViewById(R.id.bLogIn);
        EditText usernameText = findViewById(R.id.etUsername);
        EditText passwordText = findViewById(R.id.etPassword);
        TextView forgotPassword = findViewById(R.id.tvForgotPassword);
        TextView register = findViewById(R.id.tvRegister);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(LogInActivity.this, UserRegisterActivity.class);
            startActivity(intent);
        });

        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        bLogIn.setOnClickListener(v -> {
            // Log in, obtiene las credenciales al tocar el boton

            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();

            if((username.isEmpty() || password.isEmpty())){
                new AlertDialog.Builder(this)
                        .setTitle("Atención")
                        .setMessage("Ingrese un usuario y contraseña.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }else{
                userApi.getUsers().enqueue(new Callback<List<UserModel>>() {
                    @Override
                    public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                        if(!response.isSuccessful()){
                            System.out.println("Codigo: " + response.code());
                            return;
                        }
                        List<UserModel> users = response.body();
                        System.out.println(users.get(0));
                    }

                    @Override
                    public void onFailure(Call<List<UserModel>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }

        });


    }
}