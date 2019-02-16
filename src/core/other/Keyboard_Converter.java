package core.other;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
/**
 * 
 * <h1>Converts key from Java Keyboard layout to Slick2D Keybord layout</h1>
 * 
 * */
public interface Keyboard_Converter{	
	
	/**
	 * 
	 * @param key String version of pressed key
	 **/
	public static int convert_key(String key) {
		if (key.equals("NumPad-0")) {
			return Input.KEY_0;
		}
		else if (key.equals("NumPad-1")) {
			return Input.KEY_1;
		}
		else if (key.equals("NumPad-2")) {
			return Input.KEY_2;
		}
		else if (key.equals("NumPad-3")) {
			return Input.KEY_3;
		}
		else if (key.equals("NumPad-4")) {
			return Input.KEY_4;
		}
		else if (key.equals("NumPad-5")) {
			return Input.KEY_5;
		}
		else if (key.equals("NumPad-6")) {
			return Input.KEY_6;
		}
		else if (key.equals("NumPad-7")) {
			return Input.KEY_7;
		}
		else if (key.equals("NumPad-8")) {
			return Input.KEY_8;
		}
		else if (key.equals("NumPad-9")) {
			return Input.KEY_9;
		}
		else if (key.equals("Numpad /")) {
			return Input.KEY_SLASH;
		}
		else if (key.equals("Numpad *")) {
			return Input.KEY_MULTIPLY;
		}
		else if (key.equals("Numpad -")) {
			return Input.KEY_MINUS;
		}
		else if (key.equals("Numpad +")) {
			return Input.KEY_ADD;
		}
		else if (key=="Up") {
			return Input.KEY_UP;
		}
		else if (key=="Down") {
			return Input.KEY_DOWN;
		}
		else if (key=="Left") {
			return Input.KEY_LEFT;
		}
		else if (key=="Right") {
			return Input.KEY_RIGHT;
		}
		else if(key=="Ctrl") {
			return Input.KEY_LCONTROL;
		}
		else return Keyboard.getKeyIndex(key);
	}
}
