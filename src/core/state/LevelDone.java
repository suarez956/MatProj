package core.state;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.other.MenuElement;
import it.randomtower.engine.World;

public class LevelDone extends World{
	private MenuElement end;
	
	public LevelDone(int id, GameContainer container) {
		super(id, container);
		
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		end = new MenuElement(0, 0, new Image("res/img/menu/level_done.png"));
		add(end);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException {
		super.update(container, game, arg2);
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			game.enterState(1);
		}
	}

}
