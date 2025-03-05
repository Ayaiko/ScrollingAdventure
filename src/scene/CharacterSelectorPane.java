package scene;

import config.GameConfig;
import core.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


import java.util.HashMap;
import java.util.Map;

public class CharacterSelectorPane extends BaseScene {
    private Pane root;
    private VBox mainLayout;
    private HBox characterBox;
    private Text selectedCharacter;
    private Map<String, StackPane> characterCards = new HashMap<>(); // Map เก็บการ์ดตัวละคร
    private String currentSelected = "Daerei"; // ค่าเริ่มต้น

    public CharacterSelectorPane() {
        initScene();
        initTitle();
        initCharacterSelection();
        initFooter();

        root.getChildren().add(mainLayout);
        updateSelection(currentSelected); // ตั้งค่าเริ่มต้น
    }

    private void initTitle() {
        Text title = new Text("SELECT CHARACTER");
        title.setFont(Font.font("Serif", FontWeight.BOLD, 80));
        title.setFill(Color.HOTPINK);

        Rectangle titleFrame = new Rectangle(1000, 150); // ลดขนาดกรอบ
        titleFrame.setArcWidth(30);
        titleFrame.setArcHeight(30);
        titleFrame.setFill(Color.PINK);
        titleFrame.setStroke(Color.HOTPINK);
        titleFrame.setStrokeWidth(5);

        StackPane titlePane = new StackPane();
        titlePane.getChildren().addAll(titleFrame, title);
        titlePane.setAlignment(Pos.CENTER);  // ตั้งตำแหน่งให้ตรงกลางกรอบ

        mainLayout.getChildren().add(titlePane);
    }

    private void initCharacterSelection() {
        characterBox = new HBox(50);
        characterBox.setAlignment(Pos.CENTER);

        String[] names = {"Daerei", "Saelith", "Vaeriel"};

        for (String name : names) {
            StackPane card = createCharacterCard(name);
            characterCards.put(name, card); // เก็บการ์ดไว้ใน Map
            characterBox.getChildren().add(card);
        }

        mainLayout.getChildren().add(characterBox);
    }

    private StackPane createCharacterCard(String name) {
        // กรอบแทนภาพ
        Rectangle frame = new Rectangle(220, 350);
        frame.setArcWidth(25);
        frame.setArcHeight(25);
        frame.setFill(Color.LAVENDERBLUSH);
        frame.setStroke(Color.HOTPINK);
        frame.setStrokeWidth(4);

        Text nameText = new Text(name);
        nameText.setFont(Font.font("Serif", FontWeight.BOLD, 30));
        nameText.setFill(Color.HOTPINK);

        StackPane characterCard = new StackPane();
        characterCard.getChildren().addAll(frame, nameText);
        characterCard.setAlignment(Pos.CENTER);

        // คลิกเลือกตัวละคร
        characterCard.setOnMouseClicked(event -> updateSelection(name));

        return characterCard;
    }

    private void updateSelection(String name) {
        // อัปเดตตัวละครที่เลือก
        currentSelected = name;
        selectedCharacter.setText("Selected: " + name);

        // ลูปผ่านทุกการ์ด แล้วอัปเดตเอฟเฟกต์
        for (Map.Entry<String, StackPane> entry : characterCards.entrySet()) {
            String character = entry.getKey();
            StackPane card = entry.getValue();

            if (character.equals(name)) {
                card.setEffect(new Glow(0.8)); // ตัวที่เลือก เรืองแสง
            } else {
                card.setEffect(null); // ตัวที่ไม่ได้เลือก กลับเป็นปกติ
            }
        }
    }


    private void initFooter() {
        selectedCharacter = new Text("Selected: Daerei"); // ค่าเริ่มต้นเป็น Daerei
        selectedCharacter.setFont(Font.font("Serif", FontWeight.BOLD, 30));
        selectedCharacter.setFill(Color.PALEVIOLETRED);

        // เพิ่มข้อความใหม่แทน "Good luck"
        Text storyText = new Text("Three fates entwined, three destinies await… In whose tale shall you weave your courage?\n\n" +
                                  "Will you aid the lost princess seeking redemption, the runaway royal defying fate, or the fallen heir reclaiming her throne?\n\n" +
                                  "Choose wisely, for their journey now rests in your hands.");
        storyText.setFont(Font.font("Serif", FontWeight.NORMAL, 24));
        storyText.setFill(Color.HOTPINK);
        storyText.setWrappingWidth(1200); // กำหนดขนาดเพื่อให้ข้อความไม่ยาวเกิน

        Rectangle storyTextFrame = new Rectangle(1300, 220); // ขยายกรอบให้พอดีกับข้อความ
        storyTextFrame.setArcWidth(20);
        storyTextFrame.setArcHeight(20);
        storyTextFrame.setFill(Color.LAVENDERBLUSH);
        storyTextFrame.setStroke(Color.HOTPINK);
        storyTextFrame.setStrokeWidth(3);

        StackPane storyPane = new StackPane();
        storyPane.getChildren().addAll(storyTextFrame, storyText);
        storyPane.setAlignment(Pos.CENTER);
        
        // ทำให้คำโปรยชิดกับ "Selected" มากขึ้น
        storyPane.setTranslateY(20); // ลดระยะห่างระหว่างคำโปรยกับ "Selected"

        // ปุ่มกลับไปที่หน้าเมนู
        Button backButton = new Button("Back to Menu");
        backButton.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #F08080; -fx-border-color: #FF1493; -fx-border-width: 2;");
        backButton.setShape(new javafx.scene.shape.Rectangle(15, 15)); // ให้ปุ่มมีขอบมน

        // เอฟเฟกต์เมื่อเมาส์เข้าและออกจากปุ่ม
        backButton.setOnMouseEntered((MouseEvent event) -> {
            backButton.setStyle("-fx-background-color: #FF1493; -fx-border-color: #FF1493; -fx-border-width: 2; -fx-text-fill: white;");
            backButton.setEffect(new Glow(0.8)); // ทำให้ปุ่มเรืองแสง
        });

        backButton.setOnMouseExited((MouseEvent event) -> {
            backButton.setStyle("-fx-background-color: #F08080; -fx-border-color: #FF1493; -fx-border-width: 2; -fx-text-fill: white;");
            backButton.setEffect(null); // หยุดเรืองแสงเมื่อเมาส์ออกจากปุ่ม
        });

        backButton.setOnAction(event -> goBackToMenu());

        // วางปุ่มใน HBox พร้อมกับข้อความ "Selected"
        HBox footerBox = new HBox(20, selectedCharacter, backButton);
        footerBox.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(footerBox, storyPane); // ใช้ footerBox พร้อมกับข้อความนี้แทน Good luck
    }

    private void goBackToMenu() {
        // ฟังก์ชันสำหรับกลับไปที่หน้าเมนู
        // คุณสามารถจัดการการเปลี่ยนหน้าได้ตามที่ต้องการที่นี่
        SceneManager.getInstance().showStartMenu(); // สมมุติว่า `MainMenuPane` คือคลาสของหน้าเมนู
    }



    @Override
    protected void initScene() {
        root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH, CornerRadii.EMPTY, null)));
        scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        mainLayout = new VBox(40);
        mainLayout.setAlignment(Pos.CENTER);
    }
}
