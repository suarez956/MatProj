package core.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import it.randomtower.engine.World;

public class LevelDone extends World{

	public LevelDone(int id, GameContainer container) {
		super(id, container);
		
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		super.render(arg0, arg1, arg2);
		arg2.drawString("Chces zapsat cas do Highscore?", container.getWidth() / 2 - 130, container.getHeight() / 2);
		arg2.draw(new Rectangle(container.getWidth() / 2 - 50, container.getHeight() / 2, 260, 20));

	}
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		super.update(arg0, arg1, arg2);
		
	}

}
