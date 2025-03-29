package com.example.deremate.forgotpassword;

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
import com.example.deremate.login.LogInActivity;
import com.example.deremate.register.UserRegisterActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

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
            }
        });

        backToLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LogInActivity.class);
            startActivity(intent);
        });


    }
}