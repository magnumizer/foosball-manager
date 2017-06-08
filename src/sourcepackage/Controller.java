package sourcepackage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import sourcepackage.GUIObjects.TeamTile;
import sourcepackage.GUIObjects.TeamTileEdit;
import sourcepackage.GUIObjects.TournamentTile;
import sourcepackage.GUIObjects.TournamentTileEdit;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.LogManager;


public class Controller implements Initializable
{
    double xOffset = 0;
    double yOffset = 0;

    private Tournament currentTournament;
    private DBWrapper dbWrapper = new DBWrapper();

    //region FXML
    @FXML
    private Pane rootPane;
    @FXML
    private Pane aboutPane;
    @FXML
    private Tab playersTab;
    @FXML
    private Tab teamsTab;
    @FXML
    private Tab matchesTab;
    @FXML
    private AnchorPane playersTabContent;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private DatePicker playerBirthdayField;
    @FXML
    private TextField playerEmailTextField;
    @FXML
    private TextField playerNameTextFieldEdit;
    @FXML
    private DatePicker playerBirthdayFieldEdit;
    @FXML
    private TextField playerEmailTextFieldEdit;
    @FXML
    private ScrollPane tournamentList;
    @FXML
    private Pane bracket;
    @FXML
    private Tab tab32;
    @FXML
    private Tab tab16;
    @FXML
    private Tab tab8;
    @FXML
    private Tab tab4;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab1;
    @FXML
    private TableView<Match> table32;
    @FXML
    TableColumn<Match, String> matchDateCol32;
    @FXML
    TableColumn<Match, String> team1Col32;
    @FXML
    TableColumn<Match, Integer> score1Col32;
    @FXML
    TableColumn<Match, String> team2Col32;
    @FXML
    TableColumn<Match, Integer> score2Col32;
    @FXML
    TableColumn<Match, String> winnerCol32;
    @FXML
    private TableView<Match> table16;
    @FXML
    TableColumn<Match, String> matchDateCol16;
    @FXML
    TableColumn<Match, String> team1Col16;
    @FXML
    TableColumn<Match, Integer> score1Col16;
    @FXML
    TableColumn<Match, String> team2Col16;
    @FXML
    TableColumn<Match, Integer> score2Col16;
    @FXML
    TableColumn<Match, String> winnerCol16;
    @FXML
    private TableView<Match> table8;
    @FXML
    TableColumn<Match, String> matchDateCol8;
    @FXML
    TableColumn<Match, String> team1Col8;
    @FXML
    TableColumn<Match, Integer> score1Col8;
    @FXML
    TableColumn<Match, String> team2Col8;
    @FXML
    TableColumn<Match, Integer> score2Col8;
    @FXML
    TableColumn<Match, String> winnerCol8;
    @FXML
    private TableView<Match> table4;
    @FXML
    TableColumn<Match, String> matchDateCol4;
    @FXML
    TableColumn<Match, String> team1Col4;
    @FXML
    TableColumn<Match, Integer> score1Col4;
    @FXML
    TableColumn<Match, String> team2Col4;
    @FXML
    TableColumn<Match, Integer> score2Col4;
    @FXML
    TableColumn<Match, String> winnerCol4;
    @FXML
    private TableView<Match> table2;
    @FXML
    TableColumn<Match, String> matchDateCol2;
    @FXML
    TableColumn<Match, String> team1Col2;
    @FXML
    TableColumn<Match, Integer> score1Col2;
    @FXML
    TableColumn<Match, String> team2Col2;
    @FXML
    TableColumn<Match, Integer> score2Col2;
    @FXML
    TableColumn<Match, String> winnerCol2;
    @FXML
    private TableView<Match> table1;
    @FXML
    TableColumn<Match, String> matchDateCol1;
    @FXML
    TableColumn<Match, String> team1Col1;
    @FXML
    TableColumn<Match, Integer> score1Col1;
    @FXML
    TableColumn<Match, String> team2Col1;
    @FXML
    TableColumn<Match, Integer> score2Col1;
    @FXML
    TableColumn<Match, String> winnerCol1;
    @FXML
    private TitledPane editMatchPanel;
    @FXML
    private DatePicker dateMatchFieldEdit;
    @FXML
    private ComboBox<Team> team1MatchFieldEdit;
    @FXML
    private TextField score1MatchFieldEdit;
    @FXML
    private ComboBox<Team> team2MatchFieldEdit;
    @FXML
    private TextField score2MatchFieldEdit;
    @FXML
    private Label tournamentLabel;
    @FXML
    private Button addTeamBtn;
    @FXML
    private Button addTournamentBtn;
    @FXML
    private GridPane teamGrid;
    @FXML
    private AnchorPane teamGridParent;
    @FXML
    private GridPane tournamentGrid;
    @FXML
    private AnchorPane tournamentGridParent;
    @FXML
    private TabPane tournamentTabPane;
    @FXML
    private Button editPlayerBtn;
    @FXML
    private Button deletePlayerBtn;
    @FXML
    private TitledPane newPlayerPanel;
    @FXML
    private TitledPane editPlayerPanel;
    @FXML
    TableColumn<Player, Integer> rankCol;
    @FXML
    TableColumn<Player, String> nameCol;
    @FXML
    TableColumn<Player, String> dateOfBirthCol;
    @FXML
    TableColumn<Player, String> emailCol;
    @FXML
    private TableView<Player> playerTable;
    //endregion

    //region MenuBar
    public synchronized void injectVirus(ActionEvent actionEvent) //just for fun :)
    {
        try
        {
            System.out.println("Injecting virus.............................");
            Thread.currentThread().sleep(4000);
            System.out.println("Done.\n#35XE3AY82@?:System.isCorrupted(true)");
        } catch (InterruptedException e)
        {
            System.out.println("IT'S A TRAP!");
            System.exit(420);
        }
    }

    public void handleDelete(ActionEvent actionEvent)
    {
        if (playersTab.isSelected())
        {
            deletePlayer(null);
        }
    }

    public void openAboutPane(ActionEvent actionEvent)
    {
        aboutPane.setDisable(false);
        aboutPane.setVisible(true);
    }

    public void closeAboutPane(ActionEvent actionEvent)
    {
        aboutPane.setDisable(true);
        aboutPane.setVisible(false);
    }

    public void handleStageMovement()
    {
        rootPane.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        rootPane.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Main.primaryStage.setX(event.getScreenX() - xOffset);
                Main.primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public void minimizeProgram(ActionEvent actionEvent)
    {
        Main.primaryStage.setIconified(true);
    }

    public void closeProgram(ActionEvent actionEvent)
    {
        StageHandler.closeProgram();
    }
    //endregion

    //region PlayersTab
    public void openPlayerCreate(ActionEvent actionEvent)
    {
        newPlayerPanel.setDisable(false);
        newPlayerPanel.setVisible(true);
    }

    public void openPlayerEdit(ActionEvent actionEvent)
    {
        int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            LocalDate localDate = LocalDate.parse(Player.allPlayers.get(selectedIndex).getDateOfBirth());

            playerNameTextFieldEdit.setText(Player.allPlayers.get(selectedIndex).getName());
            playerBirthdayFieldEdit.setValue(localDate);
            playerEmailTextFieldEdit.setText(Player.allPlayers.get(selectedIndex).getEmail());
        }

        editPlayerPanel.setDisable(false);
        editPlayerPanel.setVisible(true);
    }

