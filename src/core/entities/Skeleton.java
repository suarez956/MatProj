package core.entities;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import core.state.Game;
import it.randomtower.engine.entity.Entity;

public class Skeleton extends Entity{

	private boolean orientation = false;
	private int health = 4;
	private int hit_time = 0;
	
	private Audio hitted;
	
	
	public Skeleton(float x, float y, SpriteSheet sprite) {
		super(x, y);
		setAnimation(sprite);
		init();
	}
	
	public Skeleton(float x, float y, SpriteSheet sprite, int health) {
		super(x, y);
		setAnimation(sprite);
		this.health = health;
		init();
	}

	private void setAnimation(SpriteSheet sprite) {
		setGraphic(sprite);
		duration = 150;
		addAnimation("MOVE_LEFT", true, 0, 0, 1, 2, 3, 4);
		addAnimation("MOVE_RIGHT", true, 1, 0, 1, 2, 3, 4);
		addAnimation("ATT_LEFT", true, 2, 0, 1, 2);
		addAnimation("ATT_RIGHT", true, 3, 0, 1, 2);
	}
	
	private void init() {
		try {
			hitted = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/sfx/skeleton.ogg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addType("Enemy");
		setHitBox(0, 0, width-5, height-5);
	}
	
	
	
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		super.update(arg0, arg1);
		if(collide("Tile", x-5, y)==null && !orientation) {
			currentAnim = "MOVE_LEFT";
				x-=1.5;	
		}
		else if(collide("Tile", x+5, y)==null && orientation) {
			currentAnim = "MOVE_RIGHT";
			x+=1.5;	
		}
		else {
			orientation = !orientation;
		}
		if (collide("Weapon",x,y) != null && hit_time == 0) {
			if (health == 0) {
				Game.score+=100;
				this.destroy();
			}else {
				health-= 1*Game.weapon_hit_mod;
			}
			hit_time = 15;
			hitted.playAsSoundEffect(1f, 1f, false);
		}
		if (hit_time > 0) {
			hit_time--;
		}
		
		if(world instanceof Game) {
			x+=(Player.delta_x)*10;
			y+=(Player.delta_y)*10;
		}
		
	}
	
	//GETTERS, SETTERS
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
