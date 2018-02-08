package JumpNShootMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class JumpNShootMan extends JPanel{
    //Main Game Variables
        //Character starting position
        int cx = 270;
        int cy = 380;
        int ceiling = 10;
        int groundFloor = 480;
        int gameScore = 0;
        boolean gameOver;
        int velocity = 20; //jump strength
        int gravity = 1; //weight
    //Image Variables
        Image Megaman;
        Image gameScreen;
    //User input variables
        int leftArrow = 0;
        int rightArrow = 0;
        int upArrow = 0;
        int spaceBar = 0;
        int escKey = 0;
    //Timer and delay
        Timer timerEv;       
        
    public static void main(String[] args) {
        JumpNShootMan mainPanel = new JumpNShootMan();
        //Setup main window and display to the screen
        JFrame window = new JFrame();
        window.setTitle("JumpNShootMan");
        window.setSize(650, 700); //Horizontal and vertical pixal size
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null); //Centers game window on the screen
        window.setResizable(false); //Cannot resize game window
        window.add(mainPanel);
        window.setVisible(true);
    }    
    public JumpNShootMan(){
        setBackground(Color.BLACK);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressedEvent(e);
            }            
            @Override
            public void keyReleased(KeyEvent e) {
                keyReleasedEvent(e);
            }
        });
        setFocusable(true); //This window can receive input from the keyboard
        setDoubleBuffered(true); //Performs clean screen buffering
        //Megaman images
        ImageIcon ii = new 
        ImageIcon("C:\\Users\\Justin\\Documents\\BPCC Work\\College S18\\CIT 260 Interactive Program Design\\JumpNShootMan Files\\JumpNShootMan\\src\\JumpNShootMan\\images\\MegamanIdle1.png");
        Megaman = ii.getImage();
        
        timerEv = new Timer(16, new TimerClass()); //Time given in milliseconds
        timerEv.start();
        }    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Draw the screen here
        g.drawImage(Megaman, cx, cy, this);
        Toolkit.getDefaultToolkit().sync();
    }
    public void eventFrame(){   
        //PLAYER EVENTS
            //Jumping
            if (upArrow == 1){
                cy -= velocity; //Move the player on the y-axis based on the strength of the jump
                velocity -= gravity; //Gradually decreases the strength of the jump 
                if (cy > groundFloor) //Keeps player from going off the bottom of the screen
                    cy = groundFloor;
                if (cy < ceiling) //Keeps player from going off the top of the screen
                    cy = ceiling;            
            }
            if (cy < groundFloor && upArrow == 0){
                cy -= velocity; //Move the player on the y-axis based on the strength of the jump
                velocity -= gravity; //Gradually decreases the strength of the jump             
            }
            if (cy >= groundFloor){
                velocity = 20; //Resets velocity when player hits the ground
            }
            //Movement
            if (leftArrow == 1 && rightArrow == 0) {
                cx = cx - 4;
                if (cx < 5)
                cx = 5;
            }
            if (leftArrow == 0 && rightArrow == 1) {
                cx = cx + 4;
                if (cx > 620)
                cx = 620;
            }        
            //Shooting
            if (spaceBar == 1){
                //Shooting 
            }
        //ENEMY EVENTS
            //Spawn
            //Movement
            //Collision
            //Kill
        //OTHER EVENTS
            //Exit
            if (escKey == 1)
                System.exit(0);

            //Check for game over
            //if (gameOver){
                //timerEv.stop();
            //}
        repaint(); //Calls the paintComponent method
    }
    public void keyPressedEvent(KeyEvent e){
        int cd = e.getKeyCode();
        if (cd == KeyEvent.VK_UP) //jump
            upArrow = 1;
        if (cd == KeyEvent.VK_LEFT) //move left
            leftArrow = 1;
        if (cd == KeyEvent.VK_RIGHT) //move right
            rightArrow = 1;       
        if (cd == KeyEvent.VK_SPACE) //shoot
            spaceBar = 1;
        if (cd == KeyEvent.VK_ESCAPE) //exit game
            escKey = 1;
    }    
    public void keyReleasedEvent(KeyEvent e){
        int cd = e.getKeyCode();
        if (cd == KeyEvent.VK_UP)
            upArrow = 0;
        if (cd == KeyEvent.VK_LEFT)
            leftArrow = 0;
        if (cd == KeyEvent.VK_RIGHT)
            rightArrow = 0;        
        if (cd == KeyEvent.VK_SPACE)
            spaceBar = 0;
    }
    class TimerClass implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            eventFrame();
        }
    }
}
