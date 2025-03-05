package logic.game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class InputController {
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    public InputController() {

    }

    // Setup method to be called when a scene is available
    public void keyboardSetup(Scene scene) {
        scene.setOnKeyPressed(e -> handleKeyPress(e));
        scene.setOnKeyReleased(e -> handleKeyRelease(e));
    }

    private void handleKeyPress(KeyEvent e) {
        pressedKeys.add(e.getCode());
    }

    private void handleKeyRelease(KeyEvent e) {
        pressedKeys.remove(e.getCode());
    }

    public boolean isKeyPressed(KeyCode key) {
        return pressedKeys.contains(key);
    }
}
