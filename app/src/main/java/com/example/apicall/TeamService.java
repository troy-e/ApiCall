// TeamService.Java: This interface defines the API endpoint to get the list of teams.
package com.example.apicall;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamService {
        @GET("search_all_teams.php?l=English%20Premier%20League")
        Call<TeamResponse> getTeams(); // This method defines the endpoint to get the list of teams.
}
