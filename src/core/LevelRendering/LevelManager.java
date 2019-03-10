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
import core.entities.*;
import core.items.*;
import it.randomtower.engine.entity.Entity;

public class LevelManager extends Entity{

	private ArrayList<Spider> spiders;
	private ArrayList<Skeleton> skeletons;
	private ArrayList<Gem> gems = new ArrayList<>();
	private ArrayList<Health> healths = new ArrayList<>();
	private ArrayList<File> levels;
	private ArrayList<Weapon> weapons;
	private Player player;
	private Map mapa;
	private ArrayList<Tile> tiles;
	private int level_index = 0;
	private StateBasedGame game;
	private Gem ender;
	
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
		level_index = index;
		spiders = new ArrayList<>();
		skeletons = new ArrayList<>();
		gems = new ArrayList<>();
		healths = new ArrayList<>();
		tiles = new ArrayList<>();
		weapons = new ArrayList<>();
		this.game = game;
		try {
			Scanner sc = new Scanner(curr_level);
			for (;sc.hasNext();) {
				String[] splitted = sc.nextLine().split(" ");
				for (String string : splitted) {
					if(string.equals("H")) {
						try {
							if (player != null) {
								player.x = Integer.parseInt(splitted[2]);
								player.y = Integer.parseInt(splitted[3]);
							}else {
								player = new Player(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new SpriteSheet(splitted[1], 50, 100), ((GameStart) game).get_config());
							}
							
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
					if (string.equals("Z")) {
						try {
							healths.add(new Health(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new Image(splitted[1])));
						} catch (Exception e) {
							System.err.println("Nelze přidat Pickup Item - health");
						}
					}
					if (string.equals("P")) {
						System.out.println(string.length());
						try {
							if(splitted.length == 4) {
								spiders.add(new Spider(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new SpriteSheet(splitted[1], 75, 75)));
							}else {
								spiders.add(new Spider(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new SpriteSheet(splitted[1], 75, 75), Integer.parseInt(splitted[4])));
							}
						} catch (Exception e) {
							System.err.println("Nepodařilo se přidat pavouka!");
						}
					}
					if(string.equals("K")) {
						try {
							if(splitted.length == 4) {
								skeletons.add(new Skeleton(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new SpriteSheet(splitted[1], 100, 125)));
							}else {
								skeletons.add(new Skeleton(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new SpriteSheet(splitted[1], 100, 125), Integer.parseInt(splitted[4])));
							}
						} catch (Exception e) {
							System.err.println("Nepodařilo se přidat kostlivca!");
						}
					}
					if (string.equals("GE")) {
						try {
							ender = new Gem(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new Image(splitted[1]), true);
						} catch (Exception e) {
							System.err.println("Nepodařilo se přidat další gem!");
						}
					}
					if(string.equals("W")) {
						try {
							weapons.add(new Weapon(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), new Image(splitted[1]), Integer.parseInt(splitted[4])));
						} catch (Exception e) {
							System.err.println("Nepodařilo se přidat další zbraň!");
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
	
	public boolean loadNextLevel() throws Exception {
		level_index++;
		try {
			if (level_index>levels.size()-1) throw new Exception("Není zde level");
			spiders = new ArrayList<>();
			skeletons = new ArrayList<>();
			gems = new ArrayList<>();
			healths = new ArrayList<>();
			player=null;
			mapa = null;
			ender = null;
			tiles=new ArrayList<>();
			loadLevel(level_index, game);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
	//GETTER, SETTERS
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	 public ArrayList<Gem> getGems(){
		 return gems;
	 }

	public ArrayList<Health> getHealths() {
		return healths;
	}

	public ArrayList<Spider> getSpiders(){
		return spiders;
	}
	
	public ArrayList<Skeleton> getSkeleton(){
		return skeletons;
	}
	
	public ArrayList<Weapon> getWeapon(){
		return weapons;
	}
	
	public Gem getEnder() {
		return ender;
	}

	public int getLevel_index() {
		return level_index;
	}

	
}
