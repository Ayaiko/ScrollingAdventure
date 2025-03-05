package scene;

import java.util.ArrayList;

import core.SceneManager;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class StartScene extends BaseScene {
	private Button startBtn;
	private Button exitBtn;
	private VBox vBox;
	
	public StartScene() {
		initStartButton();
		initExitButton();
		
		vBox = new VBox();
		vBox.getChildren().addAll(startBtn, exitBtn);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		
		root.getChildren().add(vBox);
	}

	private void initExitButton() {
		// TODO Auto-generated method stub
		exitBtn = new Button("Exit");
		
		exitBtn.setOnAction(e -> {
			Platform.exit();
		});
	}

	private void initStartButton() {
		startBtn = new Button();

		startBtn.setOnAction(e -> {
			SceneManager.getInstance().showGameMenu();
		});
		
		startBtn.setText("Start");
	}
	
	

}
