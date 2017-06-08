package sourcepackage.GUIObjects;//Magnus Svendsen DAT16i


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.lang.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import sourcepackage.*;

public class TournamentTileEdit extends Pane
{
    public static ArrayList<TournamentTileEdit> allTournamentTileEdits = new ArrayList<>();

    Tournament tournament;
    TournamentTile tournamentTile;

    AnchorPane tournamentGridParent;
    GridPane tournamentGrid;
    Button addTournamentBtn;

    TitledPane titledPane;
    AnchorPane anchorPane;
    Label label;
    TextField textField;
    Label label0;
    DatePicker datePicker;
    Label label1;
    Label label2;

    public TournamentTileEdit(TournamentTile tournamentTile, Controller controller)
    {

        titledPane = new TitledPane();
        anchorPane = new AnchorPane();
        label = new Label();
        textField = new TextField();
        label0 = new Label();
        datePicker = new DatePicker();
        label1 = new Label();
        ChoiceBox<Integer> choiceBox = new ChoiceBox(FXCollections.observableArrayList(
                2, 4, 8, 16, 32, 64)
        );
        label2 = new Label();
        Button confirmBtn = new Button();
        Button deleteBtn = new Button();

        String css = getClass().getResource("tournedit.css").toExternalForm();

        this.tournamentTile = tournamentTile;
        this.tournament = tournamentTile.getTournament();

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
        titledPane.setText("New Tournament");
        titledPane.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setMaxHeight(USE_PREF_SIZE);
        label.setMaxWidth(USE_PREF_SIZE);
        label.setMinHeight(USE_PREF_SIZE);
        label.setMinWidth(USE_PREF_SIZE);
        label.setPrefHeight(17.0);
        label.setPrefWidth(309.0);
        label.setText("Tournament name");

        textField.setAlignment(javafx.geometry.Pos.CENTER);
        textField.setLayoutX(28.5);
        textField.setLayoutY(16.0);
        textField.setMaxHeight(USE_PREF_SIZE);
        textField.setMaxWidth(USE_PREF_SIZE);
        textField.setMinHeight(USE_PREF_SIZE);
        textField.setMinWidth(USE_PREF_SIZE);
        textField.setPrefHeight(19.0);
        textField.setPrefWidth(251.0);
        textField.setText("Tournament " + Tournament.tournamentCount);
        textField.setFont(new Font(11.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label0.setLayoutY(40.0);
        label0.setMaxHeight(USE_PREF_SIZE);
        label0.setMaxWidth(USE_PREF_SIZE);
        label0.setMinHeight(USE_PREF_SIZE);
        label0.setMinWidth(USE_PREF_SIZE);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(309.0);
        label0.setText("Select a start date");

        datePicker.getStylesheets().add(css);
        datePicker.setLayoutX(28.5);
        datePicker.setLayoutY(56.0);
        datePicker.setMaxHeight(USE_PREF_SIZE);
        datePicker.setMaxWidth(USE_PREF_SIZE);
        datePicker.setMinHeight(USE_PREF_SIZE);
        datePicker.setMinWidth(USE_PREF_SIZE);
        datePicker.setPrefHeight(19.0);
        datePicker.setPrefWidth(251.0);

        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label1.setLayoutY(79.0);
        label1.setMaxHeight(USE_PREF_SIZE);
        label1.setMaxWidth(USE_PREF_SIZE);
        label1.setMinHeight(USE_PREF_SIZE);
        label1.setMinWidth(USE_PREF_SIZE);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(309.0);
        label1.setText("Tournament size");

        choiceBox.getStylesheets().add(css);
        choiceBox.setLayoutX(100.0);
        choiceBox.setLayoutY(96.0);
        choiceBox.setMaxHeight(USE_PREF_SIZE);
        choiceBox.setMaxWidth(USE_PREF_SIZE);
        choiceBox.setMinHeight(USE_PREF_SIZE);
        choiceBox.setMinWidth(USE_PREF_SIZE);
        choiceBox.setPrefHeight(22.0);
        choiceBox.setPrefWidth(60.0);

        label2.setAlignment(javafx.geometry.Pos.CENTER);
        label2.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label2.setLayoutX(160.0);
        label2.setLayoutY(99.0);
        label2.setMaxHeight(USE_PREF_SIZE);
        label2.setMaxWidth(USE_PREF_SIZE);
        label2.setMinHeight(USE_PREF_SIZE);
        label2.setMinWidth(USE_PREF_SIZE);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(40.0);
        label2.setText("teams");

        titledPane.setContent(anchorPane);
        titledPane.setFont(new Font("Courier New Bold", 26.0));

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
                    if (datePicker.getValue() != null)
                    {
                        if (choiceBox.getValue() != null)
                        {
                            tournament.setTournamentName(textField.getText());
                            tournament.setStartDate(datePicker.getValue().toString());
                            tournament.setTournamentSize(choiceBox.getValue());
                            titledPane.setText(tournament.getTournamentName());
                            tournamentTile.setTournamentName(tournament.getTournamentName());
                            tournamentTile.setStartDateText(tournament.getStartDate());
                            tournamentTile.setLastGameText("N/A");

                            switch (tournament.getTournamentSize())
                            {
                                case 2:
                                    tournamentTile.setCurrentStageText("Final");
                                    tournament.setCurrentStage(1);
                                    break;
                                case 4:
                                    tournamentTile.setCurrentStageText("Semi-final");
                                    tournament.setCurrentStage(2);
                                    break;
                                case 8:
                                    tournamentTile.setCurrentStageText("Quarterfinal");
                                    tournament.setCurrentStage(4);
                                    break;
                                case 16:
                                    tournamentTile.setCurrentStageText("Round of 8");
                                    tournament.setCurrentStage(8);
                                    break;
                                case 32:
                                    tournamentTile.setCurrentStageText("Round of 16");
                                    tournament.setCurrentStage(16);
                                    break;
                                case 64:
                                    tournamentTile.setCurrentStageText("Round of 32");
                                    tournament.setCurrentStage(32);
                                    break;
                                default:
                                    break;
                            }
                            generateMatches();
                            tournamentTile.setEditMode(false);
                            setEditMode(false);
                        }
                        else
                        {
                            StageHandler.displayAlert("Tournament size not specified", "Tournament size missing!", "Please select a Tournament size");
                        }
                    }
                    else
                    {
                        StageHandler.displayAlert("Start date not specified", "Start date missing!", "Please select a start date");
                    }

                }
                else
                {
                    StageHandler.displayAlert("Tournament name not specified", "Tournament name missing!", "Please type a name for the Tournament");
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
        deleteBtn.setText("Cancel");
        deleteBtn.setTextAlignment(TextAlignment.CENTER);
        deleteBtn.setTextFill(Color.WHITE);
        deleteBtn.setTextOverrun(OverrunStyle.CLIP);
        deleteBtn.setFont(new Font(11.0));
        deleteBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                deleteTournament();
            }
        });

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(textField);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(datePicker);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(choiceBox);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(confirmBtn);
        anchorPane.getChildren().add(deleteBtn);

