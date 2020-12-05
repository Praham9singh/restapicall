package com.example.apicall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewfinal = findViewById(R.id.textviews);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Gorest gorest = retrofit.create(Gorest.class);
        Call<List<Post>> call = gorest.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful())
                {
                    textViewfinal.setText("code: "+response.code() );
                    return;
                }
                List<Post> users = response.body();
                for (Post post: users)
                {
                    String content = " ";
                    content +="username:"+post.getUsername()+"\n";
                    content +="email:"+post.getEmail()+"\n";
                    content +="gender:"+post.getGender()+"\n";
                    content += "status:"+post.getStatus()+"\n\n";
                    textViewfinal.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewfinal.setText(t.getMessage());

            }
        });
    }
}