package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.LevelRendering.LevelManager;
import core.other.Config;
import core.other.Config_Window;
import core.state.Game;
import core.state.GameCompleted;
import core.state.Intro;
import core.state.LevelDone;
import core.state.LevelFailed;
import core.state.PauseMenu;
import it.randomtower.engine.ME;

/**
 *
 * @author honza
 */
public class GameStart extends StateBasedGame {

	/**
	 *
	 * @param name
	 */
	public GameStart(String name) {
		super(name);
	}

	static Config cfg = new Config("config.cfg");
	public static Config_Window config_window;
	static AppGameContainer app;
	public boolean show_fps = false;
	public static LevelManager lm = new LevelManager(0, 0);
	
	public boolean getShow_FPS() {
		return show_fps;
	}
	public Config get_config() {
		return cfg;
	
	}
	public LevelManager getLM() {
		return lm;
	}
	/**
	 *
	 * @param args
	 * @throws SlickException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws SlickException, FileNotFoundException {
		System.setProperty("java.library.path", "lib"); 
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		cfg.load();
		config_window = new Config_Window(cfg);
		ME.debugEnabled = false;
		app = new AppGameContainer(new GameStart("Project Caveman"), 800,600, false);
		app.setTargetFrameRate(60);
		app.start();
		app.setShowFPS(true);
	}

	/**
	 *
	 * @param gc
	 * @throws SlickException
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Intro(0, gc));
		addState(new Game(1, gc));
		addState(new LevelDone(2, gc));
		addState(new LevelFailed(3, gc));
		addState(new GameCompleted(4, gc));
		addState(new PauseMenu(5, gc));
	}
	
	/**
	 *
	 * @param key
	 * @param c
	 */
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if (key == Input.KEY_F5) {
			if (app.isFullscreen()) {
				try {
					app.setFullscreen(false);
				} catch (SlickException ex) {
					Logger.getLogger(GameStart.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				try {
					app.setFullscreen(true);
				} catch (SlickException ex) {
					Logger.getLogger(GameStart.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		if (key == Input.KEY_F1) {
			show_fps = !show_fps;
		}
		/*if (key == Input.KEY_M){
			ME.debugEnabled = !ME.debugEnabled;
		}*/
		if (key == Input.KEY_F8) {
			app.setSoundVolume(app.getSoundVolume()-0.5f);
		}
		if (key == Input.KEY_F9) {
			app.setSoundVolume(app.getSoundVolume()+0.5f);
		}
	}
}
