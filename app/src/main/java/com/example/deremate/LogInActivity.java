package com.example.deremate;

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

public class LogInActivity extends AppCompatActivity {



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

        });

        forgotPassword.setOnClickListener(v -> {

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
                // Llamada a la API
            }

        });


    }
}