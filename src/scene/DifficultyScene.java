package scene;

import config.GameConfig;
import core.SceneManager;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class DifficultyScene extends BaseScene{
	private Stage stage;
    private static double initialSpeedMultiplier;
    private static boolean speedIncreases;
    private Button easyBtn;
    private Button normalBtn;
    private Button hardBtn;
    private Button backToMenuBtn;
    private VBox vBox;
    private Pane root;
    private Stage primaryStage;
    
    public DifficultyScene() {
        initScene();
        initTitlePane();
        initEasyButton();
        initNormalButton();
        initHardButton();
        initBackToMenuButton();

        vBox = new VBox();
        vBox.getChildren().addAll(easyBtn, normalBtn, hardBtn, backToMenuBtn);
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
        Text title = new Text("CHOOSE DIFFICULTY LEVEL");
        title.setFont(Font.font("Serif", FontWeight.BOLD, 70));
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
        Text tagline = new Text("\"The path ahead is full of trials, each more daunting than the last. Choose wisely the difficulty of your journey, for the road you take shall shape the challenges you face.\"\r\n"
        		+ "\r\n"
        		+ "Easy: A gentle breeze guides you forward, where obstacles are few and victory is within reach.\r\n"
        		+ "Normal: The road is uncertain, with both trials and triumphs awaiting. Only the brave shall succeed.\r\n"
        		+ "Hard: The path is fraught with peril, where every step is a test of strength and resolve. Only the most valiant may endure.\r\n"
        		+ "\r\n"
        		+ "Choose your fate, and let the journey unfold as you dare.");
        tagline.setFont(Font.font("Serif", FontWeight.NORMAL, 24));
        tagline.setFill(Color.HOTPINK);
        tagline.setWrappingWidth(1200); // Make sure the text fits on the screen

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

    private void initEasyButton() {
        easyBtn = createStyledButton("Easy");
        easyBtn.setOnAction((e -> {
            setInitialSpeedMultiplier(1.0);
            setSpeedIncreases(false);
            highlightSelectedButton(easyBtn);
        }));
    }

    private void initNormalButton() {
        normalBtn = createStyledButton("Normal");
        normalBtn.setOnAction((e -> {
            setInitialSpeedMultiplier(1.0);
            setSpeedIncreases(true);
            highlightSelectedButton(normalBtn);
        }));
    }

    private void initHardButton() {
        hardBtn = createStyledButton("Hard");
        hardBtn.setOnAction((e -> {
            setInitialSpeedMultiplier(1.5);
            setSpeedIncreases(true);
            highlightSelectedButton(hardBtn);
        }));
    }

    private void initBackToMenuButton() {
        backToMenuBtn = createStyledButton("Back to Menu");
        backToMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.getInstance().showStartMenu();
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
    
    private void highlightSelectedButton(Button selectedButton) {
        // Remove glow effect from all buttons
        easyBtn.setEffect(null);
        normalBtn.setEffect(null);
        hardBtn.setEffect(null);

        // Apply glow effect to the selected button
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW); // Set the glow color
        glow.setRadius(25); // Adjust the intensity of the glow
        glow.setSpread(0.5); // Adjust how far the glow spreads
        selectedButton.setEffect(glow);
    }

    @Override
    protected void initScene() {
        root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH, CornerRadii.EMPTY, null)));
        scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
    }


    /*private void startGame(double initialSpeed, boolean speedIncreases) {
        GameScene gameScene = new GameScene(initialSpeed, speedIncreases);
        stage.setScene(gameScene.getScene());
    }*/
    
    public static double getInitialSpeedMultiplier() {
		return initialSpeedMultiplier;
	}

	public static void setInitialSpeedMultiplier(double initialSpeedMultiplier) {
		DifficultyScene.initialSpeedMultiplier = initialSpeedMultiplier;
	}

	public static boolean isSpeedIncreases() {
		return speedIncreases;
	}

	public static void setSpeedIncreases(boolean speedIncreases) {
		DifficultyScene.speedIncreases = speedIncreases;
	}
}
