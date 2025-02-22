package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox{
	
	public MenuPane() {
		Button startButton = new Button("Start");
		startButton.setOnAction(e -> startGame());
		
		Button quitButton = new Button("Quit");
		quitButton.setOnAction(e -> quitGame());
		
		this.getChildren().addAll(startButton, quitButton);
	}
	
	private void startGame() {
		
	}
	
	private void quitGame() {
		
	}
	
	
}
