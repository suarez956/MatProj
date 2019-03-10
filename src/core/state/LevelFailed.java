package core.state;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.other.MenuElement;
import it.randomtower.engine.World;

public class LevelFailed extends World{
	
	public LevelFailed(int id, GameContainer container) {
		super(id, container);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		super.render(arg0, arg1, arg2);
		MenuElement main = new MenuElement(0, 0, new Image("res/img/menu/dead_menu.png"));
		add(main);
	}
	
	@Override
	public void update(GameContainer conatiner, StateBasedGame game, int arg2) throws SlickException {
		super.update(conatiner, game, arg2);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN) && game.getCurrentStateID() == 3) {
			game.enterState(1);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && game.getCurrentStateID() == 3) {
			game.enterState(0);
		}
	}
}
