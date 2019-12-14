
package My_First_Java_Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Random;

/**
 *
 * @author End User
 */
public class Handler{
    
    private ArrayList<Sprite> sprites;
    private ArrayList<Bullet> friendlyBullets;
    private Random rand = new Random();
    private Font ammoFont, warningsFont, gameOverFont;
    private String warning = "";
    
    public Handler(){
        ammoFont = new Font("SansSerif", 1, 20);
        warningsFont = new Font("SansSerif", 1, 10);
        gameOverFont = new Font("SansSerif", 1, 50);
        sprites = new ArrayList<>();
        friendlyBullets = new ArrayList<>();
    }
    
    public void drawSprites(Graphics g){
        Sprite sprite = sprites.get(0);
        MainCharacter hero = (MainCharacter)sprite;
        if (hero.getStatus()){
            for(int i = 0; i < sprites.size(); i++){
                Sprite s = sprites.get(i);
                try{
                    g.drawImage(new ImageIcon(s.image).getImage(), s.posX, s.posY, null);
                }catch(NullPointerException e){

                }
            }
            for(int i = 0; i < friendlyBullets.size(); i++){
                Sprite s = friendlyBullets.get(i);
                try{
                    g.drawImage(new ImageIcon(s.image).getImage(), s.posX, s.posY, null);
                }catch(NullPointerException e){

                }
            }
            
            g.setColor(Color.RED);
            g.fillRect(5, 5, hero.getMaxHp()*2, 20);
        
            g.setColor(Color.GREEN);
            g.fillRect(5, 5, hero.getHealth()*2, 20);
            
            g.setColor(Color.WHITE);
            g.setFont(ammoFont);
            
            String s = "Ammo = " + hero.getAmmoQty();
            g.drawString(s, 400, 20);
            
            g.setFont(warningsFont);
            g.drawString(warning, hero.getPosX()-50, hero.getPosY()-15);
        }
    }
    
    public void updateSprites(){
        Sprite sprite = sprites.get(0);
        Character character = (Character)sprite;
        if(character.getStatus()){
            for(int i = 0; i < sprites.size(); i++){
                Sprite s = sprites.get(i);
                try{
                    Class sClass = s.getClass();
                    if(sClass == MainCharacter.class || sClass == Enemy.class){
                        Character c = (Character)s;
                        c.move();
                    }else if (s.getClass() == Bullet.class){
                        Bullet b = (Bullet)s;
                        b.move();
                        short x = b.getPosX();
                        short y = b.getPosY();
                        if(x >= 800 || x <= 00){
                            sprites.remove(i);
                            i--;
                        }
                    }
                }catch(NullPointerException e){
                    
                }
            }
            for(int i = 0; i < friendlyBullets.size(); i++){
                Bullet b = friendlyBullets.get(i);
                try{
                    b.move();
                }catch(NullPointerException e){
                    friendlyBullets.remove(i);
                    warning = "no ammo";
                }
            }
        }
    }
    
    public void heroCollisions(MainCharacter hero, Graphics g){
        for(int i = 0; i < sprites.size(); i++){
           Sprite s = sprites.get(i);
           // sprite dimensions
           int sx = s.getPosX(); int sy = s.getPosY(); 
           int sw = s.getWidth(); int sh = s.getHeight();
           // main character dimensions
           int hx = hero.getPosX(); int hy = hero.getPosY(); 
           int hw = hero.getWidth(); int hh = hero.getHeight();
           //check if they collided
           boolean TLC = sy >= hy && sy <= hy+hh 
                   && sx >= hx && sx <= hx+hw;// Top Left Corner
           boolean TRC = sy >= hy && sy <= hy+hh 
                   && sx+sw >= hx && sx+sw <= hx+hw;// Top Right Corner
           boolean BLC = sy+sh >= hy && sy+sh <= hy+hh 
                   && sx >= hx && sx <= hx+hw;// Bottom Left Corner
           boolean BRC = sy+sh >= hy && sy+sh <= hy+hh 
                   && sx+sw >= hx && sx+sw <= hx+hw;// Bottom Right Corner
           if(TLC || TRC || BLC || BRC){//if any of the corners of the sprite are inside the main characters
               Class classType = s.getClass();
                if(classType == Enemy.class){
                    hero.lowerHealth((short)20);
                    Enemy e = (Enemy)s;
                    e.die();
                    spawnEnemy();
                    sprites.remove(i);
                    i--;
                }else if(classType == Bullet.class){
                    Bullet b = (Bullet)s;
                    hero.lowerHealth(b.getDamage());
                    sprites.remove(i);
                    i--;
                }else if(classType == Ammo.class){
                    warning = "";
                    Ammo a = (Ammo)s;
                    a.giveBonus(hero);
                    sprites.remove(i);
                    i--;
                }else if(classType == HealthKit.class){
                    HealthKit hk = (HealthKit)s;
                    if(hero.getHealth() < hero.getMaxHp()){
                        hk.giveBonus(hero);
                        sprites.remove(i);
                        i--;
                    }
                }else if(classType == Boots.class){
                    Boots b = (Boots)s;
                    b.giveBonus(hero);
                    sprites.remove(i);
                    i--;
                }
                if(hero.getHealth() <= 0){
                    hero.die();
                    g.setColor(Color.white);
                    g.setFont(gameOverFont);
                    g.drawString("Game Over", 250, 300 );
                    g.setFont(ammoFont);
                    g.drawString("Press 'R' to restart", 200, 350);
                    
                }
            }
        }
    }
    
