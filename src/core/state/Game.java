package core.state;


import java.io.File;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.GameStart;
import core.LevelRendering.LevelManager;
import core.LevelRendering.Tile;
import core.LevelRendering.TileSet;
import core.entities.Player;
import it.randomtower.engine.World;


public class Game extends World {
	private Player player;
	private LevelManager lm;
	//TODO Collision Creature with player ==> Health down
	//TODO Collision with world
	
	
	
	
	public Game(int id, GameContainer container) throws SlickException {
		super(id, container);
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		lm = ((GameStart) game).getLM();
		lm.loadLevel(0, game);
		player = lm.getPlayer();
		add(player);
		ArrayList<Tile> tiles = lm.getTiles();
		for (Tile tile : tiles) {
			add(tile);
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		if (((GameStart) game).getShow_FPS()) {
			g.drawString("fps: " + container.getFPS(), 0, 0);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
		super.update(container, game, i);
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && game.getCurrentStateID() == 1) {
			game.enterState(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_P)&& game.getCurrentStateID()==1) {
			game.enterState(5);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F10)&&game.getCurrentStateID()==1) {
			game.enterState(2);
		}
	}
}
