package core.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.entities.Player;
import core.state.Game;
import it.randomtower.engine.entity.Entity;

public class Gem extends Entity{

	private boolean ender;
	
	public Gem(float x, float y, Image image, boolean ender) {
		super(x, y, image);
		this.ender = ender;
		setHitBox(0, 0, image.getWidth(), image.getHeight());
		addType("Gem");
	}

	public boolean isEnder() {
		return ender;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		super.update(arg0, arg1);
		if(world instanceof Game) {
			x+=(Player.delta_x)*10;
			y+=(Player.delta_y)*10;
		}
	}
	
}
