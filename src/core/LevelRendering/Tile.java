package core.LevelRendering;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.entities.Player;
import core.state.Game;
import it.randomtower.engine.entity.Entity;

public class Tile extends Entity {

	public Tile(float x, float y, Image image) {
		super(x, y, image);
		addType("Tile");
	}

	private String colide_type;
	private boolean collidable;
	
	
	public String getColide_type() {
		return colide_type;
	}
	
	public boolean getCollidable() {
		return collidable;
	}
	public void setColide_type(String colide_type) {
		this.colide_type = colide_type;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}
	
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		super.update(arg0, arg1);
		if(world instanceof Game) {
			x-=Player.delta_x;
			//y-=Player.delta_y;
		}
		
	}
	
	
	
	
}
