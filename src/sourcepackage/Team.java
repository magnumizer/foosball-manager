package sourcepackage;//Magnus Svendsen DAT16i

import java.util.ArrayList;

public class Team
{
    private String teamName;
    private Player[] players = new Player[2];

    private int id;

    public static int teamCount = 0;

    public static ArrayList<Team> allTeams = new ArrayList<>();

    public Team(String teamName, Player player1, Player player2)
    {
        this.teamName = teamName;
        this.players[0] = player1;
        this.players[1] = player2;

        teamCount++;
        this.id = teamCount;
        allTeams.add(this);
    }

    public void setTeamName(String name)
    {
        this.teamName = name;
    }

    public String getTeamName()
    {
        return this.teamName;
    }

    public void setPlayer1(Player player)
    {
        this.players[0] = player;
    }

    public void setPlayer2(Player player)
    {
        this.players[1] = player;
    }

    public Player[] getPlayers()
    {
        return this.players;
    }

    public int getId()
    {
        return this.id;
    }

    @Override
    public String toString()
    {
        return this.teamName;
    }
}