        getChildren().add(titledPane);
    }

    private void generateMatches()
    {
        for (int i = 0; i < 32; i++)
        {
            tournament.matches32.add(new Match("", Team.allTeams.get(0), 0, Team.allTeams.get(0), 0));
        }
        for (int i = 0; i < 16; i++)
        {
            tournament.matches16.add(new Match("", Team.allTeams.get(0), 0, Team.allTeams.get(0), 0));
        }
        for (int i = 0; i < 8; i++)
        {
            tournament.matches8.add(new Match("", Team.allTeams.get(0), 0, Team.allTeams.get(0), 0));
        }
        for (int i = 0; i < 4; i++)
        {
            tournament.matches4.add(new Match("", Team.allTeams.get(0), 0, Team.allTeams.get(0), 0));
        }
        for (int i = 0; i < 2; i++)
        {
            tournament.matches2.add(new Match("", Team.allTeams.get(0), 0, Team.allTeams.get(0), 0));
        }

        tournament.matches1.add(new Match("", Team.allTeams.get(0), 0, Team.allTeams.get(0), 0));
    }

    private void setEditMode(boolean editMode)
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

    public void setTournamentTile(Tournament _tournament)
    {
        tournament.setTournamentName(_tournament.getTournamentName());
        tournament.setStartDate(_tournament.getStartDate());
        tournament.setTournamentSize(_tournament.getTournamentSize());
        titledPane.setText(_tournament.getTournamentName());
        tournamentTile.setTournamentName(_tournament.getTournamentName());
        tournamentTile.setStartDateText(_tournament.getStartDate());
        tournamentTile.setLastGameText("N/A");
        tournamentTile.setCurrentStageText("Round of " + (_tournament.getTournamentSize() / 2));
        tournamentTile.setEditMode(false);
        setEditMode(false);
    }

    public void deleteTournament()
    {
        Tournament.allTournaments.remove(tournament);
        Tournament.tournamentCount--;

        //clear gridpane, but preserve grid lines
        Node node = tournamentGrid.getChildren().get(0);
        tournamentGrid.getChildren().clear();
        tournamentGrid.add(node,0,0);

        //remove current tournamenttiles
        TournamentTile.allTournamentTiles.remove(tournamentTile);
        TournamentTileEdit.allTournamentTileEdits.remove(this);

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
