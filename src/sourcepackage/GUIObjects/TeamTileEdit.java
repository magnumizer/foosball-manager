package sourcepackage.GUIObjects;//Magnus Svendsen DAT16i

import java.lang.*;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import sourcepackage.*;

public class TeamTileEdit extends TitledPane
{
    public static ArrayList<TeamTileEdit> allTeamTileEdits = new ArrayList<>();
    ObservableList<Player> players = FXCollections.observableArrayList(Player.allPlayers);

    DBWrapper dbWrapper = new DBWrapper();

    private Team team;
    private TeamTile teamTile;

    private AnchorPane teamGridParent;
    private GridPane teamGrid;
    private Button addTeamBtn;

    ComboBox<Player> choiceBox = new ComboBox<>(players);
    ComboBox<Player> choiceBox0 = new ComboBox<>(players);

    public TeamTileEdit(TeamTile teamTile, Controller controller)
    {
        AnchorPane anchorPane = new AnchorPane();
        Label labelName = new Label();
        TextField textField = new TextField();
        Label labelPlayer1 = new Label();
        Label labelPlayer2 = new Label();
        Button confirmBtn = new Button();
        Button deleteBtn = new Button();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem();

        String css = getClass().getResource("combo.css").toExternalForm();

        this.teamTile = teamTile;
        this.team = teamTile.getTeam();

        this.teamGridParent = controller.getTeamGridParent();
        this.teamGrid = controller.getTeamGrid();
        this.addTeamBtn = controller.getAddTeamBtn();

        //getStylesheets().add(css);
        setId("TitledPane");
        setAlignment(Pos.CENTER);
        setAnimated(false);
        setCollapsible(false);
        setText(team.getTeamName());
        setTextAlignment(TextAlignment.CENTER);
        setFont(Font.font("Courier New Bold", 26));
        setPrefHeight(220);

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        labelName.setAlignment(Pos.CENTER);
        labelName.setPrefHeight(17.0);
        labelName.setPrefWidth(309.0);
        labelName.setText("Team name");

        textField.setAlignment(Pos.CENTER);
        textField.setLayoutX(28.5);
        textField.setLayoutY(16.0);
        textField.setMaxHeight(USE_PREF_SIZE);
        textField.setMaxWidth(USE_PREF_SIZE);
        textField.setMinHeight(USE_PREF_SIZE);
        textField.setMinWidth(USE_PREF_SIZE);
        textField.setPrefHeight(19.0);
        textField.setPrefWidth(251.0);
        textField.setText(team.getTeamName());
        textField.setFont(new Font(11.0));


        labelPlayer1.setAlignment(Pos.CENTER);
        labelPlayer1.setLayoutY(40.0);
        labelPlayer1.setPrefHeight(17.0);
        labelPlayer1.setPrefWidth(309.0);
        labelPlayer1.setText("Select Player 1");

        choiceBox.getStylesheets().add(css);
        choiceBox.setLayoutX(28.5);
        choiceBox.setLayoutY(56.0);
        choiceBox.setMaxHeight(USE_PREF_SIZE);
        choiceBox.setMaxWidth(USE_PREF_SIZE);
        choiceBox.setMinHeight(USE_PREF_SIZE);
        choiceBox.setMinWidth(USE_PREF_SIZE);
        choiceBox.setPrefHeight(19.0);
        choiceBox.setPrefWidth(251.0);
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>()
        {

            @Override
            public void changed(ObservableValue<? extends Player> arg0, Player arg1, Player arg2)
            {
                if (arg2 != null)
                {
                    //team.setPlayer1Btn(arg2);
                    choiceBox.setPromptText(arg2.getName());
                    choiceBox.setValue(arg2);
                }
            }
        });

        labelPlayer2.setAlignment(Pos.CENTER);
        labelPlayer2.setLayoutY(80.0);
        labelPlayer2.setPrefHeight(17.0);
        labelPlayer2.setPrefWidth(309.0);
        labelPlayer2.setText("Select Player 2");

