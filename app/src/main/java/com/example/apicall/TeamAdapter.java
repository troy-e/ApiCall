// TeamAdapter.Java: This class helps us connect the list of teams to the RecyclerView for display.
package com.example.apicall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private List<Team> teams; // This list holds the teams we want to display.

    public TeamAdapter(List<Team> teams) {
        this.teams = teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method creates a new ViewHolder instance for each item in the RecyclerView.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        // This method binds the data to the ViewHolder, setting the team name and badge image.
        Team team = teams.get(position);
        holder.teamName.setText(team.getStrTeam());
        Picasso.get().load(team.getStrTeamBadge()).into(holder.teamBadge);
        // You can bind other data here as needed.
    }

    @Override
    public int getItemCount() {
        return teams.size(); // This method returns the total number of items in the list.
    }

    static class TeamViewHolder extends RecyclerView.ViewHolder {
        // This class represents a single item view in the RecyclerView.
        TextView teamName;
        ImageView teamBadge;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.tvTeamName); // We find and assign the TextView for the team name.
            teamBadge = itemView.findViewById(R.id.ivTeamBadge); // We find and assign the ImageView for the team badge.
            // You can initialize other views here if needed.
        }
    }
}
