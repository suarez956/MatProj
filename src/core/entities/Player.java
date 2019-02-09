package core.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import core.GameStart;
import core.LevelRendering.Tile;
import core.items.Gem;
import core.other.Config;
import core.state.Game;
import it.randomtower.engine.entity.Entity;

@SuppressWarnings("unused")
public class Player extends Entity {

	// TODO Health, Battery Bar, !!GRAPHICS!!, animation, throwing a pickaxe, climbing a rope, placing dynamite
	private SpriteSheet sheet;
	private boolean fall = true;
	private boolean orientation = false; 	// false=> -> ; true => <-
	private float n_height;
	private File player_config;
	private double jump_diff = 0;
	
	static public float delta_x = 0f;
	static public float delta_y = 0f;

	public Player(float x, float y, SpriteSheet playersheet,Config config) {
		super(x, y);
		setAnimations(playersheet);
		String name = "PLAYER";
		setCollision(name);
		defControl(config);
		this.sheet = playersheet;
	}
	void defControl(Config config) {
		define("JUMP",config.getKey_up());
		define("LEFT",config.getKey_left());
		define("DOWN",config.getKey_down());
		define("RIGHT",config.getKey_right());
		define("SPECIAL",config.getKey_special());
	}

	private void setCollision(String name) {
		setHitBox(0, 0, 50, 88);
		addType(name);
	}
	
	private void setAnimations(SpriteSheet playersheet) {
		setGraphic(playersheet);
		duration = 150;
		addAnimation("NO_MOVE_L", false, 0, 3);
		addAnimation("NO_MOVE_R", false, 0, 4);
		addAnimation("MOVE_LEFT", true, 0, 0, 1, 2, 3);
		addAnimation("MOVE_RIGHT", true, 0, 4, 5, 6, 7);
		//addAnimation("THROW", true,2,0,1,2);
		//addAnimation("JUMP", true, 3, 0,1,2,3,3,2,1);
	}
	public float getHeight() {
		return n_height;
	}
	
	@Override
	public void collisionResponse(Entity other) {
		super.collisionResponse(other);
		if (other instanceof Gem) {
			System.out.println("Hitnutý gem");
			Game.score+=100;
			Gem gem = (Gem)other;
			gem.destroy();
		}
		if (other instanceof Tile) {
			System.out.println("Hitnutý Tile");
		}
	}
	
	@Override
	public void update(GameContainer gc, int arg) throws SlickException {
		super.update(gc, arg);
		n_height = gc.getHeight();
		float previous_x = 0f;
		float previous_y = 0f;
		if(collide("Tile", x-5, y)==null && check("LEFT")) {
				currentAnim = "MOVE_LEFT";								
				orientation = false;
					previous_x = x;
					x-=0.5;
					delta_x = previous_x-x;		
		}
		else if(collide("Tile", x+5, y)==null && check("RIGHT")) {
				currentAnim = "MOVE_RIGHT";
				orientation = true;
					previous_x = x;
					x+=0.5;
					delta_x = previous_x-x;
		}
		else {
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
			System.out.println(jump_diff);
			if(jump_diff<=50 && !fall && collide("Tile",x,y-5)==null) {
				previous_y = y;
				y-=0.5;
				delta_y = previous_y-y;
				jump_diff+=5;
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
	
	
	
	

}