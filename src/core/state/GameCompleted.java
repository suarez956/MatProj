package core.state;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import core.other.MenuElement;
import it.randomtower.engine.World;

public class GameCompleted extends World {
	
	//Fonts
	java.awt.Font UIFont_awt;
	org.newdawn.slick.UnicodeFont UIFont;

	private MenuElement end;
	
	public GameCompleted(int id, GameContainer container) throws SlickException {
		super(id, container);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		end = new MenuElement(0, 0, new Image("res/img/menu/game_done.png"));
		
		//Font creation
		try{
			UIFont_awt = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/PixelGameFont.ttf"));
		    UIFont_awt = UIFont_awt.deriveFont(java.awt.Font.PLAIN, 30); 
		    UIFont = new org.newdawn.slick.UnicodeFont(UIFont_awt);
			UIFont.addAsciiGlyphs();
			UIFont.getEffects().add(new ColorEffect(java.awt.Color.MAGENTA));
			UIFont.addAsciiGlyphs();
			UIFont.loadGlyphs();
			UIFont.drawString(50, 150, "Final Score: " + Game.score);
		}catch(Exception e){
		    e.printStackTrace();
	    }
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2) throws SlickException {
		super.update(arg0, game, arg2);
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			game.enterState(0);
		}
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		add(end);
		
	}
	
}
