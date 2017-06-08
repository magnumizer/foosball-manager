package sourcepackage;//Magnus Svendsen DAT16i

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Match
{
    SimpleStringProperty date;
    SimpleStringProperty team1Name;
    int team1Score;
    SimpleStringProperty team2Name;
    int team2Score;
    SimpleStringProperty winner;

    Team[] teams = new Team[2];

    public static ArrayList<Match> allMatches = new ArrayList<>();

    public Match(String date, Team team1, int team1Score, Team team2, int team2Score)
    {
        this.date = new SimpleStringProperty(date);
        this.teams[0] = team1;
        this.teams[1] = team2;
        this.team1Name = new SimpleStringProperty(team1.getTeamName());
        this.team2Name = new SimpleStringProperty(team2.getTeamName());
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.winner = new SimpleStringProperty(getWinner());

        allMatches.add(this);
    }

    public String getWinner()
    {
        if (team1Score > team2Score)
        {
            return teams[0].getTeamName();
        }
        else if (team2Score > team1Score)
        {
            return teams[1].getTeamName();
        }
        else
        {
            return "";
        }
    }

    public Team getWinningTeam()
    {
        if (team1Score > team2Score)
        {
            return teams[0];
        }
        else if (team2Score > team1Score)
        {
            return teams[1];
        }
        else
        {
            return Team.allTeams.get(0);
        }
    }

    public String getDate()
    {
        return date.get();
    }

    public void setDate(String date)
    {
        this.date.set(date);
    }

    public void setTeam1(Team team)
    {
        this.teams[0] = team;
        this.team1Name.set(team.getTeamName());
    }

    public void setTeam2(Team team)
    {
        this.teams[1] = team;
        this.team2Name.set(team.getTeamName());
    }

    public String getTeam1Name()
    {
        return teams[0].getTeamName();
    }

    public String getTeam2Name()
    {
        return teams[1].getTeamName();
    }

    public Team[] getTeams()
    {
        return this.teams;
    }

    public int getTeam1Score()
    {
        return team1Score;
    }

    public void setTeam1Score(int team1Score)
    {
        this.team1Score = team1Score;
    }

    public int getTeam2Score()
    {
        return team2Score;
    }

    public void setTeam2Score(int team2Score)
    {
        this.team2Score = team2Score;
    }
}
