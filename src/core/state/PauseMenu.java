package core.state;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.randomtower.engine.World;

public class PauseMenu extends World {

	public PauseMenu(int id, GameContainer container) {
		super(id, container);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
		super.update(container, game, i);
		if (Keyboard.isKeyDown(Keyboard.KEY_P)&& game.getCurrentStateID()==5) {
			game.enterState(1);
		}

	}

}
