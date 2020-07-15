package Draughts;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        int width = 800, height = 800;

        Game game = new Game(width,height, 10);
        Parent root = game.board.root;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

        primaryStage.minHeightProperty().bind(primaryStage.widthProperty().multiply(1));
        primaryStage.maxHeightProperty().bind(primaryStage.widthProperty().multiply(1));

        Controller controller = new Controller(primaryStage, game);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
