package sourcepackage;//Magnus Svendsen DAT16i

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import java.util.Optional;

public abstract class StageHandler
{
    public static int addTeamBtnCurrentCol = 0;
    public static int addTeamBtnCurrentRow = 0;
    public static int addTourBtnCurrentCol = 0;
    public static int addTourBtnCurrentRow = 0;

    public static void closeProgram()
    {
        Main.primaryStage.fireEvent(
                new WindowEvent(
                        Main.primaryStage,
                        WindowEvent.WINDOW_CLOSE_REQUEST
                ));
    }

    public static void displayAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(Main.primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static EventHandler<WindowEvent> confirmCloseEventHandler = event ->
    {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Exit Foosball Manager?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Confirm Exit");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(Main.primaryStage);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };
}
