package sourcepackage.GUIObjects;//Magnus Svendsen DAT16i

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import java.lang.*;
import java.util.*;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import sourcepackage.Controller;
import sourcepackage.DBWrapper;
import sourcepackage.StageHandler;
import sourcepackage.Tournament;

public class TournamentTile extends Pane
{
    public static ArrayList<TournamentTile> allTournamentTiles = new ArrayList<>();
    DBWrapper dbWrapper = new DBWrapper();

    Tournament tournament;
    TournamentTileEdit tournamentTileEdit;
    AnchorPane tournamentGridParent;
    GridPane tournamentGrid;
    Button addTournamentBtn;

    TitledPane titledPane;
    Label startDateText;
    Label lastGameText;
    Label currentStageText;
    Label winnerText;

    public TournamentTile(Tournament tournament, Controller controller)
    {
        titledPane = new TitledPane();
        AnchorPane anchorPane = new AnchorPane();
        Line line = new Line();
        Label label = new Label();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label5 = new Label();
        Button deleteBtn = new Button();

        startDateText = new Label();
        lastGameText = new Label();
        currentStageText = new Label();
        winnerText = new Label();


        this.tournament = tournament;
        this.tournamentGridParent = controller.getTournamentGridParent();
        this.tournamentGrid = controller.getTournamentGrid();
        this.addTournamentBtn = controller.getAddTournamentBtn();


        setId("Pane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(220.0);
        setPrefWidth(309.0);

        titledPane.setAlignment(javafx.geometry.Pos.CENTER);
        titledPane.setAnimated(false);
        titledPane.setCollapsible(false);
        titledPane.setMaxHeight(USE_PREF_SIZE);
        titledPane.setMaxWidth(USE_PREF_SIZE);
        titledPane.setMinHeight(USE_PREF_SIZE);
        titledPane.setMinWidth(USE_PREF_SIZE);
        titledPane.setPrefHeight(220.0);
        titledPane.setPrefWidth(309.0);
        titledPane.setText("Tournament 1");
        titledPane.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titledPane.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                controller.showBracket(tournament);
            }
        });

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        line.setEndX(153.0);
        line.setEndY(100.0);
        line.setStartX(153.0);
        line.setStartY(30.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutY(20.0);
        label.setMaxHeight(USE_PREF_SIZE);
        label.setMaxWidth(USE_PREF_SIZE);
        label.setMinHeight(USE_PREF_SIZE);
        label.setMinWidth(USE_PREF_SIZE);
        label.setPrefHeight(29.0);
        label.setPrefWidth(153.0);
        label.setText("Start date");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font("Marlett", 15.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutY(49.0);
        label0.setMaxHeight(USE_PREF_SIZE);
        label0.setMaxWidth(USE_PREF_SIZE);
        label0.setMinHeight(USE_PREF_SIZE);
        label0.setMinWidth(USE_PREF_SIZE);
        label0.setPrefHeight(29.0);
        label0.setPrefWidth(153.0);
        label0.setText("Last game at");
        label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label0.setFont(new Font("Marlett", 15.0));

        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setLayoutY(78.0);
        label1.setMaxHeight(USE_PREF_SIZE);
        label1.setMaxWidth(USE_PREF_SIZE);
        label1.setMinHeight(USE_PREF_SIZE);
        label1.setMinWidth(USE_PREF_SIZE);
        label1.setPrefHeight(29.0);
        label1.setPrefWidth(153.0);
        label1.setText("Current stage");
        label1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label1.setFont(new Font("Marlett", 15.0));

        startDateText.setAlignment(javafx.geometry.Pos.CENTER);
        startDateText.setLayoutX(153.0);
        startDateText.setLayoutY(20.0);
        startDateText.setMaxHeight(USE_PREF_SIZE);
        startDateText.setMaxWidth(USE_PREF_SIZE);
        startDateText.setMinHeight(USE_PREF_SIZE);
        startDateText.setMinWidth(USE_PREF_SIZE);
        startDateText.setPrefHeight(29.0);
        startDateText.setPrefWidth(153.0);
        startDateText.setText("02/02/2017");
        startDateText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        startDateText.setFont(new Font("Marlett", 15.0));

        lastGameText.setAlignment(javafx.geometry.Pos.CENTER);
        lastGameText.setLayoutX(153.0);
        lastGameText.setLayoutY(49.0);
        lastGameText.setMaxHeight(USE_PREF_SIZE);
        lastGameText.setMaxWidth(USE_PREF_SIZE);
        lastGameText.setMinHeight(USE_PREF_SIZE);
        lastGameText.setMinWidth(USE_PREF_SIZE);
        lastGameText.setPrefHeight(29.0);
        lastGameText.setPrefWidth(153.0);
        lastGameText.setText("02/02/2017");
        lastGameText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        lastGameText.setFont(new Font("Marlett", 15.0));

        currentStageText.setAlignment(javafx.geometry.Pos.CENTER);
        currentStageText.setLayoutX(153.0);
        currentStageText.setLayoutY(78.0);
        currentStageText.setMaxHeight(USE_PREF_SIZE);
        currentStageText.setMaxWidth(USE_PREF_SIZE);
        currentStageText.setMinHeight(USE_PREF_SIZE);
        currentStageText.setMinWidth(USE_PREF_SIZE);
        currentStageText.setPrefHeight(29.0);
        currentStageText.setPrefWidth(153.0);
        currentStageText.setText("Quarterfinal");
        currentStageText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        currentStageText.setFont(new Font("Marlett", 15.0));

        label5.setAlignment(javafx.geometry.Pos.CENTER);
        label5.setLayoutY(120.0);
        label5.setMaxHeight(USE_PREF_SIZE);
        label5.setMaxWidth(USE_PREF_SIZE);
        label5.setMinHeight(USE_PREF_SIZE);
        label5.setMinWidth(USE_PREF_SIZE);
        label5.setPrefHeight(29.0);
        label5.setPrefWidth(153.0);
        label5.setText("Winner");
        label5.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label5.setFont(new Font("Marlett", 20.0));

        winnerText.setAlignment(javafx.geometry.Pos.CENTER);
        winnerText.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        winnerText.setLayoutX(153.0);
        winnerText.setLayoutY(120.0);
        winnerText.setMaxHeight(USE_PREF_SIZE);
        winnerText.setMaxWidth(USE_PREF_SIZE);
        winnerText.setMinHeight(USE_PREF_SIZE);
        winnerText.setMinWidth(USE_PREF_SIZE);
        winnerText.setPrefHeight(29.0);
        winnerText.setPrefWidth(153.0);
        winnerText.setText("TBD");
        winnerText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        winnerText.setUnderline(true);
        winnerText.setFont(new Font("Meiryo UI", 22.0));
        titledPane.setContent(anchorPane);
        titledPane.setFont(new Font("Courier New Bold", 26.0));


        deleteBtn.setAlignment(javafx.geometry.Pos.CENTER);
        deleteBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        deleteBtn.setMaxHeight(USE_PREF_SIZE);
        deleteBtn.setMaxWidth(USE_PREF_SIZE);
        deleteBtn.setMinHeight(USE_PREF_SIZE);
        deleteBtn.setMinWidth(USE_PREF_SIZE);
        deleteBtn.setMnemonicParsing(false);
        deleteBtn.setPrefHeight(50.0);
        deleteBtn.setPrefWidth(50.0);
        deleteBtn.setText("X");
        deleteBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        deleteBtn.setTextFill(javafx.scene.paint.Color.RED);
        deleteBtn.setTranslateX(260.0);
        deleteBtn.setWrapText(true);
        deleteBtn.setFont(new Font("Microsoft JhengHei", 24.0));
        deleteBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                deleteTournament();
            }
        });

        anchorPane.getChildren().add(line);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(startDateText);
        anchorPane.getChildren().add(lastGameText);
        anchorPane.getChildren().add(currentStageText);
        anchorPane.getChildren().add(label5);
        anchorPane.getChildren().add(winnerText);

        getChildren().add(titledPane);
        getChildren().add(deleteBtn);
    }

    public Tournament getTournament()
    {
        return this.tournament;
    }

    public void setTournamentTileEdit(TournamentTileEdit tournamentTileEdit)
    {
        this.tournamentTileEdit = tournamentTileEdit;
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

    public void deleteTournament()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete\n" + tournament.getTournamentName() + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("Confirm deletion");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            Tournament.allTournaments.remove(tournament);
            Tournament.tournamentCount--;

            //clear gridpane, but preserve grid lines
            Node node = tournamentGrid.getChildren().get(0);
            tournamentGrid.getChildren().clear();
            tournamentGrid.add(node,0,0);

            //remove current tournamenttile
            TournamentTile.allTournamentTiles.remove(this);
            TournamentTileEdit.allTournamentTileEdits.remove(tournamentTileEdit);

            //re-add all tournamenttiles
            StageHandler.addTourBtnCurrentCol = 0;
            StageHandler.addTourBtnCurrentRow = 0;

            for (int i = 0; i < TournamentTile.allTournamentTiles.size(); i++)
            {
                tournamentGrid.add(TournamentTile.allTournamentTiles.get(i), StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);
                tournamentGrid.add(TournamentTileEdit.allTournamentTileEdits.get(i), StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);

                if (StageHandler.addTourBtnCurrentCol < 2)
                {
                    StageHandler.addTourBtnCurrentCol++;
                }
                else
                {
                    StageHandler.addTourBtnCurrentRow++;
                    StageHandler.addTourBtnCurrentCol = 0;
                }
            }
            //re-add addTournamentBtn
            tournamentGrid.add(addTournamentBtn, StageHandler.addTourBtnCurrentCol, StageHandler.addTourBtnCurrentRow);

            //re-size scrollpane
            if (StageHandler.addTourBtnCurrentCol == 0 && StageHandler.addTourBtnCurrentRow > 0)
            {
                tournamentGridParent.setPrefHeight(tournamentGridParent.getPrefHeight() - 250);
            }
        }
    }

    public void setTournamentName(String tournamentName)
    {
        titledPane.setText(tournamentName);
    }

    public void setStartDateText(String startDate)
    {
        startDateText.setText(startDate);
    }

    public void setLastGameText(String lastGame)
    {
        lastGameText.setText(lastGame);
    }

    public void setCurrentStageText(String currentStage)
    {
        currentStageText.setText(currentStage);
    }

    public void setWinnerText(String teamWinner)
    {
        winnerText.setText(teamWinner);
    }
}
