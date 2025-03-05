package scene;

import java.util.ArrayList;

import config.GameConfig;
import core.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartScene extends BaseScene {
	private Button playBtn;
	private Button exitBtn;
	private VBox vBox;
	private Pane root;
	
	public StartScene() {
		initScene();
		initPlayButton();
		initExitButton();
		
		vBox = new VBox();
		vBox.getChildren().addAll(playBtn, exitBtn);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		
		root.getChildren().add(vBox);
	}

	private void initExitButton() {
		// TODO Auto-generated method stub
		exitBtn = new Button("Exit");
		exitBtn.setFont(Font.font("Tohama", FontWeight.BOLD, 16));
		exitBtn.setTextFill(Color.WHITE);
		exitBtn.setPrefWidth(150);
		exitBtn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null)));
		exitBtn.setOnMouseEntered(e -> exitBtn.setBackground(new Background(new BackgroundFill(Color.web("#7AB2B2"), new CornerRadii(10), null))));
		exitBtn.setOnMouseExited(e -> exitBtn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null))));
		exitBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	Platform.exit();
		    }
		});
	}

	
    private void initPlayButton(){
        playBtn = new Button("Play");
        playBtn.setFont(Font.font("Tohama", FontWeight.BOLD,16));
        playBtn.setTextFill(Color.WHITE);
        playBtn.setPrefWidth(150);
        playBtn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null)));
        playBtn.setOnMouseEntered(e -> playBtn.setBackground(new Background(new BackgroundFill(Color.web("#7AB2B2"), new CornerRadii(10), null))));
        playBtn.setOnMouseExited(e -> playBtn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null))));
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.getInstance().showGame();
            }
        });
    }
    
    @Override
	protected void initScene() {
		// TODO Auto-generated method stub
    	root = new StackPane();
		scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
	}

}
