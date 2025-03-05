package core;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	SceneManager.init(primaryStage);
    	
        SceneManager.getInstance().showStartMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

