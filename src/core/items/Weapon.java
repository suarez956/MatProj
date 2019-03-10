package core.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.entities.Player;
import core.state.Game;
import it.randomtower.engine.entity.Entity;

public class Weapon extends Entity {
	private boolean orientation;
	private int throwable = 0; // 0 - pro sebrání, 1 - pro házení
	private int modifier = 1;
	
	public Weapon(float x, float y, Image image, boolean orientation) {
		super(x, y, image);
		this.orientation = orientation;
		setHitBox(0, 0, width, height);
		addType("Weapon");
		setCentered(true);
		this.throwable = 1;
	}
	
	public Weapon(float x, float y, Image image, int modifier) {
		super(x, y, image);
		setHitBox(0, 0, width, height);
		addType("Weapon");
		setCentered(true);
		this.modifier = modifier;
		this.throwable = 0;
	}
	
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		super.update(arg0, arg1);
		int angle = this.getAngle();
		if (throwable == 1) {
			if(orientation) {
				x += 2;
			    y += 2;
			    angle += 5;
				if (angle >= 360)
					angle -= 360;
				this.setAngle(angle);
			}else {
				x -= 5;
			    y += 5;
			    angle -= 5;
			    if (angle <= -360)
					angle += 360;
				this.setAngle(angle);
			}
		}
	    if ((collide("Tile", x, y+7) != null || collide("Enemy", x-5, y-5) != null) && throwable == 1) {
			this.destroy();
		}
	    if (collide("PLAYER", x, y) != null) {
			if (throwable == 0) {
				this.destroy();
				Game.weapon_image = this.currentImage;
				Game.weapon_hit_mod = modifier;
			}
		}
	    if(world instanceof Game) {
			x+=(Player.delta_x)*10;
			y+=(Player.delta_y)*10;
		}
	}
	

}
