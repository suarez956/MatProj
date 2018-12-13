package core.state;

import core.*;
import core.LevelRendering.LevelManager;
import it.randomtower.engine.World;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author honza
 */
public class Intro extends World {
	/**
	 *
	 * @param id
	 * @param gc
	 */
	
	
	public Intro(int id, GameContainer gc) {
		super(id, gc);
	}

	private static LevelManager lm;
	
	/**
	 *
	 * @param container
	 * @param game
	 * @param g
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawString("vstup", container.getWidth() / 2 - 50, container.getHeight() / 2);
		g.draw(new Rectangle(container.getWidth() / 2 - 50, container.getHeight() / 2, 100, 20));

		g.drawString("Nastavení", container.getWidth() / 2 - 50, container.getHeight() / 2 + 30);
		g.draw(new Rectangle(container.getWidth() / 2 - 50, container.getHeight() / 2 + 30, 100, 20));
		if (((GameStart) game).getShow_FPS()) {
			g.drawString("fps: " + container.getFPS(), 0, 0);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
		super.update(container, game, i);
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN) && game.getCurrentStateID() ==0) {
			game.enterState(1);
		}
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		lm = ((GameStart) game).getLM();
		lm.loadLevels();
		lm.loadLevel(0, game);
	}

}
