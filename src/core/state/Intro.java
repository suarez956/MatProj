package core.state;

import core.*;
import core.LevelRendering.LevelManager;
import core.other.MenuElement;
import it.randomtower.engine.World;

import java.awt.Menu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
	private MenuElement background;
	private MenuElement start;
	private MenuElement setting;
	private MenuElement end;
	
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
		if (container.getInput().isMousePressed(0)) {
			if (container.getInput().getMouseX() >= start.x && 
				container.getInput().getMouseX() <= start.x+start.width && 
				container.getInput().getMouseY() >= start.y && 
				container.getInput().getMouseY() <= start.y+start.height) {
					game.enterState(1);
			}
			if (container.getInput().getMouseX() >= setting.x && 
					container.getInput().getMouseX() <= setting.x+setting.width && 
					container.getInput().getMouseY() >= setting.y && 
					container.getInput().getMouseY() <= setting.y+setting.height) {
						if (GameStart.config_window.isVisible()) {
							GameStart.config_window.setVisible(false);
						} else {
							GameStart.config_window.setVisible(true);
						}
			}
			if (container.getInput().getMouseX() >= end.x && 
					container.getInput().getMouseX() <= end.x+end.width && 
					container.getInput().getMouseY() >= end.y && 
					container.getInput().getMouseY() <= end.y+end.height) {
						System.exit(0);
			}
		}
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		lm = ((GameStart) game).getLM();
		lm.loadLevels();
		lm.loadLevel(0, game);
		background = new MenuElement(0, 0, new Image("res/img/menu/menu.png"));
		add(background);
		start = new MenuElement(325, 200, new Image("res/img/menu/start.png"));
		add(start);
		setting = new MenuElement(325, 300, new Image("res/img/menu/nastaveni.png"));
		add(setting);
		end = new MenuElement(325, 400, new Image("res/img/menu/konec.png"));
		add(end);
		
	}

}
