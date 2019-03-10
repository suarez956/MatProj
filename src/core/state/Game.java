package core.state;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import core.GameStart;
import core.LevelRendering.LevelManager;
import core.LevelRendering.Tile;
import core.entities.Player;
import core.entities.Skeleton;
import core.entities.Spider;
import core.items.Gem;
import core.items.Health;
import core.items.Weapon;
import it.randomtower.engine.World;


public class Game extends World {
	private Player player;
	private LevelManager lm;
	public static int score = 0;
	Gem level_end;
	private Gem ender;
	public static Image weapon_image;
	public static int weapon_hit_mod = 1;
	public static int reload = 0; 	//0 - není potøeba naèíst level 1 - naète se jiný level
	
	
	//Timers
	private int throw_time = 0;
	private int hit_time = 0;
	private int transition_time = 3;
	
	//Fonts
	java.awt.Font UIFont_awt;
	org.newdawn.slick.UnicodeFont UIFont;
	
	
	public Game(int id, GameContainer container) throws SlickException {
		super(id, container);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		lm = ((GameStart) game).getLM();
		load(game, 0);
		//Font creation
		try{
	        UIFont_awt = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/PixelGameFont.ttf"));
	        UIFont_awt = UIFont_awt.deriveFont(java.awt.Font.PLAIN, 30); 
	        UIFont = new org.newdawn.slick.UnicodeFont(UIFont_awt);
	        UIFont.addAsciiGlyphs();
	        UIFont.getEffects().add(new ColorEffect(java.awt.Color.white));
	        UIFont.addAsciiGlyphs();
	        UIFont.loadGlyphs();
	    }catch(Exception e){
	            e.printStackTrace();
	    }
		weapon_image = new Image("res/img/pickups/basic_pickaxe.png");
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		if (((GameStart) game).getShow_FPS()) {
			String string = "fps: " + container.getFPS()+" X:"+player.x+" Y:"+player.y;
			g.drawString(string, 0, 0);
		}
		UIFont.drawString(0, 0, "Score: "+String.valueOf(score));
		g.drawImage(new Image("res/img/levels/other/health.png"), 0, 40);
		UIFont.drawString(40, 40, "x " + Player.getHealth());
		g.drawRect(750, 0, 50, 50);
		g.drawImage(weapon_image, 750, 0);
		
	}

	private void load(StateBasedGame game, int curr_level) {
		this.clear();
		lm.loadLevel(curr_level, game);
		player = lm.getPlayer();
		ender = lm.getEnder();
		add(player);
		for (Tile tile : lm.getTiles()) {
			add(tile);
		}
		for(Gem gem : lm.getGems()) {
			add(gem);
		}
		for (Health health : lm.getHealths()) {
			add(health);
		}
		for (Spider spider : lm.getSpiders()) {
			add(spider);
		}
		for (Skeleton skeleton : lm.getSkeleton()) {
			add(skeleton);
		}
		for (Weapon weapon : lm.getWeapon()) {
			add(weapon);
		}
		add(ender);
		throw_time= 0;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
		super.update(container, game, i);
		if (reload == 1) {
			reload = 0;
			load(game, lm.getLevel_index());
		}
		
		if(transition_time == 0) {
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && game.getCurrentStateID() == 1) {
				game.enterState(0);
				load(game, lm.getLevel_index());
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_P)&& game.getCurrentStateID()==1) {
				game.enterState(5);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_F10)&&game.getCurrentStateID()==1) {
				game.enterState(2);
			}
			transition_time+=3;
		}else {
			transition_time--;
		}
		if(player.collide("Enemy", player.x, player.y) != null && hit_time == 0) {
			hit_time=100;
			Player.setHealth(Player.getHealth()-1);
		}
		if (hit_time>0) {
			hit_time--;
		}
		if(Player.getHealth()<=0) {
			Player.setHealth(3);
			load(game, lm.getLevel_index());
			game.enterState(3);
		}
		if (player.check("SPECIAL") && throw_time == 0) {
			throw_time = 10;
			if (player.getOrientation()) {
				add(new Weapon(player.x+player.width, player.y, weapon_image, player.getOrientation()));
			}else {
				add(new Weapon(player.x, player.y, weapon_image, player.getOrientation()));
			}
		}
		if (throw_time >0) throw_time--;
		if(ender.collide("PLAYER", ender.x, ender.y) != null) {
			try {
				if (lm.loadNextLevel()) {
					load(game, lm.getLevel_index());
					game.enterState(2);
				}else {
					load(game, 0);
					game.enterState(4);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
