package core.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import core.GameStart;
import core.LevelRendering.Tile;
import core.items.Gem;
import core.other.Config;
import core.state.Game;
import it.randomtower.engine.entity.Entity;

@SuppressWarnings("unused")
public class Player extends Entity {
	
	private SpriteSheet sheet;
	private boolean fall = true;
	private boolean orientation = false; 	// false=> -> ; true => <-
	private float n_height;
	private File player_config;
	private double jump_diff = 0;
	private static int health = 3;
	private Audio movement;
	
	
	public static float delta_x = 0f;
	public static float delta_y = 0f;

	public Player(float x, float y, SpriteSheet playersheet,Config config) {
		super(x, y);
		setAnimations(playersheet);
		setHitBox(4, 0, 46, 88);
		addType("PLAYER");
		defControl(config);
		this.sheet = playersheet;
		
		try {
			movement = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/sfx/player.ogg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	void defControl(Config config) {
		define("JUMP",config.getKey_up());
		define("LEFT",config.getKey_left());
		define("DOWN",config.getKey_down());
		define("RIGHT",config.getKey_right());
		define("SPECIAL",config.getKey_special());
	}
	public boolean getOrientation() {
		return orientation;
	}
	
	private void setAnimations(SpriteSheet playersheet) {
		setGraphic(playersheet);
		duration = 150;
		addAnimation("NO_MOVE_L", false, 0, 2);
		addAnimation("NO_MOVE_R", false, 1, 2);
		addAnimation("MOVE_LEFT", true, 0, 2, 3, 4, 5, 0, 1);
		addAnimation("MOVE_RIGHT", true, 1, 2, 3, 4, 5, 0, 1);
	}
	public float getHeight() {
		return n_height;
	}
	
	@Override
	public void update(GameContainer gc, int arg) throws SlickException {
		super.update(gc, arg);
		n_height = gc.getHeight();
		float previous_x = 0f;
		float previous_y = 0f;
		if(collide("Tile", x-5, y)==null && check("LEFT")) {
			if(!movement.isPlaying()) {
				movement.playAsSoundEffect(1f, 1f, false);
			}
			currentAnim = "MOVE_LEFT";								
			orientation = false;
				previous_x = x;
				x-=0.5;
				delta_x = previous_x-x;		
		}
		else if(collide("Tile", x+5, y)==null && check("RIGHT")) {
			if(!movement.isPlaying()) {
				movement.playAsSoundEffect(1f, 1f, false);
			}
			currentAnim = "MOVE_RIGHT";
			orientation = true;
				previous_x = x;
				x+=0.5;
				delta_x = previous_x-x;
		}
		else {
			movement.stop();
			delta_x = 0f;
			if(orientation==true) {
				currentAnim="NO_MOVE_R";
			}else {
				currentAnim="NO_MOVE_L";
			}
		}
		if(collide("Tile",x,y+5)==null) {
			if(!check("JUMP") || fall) {
				previous_y = y;
				y+=0.5;
				delta_y = previous_y-y;
			}
		}
		else {
			delta_y=0;
			fall = false;
			jump_diff=0;
		}
		if(check("JUMP") && !fall) {
			if(jump_diff<=50 && !fall && collide("Tile",x,y-5)==null) {
				previous_y = y;
				y-=0.3;
				delta_y = previous_y-y;
				jump_diff+=3;
			}
			else {
				fall =true;
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		
	}
	public static int getHealth() {
		return health;
	}
	public static void setHealth(int health) {
		Player.health = health;
	}
	
	
	
	

}