        choiceBox0.getStylesheets().add(css);
        choiceBox0.setLayoutX(28.5);
        choiceBox0.setLayoutY(96.0);
        choiceBox0.setMaxHeight(USE_PREF_SIZE);
        choiceBox0.setMaxWidth(USE_PREF_SIZE);
        choiceBox0.setMinHeight(USE_PREF_SIZE);
        choiceBox0.setMinWidth(USE_PREF_SIZE);
        choiceBox0.setPrefHeight(19.0);
        choiceBox0.setPrefWidth(251.0);
        choiceBox0.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>()
        {

            @Override
            public void changed(ObservableValue<? extends Player> arg0, Player arg1, Player arg2)
            {
                if (arg2 != null)
                {
                    //team.setPlayer2Btn(arg2);
                    choiceBox0.setPromptText(arg2.getName());
                    choiceBox0.setValue(arg2);
                }
            }
        });

        confirmBtn.setAlignment(Pos.CENTER);
        confirmBtn.setContentDisplay(ContentDisplay.CENTER);
        confirmBtn.setLayoutX(65.0);
        confirmBtn.setLayoutY(125.0);
        confirmBtn.setMaxHeight(USE_PREF_SIZE);
        confirmBtn.setMaxWidth(USE_PREF_SIZE);
        confirmBtn.setMinHeight(USE_PREF_SIZE);
        confirmBtn.setMinWidth(USE_PREF_SIZE);
        confirmBtn.setMnemonicParsing(false);
        confirmBtn.setPrefHeight(30.0);
        confirmBtn.setPrefWidth(75.0);
        confirmBtn.setStyle("-fx-background-color: green;");
        confirmBtn.setText("Confirm");
        confirmBtn.setTextAlignment(TextAlignment.CENTER);
        confirmBtn.setTextFill(Color.WHITE);
        confirmBtn.setTextOverrun(OverrunStyle.CLIP);
        confirmBtn.setFont(new Font(11.0));
        confirmBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (!textField.getText().equals(""))
                {
                    if (choiceBox.getValue() != null)
                    {
                        if (choiceBox0.getValue() != null)
                        {
                            team.setPlayer1(choiceBox.getValue());
                            team.setPlayer2(choiceBox0.getValue());
                            team.setTeamName(textField.getText());
                            setText(team.getTeamName());
                            teamTile.setTeamName(team.getTeamName());
                            teamTile.setPlayer1(choiceBox.getValue());
                            teamTile.setPlayer2(choiceBox0.getValue());
                            teamTile.setFont(Font.font("Courier New Bold", 26));
                            dbWrapper.updateTeamTable(team);
                            teamTile.setEditMode(false);
                            setEditMode(false);
                        }
                        else
                        {
                            StageHandler.displayAlert("Player 2 not specified", "Player 2 missing!", "Please select a Player 2");
                        }
                    }
                    else
                    {
                        StageHandler.displayAlert("Player 1 not specified", "Player 1 missing!", "Please select a Player 1");
                    }
                }
                else
                {
                    StageHandler.displayAlert("Team name not specified", "Team name missing!", "Please type a name for the Team");
                }
            }
        });

        deleteBtn.setAlignment(Pos.CENTER);
        deleteBtn.setContentDisplay(ContentDisplay.CENTER);
        deleteBtn.setLayoutX(167.0);
        deleteBtn.setLayoutY(125.0);
        deleteBtn.setMaxHeight(USE_PREF_SIZE);
        deleteBtn.setMaxWidth(USE_PREF_SIZE);
        deleteBtn.setMinHeight(USE_PREF_SIZE);
        deleteBtn.setMinWidth(USE_PREF_SIZE);
        deleteBtn.setMnemonicParsing(false);
        deleteBtn.setPrefHeight(30.0);
        deleteBtn.setPrefWidth(75.0);
        deleteBtn.setStyle("-fx-background-color: red;");
        deleteBtn.setText("Delete");
        deleteBtn.setTextAlignment(TextAlignment.CENTER);
        deleteBtn.setTextFill(Color.WHITE);
        deleteBtn.setTextOverrun(OverrunStyle.CLIP);
        deleteBtn.setFont(new Font(11.0));
        deleteBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                deleteTeam();
            }
        });


        menuItem.setMnemonicParsing(false);
        //menuItem.setOnAction(this::deleteTeam);
        menuItem.setText("Delete");
        setContextMenu(contextMenu);
        setFont(new Font("Courier New Bold", 26.0));

        anchorPane.getChildren().add(labelName);
        anchorPane.getChildren().add(textField);
        anchorPane.getChildren().add(labelPlayer1);
        anchorPane.getChildren().add(choiceBox);
        anchorPane.getChildren().add(labelPlayer2);
        anchorPane.getChildren().add(choiceBox0);
        anchorPane.getChildren().add(confirmBtn);
        anchorPane.getChildren().add(deleteBtn);
        contextMenu.getItems().add(menuItem);

        setContent(anchorPane);
    }

    public void setEditMode(boolean editMode)
    {
        if (editMode)
        {
            this.setVisible(true);
            this.setDisabled(false);
        }
        else
        {
            this.setVisible(false);
            this.setDisabled(true);
        }
    }

    public void setTeamTile(Team _team)
    {
        Player[] players = team.getPlayers();
        team.setPlayer1(players[0]);
        team.setPlayer2(players[1]);
        team.setTeamName(_team.getTeamName());
        setText(_team.getTeamName());
        choiceBox.setValue(players[0]);
        choiceBox0.setValue(players[1]);
        teamTile.setTeamName(_team.getTeamName());
        teamTile.setPlayer1(players[0]);
        teamTile.setPlayer2(players[1]);
        teamTile.setFont(Font.font("Courier New Bold", 26));
        teamTile.setEditMode(false);
        setEditMode(false);
    }

    public void updatePlayerList()
    {
        players = FXCollections.observableArrayList(Player.allPlayers);
        choiceBox.setItems(players);
        choiceBox0.setItems(players);
    }

    public void deleteTeam()
    {
        String _tournamentName = null;
        boolean isInTournament = false;

        for (Tournament tournament : Tournament.allTournaments)
        {
            switch (tournament.getTournamentSize())
            {
                case 64:
                    for (Match match : tournament.matches32)
                    {
                        Team[] teams = match.getTeams();
                        if (team == teams[0] || team == teams[1])
                        {
                            _tournamentName = tournament.getTournamentName();
                            isInTournament = true;
                            break;
                        }
                    }
                    break;
                case 32:
                    for (Match match : tournament.matches16)
                    {
                        Team[] teams = match.getTeams();
                        if (team == teams[0] || team == teams[1])
                        {
                            _tournamentName = tournament.getTournamentName();
                            isInTournament = true;
                            break;
                        }
                    }
                    break;
                case 16:
                    for (Match match : tournament.matches8)
                    {
                        Team[] teams = match.getTeams();
                        if (team == teams[0] || team == teams[1])
                        {
                            _tournamentName = tournament.getTournamentName();
                            isInTournament = true;
                            break;
                        }
                    }
                    break;
                case 8:
                    for (Match match : tournament.matches4)
                    {
                        Team[] teams = match.getTeams();
                        if (team == teams[0] || team == teams[1])
                        {
                            _tournamentName = tournament.getTournamentName();
                            isInTournament = true;
                            break;
                        }
                    }
                    break;
                case 4:
                    for (Match match : tournament.matches2)
                    {
                        Team[] teams = match.getTeams();
                        if (team == teams[0] || team == teams[1])
                        {
                            _tournamentName = tournament.getTournamentName();
                            isInTournament = true;
                            break;
                        }
                    }
                    break;
                case 2:
                    for (Match match : tournament.matches1)
                    {
                        Team[] teams = match.getTeams();
                        if (team == teams[0] || team == teams[1])
                        {
                            _tournamentName = tournament.getTournamentName();
                            isInTournament = true;
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        Alert alert = null;

        if (isInTournament)
        {
            alert = new Alert(Alert.AlertType.CONFIRMATION, team.getTeamName() + " is in " + _tournamentName + "\nAre you sure you want to delete?", ButtonType.YES, ButtonType.CANCEL);
        }
        else
        {
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete\n" + team.getTeamName() + " ?", ButtonType.YES, ButtonType.CANCEL);
        }

        alert.setHeaderText("Confirm deletion");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            dbWrapper.deleteTeam(team);
            Team.allTeams.remove(team);
            Team.teamCount--;

            //clear gridpane, but preserve grid lines
            Node node = teamGrid.getChildren().get(0);
            teamGrid.getChildren().clear();
            teamGrid.add(node,0,0);

            //remove current teamtiles
            TeamTile.allTeamTiles.remove(teamTile);
            TeamTileEdit.allTeamTileEdits.remove(this);

            //re-add all teamtiles
            StageHandler.addTeamBtnCurrentCol = 0;
            StageHandler.addTeamBtnCurrentRow = 0;

            for (int i = 0; i < TeamTile.allTeamTiles.size(); i++)
            {
                teamGrid.add(TeamTile.allTeamTiles.get(i), StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);
                teamGrid.add(TeamTileEdit.allTeamTileEdits.get(i), StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);

                if (StageHandler.addTeamBtnCurrentCol < 2)
                {
                    StageHandler.addTeamBtnCurrentCol++;
                }
                else
                {
                    StageHandler.addTeamBtnCurrentRow++;
                    StageHandler.addTeamBtnCurrentCol = 0;
                }
            }
            //re-add addTeamBtn
            teamGrid.add(addTeamBtn, StageHandler.addTeamBtnCurrentCol, StageHandler.addTeamBtnCurrentRow);

            //re-size scrollpane
            if (StageHandler.addTeamBtnCurrentCol == 0 && StageHandler.addTeamBtnCurrentRow > 0)
            {
                teamGridParent.setPrefHeight(teamGridParent.getPrefHeight() - 250);
            }
        }
    }
}