package core.other;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


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
    private javax.swing.JLabel label_right;
    private javax.swing.JLabel window_name;
    private javax.swing.JButton button_save;
    private javax.swing.JButton button_special;
    private javax.swing.JLabel label_special;
    private javax.swing.JButton button_default;
    //END	
    
    //KEYS
    private int key_button = 0; //LEFT=>0;RIGHT=>1;JUMP=>2;DOWN=>3;SPECIAL=>4
    private int key;
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
			key = Keyboard_Converter.convert_key(e.getKeyText(e.getKeyCode()));
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
        this.key_up = config.getKey_up();
        this.key_down = config.getKey_down();
        this.key_left = config.getKey_left();
        this.key_right = config.getKey_right();
        this.key_special = config.getKey_special();
    	init();
     }
     //END
    
     //INIT
    /**
     * Inicialization of components
     */
    private void init(){
    	//Font
    	Font UIFont_awt;
        try {
        UIFont_awt = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/PixelGameFont.ttf"));
        UIFont_awt = UIFont_awt.deriveFont(java.awt.Font.PLAIN, 45); 
        }catch (Exception e) {
        	e.printStackTrace();
        	UIFont_awt = new java.awt.Font("Garuda", 0, 16);
		}
        
    	window_name = new javax.swing.JLabel();
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

    	
        window_name.setFont(UIFont_awt); // NOI18N
        window_name.setText("Configuration");

        label_jump.setFont(UIFont_awt.deriveFont(16f)); // NOI18N
        label_jump.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_jump.setText("Jump");

        button_jump.setFont(UIFont_awt.deriveFont(16f)); // NOI18N

        label_left.setFont(UIFont_awt.deriveFont(16f)); // NOI18N
        label_left.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_left.setText("Left");

        button_left.setFont(UIFont_awt.deriveFont(16f)); // NOI18N

        button_right.setFont(UIFont_awt.deriveFont(16f)); // NOI18N

        label_right.setFont(UIFont_awt.deriveFont(16f)); // NOI18N
        label_right.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_right.setText("Right");

        button_down.setFont(UIFont_awt.deriveFont(16f)); // NOI18N

        label_down.setFont(UIFont_awt.deriveFont(16f)); // NOI18N
        label_down.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_down.setText("Down");
        
        label_special.setFont(UIFont_awt.deriveFont(16f)); // NOI18N
        label_special.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_special.setText("Special Button");

        button_special.setFont(UIFont_awt.deriveFont(16f));
        button_save.setFont(UIFont_awt.deriveFont(16f)); // NOI18N
        button_default.setFont(UIFont_awt.deriveFont(16f)); // NOI18N
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(48)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(button_default, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        					.addGap(38)
        					.addComponent(button_save, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(27)
        							.addComponent(label_special))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(42)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(label_jump, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        								.addComponent(label_left, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        					.addGap(29)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(button_special, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(button_jump, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(button_left, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
        					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(label_down)
        							.addGap(29)
        							.addComponent(button_down, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(label_right)
        							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(button_right, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))))
        			.addGap(74))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(277)
        			.addComponent(window_name)
        			.addContainerGap(288, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(window_name, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        			.addGap(74)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(button_jump, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        						.addComponent(label_jump))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(button_left, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        						.addComponent(label_left))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(button_special, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        						.addComponent(label_special)))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(button_right, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        						.addComponent(label_right))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(button_down, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        						.addComponent(label_down))))
        			.addGap(75)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(button_save, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        				.addComponent(button_default, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(176, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
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
}