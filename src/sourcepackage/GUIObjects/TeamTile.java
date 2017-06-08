package sourcepackage.GUIObjects;//Magnus Svendsen DAT16i


import java.lang.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sourcepackage.Controller;
import sourcepackage.Player;
import sourcepackage.Team;


public class TeamTile extends TitledPane
{
    public static ArrayList<TeamTile> allTeamTiles = new ArrayList<>();

    private Team team;
    private TeamTileEdit teamTileEdit;

    Button player1Btn = new Button();
    Button player2Btn = new Button();

    Player player1;
    Player player2;

    Tab playersTab;
    TableView playerTable;
    Button editBtn;
    Button delBtn;

    public TeamTile(Team team, Controller controller)
    {
        AnchorPane anchorPane = new AnchorPane();

        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem();

        this.team = team;
        this.playersTab = controller.getPlayersTab();
        this.playerTable = controller.getPlayersTable();
        this.editBtn = controller.getEditPlayerBtn();
        this.delBtn = controller.getDeletePlayerBtn();

        setId("TitledPane");
        setAlignment(Pos.CENTER);
        setAnimated(false);
        setCollapsible(false);
        setText(team.getTeamName());
        setTextAlignment(TextAlignment.CENTER);
        setFont(Font.font("Courier New Bold", 26));
        setPrefHeight(220);

        setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setEditMode(true);
                teamTileEdit.setEditMode(true);
            }
        });

        menuItem.setMnemonicParsing(false);
        //menuItem.setOnAction();
        menuItem.setText("Delete");

        contextMenu.getItems().add(menuItem);
        setContextMenu(contextMenu);

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        player1Btn.setAlignment(javafx.geometry.Pos.CENTER);
        player1Btn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        player1Btn.setLayoutX(91.0);
        player1Btn.setLayoutY(33.0);
        player1Btn.setMaxHeight(USE_PREF_SIZE);
        player1Btn.setMaxWidth(USE_PREF_SIZE);
        player1Btn.setMinHeight(USE_PREF_SIZE);
        player1Btn.setMinWidth(USE_PREF_SIZE);
        player1Btn.setMnemonicParsing(false);
        player1Btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                jumpToPlayer(player1);
            }
        });
        player1Btn.setPrefWidth(300.0);
        player1Btn.setText("Player 1");
        player1Btn.setTranslateX(-87.0);

        player2Btn.setAlignment(javafx.geometry.Pos.CENTER);
        player2Btn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        player2Btn.setLayoutX(91.0);
        player2Btn.setLayoutY(33.0);
        player2Btn.setMaxHeight(USE_PREF_SIZE);
        player2Btn.setMaxWidth(USE_PREF_SIZE);
        player2Btn.setMinHeight(USE_PREF_SIZE);
        player2Btn.setMinWidth(USE_PREF_SIZE);
        player2Btn.setMnemonicParsing(false);
        player2Btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                jumpToPlayer(player2);
            }
        });
        player2Btn.setPrefWidth(300.0);
        player2Btn.setText("Player 2");
        player2Btn.setTranslateX(-87.0);
        player2Btn.setTranslateY(50.0);

        anchorPane.getChildren().add(player1Btn);
        anchorPane.getChildren().add(player2Btn);

        setContent(anchorPane);
    }

    public Team getTeam()
    {
        return this.team;
    }

    public void setTeamTileEdit(TeamTileEdit teamTileEdit)
    {
        this.teamTileEdit = teamTileEdit;
    }

    public void setEditMode(boolean editMode)
    {
        if (editMode)
        {
            this.setVisible(false);
            this.setDisabled(true);
        }
        else
        {
            this.setVisible(true);
            this.setDisabled(false);
        }
    }

    public void setTeamName(String teamName)
    {
        setText(teamName);
    }

    public void setPlayer1(Player player)
    {
        player1 = player;
        player1Btn.setText(player.getName());
    }

    public void setPlayer2(Player player)
    {
        player2 = player;
        player2Btn.setText(player.getName());
    }

    private void jumpToPlayer(Player player)
    {
        playersTab.getTabPane().getSelectionModel().select(0);
        playerTable.getSelectionModel().select(player);
        editBtn.setDisable(false);
        delBtn.setDisable(false);
        playerTable.scrollTo(player);
    }
}
