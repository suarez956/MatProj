package core.LevelRendering;

import java.io.File;

public class TileSet {
	private File texture;
	private int id;
	private int width;
	private int height;
	private String name;

	public TileSet(String name, File texture, int id, int width, int height) {
		this.texture = texture;
		this.id = id;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public File getTexture() {
		return texture;
	}

	public void setTexture(File texture) {
		this.texture = texture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
