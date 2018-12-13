package core.other;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import javax.swing.JOptionPane;

import org.newdawn.slick.Input;

/**
 *
 * @author honza
 */
public class Config {

    //CONFIG PARAMATERS
    private int width = 0;
    private int height = 0;
    private static int key_up = 0;
    private static int key_down = 0;
    private static int key_left = 0;
    private static int key_right = 0;
    private static int key_special = 0;
    //END

    //CONFIG FILE
    private File config_file;
	private Scanner in;
    
    /**
     *
     * @param config_path
     */
    public Config(String config_path) {
        this.config_file = new File(config_path);
        try {
            if (!this.config_file.exists()) {
                this.config_file.createNewFile();
                defaults();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //END

    //PARSING

    /**
     *
     * @throws FileNotFoundException
     */
    public void load() throws FileNotFoundException {
        in = new Scanner(new FileReader(config_file));
        String x = "";
        for (@SuppressWarnings("unused")
		int i = 0; in.hasNextLine(); i++) {
            x = x + " " + in.nextLine();
        }
        String splitted[] = x.split(" ");
        for (int i = 1; i < splitted.length; i++) {
            switch (i) {
                case 1:
                    height = Integer.parseInt(splitted[i]);
                    //System.out.println("H: "+height);
                    break;
                case 2:
                    width = Integer.parseInt(splitted[i]);
                    //System.out.println("W: "+width);
                    break;
                case 3:
                    key_up = Integer.parseInt(splitted[i]);
                    //System.out.println("K_UP: "+key_up);
                    break;
                case 4:
                    key_down = Integer.parseInt(splitted[i]);
                    //System.out.println("K_DOWN: "+key_down);
                    break;
                case 5:
                    key_left = Integer.parseInt(splitted[i]);
                    //System.out.println("K_LEFT: "+key_left);
                    break;
                case 6:
                    key_right = Integer.parseInt(splitted[i]);
                    //System.out.println("K_RIGHT: "+key_right);
                    break;
                case 7:
                	key_special = Integer.parseInt(splitted[i]);
                	//System.out.println("K_SPECIAL: "+key_special);
                	break;
            }
        }
    }
    //END

    //DEFAULT VALUES FOR CONFIG
    protected final void defaults() throws IOException {
        height = 800;
        width = 600;
        key_up = Input.KEY_UP;
        key_down = Input.KEY_DOWN;
        key_left = Input.KEY_LEFT;
        key_right = Input.KEY_RIGHT;
        key_special = Input.KEY_LCONTROL;
        
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(Integer.toString(height), Integer.toString(width), Integer.toString(key_up), Integer.toString(key_down), Integer.toString(key_left), Integer.toString(key_right),Integer.toString(key_special)));
        try {
            if (this.config_file.exists()) {
            	Files.delete(config_file.toPath());
            	config_file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(config_file));
        for (int i = 0; i < lines.size(); i++) {
            bw.write(lines.get(i));
            bw.write("\n");
        }
        bw.close();
        JOptionPane.showMessageDialog(null, "Základní nastavení obnoveno.");
    }
    //END
    //SAVE
    
    public void save() throws IOException{
    	ArrayList<String> lines = new ArrayList<>(Arrays.asList(Integer.toString(height), Integer.toString(width), Integer.toString(key_up), Integer.toString(key_down), Integer.toString(key_left), Integer.toString(key_right),Integer.toString(key_special)));
        try {
            if (this.config_file.exists()) {
            	Files.delete(config_file.toPath());
            	config_file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(config_file));
        for (int i = 0; i < lines.size(); i++) {
            bw.write(lines.get(i));
            bw.write("\n");
        }
        bw.close();
    	JOptionPane.showMessageDialog(null, "ÃšspÄ›Å¡nÄ› uloÅ¾eno.");
    }
    
    
    
    //END
    
    //DEBUG-ONLY

    /**
     * Debug purpose -> Deletes config file
     */
    public void debug_delete_config() {
        try {
            Files.delete(config_file.toPath());
            System.out.println("Config has been deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //END
    
    //GETTERS

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return
     */
    public int getKey_down() {
        return key_down;
    }

    /**
     *
     * @return
     */
    public int getKey_left() {
        return key_left;
    }

    /**
     *
     * @return
     */
    public int getKey_right() {
        return key_right;
    }

    /**
     *
     * @return
     */
    public int getKey_up() {
        return key_up;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return
     * 
     */
    public int getKey_special() {
		return key_special;
	}
    
    public String getFileRes() {
    	return Integer.toString(getHeight())+Integer.toString(getWidth());
    	
    	
    }
    
    
    
    
    //END
    
    //SETTERS

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @param key_down
     */
    public void setKey_down(int key_down) {
        Config.key_down = key_down;
    }

    /**
     *
     * @param key_left
     */
    public void setKey_left(int key_left) {
        Config.key_left = key_left;
    }

    /**
     *
     * @param key_right
     */
    public void setKey_right(int key_right) {
        Config.key_right = key_right;
    }

    /**
     *
     * @param key_up
     */
    public void setKey_up(int key_up) {
        Config.key_up = key_up;
    }
    /**
     * 
     *@param key_special 
     **/
    public void setKey_special(int key_special) {
    	Config.key_special = key_special;
	}
    
}
