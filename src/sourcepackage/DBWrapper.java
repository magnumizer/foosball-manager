package sourcepackage;//Magnus Svendsen DAT16i

import java.sql.*;

public class DBWrapper
{

    public void updatePlayerTable(Player player)
    {
        int _rank = player.getRank();
        String _name = player.getName();
        String _date = player.getDateOfBirth();
        String _email = player.getEmail();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `players` (`rank`, `name`, `date`, `email`) " +
                    "VALUES ('" + _rank + "', '" + _name + "', '" + _date + "', '" + _email + "');";


            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deletePlayer(Player player)
    {
        int rank = player.getRank();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "DELETE FROM players WHERE rank = ?;";

            PreparedStatement prepStmt = con.prepareStatement(sql);
            prepStmt.setInt(1, rank);
            prepStmt.execute();

            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void updateTeamTable(Team team)
    {
        String _name = team.getTeamName();
        int _id = team.getId();
        Player[] players = team.getPlayers();
        int player1PK = players[0].getRank();
        int player2PK = players[1].getRank();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `teams` (`name`, `player1`, `player2`, `id`) " +
                    "VALUES ('" + _name + "', '" + player1PK + "', '" + player2PK + "', '" + _id + "');";


            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteTeam(Team team)
    {
        int _id = team.getId();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "DELETE FROM teams WHERE id = ?;";

            PreparedStatement prepStmt = con.prepareStatement(sql);
            prepStmt.setInt(1, _id);
            prepStmt.execute();

            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void getPlayerData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM players;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String name = rs.getString("name");
                String date = rs.getString("date");
                String email = rs.getString("email");

                new Player(name, date, email);
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void getTeamData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM teams;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String name = rs.getString("name");
                int player1PK = rs.getInt("player1");
                int player2PK = rs.getInt("player2");

                Team team = new Team(name, null, null);

                for (Player player : Player.allPlayers)
                {
                    if (player1PK == player.getRank())
                    {
                        team.setPlayer1(player);
                    }
                    else if (player2PK == player.getRank())
                    {
                        team.setPlayer2(player);
                    }
                }
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
