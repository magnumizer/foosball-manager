package sourcepackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception{
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        Scene scene = new Scene(root, 1024, 768);

        primaryStage.setTitle("Foosball Manager");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("sourcepackage/style.css");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setOnCloseRequest(StageHandler.confirmCloseEventHandler);
        primaryStage.show();
    }



    public static void main(String[] args)
    {
        launch(args);
    }


}
