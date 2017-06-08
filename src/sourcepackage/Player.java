package sourcepackage;//Magnus Svendsen DAT16i

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Player
{
    private SimpleStringProperty name;
    private SimpleStringProperty dateOfBirth;
    private SimpleStringProperty email;
    private int rank;

    public static int playerCount;
    public static ArrayList<Player> allPlayers = new ArrayList<>();

    public Player(String name, String dateOfBirth, String email)
    {
        this.name = new SimpleStringProperty(name);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.email = new SimpleStringProperty(email);

        playerCount++;

        this.rank = calculateRank();

        Player.allPlayers.add(this);
    }

    private int calculateRank()
    {
        int val = 1;

        for (Player player : Player.allPlayers)
        {
            if (val <= player.getRank())
            {
                val++;
            }
        }

        return val;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String n)
    {
        name.set(n);
    }

    public String getDateOfBirth()
    {
        return dateOfBirth.get();
    }

    public void setDateOfBirth(String dOB)
    {
        dateOfBirth.set(dOB);
    }

    public String getEmail()
    {
        return email.get();
    }

    public void setEmail(String em)
    {
        email.set(em);
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    @Override
    public String toString()
    {
        return name.getValue();
    }
}
