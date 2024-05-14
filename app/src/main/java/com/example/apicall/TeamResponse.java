// TeamResponse.Java: This class represents the response we get from the API, containing a list of teams.
package com.example.apicall;

import java.util.List;

public class TeamResponse {
    private List<Team> teams; // This list holds the teams received from the API.

    public List<Team> getTeams() {
        return teams; // This method returns the list of teams.
    }
}
