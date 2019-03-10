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

public class Spider extends Entity {
	private int health = 3;
	private boolean orientation = false;
	private int hit_time = 0;
	private Audio hitted;
	
	
	public Spider(float x, float y, SpriteSheet sprite) {
		super(x, y);
		set_animations(sprite);
		init();
	}
	
	public Spider(float x, float y, SpriteSheet sprite, int health) {
		super(x, y);
		set_animations(sprite);
		this.health = health;
		init();
	}
	
	private void set_animations(SpriteSheet sprite) {
		setGraphic(sprite);
		duration = 150;
		addAnimation("MOVE_LEFT", true, 0, 0, 1);
		addAnimation("MOVE_RIGHT", true, 1, 0, 1);
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	private void init() {
		try {
			hitted = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/sfx/spider.ogg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addType("Enemy");
		setHitBox(0, 0, width, height);
		setCentered(true);
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
		if (collide("PLAYER",x,y) != null) {
			if(orientation) {
				orientation = !orientation;
			}else {
				orientation = !orientation;
			}
		}
		if (collide("Weapon",x,y) != null && hit_time == 0) {
			hit_time = 15;
			hitted.playAsSoundEffect(1f, 1f, false);
			if(health == 0) {
				this.destroy();
				Game.score+=50;
			}else {
				health--;
			}
		}
		if (hit_time > 0) {
			hit_time--;
		}
		
		if(world instanceof Game) {
			x+=(Player.delta_x)*10;
			y+=(Player.delta_y)*10;
		}
	}
	
	

}
