package sourcepackage;//Magnus Svendsen DAT16i

import java.util.ArrayList;

public class Tournament
{
    String tournamentName;
    String startDate;
    int tournamentSize;
    int currentStage;

    public static int tournamentCount = 0;
    public static ArrayList<Tournament> allTournaments = new ArrayList<>();

    public ArrayList<Match> matches32 = new ArrayList<>();
    public ArrayList<Match> matches16 = new ArrayList<>();
    public ArrayList<Match> matches8 = new ArrayList<>();
    public ArrayList<Match> matches4 = new ArrayList<>();
    public ArrayList<Match> matches2 = new ArrayList<>();
    public ArrayList<Match> matches1= new ArrayList<>();

    public Tournament(String tournamentName, String startDate, int tournamentSize)
    {
        this.tournamentName = tournamentName;
        this.startDate = startDate;
        this.tournamentSize = tournamentSize;

        tournamentCount++;
        allTournaments.add(this);
    }

    public void setTournamentName(String tournamentName)
    {
        this.tournamentName = tournamentName;
    }

    public String getTournamentName()
    {
        return this.tournamentName;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setTournamentSize(int size)
    {
        this.tournamentSize = size;
    }

    public int getTournamentSize()
    {
        return tournamentSize;
    }

    public int getCurrentStage()
    {
        return currentStage;
    }

    public void setCurrentStage(int currentStage)
    {
        this.currentStage = currentStage;
    }
}
