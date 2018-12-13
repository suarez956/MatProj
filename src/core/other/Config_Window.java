package core.other;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;


/**
 *
 * @author honza
 */
@SuppressWarnings("serial")

public class Config_Window extends JFrame{
	
	//CONFIG FILE
    private final Config config_file;
    //END
	
	//JFRAME BLOCKS
	private javax.swing.JButton button_down;
    private javax.swing.JButton button_jump;
    private javax.swing.JButton button_left;
    private javax.swing.JButton button_right;
    private javax.swing.JLabel label_down;
    private javax.swing.JLabel label_jump;
    private javax.swing.JLabel label_left;
    private javax.swing.JLabel label_resolution;
    private javax.swing.JLabel label_right;
    private javax.swing.JComboBox<String> res_box;
    private javax.swing.JLabel window_name;
    private javax.swing.JButton button_save;
    private javax.swing.JButton button_special;
    private javax.swing.JLabel label_special;
    private javax.swing.JButton button_default;
    //END	
    
    //KEYS
    private int key_button = 0; //LEFT=>0;RIGHT=>1;JUMP=>2;DOWN=>3;SPECIAL=>4
    private int key;
    private int width = 0;
    private int height = 0;
	private int key_up = 0;
    private int key_down = 0;
    private int key_left = 0;
    private int key_right = 0;
    private int key_special = 0;
    //END
    
    //UNIVERSAL KEY LISTENER
    private KeyListener l = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}
		
		@SuppressWarnings("static-access")
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("KeyEvent Text: "+e.getKeyText(e.getKeyCode()));
			//System.out.println("Converted key: "+Keyboard_Converter.convert_key(e.getKeyText(e.getKeyCode())));
			key = Keyboard_Converter.convert_key(e.getKeyText(e.getKeyCode()));
			System.out.println("Key: "+key);
			//System.out.println("Key name: "+Keyboard.getKeyName(key));
			switch (key_button) {
				case 0:
					key_left = key;
					button_left.removeKeyListener(l);
					button_left.setText(Keyboard.getKeyName(key_left));
					break;
				case 1:
					key_right = key;
					button_right.removeKeyListener(l);
					button_right.setText(Keyboard.getKeyName(key_right));
					break;
				case 2:
					key_up = key;
					button_jump.removeKeyListener(l);
					button_jump.setText(Keyboard.getKeyName(key_up));
					break;
				case 3:
					key_down = key;
					button_down.removeKeyListener(l);
					button_down.setText(Keyboard.getKeyName(key_down));
					break;
				case 4:
					key_special= key;
					button_special.removeKeyListener(l);
					button_special.setText(Keyboard.getKeyName(key_special));
					break;
			
			}
		}
	};
    //END
    
	//CONSTRUCTOR
    /**
     * Constructor
     * @param config Configuration file
     */
    public Config_Window(Config config) {
    	super("Configuration");
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.config_file = config;
    	try {
			this.config_file.load();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	this.width = config.getWidth();
        this.height = config.getHeight();
        this.key_up = config.getKey_up();
        this.key_down = config.getKey_down();
        this.key_left = config.getKey_left();
        this.key_right = config.getKey_right();
        this.key_special = config.getKey_special();
    	init();
    	res_box.setSelectedItem(height+"x"+width);
     }
     //END
    
     //INIT
    /**
     * Inicialization of components
     */
    private void init(){
    	window_name = new javax.swing.JLabel();
        label_resolution = new javax.swing.JLabel();
        res_box = new javax.swing.JComboBox<>();
        label_jump = new javax.swing.JLabel();
        button_jump = new javax.swing.JButton(Input.getKeyName(key_up));
        label_left = new javax.swing.JLabel();
        button_left = new javax.swing.JButton(Input.getKeyName(key_left));
        button_right = new javax.swing.JButton(Input.getKeyName(key_right));
        label_right = new javax.swing.JLabel();
        button_down = new javax.swing.JButton(Input.getKeyName(key_down));
        label_down = new javax.swing.JLabel();
        label_special = new javax.swing.JLabel();
        button_special = new javax.swing.JButton(Input.getKeyName(key_special));
        button_save = new javax.swing.JButton("Save");
        button_default = new javax.swing.JButton("Load Defaults");

    	
        window_name.setFont(new java.awt.Font("Garuda", 0, 48)); // NOI18N
        window_name.setText("Configuration");

        label_resolution.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        label_resolution.setText("Resolution");

        res_box.setFont(new java.awt.Font("Garuda", 0, 13)); // NOI18N
        res_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "800x600", "1024x768", "1280x720", "1366x768", "1680x1050" }));

        label_jump.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        label_jump.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_jump.setText("Jump");

        button_jump.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N

        label_left.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        label_left.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_left.setText("Left");

        button_left.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N

        button_right.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N

        label_right.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        label_right.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_right.setText("Right");

        button_down.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N

        label_down.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        label_down.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_down.setText("Down");
        
        label_special.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        label_special.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_special.setText("Special Button");

        button_special.setFont(new java.awt.Font("Garuda", 0, 16));
        button_save.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        button_default.setFont(new java.awt.Font("Garuda", 0, 16)); // NOI18N
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(window_name)
                        .addGap(255, 255, 255))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(button_default, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label_left, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label_special)
                                            .addComponent(label_jump))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(button_special, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(button_jump, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(button_left, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(38, 38, 38)
                                .addComponent(button_save, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_resolution)
                                        .addGap(18, 18, 18)
                                        .addComponent(res_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(175, 175, 175))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_down)
                                        .addGap(18, 18, 18)
                                        .addComponent(button_down, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_right)
                                        .addGap(18, 18, 18)
                                        .addComponent(button_right, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(119, 119, 119))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(window_name, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_resolution)
                    .addComponent(res_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_right, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button_right, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_down)
                            .addComponent(button_down, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_jump)
                            .addComponent(button_jump, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_left, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_left))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_special)
                            .addComponent(button_special, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button_save, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_default, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        pack();
        
        button_left.addActionListener((ActionEvent e)->{
        	key_button = 0;
        	button_left.addKeyListener(l);
        });
        button_jump.addActionListener((ActionEvent e)->{
        	key_button = 2;
        	button_jump.addKeyListener(l);
        });
        button_right.addActionListener((ActionEvent e)->{
        	key_button = 1;
        	button_right.addKeyListener(l);
        });
        button_down.addActionListener((ActionEvent e)->{
        	key_button = 3;
        	button_down.addKeyListener(l);
        });
        button_special.addActionListener((ActionEvent e)->{
        	key_button = 4;
        	button_special.addKeyListener(l);
        });
        
        button_default.addActionListener((ActionEvent e)->{
        	try {
				config_file.defaults();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        button_save.addActionListener((ActionEvent e)->{
        	try {
        		String resolution = (String) res_box.getSelectedItem();
        		String splitted[] = resolution.split("x");
        		height = Integer.parseInt(splitted[0]);
        		width = Integer.parseInt(splitted[1]);
        		config_file.setHeight(height);
        		config_file.setWidth(width);
        		config_file.setKey_down(key_down);
        		config_file.setKey_left(key_left);
        		config_file.setKey_right(key_right);
        		config_file.setKey_up(key_up);
        		config_file.setKey_special(key_special);
        		
				config_file.save();
			} catch (IOException el) {
				el.printStackTrace();
			}
        });
        
    }
    //END
}
