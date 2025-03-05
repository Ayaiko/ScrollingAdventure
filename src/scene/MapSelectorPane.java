package scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MapSelectorPane extends VBox {
    private static MapSelectorPane instance;

    private MapSelectorPane() {
        // TODO: Complete the remaining code of the constructor
    	super();
    	setPrefHeight(600);
    	setPrefWidth(400);
    	setAlignment(Pos.TOP_CENTER);
    	setSpacing(10);
    	
    	BackgroundFill bgFill = new BackgroundFill(Color.web("#EEF7FF"), CornerRadii.EMPTY, Insets.EMPTY);
    	
    	setBackground(new Background(bgFill) );
    	
    	setPadding(new Insets(10, 0, 10, 0));
    }

    public static MapSelectorPane getInstance() {
        if (instance == null) {
            instance = new MapSelectorPane();
        }
        return instance;
    }
}
