package com.example.deremate.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deremate.R;
import com.example.deremate.login.LogInActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserRegisterActivity extends AppCompatActivity {

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


        toLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(UserRegisterActivity.this, LogInActivity.class);
            startActivity(intent);
        });

        buttonRegister.setOnClickListener(v -> {
            // Register de usuario, pasar datos a la API y cargar usuario
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();

            if(username.length() < 4 || password.length() < 4){
                new AlertDialog.Builder(this)
                        .setTitle("Atención")
                        .setMessage("El nombre de usuario y contraseña deben tener mas de 4 caracteres.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }else{
                // conectar con api y crear usuario
            }

        });

    }

}