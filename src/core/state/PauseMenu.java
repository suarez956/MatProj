package core.state;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.GameStart;
import core.other.MenuElement;
import it.randomtower.engine.World;

public class PauseMenu extends World {

	private MenuElement background;
	private MenuElement back;
	private MenuElement save;
	private MenuElement menu;
	private int transition_time = 3;
	
	
	public PauseMenu(int id, GameContainer container) {
		super(id, container);
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		
		background = new MenuElement(0, 0, new Image("res/img/menu/pauza_menu.png"));
		add(background);
		back = new MenuElement(325, 200, new Image("res/img/menu/back.png"));
		add(back);
		save = new MenuElement(325, 300, new Image("res/img/menu/save.png"));
		add(save);
		menu = new MenuElement(325, 400, new Image("res/img/menu/button_menu.png"));
		add(menu);
		
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
		super.update(container, game, i);
		if (transition_time==0){
			if (Keyboard.isKeyDown(Keyboard.KEY_P)&& game.getCurrentStateID()==5) {
				game.enterState(1);
			}
			
			if(Mouse.isButtonDown(0)) {
				if (container.getInput().getMouseX() >= back.x && 
						container.getInput().getMouseX() <= back.x+back.width && 
						container.getInput().getMouseY() >= back.y && 
						container.getInput().getMouseY() <= back.y+back.height) {
							game.enterState(1);
					}
					if (container.getInput().getMouseX() >= save.x && 
							container.getInput().getMouseX() <= save.x+save.width && 
							container.getInput().getMouseY() >= save.y && 
							container.getInput().getMouseY() <= save.y+save.height) {
								save(game);
					}
					if (container.getInput().getMouseX() >= menu.x && 
							container.getInput().getMouseX() <= menu.x+menu.width && 
							container.getInput().getMouseY() >= menu.y && 
							container.getInput().getMouseY() <= menu.y+menu.height) {
								game.enterState(0);
					}
			}
			transition_time+=3;
		}else {
			transition_time--;
		}
	}

	public void save(StateBasedGame game) {
		File savefile = new File("save.txt");
		
		if(savefile.exists()) {
			savefile.delete();
			try {
				savefile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				savefile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ArrayList<String> lines = new ArrayList<>(Arrays.asList(String.valueOf(GameStart.lm.getLevel_index()), String.valueOf(Game.score)));
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(savefile));
			for (int i = 0; i < lines.size(); i++) {
	            bw.write(lines.get(i));
	            bw.write("\n");
	        }
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Hra uložena.", null, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
