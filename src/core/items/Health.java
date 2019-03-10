package core.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.entities.Player;
import core.state.Game;
import it.randomtower.engine.entity.Entity;

public class Health extends Entity{

	public Health(float x, float y, Image image) {
		super(x, y, image);
		setHitBox(0, 0, image.getWidth(), image.getHeight());
		addType("Health_Box");
	}
	
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		super.update(arg0, arg1);
		if(world instanceof Game) {
			x+=(Player.delta_x)*10;
			y+=(Player.delta_y)*10;
		}
		if (collide("PLAYER", x, y) != null) {
			Player.setHealth(Player.getHealth()+1);
			this.destroy();
		}
	}
}