    public void enemyCollisions() {
        for(int i = 0; i < sprites.size(); i++){
            Sprite s = sprites.get(i);
            if(s.getClass() == Enemy.class){
                Enemy e = (Enemy)s;
                // Enemy dimensions
                int ex = e.getPosX(); int ey = e.getPosY(); 
                int ew = e.getWidth(); int eh = e.getHeight();

                for(int j = 0; j < friendlyBullets.size(); j++){
                    Bullet b = friendlyBullets.get(j);
                    // main character dimensions
                    try{
                        int bx = b.getPosX(); int by = b.getPosY(); 
                        int bw = b.getWidth(); int bh = b.getHeight();

                        boolean TLC = by >= ey && by <= ey+eh 
                                && bx >= ex && bx <= ex+ew;// Top Left Corner
                        boolean TRC = by >= ey && by <= ey+eh 
                                && bx+bw >= ex && bx+bw <= ex+ew;// Top Right Corner
                        boolean BLC = by+bh >= ey && by+bh <= ey+eh 
                                && bx >= ex && bx <= ex+ew;// Bottom Left Corner
                        boolean BRC = by+bh >= ey && by+bh <= ey+eh 
                                && bx+bw >= ex && bx+bw <= ex+ew;// Bottom Right Corner
                        if(TLC || TRC || BLC || BRC){
                            e.lowerHealth(b.getDamage());
                            friendlyBullets.remove(j);
                            j--;
                            if(e.getHealth() <= 0) e.die();
                            if(!e.getStatus()){
                                short xPos = (short)(rand.nextInt(740) + 20);
                                short yPos = (short)(rand.nextInt(510) + 50);
                                Ammo ammo = new Ammo((short)20, (short)20, xPos, 
                                        yPos, (short)3,
                                        "src/mx/itesm/ProyectoFinal/ammo.png");
                                sprites.add(ammo);
                                sprites.remove(i);
                                spawnEnemy();
                                i--;
                            }
                        }
                    }catch(NullPointerException exception){

                    }
                }
            }
        }
    }
    
    public void enemiesAttack(MainCharacter main) {
        for(int i = 0; i < sprites.size(); i++){
            Sprite s = sprites.get(i);
            if(s.getClass() == Enemy.class){
                Enemy e = (Enemy)s;
                e.detectEnemy(main);
                if(e.getStatus()){
                    if(e.getPosX() >= 0 && e.getPosX() <= 814 && e.getPosY()>= 0 && e.getPosY() <= 636 )
                        addSprite(e.shoot());
                }
            }
        }
    }
    
    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }
    
    public void addFriendlyBullet(Bullet bullet){
        friendlyBullets.add(bullet);
    }
    
    public void generateCollectibles(){
        float r = rand.nextFloat();
        System.out.println("random = " + r);
        if(r <= 0.7f){
            short xPos = (short)(rand.nextInt(740) + 20);
            short yPos = (short)(rand.nextInt(510) + 50);
            HealthKit hk = new HealthKit((short)20, (short)20, xPos, yPos,
                    (byte)10, "src/mx/itesm/ProyectoFinal/medkit.png");
            sprites.add(hk);
        }else if (r <= 0.8f){
            short xPos = (short)(rand.nextInt(740) + 20);
            short yPos = (short)(rand.nextInt(510) + 50);
            Boots b = new Boots((short)20, (short)20, xPos, yPos, (byte)1, 
                    "src/mx/itesm/ProyectoFinal/Boots.png");
            sprites.add(b);
        }else{
            short xPos = (short)(rand.nextInt(740) + 20);
            short yPos = (short)(rand.nextInt(510) + 50);
            Ammo a = new Ammo((short)20, (short)20, xPos, yPos, (short)3,
                                 "src/mx/itesm/ProyectoFinal/ammo.png");
            sprites.add(a);
        }
    }

    private void spawnEnemy() {
        int r = rand.nextInt(4);
        short x, y;
        switch(r){
            case 0:
                x = 400; y = 1100;
                break;
            case 1:
                x = -500; y = 300;
                break;
            case 2:
                x = 400; y = 1100;
                break;
            case 3:
                x = 1300; y = 300;
                break;
            default:
                x = 0; y = 0;
                break;
        }
        Enemy e = new Enemy((short)40, (short)40, x, y, (short)1, (short)20,
                            (byte)r, "src/mx/itesm/ProyectoFinal/enemy.png");
        addSprite(e);
    }
    
    
}