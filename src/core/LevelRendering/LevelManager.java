package core.LevelRendering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import core.GameStart;
import core.entities.Player;
import core.items.Gem;
import it.randomtower.engine.entity.Entity;

public class LevelManager extends Entity{

	private ArrayList<Entity> enemies;
	private ArrayList<Gem> gems = new ArrayList<>();
	private ArrayList<File> levels;
	private Player player;
	private Map mapa;
	private ArrayList<Tile> tiles;
	private int level_index = 0;
	private StateBasedGame game;
	private FilenameFilter filter = new FilenameFilter() {
		
		@Override
		public boolean accept(File dir, String name) {
			if(name.endsWith(".txt")) {
				return true;
			}
			else return false;
		}
	};
	
	public LevelManager(float x, float y) {
		super(x, y);
		System.out.println("Loaded LevelManager");
	}
	
	public void loadLevels() {
		levels = new ArrayList<>(Arrays.asList(new File("res/levels").listFiles(filter)));
		System.out.println("Loaded all levels");
	}
	
	public int getArrayListSize() {
		return levels.size();
	}

	public void createMap(String map_path) {
		mapa = new Map(map_path);
		tiles = mapa.getTiles();
	}
	
	
	public void loadLevel(int index, StateBasedGame game){
		File curr_level = levels.get(index);
		this.game = game;
		try {
			Scanner sc = new Scanner(curr_level);
			for (;sc.hasNext();) {
				String[] splitted = sc.nextLine().split(" ");
				for (String string : splitted) {
					if(string.equals("P")) {
						try {
							player = new Player(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new SpriteSheet(splitted[1], 50, 100), ((GameStart) game).get_config());
						} catch (Exception e) {
							System.err.println("Nepovedlo se vytvořit hráče!");
						}
					}
					if(string.equals("M")) {
						createMap(splitted[1]);
						}
					if(string.equals("G")) {
						try {
							gems.add(new Gem(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new Image(splitted[1]), false));
						} catch (Exception e) {
							System.err.println("Nepodařilo se přidat další gem!");
						}
					}
					}
				} 
			sc.close();	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void loadNextLevel() throws Exception {
		level_index++;
		try {
			if (level_index>levels.size()-1) throw new Exception("Není zde level");
			levels = null;
			enemies=null;
			player=null;
			mapa = null;
			tiles=null;
			loadLevel(level_index, game);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	//Gettry,settry
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	 public ArrayList<Gem> getGems(){
		 return gems;
	 }
}