    public void addPlayerToList(ActionEvent actionEvent)
    {
        if (!playerNameTextField.getText().equals(""))
        {
            if (playerBirthdayField.getValue() != null)
            {
                if (!playerEmailTextField.getText().equals("") && playerEmailTextField.getText().indexOf('@') >= 0)
                {
                    Player player = new Player(playerNameTextField.getText(), playerBirthdayField.getValue().toString(), playerEmailTextField.getText());
                    hideNewPlayerPanel(null);
                    clearPlayerCreationFields();
                    setPlayerTable();
                    updateAllTeamTileEdits();
                    dbWrapper.updatePlayerTable(player);
                }
                else
                {
                    //no email
                    StageHandler.displayAlert("Error 71: Player e-mail not specified", "Player e-mail missing!", "Please type a valid e-mail for the Player");
                }
            }
            else
            {
                //no bday
                StageHandler.displayAlert("Error 70: Player birth date not specified", "Player date of birth missing!", "Please choose a date of birth for the Player");
            }
        }
        else
        {
            //no name
            StageHandler.displayAlert("Error 69: Player name not specified", "Player name missing!", "Please type a name for the Player");
        }
    }

    public void updatePlayer(ActionEvent actionEvent)
    {
        int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            editPlayer(selectedIndex);
        }
        else
        {
            // Nothing selected.
            StageHandler.displayAlert("Error 420: You have not selected a Player", "No Player Selected", "Please select the Player you want to edit.");
        }
    }

    private void editPlayer(int selectedIndex)
    {
        if (!playerNameTextFieldEdit.getText().equals(""))
        {
            if (playerBirthdayFieldEdit.getValue() != null)
            {
                if (!playerEmailTextFieldEdit.getText().equals(""))
                {
                    Player player = Player.allPlayers.get(selectedIndex);
                    player.setName(playerNameTextFieldEdit.getText());
                    player.setDateOfBirth(playerBirthdayFieldEdit.getValue().toString());
                    player.setEmail(playerEmailTextFieldEdit.getText());
                    hideEditPlayerPanel(null);
                    setPlayerTable();
                    updateAllTeamTileEdits();
                    dbWrapper.updatePlayerTable(player);
                }
                else
                {
                    //no email
                    StageHandler.displayAlert("Error 71: Player e-mail not specified", "Player e-mail missing!", "Please type an e-mail for the Player");
                }
            }
            else
            {
                //no bday
                StageHandler.displayAlert("Error 70: Player birth date not specified", "Player date of birth missing!", "Please type a date of birth for the Player");
            }
        }
        else
        {
            //no name
            StageHandler.displayAlert("Error 69: Player name not specified", "Player name missing!", "Please type a name for the Player");
        }
    }

    public void refreshPlayerTable(ActionEvent actionEvent)
    {
        playerTable.getItems().clear();
        //load from db
        playerTable.getItems().setAll(Player.allPlayers);
    }

    private void clearPlayerCreationFields()
    {
        playerNameTextField.clear();
        playerBirthdayField.setValue(null);
        playerEmailTextField.clear();
    }

    private void clearPlayerEditFields()
    {
        playerNameTextFieldEdit.clear();
        playerBirthdayFieldEdit.setValue(null);
        playerEmailTextFieldEdit.clear();
    }

    public void handlePlayerTableSelection()
    {
        playersTabContent.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                clearTableSelection();
            }
        });
        playerTable.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0)
                {
                    deletePlayerBtn.setDisable(false);
                    editPlayerBtn.setDisable(false);
                }
            }
        });
    }

    private void clearTableSelection()
    {
        playerTable.getSelectionModel().clearSelection();
        deletePlayerBtn.setDisable(true);
        editPlayerBtn.setDisable(true);
    }

    private void setPlayerTable()
    {
        playerTable.getItems().setAll(Player.allPlayers);
    }

    private void updateAllTeamTileEdits()
    {
        for (TeamTileEdit teamTileEdit : TeamTileEdit.allTeamTileEdits)
        {
            teamTileEdit.updatePlayerList();
        }
    }

    public void deletePlayer(ActionEvent actionEvent)
    {
        int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Player player = Player.allPlayers.get(selectedIndex);
            String _teamName = null;
            boolean isInTeam = false;

            for (Team team : Team.allTeams)
            {
                Player[] players = team.getPlayers();
                if (players[0] == player || players[1] == player)
                {
                    isInTeam = true;
                    _teamName = team.getTeamName();
                }
            }

            Alert alert = null;

            if (isInTeam)
            {
                alert = new Alert(Alert.AlertType.CONFIRMATION, player.getName() + " is in " + _teamName + "\nAre you sure you want to delete?", ButtonType.YES, ButtonType.CANCEL);
            }
            else
            {
                alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete\n" + player.getName() + " ?", ButtonType.YES, ButtonType.CANCEL);
            }

            alert.setHeaderText("Confirm deletion");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
            {
                for (Team team : Team.allTeams)
                {
                    Player[] players = team.getPlayers();
                    if (players[0] == player)
                    {
                        team.setPlayer1(null);
                    }
                    else if (players[1] == player)
                    {
                        team.setPlayer2(null);
                    }
                }

                for (int i = 0; i < Player.allPlayers.size(); i++)
                {
                    if (Player.allPlayers.get(i).getRank() > player.getRank())
                    {
                        Player.allPlayers.get(i).setRank(Player.allPlayers.get(i).getRank() - 1);
                    }
                }

                dbWrapper.deletePlayer(player);
                playerTable.getItems().remove(selectedIndex);
                Player.allPlayers.remove(selectedIndex);
                Player.playerCount--;
                updateAllTeamTileEdits();
                LogManager.getLogManager().reset();

                if (Player.allPlayers.isEmpty())
                {
                    deletePlayerBtn.setDisable(true);
                    editPlayerBtn.setDisable(true);
                }
            }
        }
        else
        {
            // Nothing selected.
            StageHandler.displayAlert("Error 420: You have not selected a Player", "No Player Selected", "Please select the Player you want to delete.");
        }
    }

    public void hideNewPlayerPanel(ActionEvent actionEvent)
    {
        newPlayerPanel.setDisable(true);
        newPlayerPanel.setVisible(false);
    }

    public void hideEditPlayerPanel(ActionEvent actionEvent)
    {
        editPlayerPanel.setDisable(true);
        editPlayerPanel.setVisible(false);
        clearPlayerEditFields();
    }
    //endregion

    //region TeamsTab
    public void newTeam(ActionEvent actionEvent)
    {
        teamGrid.getChildren().remove(addTeamBtn);

        Team team = new Team("Team " + (Team.teamCount+1), null, null);

        TeamTile teamTile = new TeamTile(team, this);

        TeamTileEdit teamTileEdit = new TeamTileEdit(teamTile, this);

        teamTile.setTeamTileEdit(teamTileEdit);

        teamGrid.add(teamTile, StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);
        teamGrid.add(teamTileEdit, StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);

        TeamTile.allTeamTiles.add(teamTile);
        TeamTileEdit.allTeamTileEdits.add(teamTileEdit);

        if (StageHandler.addTeamBtnCurrentCol == 0 && StageHandler.addTeamBtnCurrentRow > 0)
        {
            teamGridParent.setPrefHeight(teamGridParent.getPrefHeight() + 250);
            teamGrid.addRow(StageHandler.addTeamBtnCurrentRow + 1);
        }

        updateTeamList();
        moveTeamBtnFwd();
    }

    private void createTeamFromDB(Team team)
    {
        teamGrid.getChildren().remove(addTeamBtn);

        TeamTile teamTile = new TeamTile(team, this);

        TeamTileEdit teamTileEdit = new TeamTileEdit(teamTile, this);

        teamTile.setTeamTileEdit(teamTileEdit);

        teamGrid.add(teamTile, StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);
        teamGrid.add(teamTileEdit, StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);

        TeamTile.allTeamTiles.add(teamTile);
        TeamTileEdit.allTeamTileEdits.add(teamTileEdit);

        if (StageHandler.addTeamBtnCurrentCol == 0 && StageHandler.addTeamBtnCurrentRow > 0)
        {
            teamGridParent.setPrefHeight(teamGridParent.getPrefHeight() + 250);
            teamGrid.addRow(StageHandler.addTeamBtnCurrentRow + 1);
        }

        teamTileEdit.setTeamTile(team);

        moveTeamBtnFwd();
    }

    private void setTeamGrid()
    {
        for (Team team : Team.allTeams)
        {
            createTeamFromDB(team);
        }
    }

    private void moveTeamBtnFwd()
    {
        if (StageHandler.addTeamBtnCurrentCol < 2)
        {
            StageHandler.addTeamBtnCurrentCol++;
            teamGrid.add(addTeamBtn, StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);
        }
        else
        {
            StageHandler.addTeamBtnCurrentRow++;
            StageHandler.addTeamBtnCurrentCol = 0;
            teamGrid.add(addTeamBtn, StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);
        }
    }
    //endregion

    //region MatchesTab

    public void updateTeamList()
    {
        ObservableList<Team> teams = FXCollections.observableArrayList(Team.allTeams);
        team1MatchFieldEdit.setItems(teams);
        team2MatchFieldEdit.setItems(teams);
    }

    public void showBracket(Tournament tournament)
    {
        tournamentLabel.setText(tournament.getTournamentName());
        this.currentTournament = tournament;

        switch (tournament.getTournamentSize())
        {
            case 2:
                tab32.setDisable(true);
                tab32.setStyle("-fx-background-color: transparent;");
                tab16.setDisable(true);
                tab16.setStyle("-fx-background-color: transparent;");
                tab8.setDisable(true);
                tab8.setStyle("-fx-background-color: transparent;");
                tab4.setDisable(true);
                tab4.setStyle("-fx-background-color: transparent;");
                tab2.setDisable(true);
                tab2.setStyle("-fx-background-color: transparent;");
                tab1.setDisable(false);
                tab1.setStyle("");
                break;
            case 4:
                tab32.setDisable(true);
                tab32.setStyle("-fx-background-color: transparent;");
                tab16.setDisable(true);
                tab16.setStyle("-fx-background-color: transparent;");
                tab8.setDisable(true);
                tab8.setStyle("-fx-background-color: transparent;");
                tab4.setDisable(true);
                tab4.setStyle("-fx-background-color: transparent;");
                tab2.setDisable(false);
                tab2.setStyle("");
                tab1.setDisable(false);
                tab1.setStyle("");
                break;
            case 8:
                tab32.setDisable(true);
                tab32.setStyle("-fx-background-color: transparent;");
                tab16.setDisable(true);
                tab16.setStyle("-fx-background-color: transparent;");
                tab8.setDisable(true);
                tab8.setStyle("-fx-background-color: transparent;");
                tab4.setDisable(false);
                tab4.setStyle("");
                tab2.setDisable(false);
                tab2.setStyle("");
                tab1.setDisable(false);
                tab1.setStyle("");
                break;
            case 16:
                tab32.setDisable(true);
                tab32.setStyle("-fx-background-color: transparent;");
                tab16.setDisable(true);
                tab16.setStyle("-fx-background-color: transparent;");
                tab8.setDisable(false);
                tab8.setStyle("");
                tab4.setDisable(false);
                tab4.setStyle("");
                tab2.setDisable(false);
                tab2.setStyle("");
                tab1.setDisable(false);
                tab1.setStyle("");
                break;
            case 32:
                tab32.setDisable(true);
                tab32.setStyle("-fx-background-color: transparent;");
                tab16.setDisable(false);
                tab16.setStyle("");
                tab8.setDisable(false);
                tab8.setStyle("");
                tab4.setDisable(false);
                tab4.setStyle("");
                tab2.setDisable(false);
                tab2.setStyle("");
                tab1.setDisable(false);
                tab1.setStyle("");
                break;
            case 64:
                tab32.setDisable(false);
                tab32.setStyle("");
                tab16.setDisable(false);
                tab16.setStyle("");
                tab8.setDisable(false);
                tab8.setStyle("");
                tab4.setDisable(false);
                tab4.setStyle("");
                tab2.setDisable(false);
                tab2.setStyle("");
                tab1.setDisable(false);
                tab1.setStyle("");
                break;
            default:
                break;
        }

        switch (tournament.getCurrentStage())
        {
            case 32:
                tournamentTabPane.getSelectionModel().select(0);
                break;
            case 16:
                tournamentTabPane.getSelectionModel().select(1);
                break;
            case 8:
                tournamentTabPane.getSelectionModel().select(2);
                break;
            case 4:
                tournamentTabPane.getSelectionModel().select(3);
                break;
            case 2:
                tournamentTabPane.getSelectionModel().select(4);
                break;
            case 1:
                tournamentTabPane.getSelectionModel().select(5);
                break;
            default:
                tournamentTabPane.getSelectionModel().select(5);
                break;
        }

        updateTeamList();

        clearTournamentTables();
        addTournamentMatches(tournament);

        tournamentList.setDisable(true);
        tournamentList.setVisible(false);

        bracket.setDisable(false);
        bracket.setVisible(true);
    }

    private void clearTournamentTables()
    {
        table32.getItems().clear();
        table16.getItems().clear();
        table8.getItems().clear();
        table4.getItems().clear();
        table2.getItems().clear();
        table1.getItems().clear();
    }

    private void addTournamentMatches(Tournament tournament)
    {
        table32.getItems().addAll(tournament.matches32);
        table16.getItems().addAll(tournament.matches16);
        table8.getItems().addAll(tournament.matches8);
        table4.getItems().addAll(tournament.matches4);
        table2.getItems().addAll(tournament.matches2);
        table1.getItems().addAll(tournament.matches1);
    }

    public void hideBracket(ActionEvent actionEvent)
    {
        bracket.setDisable(true);
        bracket.setVisible(false);

        tournamentList.setDisable(false);
        tournamentList.setVisible(true);

        hideEditMatchPanel(null);
    }

    public void newTournament(ActionEvent actionEvent)
    {
        if (Team.teamCount > 0)
        {
            tournamentGrid.getChildren().remove(addTournamentBtn);

            Tournament tournament = new Tournament("Tournament " + (Tournament.tournamentCount+1), null, 0);

            TournamentTile tournamentTile = new TournamentTile(tournament, this);

            TournamentTileEdit tournamentTileEdit = new TournamentTileEdit(tournamentTile, this);

            tournamentTile.setTournamentTileEdit(tournamentTileEdit);

            tournamentGrid.add(tournamentTile, StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);
            tournamentGrid.add(tournamentTileEdit, StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);

            TournamentTile.allTournamentTiles.add(tournamentTile);
            TournamentTileEdit.allTournamentTileEdits.add(tournamentTileEdit);

            if (StageHandler.addTourBtnCurrentCol == 0 && StageHandler.addTourBtnCurrentRow > 0)
            {
                tournamentGridParent.setPrefHeight(tournamentGridParent.getPrefHeight() + 250);
                tournamentGrid.addRow(StageHandler.addTourBtnCurrentRow + 1);
            }

            moveTournamentBtnFwd();
        }
        else
        {
            StageHandler.displayAlert("No teams", "Unable to create new tournament. Team list empty.", "Please create a new team first");
        }
    }

    private void createTournamentFromDB(Tournament tournament)
    {
        tournamentGrid.getChildren().remove(addTournamentBtn);

        TournamentTile tournamentTile = new TournamentTile(tournament, this);

        TournamentTileEdit tournamentTileEdit = new TournamentTileEdit(tournamentTile, this);

        tournamentTile.setTournamentTileEdit(tournamentTileEdit);

        tournamentGrid.add(tournamentTile, StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);
        tournamentGrid.add(tournamentTileEdit, StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);

        TournamentTile.allTournamentTiles.add(tournamentTile);
        TournamentTileEdit.allTournamentTileEdits.add(tournamentTileEdit);

        if (StageHandler.addTourBtnCurrentCol == 0 && StageHandler.addTourBtnCurrentRow > 0)
        {
            tournamentGridParent.setPrefHeight(tournamentGridParent.getPrefHeight() + 250);
            tournamentGrid.addRow(StageHandler.addTourBtnCurrentRow + 1);
        }

        tournamentTileEdit.setTournamentTile(tournament);

        moveTournamentBtnFwd();

    }


    public void openMatchEdit(ActionEvent actionEvent)
    {
        int selectedTab = tournamentTabPane.getSelectionModel().getSelectedIndex();

        if (selectedTab == 0) //tab32
        {
            int selectedIndex = table32.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                Team[] teams = currentTournament.matches32.get(selectedIndex).getTeams();

                if (!currentTournament.matches32.get(selectedIndex).getDate().equals(""))
                {
                    LocalDate localDate = LocalDate.parse(currentTournament.matches32.get(selectedIndex).getDate());
                    dateMatchFieldEdit.setValue(localDate);
                }

                team1MatchFieldEdit.setValue(teams[0]);
                score1MatchFieldEdit.setText(Integer.toString(currentTournament.matches32.get(selectedIndex).getTeam1Score()));
                team2MatchFieldEdit.setValue(teams[1]);
                score2MatchFieldEdit.setText(Integer.toString(currentTournament.matches32.get(selectedIndex).getTeam2Score()));
            }
        }
        else if (selectedTab == 1) //tab16
        {
            int selectedIndex = table16.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                Team[] teams = currentTournament.matches16.get(selectedIndex).getTeams();

                if (!currentTournament.matches16.get(selectedIndex).getDate().equals(""))
                {
                    LocalDate localDate = LocalDate.parse(currentTournament.matches16.get(selectedIndex).getDate());
                    dateMatchFieldEdit.setValue(localDate);
                }

                team1MatchFieldEdit.setValue(teams[0]);
                score1MatchFieldEdit.setText(Integer.toString(currentTournament.matches16.get(selectedIndex).getTeam1Score()));
                team2MatchFieldEdit.setValue(teams[1]);
                score2MatchFieldEdit.setText(Integer.toString(currentTournament.matches16.get(selectedIndex).getTeam2Score()));
            }
        }
        else if (selectedTab == 2) //tab8
        {
            int selectedIndex = table8.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                Team[] teams = currentTournament.matches8.get(selectedIndex).getTeams();

                if (!currentTournament.matches8.get(selectedIndex).getDate().equals(""))
                {
                    LocalDate localDate = LocalDate.parse(currentTournament.matches8.get(selectedIndex).getDate());
                    dateMatchFieldEdit.setValue(localDate);
                }

                team1MatchFieldEdit.setValue(teams[0]);
                score1MatchFieldEdit.setText(Integer.toString(currentTournament.matches8.get(selectedIndex).getTeam1Score()));
                team2MatchFieldEdit.setValue(teams[1]);
                score2MatchFieldEdit.setText(Integer.toString(currentTournament.matches8.get(selectedIndex).getTeam2Score()));
            }
        }
        else if (selectedTab == 3) //tab4
        {
            int selectedIndex = table4.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                Team[] teams = currentTournament.matches4.get(selectedIndex).getTeams();

                if (!currentTournament.matches4.get(selectedIndex).getDate().equals(""))
                {
                    LocalDate localDate = LocalDate.parse(currentTournament.matches4.get(selectedIndex).getDate());
                    dateMatchFieldEdit.setValue(localDate);
                }

                team1MatchFieldEdit.setValue(teams[0]);
                score1MatchFieldEdit.setText(Integer.toString(currentTournament.matches4.get(selectedIndex).getTeam1Score()));
                team2MatchFieldEdit.setValue(teams[1]);
                score2MatchFieldEdit.setText(Integer.toString(currentTournament.matches4.get(selectedIndex).getTeam2Score()));
            }
        }
        else if (selectedTab == 4) //tab2
        {
            int selectedIndex = table2.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                Team[] teams = currentTournament.matches2.get(selectedIndex).getTeams();

                if (!currentTournament.matches2.get(selectedIndex).getDate().equals(""))
                {
                    LocalDate localDate = LocalDate.parse(currentTournament.matches2.get(selectedIndex).getDate());
                    dateMatchFieldEdit.setValue(localDate);
                }

                team1MatchFieldEdit.setValue(teams[0]);
                score1MatchFieldEdit.setText(Integer.toString(currentTournament.matches2.get(selectedIndex).getTeam1Score()));
                team2MatchFieldEdit.setValue(teams[1]);
                score2MatchFieldEdit.setText(Integer.toString(currentTournament.matches2.get(selectedIndex).getTeam2Score()));
            }
        }
        else if (selectedTab == 5) //tab1
        {
            int selectedIndex = table1.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                Team[] teams = currentTournament.matches1.get(selectedIndex).getTeams();

                if (!currentTournament.matches1.get(selectedIndex).getDate().equals(""))
                {
                    LocalDate localDate = LocalDate.parse(currentTournament.matches1.get(selectedIndex).getDate());
                    dateMatchFieldEdit.setValue(localDate);
                }

                team1MatchFieldEdit.setValue(teams[0]);
                score1MatchFieldEdit.setText(Integer.toString(currentTournament.matches1.get(selectedIndex).getTeam1Score()));
                team2MatchFieldEdit.setValue(teams[1]);
                score2MatchFieldEdit.setText(Integer.toString(currentTournament.matches1.get(selectedIndex).getTeam2Score()));
            }
        }

        editMatchPanel.setDisable(false);
        editMatchPanel.setVisible(true);
    }

    public void updateMatch(ActionEvent actionEvent)
    {
        int selectedTab = tournamentTabPane.getSelectionModel().getSelectedIndex();

        if (selectedTab == 0) //tab32
        {
            int selectedIndex = table32.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                editMatch(selectedTab, selectedIndex);
            }
            else
            {
                // Nothing selected.
                StageHandler.displayAlert("Error 420: You have not selected a Match", "No Match Selected", "Please select the Match you want to edit.");
            }
        }
        else if (selectedTab == 1) //tab16
        {
            int selectedIndex = table16.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                editMatch(selectedTab, selectedIndex);
            }
            else
            {
                // Nothing selected.
                StageHandler.displayAlert("Error 420: You have not selected a Match", "No Match Selected", "Please select the Match you want to edit.");
            }
        }
        else if (selectedTab == 2) //tab8
        {
            int selectedIndex = table8.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                editMatch(selectedTab, selectedIndex);
            }
            else
            {
                // Nothing selected.
                StageHandler.displayAlert("Error 420: You have not selected a Match", "No Match Selected", "Please select the Match you want to edit.");
            }
        }
        else if (selectedTab == 3) //tab4
        {
            int selectedIndex = table4.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                editMatch(selectedTab, selectedIndex);
            }
            else
            {
                // Nothing selected.
                StageHandler.displayAlert("Error 420: You have not selected a Match", "No Match Selected", "Please select the Match you want to edit.");
            }
        }
        else if (selectedTab == 4) //tab2
        {
            int selectedIndex = table2.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                editMatch(selectedTab, selectedIndex);
            }
            else
            {
                // Nothing selected.
                StageHandler.displayAlert("Error 420: You have not selected a Match", "No Match Selected", "Please select the Match you want to edit.");
            }
        }
        else if (selectedTab == 5) //tab1
        {
            int selectedIndex = table1.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0)
            {
                editMatch(selectedTab, selectedIndex);
            }
            else
            {
                // Nothing selected.
                StageHandler.displayAlert("Error 420: You have not selected a Match", "No Match Selected", "Please select the Match you want to edit.");
            }
        }
    }

    private void editMatch(int selectedTab, int selectedIndex)
    {
        if (score1MatchFieldEdit.getText().equals(""))
        {
            score1MatchFieldEdit.setText("0");
        }
        if (score2MatchFieldEdit.getText().equals(""))
        {
            score2MatchFieldEdit.setText("0");
        }

        if (dateMatchFieldEdit.getValue() != null)
        {
            if (team1MatchFieldEdit.getValue() != null)
            {
                if (team2MatchFieldEdit.getValue() != null)
                {
                    if (team1MatchFieldEdit.getValue() != team2MatchFieldEdit.getValue())
                    {
                        if (!checkIfAlreadyPlaying(selectedTab, selectedIndex))
                        {
                            String date = dateMatchFieldEdit.getValue().toString();
                            if (!score1MatchFieldEdit.getText().equals(score2MatchFieldEdit.getText()))
                            {
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                LocalDate localDate = LocalDate.now();
                                date = dtf.format(localDate);
                            }

                            switch (selectedTab)
                            {
                                case 0:
                                    currentTournament.matches32.get(selectedIndex).setDate(dateMatchFieldEdit.getValue().toString());
                                    currentTournament.matches32.get(selectedIndex).setTeam1(team1MatchFieldEdit.getValue());
                                    currentTournament.matches32.get(selectedIndex).setTeam1Score(Integer.parseInt(score1MatchFieldEdit.getText()));
                                    currentTournament.matches32.get(selectedIndex).setTeam2(team2MatchFieldEdit.getValue());
                                    currentTournament.matches32.get(selectedIndex).setTeam2Score(Integer.parseInt(score2MatchFieldEdit.getText()));
                                    currentTournament.setCurrentStage(32);
                                    updateTournamentTile(date, "Round of 32", "TBD");
                                    if (checkRoundComplete(selectedTab))
                                    {
                                        currentTournament.setCurrentStage(16);
                                        updateTournamentTile(date, "Round of 16", "TBD");
                                    }
                                    break;
                                case 1:
                                    currentTournament.matches16.get(selectedIndex).setDate(dateMatchFieldEdit.getValue().toString());
                                    currentTournament.matches16.get(selectedIndex).setTeam1(team1MatchFieldEdit.getValue());
                                    currentTournament.matches16.get(selectedIndex).setTeam1Score(Integer.parseInt(score1MatchFieldEdit.getText()));
                                    currentTournament.matches16.get(selectedIndex).setTeam2(team2MatchFieldEdit.getValue());
                                    currentTournament.matches16.get(selectedIndex).setTeam2Score(Integer.parseInt(score2MatchFieldEdit.getText()));
                                    currentTournament.setCurrentStage(16);
                                    updateTournamentTile(date, "Round of 16", "TBD");
                                    if (checkRoundComplete(selectedTab))
                                    {
                                        currentTournament.setCurrentStage(8);
                                        updateTournamentTile(date, "Round of 8", "TBD");
                                    }
                                    break;
                                case 2:
                                    currentTournament.matches8.get(selectedIndex).setDate(dateMatchFieldEdit.getValue().toString());
                                    currentTournament.matches8.get(selectedIndex).setTeam1(team1MatchFieldEdit.getValue());
                                    currentTournament.matches8.get(selectedIndex).setTeam1Score(Integer.parseInt(score1MatchFieldEdit.getText()));
                                    currentTournament.matches8.get(selectedIndex).setTeam2(team2MatchFieldEdit.getValue());
                                    currentTournament.matches8.get(selectedIndex).setTeam2Score(Integer.parseInt(score2MatchFieldEdit.getText()));
                                    currentTournament.setCurrentStage(8);
                                    updateTournamentTile(date, "Round of 8", "TBD");
                                    if (checkRoundComplete(selectedTab))
                                    {
                                        currentTournament.setCurrentStage(4);
                                        updateTournamentTile(date, "Quarterfinal", "TBD");
                                    }
                                    break;
                                case 3:
                                    currentTournament.matches4.get(selectedIndex).setDate(dateMatchFieldEdit.getValue().toString());
                                    currentTournament.matches4.get(selectedIndex).setTeam1(team1MatchFieldEdit.getValue());
                                    currentTournament.matches4.get(selectedIndex).setTeam1Score(Integer.parseInt(score1MatchFieldEdit.getText()));
                                    currentTournament.matches4.get(selectedIndex).setTeam2(team2MatchFieldEdit.getValue());
                                    currentTournament.matches4.get(selectedIndex).setTeam2Score(Integer.parseInt(score2MatchFieldEdit.getText()));
                                    currentTournament.setCurrentStage(4);
                                    updateTournamentTile(date, "Quarterfinal", "TBD");
                                    if (checkRoundComplete(selectedTab))
                                    {
                                        currentTournament.setCurrentStage(2);
                                        updateTournamentTile(date, "Semi-final", "TBD");
                                    }
                                    break;
                                case 4:
                                    currentTournament.matches2.get(selectedIndex).setDate(dateMatchFieldEdit.getValue().toString());
                                    currentTournament.matches2.get(selectedIndex).setTeam1(team1MatchFieldEdit.getValue());
                                    currentTournament.matches2.get(selectedIndex).setTeam1Score(Integer.parseInt(score1MatchFieldEdit.getText()));
                                    currentTournament.matches2.get(selectedIndex).setTeam2(team2MatchFieldEdit.getValue());
                                    currentTournament.matches2.get(selectedIndex).setTeam2Score(Integer.parseInt(score2MatchFieldEdit.getText()));
                                    currentTournament.setCurrentStage(2);
                                    updateTournamentTile(date, "Semi-final", "TBD");
                                    if (checkRoundComplete(selectedTab))
                                    {
                                        currentTournament.setCurrentStage(1);
                                        updateTournamentTile(date, "Final", "TBD");
                                    }
                                    break;
                                case 5:
                                    currentTournament.matches1.get(selectedIndex).setDate(dateMatchFieldEdit.getValue().toString());
                                    currentTournament.matches1.get(selectedIndex).setTeam1(team1MatchFieldEdit.getValue());
                                    currentTournament.matches1.get(selectedIndex).setTeam1Score(Integer.parseInt(score1MatchFieldEdit.getText()));
                                    currentTournament.matches1.get(selectedIndex).setTeam2(team2MatchFieldEdit.getValue());
                                    currentTournament.matches1.get(selectedIndex).setTeam2Score(Integer.parseInt(score2MatchFieldEdit.getText()));
                                    currentTournament.setCurrentStage(1);
                                    if (!checkRoundComplete(selectedTab))
                                    {
                                        updateTournamentTile(date, "Final", "TBD");
                                    }
                                    else
                                    {
                                        updateTournamentTile(date, "Complete", currentTournament.matches1.get(selectedIndex).getWinner());
                                    }
                                    break;
                                default:
                                    System.out.println("error");
                                    break;
                            }
                            handleWinner();
                            hideEditMatchPanel(null);
                            setMatchTables();
                            //save to db
                        }
                        else
                        {
                            //team already playing
                            StageHandler.displayAlert("Invalid request", "A team can't play twice in the same round!", "Please select a different team");
                        }
                    }
                    else
                    {
                        //same team
                        StageHandler.displayAlert("Invalid request", "A team can't play versus itself!", "Please select 2 different teams");
                    }
                }
                else
                {
                    //no team
                    StageHandler.displayAlert("Team 2 not specified", "Team 2 is missing!", "Please select a Team 2 for the Match");
                }
            }
            else
            {
                //no team
                StageHandler.displayAlert("Team 1 not specified", "Team 1 is missing!", "Please select a Team 1 for the Match");
            }
        }
        else
        {
            //no date
            StageHandler.displayAlert("Match date not specified", "Match date missing!", "Please select a date for the Match");
        }
    }

    private boolean checkIfAlreadyPlaying(int selectedTab, int selectedIndex)
    {
        boolean isAlreadyPlaying = false;

        if (currentTournament.getTournamentSize() == 64)
        {
            if (selectedTab == 0)
            {
                for (Match match : currentTournament.matches32)
                {
                    Team[] teams = match.getTeams();
                    Team team1 = teams[0];
                    Team team2 = teams[1];
                    if (match != currentTournament.matches32.get(selectedIndex))
                    {
                        if (team1MatchFieldEdit.getValue() == team1 || team1MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                        if (team2MatchFieldEdit.getValue() == team1 || team2MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                    }
                }
            }
        }
        else if (currentTournament.getTournamentSize() == 32)
        {
            if (selectedTab == 1)
            {
                for (Match match : currentTournament.matches16)
                {
                    Team[] teams = match.getTeams();
                    Team team1 = teams[0];
                    Team team2 = teams[1];
                    if (match != currentTournament.matches16.get(selectedIndex))
                    {
                        if (team1MatchFieldEdit.getValue() == team1 || team1MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                        if (team2MatchFieldEdit.getValue() == team1 || team2MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                    }
                }
            }
        }
        else if (currentTournament.getTournamentSize() == 16)
        {
            if (selectedTab == 2)
            {
                for (Match match : currentTournament.matches8)
                {
                    Team[] teams = match.getTeams();
                    Team team1 = teams[0];
                    Team team2 = teams[1];
                    if (match != currentTournament.matches8.get(selectedIndex))
                    {
                        if (team1MatchFieldEdit.getValue() == team1 || team1MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                        if (team2MatchFieldEdit.getValue() == team1 || team2MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                    }
                }
            }
        }
        else if (currentTournament.getTournamentSize() == 8)
        {
            if (selectedTab == 3)
            {
                for (Match match : currentTournament.matches4)
                {
                    Team[] teams = match.getTeams();
                    Team team1 = teams[0];
                    Team team2 = teams[1];
                    if (match != currentTournament.matches4.get(selectedIndex))
                    {
                        if (team1MatchFieldEdit.getValue() == team1 || team1MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                        if (team2MatchFieldEdit.getValue() == team1 || team2MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                    }
                }
            }
        }
        else if (currentTournament.getTournamentSize() == 4)
        {
            if (selectedTab == 4)
            {
                for (Match match : currentTournament.matches2)
                {
                    Team[] teams = match.getTeams();
                    Team team1 = teams[0];
                    Team team2 = teams[1];
                    if (match != currentTournament.matches2.get(selectedIndex))
                    {
                        if (team1MatchFieldEdit.getValue() == team1 || team1MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                        if (team2MatchFieldEdit.getValue() == team1 || team2MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                    }
                }
            }
        }
        else if (currentTournament.getTournamentSize() == 2)
        {
            if (selectedTab == 5)
            {
                for (Match match : currentTournament.matches1)
                {
                    Team[] teams = match.getTeams();
                    Team team1 = teams[0];
                    Team team2 = teams[1];
                    if (match != currentTournament.matches1.get(selectedIndex))
                    {
                        if (team1MatchFieldEdit.getValue() == team1 || team1MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                        if (team2MatchFieldEdit.getValue() == team1 || team2MatchFieldEdit.getValue() == team2)
                        {
                            isAlreadyPlaying = true;
                        }
                    }
                }
            }
        }

        return isAlreadyPlaying;
    }

    private void updateTournamentTile(String date, String stage, String winner)
    {
        for (TournamentTile tournamentTile : TournamentTile.allTournamentTiles)
        {
            if (currentTournament == tournamentTile.getTournament())
            {
                tournamentTile.setLastGameText(date);
                tournamentTile.setCurrentStageText(stage);
                tournamentTile.setWinnerText(winner);
            }
        }
    }

    private boolean checkRoundComplete(int selectedTab)
    {
        boolean complete = true;

        switch (selectedTab)
        {
            case 0:
                for (int i = 0; i < currentTournament.matches32.size(); i++)
                {
                    if (currentTournament.matches32.get(i).getWinner().equals(""))
                    {
                        complete = false;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < currentTournament.matches16.size(); i++)
                {
                    if (currentTournament.matches16.get(i).getWinner().equals(""))
                    {
                        complete = false;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < currentTournament.matches8.size(); i++)
                {
                    if (currentTournament.matches8.get(i).getWinner().equals(""))
                    {
                        complete = false;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < currentTournament.matches4.size(); i++)
                {
                    if (currentTournament.matches4.get(i).getWinner().equals(""))
                    {
                        complete = false;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < currentTournament.matches2.size(); i++)
                {
                    if (currentTournament.matches2.get(i).getWinner().equals(""))
                    {
                        complete = false;
                    }
                }
                break;
            case 5:
                if (currentTournament.matches1.get(0).getWinner().equals(""))
                {
                    complete = false;
                }
                break;
            default:
                System.out.println("error");
                break;
        }
        return complete;
    }

    private void handleWinner()
    {
        if (currentTournament.getTournamentSize() == 64)
        {
            int j = 0;
            for (int i = 0; i < currentTournament.matches16.size(); i++)
            {
                currentTournament.matches16.get(i).setTeam1(currentTournament.matches32.get(j).getWinningTeam());
                currentTournament.matches16.get(i).setTeam2(currentTournament.matches32.get(j+1).getWinningTeam());
                j += 2;
            }

            int k = 0;
            for (int i = 0; i < currentTournament.matches8.size(); i++)
            {
                currentTournament.matches8.get(i).setTeam1(currentTournament.matches16.get(k).getWinningTeam());
                currentTournament.matches8.get(i).setTeam2(currentTournament.matches16.get(k+1).getWinningTeam());
                k += 2;
            }

            int l = 0;
            for (int i = 0; i < currentTournament.matches4.size(); i++)
            {
                currentTournament.matches4.get(i).setTeam1(currentTournament.matches8.get(l).getWinningTeam());
                currentTournament.matches4.get(i).setTeam2(currentTournament.matches8.get(l+1).getWinningTeam());
                l += 2;
            }

            int m = 0;
            for (int i = 0; i < currentTournament.matches2.size(); i++)
            {
                currentTournament.matches2.get(i).setTeam1(currentTournament.matches4.get(m).getWinningTeam());
                currentTournament.matches2.get(i).setTeam2(currentTournament.matches4.get(m+1).getWinningTeam());
                m += 2;
            }

            int n = 0;
            for (int i = 0; i < currentTournament.matches1.size(); i++)
            {
                currentTournament.matches1.get(i).setTeam1(currentTournament.matches2.get(n).getWinningTeam());
                currentTournament.matches1.get(i).setTeam2(currentTournament.matches2.get(n+1).getWinningTeam());
                n += 2;
            }
        }
        else if (currentTournament.getTournamentSize() == 32)
        {
            int j = 0;
            for (int i = 0; i < currentTournament.matches8.size(); i++)
            {
                currentTournament.matches8.get(i).setTeam1(currentTournament.matches16.get(j).getWinningTeam());
                currentTournament.matches8.get(i).setTeam2(currentTournament.matches16.get(j+1).getWinningTeam());
                j += 2;
            }

            int l = 0;
            for (int i = 0; i < currentTournament.matches4.size(); i++)
            {
                currentTournament.matches4.get(i).setTeam1(currentTournament.matches8.get(l).getWinningTeam());
                currentTournament.matches4.get(i).setTeam2(currentTournament.matches8.get(l+1).getWinningTeam());
                l += 2;
            }

            int m = 0;
            for (int i = 0; i < currentTournament.matches2.size(); i++)
            {
                currentTournament.matches2.get(i).setTeam1(currentTournament.matches4.get(m).getWinningTeam());
                currentTournament.matches2.get(i).setTeam2(currentTournament.matches4.get(m+1).getWinningTeam());
                m += 2;
            }

            int n = 0;
            for (int i = 0; i < currentTournament.matches1.size(); i++)
            {
                currentTournament.matches1.get(i).setTeam1(currentTournament.matches2.get(n).getWinningTeam());
                currentTournament.matches1.get(i).setTeam2(currentTournament.matches2.get(n+1).getWinningTeam());
                n += 2;
            }
        }
        else if (currentTournament.getTournamentSize() == 16)
        {
            int j = 0;
            for (int i = 0; i < currentTournament.matches4.size(); i++)
            {
                currentTournament.matches4.get(i).setTeam1(currentTournament.matches8.get(j).getWinningTeam());
                currentTournament.matches4.get(i).setTeam2(currentTournament.matches8.get(j+1).getWinningTeam());
                j += 2;
            }

            int m = 0;
            for (int i = 0; i < currentTournament.matches2.size(); i++)
            {
                currentTournament.matches2.get(i).setTeam1(currentTournament.matches4.get(m).getWinningTeam());
                currentTournament.matches2.get(i).setTeam2(currentTournament.matches4.get(m+1).getWinningTeam());
                m += 2;
            }

            int n = 0;
            for (int i = 0; i < currentTournament.matches1.size(); i++)
            {
                currentTournament.matches1.get(i).setTeam1(currentTournament.matches2.get(n).getWinningTeam());
                currentTournament.matches1.get(i).setTeam2(currentTournament.matches2.get(n+1).getWinningTeam());
                n += 2;
            }
        }
        else if (currentTournament.getTournamentSize() == 8)
        {
            int j = 0;
            for (int i = 0; i < currentTournament.matches2.size(); i++)
            {
                currentTournament.matches2.get(i).setTeam1(currentTournament.matches4.get(j).getWinningTeam());
                currentTournament.matches2.get(i).setTeam2(currentTournament.matches4.get(j+1).getWinningTeam());
                j += 2;
            }

            int n = 0;
            for (int i = 0; i < currentTournament.matches1.size(); i++)
            {
                currentTournament.matches1.get(i).setTeam1(currentTournament.matches2.get(n).getWinningTeam());
                currentTournament.matches1.get(i).setTeam2(currentTournament.matches2.get(n+1).getWinningTeam());
                n += 2;
            }
        }
        else if (currentTournament.getTournamentSize() == 4)
        {
            int j = 0;
            for (int i = 0; i < currentTournament.matches1.size(); i++)
            {
                currentTournament.matches1.get(i).setTeam1(currentTournament.matches2.get(j).getWinningTeam());
                currentTournament.matches1.get(i).setTeam2(currentTournament.matches2.get(j+1).getWinningTeam());
                j += 2;
            }
        }
    }

    public void hideEditMatchPanel(ActionEvent actionEvent)
    {
        editMatchPanel.setDisable(true);
        editMatchPanel.setVisible(false);
        clearMatchEditFields();
    }

    private void clearMatchEditFields()
    {
        dateMatchFieldEdit.setValue(null);
        team1MatchFieldEdit.setValue(null);
        score1MatchFieldEdit.clear();
        team2MatchFieldEdit.setValue(null);
        score2MatchFieldEdit.clear();
    }

    private void setMatchTables()
    {
        table32.getItems().setAll(currentTournament.matches32);
        table16.getItems().setAll(currentTournament.matches16);
        table8.getItems().setAll(currentTournament.matches8);
        table4.getItems().setAll(currentTournament.matches4);
        table2.getItems().setAll(currentTournament.matches2);
        table1.getItems().setAll(currentTournament.matches1);
    }

    private void setTournamentGrid()
    {
        for (Tournament tournament : Tournament.allTournaments)
        {
            createTournamentFromDB(tournament);
        }
    }

    private void moveTournamentBtnFwd()
    {
        if (StageHandler.addTourBtnCurrentCol < 2)
        {
            StageHandler.addTourBtnCurrentCol++;
            tournamentGrid.add(addTournamentBtn, StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);
        }
        else
        {
            StageHandler.addTourBtnCurrentRow++;
            StageHandler.addTourBtnCurrentCol = 0;
            tournamentGrid.add(addTournamentBtn, StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);
        }
    }
    //endregion

    //region Getters
    public TableView getPlayersTable()
    {
        return playerTable;
    }
    public Tab getPlayersTab()
    {
        return playersTab;
    }
    public GridPane getTeamGrid()
    {
        return teamGrid;
    }
    public AnchorPane getTeamGridParent()
    {
        return teamGridParent;
    }
    public Button getAddTeamBtn()
    {
        return addTeamBtn;
    }
    public Button getEditPlayerBtn()
    {
        return editPlayerBtn;
    }
    public Button getDeletePlayerBtn()
    {
        return deletePlayerBtn;
    }
    public AnchorPane getTournamentGridParent()
    {
        return tournamentGridParent;
    }
    public GridPane getTournamentGrid()
    {
        return tournamentGrid;
    }
    public Button getAddTournamentBtn()
    {
        return addTournamentBtn;
    }
    //endregion

    //region Init
    private void handleComboBoxDisabling()
    {
        int selectedTab = tournamentTabPane.getSelectionModel().getSelectedIndex();

        if (currentTournament.getTournamentSize() == 64)
        {
            if (selectedTab > 0) //not tab32)
            {
                team1MatchFieldEdit.setDisable(true);
                team2MatchFieldEdit.setDisable(true);

                if (!checkRoundComplete(0))
                {
                    score1MatchFieldEdit.setDisable(true);
                    score2MatchFieldEdit.setDisable(true);
                }
                else
                {
                    score1MatchFieldEdit.setDisable(false);
                    score2MatchFieldEdit.setDisable(false);
                }
            }
            else
            {
                team1MatchFieldEdit.setDisable(false);
                team2MatchFieldEdit.setDisable(false);
                score1MatchFieldEdit.setDisable(false);
                score2MatchFieldEdit.setDisable(false);
            }
        }
        else if (currentTournament.getTournamentSize() == 32)
        {
            if (selectedTab > 1) //not tab16)
            {
                team1MatchFieldEdit.setDisable(true);
                team2MatchFieldEdit.setDisable(true);

                if (!checkRoundComplete(1))
                {
                    score1MatchFieldEdit.setDisable(true);
                    score2MatchFieldEdit.setDisable(true);
                }
                else
                {
                    score1MatchFieldEdit.setDisable(false);
                    score2MatchFieldEdit.setDisable(false);
                }
            }
            else
            {
                team1MatchFieldEdit.setDisable(false);
                team2MatchFieldEdit.setDisable(false);
                score1MatchFieldEdit.setDisable(false);
                score2MatchFieldEdit.setDisable(false);
            }
        }
        else if (currentTournament.getTournamentSize() == 16)
        {
            if (selectedTab > 2) //not tab8)
            {
                team1MatchFieldEdit.setDisable(true);
                team2MatchFieldEdit.setDisable(true);

                if (!checkRoundComplete(2))
                {
                    score1MatchFieldEdit.setDisable(true);
                    score2MatchFieldEdit.setDisable(true);
                }
                else
                {
                    score1MatchFieldEdit.setDisable(false);
                    score2MatchFieldEdit.setDisable(false);
                }
            }
            else
            {
                team1MatchFieldEdit.setDisable(false);
                team2MatchFieldEdit.setDisable(false);
                score1MatchFieldEdit.setDisable(false);
                score2MatchFieldEdit.setDisable(false);
            }
        }
        else if (currentTournament.getTournamentSize() == 8)
        {
            if (selectedTab > 3) //not tab4)
            {
                team1MatchFieldEdit.setDisable(true);
                team2MatchFieldEdit.setDisable(true);

                if (!checkRoundComplete(3))
                {
                    score1MatchFieldEdit.setDisable(true);
                    score2MatchFieldEdit.setDisable(true);
                }
                else
                {
                    score1MatchFieldEdit.setDisable(false);
                    score2MatchFieldEdit.setDisable(false);
                }
            }
            else
            {
                team1MatchFieldEdit.setDisable(false);
                team2MatchFieldEdit.setDisable(false);
                score1MatchFieldEdit.setDisable(false);
                score2MatchFieldEdit.setDisable(false);
            }
        }
        else if (currentTournament.getTournamentSize() == 4)
        {
            if (selectedTab > 4) //not tab2)
            {
                team1MatchFieldEdit.setDisable(true);
                team2MatchFieldEdit.setDisable(true);

                if (!checkRoundComplete(4))
                {
                    score1MatchFieldEdit.setDisable(true);
                    score2MatchFieldEdit.setDisable(true);
                }
                else
                {
                    score1MatchFieldEdit.setDisable(false);
                    score2MatchFieldEdit.setDisable(false);
                }
            }
            else
            {
                team1MatchFieldEdit.setDisable(false);
                team2MatchFieldEdit.setDisable(false);
                score1MatchFieldEdit.setDisable(false);
                score2MatchFieldEdit.setDisable(false);
            }
        }
        else if (currentTournament.getTournamentSize() == 2)
        {
            if (selectedTab > 5) //not tab1)
            {
                team1MatchFieldEdit.setDisable(true);
                team2MatchFieldEdit.setDisable(true);

                if (!checkRoundComplete(5))
                {
                    score1MatchFieldEdit.setDisable(true);
                    score2MatchFieldEdit.setDisable(true);
                }
                else
                {
                    score1MatchFieldEdit.setDisable(false);
                    score2MatchFieldEdit.setDisable(false);
                }
            }
            else
            {
                team1MatchFieldEdit.setDisable(false);
                team2MatchFieldEdit.setDisable(false);
                score1MatchFieldEdit.setDisable(false);
                score2MatchFieldEdit.setDisable(false);
            }
        }

    }

    private void tournamentTabListener()
    {
        tournamentTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>()
        {
            @Override
            public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab)
            {
                if (currentTournament != null)
                {
                    handleComboBoxDisabling();
                }
            }
        });
    }

    private void forceNumericScore()
    {
        score1MatchFieldEdit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    score1MatchFieldEdit.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        score2MatchFieldEdit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    score2MatchFieldEdit.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void centerAlignTables()
    {
        rankCol.setStyle("-fx-alignment: CENTER");
        nameCol.setStyle("-fx-alignment: CENTER");
        dateOfBirthCol.setStyle("-fx-alignment: CENTER");
        emailCol.setStyle("-fx-alignment: CENTER");

        matchDateCol1.setStyle("-fx-alignment: CENTER");
        team1Col1.setStyle("-fx-alignment: CENTER");
        score1Col1.setStyle("-fx-alignment: CENTER");
        team2Col1.setStyle("-fx-alignment: CENTER");
        score2Col1.setStyle("-fx-alignment: CENTER");
        winnerCol1.setStyle("-fx-alignment: CENTER");

        matchDateCol2.setStyle("-fx-alignment: CENTER");
        team1Col2.setStyle("-fx-alignment: CENTER");
        score1Col2.setStyle("-fx-alignment: CENTER");
        team2Col2.setStyle("-fx-alignment: CENTER");
        score2Col2.setStyle("-fx-alignment: CENTER");
        winnerCol2.setStyle("-fx-alignment: CENTER");

        matchDateCol4.setStyle("-fx-alignment: CENTER");
        team1Col4.setStyle("-fx-alignment: CENTER");
        score1Col4.setStyle("-fx-alignment: CENTER");
        team2Col4.setStyle("-fx-alignment: CENTER");
        score2Col4.setStyle("-fx-alignment: CENTER");
        winnerCol4.setStyle("-fx-alignment: CENTER");

        matchDateCol8.setStyle("-fx-alignment: CENTER");
        team1Col8.setStyle("-fx-alignment: CENTER");
        score1Col8.setStyle("-fx-alignment: CENTER");
        team2Col8.setStyle("-fx-alignment: CENTER");
        score2Col8.setStyle("-fx-alignment: CENTER");
        winnerCol8.setStyle("-fx-alignment: CENTER");

        matchDateCol16.setStyle("-fx-alignment: CENTER");
        team1Col16.setStyle("-fx-alignment: CENTER");
        score1Col16.setStyle("-fx-alignment: CENTER");
        team2Col16.setStyle("-fx-alignment: CENTER");
        score2Col16.setStyle("-fx-alignment: CENTER");
        winnerCol16.setStyle("-fx-alignment: CENTER");

        matchDateCol32.setStyle("-fx-alignment: CENTER");
        team1Col32.setStyle("-fx-alignment: CENTER");
        score1Col32.setStyle("-fx-alignment: CENTER");
        team2Col32.setStyle("-fx-alignment: CENTER");
        score2Col32.setStyle("-fx-alignment: CENTER");
        winnerCol32.setStyle("-fx-alignment: CENTER");
    }

    private void setupTableColumns()
    {
        rankCol.setCellValueFactory(new PropertyValueFactory<Player, Integer>("rank"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<Player, String>("dateOfBirth"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Player, String>("email"));

        matchDateCol1.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        team1Col1.setCellValueFactory(new PropertyValueFactory<Match, String>("team1Name"));
        score1Col1.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team1Score"));
        team2Col1.setCellValueFactory(new PropertyValueFactory<Match, String>("team2Name"));
        score2Col1.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team2Score"));
        winnerCol1.setCellValueFactory(new PropertyValueFactory<Match, String>("winner"));

        matchDateCol2.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        team1Col2.setCellValueFactory(new PropertyValueFactory<Match, String>("team1Name"));
        score1Col2.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team1Score"));
        team2Col2.setCellValueFactory(new PropertyValueFactory<Match, String>("team2Name"));
        score2Col2.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team2Score"));
        winnerCol2.setCellValueFactory(new PropertyValueFactory<Match, String>("winner"));

        matchDateCol4.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        team1Col4.setCellValueFactory(new PropertyValueFactory<Match, String>("team1Name"));
        score1Col4.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team1Score"));
        team2Col4.setCellValueFactory(new PropertyValueFactory<Match, String>("team2Name"));
        score2Col4.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team2Score"));
        winnerCol4.setCellValueFactory(new PropertyValueFactory<Match, String>("winner"));

        matchDateCol8.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        team1Col8.setCellValueFactory(new PropertyValueFactory<Match, String>("team1Name"));
        score1Col8.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team1Score"));
        team2Col8.setCellValueFactory(new PropertyValueFactory<Match, String>("team2Name"));
        score2Col8.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team2Score"));
        winnerCol8.setCellValueFactory(new PropertyValueFactory<Match, String>("winner"));

        matchDateCol16.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        team1Col16.setCellValueFactory(new PropertyValueFactory<Match, String>("team1Name"));
        score1Col16.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team1Score"));
        team2Col16.setCellValueFactory(new PropertyValueFactory<Match, String>("team2Name"));
        score2Col16.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team2Score"));
        winnerCol16.setCellValueFactory(new PropertyValueFactory<Match, String>("winner"));

        matchDateCol32.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        team1Col32.setCellValueFactory(new PropertyValueFactory<Match, String>("team1Name"));
        score1Col32.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team1Score"));
        team2Col32.setCellValueFactory(new PropertyValueFactory<Match, String>("team2Name"));
        score2Col32.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team2Score"));
        winnerCol32.setCellValueFactory(new PropertyValueFactory<Match, String>("winner"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        centerAlignTables();
        setupTableColumns();

        dbWrapper.getPlayerData();
        dbWrapper.getTeamData();

        forceNumericScore();
        tournamentTabListener();
        handleStageMovement();
        handlePlayerTableSelection();
        setPlayerTable();
        setTeamGrid();
        setTournamentGrid();
    }
    //endregion
}