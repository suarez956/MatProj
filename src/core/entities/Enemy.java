package core.entities;

import org.newdawn.slick.SpriteSheet;

import core.LevelRendering.Tile;
import it.randomtower.engine.entity.Entity;

public class Enemy extends Entity {

	public Enemy(float x, float y, SpriteSheet sprite) {
		super(x, y);
		setHitBox(0, 0, sprite.getWidth(), sprite.getHeight());
		addType("ENEMY");
		setAnimations(sprite);
	}

	private void setAnimations(SpriteSheet sprite) {
		
	}
	
}
