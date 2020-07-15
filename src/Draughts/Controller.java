package Draughts;

import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {

    Stage stage;
    Game game;

    Controller(Stage stage, Game game){
        this.stage = stage;
        stage.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandler);
        this.game = game;
    }

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            game.manageClick((int)e.getX(), (int)e.getY());
        }
    };
}
