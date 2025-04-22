package com.example.deremate.activities.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deremate.R;
import com.example.deremate.activities.login.LogInActivity;
import com.example.deremate.data.api.UserApi;
import com.example.deremate.models.EmailRequest;
import com.example.deremate.models.ResponseMessage;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ForgotPasswordActivity extends AppCompatActivity {
    @Inject
    public UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText emailText = findViewById(R.id.etEmail);
        Button sendEmailButton = findViewById(R.id.bSendEmail);
        TextView backToLogIn = findViewById(R.id.tvToLogIn);

        sendEmailButton.setOnClickListener(v -> {
            // Enviar mail para recuperar contraseña
            String email = emailText.getText().toString();

            if(email.isEmpty()){
                new AlertDialog.Builder(this)
                        .setTitle("Atención")
                        .setMessage("Introduce un email valido")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }else{
                // Enviar email (verificar que exista la cuenta antes)
                sendRecoveryEmail(email);
            }
        });

        backToLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LogInActivity.class);
            startActivity(intent);
        });


        backToLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LogInActivity.class);
            startActivity(intent);
        });

    }
    private void sendRecoveryEmail(String email) {
        EmailRequest emailRequest = new EmailRequest(email);
        userApi.sendRecoveryEmail(emailRequest).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    Toast.makeText(ForgotPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(ForgotPasswordActivity.this, "Error: " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error desconocido", Toast.LENGTH_SHORT).show();
                    }}
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
