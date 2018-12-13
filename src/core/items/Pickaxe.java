package core.items;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import core.other.Modifier;
import it.randomtower.engine.entity.Entity;

@SuppressWarnings("unused")
public class Pickaxe extends Entity {

	private ArrayList<Modifier> modifiers = new ArrayList<>();
	
	
	
	public Pickaxe(float x, float y, Image image) {
		super(x, y, image);
	}
	
	
	
	

}
