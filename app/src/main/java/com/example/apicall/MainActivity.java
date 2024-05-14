// MainActivity.Java: This is the main activity of our app. Think of it as the central hub where everything starts.
package com.example.apicall;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // This is the base URL for the API we're going to call to get our data.
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";

    private RecyclerView recyclerView; // This is where we'll display our list of teams.
    private TeamAdapter adapter; // This is the adapter that helps us connect our list of teams to the RecyclerView.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // This sets the layout of our activity to activity_main.xml.

        recyclerView = findViewById(R.id.rvListFootball); // We find the RecyclerView component in our layout.
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // We set a layout manager to arrange our items in a list.
        adapter = new TeamAdapter(new ArrayList<>()); // We create a new adapter for our RecyclerView and provide an empty list of teams.
        recyclerView.setAdapter(adapter); // We set the adapter to our RecyclerView.

        // This part creates a Retrofit instance to make API calls.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TeamService service = retrofit.create(TeamService.class); // We create a service interface to define our API endpoints.
        Call<TeamResponse> call = service.getTeams(); // We make a call to get the list of teams from the API.
        call.enqueue(new Callback<TeamResponse>() { // We enqueue the call to run asynchronously.
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful()) { // If the response is successful...
                    TeamResponse teamResponse = response.body(); // We get the response body which contains our data.
                    if (teamResponse != null && teamResponse.getTeams() != null) { // If the response body and teams are not null...
                        adapter.setTeams(teamResponse.getTeams()); // We update our adapter with the list of teams we received.
                    }
                } else {
                    // Handle unsuccessful response here
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                // Handle network failures here
            }
        });
    }
}
