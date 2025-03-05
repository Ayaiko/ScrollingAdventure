package entity;

import config.GameConfig;
import logic.game.SpriteAnimator;

public class Player extends SpriteAnimator {
	private double velocityY = 0;// Vertical velocity
	private boolean isJumping = false;// Jump state

	public Player(String playerImage) {
		super(playerImage, 7);
		
		sprite.setFitHeight(120);
		sprite.setFitWidth(120);
		int playerPosition = (int) (GameConfig.GROUND_LEVEL - sprite.getFitHeight() + 10);
		sprite.setTranslateY(playerPosition);

	}

	public void jump() {
		if (!isJumping) {
			isJumping = true;
			velocityY = -GameConfig.JUMP_FORCE;
		}
	}

	public void update() {
	    if (isJumping) {
	        velocityY += GameConfig.GRAVITY;
	        int playerPosition = (int) (GameConfig.GROUND_LEVEL - sprite.getFitHeight() + 10);

	        // ใช้ Math.min เพื่อให้ตำแหน่ง Y ไม่ต่ำกว่าพื้น
	        sprite.setTranslateY(Math.min(sprite.getTranslateY() + velocityY, playerPosition));
	        

	        // ตรวจสอบว่าแตะพื้นแล้วหรือยัง
	        if (sprite.getTranslateY() >= playerPosition) {
	            velocityY = 0;  // หยุดการตก
	            isJumping = false;  // หยุดการกระโดด
	        }
	    }
	}
}