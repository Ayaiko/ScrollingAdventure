package application;

import gui.GamePane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.GameSystem;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        GamePane gamePane = GamePane.getInstance();
        ImageView dino = new ImageView("dino.png");
        
        gamePane.prefHeightProperty().bind(root.heightProperty());
        gamePane.prefWidthProperty().bind(root.widthProperty());
        
        root.getChildren().addAll(gamePane, dino);
        
        Scene scene = new Scene(root, GameSystem.gameWidth, GameSystem.gameHeight);
        
        primaryStage.setTitle("Character Jump Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
