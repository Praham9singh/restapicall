package com.example.apicall;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Gorest {
    @GET("users")
    Call<List<Post>> getPosts();
}
