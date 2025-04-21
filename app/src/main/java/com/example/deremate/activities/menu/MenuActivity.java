package com.example.deremate.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import com.example.deremate.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deremate.R;
import com.example.deremate.activities.historyOrders.HistoryOrdersActivity;
import com.example.deremate.activities.login.LogInActivity;
import com.example.deremate.activities.pendingOrders.pendingOrders;
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
public class MenuActivity extends AppCompatActivity {
    @Inject
    TokenRepository tokenRepository;
    @Inject
    UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        Button logoutButton = findViewById(R.id.logOutButton);
        Button pendingOrdersButton = findViewById(R.id.pendingOrdersButton);
        Button historyOrdersButton = findViewById(R.id.historyOrdersButton);
        TextView tvUsername = findViewById(R.id.tvUsername);


        String savedToken = tokenRepository.getToken();
        TokenModel tokenToVerify = new TokenModel(savedToken);



        userApi.checkUserToken(tokenToVerify).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    UserModel userModel = response.body();
                    tvUsername.setText("Hola denuevo, " + userModel.getUsername());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pendingOrdersButton.setOnClickListener(v->{
            Intent intent = new Intent(MenuActivity.this, pendingOrders.class);
            startActivity(intent);
            finish();
        });

        historyOrdersButton.setOnClickListener(v->{
            Intent intent = new Intent(MenuActivity.this, HistoryOrdersActivity.class);
            startActivity(intent);
            finish();
        });



        logoutButton.setOnClickListener(v -> {
            tokenRepository.clearToken();
            Intent intent = new Intent(MenuActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });

    }
}