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
import core.other.Config;
import it.randomtower.engine.entity.Entity;

@SuppressWarnings("unused")
public class Player extends Entity {

	// TODO Health, Battery Bar, !!GRAPHICS!!, animation, throwing a pickaxe, climbing a rope, placing dynamite
	private SpriteSheet sheet;
	private boolean fall = true;
	private boolean orientation = false; 	// false=> -> ; true => <-
	private float n_height;
	private File player_config;
	private int n = 0;
	
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
		setHitBox(0, 0, 42, 95);
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
	public void update(GameContainer gc, int arg) throws SlickException {
		super.update(gc, arg);
		n_height = gc.getHeight();
		/*
		 if(fall) {
			 if (y+100<=gc.getHeight()) {
				 y++;
				 System.out.println(y);
				 delta_y+=(y*0.1f);
			}
			else {
					fall = false;
				}
		 }
		if (!check("JUMP")) {
			fall = true;
			if (fall && y+100<=gc.getHeight()) {
				y++;
				System.out.println(y);
			}
		}
		*/
		float previous_x = 0f;
		float previous_y = 0f;
		if (check("LEFT")) {														//Kotrola stisknutí kl. LEFT
			currentAnim = "MOVE_LEFT";												//Přepnutí animace na "MOVE_LEFT"	
			orientation = false;													//viz. orientace boolean
			if (collide("Tile", x-5,y+10)==null) {									//kolize se SOLID blokem nebo koncem okna
				previous_x = x;
				x-=1f;
				delta_x = previous_x-x;
				}
		}
		else if (check("RIGHT")) {
			currentAnim = "MOVE_RIGHT";
			orientation = true;
			if (collide("Tile", x+5,y+10)==null) {
				previous_x = x;
				x+=1f;
				delta_x = previous_x-x;
			}
		}else {
			delta_x = 0;
			if(orientation==true) {
				currentAnim="NO_MOVE_R";
			}else {
				currentAnim="NO_MOVE_L";
			}
		}
		if(collide("Tile", x, y+10)!=null) {
			delta_y = 0;
		}else {
			previous_y = y;
			y+=0.25f;
			delta_y = previous_y-y;
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		
	}
	
	
	
	

}