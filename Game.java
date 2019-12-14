/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_First_Java_Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 *
 * @author End User
 */

public class Game extends Canvas implements Runnable, KeyListener{
    
    private Thread thread;
    private boolean gameIsRunning = false;
    public final int WIDTH = 814, HEIGHT = 636;
    private Handler handle = new Handler();
    private MainCharacter hero;
    private Window window;
    
    public Game(){
        init();
        window = new Window(WIDTH, HEIGHT, this);
    }
    
    public void init(){
        addKeyListener(this);
        setFocusable(true); 
        hero = new MainCharacter((short)40, (short)40,
                (short)(WIDTH/2-20), (short)(HEIGHT/2-20), (short)1, (short)150,
                (byte)1, (short)5, "src/mx/itesm/ProyectoFinal/hero.png");
        handle.addSprite(hero);
        
        for(int i = 0; i < 4; i++){
            short x = 0;
            short y = 0;
            byte dir = 0;
            switch(i){
                case 0:
                    x = 40; y = 520; dir = 0; break;
                case 1:
                    x = 40; y = 60; dir = 1; break;
                case 2:
                    x = 720; y = 60; dir = 2; break;
                case 3:
                    x = 720; y = 520; dir = 3; break;
            }
            Enemy e = new Enemy((short)40, (short)40, x, y, 
                    (short)1, (short)20, dir, 
                    "src/mx/itesm/ProyectoFinal/enemy.png");
            handle.addSprite(e);
        }
        
        for(int i = 0; i<2; i++){
            Wall w = new Wall((short)800, (short)20, (short)0, (short)(30 + i*550), 
            "src/mx/itesm/ProyectoFinal/WallH.png");
            handle.addSprite(w);
        }
        for(int i = 0; i < 2; i++){
            Wall w = new Wall((short)20, (short)560, (short)(i*780), (short)(50), 
            "src/mx/itesm/ProyectoFinal/WallV.png");
            handle.addSprite(w);
        }
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        gameIsRunning = true;
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            gameIsRunning = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double ticks = 100.0;
        double ns = 1000000000/ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int seconds = 0;
        while(gameIsRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1){
                tick();
                delta--;
            }
            if(gameIsRunning){
                render();
            }
            frames++;
                            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                handle.enemiesAttack(hero);
                seconds++;
                System.out.println("seconds = " + seconds);
                if (seconds == 5){
                    handle.generateCollectibles();
                    seconds = 0;
                }
            }
        }
        this.stop();
    }
    
    private void tick(){
        
        handle.updateSprites();
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.BLACK); 
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handle.heroCollisions(hero, g);
        handle.enemyCollisions();
        handle.drawSprites(g);
        
        g.dispose();
        bs.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                hero.setDirection((byte) 0);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection((byte) 1);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection((byte) 2);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection((byte) 3);
                break;
            case KeyEvent.VK_SPACE:
                try{
                    handle.addFriendlyBullet(hero.shoot());
                }catch(NullPointerException ex){
                    
                }
                break;
            case KeyEvent.VK_R:
                gameIsRunning = false;
                window.dispose();
                new Game();
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    
    
    public static void main(String[] args){
        new Game();
    }
    
}
