package scene;

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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScene extends BaseScene {
    private Button playBtn;
    private Button exitBtn;
    private Button selectMapBtn;
    private Button selectCharacterBtn;
    private VBox vBox;
    private Pane root;
    private Stage primaryStage;

    public StartScene() {
        initScene();
        initTitlePane();
        initPlayButton();
        initExitButton();
        initSelectMapButton();
        initSelectCharacterButton();

        vBox = new VBox();
        vBox.getChildren().addAll(playBtn, selectCharacterBtn, selectMapBtn, exitBtn);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);  // Align buttons at the center

        // Create a VBox to hold the title at the top and buttons at the center
        VBox mainLayout = new VBox();
        mainLayout.setSpacing(50);  // Adjust spacing between title and buttons
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(initTitlePane(), vBox, initTaglinePane());  // Add title, buttons, and tagline

        root.getChildren().add(mainLayout);
    }

    private StackPane initTitlePane() {
        // Create the text for the title
        Text title = new Text("RUN, Daerei!");
        title.setFont(Font.font("Serif", FontWeight.BOLD, 120));
        title.setFill(Color.HOTPINK);

        // Create a rectangle to serve as the frame for the title
        Rectangle titleFrame = new Rectangle();
        titleFrame.setWidth(title.getLayoutBounds().getWidth() + 40); // Add padding to the title frame
        titleFrame.setHeight(title.getLayoutBounds().getHeight() + 20); // Add padding to the title frame
        titleFrame.setArcWidth(25); // Rounded corners
        titleFrame.setArcHeight(25); // Rounded corners
        titleFrame.setFill(Color.PINK); // Background color of the frame
        titleFrame.setStroke(Color.HOTPINK); // Border color
        titleFrame.setStrokeWidth(5); // Border width

        // Position the frame and title
        StackPane titlePane = new StackPane();
        titlePane.getChildren().addAll(titleFrame, title);
        StackPane.setAlignment(titlePane, Pos.TOP_CENTER);

        return titlePane;
    }

    private StackPane initTaglinePane() {
        // Create the text for the tagline
        Text tagline = new Text("Embark on the journey of a fallen princess, bound by fate and yearning to break free. "
                + "Chase her quest for fortune and freedom as she faces obstacles that stand in her way. "
                + "Will you help her escape the chains of her past?");
        tagline.setFont(Font.font("Serif", FontWeight.NORMAL, 24));
        tagline.setFill(Color.HOTPINK);
        tagline.setWrappingWidth(600); // Make sure the text fits on the screen

        // Create a rectangle to serve as the frame for the tagline
        Rectangle taglineFrame = new Rectangle();
        taglineFrame.setWidth(tagline.getLayoutBounds().getWidth() + 40); // Add padding to the tagline frame
        taglineFrame.setHeight(tagline.getLayoutBounds().getHeight() + 20); // Add padding to the tagline frame
        taglineFrame.setArcWidth(15); // Rounded corners
        taglineFrame.setArcHeight(15); // Rounded corners
        taglineFrame.setFill(Color.LAVENDERBLUSH); // Background color of the frame
        taglineFrame.setStroke(Color.HOTPINK); // Border color
        taglineFrame.setStrokeWidth(3); // Border width

        // Position the frame and tagline
        StackPane taglinePane = new StackPane();
        taglinePane.getChildren().addAll(taglineFrame, tagline);
        StackPane.setAlignment(taglinePane, Pos.BOTTOM_CENTER);

        return taglinePane;
    }

    private void initExitButton() {
        exitBtn = createStyledButton("Exit");
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
    }

    private void initPlayButton() {
        playBtn = createStyledButton("Play");
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.getInstance().showGame();
            }
        });
    }

    private void initSelectMapButton() {
        selectMapBtn = createStyledButton("Select Map");
        selectMapBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	//Change to MapSeloctorPane
            }
        });
    }
    
    private void initSelectCharacterButton() {
    	selectCharacterBtn = createStyledButton("Select Character");
        selectCharacterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	//Change to CharacterSeloctorPane
            }
        });
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Serif", FontWeight.BOLD, 36));
        button.setTextFill(Color.WHITE);
        button.setPrefWidth(400);
        button.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(15), null)));
        button.setOnMouseEntered(e -> button.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED, new CornerRadii(15), null))));
        button.setOnMouseExited(e -> button.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(15), null))));
        return button;
    }

    @Override
    protected void initScene() {
        root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH, CornerRadii.EMPTY, null)));
        scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
    }
}


