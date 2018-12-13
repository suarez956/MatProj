package core.LevelRendering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map {

	private ArrayList<Tile> tiles = new ArrayList<>();
	private ArrayList<TileSet> tile_settes = new ArrayList<>();
	private File map;

	public Map(String map_path) {
		this.map = new File(map_path);
		try {
			this.loadMap();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	private void loadMap() throws FileNotFoundException, SlickException {
		Scanner sc = new Scanner(new FileReader(map));
		do {
			String line = sc.nextLine();
			if (line.equals("#tileset")) {
				File texture = null;
				int id = 0;
				int width = 0;
				int height = 0;
				String name = null;
				String[] splitted;
				do {
					splitted = sc.nextLine().split(" ");
					switch (splitted[0]) {
					case "name":
						name = splitted[1];
						break;

					case "texture":
						texture = new File(splitted[1]);
						break;

					case "width":
						width = Integer.parseInt(splitted[1]);
						break;

					case "height":
						height = Integer.parseInt(splitted[1]);
						break;

					case "id":
						id = Integer.parseInt(splitted[1]);
						break;
					}

				} while (!splitted[0].equals("#end"));
				tile_settes.add(new TileSet(name, texture, id, width, height));
			}
			if (line.equals("#data")) {
				String[] splitted;
				int row = 0;
				do {
					int collumn = 0;
					splitted = sc.nextLine().split(",");
					for (String string : splitted) {
						if (!string.equals("#end")) {
							int parsed = Integer.parseInt(string) - 1;
							if (parsed != -1)
								tiles.add(new Tile(collumn * tile_settes.get(parsed).getWidth(),row * tile_settes.get(parsed).getHeight(),new Image(tile_settes.get(parsed).getTexture().getAbsolutePath())));
							collumn++;
						}
					}
					row++;
				} while (!splitted[0].equals("#end"));
			}
		} while (sc.hasNext());
		sc.close();
	}
}
