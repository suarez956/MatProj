package core.other;

import org.newdawn.slick.Image;

import it.randomtower.engine.entity.Entity;

public class Background extends Entity{
	private Image image;

	public Background(float x, float y, Image image) {
		super(x, y, image);
		this.image = image;
		setCollision();
	}
	
	private void setCollision() {
		setHitBox(0, 0, image.getWidth(), image.getHeight());
		addType(SOLID);
	}

	

}
