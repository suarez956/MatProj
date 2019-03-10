package core.state;

import core.*;
import core.LevelRendering.LevelManager;
import core.other.MenuElement;
import it.randomtower.engine.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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
	private MenuElement load;
	private int transition_time = 3;
	
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
		if (Game.score > 0) {
			Game.score = 0;
		}
		if(transition_time == 0) {
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN) && game.getCurrentStateID() ==0) {
				game.enterState(1);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_F10)) {
				game.enterState(4);
			}		
			if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
				if (GameStart.config_window.isVisible()) {
					GameStart.config_window.setVisible(false);
				} else {
					GameStart.config_window.setVisible(true);
				}
			}			
			if (container.getInput().isMousePressed(0)) {
				if (container.getInput().getMouseX() >= start.x && 
					container.getInput().getMouseX() <= start.x+start.width && 
					container.getInput().getMouseY() >= start.y && 
					container.getInput().getMouseY() <= start.y+start.height) {
						lm.loadLevel(0, game);
						game.enterState(1);
				}
				
				if (container.getInput().getMouseX() >= load.x && 
						container.getInput().getMouseX() <= load.x+load.width && 
						container.getInput().getMouseY() >= load.y && 
						container.getInput().getMouseY() <= load.y+load.height) {
							try {
								load(game);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
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
			transition_time+=3;
		}else {
			transition_time--;
		}
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		lm = ((GameStart) game).getLM();
		lm.loadLevels();
		background = new MenuElement(0, 0, new Image("res/img/menu/menu.png"));
		add(background);
		start = new MenuElement(325, 200, new Image("res/img/menu/start.png"));
		add(start);
		load = new MenuElement(325, 300, new Image("res/img/menu/load.png"));
		add(load);
		setting = new MenuElement(325, 400, new Image("res/img/menu/nastaveni.png"));
		add(setting);
		end = new MenuElement(325, 500, new Image("res/img/menu/konec.png"));
		add(end);
		
	}
	
	public void load(StateBasedGame game) throws FileNotFoundException {
		File savefile = new File("save.txt");
	 	if (savefile.exists()) {
	 		Scanner in = new Scanner(new FileReader("save.txt"));
	        String x = "";
	        do {
	            x = x + " " + in.nextLine();
	        }while(in.hasNextLine());
	        in.close();
	        String splitted[] = x.split(" ");
	        
	        int level = 0;
	        int score = 0;
	        
	        for (int i = 1; i < splitted.length; i++) {
	            switch (i) {
				case 1:
					level = Integer.parseInt(splitted[i]);
					break;
				case 2:
					score = Integer.parseInt(splitted[i]);
					break;
				}
	        }
	        lm.loadLevel(level, game);
	        Game.reload = 1;
	        Game.score = score;
	        game.enterState(1);
		}else {
			JOptionPane.showMessageDialog(null, "Nemáš uloenou hru!", "Neexistuje save!", JOptionPane.WARNING_MESSAGE);
		}
	}
	

}
